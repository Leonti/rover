package com.leonti.rover;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.leonti.rover.util.HttpUtil;
import com.leonti.rover.util.RequestTask;

public class ControlActivity extends Activity {

	private static String TAG = "ControlActivity";
	
	private static String IP_URL = "http://82.196.6.179:16000";
	
	private String rPiUrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		
	    getWindow().requestFeature(Window.FEATURE_ACTION_BAR);   //new
	    getActionBar().hide();                                   //new		
		
		setContentView(R.layout.activity_control);
		
		rPiUrl = "http://" + HttpUtil.get(IP_URL) + ":16000/";
		
		Log.i(TAG, "RPi ip is: " + rPiUrl);
	}

	
	public void forward(View view) {
		Log.i(TAG, "forward button click");
		new RequestTask().execute(rPiUrl + "forward");
	}	

	public void back(View view) {
		Log.i(TAG, "back button click");
		new RequestTask().execute(rPiUrl + "back");
	}		
	
	public void left(View view) {
		Log.i(TAG, "left button click");
		new RequestTask().execute(rPiUrl + "left");
	}
	
	public void right(View view) {
		Log.i(TAG, "right button click");
		new RequestTask().execute(rPiUrl + "right");
	}
	
	
	public void stop(View view) {
		Log.i(TAG, "stop button click");
		new RequestTask().execute(rPiUrl + "stop");
	}
	
}
