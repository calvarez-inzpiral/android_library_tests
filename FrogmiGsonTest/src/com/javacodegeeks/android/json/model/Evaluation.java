package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Evaluation {
	

	public List<Result> evaluation;
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("name")
	public String mName;
	
	@SerializedName("code")
	public int mCcode;
	
	@SerializedName("children")
	public List<Result> mChildern;
	
	
	
//	"node":"Frogmi::Evaluation",
//	"name":"SMU P.P Chequeo de Cajas Unimarc 2.0",
//	"code":"864",
//	"children":[
	

}
