package com.inzpiral.consumer.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Calendar;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inzpiral.consumer.R;
import com.inzpiral.consumer.fragments.FragmentAdapter;
import com.inzpiral.consumer.fragments.LocationSlideMenu;
import com.inzpiral.consumer.fragments.SpinnerFragment;
import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.utils.ConsumerDeserializer;
import com.inzpiral.consumer.utils.EvaluationHelper;
import com.inzpiral.consumer.utils.NetworkUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.PageIndicator;

public class HomeActivity extends SlidingFragmentActivity {

	// Manejo de tabs
	private FragmentAdapter mAdapter;
	private ViewPager mPager;
	private PageIndicator mIndicator;
	protected Fragment mFrag;

	// Manejo de evaluacion
	private EvaluationHelper mHelper;
	private String mURL = "http://www.frogmi.com/consumo_masivo.json";
	//	private String mURL = "http://192.168.1.153/test/consumo_masivo.json";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.loadEvaluation();
		setContentView(R.layout.fragment_home);
		this.loadSlideBar(savedInstanceState);
		this.loadSpinners();
	}

	private void loadSpinners() {
		getSupportFragmentManager().beginTransaction().add(R.id.spinners_frame, new SpinnerFragment(), "SpinnerFragment").commit();
	}

	private void loadSlideBar(Bundle savedInstanceState) {
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new LocationSlideMenu();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		//	sm.setShadowWidthRes(R.dimen.shadow_width);
		//	sm.setShadowDrawable(R.drawable.shadow);
		//	sm.setFadeDegree(0.35f);
		sm.destroyDrawingCache();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

	public void loadTabs(final int tabNumber){
		runOnUiThread(new Runnable() {

			@Override
			public void run() {

				mAdapter = new FragmentAdapter(getSupportFragmentManager(), tabNumber);
				mPager = (ViewPager)findViewById(R.id.pager);
				mPager.setAdapter(mAdapter);
				mPager.setOffscreenPageLimit(3);
				mPager.getAdapter().notifyDataSetChanged();
				mIndicator = (IconPageIndicator)findViewById(R.id.indicator);
				mIndicator.setViewPager(mPager);
				mIndicator.notifyDataSetChanged();

			}
		});

	}

	public void loadEvaluation() {
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

				Evaluation evaluation = gson.fromJson(reader, Evaluation.class);
				mHelper = EvaluationHelper.getInstance(evaluation);

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
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed(){
		moveTaskToBack(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				toggle();
			}
		}, 1000);
	}

	public void loadCategories(long id, int position) {
		System.out.println("id:" + id + ", position:" + position);

		mHelper = EvaluationHelper.getInstance();
		Node selectedLocation = mHelper.getLocations().get(position);

		if(mHelper.getCurrentLocation() == selectedLocation) {
			closeSlidingmenu();
			return;
		}

		mHelper.setCurrentLocation(selectedLocation);

		Fragment newFragment = new SpinnerFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		transaction.replace(R.id.spinners_frame, newFragment);
		transaction.addToBackStack(null);
		transaction.commit();

		closeSlidingmenu();
	}

	private void closeSlidingmenu() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);
	}


	public static void saveOnSD(){


		Thread t = new Thread(null, new Runnable() {
			@Override
			public void run() {

				//				Node v = (Node) EvaluationHelper.getInstance().getRoot();
				Evaluation v = EvaluationHelper.getInstance().getEvaluations();
				Gson gson = new Gson();

				String s = gson.toJson(v);
				System.out.println(s);
				System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/json.txt");
				File myFile = new File(Environment.getExternalStorageDirectory() + "/json.txt");
				try {
					myFile.createNewFile();
					FileOutputStream fOut = new FileOutputStream(myFile);
					OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
					myOutWriter.append(s);
					myOutWriter.close();
					fOut.close();
					System.out.println("guardo todo en un txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "saving", 1024 * 1024);
		t.start();

		try {
			t.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//        File myFile = new File("/sdcard/myjsonstuff.txt");
		//        FileInputStream fIn = new FileInputStream(myFile);
		//        BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
		//        String aDataRow = "";
		//        String aBuffer = ""; //Holds the text
		//        while ((aDataRow = myReader.readLine()) != null) 
		//        {
		//            aBuffer += aDataRow ;
		//        }
		//        myReader.close();
		//		
	}

}

