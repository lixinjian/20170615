package com.ds365.commons.utils;

public class SingleClickUtils {
	
	private static final int DEFAULT_TIME = 1000;
	private static long lastTime;

	public static boolean isSingle(){
		boolean isSingle ;
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastTime <= DEFAULT_TIME){
			isSingle = true;
		}else{
			isSingle = false;
		}
		lastTime = currentTime;
		return isSingle;
	}
}
