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
	// K : roomId , V : ä�ù濡 ������ session
	private static Map<String, List<Session>> accMap = Collections.synchronizedMap(new HashMap<>());
	private static WebSocket webSocket = new WebSocket();

	public void sendChat(Chat chat) throws Exception {
		System.out.println("chat/json/sendChat :: @RequestBody Chat : "+chat);
		
		// ������� ���� ������ �������� ������ �����
		MongoClient mongoConn = new MongoClient("localhost", 27017);
		MongoDatabase mongoDB = mongoConn.getDatabase("Euroverse");
		
		// �÷��� ������ �������� ������ ���θ���� 
		MongoCollection<Document> coll = mongoDB.getCollection("chat");
		Bson filter = Filters.eq("_id",chat.getChatId());
		Bson update = new Document("$set", new
				Document("_id", chat.getChatId()) 
				.append("chatContent", chat.getChatContent())
				.append("chatDate", chat.getChatDate())
				.append("senderId", chat.getSenderId())
				.append("chatRoomId", chat.getChatRoomId()));
				
		UpdateOptions options = new UpdateOptions().upsert(true); // ������ update, ������ insert
		System.out.println("��2");

		//coll.insertOne(doc);  // �̳��� 73������ 77������ ����Ŭ���ִ� ������ �״��  ����db�� ����.
		coll.updateOne(filter, update, options);  // �̳��� 84������ 90�������� �ִ³��ε� �ߺ��Ȱ� ������Ʈ�ϰ� ������ insert �ϸ鼭 ����db�� ���� .. �̰�¯��
		System.out.println("��������");
		
		mongoConn.close();
	}
	
	
	// �� ������ ����Ǹ� ȣ��Ǵ� �̺�Ʈ
	@OnOpen
	public void handleOpen(@PathParam("roomId") String roomId, @PathParam("userId") String userId, Session session) throws Exception {
				System.out.println("����ä�� ::: [" + roomId + "] client is now connected...");
				
				List<Session> mapList = accMap.get(roomId); // ���� roomId�� session ����Ʈ ȣ��
				if (mapList == null || mapList.size() == 0) { // ���ٸ� ����
					mapList = new ArrayList<>();
				}
				mapList.add(session); // ������ or ȣ��� session ����Ʈ�� add
				accMap.put(roomId, mapList);
				
				List<String> userList = getChatMems(roomId);
				
				String message = userId + "���� �����ϼ̽��ϴ�."; 
				sendToRoom(roomId, message, session);
				sendToUser(userList, "chat");
				
	}

	@OnMessage
	public void handleMessage(@PathParam("roomId") String roomId, @PathParam("userId") String userId, String message, Session session)
			throws Exception {
				// process booking from the given guest here
				System.out.println("[���� ä��] Client To Client : " + message);
				sendToRoom(roomId, message, session);
				insertMongo(message);
				
				List<String> userList = getChatMems(roomId);
				sendToUser(userList, "chat");

	}

	// �� ������ ������ ȣ��Ǵ� �̺�Ʈ
	@OnClose
	public void handleClose(@PathParam("roomId") String roomId, @PathParam("userId") String userId, Session session) throws Exception {
				
			System.out.println("[���� ä��] client is now disconnected...");
			String message = userId + "���� �����ϼ̽��ϴ�."; 
			for (Map.Entry<String, List<Session>> entry : accMap.entrySet()) {
				System.out.println("�����ϴ� getKey :::: " + entry.getKey());
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

	//�� ������ ������ ���� ȣ��Ǵ� �̺�Ʈ
	@OnError
	public void handleError(Throwable t) {
				t.printStackTrace();
	}
	
	
	private void sendToRoom(String roomId, String message, Session session) throws Exception {
		
		for (Map.Entry<String, List<Session>> entry : accMap.entrySet()) {
			System.out.println("[���� ä��] getKey :::: " + entry.getKey()+" || message : "+message);
			if (entry.getKey().equals(roomId)) {
				for (Session se : entry.getValue()) {
					se.getBasicRemote().sendText(message);
					System.out.println(roomId+"�� ���� �޽��� : "+message);
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
				
				// ������� ���� ������ �������� ������ �����
				MongoClient mongoConn = new MongoClient("localhost", 27017);
				MongoDatabase mongoDB = mongoConn.getDatabase("Euroverse");
				Chat chat = jsonToChat(json);
				System.out.println("\n\t\t MongoDB ���� �� Chat ::: "+chat);
				// �÷��� ������ �������� ������ ���θ���� 
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
						
				UpdateOptions options = new UpdateOptions().upsert(true); // ������ update, ������ insert
				System.out.println("��2");
				 */
				coll.insertOne(chatToDB);  // �̳��� 73������ 77������ ����Ŭ���ִ� ������ �״��  ����db�� ����.
				//coll.updateOne(filter, update, options);  // �̳��� 84������ 90�������� �ִ³��ε� �ߺ��Ȱ� ������Ʈ�ϰ� ������ insert �ϸ鼭 ����db�� ���� .. �̰�¯��
				System.out.println("��������");
				
				mongoConn.close();
	}
	
	private List<String> getChatMems(String roomId) throws Exception {
		
		// ������� ���� ������ �������� ������ �����
		MongoClient mongoConn = new MongoClient("localhost", 27017);
		MongoDatabase mongoDB = mongoConn.getDatabase("Euroverse");

		// �÷��� ������ �������� ������ ���θ���� 
		MongoCollection<Document> coll = mongoDB.getCollection("ChatRoom");
		BasicDBObject query = new BasicDBObject("chatRoomId", roomId);
		FindIterable<Document> fit = coll.find(query);
		Document doc = fit.first();
		List<String> userIdList = (List<String>)doc.get("chatMems");
		for(String userId : userIdList ) {
			System.out.println(roomId +"�� �����ϰ� �ִ� ȸ�� : "+userId);
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

