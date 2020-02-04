package com.model2.mvc.service.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Plan {
	
	private String planId;				// plan_id (VARCHAR2) 
	private User planMaster;			// plan_master_id (VARCHAR2) 
	private String planTitle;			// plan_title
	private String planImg;				// plan_img
	private String planType;			// plan_type (CHAR) :: ����ȥ��(A), ����ȥ��(B), ���ڳ���(C), ���ڳ���(D), ��ü(E), �θ�԰�(F), Ŀ��(G)
	
	private Timestamp planRegDate;		// plan_reg_date (TIMESTAMP)
	private Timestamp startDate;		// start_date (TIMESTAMP)
	private String startDateString;		
	private String planStatus;			// plan_status (CHAR) :: �����غ���(R), ����Ϸ�(C)
	
	private String endDate;			//������������
	private int planTotalDays;		//�� �����ϼ�
	private int planDday;			//���� D-DAY
	
	private List<User> planPartyList;	//�÷��� ������
	private int planPartySize;			//�÷��� �����ο���
	
	private List<Todo> todoList;		//TODO ����Ʈ
	//private Route route;				//�����Ʈ
	private List<Day> dayList;			//���� ����Ʈ
	private List<Daily> dailyList;		//���� ����Ʈ
	private List<Stuff> stuffList;		//�غ� ����Ʈ
	private List<Memo> memoList;		//�޸� ����Ʈ
	
	
	private List<City> cityList;		//��Ƽ ����Ʈ
	private List<Daily> budgetOverviewList;	//����̸����� ����Ʈ
	
	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public List<Daily> getBudgetOverviewList() {
		return budgetOverviewList;
	}
	public void setBudgetOverviewList(List<Daily> budgetOverviewList) {
		this.budgetOverviewList = budgetOverviewList;
	}



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



	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
		
		if(startDate != null) {
			Date dateForDay = new Date(startDate.getTime());		//������ ���� dateForDay
			this.startDateString = startDate.toString().substring(0,10) + " : "+dateForDay.toString().substring(0,3);
		}
	}

	public String getStartDateString() {
		return startDateString;
	}
	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
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

	public List<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}

	public List<Day> getDayList() {
		return dayList;
	}
	public void setDayList(List<Day> dayList) {
		this.dayList = dayList;
	}

	public List<Daily> getDailyList() {
		return dailyList;
	}
	public void setDailyList(List<Daily> dailyList) {
		this.dailyList = dailyList;
	}

	public List<Stuff> getStuffList() {
		return stuffList;
	}
	public void setStuffList(List<Stuff> stuffList) {
		this.stuffList = stuffList;
	}

	public List<Memo> getMemoList() {
		return memoList;
	}
	public void setMemoList(List<Memo> memoList) {
		this.memoList = memoList;
	}
	
	
	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", planMaster=" + planMaster + ", planTitle=" + planTitle + ", planImg="
				+ planImg + ", planType=" + planType + ", planRegDate=" + planRegDate + ", startDate=" + startDate
				+ ", startDateString=" + startDateString + ", planStatus=" + planStatus + ", endDate=" + endDate;
//				+ ", planTotalDays=" + planTotalDays + ", planDday=" + planDday + ", \n planPartyList=" + planPartyList
//				+ ", planPartySize=" + planPartySize + ", \n todoList=" + todoList + ", \n dayList=" + dayList
//				+ ", \n dailyList=" + dailyList + ", \n stuffList=" + stuffList + ", \n memoList=" + memoList + ", \n cityList="
//				+ cityList + ", \n budgetOverviewList=" + budgetOverviewList + "]";
	}
	

}
