package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Choice {
	
	@SerializedName("etiqueta")
	public String mTag;
	
	@SerializedName("nombre")
	public String mName;
	
	@SerializedName("hijos")
	public List<Choice> mChildren;

}
