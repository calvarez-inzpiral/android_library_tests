package com.javacodegeeks.android.json.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Picture {

//	{
//		node: "Frogmi::Activities::Manual::Picture",
//		question: "Adjunte fotograf’a",
//		value: null,
//		required: false,
//		code: "362304f0-3f27-0131-ae6c-00c16eea5c29",
//		quality: 3,
//		created_at: "2013-12-04T15:32:31Z",
//		updated_at: "2013-12-04T15:32:31Z"
//		},
//}

	public List<Result> comment;
		
		@SerializedName("node")
		public String mNode;
		
		@SerializedName("question")
		public String mQuestion;
		
		@SerializedName("value")
		public String mValue;
		
		@SerializedName("required")
		public boolean mRequired;
		
		@SerializedName("code")
		public String mCode;
		
		@SerializedName("quality")
		public int mQuality;
		
		@SerializedName("created_at")
		public String mCreatedAt;
		
		@SerializedName("updated_at")
		public String mUpdatedAt;
	}
