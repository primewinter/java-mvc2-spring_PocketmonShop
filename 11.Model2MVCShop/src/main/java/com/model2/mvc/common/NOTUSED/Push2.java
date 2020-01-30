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
		
			/* �� ������ ����Ǹ� ȣ��Ǵ� �̺�Ʈ
			 * @throws IOException
			 */
			@OnOpen
			public void handleOpen(@PathParam("userId") String userId,  Session session) throws IOException {
		System.out.println("userId : "+userId);
				/*
		 * System.out.println("::: [" + userId + "]client is now connected...");
		 * 
		 * List<Session> mapList = slMap.get(userId); // ���� userId�� session ����Ʈ ȣ�� if
		 * (mapList == null || mapList.size() == 0) { // ���ٸ� ���� mapList = new
		 * ArrayList<>(); } mapList.add(session); // ������orȣ��� session����Ʈ�� add
		 * 
		 * TimerTask runTask = checkMap.get(userId); // ���� userId�� TimerTask ȣ�� if
		 * (runTask == null) { // ���ٸ� ���� runTask = new TimerTask() {
		 * 
		 * @Override public void run() { System.out.println("TimerTask.run()"); String
		 * message = userId + "�˸� [" + System.currentTimeMillis() + "]";
		 * Iterator<Session> itr = slMap.get(userId).iterator(); while (itr.hasNext()) {
		 * try { Session session = itr.next(); System.out.println("while�� ���� ��");
		 * session.getBasicRemote().sendText(message);
		 * 
		 * } catch (IOException e) { e.printStackTrace(); } } } };
		 * 
		 * Timer timer = new Timer(true); timer.scheduleAtFixedRate(runTask, 0, 3000);
		 * // 3�ʸ��� �� tast // timer.scheduleAtFixedRate(task, 0, 30*60*1000); //30�и��� ��
		 * task }
		 * 
		 * slMap.put(userId, mapList); checkMap.put(userId, runTask);
		 * 
		 * 
		 * for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
		 * System.out.println("getKey :::: " + entry.getKey()); if
		 * (entry.getKey().equals(userId)) { for (Session se : entry.getValue()) {
		 * se.getBasicRemote().sendText("���� " + userId + "���� �����ϼ̽��ϴ�."); } } }
		 */
		System.out.println("[handleOpen] "+session);
			}
		
			/**
			 * �� �������κ��� �޽����� ���� ȣ��Ǵ� �̺�Ʈ
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
		
			// �� ������ ������ ȣ��Ǵ� �̺�Ʈ
			@OnClose
			public void handleClose(Session session) throws IOException {
						System.out.println("[client is now disconnected]"+session);
		/*
		 * for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
		 * System.out.println("�����ϴ� getKey :::: " + entry.getKey()); if
		 * (entry.getKey().equals(userId)) { entry.getValue().remove(session); for
		 * (Session se : entry.getValue()) { se.getBasicRemote().sendText("���� " +
		 * userId + "���� �����ϼ̽��ϴ�."); } } }
		 * 
		 * for (Map.Entry<String, TimerTask> entry : checkMap.entrySet()) { if
		 * (entry.getKey().equals(userId)) { entry.getValue().cancel();
		 * checkMap.remove(userId); System.out.println(userId + "���� �˸��� ����Ǿ����ϴ�."); } }
		 */
		
			}
		
			//�� ������ ������ ���� ȣ��Ǵ� �̺�Ʈ
			 /* 
			 * @param t
			 */
			@OnError
			public void handleError(Throwable t) {
						t.printStackTrace();
			}

}
