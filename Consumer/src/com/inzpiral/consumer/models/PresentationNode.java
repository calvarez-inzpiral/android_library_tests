package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.View;


public class PresentationNode extends Node {
	
	transient protected Activity mActivity;
	transient protected View mParentView;
	transient protected int mParentId;
	
}
