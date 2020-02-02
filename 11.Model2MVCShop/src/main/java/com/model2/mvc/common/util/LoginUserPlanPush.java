package com.model2.mvc.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.model2.mvc.service.domain.Push;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.web.chat.WebSocket;

//@WebListener
public class LoginUserPlanPush implements HttpSessionListener, HttpSessionAttributeListener {

	//Field
	private static Map<String, TimerTask> checkMap = Collections.synchronizedMap(new HashMap<String, TimerTask>());
	private static Map<String, User> userMap = Collections.synchronizedMap(new HashMap<String, User>());
	
	public LoginUserPlanPush() {
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
			System.out.println("session에서 가져온 User : "+sessionUser);
			String userId = sessionUser.getUserId();
			userMap.put(se.getSession().getId(), sessionUser);
			
			// 알림할 플래너가 있는지 check B/L 필요
	
			// TimerTask
			TimerTask runTask = checkMap.get(userId); // 같은 userId의 TimerTask 호출 
			if (runTask == null) { // 없다면 생성
			
				WebSocket webSocket = new WebSocket();
				runTask = new TimerTask() {
				  
					@Override public void run() { 
						Push push = new Push(); 
						push.setPushType("T"); 
						push.setPushMsg("todo 리스트 중 택 1"); // 플래너에서 랜덤으로 toDo 하나 택 B/L 필요
						System.out.println("TimerTask.run()"+userId+push); 
						try {
							webSocket.sendPlanTimer(userId, push);
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

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

}
