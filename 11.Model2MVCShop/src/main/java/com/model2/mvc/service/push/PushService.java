package com.model2.mvc.service.push;

import java.util.List;

import com.model2.mvc.service.domain.Push;

public interface PushService {
	
	// 알림 내역에 추가
	public void addPush(Push push) throws Exception;
	
	// 알림 목록 조회
	public List<Push> getPushList(String userId) throws Exception;
	
	// 알림 읽음
	public void readPush(int pushId) throws Exception;
	
	// 알림 삭제
	public void deletePush(int pushId) throws Exception;
	
	// 안 읽은 알림 수
	public int getUnreadCount(String userId) throws Exception;

	
}
