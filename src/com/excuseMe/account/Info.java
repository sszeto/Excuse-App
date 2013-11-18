package com.excuseMe.account;

import java.io.Serializable;



@SuppressWarnings("serial")
public class Info implements Serializable {
	

	
	//Pretty sentences
	String name, 
		ageTxt,
		genderTxt,
		incomeTxt,
		locationTxt,
		relationshipTxt,
		religionTxt,
		ethnicityTxt,
		personalityTxt,
		familyTxt,
		hasBrotherTxt,
		hasSisterTxt,
		timeUpdatedTxt; 
		
	//Keywords
	String gender, 
		income, 
		location, 
		relationship, 
		religion, 
		ethnicity, 
		personality;
	
	Boolean hasFamily,
	hasBrother,
	hasSister;

	int aboutId,
		userId, 
		age;

	

	//Decode ID's
	int incomeId,
		locationId,
		relationshipId,
		religionId,
		ethnicityId,
		personalityId;
	
	
	public Info(){
		
	}
	
	
	public String writeInfo(){
		return  ageTxt + ".  " + 
				genderTxt + ".  " +
				incomeTxt + ".  " +
				locationTxt + ".  " +
				relationshipTxt + ".  " +
				religionTxt + ".  " +
				ethnicityTxt + ".  " +
				personalityTxt + ".  " +
				familyTxt + ".  " +
				hasBrotherTxt + ".  " +
				hasSisterTxt + ".  " + 
				timeUpdatedTxt; 
		
	}
	
	
	public boolean syncInfo(){
		
		//To be implemented
		
		
		return false;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAgeTxt() {
		return ageTxt;
	}


	public void setAgeTxt(String ageTxt) {
		this.ageTxt = ageTxt;
	}


	public String getGenderTxt() {
		return genderTxt;
	}


	public void setGenderTxt(String genderTxt) {
		this.genderTxt = genderTxt;
	}


	public String getIncomeTxt() {
		return incomeTxt;
	}


	public void setIncomeTxt(String incomeTxt) {
		this.incomeTxt = incomeTxt;
	}


	public String getLocationTxt() {
		return locationTxt;
	}


	public void setLocationTxt(String locationTxt) {
		this.locationTxt = locationTxt;
	}


	public String getRelationshipTxt() {
		return relationshipTxt;
	}


	public void setRelationshipTxt(String relationshipTxt) {
		this.relationshipTxt = relationshipTxt;
	}


	public String getReligionTxt() {
		return religionTxt;
	}


	public void setReligionTxt(String religionTxt) {
		this.religionTxt = religionTxt;
	}


	public String getEthnicityTxt() {
		return ethnicityTxt;
	}


	public void setEthnicityTxt(String ethnicityTxt) {
		this.ethnicityTxt = ethnicityTxt;
	}


	public String getPersonalityTxt() {
		return personalityTxt;
	}


	public void setPersonalityTxt(String personalityTxt) {
		this.personalityTxt = personalityTxt;
	}


	public String getFamilyTxt() {
		return familyTxt;
	}


	public void setFamilyTxt(String familyTxt) {
		this.familyTxt = familyTxt;
	}


	public String getHasBrotherTxt() {
		return hasBrotherTxt;
	}


	public void setHasBrotherTxt(String hasBrotherTxt) {
		this.hasBrotherTxt = hasBrotherTxt;
	}


	public String getHasSisterTxt() {
		return hasSisterTxt;
	}


	public void setHasSisterTxt(String hasSisterTxt) {
		this.hasSisterTxt = hasSisterTxt;
	}


	public String getTimeUpdatedTxt() {
		return timeUpdatedTxt;
	}


	public void setTimeUpdatedTxt(String timeUpdatedTxt) {
		this.timeUpdatedTxt = timeUpdatedTxt;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getIncome() {
		return income;
	}


	public void setIncome(String income) {
		this.income = income;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getRelationship() {
		return relationship;
	}


	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}


	public String getReligion() {
		return religion;
	}


	public void setReligion(String religion) {
		this.religion = religion;
	}


	public String getEthnicity() {
		return ethnicity;
	}


	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}


	public String getPersonality() {
		return personality;
	}


	public void setPersonality(String personality) {
		this.personality = personality;
	}


	public Boolean getHasFamily() {
		return hasFamily;
	}


	public void setHasFamily(Boolean hasFamily) {
		this.hasFamily = hasFamily;
	}


	public Boolean getHasBrother() {
		return hasBrother;
	}


	public void setHasBrother(Boolean hasBrother) {
		this.hasBrother = hasBrother;
	}


	public Boolean getHasSister() {
		return hasSister;
	}


	public void setHasSister(Boolean hasSister) {
		this.hasSister = hasSister;
	}


	public int getAboutId() {
		return aboutId;
	}


	public void setAboutId(int aboutId) {
		this.aboutId = aboutId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
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


	public int getReligionId() {
		return religionId;
	}


	public void setReligionId(int religionId) {
		this.religionId = religionId;
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


	/**
	 * @return the occupationTxt
	 */


	/**
	 * @return the hobbyTxt
	 */
	
	
	


	
}


	