package com.javacodegeeks.android.json.model;

import com.google.gson.annotations.SerializedName;

public class Question {
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("required")
	public boolean mRequired;
	
	@SerializedName("code")
	public String mCode;
	
	@SerializedName("original_code")
	public String mOriginalCode;
	
	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("node_order")
	public int mNodeOrder;
	
}
