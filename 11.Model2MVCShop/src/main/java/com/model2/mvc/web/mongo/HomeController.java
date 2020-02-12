package com.model2.mvc.web.mongo;

import java.util.ArrayList;
import java.util.Set;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
import com.google.gson.Gson;
import com.model2.mvc.service.domain.Fruit;
import com.model2.mvc.service.mongo.MongoService;
 
 
@Controller
@ResponseBody
@RequestMapping("/mongo/*")
public class HomeController {
 
	//@RequestMapping(value = "/mongo", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println(this.getClass());
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
	
	@RequestMapping(value = "showCollectionsMongo", method = RequestMethod.POST)
	public ModelAndView showCollections() {
		System.out.println("showCollectionsMongo :: POST");
		ModelAndView mav = new ModelAndView("jsonView");
		
		MongoService mongoService = new MongoService();
		Set<String> collections = mongoService.showCollections();
		for(String s : collections) {
			System.out.println("collection ::: " + s);
		}
		mongoService.close();
		return mav;
	}
	
	@RequestMapping(value = "insertMongo", method = RequestMethod.POST)
	public ModelAndView insertMongo() {
		ModelAndView mav = new ModelAndView("jsonView");
		
		MongoService mongoService = new MongoService();
		mongoService.insert("testData2");
		mongoService.close();
		return mav;
	}
	
	@RequestMapping(value = "findAllMongo", method = RequestMethod.POST)
	public ModelAndView findAllMongo() {
		ModelAndView mav = new ModelAndView("jsonView");
		
		MongoService mongoService = new MongoService();
		ArrayList<Fruit> fruits = (ArrayList<Fruit>) mongoService.findAll("testData");
		Gson gson = new Gson();
		String result = gson.toJson(fruits);
		System.out.println("result ::: " + result);
		
		mongoService.close();
		return mav;
	}
	
	@RequestMapping(value = "findOneMongo", method = RequestMethod.POST)
	public ModelAndView findMongo() {
		ModelAndView mav = new ModelAndView("jsonView");
		
		MongoService mongoService = new MongoService();
		Fruit condition = new Fruit();
		condition.set_id("5cb31f0285980309d27af0a8");
		Fruit fruit = mongoService.findOne("testData", condition);
		Gson gson = new Gson();
		String result = gson.toJson(fruit);
		System.out.println("result ::: " + result);
		mongoService.close();
		return mav;
	}
	
	@RequestMapping(value = "findInConditionMongo", method = RequestMethod.POST)
	public ModelAndView findInConditionMongo() {
		ModelAndView mav = new ModelAndView("jsonView");
		
		MongoService mongoService = new MongoService();
		Fruit condition = new Fruit();
		condition.setPrice(5000);
		condition.setName("Jamong");
		ArrayList<Fruit> fruits = (ArrayList<Fruit>) mongoService.findInConditionMongo("testData2", condition);
		Gson gson = new Gson();
		String result = gson.toJson(fruits);
		System.out.println("result ::: " + result);
		mongoService.close();
		return mav;
	}
	
	@RequestMapping(value = "removeCollection", method = RequestMethod.POST)
	public ModelAndView removeCollection() {
		ModelAndView mav = new ModelAndView("jsonView");
		
		MongoService mongoService = new MongoService();
		mongoService.removeCollection("testData2");
		mongoService.close();
		return mav;
	}
	
	@RequestMapping(value = "removeData", method = RequestMethod.POST)
	public ModelAndView removeData() {
		ModelAndView mav = new ModelAndView("jsonView");
		
		MongoService mongoService = new MongoService();
		Fruit condition = new Fruit();
		condition.set_id("5cb31f0285980309d27af0a8");
		mongoService.removeData("testData", condition);
		mongoService.close();
		return mav;
	}
}