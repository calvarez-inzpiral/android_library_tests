package com.inzpiral.consumer.models;

import com.google.gson.annotations.SerializedName;

public class BaseNode {

	@SerializedName("node")
	private String mNode;
	
	@SerializedName("code")
	private String mCode;
	
	@SerializedName("required")
	private boolean mRequired;

	public String getNode() {
		return mNode;
	}

	public void setNode(String mNode) {
		this.mNode = mNode;
	}

	public String getCode() {
		return mCode;
	}

	public void setCode(String mCode) {
		this.mCode = mCode;
	}

	public boolean isRequired() {
		return mRequired;
	}

	public void setRequired(boolean mRequired) {
		this.mRequired = mRequired;
	}

}
