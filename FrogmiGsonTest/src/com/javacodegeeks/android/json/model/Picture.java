package com.javacodegeeks.android.json.model;

import com.google.gson.annotations.SerializedName;

public class Picture extends FrogmiActivity {

	@SerializedName("question")
	public String mQuestion;

	@SerializedName("quality")
	public int mQuality;
	
}
