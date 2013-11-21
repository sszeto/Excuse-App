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
	
	int userId, situationId;
	Utilities util = new Utilities();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_excuse_situation);
		
		Intent i = getIntent();
		userId = i.getIntExtra("userId", -1);
		ArrayList<String> s = i.getStringArrayListExtra("situations");
		
		Button nextBtn = (Button)findViewById(R.id.submitSituationBtn);
	
		Spinner sit = (Spinner)findViewById(R.id.situationSpinner);
		
		ArrayAdapter<String> sitAdapter = 
				new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, s);
		
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
			
			
		}
		
				
				
				);
		
		
		nextBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//til.alertNI(getSituation.this);
				Intent myIntent = new Intent(getSituation.this, GetTime.class);
				myIntent.putExtra("situationId", situationId);
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
