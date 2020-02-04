package com.model2.mvc.service.plan.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.Offer;
import com.model2.mvc.service.domain.Party;
import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.domain.Todo;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.plan.PlanDao;
import com.model2.mvc.service.plan.PlanService;

@Service("planServiceImpl")
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	@Qualifier("planDaoImpl")
	private PlanDao planDao;
	public void setPlanDao(PlanDao planDao) {
		this.planDao = planDao;
	}
	
	public PlanServiceImpl() {
		super();
	}




	@Override
	public List<Plan> getPlanList(String userId) throws Exception {
		return planDao.getPlanList(userId);
	}

	@Override
	public Plan getPlan(String planId) throws Exception {
		return planDao.getPlan(planId);
	}

	@Override
	public List<Todo> getTodoList(String planId) throws Exception {
		return planDao.getTodoList(planId);
	}

	@Override
	public List<User> getPlanPartyList(String planId) throws Exception {
		return planDao.getPlanPartyList(planId);
	}

	@Override
	public void addPlan(Plan plan) throws Exception {
		planDao.addPlan(plan);
	}

	@Override
	public void updatePlan(Plan plan) throws Exception {
		planDao.updatePlan(plan);
	}

	@Override
	public void updatePlanStatus(Plan plan) throws Exception {
		planDao.updatePlanStatus(plan);
	}

	@Override
	public void deletePlan(String planId) throws Exception {
		planDao.deletePlan(planId);
	}

	@Override
	public void deletePlanParty(Party party) throws Exception {
		planDao.deletePlanParty(party);
	}

	@Override
	public String checkUserFromParty(Party party) throws Exception {
		return planDao.checkUserFromParty(party);
	}

	@Override
	public void addOffer(Offer offer) throws Exception {
		planDao.addOffer(offer);
	}

	@Override
	public void checkTodo(Todo todo) throws Exception {
		planDao.checkTodo(todo);
	}

	@Override
	public void addTodo(Todo todo) throws Exception {
		planDao.addTodo(todo);
	}

	@Override
	public void updateTodoName(Todo todo) throws Exception {
		planDao.updateTodoName(todo);
	}

	@Override
	public void deleteTodo(String todoId) throws Exception {
		planDao.deleteTodo(todoId);
	}
	
	
	
	
	
	//User Service에 가야하는 메소드.... 테스트용으로 여기서 만들어 씀 
	
	public void updateUserSlot(String userId) throws Exception {
		planDao.updateUserSlot(userId);
	}

	public String findUserId(String userId) throws Exception {
		return planDao.findUserId(userId);
	}
	
	
	
	// 미완료 todo 리스트 개수 check
	public int getUndoneCount(String userId) throws Exception {
		return planDao.getUndoneCount(userId);
	}
	
	// 안한 todo 리스트 조회
	public List<Plan> getUndoneList(String userId) throws Exception {
		List<Plan> planList = planDao.getUndonePlanId(userId);
		for(Plan plan : planList) {
			plan.setTodoList(planDao.getUndoneTodo(plan.getPlanId()));
		}
		return planList;
	}

	// D-nn 인 플랜(todo+User) 리스트 조회
	public List<Plan> getSoonPlan(int leftDay) throws Exception {
		List<Plan> planList = planDao.getSoonPlanId(leftDay);
		for(Plan plan : planList) {
			plan.setTodoList(planDao.getSoonTodo(plan.getPlanId()));
			plan.setPlanPartyList(planDao.getPushPhoneList(plan.getPlanId()));
		}
		return planList;
	}
	
}
