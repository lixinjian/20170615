package com.ds365.appupdate;

import android.os.Environment;

public class AppUpdateConstants {

	public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/DS365/APK/";
	
	public static final String CHECKAPPUPDATEURL = "/erp-bms-web-main/app/updateData/searchReleaseByParams";
}
