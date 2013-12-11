package com.inzpiral.consumer.models;

import com.google.gson.annotations.SerializedName;

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
	
}
