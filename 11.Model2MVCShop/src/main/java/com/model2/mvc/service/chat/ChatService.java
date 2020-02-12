package com.model2.mvc.service.chat;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.model2.mvc.service.domain.ChatRoom;

public interface ChatService {
	
	// 참여중인 채팅목록 조회
	public List<Document> getChatRoomList(String userId) throws Exception;
	
	// 채팅방 입장
	public Map<String, Object> enterChatRoom(String chatRoomId, String userId) throws Exception;
	
	// 채팅방 생성
	public void createRoom(ChatRoom chatRoom) throws Exception;
	
	// 채팅방 퇴장
	public void exitRoom(String chatRoomId, String userId) throws Exception;
	
	// 채팅방 참여
	public void joinRoom(String chatRoomId, String userId) throws Exception;
	
	

}
