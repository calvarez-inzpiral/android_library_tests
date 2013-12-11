package com.inzpiral.consumer.models;

import com.google.gson.annotations.SerializedName;

public class Repetition extends PresentationNode {

	@SerializedName("original_code")
	private String mOriginalCode;

	@SerializedName("min")
	private int mMin;

	@SerializedName("max")
	private int mMax;

	@SerializedName("initial_question")
	private String mInitialQuestion;

	@SerializedName("final_question")
	private String mFinalQuestion;

	public String getOriginalCode() {
		return mOriginalCode;
	}

	public void setOriginalCode(String mOriginalCode) {
		this.mOriginalCode = mOriginalCode;
	}

	public int getMin() {
		return mMin;
	}

	public void setMin(int mMin) {
		this.mMin = mMin;
	}

	public int getMax() {
		return mMax;
	}

	public void setMax(int mMax) {
		this.mMax = mMax;
	}

	public String getInitialQuestion() {
		return mInitialQuestion;
	}

	public void setInitialQuestion(String mInitialQuestion) {
		this.mInitialQuestion = mInitialQuestion;
	}

	public String getFinalQuestion() {
		return mFinalQuestion;
	}

	public void setFinalQuestion(String mFinalQuestion) {
		this.mFinalQuestion = mFinalQuestion;
	}
	
}
