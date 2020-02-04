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
	
	
	
	//User Service�� �����ϴ� �޼ҵ�.... �׽�Ʈ������ ���⼭ ����� �� 
	public void updateUserSlot(String userId) throws Exception;
		
	public String findUserId(String userId) throws Exception;
	
	
	
	
	// �̿Ϸ� todo ����Ʈ ���� check
	public int getUndoneCount(String userId) throws Exception;
	
	// ������/����� �������� �÷��� �� �÷� ���°� 'R'�̸鼭 ���� ���� ���� ���� plan ID ��� ��ȸ
	public List<Plan> getUndonePlanId(String userId) throws Exception;
	
	//  plan_id �� �̿Ϸ�� todo ��� ��ȸ
	public List<Todo> getUndoneTodo(String planId) throws Exception;
	
	//��� �÷��� �߿��� �÷� ���°� 'R'�̸鼭 D-30 �� plan ���(�÷�id, ����) ��ȸ
	public List<Plan> getSoonPlanId(int leftDay) throws Exception;
	
	// �÷� id �� �̿Ϸ�� todo ���
	public List<Todo> getSoonTodo(String planId) throws Exception;

	// �÷� id �� ���� ���� ������ ȸ�� �ڵ��� ��ȣ ���
	public List<User> getPushPhoneList(String planId) throws Exception;
	
}
