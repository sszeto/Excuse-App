package com.excuseMe;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class GetTime extends Activity{
	
	
	Utilities util = new Utilities();
	int userId, situationId;
	TimePicker tp;
	DatePicker dp;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_excuse_time);
		
		
		Intent i = getIntent();
		userId = i.getIntExtra("userId", -1);
		situationId= i.getIntExtra("situationId", -1);

		Button nowBtn = (Button)findViewById(R.id.nowBtn);
		Button todayBtn = (Button)findViewById(R.id.todayBtn);
		Button futureBtn = (Button)findViewById(R.id.futureBtn);

		tp = (TimePicker)findViewById(R.id.timePicker);
		dp = (DatePicker)findViewById(R.id.datePicker);
		
		tp.setVisibility(View.INVISIBLE);
		dp.setVisibility(View.INVISIBLE);

		
		nowBtn.setOnClickListener( new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				util.alertNI(GetTime.this);
				

			}

		});
		
		todayBtn.setOnClickListener( new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				//util.alertNI(GetTime.this);
				setTimePickerVis(tp, View.VISIBLE);
				setDatePickerVis(dp, View.INVISIBLE);
			}

		});
		
		futureBtn.setOnClickListener( new View.OnClickListener(){

			@Override
			public void onClick(View v) {
//				util.alertNI(GetTime.this);
				setDatePickerVis(dp, View.VISIBLE);
				setTimePickerVis(tp, View.INVISIBLE);
			}

		});

	}
	
	
	
	
	@SuppressLint("NewApi")
	public void setTimePickerVis(TimePicker t, int i){
		t.setVisibility(i);
	}

	public void setDatePickerVis(DatePicker t, int i){
		t.setVisibility(i);
	}





}


