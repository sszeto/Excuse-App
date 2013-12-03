package com.excuseMe;

import com.excuseMe.dbAccess.AccountAccessDB;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordUpdate extends Activity {
	
	SharedPreferences pref;
	
	EditText passTxt, passConfirmTxt;
	TextView msg1, msg2, finishTxt;
	Button resetBtn, resetRecovQuestions;
	
	String username;
	String pass, passConfirm;
	
	AccountAccessDB a;
	Utilities u;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password);
		
		a = new AccountAccessDB();
		u= new Utilities();
		pref = getSharedPreferences("ExcuseApp",0);
		
		username = pref.getString("username", ""); //get username
		
		if(username.equals("")){
			u.logout(pref);
			Intent myIntent = new Intent(PasswordUpdate.this, UserLoginMain.class);
			myIntent.putExtra("msg", "Sorry, something went wrong. Please Try Again!");
			startActivity(myIntent);
		}
		
		
		finishTxt = (TextView)findViewById(R.id.finishedTxt);
		finishTxt.setVisibility(View.INVISIBLE);
		
		msg1 = (TextView)findViewById(R.id.msg1);
		msg2 = (TextView)findViewById(R.id.msg2);
				
		passTxt = (EditText)findViewById(R.id.passwordNewTxt);
		passConfirmTxt= (EditText)findViewById(R.id.passwordNewConfirmTxt);
		
		resetBtn = (Button)findViewById(R.id.resetPassBtn);
		resetRecovQuestions = (Button)findViewById(R.id.resetRecovBtn);
		
		resetRecovQuestions.setVisibility(View.INVISIBLE);   //button used for end action
				
		

		resetBtn.setOnClickListener(new View.OnClickListener() {  //user pressed reset

			@Override
			public void onClick(View w) {
				resetPssword();
			}
		});
		
		resetRecovQuestions.setOnClickListener(new View.OnClickListener() {  //button used for end action -- passes intent to set recovery

			@Override
			public void onClick(View w) {
				Intent i = new Intent(PasswordUpdate.this, RecoveryUpdate.class);
				i.putExtra("fromPass", true); //let recovery screen know it came from here
				startActivity(i);
			}
		});
		
		
	}
	
	
	public void resetPssword(){  // reset password
		
		pass = passTxt.getText().toString();
		passConfirm = passConfirmTxt.getText().toString();
		
		if(pass.equals(passConfirm)){   //passwords match?
			username = pref.getString("username", "");
			a.updatePassword(username, pass, 
					
					new RestCallback(){
						@Override
						public void onTaskComplete(Object result) {    //performing end actions
							
							passTxt.setVisibility(View.INVISIBLE);
							passConfirmTxt.setVisibility(View.INVISIBLE);
							
							msg1.setVisibility(View.INVISIBLE);
							msg2.setVisibility(View.INVISIBLE);
							
							finishTxt.setVisibility(View.VISIBLE);
							
							resetBtn.setText("Finish");

							resetRecovQuestions.setVisibility(View.VISIBLE);
							
							
							resetBtn.setOnClickListener(new View.OnClickListener() {   //"rewire" reset button to "finish"

								@Override
								public void onClick(View w) {
									u.logout(pref);
									
									Intent i = new Intent(PasswordUpdate.this, UserLoginMain.class);
									startActivity(i);
								}
							});
						}});
		}else{  // passwords didn't match
			u.alert("Passwords do not match", PasswordUpdate.this);
		}
		
		
		
		
	}
	
	


}
