package com.model2.mvc.service.push;

import java.util.List;
import java.util.Map;

import com.model2.mvc.service.domain.Push;

public interface PushDao {
	
	// 알림 내역에 등록
	public void addPush(Push push) throws Exception;
	
	// 알림 목록 조회
	public List<Push> getPushList(Map<String, Object> map) throws Exception;
	
	// 알림 읽음
	public void readPush(String userId) throws Exception;
	
	// 알림 삭제
	public void deletePush(List<String> pushId) throws Exception;
	
	// 안 읽은 알림 수
	public int getUnreadCount(String userId) throws Exception;
	
	// 목록 출력을 위한 totalCount 
	public int getTotalCount(Map<String, Object> map) throws Exception;

}
