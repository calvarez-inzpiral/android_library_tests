package com.inzpiral.consumer.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.inzpiral.consumer.models.BaseNode;


public class ConsumerDeserializer implements JsonDeserializer<BaseNode> {

	@Override
	public BaseNode deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		String node = json.getAsJsonObject().get("node").getAsString();
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(BaseNode.class, new ConsumerDeserializer());
		Gson gson = builder.create();
		
		return gson.fromJson(json, ModelUtils.getTypeByTag(node));
	}
	
}
