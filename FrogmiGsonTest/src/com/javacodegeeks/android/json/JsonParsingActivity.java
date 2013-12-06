package com.javacodegeeks.android.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.javacodegeeks.android.json.model.Node;
import com.javacodegeeks.android.json.model.Question;
import com.javacodegeeks.android.json.model.PresentationNode;

public class JsonParsingActivity extends Activity {
	
	String url = "http://www.frogmi.com/api/presentationAsJson?id=864";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        InputStream source = retrieveStream(url);
        
        Gson gson = new Gson();
        
        Reader reader = new InputStreamReader(source);
        
        PresentationNode evaluation = gson.fromJson(reader, PresentationNode.class);
        
        Toast.makeText(this, evaluation.mName, Toast.LENGTH_SHORT).show();
        
        List<Node> results = evaluation.mChildren;
        
        for (Node result : results) {
        	Toast.makeText(this, result.mCode, Toast.LENGTH_SHORT).show();
		}
        
    }
    
    private InputStream retrieveStream(String url) {
    	
    	DefaultHttpClient client = new DefaultHttpClient(); 
        
        HttpGet getRequest = new HttpGet(url);
          
        try {
           
           HttpResponse getResponse = client.execute(getRequest);
           final int statusCode = getResponse.getStatusLine().getStatusCode();
           
           if (statusCode != HttpStatus.SC_OK) { 
              Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url); 
              return null;
           }

           HttpEntity getResponseEntity = getResponse.getEntity();
           return getResponseEntity.getContent();
           
        } 
        catch (IOException e) {
           getRequest.abort();
           Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }
        
        return null;
        
     }
    
}