package com.ds365.commons.json;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import com.ds365.commons.utils.GenericsUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * 这里是关于网络Json的解析JavaBean的工具类
 *
 */
public abstract class JsonParser<T> {
	
	//private Gson  gson=new Gson();
	ObjectMapper mapper = new ObjectMapper();
	{
		//设置将对象转换成JSON字符串时候:包含的属性不能为空或"";    
        //Include.Include.ALWAYS 默认    
        //Include.NON_DEFAULT 属性为默认值不序列化    
        //Include.NON_EMPTY 属性为 空（""）  或者为 NULL 都不序列化    
        //Include.NON_NULL 属性为NULL 不序列化    
        //mapper.setSerializationInclusion(Inclusion.NON_EMPTY);  
        //设置将MAP转换为JSON时候只转换值不等于NULL的  
        //mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);  
        //mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);  
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
        //设置有属性不能映射成PO时不报错  
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	public  T parseJSON(String json){	
		Type type=GenericsUtils.getSuperClassGenricType(getClass(),0);
		JavaType javaType = mapper.getTypeFactory().constructType(type);
		
		T t=null;
		try {
			t = mapper.readValue(json,javaType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("parse  json to  type error:"+e.getMessage());
		} 
		return t;
		//return JacksonUtils.toObject(json, clazz);
		
		//return jacks
		//return GsonHelper.fromJson(json,(Class<T>)type);
		//return  gson.fromJson(json,type);
	}
}