package com.ds365.appupdate;

import android.content.Context;

public class AppCheckUpdateParamsModel {

	private Context context;
	private String downloadApkName;
	private String systemCode;
	private AppUpdateCallback callback;
	
	public AppCheckUpdateParamsModel(Context context,String downloadApkName,String systemCode,AppUpdateCallback callback){
		this.callback = callback;
		this.context = context;
		this.downloadApkName = downloadApkName;
		this.systemCode = systemCode;
	}
	
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public String getDownloadApkName() {
		return downloadApkName;
	}
	public void setDownloadApkName(String downloadApkName) {
		this.downloadApkName = downloadApkName;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public AppUpdateCallback getCallback() {
		return callback;
	}
	public void setCallback(AppUpdateCallback callback) {
		this.callback = callback;
	}
	
}
