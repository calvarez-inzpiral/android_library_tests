package com.inzpiral.consumer.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;


public class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter{
	private int mCount ;

	private int[] ICONS; 

	public FragmentAdapter(FragmentManager fm, int tabsNumber, int [] icons) {
		super(fm);
		mCount = tabsNumber;
		ICONS  = icons;
	}

	@Override
	public int getIconResId(int index) {
	
		return ICONS[index % ICONS.length];
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = new Fragment();
		switch(position){
		case 0:
			fragment = new MainFragment();
			break;
		case 1:
			fragment = new Tab2();
			break;
		case 2:
			fragment = new Tab3();
			break;	
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return mCount;
	}

	public void setCount(int count){
		if (count > 0 && count < 10){
			mCount = count;
			notifyDataSetChanged();
		}
	}


}
