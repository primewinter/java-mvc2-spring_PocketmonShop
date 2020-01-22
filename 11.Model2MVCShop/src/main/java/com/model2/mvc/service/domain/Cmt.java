package com.model2.mvc.service.domain;

import java.sql.Date;

public class Cmt {
	
	private int cmtNo;
	private int boardNo;
	private String userId;
	private String content;
	private Date regDate;
	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}
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
		return "Cmt [cmtNo=" + cmtNo + ", boardNo=" + boardNo + ", userId=" + userId + ", content=" + content + "]";
	}
	
	

}
