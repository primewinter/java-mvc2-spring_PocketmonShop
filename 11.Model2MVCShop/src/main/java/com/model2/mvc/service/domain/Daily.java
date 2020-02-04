package com.model2.mvc.service.domain;

public class Daily {
	
	private String planId;			//plan_id
	private String dailyId;			//daily_id
	private String dailyCate;		//daily_cate (CHAR) :: °ü±¤(D), ±³Åë(T), Åõ¾î(V), ¼÷¼Ò(R), ½Ä»ç(F), ¼îÇÎ(S), ±âÅ¸(E)
	private String dailyDetail;		//daily_detail
	private String dayNo;			//day_no
	private String dayTime;			//day_time
	private int budgetAmount;	//budget_amount
	private String budgetCurrency;	//budget_currency (CHAR) :: KRW(K), EUR(E), GBP(G)
	
	
	public Daily() {
		super();
	}


	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getDailyId() {
		return dailyId;
	}
	public void setDailyId(String dailyId) {
		this.dailyId = dailyId;
	}

	public String getDailyCate() {
		return dailyCate;
	}
	public void setDailyCate(String dailyCate) {
		this.dailyCate = dailyCate;
	}

	public String getDailyDetail() {
		return dailyDetail;
	}
	public void setDailyDetail(String dailyDetail) {
		this.dailyDetail = dailyDetail;
	}

	public String getDayNo() {
		return dayNo;
	}
	public void setDayNo(String dayNo) {
		this.dayNo = dayNo;
	}

	public String getDayTime() {
		return dayTime;
	}
	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	public int getBudgetAmount() {
		return budgetAmount;
	}
	public void setBudgetAmount(int budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public String getBudgetCurrency() {
		return budgetCurrency;
	}
	public void setBudgetCurrency(String budgetCurrency) {
		this.budgetCurrency = budgetCurrency;
	}


	@Override
	public String toString() {
		return "\nDaily [planId=" + planId + ", dailyId=" + dailyId + ", dailyCate=" + dailyCate + ", dailyDetail="
				+ dailyDetail + ", dayNo=" + dayNo + ", dayTime=" + dayTime + ", budgetAmount=" + budgetAmount
				+ ", budgetCurrency=" + budgetCurrency + "]";
	}
	
}
