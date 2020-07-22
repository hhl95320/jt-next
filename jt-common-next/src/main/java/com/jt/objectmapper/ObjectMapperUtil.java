package com.jt.objectmapper;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {

	private static ObjectMapper objectMapper =new ObjectMapper();
	
	public static String toJson(Object obj){
		if(obj==null) {
			throw new NullPointerException("对象为空");
		}
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static <T>T toObject(String json,Class<T> targetClass){
		if(StringUtils.isEmpty(json)|| targetClass==null) {
			throw new NullPointerException("参数为空");
		}
		try {
			return objectMapper.readValue(json, targetClass);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
