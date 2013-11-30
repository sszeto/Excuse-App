package com.excuseMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecordUpdated extends Activity{

	String message;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_updated);


		Intent oldIntent = getIntent();

		TextView message = (TextView)findViewById(R.id.messageView);
		message.setText(oldIntent.getStringExtra("message"));

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
