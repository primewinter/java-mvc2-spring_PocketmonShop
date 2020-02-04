package com.model2.mvc.service.plan;

import java.util.List;

import com.model2.mvc.service.domain.Offer;
import com.model2.mvc.service.domain.Party;
import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.domain.Todo;
import com.model2.mvc.service.domain.User;


public interface PlanDao {
	
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
	
	
	
	
	// 미완료 todo 리스트 개수 check
	public int getUndoneCount(String userId) throws Exception;
	
	// 마스터/멤버로 참여중인 플래너 중 플랜 상태가 'R'이면서 아직 여행 시작 전인 plan ID 목록 조회
	public List<Plan> getUndonePlanId(String userId) throws Exception;
	
	//  plan_id 로 미완료된 todo 목록 조회
	public List<Todo> getUndoneTodo(String planId) throws Exception;
	
	//모든 플래너 중에서 플랜 상태가 'R'이면서 D-30 인 plan 목록(플랜id, 제목) 조회
	public List<Plan> getSoonPlanId(int leftDay) throws Exception;
	
	// 플랜 id 당 미완료된 todo 목록
	public List<Todo> getSoonTodo(String planId) throws Exception;

	// 플랜 id 당 문자 수신 동의한 회원 핸드폰 번호 목록
	public List<User> getPushPhoneList(String planId) throws Exception;
	
}
