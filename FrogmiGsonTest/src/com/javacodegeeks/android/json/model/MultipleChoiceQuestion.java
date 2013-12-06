package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MultipleChoiceQuestion extends FrogmiActivity {
	
	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("multiple")
	public int mMultiple;
	
	@SerializedName("alternatives")
	public List<Alternative> mAlternatives;
	
}
