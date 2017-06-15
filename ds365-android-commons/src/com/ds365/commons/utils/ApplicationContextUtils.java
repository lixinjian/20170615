package com.ds365.commons.utils;

import android.content.Context;

public class ApplicationContextUtils {

	private static Context context;
	
	public static Context getContext(){
		return context;
	}
	
	public static void init(Context context){
		ApplicationContextUtils.context = context;
	}
}
