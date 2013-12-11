package com.inzpiral.consumer.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MultipleChoiceQuestion extends FrogmiActivity {

	@SerializedName("question")
	private String mQuestion;
	
	@SerializedName("multiple")
	private int mMultiple;
	
	@SerializedName("alternatives")
	private List<Alternative> mAlternatives;

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}

	public int getMultiple() {
		return mMultiple;
	}

	public void setMultiple(int mMultiple) {
		this.mMultiple = mMultiple;
	}

	public List<Alternative> getAlternatives() {
		return mAlternatives;
	}

	public void setAlternatives(List<Alternative> mAlternatives) {
		this.mAlternatives = mAlternatives;
	}
	
}
