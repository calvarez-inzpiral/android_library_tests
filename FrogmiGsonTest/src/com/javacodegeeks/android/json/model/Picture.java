package com.javacodegeeks.android.json.model;

import com.google.gson.annotations.SerializedName;

public class Picture {
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("value")
	public String mValue;
	
	@SerializedName("required")
	public boolean mRequired;
	
	@SerializedName("code")
	public String mCode;
	
	@SerializedName("quality")
	public int mQuality;
	
	@SerializedName("created_at")
	public String mCreatedAt;
	
	@SerializedName("updated_at")
	public String mUpdatedAt;
}
