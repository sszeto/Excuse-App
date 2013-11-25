package com.excuseMe;

import com.excuseMe.account.Info;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ExcuseOutput extends Activity {

	
	int situationId, timeId;
	Utilities util;
	Gson gson;
	Info info; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_excuse_situation);
		
		util = new Utilities();
		gson = new Gson();
		info = new Info();
		
		Intent oldIntent = getIntent();
		situationId = oldIntent.getIntExtra("situationId", -1);
		timeId = oldIntent.getIntExtra("timeId", -1);
		
		
		Button upVoteBtn = (Button)findViewById(R.id.upVoteBtn);
		Button newExcuseBtn = (Button)findViewById(R.id.findNextExcuseBtn);
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
}
