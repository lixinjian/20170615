package com.ds365.commons.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * 泛型工具类
 * 
 * @author CGB
 * 
 */
public class GenericsUtils {

	@SuppressWarnings("unchecked")
	public static Type getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return  params[index];
	}
	
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricClazz(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("index out of bound");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricClazz(Class clazz) {
		return getSuperClassGenricClazz(clazz, 0);
	}
}