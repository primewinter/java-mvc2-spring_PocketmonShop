package com.model2.mvc.service.chat;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.model2.mvc.service.domain.ChatRoom;

public interface ChatService {
	
	// �������� ä�ø�� ��ȸ
	public List<Document> getChatRoomList(String userId) throws Exception;
	
	// ä�ù� ����
	public Map<String, Object> enterChatRoom(String chatRoomId, String userId) throws Exception;
	
	// ä�ù� ����
	public void createRoom(ChatRoom chatRoom) throws Exception;
	
	// ä�ù� ����
	public void exitRoom(String chatRoomId, String userId) throws Exception;
	
	// ä�ù� ����
	public void joinRoom(String chatRoomId, String userId) throws Exception;
	
	

}
