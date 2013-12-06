package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MultipleChoiceQuestion {

//	"node":"Frogmi::Activities::Manual::MultipleChoiceQuestion",
//	"required":true,
//	"code":"864-14965",
//	"original_code":"14965",
//	"question":"Existen egresos de fechas anteriores a la ultima rendicion de gastos",
//	"multiple":"0",
//	"node_order":21
	
	public List<Result> multipleChoiceQuestion;
	
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
	
	@SerializedName("multiple")
	public int mMultiple;
	
	@SerializedName("node_order")
	public int mNodeOrder;
}
