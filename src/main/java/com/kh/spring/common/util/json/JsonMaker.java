package com.kh.spring.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMaker {
	
	//객체를 넣으면 제이슨으로 파싱
	public static String json(Object object) {
		
		ObjectMapper mapper = new ObjectMapper();
		String objectToJson;
		try {
			
			objectToJson = mapper.writeValueAsString(object);
			
		} catch (JsonProcessingException e) {
			throw new RuntimeException("제이슨 파싱 에러");
		}


		return objectToJson;
	}

}
