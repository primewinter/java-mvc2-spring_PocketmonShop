package com.model2.mvc.service.domain;

import java.sql.Timestamp;
import java.util.Date;

public class Day {
	
	//***********************************
	
	private int dayNo;
	private Timestamp date;
	private String dateString;
	private String cityNames;
	
	public Day() {
		super();
	}


	public int getDayNo() {
		return dayNo;
	}
	public void setDayNo(int dayNo) {
		this.dayNo = dayNo;
	}

	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
		
		if(date != null) {
			Date dateForDay = new Date(date.getTime());		//요일을 위한 dateForDay
			this.dateString = date.toString().substring(0,10) + " : "+dateForDay.toString().substring(0,3);
		}
	}

	public String getCityNames() {
		return cityNames;
	}
	public void setCityNames(String cityNames) {
		this.cityNames = cityNames;
	}

	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}


	@Override
	public String toString() {
		return "\nDay [dayNo=" + dayNo + ", date=" + date + ", dateString=" + dateString + ", cityNames=" + cityNames
				+ "]";
	}

}
