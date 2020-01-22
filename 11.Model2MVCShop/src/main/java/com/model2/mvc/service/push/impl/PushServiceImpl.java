package com.model2.mvc.service.push.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.Push;
import com.model2.mvc.service.push.PushDao;
import com.model2.mvc.service.push.PushService;

@Service("pushServiceImpl")
public class PushServiceImpl implements PushService {

	
	@Autowired
	@Qualifier("pushDaoImpl")
	private PushDao pushDao;

	@Override
	public void addPush(Push push) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Push> getPushList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readPush(int pushId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePush(int pushId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getUnreadCount(String userId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
