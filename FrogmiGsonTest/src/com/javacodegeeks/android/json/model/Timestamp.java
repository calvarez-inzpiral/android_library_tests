package com.javacodegeeks.android.json.model;

import com.google.gson.annotations.SerializedName;

public class Timestamp {
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("required")
	public boolean mRequired;
	
	@SerializedName("code")
	public String mCode;
	
	@SerializedName("original_code")
	public String mOriginalCode;
	
	@SerializedName("node_order")
	public int mNodeOrder;

}
