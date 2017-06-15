package com.ds365.erp.wms.pda.view.common.activity;

/**
 * 
 * 说明 ： 不分页的activity继承此类
 * @author Li xinJian
 * @date 2016年8月10日
 */
public abstract class BasePdaListActivity extends BasePdaActivity {

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
}
