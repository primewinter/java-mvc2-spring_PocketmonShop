package com.model2.mvc.service.domain;

import java.sql.Timestamp;
import java.util.List;

public class Plan {
	
	private String planId;				// plan_id (VARCHAR2) 
	private User planMaster;			// plan_master_id (VARCHAR2) 
	private String planTitle;			// plan_title
	private String planImg;				// plan_img
	private String planType;			// plan_type (CHAR) :: ����ȥ��(A), ����ȥ��(B), ���ڳ���(C), ���ڳ���(D), ��ü(E), �θ�԰�(F), Ŀ��(G)
	
	private Timestamp planRegDate;		// plan_reg_date (TIMESTAMP)
	private String startDate;			// start_date (TIMESTAMP)
	private String planStatus;			// plan_status (CHAR) :: �����غ���(R), ����Ϸ�(C)
	
	private String endDate;			//������������
	private int planTotalDays;		//�� �����ϼ�
	private int planDday;			//���� D-DAY
	
	private List<User> planPartyList;	//�÷��� ������
	private int planPartySize;			//�÷��� �����ο���
	
	
	
	public Plan() {
		super();
	}

	
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public User getPlanMaster() {
		return planMaster;
	}
	public void setPlanMaster(User planMaster) {
		this.planMaster = planMaster;
	}

	public String getPlanTitle() {
		return planTitle;
	}
	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public String getPlanImg() {
		return planImg;
	}
	public void setPlanImg(String planImg) {
		this.planImg = planImg;
	}

	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public Timestamp getPlanRegDate() {
		return planRegDate;
	}
	public void setPlanRegDate(Timestamp planRegDate) {
		this.planRegDate = planRegDate;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPlanTotalDays() {
		return planTotalDays;
	}
	public void setPlanTotalDays(int planTotalDays) {
		this.planTotalDays = planTotalDays;
	}

	public int getPlanDday() {
		return planDday;
	}
	public void setPlanDday(int planDday) {
		this.planDday = planDday;
	}

	public List<User> getPlanPartyList() {
		return planPartyList;
	}
	public void setPlanPartyList(List<User> planPartyList) {
		this.planPartyList = planPartyList;
	}

	public int getPlanPartySize() {
		return planPartySize;
	}
	public void setPlanPartySize(int planPartySize) {
		this.planPartySize = planPartySize;
	}



	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", planMaster=" + planMaster + ", planTitle=" + planTitle + ", planImg="
				+ planImg + ", planType=" + planType + ", planRegDate=" + planRegDate + ", startDate=" + startDate
				+ ", planStatus=" + planStatus + ", endDate=" + endDate + ", planTotalDays=" + planTotalDays
				+ ", planDday=" + planDday + ", planPartyList=" + planPartyList + ", planPartySize=" + planPartySize
				+ "]";
	}
	

}
