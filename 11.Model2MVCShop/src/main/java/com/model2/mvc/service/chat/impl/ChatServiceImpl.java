package com.model2.mvc.service.chat.impl;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.model2.mvc.service.chat.ChatService;
import com.model2.mvc.service.domain.ChatRoom;

public class ChatServiceImpl implements ChatService {

	// 참여중인 채팅목록 조회
	@Override
	public List<Document> getChatRoomList(String userId) throws Exception {
		return null;
	}

	//채팅방 입장
	@Override
	public Map<String, Object> enterChatRoom(String chatRoomId, String userId) throws Exception {
		// 기존 멤버인지 아닌지 체크
		// - 기존 멤버라면 안읽은 메시지 update
		// - 온라인 상태로 표시
		
		// - 아니라면 채팅멤버에 포함
		// - 시스템 메시지 : ㅇㅇㅇ님이 입장하셨습니다. 메시지 저장
		// 
		
		return null;
	}

	// 채팅방 생성
	@Override
	public void createRoom(ChatRoom chatRoom) throws Exception {
		// TODO Auto-generated method stub

	}

	// 채팅방 퇴장
	@Override
	public void exitRoom(String chatRoomId, String userId) throws Exception {
		// 단순 퇴장인지 영구 퇴장인지 체크
		// -단순 퇴장이면 오프라인으로 update
		
		// -영구 퇴장이면 해당 채팅방 멤버에서 제외
		// -해당 회원의 채팅목록에서 제외
	}

	// 채팅방 참여
	@Override
	public void joinRoom(String chatRoomId, String user) throws Exception {
		// 

	}

}
