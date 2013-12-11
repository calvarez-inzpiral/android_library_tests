package com.inzpiral.consumer.models;

import java.util.List;

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
	
}
