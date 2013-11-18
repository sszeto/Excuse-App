package com.excuseMe;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class getSituation extends Activity{
	
	int userId;
	Utilities util = new Utilities();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_excuse_situation);
		
		Intent i = getIntent();
		userId = i.getIntExtra("userId", -1);
		ArrayList<String> s = i.getStringArrayListExtra("situations");
		
		Button nextBtn = (Button)findViewById(R.id.submitSituationBtn);
		
		Spinner sit = (Spinner)findViewById(R.id.situationSpinner);
		
		//util.alert(s.get(2), this);
		ArrayAdapter<String> sitAdapter = 
				new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, s);
		
		sitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sit.setAdapter(sitAdapter);
		
		
		nextBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//til.alertNI(getSituation.this);
				Intent myIntent = new Intent(getSituation.this, GetTime.class);
				startActivity(myIntent);
				
			}
		});
		
		
		
	}
	
	

}
