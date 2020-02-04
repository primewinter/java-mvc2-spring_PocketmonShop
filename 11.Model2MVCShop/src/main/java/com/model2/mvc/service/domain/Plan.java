package com.model2.mvc.service.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Plan {
	
	private String planId;				// plan_id (VARCHAR2) 
	private User planMaster;			// plan_master_id (VARCHAR2) 
	private String planTitle;			// plan_title
	private String planImg;				// plan_img
	private String planType;			// plan_type (CHAR) :: 여자혼자(A), 남자혼자(B), 여자끼리(C), 남자끼리(D), 단체(E), 부모님과(F), 커플(G)
	
	private Timestamp planRegDate;		// plan_reg_date (TIMESTAMP)
	private Timestamp startDate;		// start_date (TIMESTAMP)
	private String startDateString;		
	private String planStatus;			// plan_status (CHAR) :: 여행준비중(R), 여행완료(C)
	
	private String endDate;			//여행종료일자
	private int planTotalDays;		//총 여행일수
	private int planDday;			//여행 D-DAY
	
	private List<User> planPartyList;	//플래너 참여자
	private int planPartySize;			//플래너 참여인원수
	
	private List<Todo> todoList;		//TODO 리스트
	//private Route route;				//여행루트
	private List<Day> dayList;			//일차 리스트
	private List<Daily> dailyList;		//일정 리스트
	private List<Stuff> stuffList;		//준비물 리스트
	private List<Memo> memoList;		//메모 리스트
	
	
	private List<City> cityList;		//시티 리스트
	private List<Daily> budgetOverviewList;	//예산미리보기 리스트
	
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
			Date dateForDay = new Date(startDate.getTime());		//요일을 위한 dateForDay
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
