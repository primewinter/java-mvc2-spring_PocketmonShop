package com.model2.mvc.service.push;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Push;

public interface PushService {
	
	// 알림 내역에 추가
	public void addPush(Push push) throws Exception;
	
	// 알림 목록 조회
	public Map<String, Object> getPushList(Search search, String userId) throws Exception;
	
	// 알림 읽음
	public void readPush(String userId) throws Exception;
	
	// 알림 삭제
	public void deletePush(List<String> pushId) throws Exception;
	
	// 안 읽은 알림 수
	public int getUnreadCount(String userId) throws Exception;

	
}
