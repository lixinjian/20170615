package com.ds365.appupdate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.KeyEvent;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.AppConstants;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.L;
import com.ds365.commons.utils.PackageUtil;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class AppUpdateService {

	private AppCheckUpdateParamsModel appCheckUpdateParams;
	private AppUpdateCallback callback;
	private Context context;

	public AppUpdateService(AppCheckUpdateParamsModel appCheckUpdateParams){
		this.appCheckUpdateParams = appCheckUpdateParams;
		this.callback = appCheckUpdateParams.getCallback();
		this.context = appCheckUpdateParams.getContext();
		AppUpdateGlobal.saveFileName = appCheckUpdateParams.getDownloadApkName();
	}

	public void checkUpdate(String updateHost){
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl("http://"+updateHost+AppUpdateConstants.CHECKAPPUPDATEURL+"?"+AppConstants.PLATFORMTYPE_NAME+"="+AppConstants.PLATFORMTYPE_KEY+"&appCode="+appCheckUpdateParams.getSystemCode());
		JsonParser<JsonResult<UpdateDataEntity>> jsonParser = new JsonParser<JsonResult<UpdateDataEntity>>(){};
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<UpdateDataEntity>>(jsonParser,new AbstractServiceCallBack<UpdateDataEntity>(context){

			@Override
			public void doSuccess(UpdateDataEntity data) {
				if(data==null){
					callback.onVersionRequestFail("检查版本接口返回数据非法!");
					return;
				}
				L.i("当前应用版本"+PackageUtil.getAppVersionCode(context));
				if(data.getUpdateCode()>PackageUtil.getAppVersionCode(context)){
					AppUpdateGlobal.UPDATE_URL = data.getUrl();
					if(data.getForceUpdateFlag()){
						forceUpdate(data.getDescription());
					}else{
						update(data.getDescription());
					}
				}else{
					callback.onVersionRequestSuccess();
				}
			}
			@Override
			public void doFaile(String str) {
				callback.onVersionRequestFail(str);
			}
		}){});
	}

	/***
	 * 版本升级 弹出
	 */
	private void update(String description) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("版本更新");

		builder.setMessage(description);
		builder.setPositiveButton("开始更新", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				startDownLoadService();
			}
		});
		builder.setNegativeButton("暂不更新", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				callback.onUpdateRefuseCallBack();
			}
		});

		builder.create().show();
	}
	/***
	 * 开启下载服务
	 */
	private void startDownLoadService(){
		AppUpdateGlobal.setDownload(true);
		Intent it = new Intent(context,DownloadService.class);
		//防止用户启动下载服务后，未安装新应用，再次启动下载服务时需要先关闭下载服务
		context.stopService(it);
		
		context.startService(it);
		//启动下载服务后要关闭页面，以免用户下载完成后选择不安装跳过强制更新
		Activity activity = (Activity)context;
		activity.finish();
	}
	
	/***
	 * 版本升级 弹出
	 * <p/>
	 * 强更
	 */
	private void forceUpdate(String description) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("版本更新");

		builder.setMessage(description);
		builder.setPositiveButton("开始更新", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				startDownLoadService();
			}
		});
		builder.setNegativeButton("退出应用", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				callback.onForceUpdateRefuseCallBack();
			}
		});
		builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_SEARCH) {
					return true;
				} else {
					return true; // 默认返回 false，这里false不能屏蔽返回键，改成true就可以了
				}
			}
		});
		builder.create().show();
	}
}
