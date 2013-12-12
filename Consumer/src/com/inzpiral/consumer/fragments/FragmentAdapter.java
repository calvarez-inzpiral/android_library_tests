package com.inzpiral.consumer.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inzpiral.consumer.R;
import com.viewpagerindicator.IconPagerAdapter;


public class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter{

	protected static final String TAB_NAME_1 = "Oferta";
	protected static final String TAB_NAME_2 = "Visibilidad";
	protected static final String TAB_NAME_3 = "Otros";
	protected static final String[] CONTENT = new String[] {
		TAB_NAME_1 , TAB_NAME_2	, TAB_NAME_3
	};
	protected static final int[] ICONS = new int[] {
		R.drawable.perm_group_calendar,
		R.drawable.perm_group_camera,
		R.drawable.perm_group_device_alarms,

	};

	private int mCount = CONTENT.length;

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
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

	@Override
	public CharSequence getPageTitle(int position){
		String title = "";
		switch(position){
		case 0:
			title = TAB_NAME_1;
			break;
		case 1:
			title = TAB_NAME_2;
			break;
		case 2:
			title = TAB_NAME_3;
			break;	
		}
		return title;

	}

	public void setCount(int count){
		if (count > 0 && count < 10){
			mCount = count;
			notifyDataSetChanged();
		}
	}


}
