package com.ds365.commons.activity;

import com.ds365.commons.widget.NavigationView.HeadMenuClickListener;

public abstract class BaseMessageActivity extends BaseActivity {

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
	protected void setHeadLeft(HeadMenuClickListener leftListener){
		if(headView!=null){
			headView.setLeftListener(leftListener);
		}
	}
	
	protected abstract void checkUserLogin();

}
