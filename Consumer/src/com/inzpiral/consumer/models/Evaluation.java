package com.inzpiral.consumer.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Evaluation {
	
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("name")
	public String mName;
	
	@SerializedName("code")
	public String mCode;

	@SerializedName("children")
	public List<? extends BaseNode> mChildren;
	
}
