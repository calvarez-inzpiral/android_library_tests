package com.inzpiral.consumer.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Calendar;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inzpiral.consumer.R;
import com.inzpiral.consumer.fragments.FragmentAdapter;
import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.utils.ConsumerDeserializer;
import com.inzpiral.consumer.utils.NetworkUtils;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class HomeActivity extends SherlockFragmentActivity {
	
	// Manejo de tabs
	private FragmentAdapter mAdapter;
	private ViewPager mPager;
	private PageIndicator mIndicator;

	// Manejo de evaluacion
	private Evaluation mEvaluation;
	private String mURL = "http://10.0.1.13/test/consumo_masivo.json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.loadEvaluation();
		setContentView(R.layout.fragmenthome);
		
		this.loadTabs();
	}

	@Override
	public void onBackPressed(){
		moveTaskToBack(true);
	}

	private void loadTabs(){
		mAdapter = new FragmentAdapter(getSupportFragmentManager());

		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);		
	}
	
	public void loadEvaluation() {
//		mMainView.enableAll(false);
		
		InputStream source = NetworkUtils.retrieveStream(mURL);

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

//		mMainView.enableAll(true);
	}

	public Evaluation getEvaluation() {
		return mEvaluation;
	}
	
}

