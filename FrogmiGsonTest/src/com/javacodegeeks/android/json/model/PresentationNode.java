package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PresentationNode {
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("required")
	public boolean mRequired;
	
	@SerializedName("name")
	public String mName;
	
	@SerializedName("code")
	public String mCode;
	
	@SerializedName("node_order")
	public int mNodeOrder;

	@SerializedName("children")
	public List<Node> mChildren;
	
}
