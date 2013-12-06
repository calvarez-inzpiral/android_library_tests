package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Geotag {
	
	@SerializedName("original_code")
	public int mOriginalCode;
	
	@SerializedName("node_order")
	public int mNodeOrder;
}
