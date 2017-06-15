package com.ds365.commons;


public class AppConstants {
	
	//public static final String CAPTCHA_TOKEN_KEY_APP="captchaTokenKey";//验证码的token(app)
	
	//public static final String CAPTCHA_KEY_PREFIX_APP="captchaCode_";//APP 验证码key前缀
	
	public static final String requestSourceParamsName="requestSource";
	
	public static final String requestSourceValue_app="app";
	
	public  static final String captchTokenParamsName="captchaTokenKey";
	
	public  static final String tokenParamsName="token";
	/**
	 * 清空listView中的数据
	 */
	public static final int CLEAR_LISTVIEW_YES = 1;
	
	/**
	 * 不清空listView中的数据
	 */
	public static final int CLEAR_LISTVIEW_NO = 2;
	
	/**
	 * android 请求平台类型
	 */
	public static final String PLATFORMTYPE_KEY = "1";
	/**
	 * android 请求平台类型
	 */
	public static final String PLATFORMTYPE_NAME = "platformTypeId";
	
	
	/**
	 * websocket重连次数
	 * */
	public static final int WEBSOCKET_RECONNECT_COUNT = 3;
	
	public static final String HEARTBEAT_MESSAGE = "y";
	
	/**
	 * url参数的start,limit.
	 */
	public static final int START = 0;
	public static final int LIMIT = 25;
	public static final int CANCLE_DIALOG = 0;
	public static final int CREATE_DIALOG = 1;
	
	public static final String NOTIFICATIONID_KEY = "notificationId";
	
	//用来表示从消息页面跳转至activity
	public static final String Intent_FROMMESSAGE_KEY = "intent_frommessage";
	
}
