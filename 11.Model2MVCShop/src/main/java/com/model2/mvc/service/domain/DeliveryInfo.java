package com.model2.mvc.service.domain;

import java.sql.Date;

public class DeliveryInfo {
	
	private int tranNo;
	private String userId;
	private String receiverName;
	private String receiverPhone;
	private String request;
	private String address;
	private Date orderDate;
	private String statusCode;
	
	public DeliveryInfo() {
	}

	public int getTranNo() {
		return tranNo;
	}

	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String buyerId) {
		this.userId = buyerId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "DeliveryInfo [tranNo=" + tranNo + ", userId=" + userId + ", receiverName=" + receiverName
				+ ", receiverPhone=" + receiverPhone + ", request=" + request + ", address=" + address + ", orderDate="
				+ orderDate + ", statusCode=" + statusCode + "]";
	}

}
