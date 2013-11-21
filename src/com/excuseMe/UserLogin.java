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




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
				Intent myIntent = new Intent(UserLogin.this, ProfileCreate.class);
				startActivity(myIntent);
				

			}
		});

	}





	

	private void realLoginCheck(){

		EditText usernameText = (EditText)findViewById(R.id.userName);
		EditText credText = (EditText)findViewById(R.id.password);

		 acc.checkLogin(usernameText.getText().toString(), credText.getText().toString(), new RestCallback() {

			@Override
			public void onTaskComplete(Object result) {

				HashMap<String, Object> a = g.fromJson((String)result, HashMap.class );


				if(((Double) a.get("success")).intValue() != 1 )
				{
					util.alert("Incorrect Username or Password", UserLogin.this);	
				}
				else
				{
					
					Intent myIntent = new Intent(UserLogin.this, UserPanel.class);
					myIntent.putExtra("userId", Integer.parseInt((String)a.get("userId")));
					startActivity(myIntent);
					
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



}
