package com.excuseMe;

import android.app.AlertDialog;
import android.content.Context;

public class Utilities {
	
	public Utilities(){
		
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
