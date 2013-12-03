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
		fromPassScreen = oldIntent.getBooleanExtra("fromPass", false);   // did we come from Password Reset Screen?

		recoveryTxt = (EditText)findViewById(R.id.recoveryTxt);
		recoveryAns= (EditText)findViewById(R.id.recoveryAnswerTxt);

		Button done = (Button)findViewById(R.id.recoveryBtn);



		done.setOnClickListener(new View.OnClickListener() {   //sets recovery questions

			@Override
			public void onClick(View v) {
				realSetRecovery();
			}
		});

	}



	private void realSetRecovery(){

		String username = myPref.getString("username", "");
		
		if(username.equals("")){
			u.logout(myPref);
			Intent myIntent = new Intent(RecoveryUpdate.this, UserLoginMain.class);
			myIntent.putExtra("msg", "Sorry, something went wrong. Please Try Again!");
			startActivity(myIntent);
		}
				
		recoveryQ = recoveryTxt.getText().toString();
		recoveryA = recoveryAns.getText().toString();

		if(recoveryQ.length() < 5){  //check for lengths
			u.alert("You forgot to enter a question!", this);
		}else if(recoveryA.length() < 1){
			u.alert("You forgot to enter an answer!", this);
		}else{ //passes length test
			a.updateRecovery(username, recoveryQ, recoveryA,
					new RestCallback(){

						@Override
						public void onTaskComplete(Object result) {
							Intent i;
							if(isFromPassScreen() == false){
								i = new Intent(RecoveryUpdate.this, ProfileCreate.class);
								startActivity(i);
							}else{  // we came from the password reset screen
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
