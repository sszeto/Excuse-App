package com.excuseMe;

import java.util.ArrayList;

import com.excuseMe.dbAccess.accountAccess;
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

public class GetSituation extends Activity implements OnItemSelectedListener {
	
	int userId, situationId, timeId,timeOfDayId;
	Utilities util = new Utilities();
	ArrayList<String> s;
	
	accountAccess acc;
	Gson gson;
	SharedPreferences pref;
	Spinner sit;
	
	ArrayAdapter<String> sitAdapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_excuse_situation);
		
		gson = new Gson();
		acc = new accountAccess();
		pref = getSharedPreferences("ExcuseApp",0);
		
		userId = pref.getInt("userId", -1);
		
		setSituations();
		
		Button nextBtn = (Button)findViewById(R.id.submitSituationBtn);
	
		Spinner time = (Spinner)findViewById(R.id.timeSpinner);
		Spinner timeOfDay = (Spinner)findViewById(R.id.timeOfDaySpinner);
		
		ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,
				R.array.time_array, android.R.layout.simple_spinner_item);

		ArrayAdapter<CharSequence> timeOfDayAdapter = ArrayAdapter.createFromResource(this,
				R.array.time_of_day_array, android.R.layout.simple_spinner_item);
		
			
		timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		timeOfDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		time.setAdapter(timeAdapter);
		timeOfDay.setAdapter(timeOfDayAdapter);
		
		

		time.setOnItemSelectedListener(new  OnItemSelectedListener(){

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

		
		
		
		
		nextBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				til.alertNI(getSituation.this);
				Intent myIntent = new Intent(GetSituation.this, ExcuseOutput.class);
				myIntent.putExtra("situationId", situationId);
				myIntent.putExtra("timeId", timeId);
				myIntent.putExtra("timeOfDayId", timeOfDayId);
				startActivity(myIntent);
				
				
			}
		});
		
		
		
	}
	
	
	
	public void setSituations(){
		acc.getSituationsDB(new RestCallback(){
			@Override
			public void onTaskComplete(Object result) {								
				s = gson.fromJson((String)result, ArrayList.class);
				
				sit = (Spinner)findViewById(R.id.situationSpinner);
				sitAdapter = new ArrayAdapter<String>(GetSituation.this,android.R.layout.simple_spinner_item, s);
				sitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sit.setAdapter(sitAdapter);
				
				
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
