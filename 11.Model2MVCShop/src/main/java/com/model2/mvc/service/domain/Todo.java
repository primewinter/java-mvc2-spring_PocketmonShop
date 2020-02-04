package com.model2.mvc.service.domain;

public class Todo {
	
	private String planId;		//plan_id
	private String todoId;		//todo_id
	private String todoName;	//todo_name
	private String todoCheck;	//todo_check (CHAR) :: FLAG(T/F)
	
	//private String todoUse;		//todo_use (CHAR) :: FLAG(T/F)
	//private int todoNo;			//todo_no ???? 필요한가
	
	
	public Todo() {
		super();
	}

	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getTodoId() {
		return todoId;
	}
	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	public String getTodoName() {
		return todoName;
	}
	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}


	public String getTodoCheck() {
		return todoCheck;
	}
	public void setTodoCheck(String todoCheck) {
		this.todoCheck = todoCheck;
	}


	@Override
	public String toString() {
		return "\nTodo [planId=" + planId + ", todoId=" + todoId + ", todoName=" + todoName + ", todoCheck=" + todoCheck
				+ "]";
	}


}
