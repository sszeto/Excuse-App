package com.excuseMe;

import java.util.ArrayList;

import com.excuseMe.account.Info;
import com.excuseMe.dbAccess.accountAccess;
import com.google.gson.Gson;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UserPanel extends Activity{
	
	Utilities util = new Utilities();
	int userId;
	accountAccess acc = new accountAccess();
	Gson g = new Gson();
	
	ArrayList<String> situationsArray;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_panel);
		
		Intent i = getIntent();
		userId = i.getIntExtra("userId", -1);		
		
		Button getExcuseBtn = (Button)findViewById(R.id.findExcuseBtn);
		Button editProfileBtn = (Button)findViewById(R.id.editProfileBtn);
		Button submitExcuseBtn = (Button)findViewById(R.id.submitExcuseBtn);
		
		
		
		getExcuseBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//util.alertNI(UserPanel.this);
				setSituations();
				getExcuse();
				
			}
		});

		editProfileBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getUser();
			}
		});
		
		submitExcuseBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				util.alertNI(UserPanel.this);
			}
		});

		
	}

	
	
	
	public void getUser(){
	
		
		acc.getUserProfile(userId, new RestCallback(){
			
			
			@Override
			public void onTaskComplete(Object result) {
				
				Intent myIntent = new Intent(UserPanel.this, ProfileGet.class);
				Info infoTest = g.fromJson((String)result, Info.class);
				//util.alert((String)result, UserPanel.this);
				
				myIntent.putExtra("userInfo", infoTest);
				startActivity(myIntent);
				
			}
		});
		
		
		
	}
	
	
	public void getExcuse(){
	
		
		acc.getUserProfile(userId, new RestCallback(){
			
			
			@Override
			public void onTaskComplete(Object result) {
				
				Intent myIntent = new Intent(UserPanel.this, getSituation.class);
				Info infoTest = g.fromJson((String)result, Info.class);
				//util.alert((String)result, UserPanel.this);
				
				myIntent.putExtra("userInfo", infoTest);
				myIntent.putStringArrayListExtra("situations", situationsArray);
				startActivity(myIntent);
				
			}
		});
		
		
		
	}
	
	
	
	public void setSituations(){
		
			acc.getSituationsDB(new RestCallback(){
				
				
				@Override
				public void onTaskComplete(Object result) {
					
					
					setSituationsArray(g.fromJson((String)result, ArrayList.class));
					//util.alert(situationsArray.get(2), UserPanel.this);
					//Log.d("","Done setting situations array");	
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
