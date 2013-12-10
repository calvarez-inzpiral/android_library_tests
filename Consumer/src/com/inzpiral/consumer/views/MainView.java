package com.inzpiral.consumer.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.controllers.MainController.MainControllerListener;

public class MainView extends RelativeLayout{

	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	// Listeners
	public void setListeners(OnItemSelectedListener onItemSelectedListener){
		getCategories().setOnItemSelectedListener(onItemSelectedListener);
		getLocations().setOnItemSelectedListener(onItemSelectedListener);
	}
	
	// Llamadas externas
	public void enableAll(boolean status) {
//		getCategories().setEnabled(status);
//		getLocations().setEnabled(status);
		getContent().setEnabled(status);
		getWaitLooper().setVisibility(status ? View.GONE : View.VISIBLE);
	}
	
	// Getters
	public ProgressBar getWaitLooper() {
		return ((ProgressBar) this.findViewById(R.id.wait));
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
