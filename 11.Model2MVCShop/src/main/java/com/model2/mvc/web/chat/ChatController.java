package com.model2.mvc.web.chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.service.domain.Plan;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Controller
@RequestMapping("/chat/*")
public class ChatController {
	
	public ChatController() {
		System.out.println(this.getClass());
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
		// connection�� ���� instance ����
		MongoClient mongoClient = MongoClients.create(
		         MongoClientSettings.builder()
		                 .applyToClusterSettings(builder ->
		                         builder.hosts(Arrays.asList(new ServerAddress("hostOne", 27017))))
		                 .build());
		
		// dataBase ���� : "chat" = Ư�� database �̸�
		MongoDatabase database = mongoClient.getDatabase("chat");
		
		// Collection ���� : "user" = Ư�� collection �̸�
		MongoCollection<Document> collection = database.getCollection("user");
		
		
		// document �����
		//document Ŭ���� ��ü ���� �� �����ڷ� �ʵ� �̸��� ���� �Ű������� �ָ� �׿� �ش��ϴ� JSON ������ ����� ����. 
		Document doc = new Document("name", "MongoDB").append("type", "database").append("count", 1);
		// ���� �迭 ������ ���� ������ ���� ���״� Arrays Ŭ������ asList() �޼ҵ带 �̿��Ͽ� ���� �����ϸ� �ȴ�.
		doc.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"));
		// ���� �ʵ尡 ������ ��쿡�� ���ο� Document ��ü�� ���� �ʵ��� ������ �־��ָ� �ȴ�.
		doc.append("info", new Document("x", 203).append("y", 102));
		
		
		// �ϳ��� ����(DOCUMENT) �����ϱ�
		collection.insertOne(doc);
		
		// ���� ����(DOCUMENTS) �����ϱ�
		List<Document> documents = new ArrayList<Document>();
		 for (int i = 0; i < 100; i++) {
		     documents.add(new Document("i", i));
		 }
		 collection.insertMany(documents);
		
	}
	

}
