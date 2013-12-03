package com.excuseMe.dbAccess;

import java.io.UnsupportedEncodingException;

import android.util.Log;

import com.google.gson.*;
import com.ppierson.webservicetasks.GetWebserviceTask;
import com.ppierson.webservicetasks.RestCallback;
import com.ppierson.webservicetasks.utils.Constants;
import com.excuseMe.account.Info;
import com.excuseMe.*;
public class AccountAccessDB {

	Gson gson; 
	Info info;
	String myResult;
	Utilities util = new Utilities();


	public AccountAccessDB(){
		gson = new Gson();
		info = new Info();


	}
	
	//updates password -- uses MD5 encryption
	public void updatePassword(String username, String password, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/resetPassword.php?username=" +
				username + "&password=" + MD5(password) ;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	//checks for correct recovery answer 
	public void checkRecoveryQuestion(String username, String answer, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/checkRecovery.php?username=" +
				username + "&getQuestion=0" + "&answer=" +  MD5(answer);
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	//gets recovery question for user, if exists
	public void getRecoveryQuestion(String username, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/checkRecovery.php?username=" +
				username + "&getQuestion=1";
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	
	//updates recovery questions
	public void updateRecovery(String username, String recoveryQ, String recoveryA, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/setRecovery.php?username=" +
				username + "&recovQ=" + recoveryQ + "&recovA=" + MD5(recoveryA);
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	//submits excuses by user
	public void submitExcuse(int userId, String excuseTxt, String excuseDesc, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/submitExcuse.php?userId=" +
				userId+ "&excuseTxt=" + excuseTxt + "&excuseDesc=" + excuseDesc;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	//record "never show again" button on excuse output
	public void recordBadExcuse(int userId, int excuseId, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/badExcuse.php?userId=" +
				userId+ "&excuseId=" + excuseId;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	
	//user used the excuse
	public void useExcuse(int userId, int excuseId, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/useExcuse.php?userId=" +
				userId+ "&excuseId=" + excuseId;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	
	//grab all excuses
	public void getExcuse(int userId, int situationId, int timeId, int timeOfDayId, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/retrieveExcuse.php?userId=" +
				userId+ "&situationId=" + situationId + "&timeId=" + timeId + "&timeOfDayId=" + timeOfDayId;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	
	//updates user info
	public void updateUserInfo(int userId, int ageId, int genderId, int incomeId,int locationId, int relationshipId,
									int ethnicityId, int personalityId, int familyId, int brotherId,
									    int sisterId, RestCallback callback){
		
		if(userId > 0){
		
		String getURL = "http://www.rantpit.com/excuseApp/updateUserInfo.php?userId=" + userId +
				"&ageId=" + ageId +
				"&genderId=" + genderId +
				"&incomeId=" + incomeId +
				"&locationId=" + locationId +
				"&relationshipId=" + relationshipId +
				"&ethnicityId=" + ethnicityId +
				"&personalityId=" + personalityId +
				"&familyId=" + familyId +
				"&brotherId=" + brotherId +
				"&sisterId=" + sisterId;
		
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);
		
		}
		

	}


	//grabs all situations for SituationRead
	public void getSituationsDB(RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/getSituations.php";
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}


	//checks user login credentials -- uses md5 encryption
	public void checkLogin(String username, String cred, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/login.php?username=" + username + "&cred=" + MD5(cred);
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}

	
	//registers user -- uses md5 encrypt
	public void register(String username, String email, String firstName, String lastName,  String cred, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/register.php?username=" + username + 
				"&email=" + email+ "&first="+ firstName + "&last="+ lastName + "&cred=" + MD5(cred);
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);		
	}


	// gets user profile 
	public void getUserProfile(int userId, RestCallback callback){
		Log.d("", "Id Passed: "+ userId);
		String getURL = "http://www.rantpit.com/excuseApp/retrieveUserInfo.php?userId="+userId;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);
		standardGetWebTask.execute(getURL);


	}

	
	//grabs user id
	public void getUserId(String user, RestCallback callback){
		Log.d("", "Username Passed: "+ user.toLowerCase());
		String getURL = "http://www.rantpit.com/excuseApp/retrieveUserId.php?username="+ user.toLowerCase(); 
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);
		standardGetWebTask.execute(getURL);


	}

	//creates a new profile
	public void createUserInfo(int userId, int ageId, int genderId, int incomeId, 
			int locationId, int relationshipId, int ethnicityId, int personalityId, int famId,
			int brotherId, int sisterId, RestCallback callback){

		Log.d("", "User Id Passed: "+ userId);
		String getURL = "http://www.rantpit.com/excuseApp/createUserInfo.php?userId="+ userId+
				"&ageId="+ageId+
				"&genderId="+genderId+
				"&incomeId="+incomeId+
				"&locationId="+locationId+
				"&relationshipId="+relationshipId+
				"&ethnicityId="+ethnicityId+
				"&personalityId="+personalityId+
				"&familyId="+famId+
				"&brotherId="+brotherId+
				"&sisterId="+sisterId;



		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);
		standardGetWebTask.execute(getURL);



	}





	//md5 encryption function
	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			return e.toString();
		} catch (UnsupportedEncodingException e) {
			return e.toString();

		}
	}



}
