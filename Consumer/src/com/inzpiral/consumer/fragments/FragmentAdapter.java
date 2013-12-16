package com.inzpiral.consumer.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.inzpiral.consumer.R;
import com.viewpagerindicator.IconPagerAdapter;


public class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter{
	private int mCount ;
	private int[] ICONS; 
	
	public FragmentAdapter(FragmentManager fm, int tabsNumber) {
		super(fm);
		mCount = tabsNumber;
		
		switch(tabsNumber){
		case 1:
			int[] ICONS1 = new int[] {
					R.drawable.perm_group_calendar,
				};
			  ICONS = ICONS1;
			  setICONS(ICONS1);
			break;
		case 2:
			 int[] ICONS2 = new int[] {
					R.drawable.perm_group_calendar,
					R.drawable.perm_group_camera,
			            
				};
			 ICONS = ICONS2;
			  setICONS(ICONS2);
			break;
		case 3:
		     int[] ICONS3 = new int[] {
					R.drawable.perm_group_calendar,
					R.drawable.perm_group_camera,
			        R.drawable.perm_group_device_alarms,
				};
		     ICONS = ICONS3;
		     setICONS(ICONS3);
	    
			break;	
		}
		
	}
	public FragmentAdapter(FragmentManager fm) {
		super(fm);
		mCount = 1;
		
		int[] ICONS1 = new int[] {
					R.drawable.perm_group_calendar,
			};
		ICONS = ICONS1;
		setICONS(ICONS1);
			
		
		
	}

	public void setICONS(int[] iCONS) {
		notifyDataSetChanged();
		ICONS = iCONS;
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
			fragment = new SalesFragment();
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
