package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ChainedChoice extends FrogmiActivity {

	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("chain_choice")
	public List<Choice> mChildren;
	
}
