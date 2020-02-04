package com.model2.mvc.service.domain;

public class Stuff {

	private String planId;		//plan_id
	private String stuffId;		//stuff_id
	private String stuffName;	//stuff_name
	private String stuffCheck;	//stuff_check (CHAR) :: FLAG(T/F)
	
	//private String stuffUse;	//stuff_use (CHAR) :: FLAG(T/F)
	//private int stuffNo;		//stuff_no ??? 필요한가

	
	public Stuff() {
		super();
	}

	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getStuffId() {
		return stuffId;
	}
	public void setStuffId(String stuffId) {
		this.stuffId = stuffId;
	}

	public String getStuffName() {
		return stuffName;
	}
	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}

	public String getStuffCheck() {
		return stuffCheck;
	}
	public void setStuffCheck(String stuffCheck) {
		this.stuffCheck = stuffCheck;
	}


	@Override
	public String toString() {
		return "\nStuff [planId=" + planId + ", stuffId=" + stuffId + ", stuffName=" + stuffName + ", stuffCheck="
				+ stuffCheck + "]";
	}

	
}
