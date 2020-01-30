package com.model2.mvc.web.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

@ServerEndpoint("/chatSocket/{planId}/{userId}")
public class ChatSocket {

			private static Map<String, List<Session>> slMap = Collections.synchronizedMap(new HashMap<>());
		
			/* 웹 소켓이 연결되면 호출되는 이벤트
			 * @throws IOException
			 */
			@OnOpen
			public void handleOpen(@PathParam("planId") String planId, @PathParam("userId") String userId, Session session) throws IOException {
						System.out.println("CHATSOCKET ::: [" + planId + "] client is now connected...");
				
						List<Session> mapList = slMap.get(planId); // 같은 userId의 session 리스트 호출
						if (mapList == null || mapList.size() == 0) { // 없다면 생성
							mapList = new ArrayList<>();
						}
						mapList.add(session); // 생성된or호출된 session 리스트에 add
						slMap.put(planId, mapList);
				
				
						for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
							System.out.println("CHATSOCKET getKey :::: " + entry.getKey());
							if (entry.getKey().equals(planId)) {
								for (Session se : entry.getValue()) {
									se.getBasicRemote().sendText(userId + "님이 입장하셨습니다.");
								}
							}
						}
		
			}
		
			/**
			 * 웹 소켓으로부터 메시지가 오면 호출되는 이벤트
			 * @param message
			 * @return
			 * @throws IOException
			 * @throws JsonMappingException
			 * @throws JsonGenerationException
			 */
			@OnMessage
			public void handleMessage(@PathParam("planId") String planId, @PathParam("userId") String userId, String message, Session session)
					throws JsonGenerationException, JsonMappingException, IOException {
						// process booking from the given guest here
						System.out.println("CHATSOCKET [client to client] " + message);
						
						for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
							System.out.println("getKey :::: " + entry.getKey());
							if (entry.getKey().equals(planId)) {
								for (Session se : entry.getValue()) {
									se.getBasicRemote().sendText(message);
								}
							}
						}
	
			}
		
			// 웹 소켓이 닫히면 호출되는 이벤트
			@OnClose
			public void handleClose(@PathParam("planId") String planId, @PathParam("userId") String userId, Session session) throws IOException {
						System.out.println("client is now disconnected...");
				
						for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
							System.out.println("퇴장하는 getKey :::: " + entry.getKey());
							if (entry.getKey().equals(planId)) {
								entry.getValue().remove(session);
								for (Session se : entry.getValue()) {
									se.getBasicRemote().sendText(userId + "님이 퇴장하셨습니다.");
								}
							}
						}
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

