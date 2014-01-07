package com.inzpiral.consumer.models;

import com.google.gson.annotations.SerializedName;

public class Reference {

	@SerializedName("code")
	private String mCode;
	

	public String getCode() {
		return mCode;
	}

	public void setCode(String code) {
		this.mCode = code;
	}
	
}
