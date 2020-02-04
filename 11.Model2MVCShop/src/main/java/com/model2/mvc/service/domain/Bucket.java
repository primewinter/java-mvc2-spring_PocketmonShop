package com.model2.mvc.service.domain;

public class Bucket {
	
	//*** bucket 할지말지 고민중..
	
	private String planId;			//plan_id
	private String cityId;			//city_id
	private String cityName;		
	
	private String bucketId;		//bucket_id
	private String bucketName;		//bucket_name
	private String bucketDetail;	//bucket_detail
	private String bucketImg;		//bucket_img
	private String bucketCheck;		//bucket_check
	
	
	public Bucket() {
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

	public String getBucketId() {
		return bucketId;
	}
	public void setBucketId(String bucketId) {
		this.bucketId = bucketId;
	}

	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketDetail() {
		return bucketDetail;
	}
	public void setBucketDetail(String bucketDetail) {
		this.bucketDetail = bucketDetail;
	}

	public String getBucketImg() {
		return bucketImg;
	}
	public void setBucketImg(String bucketImg) {
		this.bucketImg = bucketImg;
	}

	public String getBucketCheck() {
		return bucketCheck;
	}
	public void setBucketCheck(String bucketCheck) {
		this.bucketCheck = bucketCheck;
	}


	@Override
	public String toString() {
		return "Bucket [planId=" + planId + ", cityId=" + cityId + ", cityName=" + cityName + ", bucketId=" + bucketId
				+ ", bucketName=" + bucketName + ", bucketDetail=" + bucketDetail + ", bucketImg=" + bucketImg
				+ ", bucketCheck=" + bucketCheck + "]";
	}

	

}
