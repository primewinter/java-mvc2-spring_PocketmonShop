package com.model2.mvc.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.model2.mvc.service.domain.Push;
import com.model2.mvc.service.domain.Todo;
import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.plan.PlanService;
import com.model2.mvc.web.chat.WebSocket;

//@WebListener
public class LoginUserPlanPush implements HttpSessionListener, HttpSessionAttributeListener {

	//Field
	private static Map<String, TimerTask> checkMap = Collections.synchronizedMap(new HashMap<String, TimerTask>());
	private static Map<String, User> userMap = Collections.synchronizedMap(new HashMap<String, User>());
	
	public LoginUserPlanPush() {
		System.out.println(this.getClass());
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println(">>>생성된 SESSIONID "+se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(">>>제거된 SESSIONID "+se.getSession().getId());
		User sessionUser = (User)se.getSession().getAttribute("user");
		System.out.println("session에서 가져온 User : "+sessionUser);
		String userId = sessionUser.getUserId();
		
		for (Map.Entry<String, TimerTask> entry : checkMap.entrySet()) { 
			if (entry.getKey().equals(userId)) { 
				entry.getValue().cancel(); // TimerTask 종료
				checkMap.remove(userId); 
				System.out.println(userId + "님의 알림이 종료되었습니다."); 
			} 
		}
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println(">>>추가된 SESSIONID "+se.getSession().getId());
		if(se.getSession().getAttribute("user") != null) {
			User sessionUser = (User)se.getSession().getAttribute("user");
			String userId = sessionUser.getUserId();
			userMap.put(se.getSession().getId(), sessionUser);
			System.out.println("session에서 가져온 User : "+sessionUser+" || userId : "+userId);
			
			try {
				PlanService planService = (PlanService)Util.getBean("planServiceImpl");
				System.out.println("\n\ncount ::::: "+planService.getUndoneCount(userId)+"\n\n");
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
								push.setPushType("P"); 
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
							//timer.scheduleAtFixedRate(runTask, 0, 10*1000); // 10초마다 할 task 
							//timer.scheduleAtFixedRate(runTask, 0, 6*10*1000);  // 1분마다 할 task 
							timer.scheduleAtFixedRate(runTask, 0, 30*60*1000); //30분마다 할 task
							
					}
					checkMap.put(userId, runTask);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
		}	 
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

}
