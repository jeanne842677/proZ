package com.kh.spring.common.util.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CamelMap{
			
	
	
	
	
	
	public static Map<String, Object> changeMap(Map<String , Object> map) {
		
		Map<String ,  Object> newMap = new HashMap<>();
		
		for( Entry<String, Object > ee : map.entrySet() ) {
			
			String[] strArr = ee.getKey().toLowerCase().split("_");

			StringBuffer key = new StringBuffer(strArr[0]);
			
			Object value = ee.getValue();
			
			if(strArr.length > 1) {
				for(int i = 1 ; i < strArr.length ; i++) {
					
					
					key.append(strArr[i].substring(0,1).toUpperCase());
					key.append(strArr[i].substring(1));
					
					
				}
				
			}
			
			newMap.put(key.toString(), value);
			
		
		}
		
		
		
		
			
		return newMap;

	}	
	
	
	public static List<Map<String, Object>> changeListMap(List<Map<String , Object>> list) {
		
		List<Map<String, Object>> newList = new ArrayList<>();
		
		for (Map<String, Object> m : list) {
			
			newList.add(changeMap(m));
	
		}

		
		return newList;
		
		
	}
			
			
}