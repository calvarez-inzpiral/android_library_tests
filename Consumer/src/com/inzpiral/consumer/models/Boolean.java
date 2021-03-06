package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.gson.annotations.SerializedName;
import com.inzpiral.consumer.R;


public class Boolean extends FrogmiActivity implements IAnwerable {

	@SerializedName("question")
	private String mQuestion;

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}

	@Override
	public void display(Activity activity, View parentView, int parentId) {
		mActivity = activity;
		mParentView = parentView;
		mParentId = parentId;

		System.out.println("MOSTRANDOME! Soy un 'Boolean' de question: " + getQuestion());
		BooleanController controller = new BooleanController(new BooleanView());
	}

	@Override
	public int countAnswers() {
		return 1;
	}

	@Override
	public int totalAnswers() {
		return 1;
	}


	private class BooleanController implements OnCheckedChangeListener {
		public BooleanController(BooleanView booleanView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(booleanView);
			booleanView.getRadioButton().setText(getQuestion());
			booleanView.setListener(this);
			
			if(hasResult()) {
				booleanView.getRadioButton().setChecked(getResult().equals("true")); 
			}
			else {
			
				setResult(""+false);				
			}
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			setResult(""+isChecked);
		}

	}
	
	private class BooleanView extends LinearLayout {
		public BooleanView() {
			super(mActivity);
			LayoutInflater mInflater = LayoutInflater.from(mActivity);
			addView(mInflater.inflate(R.layout.module_boolean, null));
		}

		public CheckBox getRadioButton() {
			return (CheckBox)findViewById(R.id.check_box);
		}

		public void setListener(OnCheckedChangeListener listener) {
			getRadioButton().setOnCheckedChangeListener(listener);
		}
	}

	@Override
	public void formPercent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void formClear() {
		// TODO Auto-generated method stub
		
	}

}
