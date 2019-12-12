package com.model2.mvc.service.domain;

import java.sql.Date;

public class Basket {

	private int basketNo;
	private User visitor;
	private Product product;
	private int quantity;
	private Date regDate;
	private int flag;
	
	public Basket() {
	}

	public int getBasketNo() {
		return basketNo;
	}

	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}

	public User getVisitor() {
		return visitor;
	}

	public void setVisitor(User visitor) {
		this.visitor = visitor;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Basket [basketNo=" + basketNo + ", visitor=" + visitor + ", product=" + product + ", quantity=" + quantity
				+ ", regDate=" + regDate + ", flag=" + flag + "]";
	}
	
}
