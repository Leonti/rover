package com.leonti.rover.util;

import android.os.AsyncTask;

public class RequestTask extends AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... args) {
        return HttpUtil.get(args[0]);
	}

}
