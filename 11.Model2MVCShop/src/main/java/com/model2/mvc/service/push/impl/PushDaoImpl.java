package com.model2.mvc.service.push.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.Push;
import com.model2.mvc.service.push.PushDao;

@Repository("pushDaoImpl")
public class PushDaoImpl implements PushDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
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
