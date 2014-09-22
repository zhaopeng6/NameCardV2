package com.example.namecardv1;

import java.util.Arrays;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.namecardv1.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Add extends SwipeBackActivity implements OnClickListener {

	private static final int VIBRATE_DURATION = 20;
	private SwipeBackLayout mSwipeBackLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
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
		
		Button buttonFakeScan = (Button) findViewById(R.id.buttonFakeScan);
		buttonFakeScan.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText name = (EditText) findViewById(R.id.qrcodeID);		
				
				ParseQuery<ParseObject> query = ParseQuery.getQuery("ECardInfo");	
				query.whereEqualTo("userID", name.getText().toString());
				query.findInBackground(new FindCallback<ParseObject>() {
				  
					@Override
					public void done(List<ParseObject> objects, ParseException e) {
						// TODO Auto-generated method stub
						
						if (objects.size() !=0) {
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
						} 
						else {
						      // something went wrong
							Toast.makeText(getBaseContext(), "User not found!", Toast.LENGTH_SHORT).show();
						}
					}			
				});			
			}			
		});
		
		Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
		buttonAdd.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// find if the scanned user actually exists
				ParseQuery<ParseObject> query = ParseQuery.getQuery("ECardInfo");	
				EditText name = (EditText) findViewById(R.id.qrcodeID);	
				query.whereEqualTo("userID", name.getText().toString());
				query.findInBackground(new FindCallback<ParseObject>() {				  

					@Override
					public void done(List<ParseObject> objects, ParseException e) {
						if (objects.size()  == 0) {
							// If user non-exist
							Toast.makeText(getBaseContext(), "No such user!", Toast.LENGTH_SHORT).show();
						} else {
							// User does exist. Now checks if the ecard is already collected
							ParseUser user = ParseUser.getCurrentUser();							
							ParseQuery<ParseObject> query = ParseQuery.getQuery("ECardInfo");	
							query.whereEqualTo("userID", user.getObjectId()); // Locate current user							
							EditText name = (EditText) findViewById(R.id.qrcodeID);	
							String[] names = {name.getText().toString()};
							query.whereNotContainedIn("collectedIDs", Arrays.asList(names)); // ecard not collected
							query.findInBackground(new FindCallback<ParseObject>() {

								@Override
								public void done(List<ParseObject> objects,
										ParseException e) {
									// TODO Auto-generated method stub
									if(objects.size() == 0){
										// ecard exists!
										Toast.makeText(getBaseContext(), "Ecard exists!", Toast.LENGTH_SHORT).show();
									} else {
										// Located current user, and the ecard is not contained in collectedIDs
										ParseObject object = objects.get(0);
										EditText name = (EditText) findViewById(R.id.qrcodeID);	
										object.add("collectedIDs", name.getText().toString());											
										object.saveInBackground();	
										Toast.makeText(getBaseContext(), "ECard added!", Toast.LENGTH_SHORT).show();
									}
								}
								
							});
						}
					}

					
					
				});			
			}		
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
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
