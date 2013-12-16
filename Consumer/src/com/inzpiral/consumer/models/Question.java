package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.inzpiral.consumer.R;

public class Question extends FrogmiActivity {

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
		
		System.out.println("MOSTRANDOME! Soy un 'Question' de question: " + getQuestion());
		QuestionController controller = new QuestionController(new QuestionView());
	}

	
	private class QuestionController {
		public QuestionController(QuestionView questionView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(questionView);
			questionView.getQuestionTextView().setText(getQuestion());
		}
	}
	
	private class QuestionView extends LinearLayout {
		public QuestionView() {
			super(mActivity);
			LayoutInflater mInflater = LayoutInflater.from(mActivity);
			addView(mInflater.inflate(R.layout.module_question, null));
		}
		
		public TextView getQuestionTextView() {
			return (TextView)findViewById(R.id.question);
		}
		
		public EditText getFieldEditText() {
			return (EditText)findViewById(R.id.field);
		}
	}
	
}
