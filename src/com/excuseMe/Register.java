package com.excuseMe;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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
	
	EditText userReg , emailReg, firstReg, lastReg, credReg;
	
	
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
		
		userReg = (EditText)findViewById(R.id.usernameReg);
		emailReg = (EditText)findViewById(R.id.emailReg);
		firstReg = (EditText)findViewById(R.id.firstNameReg);
		lastReg = (EditText)findViewById(R.id.lastNameReg);
		credReg = (EditText)findViewById(R.id.passwordReg);
		
		
		
	
		a.register(userReg.getText().toString(), emailReg.getText().toString(), 
				firstReg.getText().toString(), lastReg.getText().toString(),
				credReg.getText().toString(), new RestCallback() {

					@Override
					public void onTaskComplete(Object result) {
						Log.d("", result.toString());
						HashMap<String, Object> a = g.fromJson((String)result, HashMap.class );
						
						if(((Double) a.get("success")).intValue() != 1 ){
							util.alert(a.get("message").toString(), Register.this);
						}else{
							String name = firstReg.getText().toString()+ " "+lastReg.getText().toString();
							
							Intent i= new Intent(Register.this, ProfileCreate.class);
							i.putExtra("username", a.get("username").toString() );
							i.putExtra("name", name); 
							startActivity(i);
						}
						
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
