package com.model2.mvc.web.chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.user.UserService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@Controller
@RequestMapping("/chat/*")
public class ChatController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public ChatController() {
	}

	@RequestMapping(value = "getPlan", method=RequestMethod.GET)
	public String getPlan(@RequestParam("planId") String planId, Model model) throws Exception {
		System.out.println("getPlan :: "+planId);
		Plan plan = new Plan();
		plan.setPlanId(planId);
		model.addAttribute("plan", plan);
		return "forward:/chat/chat.jsp";
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
