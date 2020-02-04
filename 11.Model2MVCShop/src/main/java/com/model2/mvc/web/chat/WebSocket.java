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
import org.json.simple.parser.ParseException;

import com.model2.mvc.service.domain.Push;

// handshake 설정하기 위한 클래스를 지정한다.
@ServerEndpoint(value = "/websocket/{userId}")

public class WebSocket {
	
			private static Map<String, Session> slMap = Collections.synchronizedMap(new HashMap<>());
		
			/* 웹 소켓이 연결되면 호출되는 이벤트
			 * @throws IOException
			 */
			@OnOpen
			public void handleOpen(@PathParam("userId") String userId, Session session) throws IOException {
						System.out.println("::: [" + userId + "]client is now connected...");
						slMap.put(userId, session);
			}
		
			/**
			 * 웹 소켓으로부터 메시지가 오면 호출되는 이벤트 :: 안 쓰일 이벤트
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
						JSONObject jsonobj = (JSONObject)JSONValue.parse(message);
					
						ObjectMapper objectMapper = new ObjectMapper();
						Push push = objectMapper.readValue(jsonobj.get("push").toString(), Push.class);
						System.out.println("binding 된 push : "+push);
						if( push.getPushType().equals("R") ) {
							String writer = push.getReceiverId();
							for (Map.Entry<String, Session> entry : slMap.entrySet()) {
								System.out.println("\n\n\n>>>Push 댓글 알림 :::: " + entry.getKey());
								if (entry.getKey().equals(writer)) {
									String result = new ObjectMapper().writeValueAsString(push);
									entry.getValue().getBasicRemote().sendText(result);
								}
							}
							
						}
			
			}
		
			// 웹 소켓이 닫히면 호출되는 이벤트
			@OnClose
			public void handleClose(@PathParam("userId") String userId, Session session) throws IOException {
						System.out.println("client is now disconnected...");
						slMap.remove(userId);
			}
		
			//웹 소켓이 에러가 나면 호출되는 이벤트
			 /* 
			 * @param t
			 */
			@OnError
			public void handleError(Throwable t) {
						t.printStackTrace();
			}
			
			public void sendPush(String userId, Push push) throws Exception {
				System.out.println("\n\nWebSocket :: sendPush "+userId);
				for (Map.Entry<String, Session> entry : slMap.entrySet()) {
					if (entry.getKey().equals(userId)) {
						String result = new ObjectMapper().writeValueAsString(push);
						entry.getValue().getBasicRemote().sendText(result);
					}
				}
				
			}

}
