package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.gson.annotations.SerializedName;
import com.inzpiral.consumer.R;


public class Boolean extends FrogmiActivity {

	transient protected BooleanController mController;

	@SerializedName("question")
	private String mQuestion;

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}
	
	@Override
	public void display(Activity activity, View ParentView, int parentId) {
		mActivity = activity;
		mParentView = ParentView;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'Boolean' de question: " + getQuestion());
		mController = new BooleanController(new BooleanView());
	}

	
	private class BooleanController implements OnCheckedChangeListener {
		private BooleanView mBooleanView;
		
		public BooleanController(BooleanView booleanView) {
			mBooleanView = booleanView;
			((LinearLayout) mParentView.findViewById(mParentId)).addView(booleanView);
			mBooleanView.getRadioButton().setText(getQuestion());
			setResult(false);
			mBooleanView.setListener(this);
		}
		
		public void retrieveResult() {
			setResult( mBooleanView.getRadioButton().isChecked() );
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			retrieveResult();
		}
		
	}
	
	private class BooleanView extends FrameLayout {
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

}
