package com.inzpiral.consumer.models;

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
	
}
