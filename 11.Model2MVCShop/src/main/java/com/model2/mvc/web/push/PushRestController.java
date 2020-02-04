package com.model2.mvc.web.push;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.domain.Push;
import com.model2.mvc.service.domain.Todo;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.plan.PlanService;
import com.model2.mvc.service.push.PushService;
import com.model2.mvc.web.chat.WebSocket;

@RestController
@RequestMapping("/push/*")
public class PushRestController {
	
	@Autowired
	@Qualifier("pushServiceImpl")
	private PushService pushService;
	@Autowired
	@Qualifier("planServiceImpl")
	private PlanService planService;

	public PushRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	private static Map<String, TimerTask> checkMap = Collections.synchronizedMap(new HashMap<String, TimerTask>());
	
	
	@RequestMapping(value="json/addPush")
	public void addPush(@RequestBody Push push) throws Exception {
		System.out.println("json/addPush :: @RequestBody Push : "+push);
		pushService.addPush(push);
	}
	
	@RequestMapping(value="json/getUnreadCount")
	public int getUnreadCount(@RequestBody User user) throws Exception {
		System.out.println("json/getUnreadCount :: @RequestBody User : "+user);
		int unreadCount = pushService.getUnreadCount(user.getUserId());

		return unreadCount;
	}
	
	@RequestMapping(value="json/getPushList/{userId}", method=RequestMethod.GET)
	public Map<String, Object> getPushList(@PathVariable("userId")String userId) throws Exception {
		System.out.println("json/getPushList :: @PathVariable : "+userId);
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(pageSize);
		Map<String , Object> map = pushService.getPushList(search, userId);
		System.out.println(" :: pushServic.getPushList(search, userId) 완료");
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", map.get("list"));
		result.put("resultPage", resultPage);
		result.put("search", search);
		
		return result;
	}
	
	@RequestMapping(value="json/getPushList/{userId}", method=RequestMethod.POST)
	public List<Push> getPushList(@PathVariable("userId")String userId, @RequestBody Map<String, Object> jsonMap) throws Exception {
		System.out.println("json/getScrollList_POST :: @PathVariable : "+userId);
		
		ObjectMapper objectmapper = new ObjectMapper();
		Search search = objectmapper.convertValue(jsonMap.get("search"), Search.class);
		System.out.println("json/getScrollList_POST :: @RequestBody Search: "+search);
		
		if(search.getCurrentPage()==0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		Map<String , Object> map = pushService.getPushList(search, userId);
		System.out.println(" :: pushService.getPushList(search, userId) 완료");
		
		return (List<Push>)map.get("list");
	}
	
	@RequestMapping(value="json/readPush/{userId}")
	public void readPush(@PathVariable("userId") String userId) throws Exception {
		System.out.println("json/readPush :: "+userId);
		pushService.readPush(userId);
	}
	
	@RequestMapping(value="json/deletePush")
	public void deletePush(@RequestBody List<String> pushId ) throws Exception {
		System.out.println("json/deletePush :: "+pushId);
		pushService.deletePush(pushId);
	}
	
	@RequestMapping(value="json/getUnreadCount/{userId}")
	public int getUnreadCount(@PathVariable("userId") String userId ) throws Exception {
		System.out.println("json/getUnreadCount :: "+userId);
		return pushService.getUnreadCount(userId);
	}
	
	@RequestMapping(value="json/enterPlan/{planId}")
	public String enterPlan(@PathVariable("planId") String planId ) throws Exception {
		System.out.println("json/enterPlan :: "+planId);
		return planId;
	}
	
	@RequestMapping(value="json/pushPlanRandom/{planId}")
	public void pushPlanRandom(@PathVariable("planId") String planId ) throws Exception {
		Plan plan = new Plan();
		//Plan plan = planService.getPlan(planId);
		SimpleDateFormat toDate = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		Date date = toDate.parse(plan.getStartDateString());
		
	}
	
	public void pushEvery30m(String userId) throws Exception {
		if ( planService.getUndoneCount(userId) > 0 ) { 
			
			List<Plan> planList = planService.getUndoneList(userId);
			
			// TimerTask
			TimerTask runTask = checkMap.get(userId); // 같은 userId의 TimerTask 호출 
			if (runTask == null) { // 없다면 생성
				
				WebSocket webSocket = new WebSocket();
				runTask = new TimerTask() {
					
					@Override public void run() { 
						Random rand = new Random();
						int random = rand.nextInt(planList.size());
						System.out.println("\nplanList.size() : "+planList.size() +" || "+random+"번째 플랜리스트에서 ");
						List<Todo> todoList = planList.get(random).getTodoList();
						random = rand.nextInt(todoList.size());
						System.out.print("todoList.size() : "+todoList.size()+" || "+random+"번째 todo 추출 :: ");
						Todo todo = todoList.get(random);
						System.out.println(todo.getTodoName());
						
						Push push = new Push(); 
						push.setPushType("T"); 
						push.setRefId(todo.getPlanId());
						push.setPushMsg(todo.getTodoName()); 
						System.out.println("\n\n"+userId+"에게 보내는 PUSH ::: "+push); 
						try {
							webSocket.sendPush(userId, push);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					} };
					
					Timer timer = new Timer(true); 
					timer.scheduleAtFixedRate(runTask, 0, 10*1000); // 10초마다 할 task 
					//timer.scheduleAtFixedRate(runTask, 0, 6*10*1000);  // 1분마다 할 task 
					//timer.scheduleAtFixedRate(task, 0, 30*60*1000); //30분마다 할 task
					
			}
			checkMap.put(userId, runTask);
		}
	}
	
	public void cancel30mPush (String userId) throws Exception {
		for (Map.Entry<String, TimerTask> entry : checkMap.entrySet()) { 
			if (entry.getKey().equals(userId)) { 
				entry.getValue().cancel(); // TimerTask 종료
				checkMap.remove(userId); 
				System.out.println(userId + "님의 알림이 종료되었습니다."); 
			} 
		}
	}
	

}
