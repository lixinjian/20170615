package com.ds365.erp.wms.pda.common.base;


import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.BaseApplication;
import com.ds365.commons.message.NotificationService;
import com.ds365.commons.utils.ActivityUtils;
import com.ds365.commons.utils.ApplicationContextUtils;
import com.ds365.commons.utils.CrashHandler;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.SharedPreferencesUtils;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.view.login.activity.LoginActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

/**
 * 这里是本应用的最底层的Application的封装类
 * 简单解释：BaseApplication在清单文件中已经配置了，应用运行的时候首先会运行这个Application，这个时候根据
 * 线程的管理，会生成一个主线程，Application运行在主线程中
 *
 */
public class PdaApplication extends BaseApplication{
	
	//应用的上下文
	private static Application application; 
	//主线程
	private static Thread mainThread;
	//主线程ID
	private static int mainThreadID=-1;
	//主线程中的Looper
	private static Looper mainThreadLooper;
	//主线程的Handler
	private static Handler mainThreadHandler;

	@SuppressWarnings("static-access")
	@Override
	public void onCreate() {
		super.onCreate();
		this.application=this;
		this.mainThread=Thread.currentThread();
		this.mainThreadID=android.os.Process.myTid();
		this.mainThreadLooper=getMainLooper();
		this.mainThreadHandler=new Handler();
		
		ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(
				this).build());
		
		
		AppGlobal.activeFlag = PdaConstants.activeFlag;
		GlobalUtils.init(this);
		
		if (!AppGlobal.activeFlag) {
			CrashHandler crashHandler = CrashHandler.getInstance();   
	        crashHandler.init(getApplicationContext());  
		}
	}
	
	/**
	 * 下面的五个方法是关于定义的成员变量的获取的方法,为工具类进行封装提供方便
	 */
	
	public static Application getMainContext(){
		if(null==application){
		}
		return application;
	}
	public static Thread getMainThread(){
		return mainThread;
	}
	public static int getMaiThredId(){
		return mainThreadID;
	}
	public static Looper getMainThreadLooper(){
		return mainThreadLooper;
	}
	public static Handler getMainHandler(){
		return mainThreadHandler;
	}
	
	/**
	 * 退出程序时调用此方法,finish所有的activity
	 */
	public static void exit() {
		AppGlobal.TOKEN = null;
		GlobalUtils.setSessionUser(null);
		ApplicationContextUtils.getContext().stopService(new Intent(ApplicationContextUtils.getContext(),NotificationService.class));
		if(AppGlobal.dbManager!=null){
			AppGlobal.dbManager.closeDB();
		}
		ActivityUtils.clear();
	}
	
	/**
	 * 注销
	 */
	public static void logout(Context context) {
		SharedPreferencesUtils.saveData(context, "userName", "");
		SharedPreferencesUtils.saveData(context, "password", "");
		IntentUtils.startActivity(context, LoginActivity.class);
		exit();
	}
}
