package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.inzpiral.consumer.R;


public class Picture extends FrogmiActivity implements IAnwerable {

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

	@Override
	public int countAnswers() {
		return hasResult() ? 1 : 0;
	}

	@Override
	public int totalAnswers() {
		return 1;
	}


	private class PictureController implements OnClickListener{
		public PictureController(PictureView sectionView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(sectionView);
			//sectionView.getPictureButton().setText(getQuestion());
			sectionView.setListeners(this);
		}

		@Override
		public void onClick(View v) {
			setResult("photo");
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

		public void setListeners(OnClickListener listener){
			getPictureButton().setOnClickListener(listener);
		}
		public TextView getTextViewButton() {
			return (TextView)findViewById(R.id.txt_PictureTitle);
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
