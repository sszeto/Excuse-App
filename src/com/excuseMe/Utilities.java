package com.excuseMe;

import com.excuseMe.dbAccess.AccountAccessDB;
import com.ppierson.webservicetasks.RestCallback;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class Utilities {
	int userId;
	AccountAccessDB a;
	SharedPreferences pref;
	
	public Utilities(){
		
	}
	
	public void setGlobalId(String username, SharedPreferences pref){
		setPref(pref);
		a = new AccountAccessDB();
		
		a.getUserId(username, new RestCallback(){
			@Override
			public void onTaskComplete(Object result) {
				setUserId(Integer.parseInt((String)result));
				SharedPreferences.Editor editor = getPref().edit();
				editor.putInt("userId", Integer.parseInt((String)result));
				editor.commit();
								
				Log.d("User Id: ","User Id: " + userId);
			}	
		});
	}
	
	public String nameCase(String first, String last){
		
		return first.substring(0,1).toUpperCase() + first.substring(1).toLowerCase() + " " + 
				last.substring(0,1).toUpperCase() + last.substring(1).toLowerCase();

	}
	
	
	public void logout(SharedPreferences myPref){
		SharedPreferences.Editor editor = myPref.edit();
		editor.putBoolean("loggedIn", false);
		editor.putInt("userId", -1);
		editor.putString("username", null);
		editor.putString("infoJson", null);
		editor.putString("name", null);
		editor.commit();
	}
	
	public void alert(String message, Context cont){
		AlertDialog.Builder alert = new AlertDialog.Builder(cont);
		alert.setMessage(message);
		alert.setTitle("ExcuseMe");
		alert.setPositiveButton("Ok", null);
		alert.setCancelable(true);
		alert.create().show();
	}
	
	
	
	public void alertNI(Context cont){
		AlertDialog.Builder alert = new AlertDialog.Builder(cont);
		alert.setMessage("Not Yet Implemented");
		alert.setTitle("ExcuseMe");
		alert.setPositiveButton("Ok", null);
		alert.setCancelable(true);
		alert.create().show();
	}

	private SharedPreferences getPref() {
		return pref;
	}

	private void setPref(SharedPreferences pref) {
		this.pref = pref;
	}

	private int getUserId() {
		return userId;
	}

	private void setUserId(int userId) {
		this.userId = userId;
	}



}
