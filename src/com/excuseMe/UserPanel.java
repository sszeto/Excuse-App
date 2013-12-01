package com.excuseMe;

import java.util.ArrayList;

import com.excuseMe.account.Info;
import com.excuseMe.dbAccess.accountAccess;
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
	accountAccess acc = new accountAccess();
	Gson g = new Gson();

	ArrayList<String> situationsArray;

	SharedPreferences myPref;
	String infoJson, username, temp, name;
	Boolean loggedIn;



	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_panel);

		Button getExcuseBtn = (Button)findViewById(R.id.findExcuseBtn);
		Button editProfileBtn = (Button)findViewById(R.id.editProfileBtn);
		Button submitExcuseBtn = (Button)findViewById(R.id.submitExcuseBtn);
		Button logoutBtn = (Button)findViewById(R.id.logoutBtn);
		
		TextView welcomeMsg = (TextView)findViewById(R.id.welcomePanelView);

		myPref = getSharedPreferences("ExcuseApp",0);
		name = myPref.getString("name", null);
		username = myPref.getString("username", null);
		userId = myPref.getInt("userId",-1);
		loggedIn = myPref.getBoolean("loggedIn", false);
		infoJson = myPref.getString("infoJson", null);	
		
		welcomeMsg.setText("Welcome " + name + "!");

		getExcuseBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(UserPanel.this, GetSituation.class);
				startActivity(myIntent);

			}
		});

		editProfileBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent;

				if(userId == -1){
					myIntent = new Intent(UserPanel.this, ProfileCreate.class);
				}else{
					myIntent = new Intent(UserPanel.this, ProfileGet.class);
				}
				
				startActivity(myIntent);


			}
		});

		submitExcuseBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(UserPanel.this, SubmitExcuse.class);
				startActivity(myIntent);


			}
		});



		logoutBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				util.logout(myPref);
				Intent myIntent = new Intent(UserPanel.this, UserLogin.class);
				startActivity(myIntent);
				

			}
		});


	}	



	public ArrayList<String> getSituationsArray() {
		return situationsArray;
	}




	public void setSituationsArray(ArrayList<String> situationsArray) {
		this.situationsArray = situationsArray;
	}







}
