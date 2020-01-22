package com.model2.mvc.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Profile("stomp")
@Controller
public class ChatMessageController {

    private final SimpMessagingTemplate template;

    @Autowired
    public ChatMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

	/*
	 * @MessageMapping("/chat/join") public void join(ChatMessage message) {
	 * message.setMessage(message.getWriter() + "���� �����ϼ̽��ϴ�.");
	 * template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(),
	 * message); }
	 * 
	 * @MessageMapping("/chat/message") public void message(ChatMessage message) {
	 * template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(),
	 * message); }
	 */
}