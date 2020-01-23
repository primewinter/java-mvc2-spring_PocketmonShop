package com.model2.mvc.service.push;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Push;

public interface PushService {
	
	// �˸� ������ �߰�
	public void addPush(Push push) throws Exception;
	
	// �˸� ��� ��ȸ
	public Map<String, Object> getPushList(Search search, String userId) throws Exception;
	
	// �˸� ����
	public void readPush(String userId) throws Exception;
	
	// �˸� ����
	public void deletePush(List<String> pushId) throws Exception;
	
	// �� ���� �˸� ��
	public int getUnreadCount(String userId) throws Exception;

	
}
