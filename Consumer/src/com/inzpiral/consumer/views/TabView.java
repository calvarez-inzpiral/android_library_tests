package com.inzpiral.consumer.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.inzpiral.consumer.R;

public class TabView extends ScrollView{

	public TabView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	// Listeners
	public void setListeners(OnItemSelectedListener onItemSelectedListener){
		
	}
	
	// Getters
	public LinearLayout getContent() {
		return ((LinearLayout) this.findViewById(R.id.tab_content));
	}
	
}
