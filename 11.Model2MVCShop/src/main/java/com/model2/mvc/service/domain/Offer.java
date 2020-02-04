package com.model2.mvc.service.domain;


import java.sql.Timestamp;


public class Offer {
	
	private String offerId; 		//offer_id
	private String offerType; 		//offer_type (CHAR) :: A(동행), P(플래너)
	private String refId; 			//ref_id
	private String offerMsg; 		//offer_msg
	private String offerStatus; 	//offer_status (CHAR) :: S(제안중), A(수락), R(거절)
	private String fromUserId; 		//from_user_id
	private String toUserId;		//to_user_id
	private Timestamp offerDate; 	//offer_date
	
	public Offer() {
	}

	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getOfferMsg() {
		return offerMsg;
	}
	public void setOfferMsg(String offerMsg) {
		this.offerMsg = offerMsg;
	}

	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}


	public Timestamp getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Timestamp offerDate) {
		this.offerDate = offerDate;
	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", offerType=" + offerType + ", refId=" + refId + ", offerMsg=" + offerMsg
				+ ", offerStatus=" + offerStatus + ", fromUserId=" + fromUserId + ", toUserId=" + toUserId
				+ ", offerDate=" + offerDate + "]";
	}

	
}
