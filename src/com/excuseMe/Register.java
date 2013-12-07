package com.excuseMe;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ppierson.*;
import com.ppierson.webservicetasks.GetWebserviceTask;
import com.ppierson.webservicetasks.JSONCallback;
import com.ppierson.webservicetasks.RestCallback;
import com.excuseMe.dbAccess.*;
import com.google.gson.Gson;
public class Register extends Activity {


	AccountAccessDB a = new AccountAccessDB();
	Utilities util = new Utilities();
	Gson g = new Gson();

	EditText userReg , emailReg, firstReg, lastReg, credReg;  // registration fields for user to input text 

	SharedPreferences myPref;

	// Activities to do on start of activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_create);  //sets layout to user-create

		myPref = getSharedPreferences("ExcuseApp",0);  //sets global shared preferences 


		Button createButton = (Button) findViewById(R.id.createButton); 

		// Create Button pressed 
		createButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				realRegister();
			}
		});

	}


	private void realRegister(){  //register the user

		userReg = (EditText)findViewById(R.id.usernameReg);
		emailReg = (EditText)findViewById(R.id.emailReg);
		firstReg = (EditText)findViewById(R.id.firstNameReg);
		lastReg = (EditText)findViewById(R.id.lastNameReg);
		credReg = (EditText)findViewById(R.id.passwordReg);

		//checks for valid username
		if(userReg.getText().toString().length() < 3){
			util.alert("Usernames must be at least 3 characters long!", this);
		}//checks for valid email
		else if(!isValidEmailAddress(emailReg.getText().toString())){
			util.alert("Please enter a valid email!", this);
		}//checks for nonempty first name
		else if(firstReg.getText().toString().length() < 1){
			util.alert("Please enter a first name!", this);
		}//checks for nonempty last name 
		else if(lastReg.getText().toString().length() < 1){
			util.alert("Please enter a last name!", this);
		}// checks for valid password length 
		else if(credReg.getText().toString().length() < 8){
			util.alert("Passwords must be at least 8 characters long!", this);
		}else{

			//all user field are correctly populated 

			a.register(userReg.getText().toString(), emailReg.getText().toString(), 
					firstReg.getText().toString(), lastReg.getText().toString(),
					credReg.getText().toString(), new RestCallback() {

				@Override
				public void onTaskComplete(Object result) {
					Log.d("", result.toString());
					
					//hashMap of server response ... from JSON using GSON  
					HashMap<String, Object> a = g.fromJson((String)result, HashMap.class );   //converts JSON result to hashmap
					
					if(((Double) a.get("success")).intValue() != 1 ){   //registration failed for some reason
						util.alert(a.get("message").toString(), Register.this);
					}else{ //registration successful

						String name = firstReg.getText().toString()+ " "+lastReg.getText().toString();
						String username = a.get("username").toString().toLowerCase();

						util.setGlobalId(username, myPref); // sets Id

						SharedPreferences.Editor editor = myPref.edit();
						editor.putString("username", username);  //set username
						editor.putString("name", name); //set name
						editor.putBoolean("loggedIn", true); //is currently logged in
						editor.commit();

						Intent i= new Intent(Register.this, RecoveryUpdate.class);	// set recovery questions						
						startActivity(i);
					}

				}

			});
		}

	}
	
	
	//checks for valid email pattern
	public static boolean isValidEmailAddress(String email) {
	    boolean stricterFilter = true; 
	    String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	    String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
	    String emailRegex = stricterFilter ? stricterFilterString : laxString;
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
	    java.util.regex.Matcher m = p.matcher(email);
	    return m.matches();
	}


}
