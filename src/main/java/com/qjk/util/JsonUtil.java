package com.qjk.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Json工具类 
 * 默认使用GSON
 * @author qiejinkai
 *
 */

public class JsonUtil {

	private JsonUtil() {
		
	}
	
	public static String toJson(Object obj){
		
		Gson gson= new GsonBuilder().create();
		
		return gson.toJson(obj);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json,T t){
		
		Gson gson= new GsonBuilder().create();
		
		return (T)gson.fromJson(json, t.getClass());
	}
	
}
