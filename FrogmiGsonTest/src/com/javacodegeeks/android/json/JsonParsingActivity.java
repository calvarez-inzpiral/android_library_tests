package com.javacodegeeks.android.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.acid.frogmi.FrogmiAndroidActivity;
import com.google.gson.Gson;
import com.javacodegeeks.android.json.model.BaseNode;
import com.javacodegeeks.android.json.model.Evaluation;
import com.javacodegeeks.android.json.model.Node;
import com.javacodegeeks.android.json.model.Question;
import com.javacodegeeks.android.json.model.PresentationNode;

public class JsonParsingActivity extends Activity {
	
	
	String url = "http://www.frogmi.com/api/presentationAsJson?id=864";
	String filename = "archivojson.txt";
	private JsonParsingActivity self;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        self = this ;
        InputStream source = retrieveStream(url);
        
        Gson gson = new Gson();
        
        Reader reader = new InputStreamReader(source);
        
        Evaluation evaluation = gson.fromJson(reader, Evaluation.class);
        
        //persistencia en txt
       

        String s = gson.toJson(evaluation);

        FileOutputStream outputStream;

        try {
          outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
          outputStream.write(s.getBytes());
          outputStream.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        
        //carga los datos desde el txt
        readBack(this);
        
        
        Toast.makeText(this, evaluation.mName, Toast.LENGTH_SHORT).show();
        
        List<BaseNode> results = evaluation.mChildren;
        
        for (BaseNode result : results) {
        	Toast.makeText(this, result.mCode, Toast.LENGTH_SHORT).show();
		}
        
    }
    
    
    
    private void readBack(Context c){
    	
    	 FileInputStream fis = c.openFileInput("archivojson.txt");
    	 InputStreamReader isr = new InputStreamReader(fis);
    	 BufferedReader bufferedReader = new BufferedReader(isr);
    	 StringBuilder sb = new StringBuilder();
    	 String line;
    	 while ((line = bufferedReader.readLine()) != null) {
    	     sb.append(line);
    	 }

    	 String json = sb.toString();
    	 Gson gson = new Gson();
    	 Evaluation evaluation= gson.fromJson(json, Evaluation.class);
    	
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