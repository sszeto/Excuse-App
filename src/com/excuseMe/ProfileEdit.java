package com.excuseMe;

import com.excuseMe.account.Info;
import com.excuseMe.dbAccess.accountAccess;
import com.google.gson.Gson;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class ProfileEdit extends Activity {
	
	
	int userId;

	String username, name;
	
	TextView nameView;

	Spinner editSexSpin, editAgeSpin, editRelationshipSpin, editPersonalitySpin, editEthnicitySpin, editLocationSpin, 
			editIncomeSpin, editBrotherSpin, editSisterSpin, editParentSpin;

	int sexId, ageId, relationshipId, personalityId, ethnicityId, locationId, incomeId
	,brotherId, sisterId, parentId;
	
	Info myInfo;
	
	accountAccess a;
	Gson g;
	SharedPreferences myPref;
	
	Button editBtn;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_edit);
		
		 a = new accountAccess();
		 g = new Gson();
		 myPref = getSharedPreferences("ExcuseApp",0);
		 userId = myPref.getInt("userId", -1);
		 username = myPref.getString("username", "");
		 name = myPref.getString("name", "");
		 
		 
		 if(userId == -1){
			 Intent i = new Intent(ProfileEdit.this, UserLogin.class);
			 startActivity(i);
		 }
		 
		 	nameView = (TextView)findViewById(R.id.editNameView);
		 	editBtn = (Button)findViewById(R.id.editBtn);
		 	
			editSexSpin= (Spinner) findViewById(R.id.editSexSpinner);
			editAgeSpin= (Spinner) findViewById(R.id.editAgeSpinner);
			editRelationshipSpin= (Spinner) findViewById(R.id.editRelationshipSpinner);
			editPersonalitySpin= (Spinner) findViewById(R.id.editPersonalitySpinner);
			editEthnicitySpin= (Spinner) findViewById(R.id.editEthnicitySpinner);
			editLocationSpin= (Spinner) findViewById(R.id.editLocationSpinner);
			editIncomeSpin= (Spinner) findViewById(R.id.editIncomeSpinner);
			editBrotherSpin= (Spinner) findViewById(R.id.editBrotherSpinner);
			editSisterSpin= (Spinner) findViewById(R.id.editSisterSpinner);
			editParentSpin= (Spinner) findViewById(R.id.editParentSpinner);
		 

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
			
			
			editSexSpin.setAdapter(sexAdapter);
			editAgeSpin.setAdapter(ageAdapter);
			editRelationshipSpin.setAdapter(relationshipAdapter);
			editPersonalitySpin.setAdapter(personalityAdapter);
			editEthnicitySpin.setAdapter(ethnicityAdapter);
			editLocationSpin.setAdapter(locationAdapter);
			editIncomeSpin.setAdapter(incomeAdapter);
			editBrotherSpin.setAdapter(brotherAdapter);
			editSisterSpin.setAdapter(sisterAdapter);
			editParentSpin.setAdapter(parentAdapter);
			
			setUserDetails();
			

			editSexSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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


			editAgeSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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


			editRelationshipSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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


			editPersonalitySpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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


			editEthnicitySpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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


			editLocationSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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

			editIncomeSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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


			editBrotherSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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


			editSisterSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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


			editParentSpin.setOnItemSelectedListener(new  OnItemSelectedListener() { 

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






			editBtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View w) {

					realUpdateProfile();


				}


			});

		}
			
	
	
	public void realUpdateProfile(){
		a.updateUserInfo(userId, ageId, sexId, incomeId, locationId, relationshipId, ethnicityId, personalityId, parentId, brotherId, sisterId,
				
				new RestCallback(){

					@Override
					public void onTaskComplete(Object result) {
						Intent myIntent = new Intent(ProfileEdit.this, RecordUpdated.class);
						myIntent.putExtra("message", "Profile Updated");
						startActivity(myIntent);
					}
			
		});	
	}
		
	
	
	
	
	public void setUserDetails(){
		a.getUserProfile(userId, new RestCallback(){
			@Override
			public void onTaskComplete(Object result) {
				
				setMyInfo(g.fromJson((String)result, Info.class));
			
				setSexId(myInfo.getGenderId());
				setAgeId(myInfo.getAgeId());
				setRelationshipId(myInfo.getRelationshipId());
				setPersonalityId(myInfo.getPersonalityId());
				setEthnicityId(myInfo.getEthnicityId());
				setLocationId(myInfo.getLocationId());
				setIncomeId(myInfo.getIncomeId());
				setBrotherId(myInfo.getBrotherId());
				setSisterId(myInfo.getSisterId());
				setParentId(myInfo.getFamilyId());
				
				nameView.setText(name);
				
				editSexSpin.setSelection(sexId - 1);
				editAgeSpin.setSelection(ageId - 1);
				editRelationshipSpin.setSelection(relationshipId - 1);
				editPersonalitySpin.setSelection(personalityId - 1);
				editEthnicitySpin.setSelection(ethnicityId - 1);
				editLocationSpin.setSelection(locationId - 1);
				editIncomeSpin.setSelection(incomeId - 1);
				editBrotherSpin.setSelection(brotherId - 1);
				editSisterSpin.setSelection(sisterId - 1);
				editParentSpin.setSelection(parentId - 1);
			}
		});



	}




	private int getUserId() {
		return userId;
	}



	private void setUserId(int userId) {
		this.userId = userId;
	}



	private String getUsername() {
		return username;
	}



	private void setUsername(String username) {
		this.username = username;
	}



	private String getName() {
		return name;
	}



	private void setName(String name) {
		this.name = name;
	}



	private TextView getNameView() {
		return nameView;
	}



	private void setNameView(TextView nameView) {
		this.nameView = nameView;
	}



	private Spinner getEditSexSpin() {
		return editSexSpin;
	}



	private void setEditSexSpin(Spinner editSexSpin) {
		this.editSexSpin = editSexSpin;
	}



	private Spinner getEditAgeSpin() {
		return editAgeSpin;
	}



	private void setEditAgeSpin(Spinner editAgeSpin) {
		this.editAgeSpin = editAgeSpin;
	}



	private Spinner getEditRelationshipSpin() {
		return editRelationshipSpin;
	}



	private void setEditRelationshipSpin(Spinner editRelationshipSpin) {
		this.editRelationshipSpin = editRelationshipSpin;
	}



	private Spinner getEditPersonalitySpin() {
		return editPersonalitySpin;
	}



	private void setEditPersonalitySpin(Spinner editPersonalitySpin) {
		this.editPersonalitySpin = editPersonalitySpin;
	}



	private Spinner getEditEthnicitySpin() {
		return editEthnicitySpin;
	}



	private void setEditEthnicitySpin(Spinner editEthnicitySpin) {
		this.editEthnicitySpin = editEthnicitySpin;
	}



	private Spinner getEditLocationSpin() {
		return editLocationSpin;
	}



	private void setEditLocationSpin(Spinner editLocationSpin) {
		this.editLocationSpin = editLocationSpin;
	}



	private Spinner getEditIncomeSpin() {
		return editIncomeSpin;
	}



	private void setEditIncomeSpin(Spinner editIncomeSpin) {
		this.editIncomeSpin = editIncomeSpin;
	}



	private Spinner getEditBrotherSpin() {
		return editBrotherSpin;
	}



	private void setEditBrotherSpin(Spinner editBrotherSpin) {
		this.editBrotherSpin = editBrotherSpin;
	}



	private Spinner getEditSisterSpin() {
		return editSisterSpin;
	}



	private void setEditSisterSpin(Spinner editSisterSpin) {
		this.editSisterSpin = editSisterSpin;
	}



	private Spinner getEditParentSpin() {
		return editParentSpin;
	}



	private void setEditParentSpin(Spinner editParentSpin) {
		this.editParentSpin = editParentSpin;
	}



	private int getSexId() {
		return sexId;
	}



	private void setSexId(int sexId) {
		this.sexId = sexId;
	}



	private int getAgeId() {
		return ageId;
	}



	private void setAgeId(int ageId) {
		this.ageId = ageId;
	}



	private int getRelationshipId() {
		return relationshipId;
	}



	private void setRelationshipId(int relationshipId) {
		this.relationshipId = relationshipId;
	}



	private int getPersonalityId() {
		return personalityId;
	}



	private void setPersonalityId(int personalityId) {
		this.personalityId = personalityId;
	}



	private int getEthnicityId() {
		return ethnicityId;
	}



	private void setEthnicityId(int ethnicityId) {
		this.ethnicityId = ethnicityId;
	}



	private int getLocationId() {
		return locationId;
	}



	private void setLocationId(int locationId) {
		this.locationId = locationId;
	}



	private int getIncomeId() {
		return incomeId;
	}



	private void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}



	private int getBrotherId() {
		return brotherId;
	}



	private void setBrotherId(int brotherId) {
		this.brotherId = brotherId;
	}



	private int getSisterId() {
		return sisterId;
	}



	private void setSisterId(int sisterId) {
		this.sisterId = sisterId;
	}



	private int getParentId() {
		return parentId;
	}



	private void setParentId(int parentId) {
		this.parentId = parentId;
	}



	private Info getMyInfo() {
		return myInfo;
	}



	private void setMyInfo(Info myInfo) {
		this.myInfo = myInfo;
	}



	private accountAccess getA() {
		return a;
	}



	private void setA(accountAccess a) {
		this.a = a;
	}



	private Gson getG() {
		return g;
	}



	private void setG(Gson g) {
		this.g = g;
	}



	private SharedPreferences getMyPref() {
		return myPref;
	}



	private void setMyPref(SharedPreferences myPref) {
		this.myPref = myPref;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
