package com.ds365.commons;

import com.ds365.commons.utils.ApplicationContextUtils;

import android.app.Application;
import android.content.Context;

public  abstract class BaseApplication extends Application{

	private Context context;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = getApplicationContext();
		
		ApplicationContextUtils.init(context);
	}
	
}
