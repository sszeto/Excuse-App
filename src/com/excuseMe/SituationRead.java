package com.excuseMe;

import java.util.ArrayList;

import com.excuseMe.dbAccess.AccountAccessDB;
import com.google.gson.Gson;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SituationRead extends Activity implements OnItemSelectedListener {
	
	int userId, situationId, timeId,timeOfDayId;    // ID's for user selections
	Utilities util = new Utilities();
	ArrayList<String> situationsArray;  //array of situations passed from database
	
	AccountAccessDB acc;
	Gson gson;
	SharedPreferences pref;
	Spinner sit;  //situation spinner 
	
	ArrayAdapter<String> sitAdapter; //situation adapter 
	
	// Activities to do on start of activity
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excuse_situation_read);  //sets layout to excuse_situation_read
		
		gson = new Gson();
		acc = new AccountAccessDB();
		pref = getSharedPreferences("ExcuseApp",0);  //sets global shared preferences 
		
		userId = pref.getInt("userId", -1);
		
		
		// if invalid email, logs user out and sends user to logn page 
		if(userId == -1){
			util.logout(pref);
			Intent myIntent = new Intent(SituationRead.this, UserLoginMain.class);
			myIntent.putExtra("msg", "Sorry, something went wrong. Please Login Again!");
			startActivity(myIntent);
		}
		
		// sets situations returend by JSON server response
		setSituations();
		
		Button nextBtn = (Button)findViewById(R.id.submitSituationBtn);
	
		
		Spinner time = (Spinner)findViewById(R.id.timeSpinner);
		Spinner timeOfDay = (Spinner)findViewById(R.id.timeOfDaySpinner);
		
		
		//sets all string arrays to correct spinners from strings.xml
		ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,
				R.array.time_array, android.R.layout.simple_spinner_item);

		ArrayAdapter<CharSequence> timeOfDayAdapter = ArrayAdapter.createFromResource(this,
				R.array.time_of_day_array, android.R.layout.simple_spinner_item);
		
			
		timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		timeOfDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		//sets correct adapters 
		time.setAdapter(timeAdapter);
		timeOfDay.setAdapter(timeOfDayAdapter);
		
		

		time.setOnItemSelectedListener(new  OnItemSelectedListener(){ // in Db, ID = pos +1 for all spinners

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {
				timeId = pos +1;

				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Nothing
				
			}
			
			
		});
		
		
		timeOfDay.setOnItemSelectedListener(new  OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {
				timeOfDayId = pos +1;

				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Nothing
				
			}
			
			
		});

		
		
		
		
		nextBtn.setOnClickListener(new View.OnClickListener() {  // passes all information to next screen 

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(SituationRead.this, ExcuseOutput.class);
				myIntent.putExtra("situationId", situationId);
				myIntent.putExtra("timeId", timeId);
				myIntent.putExtra("timeOfDayId", timeOfDayId);
				startActivity(myIntent);
				
				
			}
		});
		
		
		
	}
	
	
	
	public void setSituations(){    // grabs all situations from the current db
		acc.getSituationsDB(new RestCallback(){
			@Override
			public void onTaskComplete(Object result) {								
				// parse JSON response to an arraylist
				situationsArray = gson.fromJson((String)result, ArrayList.class);
				
				
				//sets situation spinner and options
				sit = (Spinner)findViewById(R.id.situationSpinner);
				sitAdapter = new ArrayAdapter<String>(SituationRead.this,android.R.layout.simple_spinner_item, situationsArray);
				sitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sit.setAdapter(sitAdapter);
				
				//sets actionListener fro situations spinner 
				sit.setOnItemSelectedListener(new  OnItemSelectedListener(){

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, 
							int pos, long id) {
						situationId = pos +1;

						
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// Nothing
						
					}
					
					
				});
				
			}
		});

	}
	
	
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // Already Implemented
		
    }


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	
	

}
