package com.ds365.appupdate;

public interface AppUpdateCallback {
	void onUpdateRefuseCallBack();
	void onForceUpdateRefuseCallBack();
	void onVersionRequestSuccess();
	void onVersionRequestFail(String str);
}
