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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExcuseOutput extends Activity {

	
	
	int userId, situationId, timeId, timeOfDayId;     // id's for database use
	Utilities util;
	Gson gson;
	Info info; 
	
	int currentId;  // current ID of excuse being displayed
	Set idSet;  //set of Id Keys from excuse array
	Iterator idIterator; //used to iterated through id's

	HashMap excuseList; //hashmap of all excuse returned by server
	TextView excuseTxt, questionTxt, lineTwo; //Text of excuse and other lines used for displaying mesages
	
	Button nextBtn, yesBtn, noBtn; // buttons for user to choose available options
	
	SharedPreferences pref;
	AccountAccessDB a;
	
	// Activities to do on start of activity
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excuse_output);  //set content view to excuse_output
		
		util = new Utilities();
		gson = new Gson();
		info = new Info();
		a = new AccountAccessDB();
		excuseList = new HashMap();
		
		pref = getSharedPreferences("ExcuseApp",0); //get global shared preference
		
		
		Intent oldIntent = getIntent();   // get intent from previous screen 
		situationId = oldIntent.getIntExtra("situationId", -1); // set situation Id passed
		timeId = oldIntent.getIntExtra("timeId", -1);  //set timeId passed 
		timeOfDayId = oldIntent.getIntExtra("timeOfDayId", -1); // set timeOfDay Id passesd 
		userId= pref.getInt("userId", -1); // get userId from glabl shared preferences 
		
		
		yesBtn = (Button)findViewById(R.id.yesBtn);
		noBtn = (Button)findViewById(R.id.noBtn); 
		nextBtn = (Button)findViewById(R.id.nextBtn);	
		
		excuseTxt = (TextView)findViewById(R.id.excuseTxt);
		questionTxt = (TextView)findViewById(R.id.questionTxt);
		lineTwo = (TextView)findViewById(R.id.endMsg);
		lineTwo.setVisibility(View.INVISIBLE);    // set end message of screen invisible
		
		
		if(userId < 0 ){   // makes sure a valid Id is passed .. if invalid Id, log out
			excuseTxt.setText("Incorrect User Id Passed!");
			util.logout(pref);
		} else{   // else get the first excuse
			realGetExcuse();
		}
		
		
		
		yesBtn.setOnClickListener(new View.OnClickListener() { //User selected excuse
			@Override
			public void onClick(View v) {
				realUseExcuse();
			}
		});
		
		nextBtn.setOnClickListener(new View.OnClickListener() { //User selected next button
			@Override
			public void onClick(View v) {
				nextExcuse();

			}
		});
		
		
		noBtn.setOnClickListener(new View.OnClickListener() { //User opted to never see excuse again
			@Override
			public void onClick(View v) {
				realBadExcuse();

			}
		});
	}
	

	
	
	private void realGetExcuse(){		//Get excuse from DB
		a.getExcuse(userId, situationId, timeId, timeOfDayId,
				
				new RestCallback(){
					@Override
					public void onTaskComplete(Object result) {    //Returns JSON of Applicable Excuses
						setExcuseList(gson.fromJson((String)result, HashMap.class));	
						setIdSet(excuseList.keySet());        //Set list of "Excuse Ids" to dedicated set
						setIdIterator(idSet.iterator());	//Iterate through selected Id's
						nextExcuse();	 //get next action
						
						Log.d("", "Got Excuse: " + (String)result + "!" );
						
					}

		});
		
	
	}
	
	
	private void realUseExcuse(){     // User used excuse
		a.useExcuse(userId, currentId,
				new RestCallback(){
					@Override
					public void onTaskComplete(Object result) {    //Steps to take after user finishes
						Log.d("", "Status: " + (String)result );
						
						excuseTxt.setText("Response Logged");
						lineTwo.setText("Thank You!");
						questionTxt.setText("What would you like to do?");	
						
						yesBtn.setText("Find Another Excuse!");
						nextBtn.setText("Bring Me Back To My User Panel!");
						noBtn.setText("Log Me Out!");
						
						setEndButtonAction();
						
					}
		});
	}
	
	
	private void realBadExcuse(){   //Never show excuse again
		a.recordBadExcuse(userId, currentId,
				new RestCallback(){
					@Override
					public void onTaskComplete(Object result) {
						Log.d("", "Status: " + (String)result );
						nextExcuse();  //Continue function
					}
		});
	}
	

	
	
	private void nextExcuse(){   //Grab next excuse from hashmap  ... If finished, prompt user for next action
		if(idIterator.hasNext()){
			currentId = Integer.parseInt((String)idIterator.next());
			excuseTxt.setText((String)excuseList.get(Integer.toString(currentId)) + "!");	
		}
		else{
			excuseTxt.setVisibility(View.INVISIBLE);
			lineTwo.setVisibility(View.VISIBLE);
			questionTxt.setText("What would you like to do?");	
			
			yesBtn.setText("Find Another Excuse!");
			nextBtn.setText("Bring Me Back To My User Panel!");
			noBtn.setText("Log Me Out!");
			
			setEndButtonAction();
		}
			
	}
	
	
	
	private void setEndButtonAction(){    //"Rewire" buttons for end actions
		
		
		//returns user to find a new excuse
		yesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(ExcuseOutput.this, SituationRead.class);
				startActivity(myIntent);

			}
		});
		
		
		//sets new action to return to user panel
		nextBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(ExcuseOutput.this, UserPanel.class);
				startActivity(myIntent);

			}
		});
		
		
		//logs the user out
		noBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				util.logout(pref);
				Intent myIntent = new Intent(ExcuseOutput.this, UserLoginMain.class);
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
