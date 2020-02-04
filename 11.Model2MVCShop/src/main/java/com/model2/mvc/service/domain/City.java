package com.model2.mvc.service.domain;

import java.util.List;

public class City {
	
	private String planId; 			//plan_id 
	private String cityId;			//city_id
	private String cityName;		//city_name
	private int visitOrder;			//visit_order
	private int cityDuration;		//city_duration
	private String tranType;		//tran_type	(CHAR) :: ����(T),����(B),�װ�(A),�丮(F),��Ÿ(E)
	
	private String tranDuration; 	//�̵� �ҿ�ð�
	
	private String cityLat;		//����
	private String cityLng; 	//�浵
	private String country;		//����
	private String cityInfo;	//��������
	private String cityImg;		//���� �̹���
	
	//private List<Bucket> bucketList;	//��Ŷ����Ʈ
	
	
	public City() {
		super();
	}

	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getVisitOrder() {
		return visitOrder;
	}
	public void setVisitOrder(int visitOrder) {
		this.visitOrder = visitOrder;
	}

	public int getCityDuration() {
		return cityDuration;
	}
	public void setCityDuration(int cityDuration) {
		this.cityDuration = cityDuration;
	}

	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getTranDuration() {
		return tranDuration;
	}
	public void setTranDuration(String tranDuration) {
		this.tranDuration = tranDuration;
	}

	public String getCityLat() {
		return cityLat;
	}
	public void setCityLat(String cityLat) {
		this.cityLat = cityLat;
	}

	public String getCityLng() {
		return cityLng;
	}
	public void setCityLng(String cityLng) {
		this.cityLng = cityLng;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getCityInfo() {
		return cityInfo;
	}
	public void setCityInfo(String cityInfo) {
		this.cityInfo = cityInfo;
	}

	public String getCityImg() {
		return cityImg;
	}
	public void setCityImg(String cityImg) {
		this.cityImg = cityImg;
	}


	@Override
	public String toString() {
		return "\nCity [planId=" + planId + ", cityId=" + cityId + ", cityName=" + cityName + ", visitOrder=" + visitOrder
				+ ", cityDuration=" + cityDuration + ", tranType=" + tranType + ", tranDuration=" + tranDuration
				+ ", cityLat=" + cityLat + ", cityLng=" + cityLng + ", country=" + country + ", cityInfo=" + cityInfo
				+ ", cityImg=" + cityImg + "]";
	}
	
	
}
