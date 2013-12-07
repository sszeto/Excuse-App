package com.excuseMe;

import java.util.ArrayList;

import com.excuseMe.account.Info;
import com.excuseMe.dbAccess.AccountAccessDB;
import com.google.gson.Gson;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserPanel extends Activity{

	Utilities util = new Utilities();
	int userId;
	AccountAccessDB acc = new AccountAccessDB();
	Gson g = new Gson();

	SharedPreferences myPref;
	String infoJson, username, name;  //string for profileInformation, username, and name 
	Boolean loggedIn;  // is the user logged in?


	// Activities to do on start of activity
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_panel);

		Button getExcuseBtn = (Button)findViewById(R.id.findExcuseBtn);
		Button editProfileBtn = (Button)findViewById(R.id.editProfileBtn);
		Button submitExcuseBtn = (Button)findViewById(R.id.submitExcuseBtn);
		Button logoutBtn = (Button)findViewById(R.id.logoutBtn);
		
		TextView welcomeMsg = (TextView)findViewById(R.id.welcomePanelView);

		//sets all critical user info from shared preference 
		myPref = getSharedPreferences("ExcuseApp",0);
		name = myPref.getString("name", null);
		username = myPref.getString("username", null);
		userId = myPref.getInt("userId",-1);
		loggedIn = myPref.getBoolean("loggedIn", false);
		infoJson = myPref.getString("infoJson", null);	
		
		welcomeMsg.setText("Welcome " + name + "!");
		
		// if invalid userId or empty username is present, log user out and pass error message 
		if(userId == -1 && username == null){
			util.logout(myPref);
			Intent myIntent = new Intent(UserPanel.this, UserLoginMain.class);
			myIntent.putExtra("msg", "Sorry, something went wrong. Please Login Again!");
			startActivity(myIntent);
		}

		getExcuseBtn.setOnClickListener(new View.OnClickListener() {  // starts the get excuse process 

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(UserPanel.this, SituationRead.class);
				startActivity(myIntent);

			}
		});

		editProfileBtn.setOnClickListener(new View.OnClickListener() {  //edits profile

			@Override
			public void onClick(View v) {

				Intent myIntent;
				
				// if invalid userId is passed, log user out 
				if(userId == -1){
					util.logout(myPref);
					myIntent = new Intent(UserPanel.this, UserLoginMain.class);
					myIntent.putExtra("msg", "Sorry, something went wrong. Please Login Again!");
					
				//valid userId is in shared preferences
				}else{
					myIntent = new Intent(UserPanel.this, ProfileGet.class);
				}
				
				startActivity(myIntent);


			}
		});

		// goes to submitExcuse activity
		submitExcuseBtn.setOnClickListener(new View.OnClickListener() { // user submits an excuse

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(UserPanel.this, ExcuseCreate.class);
				startActivity(myIntent);


			}
		});



		logoutBtn.setOnClickListener(new View.OnClickListener() {  //logout

			@Override
			public void onClick(View v) {

				util.logout(myPref);
				Intent myIntent = new Intent(UserPanel.this, UserLoginMain.class);
				startActivity(myIntent);
				

			}
		});


	}	







}
