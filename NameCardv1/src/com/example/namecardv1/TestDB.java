package com.example.namecardv1;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestDB extends SwipeBackActivity implements OnClickListener {
	
	private static final int VIBRATE_DURATION = 20;
	private SwipeBackLayout mSwipeBackLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testdb);
		
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
		
		ParseUser user = ParseUser.getCurrentUser();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ECardInfo");	
		query.whereEqualTo("userID", user.getObjectId()); // Locate current user	
		// should have done this by searching through the Parse.User class. Cannot fix it now
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				if(objects.size() != 0) { // current user record located
					int[] ids = {R.id.collected1, R.id.collected2, R.id.collected3, R.id.collected4};
					int i = 0;
					ParseObject object = objects.get(0);
					List<String> cIDs = new ArrayList<String>();
					cIDs = (ArrayList<String>) object.get("collectedIDs");
					for(String s : cIDs) {
						EditText name = (EditText) findViewById(ids[i]);	
						name.setText(s);
						i=i+1;
					}
				} else {
					// something wrong as current user not located
				}
			}
			
		});
		
		
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
