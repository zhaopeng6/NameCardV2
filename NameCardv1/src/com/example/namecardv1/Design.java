

package com.example.namecardv1;

import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

public class Design extends SwipeBackActivity implements OnClickListener {

	private static final int VIBRATE_DURATION = 20;
	private SwipeBackLayout mSwipeBackLayout;
    private static final int SELECT_PHOTO = 100;
    private static int RESULT_LOAD_IMAGE = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_design);
		
        Button pb = (Button) findViewById(R.id.SearchPhoneButton);
        pb.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                        Toast.makeText(Design.this, "Select Image",
                                        Toast.LENGTH_LONG).show();
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                }
        });
		
		
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
		
		Button buttonSend = (Button) findViewById(R.id.buttonSend);
		buttonSend.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
							object.put("firstName", name.getText().toString());
							name = (EditText) findViewById(R.id.LastName);
							object.put("lastName", name.getText().toString());
							name = (EditText) findViewById(R.id.Company);
							object.put("company", name.getText().toString());
							name = (EditText) findViewById(R.id.Title);
							object.put("title", name.getText().toString());
							name = (EditText) findViewById(R.id.tel);
							object.put("tel", name.getText().toString());
							name = (EditText) findViewById(R.id.email);
							object.put("email", name.getText().toString());
							name = (EditText) findViewById(R.id.link);
							object.put("link", name.getText().toString());
											
							object.saveInBackground();	
						} 
						else {
						      // something went wrong
						}
					}			
				});		
				
					
			}	
		});
		
		Button buttonRecv = (Button) findViewById(R.id.buttonRecv);
		buttonRecv.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
//							testObject.put("street", "46 Pilgrim St");
//							testObject.put("city", "Cambridge");
//							testObject.put("state", "MA");
//							testObject.put("zipcode", "02139");
							name = (EditText) findViewById(R.id.tel);
							name.setText(object.getString("tel"));
//							testObject.put("fax", "74837593");
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picturePath,options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            String imageType = options.outMimeType;
            
            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageBitmap(
            	    decodeSampledBitmapFromFile(picturePath, R.id.imageView1, 200, 200));
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
         
        }
     
    }
    public static Bitmap decodeSampledBitmapFromFile(String picturePath, int resId,
            int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(picturePath, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
  }

}
