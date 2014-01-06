package com.inzpiral.consumer.models;

public class TreeStructure {
	String mNodeTitle;
	String mPercent;
	public TreeStructure(String mNodeTitle, String mPercent) {
		super();
		this.mNodeTitle = mNodeTitle;
		this.mPercent = mPercent;
	}
	public String getNodeTitle() {
		return mNodeTitle;
	}
	public void setNodeTitle(String NodeTitle) {
		this.mNodeTitle = NodeTitle;
	}
	public String getPercent() {
		return mPercent;
	}
	public void setPercent(String Percent) {
		this.mPercent = Percent;
	}
	
}
