package com.leonti.rover;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;

import com.leonti.rover.util.HttpUtil;
import com.leonti.rover.util.RequestTask;

public class ControlActivity extends Activity {

	private static String TAG = "ControlActivity";

	private static String IP_URL = "http://82.196.6.179:16000";

	private String rPiUrl;

	private interface Action {
		String FORWARD = "forward";
		String BACK = "back";
		String LEFT = "left";
		String RIGHT = "right";
		String STOP = "stop";
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR); // new
		getActionBar().hide(); // new

		setContentView(R.layout.activity_control);

		rPiUrl = "http://" + HttpUtil.get(IP_URL) + ":16000/";

		Log.i(TAG, "RPi ip is: " + rPiUrl);

		Button buttonForward = (Button) findViewById(R.id.button_forward);
		buttonForward.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					sendAction(Action.FORWARD);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					sendAction(Action.STOP);
				}

				return true;
			}
		});

		Button buttonBack = (Button) findViewById(R.id.button_back);
		buttonBack.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					sendAction(Action.BACK);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					sendAction(Action.STOP);
				}

				return true;
			}
		});

		Button buttonLeft = (Button) findViewById(R.id.button_left);
		buttonLeft.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					sendAction(Action.LEFT);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					sendAction(Action.STOP);
				}

				return true;
			}
		});

		Button buttonRight = (Button) findViewById(R.id.button_right);
		buttonRight.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					sendAction(Action.RIGHT);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					sendAction(Action.STOP);
				}

				return true;
			}
		});

	}

	private void sendAction(String action) {
		new RequestTask().execute(rPiUrl + action);
	}
}
