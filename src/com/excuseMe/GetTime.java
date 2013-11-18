package com.excuseMe;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class GetTime extends Activity{
	
	
	Utilities util = new Utilities();
	TimePicker tp;
	DatePicker dp;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_excuse_time);


		Button nowBtn = (Button)findViewById(R.id.nowBtn);
		Button todayBtn = (Button)findViewById(R.id.todayBtn);
		Button futureBtn = (Button)findViewById(R.id.futureBtn);

		tp = (TimePicker)findViewById(R.id.timePicker);
		dp = (DatePicker)findViewById(R.id.datePicker);
		
		tp.setVisibility(0);
		dp.setVisibility(0);

		
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
				setTimePickerVis(tp, 1);

			}

		});
		
		futureBtn.setOnClickListener( new View.OnClickListener(){

			@Override
			public void onClick(View v) {
//				util.alertNI(GetTime.this);
				setDatePickerVis(dp, 1);
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


