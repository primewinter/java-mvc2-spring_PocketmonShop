package com.model2.mvc.service.push;

import java.util.List;

import com.model2.mvc.service.domain.Push;

public interface PushService {
	
	// �˸� ������ �߰�
	public void addPush(Push push) throws Exception;
	
	// �˸� ��� ��ȸ
	public List<Push> getPushList(String userId) throws Exception;
	
	// �˸� ����
	public void readPush(int pushId) throws Exception;
	
	// �˸� ����
	public void deletePush(int pushId) throws Exception;
	
	// �� ���� �˸� ��
	public int getUnreadCount(String userId) throws Exception;

	
}
