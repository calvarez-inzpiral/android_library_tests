package com.javacodegeeks.android.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.Destroyable;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.javacodegeeks.android.json.model.BaseNode;
import com.javacodegeeks.android.json.model.Evaluation;
import com.javacodegeeks.android.json.model.Node;
import com.javacodegeeks.android.json.model.Question;
import com.javacodegeeks.android.json.model.PresentationNode;
import com.javacodegeeks.android.json.model.Repetition;
import com.javacodegeeks.android.json.utils.Utils;

public class JsonParsingActivity extends Activity {
	
	String url = "http://www.frogmi.com/api/presentationAsJson?id=864";
	String filename = "archivojson.txt";
	private JsonParsingActivity self;
	private Evaluation evaluation;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        self = this ;
        InputStream source = retrieveStream(url);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BaseNode.class, new MyDeserializer());
        
        final Gson gson = builder.create();
        //        Gson gson = new Gson();
        
        final Reader reader = new InputStreamReader(source);
        
        Thread t = new Thread(null, new Runnable() {
            @Override
            public void run() {
				Calendar cal = Calendar.getInstance();
				long init = cal.getTimeInMillis();
				
            	evaluation = gson.fromJson(reader, Evaluation.class);
            	
				cal = Calendar.getInstance();
				System.out.println("Parsing time: " + (cal.getTimeInMillis() - init));
				init = cal.getTimeInMillis();
            	
//		        System.out.println(evaluation.mName);
//		        
//		        List<? extends BaseNode> results = ((PresentationNode)evaluation.mChildren.get(0)).mChildren;
//		        
//		        for (BaseNode result : results) {
//		        	System.out.println(result.mCode);
//		        }
            }
        }, "parsing", 1024 * 1024);
        t.start();
        
        try {
			t.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

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
    }
    
    class MyDeserializer implements JsonDeserializer<BaseNode> {

		@Override
		public BaseNode deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			
			String node = json.getAsJsonObject().get("node").getAsString();
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(BaseNode.class, new MyDeserializer());
			Gson gson = builder.create();
			
			return gson.fromJson(json, Utils.getTypeByTag(node));
		}
		
	}
    
    private void readBack(Context c){
    	FileInputStream fis = null;
		try {
			fis = c.openFileInput("archivojson.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	InputStreamReader isr = new InputStreamReader(fis);
    	BufferedReader bufferedReader = new BufferedReader(isr);
    	StringBuilder sb = new StringBuilder();
    	String line;
    	try {
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

    	String json = sb.toString();
    	Gson gson = new Gson();
    	
    	Evaluation evaluation = gson.fromJson(json, Evaluation.class);
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