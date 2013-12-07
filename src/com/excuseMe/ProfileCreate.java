package com.excuseMe;

import com.excuseMe.dbAccess.AccountAccessDB;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class ProfileCreate extends Activity implements OnItemSelectedListener {

	int userId; // user id 

	String username, name; 
	
	TextView nameView; // field to be populated by name

	// all spinners to be used 
	Spinner sexSpin, ageSpin, relationshipSpin, personalitySpin, ethnicitySpin, locationSpin, incomeSpin
	,brotherSpin, sisterSpin, parentSpin;

	//all ids to corresppond for database use
	int sexId, ageId, relationshipId, personalityId, ethnicityId, locationId, incomeId
	,brotherId, sisterId, parentId;
	
	AccountAccessDB a;
	
	SharedPreferences myPref;

	// Activities to do on start of activity
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_create);

		 a = new AccountAccessDB();
		 myPref = getSharedPreferences("ExcuseApp",0); // set global shared preferences 
		
		setUsername(myPref.getString("username", "")); // sets username from preferences 
		setUserId(myPref.getInt("userId", -1)); //sets userId from preferences 

		Button createBtn = (Button)findViewById(R.id.createBtn);

		nameView = (TextView)findViewById(R.id.nameView);
		nameView.setText(myPref.getString("name", ""));    // grabs user name from preferences and displays in textview 
		
		sexSpin= (Spinner) findViewById(R.id.sexSpinner);
		ageSpin= (Spinner) findViewById(R.id.ageSpinner);
		relationshipSpin= (Spinner) findViewById(R.id.relationshipSpinner);
		personalitySpin= (Spinner) findViewById(R.id.personalitySpinner);
		ethnicitySpin= (Spinner) findViewById(R.id.ethnicitySpinner);
		locationSpin= (Spinner) findViewById(R.id.locationSpinner);
		incomeSpin= (Spinner) findViewById(R.id.incomeSpinner);
		brotherSpin= (Spinner) findViewById(R.id.brotherSpinner);
		sisterSpin= (Spinner) findViewById(R.id.sisterSpinner);
		parentSpin= (Spinner) findViewById(R.id.parentSpinner);

		
		//sets all spinners to correct arrays in strings.xml 
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
				R.array.family_array, android.R.layout.simple_spinner_item);


		//sets all spinners to a simple spinner dropdown
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


		//sets all adapters 
		sexSpin.setAdapter(sexAdapter);
		ageSpin.setAdapter(ageAdapter);
		relationshipSpin.setAdapter(relationshipAdapter);
		personalitySpin.setAdapter(personalityAdapter);
		ethnicitySpin.setAdapter(ethnicityAdapter);
		locationSpin.setAdapter(locationAdapter);
		incomeSpin.setAdapter(incomeAdapter);
		brotherSpin.setAdapter(brotherAdapter);
		sisterSpin.setAdapter(sisterAdapter);
		parentSpin.setAdapter(parentAdapter);


		// ID in DB = pos + 1 for all spinners
		sexSpin.setOnItemSelectedListener(new  OnItemSelectedListener() {    

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setSexId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});


		ageSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setAgeId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});


		relationshipSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setRelationshipId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});


		personalitySpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setPersonalityId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});


		ethnicitySpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setEthnicityId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});


		locationSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setLocationId(pos+1);
				;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});

		incomeSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setIncomeId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});


		brotherSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setBrotherId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});


		sisterSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setSisterId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});


		parentSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int pos, long id) {

				setParentId(pos+1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do Nothing

			}

		});






		createBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View w) {
				
				realCreateProfile();  //create profile
				
				Intent i = new Intent(ProfileCreate.this, UserPanel.class);
				startActivity(i);




			}


		});

	}
	
	
	// create profile info ... calls createUserInfo() from accountAccess 
	public void realCreateProfile(){
		a.createUserInfo(userId, ageId, sexId, incomeId, locationId, relationshipId, ethnicityId, personalityId, parentId, brotherId, sisterId, 
				new RestCallback(){

			@Override
			public void onTaskComplete(Object result) {
				
			}
		});





	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// Implemented Individually

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// Nothing happens

	}



	protected void setSexId(int sexId) {
		this.sexId = sexId;
	}



	protected void setAgeId(int ageId) {
		this.ageId = ageId;
	}



	protected void setRelationshipId(int relationshipId) {
		this.relationshipId = relationshipId;
	}



	protected void setPersonalityId(int personalityId) {
		this.personalityId = personalityId;
	}



	protected void setEthnicityId(int ethnicityId) {
		this.ethnicityId = ethnicityId;
	}



	protected void setLocationId(int locationId) {
		this.locationId = locationId;
	}



	protected void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}



	protected void setBrotherId(int brotherId) {
		this.brotherId = brotherId;
	}



	protected void setSisterId(int sisterId) {
		this.sisterId = sisterId;
	}



	protected void setParentId(int parentId) {
		this.parentId = parentId;
	}



	protected void setUserId(int userId) {
		this.userId = userId;
	}



	protected String getUsername() {
		return username;
	}



	protected void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
