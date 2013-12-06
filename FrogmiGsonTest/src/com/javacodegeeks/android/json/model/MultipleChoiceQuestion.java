package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MultipleChoiceQuestion {

	@SerializedName("original_code")
	public int mOriginalCode;
	
	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("multiple")
	public int mMultiple;
	
	@SerializedName("node_order")
	public int mNodeOrder;
	
	@SerializedName("alternatives")
	public List<Alternative> mAlternatives;
	
}
