package com.javacodegeeks.android.json.model;

import com.google.gson.annotations.SerializedName;

public class Alternative {
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("name")
	public String mName;
	
	@SerializedName("selection_id")
	public String mSelectionId;
	
	@SerializedName("code")
	public String mCode;
	
	@SerializedName("position")
	public String mPosition;

}
