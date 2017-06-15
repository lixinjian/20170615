package com.ds365.erp.wms.pda.view.common.activity;

import com.ds365.erp.wms.pda.common.headtitlebar.SubHeadTitleBar.ButtonClickListener;

public abstract class BasePdaMessageActivity extends BasePdaActivity {
	/**
	 * 用来标记结束页面时是否跳转
	 * */
	protected boolean intentMainFlag = false;

	@Override
	protected void onResume() {
		super.onResume();
		checkUserLogin();
	}
	
	/**
	 *配置需要跳转的activity,若用户信息为空时跳转
	 * */
	protected void setHeadLeft(ButtonClickListener leftListener){
		if(headView!=null){
			headView.setListener(leftListener);
		}
	}
	
	protected abstract void checkUserLogin();
}
