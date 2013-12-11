package com.inzpiral.consumer.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Choice {
	
	@SerializedName("etiqueta")
	private String mTag;
	
	@SerializedName("nombre")
	private String mName;
	
	@SerializedName("hijos")
	private List<Choice> mChildren;

	public String getTag() {
		return mTag;
	}

	public void setTag(String mTag) {
		this.mTag = mTag;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public List<Choice> getChildren() {
		return mChildren;
	}

	public void setChildren(List<Choice> mChildren) {
		this.mChildren = mChildren;
	}

}
