package com.inzpiral.consumer.controllers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inzpiral.consumer.controllers.SpinnerController.SpinnerControllerListener;
import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.utils.ConsumerDeserializer;
import com.inzpiral.consumer.utils.NetworkUtils;
import com.inzpiral.consumer.views.MainView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class MainController {
	
	private MainView mMainView;

	private String mURL = "http://10.0.1.13/test/consumo_masivo.json";
	private Evaluation mEvaluation;
	private MainControllerListener mListener;

	public MainController(MainView loginView, MainControllerListener listener) {
		this.mMainView = loginView;
		this.mListener = listener;
	}

	public Evaluation parseConsumer() {
		return parseConsumer(mURL);
	}
	public Evaluation parseConsumer(String url) {
		mMainView.enableAll(false);
		
		InputStream source = mListener.retrieveStream(url);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BaseNode.class, new ConsumerDeserializer());
        
        final Gson gson = builder.create();
        
        final Reader reader = new InputStreamReader(source);
        
        Thread t = new Thread(null, new Runnable() {
            @Override
            public void run() {
				Calendar cal = Calendar.getInstance();
				long init = cal.getTimeInMillis();
				
            	mEvaluation = gson.fromJson(reader, Evaluation.class);
            	
				cal = Calendar.getInstance();
				System.out.println("Parsing time: " + (cal.getTimeInMillis() - init));
				init = cal.getTimeInMillis();
            }
        }, "parsing", 1024 * 1024);
        t.start();
        
        try {
			t.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		mMainView.enableAll(true);
        return mEvaluation;
	}

	// Interfaces
	public interface MainControllerListener {
		public InputStream retrieveStream(String url);
	}

}
