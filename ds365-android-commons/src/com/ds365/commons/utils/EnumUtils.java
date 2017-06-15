package com.ds365.commons.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.ds365.commons.message.MessageFunctionType;
/**
 * 
 * @author cgb
 *后期做缓存
 */
public class EnumUtils {

	
	@SuppressWarnings("unchecked")
	public static <E extends MessageFunctionType>   Map<Integer,Class>      converMessageFunctionTypeToMap(Class<E> enumType){
		Map<Integer,Class>  result=new HashMap<>();
		try {
			Method m = enumType.getMethod("values");
			E[] enumsArray = (E[])m.invoke(null, new Object[]{});
			for(E e : enumsArray){
				result.put(e.getId(),e.getIntentClass());
			}
		} catch (Exception e) {
			throw new RuntimeException("get enum by v",e);
		}
		
		return result;
	}
	
}
