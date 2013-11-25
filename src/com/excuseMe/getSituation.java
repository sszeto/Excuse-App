package com.excuseMe;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class getSituation extends Activity implements OnItemSelectedListener {
	
	int userId, situationId, timeId,timeOfDayId;
	Utilities util = new Utilities();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_excuse_situation);
		
		Intent i = getIntent();
		userId = i.getIntExtra("userId", -1);
		ArrayList<String> s = i.getStringArrayListExtra("situations");
		
		Button nextBtn = (Button)findViewById(R.id.submitSituationBtn);
	
		Spinner sit = (Spinner)findViewById(R.id.situationSpinner);
		Spinner time = (Spinner)findViewById(R.id.timeSpinner);
		Spinner timeOfDay = (Spinner)findViewById(R.id.timeOfDaySpinner);
		
		ArrayAdapter<String> sitAdapter = 
				new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, s);
		
		ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,
				R.array.time_array, android.R.layout.simple_spinner_item);

		ArrayAdapter<CharSequence> timeOfDayAdapter = ArrayAdapter.createFromResource(this,
				R.array.time_of_day_array, android.R.layout.simple_spinner_item);
		
				
		sitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		timeOfDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		sit.setAdapter(sitAdapter);
		time.setAdapter(timeAdapter);
		timeOfDay.setAdapter(timeOfDayAdapter);
		
		
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
				Intent myIntent = new Intent(getSituation.this, ExcuseOutput.class);
				myIntent.putExtra("situationId", situationId);
				myIntent.putExtra("timeId", timeId);
				startActivity(myIntent);
				
				
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
