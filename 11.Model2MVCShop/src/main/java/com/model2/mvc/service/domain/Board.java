package com.model2.mvc.service.domain;

import java.sql.Date;

public class Board {
	
	private int boardNo;
	private String userId;
	private String title;
	private String content;
	private Date regDate;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
	

}
