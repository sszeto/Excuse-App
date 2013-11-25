package com.excuseMe;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.os.Bundle;

import com.excuseMe.R;
import com.excuseMe.account.Info;
import com.excuseMe.account.User;
import com.excuseMe.dbAccess.accountAccess;
import com.google.gson.Gson;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserLogin extends Activity {


	Utilities util = new Utilities();
	accountAccess acc = new accountAccess();
	Gson g = new Gson();
	SharedPreferences myPref;
	String username;
	int userId;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myPref = getSharedPreferences("ExcuseApp", Context.MODE_PRIVATE);

		if(myPref.getBoolean("loggedIn", false) == true){
			Intent i = new Intent(this, UserPanel.class);
			startActivity(i);
		}



		setContentView(R.layout.activity_main);

		Button loginButton = (Button)findViewById(R.id.loginButton); 
		Button createNewButton = (Button)findViewById(R.id.createNewButton);
		Button forgotButton = (Button)findViewById(R.id.forgotButton);



		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				realLoginCheck();
			}
		});


		createNewButton.setOnClickListener( new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// create Action
				Intent myIntent = new Intent(UserLogin.this, Register.class);
				startActivity(myIntent);
			}
		});



		forgotButton.setOnClickListener( new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Forgot Action
				util.alertNI(UserLogin.this);


			}
		});

	}




	private void realLoginCheck(){

		EditText usernameText = (EditText)findViewById(R.id.userName);
		EditText credText = (EditText)findViewById(R.id.password);

		username = usernameText.getText().toString();

		acc.checkLogin(username, credText.getText().toString(), new RestCallback() {

			@Override
			public void onTaskComplete(Object result) {

				HashMap<String, Object> a = g.fromJson((String)result, HashMap.class );


				if(((Double) a.get("success")).intValue() != 1 )
				{
					util.alert("Incorrect Username or Password", UserLogin.this);	
				}
				else
				{
					setUserId(Integer.parseInt((String)a.get("userId")));

					loginGo();
				}

			}}
				);

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




	public void loginGo(){


		acc.getUserProfile(userId, new RestCallback(){


			@Override
			public void onTaskComplete(Object result) {

				SharedPreferences.Editor editor = myPref.edit();

				editor.putString("infoJson", (String)result);
				editor.putString("username", username);
				editor.putBoolean("loggedIn", true);
				editor.putInt("userId", userId);
				editor.commit();

				Intent myIntent = new Intent(UserLogin.this, UserPanel.class);
				startActivity(myIntent);



			}
		});



	}




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}



}
