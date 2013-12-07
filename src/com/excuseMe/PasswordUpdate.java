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
	
	EditText passTxt, passConfirmTxt; // field to input password and confirmation password
	TextView msg1, msg2, finishTxt; // message to display for the user
	Button resetBtn, resetRecovQuestions; // button to reset password and recovery questions 
	
	String username;   // username inputed by user 
	String pass, passConfirm; // password and confirmation password entered by the user 
	
	AccountAccessDB a;
	Utilities u;
	
	// Activities to do on start of activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password);
		
		a = new AccountAccessDB();
		u= new Utilities();
		pref = getSharedPreferences("ExcuseApp",0); // set gobal shared preferences 
		
		username = pref.getString("username", ""); //get username
		
		if(username.equals("")){   // if username doesn't exist log user out and create an alert dialog to explain situation 
			u.logout(pref);
			Intent myIntent = new Intent(PasswordUpdate.this, UserLoginMain.class);
			myIntent.putExtra("msg", "Sorry, something went wrong. Please Try Again!"); // message to pass to the user login screen 
			startActivity(myIntent);
		}
		
		
		finishTxt = (TextView)findViewById(R.id.finishedTxt); //text to be displayed when action is complete 
		finishTxt.setVisibility(View.INVISIBLE); // sets this text invisible to start 
		
		msg1 = (TextView)findViewById(R.id.msg1); // sets message displayed to the user
		msg2 = (TextView)findViewById(R.id.msg2); // sets second line of message to be displayed to the usre 
				
		passTxt = (EditText)findViewById(R.id.passwordNewTxt); // textfield for user to enter password
		passConfirmTxt= (EditText)findViewById(R.id.passwordNewConfirmTxt); // textfield for user to enter confirmation password 
		
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
							
							// sets user input field invisilble
							passTxt.setVisibility(View.INVISIBLE);    
							passConfirmTxt.setVisibility(View.INVISIBLE); 
							
							
							//sets initial message to user invisible 
							msg1.setVisibility(View.INVISIBLE);
							msg2.setVisibility(View.INVISIBLE);
							
							
							//sets end message to user visible 
							finishTxt.setVisibility(View.VISIBLE);
							
							//sets reset button to 'finish action'
							resetBtn.setText("Finish");

							// makes button for 'recover questions' available 
							resetRecovQuestions.setVisibility(View.VISIBLE);
							
							
							resetBtn.setOnClickListener(new View.OnClickListener() {   //"rewire" reset button to "finish"

								@Override
								public void onClick(View w) {
									u.logout(pref); // logs user out 
									
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
