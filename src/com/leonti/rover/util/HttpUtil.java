package com.leonti.rover.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpUtil {
	
	private static final HttpClient httpclient = new DefaultHttpClient();
	
	private static String TAG = "HttpUtil";
	
	public static String get(String url) {	    
	    HttpResponse response;
		try {
			response = httpclient.execute(new HttpGet(url));
			
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        return out.toString();
		    } else{
		        response.getEntity().getContent().close();
		        throw new IOException(statusLine.getReasonPhrase());
		    }							
		} catch (Throwable t) {
			Log.e(TAG, "Exception during get: " + t);
		}
		
		return null;
	} 
}
