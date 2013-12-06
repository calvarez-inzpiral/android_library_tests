package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PresentationNode extends Node {

	@SerializedName("node_order")
	public int mNodeOrder;

	@SerializedName("children")
	public List<BaseNode> mChildren;
	
}
