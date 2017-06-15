package com.ds365.commons.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ds365.commons.utils.ActivityUtils;
import com.ds365.commons.utils.NetUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.NavigationView;
import com.ds365.commons.widget.NavigationView.HeadMenuClickListener;
import com.ds365.commons.widget.R;

/**
 * 除去主页外的�?有activity都应该继承该基类�? 用于记录打开的activity 已做�?后的处理，或者一些数据的处理
 * 
 */
public abstract class BaseActivity extends Activity {

	protected NavigationView headView;
	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(getContentViewId());
		context = this;
		ActivityUtils.add(this);
		initNavigation();
		initActivityView();
		setListener();
	}

	/**
	 * 实例化头�? 0为不显示 其他为String id 或�?? drawableid parentId bu能為0
	 */
	protected void initHeadView(int parentId,int middleTextId) {
		initHeadView(parentId,getResources().getString(middleTextId));
	}
	
	protected void initHeadView(int parentId,String middleText) {
		headView = (NavigationView) findViewById(parentId);
		headView.setVisibility(View.VISIBLE);
		if (headView != null) {
			headView.setTitle(middleText);
		}
	}
	
	public void initHeadView(int parentId,int middleTextId,String rightText) {
		initHeadView(parentId,getResources().getString(middleTextId),rightText,true);
	}
	
	public void initHeadView(int parentId,String middleText,String rightText,boolean rightClickable) {
		headView = (NavigationView) findViewById(parentId);
		headView.setVisibility(View.VISIBLE);
		if (headView != null) {
			headView.setTitle(middleText);
			headView.setRightText(rightText,rightClickable);
		}
	}
	
	public void initHeadView(int parentId,int middleText,String rightText,boolean rightClickable) {
		initHeadView(parentId,getResources().getString(middleText),rightText,rightClickable);
	}
	
	public void initHeadView(int parentId,String middleText,String rightText) {
		initHeadView(parentId,middleText,rightText,true);
	}
	
	public void setHeadRight(HeadMenuClickListener rightListener){
		if(headView!=null){
			headView.setRightListener(rightListener);
		}
	}
	/**
	 * 初始化头�?
	 **/
	protected abstract void initNavigation();

	/**
	 * 设置layout资源文件
	 * @return
	 */
	protected abstract int getContentViewId();

	/**
	 * 初始化页面中的控�?
	 */
	protected abstract void initActivityView();

	/**
	 * 设置页面的监听事�?
	 */
	protected abstract void setListener();

	/**
	 * 判断网络是否连接
	 * @return
	 */
	protected boolean isNetvaiable() {
		if (!NetUtils.isConnected(context)
				|| (NetUtils.isWifi(context) && !NetUtils
						.isWifiConnected(context))) {
			T.showShort(context,
					context.getResources().getString(R.string.check_net));
			return false;
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityUtils.remove(this);
	}
}
