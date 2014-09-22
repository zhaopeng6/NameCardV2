package com.example.namecardv1;


import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Welcome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void Add(View view) {
		Intent intent = new Intent(this, Add.class);
		startActivity(intent);
	}
	public void Lookup(View view) {
		Intent intent = new Intent(this, Lookup.class);
		startActivity(intent);
	}
	public void Me(View view) {
		Intent intent = new Intent(this, Me.class);
		startActivity(intent);
	}
	public void Explore(View view) {
		Intent intent = new Intent(this, Explore.class);
		startActivity(intent);
	}
	public void TestDB(View view) {
		Intent intent = new Intent(this, TestDB.class);
		startActivity(intent);
	}
	
	public void Logout(View view) {
		ParseUser.logOut();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
