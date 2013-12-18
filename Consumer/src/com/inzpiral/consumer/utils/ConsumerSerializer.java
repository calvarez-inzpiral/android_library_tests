package com.inzpiral.consumer.utils;

import java.lang.reflect.Type;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.inzpiral.consumer.models.BaseNode;


public class ConsumerSerializer implements JsonSerializer<BaseNode> {

	@Override
	public JsonElement serialize(BaseNode src, Type typeOfSrc, JsonSerializationContext context) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(BaseNode.class, new ConsumerSerializer());
		Gson gson = builder.create();
		return gson.toJsonTree(src);
	}
//	public BaseNode deserialize(JsonElement json, Type typeOfT,
//			JsonDeserializationContext context) throws JsonParseException {
//		
//		String node = json.getAsJsonObject().get("node").getAsString();
//		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(BaseNode.class, new ConsumerDeserializer());
//		Gson gson = builder.create();
//		
//		return gson.fromJson(json, ModelUtils.getTypeByTag(node));
//	}

}
