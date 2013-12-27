package com.inzpiral.consumer.models;

import java.util.List;

import android.app.Activity;
import android.view.View;

import com.google.gson.annotations.SerializedName;

public class ChainedChoice extends FrogmiActivity {

	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("chain_choice")
	public List<Choice> mChildren;

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}
	
	public List<Choice> getChildren() {
		return mChildren;
	}

	public void setChildren(List<Choice> mChildren) {
		this.mChildren = mChildren;
	}

	@Override
	public void display(Activity activity, View ParentView, int parentId) {
		System.out.println("MOSTRANDOME! Soy un 'ChainedChoice'");
	}
	
}
