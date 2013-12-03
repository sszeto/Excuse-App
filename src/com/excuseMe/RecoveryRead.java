package com.excuseMe;

import com.excuseMe.dbAccess.AccountAccessDB;
import com.ppierson.webservicetasks.RestCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RecoveryRead extends Activity {
	
	
	
	EditText answer;
	TextView question, prompt;
	
	String username, answerQTxt, answerATxt;
	
	SharedPreferences pref;
	AccountAccessDB a;
	Utilities u;
	
	Button getQuestionBtn, recoverBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recovery_read);
		
		a= new AccountAccessDB();
		u = new Utilities();

		pref = getSharedPreferences("ExcuseApp",0);
		
		prompt = (TextView)findViewById(R.id.promptView);
		question = (TextView)findViewById(R.id.recoveryQuestionTxtView);
		answer = (EditText)findViewById(R.id.editTxtView);
		
		recoverBtn = (Button)findViewById(R.id.recoverButton);
		getQuestionBtn = (Button)findViewById(R.id.getQuestionButton);
		
		recoverBtn.setVisibility(View.INVISIBLE);
		
		
		getQuestionBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View w) {
				prepareQuestion();
				
			}
		});

		
		
		recoverBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View w) {
				realCheckQuestion();
					
			}
		});

	}
	
	
	
	
	private void prepareQuestion(){
		username = answer.getText().toString();
		
		a.getRecoveryQuestion(username, 
				new RestCallback(){

			
					@Override
					public void onTaskComplete(Object result) {
						
						if(((String)result).equals("-1")){
							u.alert("Username not found. Please try again", RecoveryRead.this);							
						}else{
							getQuestionBtn.setVisibility(View.INVISIBLE);
							recoverBtn.setVisibility(View.VISIBLE);
							
							question.setText((String)result);
							
							prompt.setText("Please answer your answer");
							answer.setText("");
				
						}
					
					}
		});
	}
	
	
	private void realCheckQuestion(){
		answerATxt = answer.getText().toString();
		
		a.checkRecoveryQuestion(username, answerATxt, 
				
				new RestCallback(){

					@Override
					public void onTaskComplete(Object result) {
						if(((String)result).equals("2")){
							u.alert("Incorrect answer. Please try again", RecoveryRead.this);
						}
						else{
							
							SharedPreferences.Editor edit = pref.edit();
							edit.putString("username", username);
							edit.commit();
							
							
							Intent myIntent = new Intent(RecoveryRead.this, PasswordUpdate.class);
							startActivity(myIntent);							
						}
						
					}
			
			
		});
		
		
		
	}
	
	

}
