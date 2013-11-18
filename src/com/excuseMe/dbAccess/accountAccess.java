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
