package com.inzpiral.consumer.models;

import com.google.gson.annotations.SerializedName;

public class Picture extends FrogmiActivity {

	@SerializedName("question")
	public String mQuestion;

	@SerializedName("quality")
	public int mQuality;
	
}
