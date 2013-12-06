package com.javacodegeeks.android.json.model;

import com.google.gson.annotations.SerializedName;

public class Picture extends FrogmiActivity {

	
	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("value")
	public String mValue;

	@SerializedName("quality")
	public int mQuality;
	
	@SerializedName("created_at")
	public String mCreatedAt;
	
	@SerializedName("updated_at")
	public String mUpdatedAt;
}
