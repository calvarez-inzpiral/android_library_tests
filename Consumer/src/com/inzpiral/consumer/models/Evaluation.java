package com.inzpiral.consumer.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Evaluation {

	@SerializedName("node")
	private String mNode;

	@SerializedName("name")
	private String mName;

	@SerializedName("code")
	private String mCode;

	@SerializedName("children")
	private List<? extends BaseNode> mChildren;

	public String getNode() {
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

	public String getCode() {
		return mCode;
	}

	public void setCode(String mCode) {
		this.mCode = mCode;
	}

	public List<? extends BaseNode> getChildren() {
		return mChildren;
	}

	public void setChildren(List<? extends BaseNode> mChildren) {
		this.mChildren = mChildren;
	}

}
