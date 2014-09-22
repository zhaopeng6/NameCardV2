package com.example.namecardv1;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Parse.initialize(this, "eXr5eE3ff6vTMkTqsWe373eVZbuOLtafn7mFwlI2", "Ud799nz6KKXZNqWS6LBgCZ2QDiKx894SpVcskkfj");
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		if(currentUser != null){
			Intent intent = new Intent(getBaseContext(), Welcome.class);
			startActivity(intent);
			finish();
		} 
		else {
		
			Button buttonSignup = (Button) findViewById(R.id.buttonSignup);			
			buttonSignup.setOnClickListener(new OnClickListener(){
				@SuppressLint("NewApi") @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ParseUser user = new ParseUser();
					EditText name = (EditText) findViewById(R.id.username);
					EditText pass = (EditText) findViewById(R.id.password);
					if(!(name.getText().toString().isEmpty()) && !(pass.getText().toString().isEmpty())) {
						user.setUsername(name.getText().toString());						
						user.setPassword(pass.getText().toString());						
						user.signUpInBackground(new SignUpCallback(){							
							public void done(ParseException e){
								if( e== null){
									Intent intent = new Intent(getBaseContext(), Welcome.class);
									startActivity(intent);
									ParseUser user = ParseUser.getCurrentUser();
									ParseObject object = new ParseObject("ECardInfo");
									object.put("userID", user.getObjectId());
									object.saveInBackground();
									finish();
								} else {
									Toast.makeText(getBaseContext(), "Already exist!", Toast.LENGTH_SHORT).show();
								}
							}
						});
					} else {
						Toast.makeText(getBaseContext(), "Incomplete signup info!", Toast.LENGTH_SHORT).show();
					}
				}			
			});
			
			Button buttonLogin = (Button) findViewById(R.id.buttonLogin);			
			buttonLogin.setOnClickListener(new OnClickListener(){
				@SuppressLint("NewApi") @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ParseUser user = new ParseUser();
					EditText name = (EditText) findViewById(R.id.username);
					EditText pass = (EditText) findViewById(R.id.password);
					if(!(name.getText().toString().isEmpty()) && !(pass.getText().toString().isEmpty())) {
						ParseUser.logInInBackground(name.getText().toString(), pass.getText().toString(), new LogInCallback(){

							@Override
							public void done(ParseUser user, ParseException arg1) {
								// TODO Auto-generated method stub
								if (user != null) {
									Intent intent = new Intent(getBaseContext(), Welcome.class);
									startActivity(intent);
									finish();
								} 
								else {
									Toast.makeText(getBaseContext(), "Login info wrong!", Toast.LENGTH_SHORT).show();
								}
							}
							
						});
					} else {
						Toast.makeText(getBaseContext(), "Incomplete login info!", Toast.LENGTH_SHORT).show();
					}
				}			
			});
		}
		
		
	}
}
