package com.ds365.commons.utils;

import android.content.Context;

public class ChangeIPSharedPreference {
	
	private static final String CHANGE_HOST = "changehost";
	private static final String CHANGE_MESSAGEHOST = "changemessage";
	private static final String CHANGE_UPDATEHOST = "changeupdate";
	
	public static void saveIP(Context context,String newHost,String newUpdateHost,String newMessageHost){
		SharedPreferencesUtils.saveData(context,CHANGE_HOST,newHost);
		SharedPreferencesUtils.saveData(context,CHANGE_UPDATEHOST,newUpdateHost);
		SharedPreferencesUtils.saveData(context,CHANGE_MESSAGEHOST,newMessageHost);
	}
	
//	public static String getIP(Context context){
//		String newIp = "";
//		String ip= SharedPreferencesUtils.getData(context,CHANGEIP, "");
//		String port = SharedPreferencesUtils.getData(context,CHANGEPORT, "");
//		if(!ip.equals("")&&!port.equals("")){
//			newIp = "http://"+ip+":"+port+"/";
//		}
//		return newIp;
//	}
	public static String getUpdateHost(Context context){
		String host= SharedPreferencesUtils.getData(context,CHANGE_UPDATEHOST, "");
		return host;
	}
	
	public static String getMessageHost(Context context){
		String host = SharedPreferencesUtils.getData(context,CHANGE_MESSAGEHOST, "");
		return host;
	}
	
	public static String getChangeHost(Context context){
		String ip= SharedPreferencesUtils.getData(context,CHANGE_HOST, "");
		return ip;
	}
}
