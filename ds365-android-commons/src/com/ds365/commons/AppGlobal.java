package com.ds365.commons;

import java.util.Map;

import com.ds365.commons.message.CheckUserLoginListener;
import com.ds365.commons.message.MessageReceivedListener;
import com.ds365.commons.message.database.DBManager;
import com.ds365.commons.utils.EnumUtils;


public class AppGlobal {
	
	public static boolean activeFlag;
	public static String TOKEN = "";
	public static boolean ISOPENTOKEN = false;
	public static TokenTimeOutListener tokenTimeOutListener;
	//消息服务IP
	public static String appServiceUrl = "";
	//数据库操作类
	public static DBManager dbManager;
	//收到消息回调
	public static MessageReceivedListener messageReceivedListener;
	public static int notificationId;
	
	public static Class messageDetailClass;
	public static Map<Integer,Class> messageFunctionTypeIntentMap;
	
	public static  void initMessageFunctionTypeIntentMap(Class functionType){
		messageFunctionTypeIntentMap = EnumUtils.converMessageFunctionTypeToMap(functionType);
	}
	public static CheckUserLoginListener checkUserLoginListener;
}
		