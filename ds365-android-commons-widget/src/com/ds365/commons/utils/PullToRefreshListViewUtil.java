package com.ds365.commons.utils;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

/**
 * 关于下拉刷新listview的设置
 * 
 * @param listView
 */
public class PullToRefreshListViewUtil {
	
	/**
	 * 上拉加载和下拉刷新
	 * @param listView
	 */
	public static void setOnPullBothListView(PullToRefreshListView listView) {
		listView.setMode(Mode.BOTH);
		
        ILoadingLayout startLabels = listView.getLoadingLayoutProxy(true, false);    
        startLabels.setPullLabel("下拉刷新数据...");// 刚下拉时，显示的提示    
        startLabels.setRefreshingLabel("正在载入中...");// 刷新时    
        startLabels.setReleaseLabel("放开刷新数据...");// 下来达到一定距离时，显示的提示    
    
        ILoadingLayout endLabels = listView.getLoadingLayoutProxy(false, true);    
        endLabels.setPullLabel("上拉加载更多数据...");
        endLabels.setRefreshingLabel("正在加载中...");
        endLabels.setReleaseLabel("放开加载更多数据...");
	}
	
	/**
	 * 下拉 刷新
	 * @param listView
	 */
	public static void setOnPullDownListView(PullToRefreshListView listView) {
		listView.setMode(Mode.PULL_FROM_START);
        ILoadingLayout startLabels = listView.getLoadingLayoutProxy(true, false);    
        startLabels.setPullLabel("下拉刷新数据...");// 刚下拉时，显示的提示    
        startLabels.setRefreshingLabel("正在载入中...");// 刷新时    
        startLabels.setReleaseLabel("放开刷新数据...");// 下来达到一定距离时，显示的提示    
	}
}
