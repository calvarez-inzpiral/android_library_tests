package com.inzpiral.consumer.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.StrictMode;


public class NetworkUtils {

	public static InputStream retrieveStream(String url) {
		StrictMode.ThreadPolicy policy = 
		        new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		DefaultHttpClient client = new DefaultHttpClient(); 
		HttpGet getRequest = new HttpGet(url);

		try {
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) { 
				System.out.println("Error " + statusCode + " for URL " + url); 
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			
			return getResponseEntity.getContent();
		} 
		catch (IOException e) {
			getRequest.abort();
			System.out.println("Error for URL " + url);
			e.printStackTrace();
		}

		return null;

	}

}
