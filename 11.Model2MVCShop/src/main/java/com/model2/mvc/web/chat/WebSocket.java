package com.model2.mvc.web.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.model2.mvc.service.domain.Push;


@ServerEndpoint("/websocket/{userId}")
public class WebSocket {

			private static Map<String, Session> slMap = Collections.synchronizedMap(new HashMap<>());
			//private static Map<String, TimerTask> checkMap = Collections.synchronizedMap(new HashMap<>());
		
			/* �� ������ ����Ǹ� ȣ��Ǵ� �̺�Ʈ
			 * @throws IOException
			 */
			@OnOpen
			public void handleOpen(@PathParam("userId") String userId, Session session) throws IOException {
						System.out.println("::: [" + userId + "]client is now connected...");
				
		/*
		 * TimerTask runTask = checkMap.get(userId); // ���� userId�� TimerTask ȣ�� if
		 * (runTask == null) { // ���ٸ� ����
		 * 
		 * runTask = new TimerTask() {
		 * 
		 * @Override public void run() { System.out.println("TimerTask.run()"); Push
		 * push = new Push(); push.setPushType("T"); push.setPushMsg("todo ����Ʈ �� �� 1");
		 * System.out.println("Timer set�� push : "+push); ObjectMapper objectMapper =
		 * new ObjectMapper(); String jsonValue = ""; try { jsonValue =
		 * objectMapper.writeValueAsString(push); Session session = slMap.get(userId);
		 * System.out.println("���� msg : "+jsonValue);
		 * session.getBasicRemote().sendText(jsonValue); } catch (IOException e1) {
		 * e1.printStackTrace(); } //String message = userId + "�˸� [" +
		 * System.currentTimeMillis() + "]";
		 * 
		 * } };
		 * 
		 * Timer timer = new Timer(true); timer.scheduleAtFixedRate(runTask, 0,
		 * 10*1000); // 10�ʸ��� �� tast //timer.scheduleAtFixedRate(runTask, 0, 6*10*1000);
		 * // 1�и��� �� tast // timer.scheduleAtFixedRate(task, 0, 30*60*1000); //30�и��� ��
		 * task
		 * 
		 * }
		 */
						
						slMap.put(userId, session);
						//checkMap.put(userId, runTask);
		
			}
		
			/**
			 * �� �������κ��� �޽����� ���� ȣ��Ǵ� �̺�Ʈ :: �� ���� �̺�Ʈ
			 * @param message
			 * @return
			 * @throws IOException
			 * @throws JsonMappingException
			 * @throws JsonGenerationException
			 * @throws ParseException 
			 */
			@OnMessage
			public void handleMessage(@PathParam("userId") String userId, String message, Session session)
					throws JsonGenerationException, JsonMappingException, IOException, ParseException {
				
						System.out.println("[client to client] " + message);
						JSONParser parser = new JSONParser();
						JSONObject jsonObj = (JSONObject)parser.parse( message );
						System.out.println("jsonObj :: "+jsonObj);
						JSONObject jsonobj = (JSONObject)JSONValue.parse(message);
						System.out.println("jsonobj :: "+jsonobj);
					
						
						ObjectMapper objectMapper = new ObjectMapper();
						Push push = objectMapper.readValue(jsonObj.toString(), Push.class);
						
						if( push.getPushType().equals("R") ) {
							String writer = push.getReceiverId();
							for (Map.Entry<String, Session> entry : slMap.entrySet()) {
								System.out.println("Push �˸� getKey :::: " + entry.getKey());
								if (entry.getKey().equals(writer)) {
									entry.getValue().getBasicRemote().sendText("����� �޷Ƚ��ϴ�.");
								}
							}
							
						}
					//slMap.get(userId).getBasicRemote().sendText(message);
			
			}
		
			// �� ������ ������ ȣ��Ǵ� �̺�Ʈ
			@OnClose
			public void handleClose(@PathParam("userId") String userId, Session session) throws IOException {
						System.out.println("client is now disconnected...");
						slMap.remove(userId);
				
		/*
		 * for (Map.Entry<String, TimerTask> entry : checkMap.entrySet()) { if
		 * (entry.getKey().equals(userId)) { entry.getValue().cancel(); // TimerTask ����
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
