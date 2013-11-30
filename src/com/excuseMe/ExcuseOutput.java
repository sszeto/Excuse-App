package com.excuseMe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.excuseMe.account.Info;
import com.excuseMe.dbAccess.accountAccess;
import com.google.gson.Gson;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExcuseOutput extends Activity {

	
	
	int userId, situationId, timeId, timeOfDayId;
	Utilities util;
	Gson gson;
	Info info; 
	
	int currentId;
	Set idSet;
	Iterator idIterator;

	HashMap excuseList = new HashMap();
	TextView excuseTxt, questionTxt, lineTwo;
	
	Button nextBtn, yesBtn, noBtn;
	
	SharedPreferences pref;
	accountAccess a;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excuse_output);
		
		util = new Utilities();
		gson = new Gson();
		info = new Info();
		a = new accountAccess();
		pref = getSharedPreferences("ExcuseApp",0);
		
		
		Intent oldIntent = getIntent();
		situationId = oldIntent.getIntExtra("situationId", -1);
		timeId = oldIntent.getIntExtra("timeId", -1);
		timeOfDayId = oldIntent.getIntExtra("timeOfDayId", -1);
		userId= pref.getInt("userId", -1);
		
		
		
		
		yesBtn = (Button)findViewById(R.id.yesBtn);
		noBtn = (Button)findViewById(R.id.noBtn);
		nextBtn = (Button)findViewById(R.id.nextBtn);
		
		
		excuseTxt = (TextView)findViewById(R.id.excuseTxt);
		questionTxt = (TextView)findViewById(R.id.questionTxt);
		lineTwo = (TextView)findViewById(R.id.lineTwo);
		
		
		if(userId < 0 ){
			util.alert("Incorrect User Id Passed!", this);
			excuseTxt.setText("Incorrect User Id Passed!");
		} else{
			realGetExcuse();
		}
		
		
		yesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				realUseExcuse();
			}
		});
		
		nextBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nextExcuse();

			}
		});
		
		
		noBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				realBadExcuse();

			}
		});
		
		
		

		
	}
	

	
	
	private void realGetExcuse(){		
		a.getExcuse(userId, situationId, timeId, timeOfDayId,
				
				new RestCallback(){
					@Override
					public void onTaskComplete(Object result) {
						setExcuseList(gson.fromJson((String)result, HashMap.class));	
						setIdSet(excuseList.keySet());
						setIdIterator(idSet.iterator());		
						nextExcuse();	
						
						Log.d("", "Got Excuse: " + (String)result + "!" );
						
					}

		});
		
	
	}
	
	
	private void realUseExcuse(){
		a.useExcuse(userId, currentId,
				new RestCallback(){
					@Override
					public void onTaskComplete(Object result) {
						Log.d("", "Status: " + (String)result );
						
						excuseTxt.setText("Your Response Has Been Logged");
						lineTwo.setText("Thank You!");
						questionTxt.setText("What would you like to do?");	
						
						yesBtn.setText("Find Another Excuse!");
						nextBtn.setText("Bring Me Back To My User Panel!");
						noBtn.setText("Log Me Out!");
						
						setEndButtonAction();
						
					}
		});
	}
	
	
	private void realBadExcuse(){
		a.recordBadExcuse(userId, currentId,
				new RestCallback(){
					@Override
					public void onTaskComplete(Object result) {
						Log.d("", "Status: " + (String)result );
						nextExcuse();
					}
		});
	}
	

	
	
	private void nextExcuse(){
		if(idIterator.hasNext()){
			currentId = Integer.parseInt((String)idIterator.next());
			excuseTxt.setText("Excuse Id: " + currentId + 
					" Excuse: " + (String)excuseList.get(Integer.toString(currentId)));	
		}
		else{
			excuseTxt.setText("Sorry, no more available excuses at this time!");
			questionTxt.setText("What would you like to do?");	
			
			yesBtn.setText("Find Another Excuse!");
			nextBtn.setText("Bring Me Back To My User Panel!");
			noBtn.setText("Log Me Out!");
			
			setEndButtonAction();
		}
			
	}
	
	
	
	private void setEndButtonAction(){
		
		yesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(ExcuseOutput.this, getSituation.class);
				startActivity(myIntent);

			}
		});
		
		
		nextBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(ExcuseOutput.this, UserPanel.class);
				startActivity(myIntent);

			}
		});
		
		noBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				util.logout(pref);
				Intent myIntent = new Intent(ExcuseOutput.this, UserLogin.class);
				startActivity(myIntent);

			}
		});
		
		
	}
	
	

	private void setExcuseList(HashMap excuseList) {
		this.excuseList = excuseList;
	}

	private void setIdSet(Set idSet) {
		this.idSet = idSet;
	}

	private void setIdIterator(Iterator idIterator) {
		this.idIterator = idIterator;
	}

}
