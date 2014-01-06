package com.inzpiral.consumer.activities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inzpiral.consumer.R;
import com.inzpiral.consumer.fragments.FragmentAdapter;
import com.inzpiral.consumer.fragments.SlideExpandableFragment;
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

public class HomeActivity extends SlidingFragmentActivity implements 
ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

	// Manejo de tabs
	private FragmentAdapter mAdapter;
	private ViewPager mPager;
	private PageIndicator mIndicator;
	protected Fragment mFrag;

	// Manejo de evaluacion
	private EvaluationHelper mHelper;
	private String mURL = "http://www.frogmi.com/consumo_masivo.json";
	//	private String mURL = "http://192.168.1.153/test/consumo_masivo.json";

	// Geotag
	private LocationClient mLocationClient;
	private LocationRequest mLocationRequest;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.loadEvaluation();
		setContentView(R.layout.fragment_home);
		this.loadSlideBar(savedInstanceState);
		this.loadSpinners();

		// Create the LocationClient object
		mLocationClient = new LocationClient(this, this, this);
		// Create the LocationRequest object
		mLocationRequest = LocationRequest.create();
		// Use high accuracy
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		// Set the update interval to 10 seconds
		mLocationRequest.setInterval(10000);
		// Set the fastest update interval to 5 seconds
		mLocationRequest.setFastestInterval(5000);
	}

	@Override
	public void onStart() {
		super.onStart();
		mLocationClient.connect();
	}
	@Override
	public void onStop() {
		if (mLocationClient.isConnected()) {
			mLocationClient.removeLocationUpdates(this);
		}
		mLocationClient.disconnect();
		super.onStop();
	}

	private void loadSpinners() {
		getSupportFragmentManager().beginTransaction().add(R.id.spinners_frame, new SpinnerFragment(), "SpinnerFragment").commit();
	}

	private void loadSlideBar(Bundle savedInstanceState) {
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			//mFrag = new SlideMenuFragment();
			mFrag = new SlideExpandableFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			//mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
			mFrag = (Fragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
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

		mAdapter = new FragmentAdapter(getSupportFragmentManager(), tabNumber);
		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		mPager.setOffscreenPageLimit(3);

		mIndicator = (IconPageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
		mIndicator.notifyDataSetChanged();

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

	public void loadCategories(int groupPosition, int childPosition) {
		System.out.println("groupPosition:" + groupPosition + ", childPosition:" + childPosition);

		mHelper = EvaluationHelper.getInstance();
		Node selectedCategory = mHelper.getCategories().get(groupPosition);
		Node selectedBrand = (Node) selectedCategory.getChildren().get(groupPosition);

		if(mHelper.getCurrentCategory() == selectedCategory && mHelper.getCurrentBrand() == selectedBrand) {
			closeSlidingmenu();
			return;
		}

		mHelper.setCurrentCategory(selectedCategory);
		mHelper.setCurrentBrand(selectedBrand);

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

	// Geotag listener
	@Override
	public void onConnected(Bundle dataBundle) {
		Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		mLocationClient.requestLocationUpdates(mLocationRequest, this);
		//		displayCurrentLocation();
	}

	@Override
	public void onDisconnected() {
		Toast.makeText(this, "Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		Toast.makeText(this, "Connection Failure : " + connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLocationChanged(Location location) {
		displayCurrentLocation();
	}

	public void displayCurrentLocation() {
		Location currentLocation = null;

		// Get the current location's latitude & longitude
		currentLocation = mLocationClient.getLastLocation();
		String msg = "Current Location: " +
				Double.toString(currentLocation.getLatitude()) + "," +
				Double.toString(currentLocation.getLongitude());

		// Display the current location in the UI
		System.out.println(msg);

		// To display the current address in the UI
		(new GetAddressTask(this)).execute(currentLocation);
		mLocationClient.removeLocationUpdates(this);
	}
	/*
	 * Following is a subclass of AsyncTask which has been used to get
	 * address corresponding to the given latitude & longitude.
	 */
	private class GetAddressTask extends AsyncTask<Location, Void, String>{
		Context mContext;
		public GetAddressTask(Context context) {
			super();
			mContext = context;
		}

		/*
		 * When the task finishes, onPostExecute() displays the address. 
		 */
		@Override
		protected void onPostExecute(String address) {
			// Display the current address in the UI
			System.out.println(address);
		}
		@Override
		protected String doInBackground(Location... params) {
			Geocoder geocoder =
					new Geocoder(mContext, Locale.getDefault());
			// Get the current location from the input parameter list
			Location loc = params[0];
			// Create a list to contain the result address
			List<Address> addresses = null;
			try {
				addresses = geocoder.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
			} catch (IOException e1) {
				Log.e("LocationSampleActivity", 
						"IO Exception in getFromLocation()");
				e1.printStackTrace();
				return ("IO Exception trying to get address");
			} catch (IllegalArgumentException e2) {
				// Error message to post in the log
				String errorString = "Illegal arguments " +
						Double.toString(loc.getLatitude()) +
						" , " +
						Double.toString(loc.getLongitude()) +
						" passed to address service";
				Log.e("LocationSampleActivity", errorString);
				e2.printStackTrace();
				return errorString;
			}
			// If the reverse geocode returned an address
			if (addresses != null && addresses.size() > 0) {
				// Get the first address
				Address address = addresses.get(0);
				/*
				 * Format the first line of address (if available),
				 * city, and country name.
				 */
				String addressText = String.format(
						"%s, %s, %s",
						// If there's a street address, add it
						address.getMaxAddressLineIndex() > 0 ?
								address.getAddressLine(0) : "",
								// Locality is usually a city
								address.getLocality(),
								// The country of the address
								address.getCountryName());
				// Return the text
				return addressText;
			} else {
				return "No address found";
			}
		}
	}

}

