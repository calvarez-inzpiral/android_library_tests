package com.inzpiral.consumer.activities;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.inzpiral.consumer.R;
import com.inzpiral.consumer.fragments.FragmentAdapter;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class HomeActivity extends SherlockFragmentActivity {
	
	//variables para manejo de tabs
	FragmentAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmenthome);

		this.loadTabs();
	}

	private void loadTabs(){
		mAdapter = new FragmentAdapter(getSupportFragmentManager());

		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);		
	}

	@Override
	public void onBackPressed(){
		moveTaskToBack(true);
	}

}

