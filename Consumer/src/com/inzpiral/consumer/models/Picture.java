package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.inzpiral.consumer.R;


public class Picture extends FrogmiActivity {

	@SerializedName("question")
	private String mQuestion;

	@SerializedName("quality")
	private int mQuality;

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}

	public int getQuality() {
		return mQuality;
	}

	public void setQuality(int mQuality) {
		this.mQuality = mQuality;
	}
	
	@Override
	public void display(Activity activity, View ParentView, int parentId) {
		mActivity = activity;
		mParentView = ParentView;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'Boolean' de question: " + getQuestion());
		PictureController controller = new PictureController(new PictureView());
	}

	
	private class PictureController {
		public PictureController(PictureView sectionView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(sectionView);
			//sectionView.getPictureButton().setText(getQuestion());
		}
	}
	
	private class PictureView extends LinearLayout {
		public PictureView() {
			super(mActivity);
			LayoutInflater mInflater = LayoutInflater.from(mActivity);
			addView(mInflater.inflate(R.layout.module_picture, null));
			getTextViewButton().setText(getQuestion());
		}
		
		public Button getPictureButton() {
			return (Button)findViewById(R.id.btn_picture);
		}
		public TextView getTextViewButton() {
			return (TextView)findViewById(R.id.txt_PictureTitle);
		}
	}
	
}
