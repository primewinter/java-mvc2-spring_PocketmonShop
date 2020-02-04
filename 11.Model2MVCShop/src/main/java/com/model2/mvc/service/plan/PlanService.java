package com.model2.mvc.service.plan;

import java.util.List;

import com.model2.mvc.service.domain.Offer;
import com.model2.mvc.service.domain.Party;
import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.domain.Todo;
import com.model2.mvc.service.domain.User;


public interface PlanService {
	
	public List<Plan> getPlanList(String userId) throws Exception;
	
	public Plan getPlan(String planId) throws Exception;
	
	
	public List<Todo> getTodoList(String planId) throws Exception;
	
	public List<User> getPlanPartyList(String planId) throws Exception;
	
	
	public void addPlan(Plan plan) throws Exception;
	
	public void updatePlan(Plan plan) throws Exception;
	
	public void updatePlanStatus(Plan plan) throws Exception;
	
	public void deletePlan(String planId) throws Exception;
	
	public void deletePlanParty(Party party) throws Exception;
	
	
	public String checkUserFromParty(Party party) throws Exception;
	
	public void addOffer(Offer offer) throws Exception;

	
	public void checkTodo(Todo todo) throws Exception;
	
	public void addTodo(Todo todo) throws Exception;
	
	public void updateTodoName(Todo todo) throws Exception;
	
	public void deleteTodo(String todoId) throws Exception;
	
	
	
	//User Service에 가야하는 메소드.... 테스트용으로 여기서 만들어 씀 
	public void updateUserSlot(String userId) throws Exception;
		
	public String findUserId(String userId) throws Exception;
	
	
	// 미완료 todo 리스트 있는지 체크
	public int getUndoneCount(String userId) throws Exception;
	
	// 안한 todo 리스트 조회
	public List<Plan> getUndoneList(String userId) throws Exception;
	
	// D-nn 인 플랜(todo+User) 리스트 조회
	public List<Plan> getSoonPlan(int leftDay) throws Exception;

	
}
