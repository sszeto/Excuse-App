package com.excuseMe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfileCreate extends Activity {
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_create);
		
		
		Spinner sexSpin= (Spinner) findViewById(R.id.sexSpinner);
		Spinner ageSpin= (Spinner) findViewById(R.id.ageSpinner);
		Spinner relationshipSpin= (Spinner) findViewById(R.id.relationshipSpinner);
		Spinner personalitySpin= (Spinner) findViewById(R.id.personalitySpinner);
		Spinner ethnicitySpin= (Spinner) findViewById(R.id.ethnicitySpinner);
		Spinner locationSpin= (Spinner) findViewById(R.id.locationSpinner);
		Spinner incomeSpin= (Spinner) findViewById(R.id.incomeSpinner);
		Spinner brotherSpin= (Spinner) findViewById(R.id.brotherSpinner);
		Spinner sisterSpin= (Spinner) findViewById(R.id.sisterSpinner);
		Spinner parentSpin= (Spinner) findViewById(R.id.parentSpinner);
		
		
		
		ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this,
		        R.array.sex_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
		        R.array.age_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> relationshipAdapter = ArrayAdapter.createFromResource(this,
		        R.array.relationship_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> personalityAdapter = ArrayAdapter.createFromResource(this,
		        R.array.personality_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> ethnicityAdapter = ArrayAdapter.createFromResource(this,
		        R.array.ethnicity_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this,
		        R.array.location_array, android.R.layout.simple_spinner_item);

		ArrayAdapter<CharSequence> incomeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.income_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> brotherAdapter = ArrayAdapter.createFromResource(this,
		        R.array.brother_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> sisterAdapter = ArrayAdapter.createFromResource(this,
		        R.array.sister_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> parentAdapter = ArrayAdapter.createFromResource(this,
		        R.array.parents_array, android.R.layout.simple_spinner_item);
		


		sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		relationshipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		personalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ethnicityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		brotherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sisterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		parentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		
		
		
		
		sexSpin.setAdapter(sexAdapter);
		ageSpin.setAdapter(ageAdapter);
		relationshipSpin.setAdapter(relationshipAdapter);
		personalitySpin.setAdapter(sexAdapter);
		ethnicitySpin.setAdapter(ethnicityAdapter);
		locationSpin.setAdapter(locationAdapter);
		incomeSpin.setAdapter(incomeAdapter);
		brotherSpin.setAdapter(brotherAdapter);
		sisterSpin.setAdapter(sisterAdapter);
		parentSpin.setAdapter(parentAdapter);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
