package com.excuseMe;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
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
	
	
	accountAccess a = new accountAccess();
	Utilities util = new Utilities();
	Gson g = new Gson();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		Button createButton = (Button) findViewById(R.id.createButton);

		createButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				realRegister();
			}
		});

	}
	
	
	private void realRegister(){
		
		EditText userReg = (EditText)findViewById(R.id.usernameReg);
		EditText emailReg = (EditText)findViewById(R.id.emailReg);
		EditText firstReg = (EditText)findViewById(R.id.firstNameReg);
		EditText lastReg = (EditText)findViewById(R.id.lastNameReg);
		EditText credReg = (EditText)findViewById(R.id.passwordReg);
		
	
		a.register(userReg.getText().toString(), emailReg.getText().toString(), 
				firstReg.getText().toString(), lastReg.getText().toString(),
				credReg.getText().toString(), new RestCallback() {

					@Override
					public void onTaskComplete(Object result) {
						Log.d("", result.toString());
						HashMap<String, Object> a = g.fromJson((String)result, HashMap.class );
						

						util.alert(a.get("message").toString(), Register.this);	
						
					}
			
		});
	
	}
		
	
	
	private void alert(String message) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage(message);
		alert.setTitle("ExcuseMe");
		alert.setPositiveButton("Ok", null);
		alert.setCancelable(true);
		alert.create().show();
	}

}
