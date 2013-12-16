package com.inzpiral.consumer.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.inzpiral.consumer.R;

public class SalesView extends RelativeLayout{

	public SalesView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	// Listeners
	public void setListeners(OnItemSelectedListener onItemSelectedListener){
		
	}
	
	// Getters
	public LinearLayout getContent() {
		return ((LinearLayout) this.findViewById(R.id.sales_content));
	}
	
}
