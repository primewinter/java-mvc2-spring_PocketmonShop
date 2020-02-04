package com.model2.mvc.service.domain;

public class Party {
	
	private String partyId;			//party_id
	private String partyUserId;		//party_user_id
	private String partyType;		//party_type (CHAR) :: A(동행), P(플래너)
	private String refId;			//ref_id
	private String partyRole;		//party_role (CHAR) :: K(주인장), M(멤버)
	
	private String partyUserNickname;
	
	
	public Party() {
		super();
	}
	


	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	
	public String getPartyUserId() {
		return partyUserId;
	}
	public void setPartyUserId(String partyUserId) {
		this.partyUserId = partyUserId;
	}
	
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	
	public String getPartyRole() {
		return partyRole;
	}
	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
	}
	
	public String getPartyUserNickname() {
		return partyUserNickname;
	}
	public void setPartyUserNickname(String partyUserNickname) {
		this.partyUserNickname = partyUserNickname;
	}




	@Override
	public String toString() {
		return "Party [partyId=" + partyId + ", partyUserId=" + partyUserId + ", partyType=" + partyType + ", refId="
				+ refId + ", partyRole=" + partyRole + ", partyUserNickname=" + partyUserNickname + "]";
	}

	
}
