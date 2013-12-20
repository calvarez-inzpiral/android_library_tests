package com.inzpiral.consumer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.inzpiral.consumer.R;
import com.viewpagerindicator.IconPagerAdapter;


public class FragmentAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter{
	private int mCount ;
	private int[] ICONS; 

	public FragmentAdapter(FragmentManager fm, int tabsNumber) {
		super(fm);
		//maneja cuando la categoria no tiene tabs
		if (tabsNumber==0 ){
			mCount = 1;
			tabsNumber=1;
		}else{
			mCount = tabsNumber;
		}

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
		Bundle bundle = new Bundle();
		//		Fragment fragment = new Fragment();
		Fragment fragment = new TabFragment();
		switch(position){
		case 0:
			bundle.putString("node_name", "Oferta");
			break;
		case 1:
			//			fragment = new VisibilityFragment();
			bundle.putString("node_name", "Visibilidad");
			break;
		case 2:
			//			fragment = new QualityFragment();
			bundle.putString("node_name", "Calidad");
			break;	
		}
		fragment.setArguments(bundle);
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
