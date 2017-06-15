package com.ds365.erp.wms.pda.view.common.activity;

import com.ds365.commons.utils.ActivityUtils;
import com.ds365.erp.wms.pda.common.headtitlebar.SubHeadTitleBar;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public abstract class BasePdaActivity extends Activity  {
	
	/**
	 * 头部导航栏
	 */
	protected SubHeadTitleBar headView;
	protected Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		// 去除标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 禁止横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//防止软键盘遮挡输入框
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		//引用layout布局
		setContentView(getContentViewId());
		ActivityUtils.add(this);
		
		initNavigation();
		initActivityView();
		setListener();
	}

	/**
	 * 实例化头部 0为不显示 其他为String id 或者 drawableid parentId bu能為0
	 */
	protected void initHeadView(int headViewId, int titleId) {
		headView = (SubHeadTitleBar) findViewById(headViewId);
		if (headView != null) {
			headView.setTitle(titleId);
		}
	}
	
	/**
	 * 实例化头部 0为不显示 其他为String id 或者 drawableid parentId bu能為0
	 */
	protected void initHeadView(int headViewId, String title) {
		headView = (SubHeadTitleBar) findViewById(headViewId);
		if (headView != null) {
			headView.setTitle(title);
		}
	}
	
	/**
	 * 关于Activity的界面填充的抽象方法，需要子类必须实现
	 * 
	 */
	protected abstract void initActivityView();

	/**
	 * 关于Activity的头部导航栏填充的抽象方法，需要子类必须实现
	 */
	protected abstract void initNavigation();
	
	/**
	 * 此方法内放置Activity中layout布局
	 * @return 返回layout的资源ID
	 */
	protected abstract int getContentViewId();
	
	/**
	 * 设置Activity中的点击监听
	 */
	protected abstract void setListener();

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		ActivityUtils.remove(this);
		super.onDestroy();
		
	}
	
	
	
}
