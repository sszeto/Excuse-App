package com.excuseMe;

import com.excuseMe.dbAccess.AccountAccessDB;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecoveryUpdate extends Activity {


	SharedPreferences myPref;
	AccountAccessDB a;
	Utilities u;

	String recoveryQ, recoveryA;

	boolean fromPassScreen;
	
	EditText recoveryTxt, recoveryAns;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recovery_create);

		a = new AccountAccessDB();
		u = new Utilities();
		myPref = getSharedPreferences("ExcuseApp",0);
				
		Intent oldIntent = getIntent();
		fromPassScreen = oldIntent.getBooleanExtra("fromPass", false);

		recoveryTxt = (EditText)findViewById(R.id.recoveryTxt);
		recoveryAns= (EditText)findViewById(R.id.recoveryAnswerTxt);

		Button done = (Button)findViewById(R.id.recoveryBtn);



		done.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				realSetRecovery();
			}
		});

	}



	private void realSetRecovery(){

		recoveryQ = recoveryTxt.getText().toString();
		recoveryA = recoveryAns.getText().toString();


		if(recoveryQ.length() < 5){
			u.alert("You forgot to enter a question!", this);
		}else if(recoveryA.length() < 1){
			u.alert("You forgot to answer an answer!", this);
		}else{
			a.updateRecovery(myPref.getString("username", ""), recoveryQ, recoveryA,
					new RestCallback(){

				@Override
				public void onTaskComplete(Object result) {
					Intent i;
					if(isFromPassScreen() == false){
						i = new Intent(RecoveryUpdate.this, ProfileCreate.class);
						startActivity(i);
					}else{
						i = new Intent(RecoveryUpdate.this, UserLoginMain.class);
						i.putExtra("msg", "Recovery Questions Updated!");
						startActivity(i);
					}
					
				}
			});
		}
	}



	private boolean isFromPassScreen() {
		return fromPassScreen;
	}



	private void setFromPassScreen(boolean fromPassScreen) {
		this.fromPassScreen = fromPassScreen;
	}





}
