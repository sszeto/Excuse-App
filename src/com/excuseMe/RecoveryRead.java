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

	EditText answer; //textField for user to input answer 
	TextView question, prompt; //messages to the user for a question and a prompt 

	String username, answerQTxt, answerATxt; //username and question and answer text from the user 

	SharedPreferences pref;
	AccountAccessDB a;
	Utilities u;

	Button getQuestionBtn, recoverBtn; // buttons to getQuestion and recovery password respectively 

	// Activities to do on start of activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recovery_read);  // layout set to recover_r ead 

		a= new AccountAccessDB();
		u = new Utilities();

		pref = getSharedPreferences("ExcuseApp",0);  //sets global shared preference 

		prompt = (TextView)findViewById(R.id.promptView);
		question = (TextView)findViewById(R.id.recoveryQuestionTxtView);
		answer = (EditText)findViewById(R.id.editTxtView);

		recoverBtn = (Button)findViewById(R.id.recoverButton);
		getQuestionBtn = (Button)findViewById(R.id.getQuestionButton);

		recoverBtn.setVisibility(View.INVISIBLE);  // only visible after prepareQuestion() check passes

		//user requests to get recovery question 
		getQuestionBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View w) {
				prepareQuestion();

			}
		});



		recoverBtn.setOnClickListener(new View.OnClickListener() {   //button to reset password

			@Override
			public void onClick(View w) {
				realCheckQuestion();

			}
		});

	}




	private void prepareQuestion(){      // Checks for valid recovery questions
		username = answer.getText().toString();

		//checks for a non empty username field 
		if(username.length() < 1){
			u.alert("Please type in a username", this); // alerts the user to input a valid username
		}else{

			//if it is a non-empty username field, do getRecoveryqustion from accountAccess class
			a.getRecoveryQuestion(username, 
					new RestCallback(){


				@Override
				public void onTaskComplete(Object result) {

					//checks for fail/ success codes from server response
					if(((String)result).equals("-1")){    //-1 = username not found 
						u.alert("Username not found. Please try again", RecoveryRead.this);							
					}else{  // all othere results represent a valid question 
						getQuestionBtn.setVisibility(View.INVISIBLE);   //"getuestion' button is set invisible 
						recoverBtn.setVisibility(View.VISIBLE); //"recover password" button set to visible 

						question.setText((String)result); // question texfield is now set to the correct question 

						prompt.setText("Please enter your answer"); // user is prompted to enter a n answer
						answer.setText("");  // answer input file is cleared 

					}

				}
			});
		}
	}


	private void realCheckQuestion(){    //Checks answer with db
		answerATxt = answer.getText().toString();

		//makes sure there is an answer in the user input box 
		if(answerATxt.length() < 1){
			u.alert("Please type in an answer!", this);
		}else{

			//calls chechRecoveryuestion from accountAccess class to check the answer 
			a.checkRecoveryQuestion(username, answerATxt, 

					new RestCallback(){

				@Override
				public void onTaskComplete(Object result) {
					//checks response codes sent back from the sever
					if(((String)result).equals("2")){ // -1 = incorrect answer
						u.alert("Incorrect answer. Please try again", RecoveryRead.this);
					}
					//all other responses = answer is correct
					else{
						
						SharedPreferences.Editor edit = pref.edit();
						edit.putString("username", username);    //sets username to global shared preferences 
						edit.commit();

						//goes to passwordUpdate screen
						Intent myIntent = new Intent(RecoveryRead.this, PasswordUpdate.class);
						startActivity(myIntent);							
					}

				}


			});

		}

	}



}
