package com.example.namecardv1;

import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Me extends SwipeBackActivity implements OnClickListener {

	private static final int VIBRATE_DURATION = 20;
	private SwipeBackLayout mSwipeBackLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me);
		
		mSwipeBackLayout = getSwipeBackLayout();
		mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
		mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
			@Override
			public void onScrollStateChange(int state, float scrollPercent) {
			}
			@Override
			public void onEdgeTouch(int edgeFlag) {
				vibrate(VIBRATE_DURATION);
			}
			@Override
			public void onScrollOverThreshold() {
				vibrate(VIBRATE_DURATION);
			}
		});
		
		Button buttonDesign = (Button) findViewById(R.id.buttonDesign);
		buttonDesign.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), Design.class);
				startActivity(intent);
			}	
		});
		
		Button buttonRefresh = (Button) findViewById(R.id.buttonRefresh);
		buttonRefresh.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				startActivity(getIntent());
			}	
		});
		
		
		ParseUser user = ParseUser.getCurrentUser();
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ECardInfo");	
		query.whereEqualTo("userID", user.getObjectId());
		query.findInBackground(new FindCallback<ParseObject>() {
		  
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				
				if (e == null) {
				      // object will be your game score
					ParseObject object = objects.get(0);
			    	EditText name = (EditText) findViewById(R.id.FirstName);
					name.setText(object.getString("firstName"));
					name = (EditText) findViewById(R.id.LastName);
					name.setText(object.getString("lastName"));
					name = (EditText) findViewById(R.id.Company);
					name.setText(object.getString("company"));
					name = (EditText) findViewById(R.id.Title);
					name.setText(object.getString("title"));
//					testObject.put("street", "46 Pilgrim St");
//					testObject.put("city", "Cambridge");
//					testObject.put("state", "MA");
//					testObject.put("zipcode", "02139");
					name = (EditText) findViewById(R.id.tel);
					name.setText(object.getString("tel"));
//					testObject.put("fax", "74837593");
					name = (EditText) findViewById(R.id.email);
					name.setText(object.getString("email"));
					name = (EditText) findViewById(R.id.link);
					name.setText(object.getString("link"));
				} 
				else {
				      // something went wrong
				}
			}			
		});	
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.me, menu);
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
	
	private void restoreTrackingMode() {
		mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
	}

	private void vibrate(long duration) {
		Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 0, duration };
		vibrator.vibrate(pattern, -1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
