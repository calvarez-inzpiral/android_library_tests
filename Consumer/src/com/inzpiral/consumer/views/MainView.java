package com.inzpiral.consumer.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.inzpiral.consumer.R;

public class MainView extends RelativeLayout{

	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setListeners(OnItemSelectedListener onItemSelectedListener){
		getCategories().setOnItemSelectedListener(onItemSelectedListener);
		getLocations().setOnItemSelectedListener(onItemSelectedListener);
	}

	public Spinner getCategories() {
		return ((Spinner) this.findViewById(R.id.categories));
	}
	
	public Spinner getLocations() {
		return ((Spinner) this.findViewById(R.id.location));
	}
	
	public LinearLayout getContent() {
		return ((LinearLayout) this.findViewById(R.id.content));
	}
	
}
