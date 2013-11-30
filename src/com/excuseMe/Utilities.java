package com.excuseMe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Utilities {
	
	public Utilities(){
		
	}
	
	
	
	public void logout(SharedPreferences myPref){
		SharedPreferences.Editor editor = myPref.edit();
		editor.putBoolean("loggedIn", false);
		editor.putInt("userId", -1);
		editor.putString("username", null);
		editor.putString("infoJson", null);
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



}
