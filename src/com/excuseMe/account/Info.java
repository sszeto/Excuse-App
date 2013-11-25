package com.excuseMe.account;

import java.io.Serializable;

import android.content.Context;
import android.content.SharedPreferences;

import com.excuseMe.dbAccess.accountAccess;



@SuppressWarnings("serial")
public class Info implements Serializable {
	

	
	//Pretty sentences
	String name, 
		ageTxt,
		genderTxt,
		incomeTxt,
		locationTxt,
		relationshipTxt,
		ethnicityTxt,
		personalityTxt,
		familyTxt,
		brotherTxt,
		sisterTxt,
		timeUpdatedTxt; 
		
	//Keywords
	String username, 
		age,
		gender, 
		income, 
		location, 
		relationship,  
		ethnicity, 
		personality,
		family,
		brother,
		sister;
	
	
	int aboutId,
		userId;

	

	//Decode ID's
	int ageId,
		genderId,
		incomeId,
		locationId,
		relationshipId,
		ethnicityId,
		personalityId,
		familyId,
		brotherId,
		sisterId;
	
	
	public Info(){
		
	}
	
	
	public String writeInfo(){
		return  ageTxt + ".  " + 
				genderTxt + ".  " +
				incomeTxt + ".  " +
				locationTxt + ".  " +
				relationshipTxt + ".  " +
				ethnicityTxt + ".  " +
				personalityTxt + ".  " +
				familyTxt + ".  " +
				brotherTxt + ".  " +
				sisterTxt + ".  " + 
				timeUpdatedTxt; 
		
	}
	
	
	public void refreshInfo(){
		


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


	public String getBrotherTxt() {
		return brotherTxt;
	}


	public void setBrotherTxt(String brotherTxt) {
		this.brotherTxt = brotherTxt;
	}


	public String getSisterTxt() {
		return sisterTxt;
	}


	public void setSisterTxt(String sisterTxt) {
		this.sisterTxt = sisterTxt;
	}


	public String getTimeUpdatedTxt() {
		return timeUpdatedTxt;
	}


	public void setTimeUpdatedTxt(String timeUpdatedTxt) {
		this.timeUpdatedTxt = timeUpdatedTxt;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
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


	public String getFamily() {
		return family;
	}


	public void setFamily(String family) {
		this.family = family;
	}


	public String getBrother() {
		return brother;
	}


	public void setBrother(String brother) {
		this.brother = brother;
	}


	public String getSister() {
		return sister;
	}


	public void setSister(String sister) {
		this.sister = sister;
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
}

	