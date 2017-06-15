package com.ds365.commons.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 *  这里是关于应用管理器的操作类
 */
public class PackageUtil {

    /**
     * 将构造私有化
     */
    private PackageUtil(){}

    /**
     * 获取当前应用的名称
     *
     * @return
     */
    public static String getAppName(Context context){
        String appName=null;
        PackageManager pm= context.getPackageManager();
        try {
            PackageInfo info=pm.getPackageInfo(context.getPackageName(),0);
            int labelRes = info.applicationInfo.labelRes;
            appName=context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
           L.e(e.toString());
        }
        return appName;
    }

    /**
     * 获取当前应用的包名
     *
     * @return
     */
    public static String getAppPackageName(Context context){
        return context.getPackageName();
    }

    /**
     * 获取当前应用的版本名称
     *
     * @return
     */
    public static String getAppVersionName(Context context){
    	PackageInfo packageInfo = getAppPackageInfo(context);
    	if(packageInfo!=null){
    		return packageInfo.versionName;
    	}else{
    		return null;
    	}
    }
    
    /**
     * 获取当前应用的版本号
     *
     * @return
     */
    public static int getAppVersionCode(Context context){
    	PackageInfo packageInfo = getAppPackageInfo(context);
    	if(packageInfo!=null){
    		return packageInfo.versionCode;
    	}else{
    		return 1;
    	}
    }
    
    private static PackageInfo getAppPackageInfo(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo;
		} catch (NameNotFoundException e) {
			 L.e(e.toString());
		}
        return null;
    }

}
