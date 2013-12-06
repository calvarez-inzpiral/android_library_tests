package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Comment {
	
	@SerializedName("original_code")
	public int mOriginalCode;
	
	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("node_order")
	public int mNodeOrder;
	

}
