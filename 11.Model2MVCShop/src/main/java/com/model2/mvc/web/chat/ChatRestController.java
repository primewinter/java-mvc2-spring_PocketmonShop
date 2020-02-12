package com.model2.mvc.web.chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Chat;
import com.model2.mvc.service.domain.ChatRoom;
import com.model2.mvc.service.domain.Party;
import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

@RestController
@RequestMapping("/chat/json/*")
public class ChatRestController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	private MongoClient mongoConn;
	private MongoDatabase mongoDB;
	private MongoCollection<Document> coll;
	
	public ChatRestController() {
		System.out.println(this.getClass()+" MongoDB 연결됨-----------------------------");
		this.mongoConn = new MongoClient("localhost", 27017);
		this.mongoDB = mongoConn.getDatabase("Euroverse");
	}

	@RequestMapping(value = "getPlan", method=RequestMethod.GET)
	public String getPlan(@RequestParam("planId") String planId, Model model) throws Exception {
		System.out.println("getPlan :: "+planId);
		Plan plan = new Plan();
		plan.setPlanId(planId);
		model.addAttribute("plan", plan);
		return "forward:/chat/chat.jsp";
	}
	
	@RequestMapping(value = "getChatRoomList/{userId}", method=RequestMethod.GET)
	public List<Document> getChatRoom(@PathVariable("userId") String userId, Model model) throws Exception {
		System.out.println("getChatRoom :: "+userId);
		List<Document> chatRoomList = new ArrayList<Document>();
		coll = mongoDB.getCollection("ChatRoom");
		List<String> listUser = new ArrayList<String>();
		listUser.add(userId);
		
		Query findQuery = new Query();
		findQuery.addCriteria(Criteria.where("_id").is(userId));
		
		BasicDBObject query = new BasicDBObject("chatMems", new BasicDBObject("$in", listUser));
		FindIterable<Document> fit = coll.find(query);
		List<Document> docs = new ArrayList<Document>();
		fit.into(docs);
		System.out.println("query 수행한 cur :: "+fit);
		for(Document doc : docs) {
			System.out.println(listUser+"를 포함하는 채팅방 목록 : "+doc);
			chatRoomList.add(doc);
		}
		return chatRoomList;
	}
	
	@RequestMapping(value="getChat/{_id}", method=RequestMethod.GET)
	public List<Document> getChat(@PathVariable("_id") String _id) throws Exception{
		System.out.println("getChat :: "+_id);

		coll = mongoDB.getCollection("Chat");
		BasicDBObject query = new BasicDBObject("chatRoomId", _id);
		FindIterable<Document> fit = coll.find(query).limit(100);
		List<Document> docs = new ArrayList<Document>();
		fit.into(docs);
		for (Document doc : docs) {
			System.out.println("채팅방id 가 "+_id+"인 채팅 메시지 : "+doc);
		}
		// _id의 값을 사용하여 오름차순으로 정렬
		//db.orders.find().sort( { "_id": 1 } )
//		amount 값을 사용하여 오름차순으로 정렬하고, 정렬한 값에서 id 값은 내림차순으로 정렬하기
//		db.orders.find().sort( { "amount": 1, "_id": -1 } )
		
		return docs;
	}
	
	@RequestMapping(value="getChat/{_id}", method=RequestMethod.POST)
	public List<Document> getChat(@PathVariable("_id") String _id, @RequestParam int page) throws Exception{
		System.out.println("getChat :: "+_id);
		
		coll = mongoDB.getCollection("Chat");
		BasicDBObject query = new BasicDBObject("_id", _id);
		FindIterable<Document> fit = coll.find(query).sort(new BasicDBObject("chatDate", -1))
				.skip((page-1)*100).limit(100);
		// page 100의 배수????? **************************
		List<Document> docs = new ArrayList<Document>();
		fit.into(docs);
		for (Document doc : docs) {
			System.out.println("채팅방id 가 "+_id+"인 채팅 메시지 : "+doc);
		}
		// _id의 값을 사용하여 오름차순으로 정렬
		//db.orders.find().sort( { "_id": 1 } )
//		amount 값을 사용하여 오름차순으로 정렬하고, 정렬한 값에서 id 값은 내림차순으로 정렬하기
//		db.orders.find().sort( { "amount": 1, "_id": -1 } )
		
		return docs;
	}
	
	@RequestMapping(value="createRoom", method=RequestMethod.POST)
	public void createRoom(@RequestBody ChatRoom chatRoom) throws Exception {
		
		System.out.println("createRoom :: ChatRoom :"+chatRoom);
		
		List<String> chatMems = chatRoom.getChatMems();
		
		coll = mongoDB.getCollection("ChatRoom");
		System.out.println("ChatRoom DB 갖고옴");
		
		Document doc = new Document("creator", chatRoom.getCreator())
												.append("chatMems", chatMems)
												.append("chatRoomName", chatRoom.getChatRoomName())
												.append("createdDate", new Date());

		System.out.println("Document : "+doc);
		coll.insertOne(doc);
		System.out.println("insert한 Document "+doc);
	}
	
	
	@RequestMapping(value="joinChatRoom", method=RequestMethod.POST)
	public void joinChatRoom(@RequestBody ChatRoom chatRoom) throws Exception {
		// 방장이 강제로 참여시키는 거
		System.out.println("joinChatRoom :: ChatRoom"+chatRoom);
		// chatRoom 에 들어있을 정보 : chatRoomId, 새로 참여시킬 회원id
		// b/l : DB에서 chatRoomId 의 chatMems 를 불러와서 거기에 새로 참여시킬 회원 id를 넣고 update
		coll = mongoDB.getCollection("ChatRoom");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append("_id", new BasicDBObject("$toString", "$"+chatRoom.get_id()));
		FindIterable<Document> cur = coll.find(searchQuery);
		Document doc = cur.first();
		List<String> arr = (List<String>) doc.get("chatMems");
		arr.add(chatRoom.getChatMems().get(0));
		
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set", 
			new BasicDBObject().append("chatMems", arr));
		coll.updateMany(searchQuery, updateQuery);			
		
	}
	
	@RequestMapping(value="quitChatRoom", method=RequestMethod.POST)
	public void quitChatRoom(@RequestBody ChatRoom chatRoom) throws Exception {
		// 강퇴
		System.out.println("quitChatRoom :: ChatRoom"+chatRoom);
		
		coll = mongoDB.getCollection("ChatRoom");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append("_id", chatRoom.get_id());
		FindIterable<Document> cur = coll.find(searchQuery);
		Document doc = cur.first();
		List<String> arr = (List<String>) doc.get("chatMems");
		System.out.println("remove 전 : "+arr);
		arr.remove(chatRoom.getChatMems().get(0));
		System.out.println("remove 후 : "+arr);
		
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set", 
			new BasicDBObject().append("chatMems", arr));
		coll.updateMany(searchQuery, updateQuery);	
		
	}
	
	@RequestMapping(value="readChat", method=RequestMethod.POST)
	public void readChat(@RequestBody Chat chat) throws Exception {
		System.out.println("readChat :: "+chat);
		
		coll = mongoDB.getCollection("Chat");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", chat.get_id());
		FindIterable<Document> cur = coll.find(query);
		Document doc = cur.first();
		List<String> arr = (List<String>) doc.get("readers");
		System.out.println("read 전 : "+arr);
		arr.add(chat.getSenderId());
		System.out.println("read 후 : "+arr);
		
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set", 
			new BasicDBObject().append("readers", arr));
		coll.updateMany(query, updateQuery);	
		
		
	}
	
	public void connectMongoDB(String chatId) throws Exception {
		
		// connection을 위한 instance 생성
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		
		// dataBase 접근 : "chat" = 특정 database 이름
		MongoDatabase database = mongoClient.getDatabase("chat");
		
		// Collection 접근 : "user" = 특정 collection 이름
		MongoCollection<Document> collection = database.getCollection("user");
		
		
		// document 만들기
		// - document 클래스 객체 생성 시 생성자로 필드 이름과 값을 매개변수로 주면 그에 해당하는 JSON 문서가 만들어 진다. 
		// - 값이 배열 형태의 값을 가지고 있을 경우네는 Arrays 클래스의 asList() 메소드를 이용하여 값을 삽입하면 된다.
		// - 하위 필드가 존재할 경우에는 새로운 Document 객체를 만들어서 필드의 값으로 넣어주면 된다.
		Document doc = new Document("content", "안녕 반가워")
				.append("type", "database")
				.append("count", 1)
				.append("read", Arrays.asList("user01", "user02", "user03"))
				.append("info", new Document("x", 203).append("y", 102));
		
		
		// 하나의 문서(DOCUMENT) 저장하기
		collection.insertOne(doc);
		
		// 여러 문서(DOCUMENTS) 저장하기
		List<Document> documents = new ArrayList<Document>();
		 for (int i = 0; i < 100; i++) {
		     documents.add(new Document("i", i));
		 }
		 collection.insertMany(documents);
		
	}
	
	
	
	
	

}
