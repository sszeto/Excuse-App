package com.excuseMe;

import com.excuseMe.account.Info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileGet extends Activity {
	
	
	
	Utilities u = new Utilities();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_get);
				
		TextView name = (TextView)findViewById(R.id.nameView);
	
		TextView age = (TextView)findViewById(R.id.ageView);
		TextView gender = (TextView)findViewById(R.id.genderView);
		TextView income = (TextView)findViewById(R.id.incomeView);
		TextView location = (TextView)findViewById(R.id.locationView);
		TextView relationship = (TextView)findViewById(R.id.relationshipView);
		TextView religion = (TextView)findViewById(R.id.religionView);
		TextView ethnicity = (TextView)findViewById(R.id.ethnicityView);

		TextView personality = (TextView)findViewById(R.id.personalityView);
		TextView hasFam = (TextView)findViewById(R.id.hasFamView);
		TextView hasBro = (TextView)findViewById(R.id.hasBrotherView);
		TextView hasSis = (TextView)findViewById(R.id.hasSisterView);
		TextView updatedAt = (TextView)findViewById(R.id.updatedAtView);
		
		Intent i = getIntent();
		Info myInfo = (Info)i.getSerializableExtra("userInfo");
		
		name.setText(myInfo.getName().toUpperCase());
		age.setText(myInfo.getAgeTxt());
		gender.setText(myInfo.getGenderTxt());
		income.setText(myInfo.getIncomeTxt()); 
		location.setText(myInfo.getLocationTxt());
		relationship.setText(myInfo.getRelationshipTxt()); 
		religion.setText(myInfo.getReligionTxt()); 
		ethnicity.setText(myInfo.getEthnicityTxt());
		personality.setText(myInfo.getPersonalityTxt());
		hasFam.setText(myInfo.getFamilyTxt());
		hasBro.setText(myInfo.getHasBrotherTxt());
		hasSis.setText(myInfo.getHasSisterTxt()); 
		updatedAt.setText(myInfo.getTimeUpdatedTxt());
		
		Button editButton = (Button)findViewById(R.id.editProfileButton);
		
		editButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//To Be Implemented
				u.alert("Not Yet Implemented", ProfileGet.this);
				
			}
		});

		
		

	

	}
	
	
	
	
	
	
	
}
