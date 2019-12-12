package com.model2.mvc.service.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Purchase {

	private int tranNo;
	private Date orderDate;
	private Timestamp timeStamp;
	private String tranCode;
	private Payment payment;
	private DeliveryInfo dlvyInfo;
	private List<Product> purchaseProd;
	private User buyer;
	
	public Purchase() {
	}
	
	public int getTranNo() {
		return tranNo;
	}
	
	public void setTranNo(int tranNo) {
		this.tranNo=tranNo;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTranCode() {
		return tranCode;
	}
	
	public void setTranCode(String tranCode) {
//		String code = tranCode.trim();
//		
//		if(code.equals("0")) {
//			code = "�Ǹ���";
//		} else if(code.equals("1")) {
//			code = "�ֹ��Ϸ�";
//		} else if(code.equals("2")) {
//			code = "��ǰ�غ���";
//		} else if(code.equals("3")) {
//			code = "�����";
//		} else if(code.equals("4")) {
//			code = "��ۿϷ�";
//		} else if(code.equals("Y")) {
//			code = "�ŷ��Ϸ�";
//		} else if(code.equals("X")) {
//			code = "������";
//		}
		
		this.tranCode=tranCode;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public DeliveryInfo getDlvyInfo() {
		return dlvyInfo;
	}

	public void setDlvyInfo(DeliveryInfo dlvyInfo) {
		this.dlvyInfo = dlvyInfo;
	}
	
	public List<Product> getPurchaseProd() {
		return purchaseProd;
	}

	public void setPurchaseProd(List<Product> purchaseProd) {
		this.purchaseProd = purchaseProd;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return "Purchase [payment=" + payment + ", dlvyInfo=" + dlvyInfo + ", purchaseProd=" + purchaseProd + ", buyer="
				+ buyer + "]";
	}

	

}
