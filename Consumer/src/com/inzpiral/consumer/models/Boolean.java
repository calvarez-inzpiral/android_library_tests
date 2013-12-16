package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.inzpiral.consumer.R;


public class Boolean extends FrogmiActivity {

	@SerializedName("question")
	private String mQuestion;

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}
	
	@Override
	public void display(Activity activity, int parentId) {
		mActivity = activity;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'Boolean' de question: " + getQuestion());
		BooleanController controller = new BooleanController(new BooleanView());
	}

	
	private class BooleanController {
		public BooleanController(BooleanView sectionView) {
			((LinearLayout) mActivity.findViewById(mParentId)).addView(sectionView);
			sectionView.getRadioButton().setText(getQuestion());
		}
	}
	
	private class BooleanView extends FrameLayout {
		public BooleanView() {
			super(mActivity);
			LayoutInflater mInflater = LayoutInflater.from(mActivity);
			addView(mInflater.inflate(R.layout.module_boolean, null));
		}
		
		public TextView getRadioButton() {
			return (TextView)findViewById(R.id.radio_button);
		}
	}

}
