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
		
			// �� ������ ����Ǹ� ȣ��Ǵ� �̺�Ʈ
			@OnOpen
			public void handleOpen(@PathParam("roomId") String roomId, @PathParam("userId") String userId, Session session) throws Exception {
						System.out.println("CHATSOCKET ::: [" + roomId + "] client is now connected...");
						List<Session> mapList = slMap.get(roomId); // ���� userId�� session ����Ʈ ȣ��
						if (mapList == null || mapList.size() == 0) { // ���ٸ� ����
							mapList = new ArrayList<>();
						}
						mapList.add(session); // ������orȣ��� session ����Ʈ�� add
						slMap.put(roomId, mapList);
						
						String message = userId + "���� �����ϼ̽��ϴ�."; 
						sendToClient(roomId, message, session);
			}
		
			@OnMessage
			public void handleMessage(@PathParam("roomId") String roomId, @PathParam("userId") String userId, String message, Session session)
					throws Exception {
						// process booking from the given guest here
						System.out.println("CHATSOCKET [client to client] " + message);
						sendToClient(roomId, message, session);
	
			}
		
			// �� ������ ������ ȣ��Ǵ� �̺�Ʈ
			@OnClose
			public void handleClose(@PathParam("roomId") String roomId, @PathParam("userId") String userId, Session session) throws Exception {
						
					System.out.println("client is now disconnected...");
					String message = userId + "���� �����ϼ̽��ϴ�."; 
					sendToClient(roomId, message, session);
				
			}
		
			//�� ������ ������ ���� ȣ��Ǵ� �̺�Ʈ
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

