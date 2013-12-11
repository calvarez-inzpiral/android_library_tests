package com.inzpiral.consumer.models;

import com.google.gson.annotations.SerializedName;

public class FrogmiActivity extends BaseNode {

	@SerializedName("node_order")
	private int mNodeOrder;
	
	@SerializedName("original_code")
	private String mOriginalCode;

	public int getNodeOrder() {
		return mNodeOrder;
	}

	public void setNodeOrder(int mNodeOrder) {
		this.mNodeOrder = mNodeOrder;
	}

	public String getOriginalCode() {
		return mOriginalCode;
	}

	public void setOriginalCode(String mOriginalCode) {
		this.mOriginalCode = mOriginalCode;
	}

}
