package com.model2.mvc.service.domain;

import java.sql.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class ChatRoom {
	
	private ObjectId _id;
	private String chatRoomId; // chatRoom_id
	private String chatRoomName; // chatRoom_name
	private int chatMemNum; // chatMem_num ä�� ���� �ο�
	private String creator;
	private List<String> chatMems; // ä�� ���� ȸ�� ���
	private Date createdDate;

	// ��¿� field
	private String lastChat; // ������ �޽���
	private int unreadMsgCount; // �� ���� �޽��� ��
	
	

	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getChatRoomId() {
		return _id.toString();
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getChatRoomName() {
		return chatRoomName;
	}
	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}
	public int getChatMemNum() {
		return chatMemNum;
	}
	public void setChatMemNum(int chatMemNum) {
		this.chatMemNum = chatMemNum;
	}
	public List<String> getChatMems() {
		return chatMems;
	}
	public void setChatMems(List<String> chatMems) {
		this.chatMems = chatMems;
	}
	public String getLastChat() {
		return lastChat;
	}
	public void setLastChat(String lastChat) {
		this.lastChat = lastChat;
	}
	public int getUnreadMsgCount() {
		return unreadMsgCount;
	}
	public void setUnreadMsgCount(int unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "ChatRoom [_id=" + _id + ", chatRoomId=" + chatRoomId + ", chatRoomName=" + chatRoomName
				+ ", chatMemNum=" + chatMemNum + ", creator=" + creator + ", chatMems=" + chatMems + ", createdDate="
				+ createdDate + ", lastChat=" + lastChat + ", unreadMsgCount=" + unreadMsgCount + "]";
	}

	
	

}
