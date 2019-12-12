package com.model2.mvc.service.domain;

import java.sql.Date;

public class PointHistory {

	//Field
	String userId;
	String contents;
	int tranNo;
	int point;
	String action;
	Date regDate;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getTranNo() {
		return tranNo;
	}
	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "PointHistory [userId=" + userId + ", contents=" + contents + ", point=" + point + ", action=" + action
				+ ", regDate=" + regDate + "]";
	}
	
}
