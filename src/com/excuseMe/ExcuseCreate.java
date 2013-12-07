package com.excuseMe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.excuseMe.account.Info;
import com.excuseMe.dbAccess.AccountAccessDB;
import com.google.gson.Gson;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExcuseCreate extends Activity {

	Button submitBtn; // Button to submit excuse
	TextView excuseTxtView, excuseDescView; //Text fields for user to put excuses

	String excuseTxt, excuseDesc; //Strings entered by user to submit

	SharedPreferences pref;
	AccountAccessDB a;
	Utilities util;

	int userId;  // user Id

	// Activities to do on start of activity
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excuse_submit); // uses excuse_sumit layout

		util = new Utilities();
		a = new AccountAccessDB();
		pref = getSharedPreferences("ExcuseApp",0);  //get global shared preferences

		userId = pref.getInt("userId", -1); // set user Id

		submitBtn = (Button)findViewById(R.id.submitExcuseBtn);
		excuseTxtView = (TextView)findViewById(R.id.excuseSubmitTxt);
		excuseDescView = (TextView)findViewById(R.id.excuseSubmitDesc);

		// Checks to see if excuse lenght requirements are reached
		//On success... call realSubmitExucse();
		submitBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				excuseTxt = excuseTxtView.getText().toString();
				excuseDesc = excuseDescView.getText().toString();

				if(excuseTxt.length() < 10){
					util.alert("Your excuse is too short!", ExcuseCreate.this);
				}else if(excuseDesc.length() < 4){
					util.alert("Your description is too short!", ExcuseCreate.this);
				}else{
					realSubmitExcuse();
				}
				
				
			}
		});


	}
	
	
	// Action for when excuse text requirements are reached
	// calls submitExcuse function from accountAccess class
	private void realSubmitExcuse(){
		a.submitExcuse(userId, excuseTxt, excuseDesc, 
				new RestCallback(){
			@Override
			public void onTaskComplete(Object result) {
				Intent myIntent = new Intent(ExcuseCreate.this, RecordUpdated.class);
				myIntent.putExtra("message", "Excuse Submitted");
				startActivity(myIntent);
			}
		});
	}


}
