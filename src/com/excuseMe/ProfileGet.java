package com.excuseMe;

import com.excuseMe.account.Info;
import com.excuseMe.dbAccess.AccountAccessDB;
import com.google.gson.Gson;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileGet extends Activity {

	Gson g;
	AccountAccessDB acc;
	Utilities u;
	
	int userId;
	Info myInfo;
	TextView name, age, gender, income, location, relationship, ethnicity, personality, family, brother, sister, updatedAt;
	
	SharedPreferences myPref;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_read);
		
		myPref = getSharedPreferences("ExcuseApp",0);
		
		g = new Gson();
		acc = new AccountAccessDB();
		u = new Utilities();

		name = (TextView)findViewById(R.id.nameView);
		age = (TextView)findViewById(R.id.ageView);
		gender = (TextView)findViewById(R.id.genderView);
		income = (TextView)findViewById(R.id.incomeView);
		location = (TextView)findViewById(R.id.locationView);
		relationship = (TextView)findViewById(R.id.relationshipView);
		ethnicity = (TextView)findViewById(R.id.ethnicityView);
		personality = (TextView)findViewById(R.id.personalityView);
		family = (TextView)findViewById(R.id.familyView);
		brother = (TextView)findViewById(R.id.brotherView);
		sister = (TextView)findViewById(R.id.sisterView);
		updatedAt = (TextView)findViewById(R.id.updatedAtView);

		
		userId = myPref.getInt("userId", -1);
		
		getUser();

		Button editButton = (Button)findViewById(R.id.editProfileButton);

		editButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent myIntent = new Intent(ProfileGet.this, ProfileEdit.class);
				startActivity(myIntent);

			}
		});
	}


	public void getUser(){


		acc.getUserProfile(userId, new RestCallback(){


			@Override
			public void onTaskComplete(Object result) {
				
				setMyInfo(g.fromJson((String)result, Info.class));
				
				
				name.setText(myPref.getString("name", ""));
				age.setText(myInfo.getAgeTxt());
				gender.setText(myInfo.getGenderTxt());
				income.setText(myInfo.getIncomeTxt()); 
				location.setText(myInfo.getLocationTxt());
				relationship.setText(myInfo.getRelationshipTxt()); 
				ethnicity.setText(myInfo.getEthnicityTxt());
				personality.setText(myInfo.getPersonalityTxt());
				family.setText(myInfo.getFamilyTxt());
				brother.setText(myInfo.getBrotherTxt());
				sister.setText(myInfo.getSisterTxt()); 
				updatedAt.setText(myInfo.getTimeUpdatedTxt());
				
				SharedPreferences.Editor editor = myPref.edit();
				editor.putString("infoJson", (String)result);
				editor.commit();
				
			}
		});



	}

	
	

	protected Info getMyInfo() {
		return myInfo;
	}







	protected void setMyInfo(Info myInfo) {
		this.myInfo = myInfo;
	}







}
