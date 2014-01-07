package com.inzpiral.consumer.models;

public class TreeStructure {
    
	private String mNodeCode;
	private String mNodeTitle;
	private String mPercent;
	private int mDepth;
	
	public TreeStructure(String nodeCode, String mNodeTitle, int depth, String mPercent) {
		this.mNodeCode = nodeCode;
		this.mNodeTitle = mNodeTitle;
		this.mDepth = depth;
		this.mPercent = mPercent;
	}
	
	public String getNodeCode() {
		return mNodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.mNodeCode = nodeCode;
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
