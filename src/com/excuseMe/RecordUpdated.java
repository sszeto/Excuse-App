package com.excuseMe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//generic class to inform user of "completed activity"
public class RecordUpdated extends Activity{

	
	// Activities to do on start of activity
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_updated_generic); //set layout to record_upated_generic 

		SharedPreferences pref = getSharedPreferences("ExcuseApp",0);
		Intent oldIntent = getIntent();
		
		TextView message = (TextView)findViewById(R.id.messageView);
		TextView thanks = (TextView)findViewById(R.id.thanksView);
		
		message.setText(oldIntent.getStringExtra("message"));    // grabs message from last screen -- required
		thanks.setText("Thank you " + pref.getString("name", "") + "!");    // sets message to thank user
		
		Button doneBtn = (Button)findViewById(R.id.recordUpdatedreturnBtn);

		doneBtn.setOnClickListener(    // returns user to user panel

				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent myIntent = new Intent(RecordUpdated.this, UserPanel.class);
						startActivity(myIntent);

					}
				});




	}
}
