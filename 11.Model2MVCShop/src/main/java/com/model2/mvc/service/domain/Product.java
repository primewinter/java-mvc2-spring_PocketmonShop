package com.model2.mvc.service.domain;

import java.sql.Date;
import java.util.Arrays;



public class Product {
	
	private String[] fileNameArr;
	private String fileName;
	private String manuDate;
	private int price;
	private String prodDetail;
	private String prodName;
	private int prodNo;
	private Date regDate;
	private int stock;
	private int best;
	private int quantity;
	
	public Product(){
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		setFileNameArr(fileName.split(","));
	    this.fileName = fileName;
	}
	public String[] getFileNameArr() {
		return fileNameArr;
	}
	public void setFileNameArr(String[] fileNameArr) {
		this.fileNameArr = fileNameArr;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate.replace("-","");
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getBest() {
		return best;
	}

	public void setBest(int best) {
		this.best = best;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	
	@Override
	public String toString() {
		return "Product [fileNameArr=" + Arrays.toString(fileNameArr) + ", fileName=" + fileName
				+ ", manuDate=" + manuDate + ", price=" + price + ", prodDetail=" + prodDetail + ", prodName="
				+ prodName + ", prodNo=" + prodNo + ", regDate=" + regDate + ", stock=" + stock + ", best=" + best
				+ ", quantity=" + quantity + "]";
	}



	
	


}