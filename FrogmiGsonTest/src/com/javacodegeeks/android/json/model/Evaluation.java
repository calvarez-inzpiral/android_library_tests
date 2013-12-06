package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Evaluation {
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("name")
	public String mName;
	
	@SerializedName("code")
	public int mCode;
	
	@SerializedName("children")
	public List<Node> mChildren;
	
}
