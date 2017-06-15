package com.ds365.erp.wms.pda.common.utils;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

/**
 * 关于下拉刷新listview的设置
 * 
 * @param listView
 */
public class ListViewUtil {
	
	/**
	 * 上拉加载和下拉刷新
	 * @param listView
	 */
	public static void setOnPullBothListView(PullToRefreshListView listView) {
		listView.setMode(Mode.BOTH);
		ILoadingLayout layout = listView.getLoadingLayoutProxy(false, true);
		layout.setPullLabel("上拉加载更多数据..");
		layout.setReleaseLabel("放开加载更多数据");
		layout.setRefreshingLabel("正在加载中 . . .");
	}
	
	/**
	 * 下拉 刷新
	 * @param listView
	 */
	public static void setOnPullDownListView(PullToRefreshListView listView) {
		listView.setMode(Mode.PULL_FROM_START);
		ILoadingLayout layout = listView.getLoadingLayoutProxy(false, true);
		layout.setPullLabel("下拉刷新..");
		layout.setReleaseLabel("放开加载更多数据");
		layout.setRefreshingLabel("正在加载中 . . .");
	}
}
