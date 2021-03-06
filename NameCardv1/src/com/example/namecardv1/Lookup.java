package com.example.namecardv1;

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

public class Lookup extends SwipeBackActivity implements OnClickListener {

	private static final int VIBRATE_DURATION = 20;
	private SwipeBackLayout mSwipeBackLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lookup);
		
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lookup, menu);
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
