package com.javacodegeeks.android.json.model;

import com.google.gson.annotations.SerializedName;

public class Repetition extends PresentationNode {

	@SerializedName("original_code")
	public String mOriginalCode;

	@SerializedName("min")
	public int mMin;

	@SerializedName("max")
	public int mMax;

	@SerializedName("initial_question")
	public String mInitialQuestion;

	@SerializedName("final_question")
	public String mFinalQuestion;
	
}
