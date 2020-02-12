package com.model2.mvc.service.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
 
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.model2.mvc.service.domain.Fruit;
import com.model2.mvc.service.domain.Taste;
import com.mongodb.MongoClient;
 
 
public class MongoService {
	
	private final int MONGO_PORT = 27017;
	private final String MONGO_HOST = "localhost";
	private final String DB_NAME = "mongogogo";
	
	private MongoClient mongo;
	private MongoOperations mongoOps;
	
	//생성시DB와 연동
	public MongoService() {
		System.out.println("MongoService :: Constructor");
		mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
		mongoOps = new  MongoTemplate(mongo, DB_NAME);
		System.out.println(" >>> mongoOps 생성 완료 ");
	}
	
	
	//연결 끊기
	public void close() {
		try {
			mongo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//DB 컬렉션 모두 출력
	public Set<String> showCollections(){
		return mongoOps.getCollectionNames();
	}
	
	//DB insert
	public void insert(String insertCollection) {
		Fruit fruit = new Fruit();
		fruit.setName("Jamong");
		fruit.setPrice(5000);
		
		Taste taste = new Taste();
		ArrayList<String> tastes = new ArrayList<String>();
		tastes.add("bitter");
		tastes.add("sweet");
		taste.setTastes(tastes);
		
		String[] testing = {"testing1", "testing2"};
		taste.setTesting(testing);
		
		fruit.setTaste(taste);
		
		mongoOps.insert(fruit, insertCollection);
	}
	
	//find one
	public Fruit findOne(String findCollection, Fruit findCondition) {
		Criteria criteria = new Criteria("_id");
		criteria.is(findCondition.get_id());
		
		Query query = new Query(criteria);
		Fruit fruit = mongoOps.findOne(query, Fruit.class, findCollection);
		return fruit;
	}
	
	//find all
	public List<Fruit> findAll(String findCollection){
		return mongoOps.findAll(Fruit.class, findCollection);
	}
	
	//find in condition
	public List<Fruit> findInConditionMongo(String findCollection, Fruit fruit){
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("price").is(fruit.getPrice()),
				Criteria.where("name").is(fruit.getName())
				));
		return mongoOps.find(query, Fruit.class, findCollection);
	}
	
	//remove collection
	public void removeCollection(String deleteCollection) {
		mongoOps.dropCollection(deleteCollection);
	}
	
	//remove data in collection
	public void removeData(String deleteCollection, Fruit condition) {
		Query query = new Query(new Criteria("_id").is(condition.get_id()));
		mongoOps.remove(query, deleteCollection);
	}
}
