package com.ds365.erp.wms.pda.common.utils;

import android.content.Context;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.TokenTimeOutListener;
import com.ds365.commons.utils.ChangeIPSharedPreference;
import com.ds365.commons.utils.SharedPreferencesUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaApplication;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.enums.MessageFunctionTypeEnum;
import com.ds365.erp.wms.pda.model.user.SessionUser;
import com.ds365.erp.wms.pda.view.message.activity.MessageDetailActivity;

public class GlobalUtils {

	private static SessionUser sessionUser;
	
	
	public static void init(Context context) {
		
		AppGlobal.activeFlag =PdaConstants.activeFlag;
		String newHost=ChangeIPSharedPreference.getChangeHost(context);
		String newUpdate = ChangeIPSharedPreference.getUpdateHost(context);
		String newMessage = ChangeIPSharedPreference.getMessageHost(context);
		if(!newHost.equals("")){
			ConstantUrl.ip = newHost;
		}
		if(!newUpdate.equals("")){
			ConstantUrl.host_update = newUpdate;
		}
		if(!newMessage.equals("")){
			ConstantUrl.host_message = newMessage;
		}
		ConstantUrl.init();
		
		
		AppGlobal.ISOPENTOKEN = true;
		AppGlobal.tokenTimeOutListener = new TokenTimeOutListener() {

			@Override
			public void onTokenTimeOut(Context context) {
				T.showLong(context,"用户登录信息过期，请重新登录！");
				PdaApplication.logout(context);
			}
		};

		/*
		 * 消息配置
		 */
		AppGlobal.notificationId = SharedPreferencesUtils.getData(context, AppConstants.NOTIFICATIONID_KEY, 0);
		// 设置消息跳转页面
		AppGlobal.messageDetailClass = MessageDetailActivity.class;

		AppGlobal.initMessageFunctionTypeIntentMap(MessageFunctionTypeEnum.class);
		
	}
	/**
	 * 获取用户信息
	 */
	public static SessionUser getSessionUser() {
		return sessionUser;
	}
	
	/**
	 * 保存用户信息
	 */
	public static void setSessionUser(SessionUser sessionUser) {
		GlobalUtils.sessionUser = sessionUser;
	}
}
