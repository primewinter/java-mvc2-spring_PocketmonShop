package com.model2.mvc.web.mongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Chat;
import com.model2.mvc.service.domain.Push;
import com.model2.mvc.web.chat.WebSocket;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
/**
 * This is proof-of-concept for extracting the data from an Oracle 
 * database.
 * @author naveen_v01
 *
 */
@ServerEndpoint("/accSocket/{roomId}/{userId}")
public class AccChatSocket {
	// K : roomId , V : 채팅방에 입장한 session
	private static Map<String, List<Session>> accMap = Collections.synchronizedMap(new HashMap<>());
	private static WebSocket webSocket = new WebSocket();

	public void sendChat(Chat chat) throws Exception {
		System.out.println("chat/json/sendChat :: @RequestBody Chat : "+chat);
		
		// 몽고디비랑 연결 있으면 가져오고 없으면 만들기
		MongoClient mongoConn = new MongoClient("localhost", 27017);
		MongoDatabase mongoDB = mongoConn.getDatabase("Euroverse");
		
		// 컬렉션 있으면 가져오고 없으면 새로만들기 
		MongoCollection<Document> coll = mongoDB.getCollection("chat");
		Bson filter = Filters.eq("_id",chat.getChatId());
		Bson update = new Document("$set", new
				Document("_id", chat.getChatId()) 
				.append("chatContent", chat.getChatContent())
				.append("chatDate", chat.getChatDate())
				.append("senderId", chat.getSenderId())
				.append("chatRoomId", chat.getChatRoomId()));
				
		UpdateOptions options = new UpdateOptions().upsert(true); // 있으면 update, 없으면 insert
		System.out.println("들어감2");

		//coll.insertOne(doc);  // 이놈은 73번부터 77번까지 오라클에있는 내용을 그대로  몽고db에 넣음.
		coll.updateOne(filter, update, options);  // 이놈은 84번부터 90번까지에 있는놈인데 중복된건 업데이트하고 없던건 insert 하면서 몽고db에 넣음 .. 이게짱임
		System.out.println("업데이투");
		
		mongoConn.close();
	}
	
	
	// 웹 소켓이 연결되면 호출되는 이벤트
	@OnOpen
	public void handleOpen(@PathParam("roomId") String roomId, @PathParam("userId") String userId, Session session) throws Exception {
				System.out.println("동행채팅 ::: [" + roomId + "] client is now connected...");
				
				List<Session> mapList = accMap.get(roomId); // 같은 roomId의 session 리스트 호출
				if (mapList == null || mapList.size() == 0) { // 없다면 생성
					mapList = new ArrayList<>();
				}
				mapList.add(session); // 생성된 or 호출된 session 리스트에 add
				accMap.put(roomId, mapList);
				
				List<String> userList = getChatMems(roomId);
				
				String message = userId + "님이 입장하셨습니다."; 
				sendToRoom(roomId, message, session);
				sendToUser(userList, "chat");
				
	}

	@OnMessage
	public void handleMessage(@PathParam("roomId") String roomId, @PathParam("userId") String userId, String message, Session session)
			throws Exception {
				// process booking from the given guest here
				System.out.println("[동행 채팅] Client To Client : " + message);
				sendToRoom(roomId, message, session);
				insertMongo(message);
				
				List<String> userList = getChatMems(roomId);
				sendToUser(userList, "chat");

	}

	// 웹 소켓이 닫히면 호출되는 이벤트
	@OnClose
	public void handleClose(@PathParam("roomId") String roomId, @PathParam("userId") String userId, Session session) throws Exception {
				
			System.out.println("[동행 채팅] client is now disconnected...");
			String message = userId + "님이 퇴장하셨습니다."; 
			for (Map.Entry<String, List<Session>> entry : accMap.entrySet()) {
				System.out.println("퇴장하는 getKey :::: " + entry.getKey());
				if (entry.getKey().equals(roomId)) {
					entry.getValue().remove(session);
					for (Session se : entry.getValue()) {
						se.getBasicRemote().sendText(message);
					}
				}
			}
			
			List<String> userList = getChatMems(roomId);
			sendToUser(userList, "chat");
		
	}

	//웹 소켓이 에러가 나면 호출되는 이벤트
	@OnError
	public void handleError(Throwable t) {
				t.printStackTrace();
	}
	
	
	private void sendToRoom(String roomId, String message, Session session) throws Exception {
		
		for (Map.Entry<String, List<Session>> entry : accMap.entrySet()) {
			System.out.println("[동행 채팅] getKey :::: " + entry.getKey()+" || message : "+message);
			if (entry.getKey().equals(roomId)) {
				for (Session se : entry.getValue()) {
					se.getBasicRemote().sendText(message);
					System.out.println(roomId+"에 보낸 메시지 : "+message);
				}
			}
		}
		
	}
	
	private void sendToUser(List<String> users, String type) throws Exception {
		Push push = new Push();
		push.setPushType(type);
		webSocket.sendChat(users, push);
	}
	
	private void insertMongo(String json) throws Exception {
				
				// 몽고디비랑 연결 있으면 가져오고 없으면 만들기
				MongoClient mongoConn = new MongoClient("localhost", 27017);
				MongoDatabase mongoDB = mongoConn.getDatabase("Euroverse");
				Chat chat = jsonToChat(json);
				System.out.println("\n\t\t MongoDB 들어가기 전 Chat ::: "+chat);
				// 컬렉션 있으면 가져오고 없으면 새로만들기 
				MongoCollection<Document> coll = mongoDB.getCollection("Chat");
				List<String> readers = new ArrayList<String>();
				readers.add(chat.getSenderId());
				Document chatToDB = new Document("chatContent", chat.getChatContent())
						.append("chatDate", new Date())
						.append("senderId", chat.getSenderId())
						.append("readers", readers)
						.append("chatRoomId", chat.getChatRoomId()
						);
				/*
				Bson update = new Document("$set", new
						Document("_id", chat.getChatId()) 
						.append("chatContent", chat.getChatContent())
						.append("chatDate", chat.getChatDate())
						.append("senderId", chat.getSenderId())
						.append("chatRoomId", chat.getChatRoomId()));
						
				UpdateOptions options = new UpdateOptions().upsert(true); // 있으면 update, 없으면 insert
				System.out.println("들어감2");
				 */
				coll.insertOne(chatToDB);  // 이놈은 73번부터 77번까지 오라클에있는 내용을 그대로  몽고db에 넣음.
				//coll.updateOne(filter, update, options);  // 이놈은 84번부터 90번까지에 있는놈인데 중복된건 업데이트하고 없던건 insert 하면서 몽고db에 넣음 .. 이게짱임
				System.out.println("업데이투");
				
				mongoConn.close();
	}
	
	private List<String> getChatMems(String roomId) throws Exception {
		
		// 몽고디비랑 연결 있으면 가져오고 없으면 만들기
		MongoClient mongoConn = new MongoClient("localhost", 27017);
		MongoDatabase mongoDB = mongoConn.getDatabase("Euroverse");

		// 컬렉션 있으면 가져오고 없으면 새로만들기 
		MongoCollection<Document> coll = mongoDB.getCollection("ChatRoom");
		BasicDBObject query = new BasicDBObject("chatRoomId", roomId);
		FindIterable<Document> fit = coll.find(query);
		Document doc = fit.first();
		List<String> userIdList = (List<String>)doc.get("chatMems");
		for(String userId : userIdList ) {
			System.out.println(roomId +"에 참여하고 있는 회원 : "+userId);
		}
		mongoConn.close();
		return userIdList;
	}
	
	private Chat jsonToChat(String json) throws Exception {
		JSONObject jsonobj = (JSONObject)JSONValue.parse(json);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Chat chat = objectMapper.readValue(jsonobj.get("chat").toString(), Chat.class);
		System.out.println(":: json > Chat : "+chat);
		
		return chat;
	}
	
	private String chatToJson(Chat chat) throws Exception {
		return new ObjectMapper().writeValueAsString(chat);
	}
	
	
}

