package com.model2.mvc.service.chat.impl;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.model2.mvc.service.chat.ChatService;
import com.model2.mvc.service.domain.ChatRoom;

public class ChatServiceImpl implements ChatService {

	// �������� ä�ø�� ��ȸ
	@Override
	public List<Document> getChatRoomList(String userId) throws Exception {
		return null;
	}

	//ä�ù� ����
	@Override
	public Map<String, Object> enterChatRoom(String chatRoomId, String userId) throws Exception {
		// ���� ������� �ƴ��� üũ
		// - ���� ������ ������ �޽��� update
		// - �¶��� ���·� ǥ��
		
		// - �ƴ϶�� ä�ø���� ����
		// - �ý��� �޽��� : ���������� �����ϼ̽��ϴ�. �޽��� ����
		// 
		
		return null;
	}

	// ä�ù� ����
	@Override
	public void createRoom(ChatRoom chatRoom) throws Exception {
		// TODO Auto-generated method stub

	}

	// ä�ù� ����
	@Override
	public void exitRoom(String chatRoomId, String userId) throws Exception {
		// �ܼ� �������� ���� �������� üũ
		// -�ܼ� �����̸� ������������ update
		
		// -���� �����̸� �ش� ä�ù� ������� ����
		// -�ش� ȸ���� ä�ø�Ͽ��� ����
	}

	// ä�ù� ����
	@Override
	public void joinRoom(String chatRoomId, String user) throws Exception {
		// 

	}

}
