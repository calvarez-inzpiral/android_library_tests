package com.inzpiral.consumer.models;

import android.app.Activity;

import com.google.gson.annotations.SerializedName;

public class FrogmiActivity extends BaseNode implements IDisplayable {
	
	transient protected Activity mActivity;
	transient protected int mParentId;

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

	@Override
	public void display(Activity activity, int parentId) {
		System.out.println("MOSTRANDOME! Soy un 'FrogmiActivity' de tipo: " + getNode());
	}

}
