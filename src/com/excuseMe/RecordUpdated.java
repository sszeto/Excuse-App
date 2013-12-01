package com.excuseMe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecordUpdated extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_updated);

		SharedPreferences pref = getSharedPreferences("ExcuseApp",0);
		Intent oldIntent = getIntent();
		
		TextView message = (TextView)findViewById(R.id.messageView);
		TextView thanks = (TextView)findViewById(R.id.thanksView);
		
		message.setText(oldIntent.getStringExtra("message"));
		thanks.setText("Thank you " + pref.getString("name", "") + "!");

		
		
		Button doneBtn = (Button)findViewById(R.id.recordUpdatedreturnBtn);



		doneBtn.setOnClickListener(

				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent myIntent = new Intent(RecordUpdated.this, UserPanel.class);
						startActivity(myIntent);

					}
				});




	}
}
