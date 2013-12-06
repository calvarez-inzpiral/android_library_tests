package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Geotag {
//	"node":"Frogmi::Activities::Automatic::Geotag",
//	"required":true,
//	"code":"864-14956",
//	"original_code":"14956",
//	"node_order":2
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("required")
	public boolean mRequired;
	
	@SerializedName("code")
	public String mCode;
	
	@SerializedName("original_code")
	public int mOriginalCode;
	
	@SerializedName("node_order")
	public int mNodeOrder;
}
