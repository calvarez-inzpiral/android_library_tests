package com.javacodegeeks.android.json.model;

import com.google.gson.annotations.SerializedName;

public class BaseNode {

	@SerializedName("node")
	public String mNode;
	
	@SerializedName("code")
	public String mCode;
	
	@SerializedName("required")
	public boolean mRequired;

}
