package com.inzpiral.consumer.models;

public class TreeStructure {
    
	private String mNodeTitle;
	private String mPercent;
	private int mDepth;
	
	public TreeStructure(String mNodeTitle, int depth, String mPercent) {
		this.mNodeTitle = mNodeTitle;
		this.mDepth = depth;
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
	
	public int getDepth() {
		return mDepth;
	}
	public void setDepth(int depth) {
		this.mDepth = depth;
	}
	
}
