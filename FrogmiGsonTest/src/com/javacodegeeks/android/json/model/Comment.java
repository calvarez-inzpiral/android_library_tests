package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Comment {
	
	@SerializedName("node")
	public String mNode;
	
	@SerializedName("required")
	public boolean mRequired;
	
	@SerializedName("code")
	public String mCode;
	
	@SerializedName("original_code")
	public int mOriginalCode;
	
	@SerializedName("question")
	public String mQuestion;
	
	@SerializedName("node_order")
	public int mNodeOrder;
	
//	"node":"Frogmi::Activities::Manual::Comment",
//	"required":true,
//	"code":"864-14954",
//	"original_code":"14954",
//	"question":"Bienvenido al proceso de auditoria de area de cajas, de la Gerencia de prevencion de perdidas",
//	"node_order":0
}
