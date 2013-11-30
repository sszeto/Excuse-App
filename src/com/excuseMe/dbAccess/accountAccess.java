package com.excuseMe.dbAccess;

import java.io.UnsupportedEncodingException;

import android.util.Log;

import com.google.gson.*;
import com.ppierson.webservicetasks.GetWebserviceTask;
import com.ppierson.webservicetasks.RestCallback;
import com.ppierson.webservicetasks.utils.Constants;
import com.excuseMe.account.Info;
import com.excuseMe.*;
public class accountAccess {

	Gson gson; 
	Info info;
	String myResult;
	Utilities util = new Utilities();


	public accountAccess(){
		gson = new Gson();
		info = new Info();


	}
	
	public void recordBadExcuse(int userId, int excuseId, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/badExcuse.php?userId=" +
				userId+ "&excuseId=" + excuseId;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	public void useExcuse(int userId, int excuseId, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/useExcuse.php?userId=" +
				userId+ "&excuseId=" + excuseId;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	public void getExcuse(int userId, int situationId, int timeId, int timeOfDayId, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/retrieveExcuse.php?userId=" +
				userId+ "&situationId=" + situationId + "&timeId=" + timeId + "&timeOfDayId=" + timeOfDayId;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}
	
	
	
	public void updateUserInfo(int userId, int ageId, int genderId, int incomeId,int locationId, int relationshipId,
									int ethnicityId, int personalityId, int familyId, int brotherId,
									    int sisterId, RestCallback callback){
		
		if(userId > 0){
		
		String getURL = "http://www.http://rantpit.com/excuseApp/updateUserInfo.php?userId=" + userId +
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



	public void getSituationsDB(RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/getSituations.php";
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}



	public void checkLogin(String username, String cred, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/login.php?username=" + username + "&cred=" + MD5(cred);
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);

	}


	public void register(String username, String email, String firstName, String lastName,  String cred, RestCallback callback){
		String getURL = "http://www.rantpit.com/excuseApp/register.php?username=" + username + 
				"&email=" + email+ "&first="+ firstName + "&last="+ lastName + "&cred=" + MD5(cred);
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);

		standardGetWebTask.execute(getURL);		
	}



	public void getUserProfile(int userId, RestCallback callback){
		Log.d("", "Id Passed: "+ userId);
		String getURL = "http://www.rantpit.com/excuseApp/retrieveUserInfo.php?userId="+userId;
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);
		standardGetWebTask.execute(getURL);


	}




	public void getUserId(String user, RestCallback callback){
		Log.d("", "Username Passed: "+ user.toLowerCase());
		String getURL = "http://www.rantpit.com/excuseApp/retrieveUserId.php?username="+ user.toLowerCase(); 
		Log.d("", "Executing UrlAction : " + getURL);

		GetWebserviceTask standardGetWebTask = new GetWebserviceTask(callback);
		standardGetWebTask.execute(getURL);


	}


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






	public String testInfo(){


		String json = gson.toJson(info);

		return json;



	}


}
