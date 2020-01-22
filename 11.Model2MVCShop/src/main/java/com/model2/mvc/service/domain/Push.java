package com.model2.mvc.service.domain;

import java.sql.Timestamp;

public class Push {
	
	private String pushId;
	private String pushType;
	private String refId;
	private Timestamp pushDate;
	private String receiverId;
	private String read;
	private int cmtCount;
	// 출력을 위한 field
	private int unread;
	private String pushMsg;
	
	public String getPushId() {
		return pushId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	public String getPushType() {
		return pushType;
	}
	public void setPushType(String pushType) {
		this.pushType = pushType;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public Timestamp getPushDate() {
		return pushDate;
	}
	public void setPushDate(Timestamp pushDate) {
		this.pushDate = pushDate;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public int getCmtCount() {
		return cmtCount;
	}
	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}
	public int getUnread() {
		return unread;
	}
	public void setUnread(int unread) {
		this.unread = unread;
	}
	public String getPushMsg() {
		return pushMsg;
	}
	public void setPushMsg(String pushMsg) {
		this.pushMsg = pushMsg;
	}
	@Override
	public String toString() {
		return "Push [pushId=" + pushId + ", pushType=" + pushType + ", refId=" + refId + ", pushDate=" + pushDate
				+ ", receiverId=" + receiverId + ", read=" + read + ", cmtCount=" + cmtCount + ", unread=" + unread
				+ ", pushMsg=" + pushMsg + "]";
	}


}
