package com.inzpiral.consumer.models;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
	public void display(Activity activity, View parentView, int parentId) {
		mActivity = activity;
		mParentView = parentView;
		mParentId = parentId;

		System.out.println("MOSTRANDOME! Soy un 'Question' de question: " + getQuestion());
		QuestionController controller = new QuestionController(new QuestionView());
	}


	private class QuestionController implements TextWatcher {
		public QuestionController(QuestionView questionView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(questionView);
			questionView.getQuestionTextView().setText(getQuestion());
			setResult("");
			questionView.setListener(this);
		}

		@Override
		public void afterTextChanged(Editable s) { }

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			setResult(s);
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

		public void setListener(TextWatcher watcher) {
			getFieldEditText().addTextChangedListener(watcher);
		}

	}

}
