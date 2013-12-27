package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.View;

import com.google.gson.annotations.SerializedName;

public abstract class FrogmiActivity extends BaseNode implements IDisplayable {
	
	// Transient values
	transient protected Activity mActivity;
	transient protected View mParentView;
	transient protected int mParentId;

	// Properties
	@SerializedName("node_order")
	private int mNodeOrder;
	
	@SerializedName("original_code")
	private String mOriginalCode;
	
	@SerializedName("result")
	private String mResult;

	// Getters and Setters
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

	public String getResult() {
		return mResult;
	}

	public void setResult(String result) {
		this.mResult = result;
	}
	
	// General methods
	public boolean hasResult() {
		return mResult != null && !mResult.equals("");
	}

	// Abstract methods
	@Override
	public abstract void display(Activity activity, View ParentView, int parentId);

}
