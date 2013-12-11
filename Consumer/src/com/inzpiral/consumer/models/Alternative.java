package com.inzpiral.consumer.models;

import com.google.gson.annotations.SerializedName;

public class Alternative {
	
	@SerializedName("node")
	private String mNode;
	
	@SerializedName("name")
	private String mName;
	
	@SerializedName("selection_id")
	private String mSelectionId;
	
	@SerializedName("code")
	private String mCode;
	
	@SerializedName("position")
	private String mPosition;

	public String getmNode() {
		return mNode;
	}

	public void setNode(String mNode) {
		this.mNode = mNode;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public String getSelectionId() {
		return mSelectionId;
	}

	public void setSelectionId(String mSelectionId) {
		this.mSelectionId = mSelectionId;
	}

	public String getCode() {
		return mCode;
	}

	public void setCode(String mCode) {
		this.mCode = mCode;
	}

	public String getPosition() {
		return mPosition;
	}

	public void setPosition(String mPosition) {
		this.mPosition = mPosition;
	}

}
