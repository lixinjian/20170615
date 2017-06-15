package com.ds365.commons.utils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ds365.commons.json.JacksonUtils;

public class BeanUtils {
	
	private static final  String  GET_PRE="get";

	 public static <T extends Serializable> Map<String, Object>   beanToMap(T t) {  
		 Map<String, Object> result=new HashMap<String, Object>();
		 
		 	Method[] methods = t.getClass().getMethods();

	        for (Method method : methods) 
	        { 
	            try 
	            { 
	                if (method.getName().startsWith(GET_PRE)) 
	                { 
	                	method.setAccessible(true);
	                    String fieldName = getFieldName(method); 

	                    Object value = method.invoke(t, (Object[])null); 
	                    
	                    if (null != value) {
	                    	 
	                    	if (value instanceof Date) {
	                    		String str = DateFormatUtils.dateToStringForyyyyMMdd((Date) value);
	                    	    result.put(fieldName, str);
							}else{
								result.put(fieldName, value.toString());
							}
//	                    result.put(fieldName, null == value ? "" : value.toString()); 
						}
	                } 
	            } 
	            catch (Exception e) 
	            { 
	            	L.e("commons",e.getMessage());
	  
	            } 
	        } 
	        
	  
	      return  result;  
		/*Map map = JacksonUtils.objectToMap(t);
		
		return map;*/
	  
	}

	private static String getFieldName(Method method) {
		String fieldName = method.getName(); 
		fieldName = fieldName.substring(fieldName.indexOf(GET_PRE) + 3); 
		fieldName = fieldName.toLowerCase().charAt(0) + fieldName.substring(1);
		return fieldName;
	} 
	
}
