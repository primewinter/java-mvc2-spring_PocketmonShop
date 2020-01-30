package com.model2.mvc.common.NOTUSED;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.web.bind.annotation.RequestParam;

//@ServerEndpoint("/replyPush/{userId}")
public class Push2 {

			private static Map<String, List<Session>> slMap = Collections.synchronizedMap(new HashMap<>());
			private static Map<String, TimerTask> checkMap = Collections.synchronizedMap(new HashMap<>());
		
			/* 웹 소켓이 연결되면 호출되는 이벤트
			 * @throws IOException
			 */
			@OnOpen
			public void handleOpen(@PathParam("userId") String userId,  Session session) throws IOException {
		System.out.println("userId : "+userId);
				/*
		 * System.out.println("::: [" + userId + "]client is now connected...");
		 * 
		 * List<Session> mapList = slMap.get(userId); // 같은 userId의 session 리스트 호출 if
		 * (mapList == null || mapList.size() == 0) { // 없다면 생성 mapList = new
		 * ArrayList<>(); } mapList.add(session); // 생성된or호출된 session리스트에 add
		 * 
		 * TimerTask runTask = checkMap.get(userId); // 같은 userId의 TimerTask 호출 if
		 * (runTask == null) { // 없다면 생성 runTask = new TimerTask() {
		 * 
		 * @Override public void run() { System.out.println("TimerTask.run()"); String
		 * message = userId + "알림 [" + System.currentTimeMillis() + "]";
		 * Iterator<Session> itr = slMap.get(userId).iterator(); while (itr.hasNext()) {
		 * try { Session session = itr.next(); System.out.println("while문 도는 중");
		 * session.getBasicRemote().sendText(message);
		 * 
		 * } catch (IOException e) { e.printStackTrace(); } } } };
		 * 
		 * Timer timer = new Timer(true); timer.scheduleAtFixedRate(runTask, 0, 3000);
		 * // 3초마다 할 tast // timer.scheduleAtFixedRate(task, 0, 30*60*1000); //30분마다 할
		 * task }
		 * 
		 * slMap.put(userId, mapList); checkMap.put(userId, runTask);
		 * 
		 * 
		 * for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
		 * System.out.println("getKey :::: " + entry.getKey()); if
		 * (entry.getKey().equals(userId)) { for (Session se : entry.getValue()) {
		 * se.getBasicRemote().sendText("띠요옹 " + userId + "님이 입장하셨습니다."); } } }
		 */
		System.out.println("[handleOpen] "+session);
			}
		
			/**
			 * 웹 소켓으로부터 메시지가 오면 호출되는 이벤트
			 * 
			 * @param message
			 * @return
			 * @throws IOException
			 * @throws JsonMappingException
			 * @throws JsonGenerationException
			 */
			@OnMessage
			public void handleMessage(String message, Session session)
					throws JsonGenerationException, JsonMappingException, IOException {
						// process booking from the given guest here
						System.out.println("[client to client] " + message+"\t\t"+session);
				
		/*
		 * for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
		 * System.out.println("getKey :::: " + entry.getKey()); if
		 * (entry.getKey().equals(userId)) { for (Session se : entry.getValue()) {
		 * se.getBasicRemote().sendText(message); } } }
		 */
		
			}
		
			// 웹 소켓이 닫히면 호출되는 이벤트
			@OnClose
			public void handleClose(Session session) throws IOException {
						System.out.println("[client is now disconnected]"+session);
		/*
		 * for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
		 * System.out.println("퇴장하는 getKey :::: " + entry.getKey()); if
		 * (entry.getKey().equals(userId)) { entry.getValue().remove(session); for
		 * (Session se : entry.getValue()) { se.getBasicRemote().sendText("띠요옹 " +
		 * userId + "님이 퇴장하셨습니다."); } } }
		 * 
		 * for (Map.Entry<String, TimerTask> entry : checkMap.entrySet()) { if
		 * (entry.getKey().equals(userId)) { entry.getValue().cancel();
		 * checkMap.remove(userId); System.out.println(userId + "님의 알림이 종료되었습니다."); } }
		 */
		
			}
		
			//웹 소켓이 에러가 나면 호출되는 이벤트
			 /* 
			 * @param t
			 */
			@OnError
			public void handleError(Throwable t) {
						t.printStackTrace();
			}

}
