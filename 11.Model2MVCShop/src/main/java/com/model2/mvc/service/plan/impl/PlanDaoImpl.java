package com.model2.mvc.service.plan.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.Offer;
import com.model2.mvc.service.domain.Party;
import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.domain.Todo;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.plan.PlanDao;

@Repository("planDaoImpl")
public class PlanDaoImpl implements PlanDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PlanDaoImpl() {
		super();
	}

	

	public List<Plan> getPlanList(String userId) throws Exception {
		return sqlSession.selectList("PlanMapper.getPlanList", userId);
	}

	public Plan getPlan(String planId) throws Exception {
		return sqlSession.selectOne("PlanMapper.getPlan", planId);
	}

	public List<Todo> getTodoList(String planId) throws Exception {
		return sqlSession.selectList("PlanMapper.getTodoList", planId);
	}

	public List<User> getPlanPartyList(String planId) throws Exception {
		return sqlSession.selectList("PlanMapper.getPlanPartyList", planId);
	}

	synchronized public void addPlan(Plan plan) throws Exception {
		sqlSession.insert("PlanMapper.addPlan", plan);
		
		sqlSession.insert("PlanMapper.addPlanPartyKing", plan.getPlanMaster().getUserId());
		
		String[] defaultTodos = {"항공권 예약하기", "여행루트 짜기", "일정표 작성하기", "환전하기"};
		for (String string : defaultTodos) {
			sqlSession.insert("PlanMapper.addDefaultTodos", string);
		}
		
		String[] deafultStuffs = {"여권", "신분증", "신용카드", "비상약"};
		for (String string : deafultStuffs) {
			sqlSession.insert("PlanSubMapper.addDefaultStuffs", string);
		}
	}

	public void updatePlan(Plan plan) throws Exception {
		sqlSession.update("PlanMapper.updatePlan", plan);
	}

	public void updatePlanStatus(Plan plan) throws Exception {
		sqlSession.update("PlanMapper.updatePlanStatus", plan);
	}

	public void deletePlan(String planId) throws Exception {
		sqlSession.update("PlanMapper.deletePlan", planId);
	}

	public void deletePlanParty(Party party) throws Exception {
		sqlSession.delete("PlanMapper.deletePlanParty", party);
	}

	
	public String checkUserFromParty(Party party) throws Exception {
		return sqlSession.selectOne("PlanMapper.checkUserFromParty", party);
	}

	public void addOffer(Offer offer) throws Exception {
		sqlSession.insert("PlanMapper.addOffer", offer);
	}

	
	public void checkTodo(Todo todo) throws Exception {
		sqlSession.update("PlanMapper.checkTodo", todo);
	}
	
	public void addTodo(Todo todo) throws Exception {
		sqlSession.insert("PlanMapper.addTodo", todo);
	}

	public void updateTodoName(Todo todo) throws Exception {
		sqlSession.update("PlanMapper.updateTodoName", todo);
	}

	public void deleteTodo(String todoId) throws Exception {
		sqlSession.delete("PlanMapper.deleteTodo", todoId);	
	}
	
	
	
	//User Service에 가야하는 메소드.... 테스트용으로 여기서 만들어 씀 
	
	public void updateUserSlot(String userId) throws Exception {
		sqlSession.update("PlanMapper.updateUserSlot", userId);
	}
	
	public String findUserId(String userId) throws Exception {
		return sqlSession.selectOne("PlanMapper.findUserId", userId);
	}
	
	
	
	public int getUndoneCount(String userId) throws Exception {
		return sqlSession.selectOne("PlanMapper.getUndoneCount", userId);
	}
	
	public List<Plan> getUndonePlanId(String userId) throws Exception {
		return sqlSession.selectList("PlanMapper.getPlanIdList", userId);
	}
	
	public List<Todo> getUndoneTodo(String planId) throws Exception {
		return sqlSession.selectList("PlanMapper.getUndoneList", planId);
	}
	
	public List<Plan> getSoonPlanId(int leftDay) throws Exception {
		return sqlSession.selectList("PlanMapper.getSoonPlanId", leftDay);
	}
	
	public List<Todo> getSoonTodo(String planId) throws Exception {
		return sqlSession.selectList("PlanMapper.getSoonTodo", planId);
	}

	public List<User> getPushPhoneList(String planId) throws Exception {
		return sqlSession.selectList("PlanMapper.getPushPhoneList2", planId); //테스트용
		//return sqlSession.selectList("PlanMapper.getPushPhoneList", planId);
	}

}
