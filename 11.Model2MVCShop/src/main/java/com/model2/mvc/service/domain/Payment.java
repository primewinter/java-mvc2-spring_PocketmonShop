package com.model2.mvc.service.domain;

import java.sql.Date;

public class Payment {
	
	private int tranNo;
	private Date orderDate;
	private String paymentOption;
	private int totalAmount; 
	private int pointPay;
	private int discount;
	private int coupon;
	private int actualAmount;
	private String userId;
	private String userName;
	
	public Payment() {
	}


	public int getTranNo() {
		return tranNo;
	}


	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}


	public String getPaymentOption() {
		return paymentOption;
	}


	public void setPaymentOption(String paymentOption) {
		String po = paymentOption.trim();
		if(po.equals("cash") ) {
			paymentOption = "현금";
		} else if (po.equals("credit")) {
			paymentOption = "신용카드";
		} else if (po.equals("banking")) {
			paymentOption = "무통장 입금";
		}
		this.paymentOption = paymentOption;
	}


	public int getActualAmount() {
		return this.totalAmount-this.pointPay-this.coupon-this.discount;
	}


	public void setActualAmount(int actualAmount) {
		this.actualAmount = actualAmount;
	}


	public int getPointPay() {
		return pointPay;
	}


	public void setPointPay(int point) {
		this.pointPay = point;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public int getCoupon() {
		return coupon;
	}


	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}


	public int getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String buyerId) {
		this.userId=buyerId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public String toString() {
		return "Payment [tranNo=" + tranNo + ", orderDate=" + orderDate + ", paymentOption=" + paymentOption
				+ ", totalAmount=" + totalAmount + ", pointPay=" + pointPay + ", discount=" + discount + ", coupon="
				+ coupon + ", actualAmount=" + actualAmount + ", userId=" + userId + ", userName=" + userName + "]";
	}


}
