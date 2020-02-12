package com.model2.mvc.service.domain;

import java.sql.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class Chat {
	
	private ObjectId _id;
	private String chatId; //chat_id
	private String chatRoomId; //chatRoom_id
	private String senderId; //sender_id
	private String chatContent; //chat_content
	private Date chatDate; //chat_date
	private List<String> readers;
	
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public List<String> getReaders() {
		return readers;
	}
	public void setReaders(List<String> readers) {
		this.readers = readers;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public Date getChatDate() {
		return chatDate;
	}
	public void setChatDate(Date chatDate) {
		this.chatDate = chatDate;
	}
	@Override
	public String toString() {
		return "Chat [_id=" + _id + ", chatId=" + chatId + ", chatRoomId=" + chatRoomId + ", senderId=" + senderId
				+ ", chatContent=" + chatContent + ", chatDate=" + chatDate + ", readers=" + readers + "]";
	}
	

}
