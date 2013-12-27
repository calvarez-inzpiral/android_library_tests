package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.View;

import com.google.gson.annotations.SerializedName;

public class Comment extends FrogmiActivity {

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
		System.out.println("MOSTRANDOME! Soy un 'Comment'");
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
