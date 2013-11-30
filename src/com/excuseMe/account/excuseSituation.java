package com.excuseMe.account;

public class excuseSituation {
	
	
	String txt;
	
	int 
	ageId,
	genderId,
	incomeId,
	locationId,
	relationshipId,
	ethnicityId,
	personalityId,
	familyId,
	brotherId,
	sisterId;
	
	int 	
	situationId,
	timeId,
	timeOfDay,
	isSingleUse;
	
	public excuseSituation(){
		txt = "";
		
		ageId = -1;
		genderId = -1;
		incomeId = -1;
		locationId = -1;
		relationshipId = -1;
		ethnicityId = -1;
		personalityId = -1;
		familyId = -1;
		brotherId = -1;
		sisterId = -1;
		
		situationId = -1;
		timeId = -1;
		timeOfDay = -1;
		isSingleUse = -1;
		
		isSingleUse = 0;		
		
	}
	
	public String toString(){
		
		return "Text: " + txt + "  " + 
		"Age ID: " + ageId + "  " + 
		"Gender ID: " + genderId + "  " + 
		"Income ID: " + incomeId + "  " + 
		"Locationd ID: " + locationId + "  " + 
		"Relationship ID: " + relationshipId + "  " + 
		"Ethnicity ID: " + ethnicityId + "  " + 
		"Personality ID: " + personalityId + "  " + 
		"Family ID: " + familyId + "  " + 
		"Brother ID: " + brotherId + "  " + 
		"Sister ID: " + sisterId + "  " + 
		"Situation ID: " + situationId + "  " + 
		"Time ID: " + timeId + "  " + 
		"Time Of Day ID: " + timeOfDay + "  " + 
		"Is Single Use ID: " + isSingleUse;
		
		
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public int getAgeId() {
		return ageId;
	}

	public void setAgeId(int ageId) {
		this.ageId = ageId;
	}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(int relationshipId) {
		this.relationshipId = relationshipId;
	}

	public int getEthnicityId() {
		return ethnicityId;
	}

	public void setEthnicityId(int ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	public int getPersonalityId() {
		return personalityId;
	}

	public void setPersonalityId(int personalityId) {
		this.personalityId = personalityId;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public int getBrotherId() {
		return brotherId;
	}

	public void setBrotherId(int brotherId) {
		this.brotherId = brotherId;
	}

	public int getSisterId() {
		return sisterId;
	}

	public void setSisterId(int sisterId) {
		this.sisterId = sisterId;
	}

	public int getSituationId() {
		return situationId;
	}

	public void setSituationId(int situationId) {
		this.situationId = situationId;
	}

	public int getTimeId() {
		return timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}

	public int getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(int timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public int getIsSingleUse() {
		return isSingleUse;
	}

	public void setIsSingleUse(int isSingleUse) {
		this.isSingleUse = isSingleUse;
	}

	
}
