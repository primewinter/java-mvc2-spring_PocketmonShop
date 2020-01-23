package com.model2.mvc.service.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Push {
	
	private String pushId; //push_id
	private String pushType; //push_type
	private String refId; //ref_id
	private Timestamp pushDate; //push_date
	private String receiverId; //receiver_id
	private String read; //read
	private int cmtCount; //cmt_count
	// 출력을 위한 field
	private int unread; //안 읽은 알림 개수
	private String pushMsg; //view단에 출력할 알림 메시지
	private String pushTime; //ss초 전, mm분 전, hh시간 전, yyyy.mm.dd  
	private String title; //게시글 제목, 댓글 내용, 플래너 이름
	
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
		if(getPushType().trim().equals("R")) {
			return  "[댓글] ["+getTitle()+"]에 댓글이 ("+getCmtCount()+")개 달렸습니다. "+getPushTime();
		} else if(getPushType().trim().equals("P")) {
			return  "[초대] "+ getRefId()+"번 플래너에 초대되었습니다. "+getPushTime();
		} else if(getPushType().trim().equals("A")) {
			return  "[동행] "+ getRefId()+"번 동행에 신청이 들어왔습니다. "+getPushTime();
		} else {
			return  "[알림] "+ getRefId()+"번 알림이 있습니다. "+getPushTime();
		}
	}
	public void setPushMsg(String pushMsg) {
		this.pushMsg = pushMsg;
	}
	
	public String getPushTime() {
		long now = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy.mm.dd");
		long pDate = pushDate.getTime();
		long diff = now-pDate;
		
		long sec = diff/1000; //초 단위
		long min = diff/(60*1000); //분 단위
		long hour = diff/(1000*60*60); //시간 단위
		
		if( sec == 0 ) {
			return "1초 전";
		} else if( sec < 60 ) {
			return sec+"초 전";
		} else if(min < 60) {
			return min+"분 전";
		} else if(hour < 24) {
			return hour+"시간 전";
		} else {
			return dayTime.format(pushDate);
		}
	}
	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Push [pushId=" + pushId + ", pushType=" + pushType + ", refId=" + refId + ", pushDate=" + pushDate
				+ ", receiverId=" + receiverId + ", read=" + read + ", cmtCount=" + cmtCount + ", unread=" + unread
				+ ", pushMsg=" + pushMsg + ", pushTime=" + pushTime + "]";
	}
	

}
