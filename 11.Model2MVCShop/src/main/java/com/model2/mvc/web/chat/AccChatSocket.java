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

//@ServerEndpoint("/accSockets/{roomId}/{userId}")
public class AccChatSocket {

			private static Map<String, List<Session>> slMap = Collections.synchronizedMap(new HashMap<>());
		
			// 웹 소켓이 연결되면 호출되는 이벤트
			@OnOpen
			public void handleOpen(@PathParam("roomId") String roomId, @PathParam("userId") String userId, Session session) throws Exception {
						System.out.println("CHATSOCKET ::: [" + roomId + "] client is now connected...");
						List<Session> mapList = slMap.get(roomId); // 같은 userId의 session 리스트 호출
						if (mapList == null || mapList.size() == 0) { // 없다면 생성
							mapList = new ArrayList<>();
						}
						mapList.add(session); // 생성된or호출된 session 리스트에 add
						slMap.put(roomId, mapList);
						
						String message = userId + "님이 입장하셨습니다."; 
						sendToClient(roomId, message, session);
			}
		
			@OnMessage
			public void handleMessage(@PathParam("roomId") String roomId, @PathParam("userId") String userId, String message, Session session)
					throws Exception {
						// process booking from the given guest here
						System.out.println("CHATSOCKET [client to client] " + message);
						sendToClient(roomId, message, session);
	
			}
		
			// 웹 소켓이 닫히면 호출되는 이벤트
			@OnClose
			public void handleClose(@PathParam("roomId") String roomId, @PathParam("userId") String userId, Session session) throws Exception {
						
					System.out.println("client is now disconnected...");
					String message = userId + "님이 퇴장하셨습니다."; 
					sendToClient(roomId, message, session);
				
			}
		
			//웹 소켓이 에러가 나면 호출되는 이벤트
			@OnError
			public void handleError(Throwable t) {
						t.printStackTrace();
			}
			
			
			public void sendToClient(String roomId, String message, Session session) throws Exception {
				
				for (Map.Entry<String, List<Session>> entry : slMap.entrySet()) {
					System.out.println("getKey :::: " + entry.getKey()+" || message : "+message);
					if (entry.getKey().equals(roomId)) {
						entry.getValue().remove(session);
						for (Session se : entry.getValue()) {
							se.getBasicRemote().sendText(message);
						}
					}
				}
				
			}

}

