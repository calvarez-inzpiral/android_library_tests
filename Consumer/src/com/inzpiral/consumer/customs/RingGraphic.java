package com.inzpiral.consumer.customs;

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

public class RingGraphic extends RelativeLayout {
	
	private int mPercent;
	private RingGraphicDrawable mDrawable;

	public RingGraphic(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mDrawable = new RingGraphicDrawable(0);
		setBackgroundDrawable(mDrawable);
	}

	public int getPercent() {
		return mPercent;
	}

	public void setPercent(int percent) {
		this.mPercent = percent;
		mDrawable.setDegrees(percentToDegrees(percent));
		((TextView) findViewById(R.id.percent_textview)).setText(percent + "%");
	}
	
	private int percentToDegrees(int percent) {
		return percent * 360 / 100;
	}
	
}
