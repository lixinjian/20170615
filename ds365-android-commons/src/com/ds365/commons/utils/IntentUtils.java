package com.ds365.commons.utils;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

public class IntentUtils {

	public static final int TAKEPICTURE_CODE = 100;
	public static File file;

	private IntentUtils() {}

	public static<T extends Activity> void startActivity(Context context, Class<T> target) {
		startActivityForMap(context, target, null, null);
		
	}

	/*public static void startActivity(Context context, Class target, String key,
			String value) {
		Intent intent = new Intent(context, target);
		intent.putExtra(key, value);
		context.startActivity(intent);
	}*/

	public static<T extends Activity,V extends Serializable> void startActivity(Context context, Class<T> target, String key,
			V value, Integer flag) {
		Map<String,V> params=new HashMap<String, V>();
		params.put(key, value);
		
		startActivityForMap(context,target,params, flag);
	}

	/*public static void startActivity(Context context, Class target, String key,
			boolean value) {
		Intent intent = new Intent(context, target);
		intent.putExtra(key, value);
		context.startActivity(intent);
	}*/
	/**
	 * 启动新的activity，并以序列化方式传递
	 * @param context
	 * @param target
	 * @param key
	 * @param value
	 */
	public static<T extends Activity> void startActivityForSeria(Context context, Class<T> target, String key,Serializable value,Integer flag) {
		Intent intent = new Intent(context, target);
		intent.putExtra(key, value);
		if(flag!=null)
			intent.setFlags(flag);
		context.startActivity(intent);
	}
	
	public static  <V extends Serializable> void startActivityForMap(Context context, Class target,Map<String,V> params,Integer flag) {
		Intent intent = new Intent(context, target);
		if(params!=null){
			for(Map.Entry<String,V>   param:params.entrySet()){
				
				intent.putExtra(param.getKey(), param.getValue());
			}
			
		}
		
		if(flag!=null)
			intent.setFlags(flag);
		context.startActivity(intent);
	}


	public static<T extends Activity> void startActivityBySeriaForResult(Activity activity, Class<T> target, String key,Serializable value,Integer flag,int requestCode) {
		Intent intent = new Intent(activity, target);
		intent.putExtra(key, value);
		if(flag!=null)
			intent.setFlags(flag);
		activity.startActivityForResult(intent,requestCode);
	}
	
	public static  <V extends Serializable> void startActivityByMapForResult(Activity activity, Class target,Map<String,V> params,Integer flag,int requestCode) {
		Intent intent = new Intent(activity, target);
		if(params!=null){
			for(Map.Entry<String,V>   param:params.entrySet()){
				
				intent.putExtra(param.getKey(), param.getValue());
			}
			
		}
		
		if(flag!=null)
			intent.setFlags(flag);
		activity.startActivityForResult(intent,requestCode);
	}

	

	public static void startBorwser(Context context, Uri uri) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(uri);
		context.startActivity(intent);
	}

	public static<T extends Activity> void startActivityForResult(Activity activity, Class<T> target,int requestCode) {
		startActivityByMapForResult(activity, target, null, null, requestCode);
	}

	public static<T extends Activity> void startActivityBySerialForResult(Activity activity,
			Class<T> target, String key, Serializable value, int requestCode) {
		
		
		startActivityBySeriaForResult(activity, target, key, value, null, requestCode);
	}
	
	
	
	public static void callPhone(Activity context, String phone) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel://" + phone));
		context.startActivity(intent);
	}

	public static void sendToSony(Context context, String number) {
		boolean isShow = true;
		if ("0".equals(number)) {
			isShow = false;
		}
		Intent localIntent = new Intent();
		localIntent
				.putExtra(
						"com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE",
						isShow);// 是否显示
		localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
		localIntent.putExtra(
				"com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME",
				getLaunchActivityName(context));// 启动页
		localIntent.putExtra(
				"com.sonyericsson.home.intent.extra.badge.MESSAGE", number);// 数字
		localIntent.putExtra(
				"com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME",
				context.getPackageName());// 包名
		context.sendBroadcast(localIntent);
	}

	public static void sendToSamsumg(Context context, String number) {
		Intent localIntent = new Intent(
				"android.intent.action.BADGE_COUNT_UPDATE");
		localIntent.putExtra("badge_count", number);// 数字
		localIntent.putExtra("badge_count_package_name",
				context.getPackageName());// 包名
		localIntent.putExtra("badge_count_class_name",
				getLaunchActivityName(context)); // 启动页
		context.sendBroadcast(localIntent);
	}

	public static String getLaunchActivityName(Context context){
		PackageManager localPackageManager = context.getPackageManager();
		Intent localIntent = new Intent("android.intent.action.MAIN");
		localIntent.addCategory("android.intent.category.LAUNCHER");
		try{
			Iterator<ResolveInfo> localIterator = localPackageManager
					.queryIntentActivities(localIntent, 0).iterator();
			while (localIterator.hasNext()){
				ResolveInfo localResolveInfo = localIterator.next();
				if (!localResolveInfo.activityInfo.applicationInfo.packageName
						.equalsIgnoreCase(context.getPackageName()))
					continue;
				String str = localResolveInfo.activityInfo.name;
				return str;
			}
		} catch (Exception localException){
			localException.printStackTrace();
		}
		return null;
	}

	public static void playVideo(Context context, String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(path), "video/*");
		context.startActivity(intent);
	}

	public static boolean currentTopActivity(Context context, String packagename) {
		if (context != null) {
			ActivityManager manager = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			RunningTaskInfo info = manager.getRunningTasks(1).get(0);
			String shortClassName = info.topActivity.getPackageName();
			if (packagename == null) {
				return false;
			}
			if (shortClassName == null) {
				return false;
			}
			return packagename.equals(shortClassName);
		}
		return false;
	}

}
