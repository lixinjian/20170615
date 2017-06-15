package com.ds365.appupdate;

public class AppUpdateGlobal {
	
	public static boolean isDownload = false;
	
	public static String UPDATE_URL = "";
	/* 下载安装名称 */
	public static String saveFileName = "ds365.apk";
	
	public static boolean isDownload() {
		return isDownload;
	}

	public static void setDownload(boolean isDownload1) {
		isDownload = isDownload1;
	}
}
