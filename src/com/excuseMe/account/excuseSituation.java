package com.excuseMe.account;

public class excuseSituation {
	
	
	String location, hobby, ethnic, reason, 
		occupation, familyId, excuseString;
	int personalityId, income;
	boolean isSingleUse;
	
	public excuseSituation(){
		location = "";
		hobby = "";
		ethnic = "";
		reason = "";
		occupation = "";
		familyId = "";
		excuseString = "";
		
		isSingleUse = false;		
		
	}

	/**
	 * @param location the location to set
	 */
	protected void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param hobby the hobby to set
	 */
	protected void setHobby(String hobby) {
		this.hobby = hobby;
	}

	/**
	 * @param ethnic the ethnic to set
	 */
	protected void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	/**
	 * @param reason the reason to set
	 */
	protected void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @param occupation the occupation to set
	 */
	protected void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @param familyId the familyId to set
	 */
	protected void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	/**
	 * @param excuseString the excuseString to set
	 */
	protected void setExcuseString(String excuseString) {
		this.excuseString = excuseString;
	}

	/**
	 * @param personalityId the personalityId to set
	 */
	protected void setPersonalityId(int personalityId) {
		this.personalityId = personalityId;
	}

	/**
	 * @param income the income to set
	 */
	protected void setIncome(int income) {
		this.income = income;
	}

	/**
	 * @param isSingleUse the isSingleUse to set
	 */
	protected void setSingleUse(boolean isSingleUse) {
		this.isSingleUse = isSingleUse;
	}
	
	

}
