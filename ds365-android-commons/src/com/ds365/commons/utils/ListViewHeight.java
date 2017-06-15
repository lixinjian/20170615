package com.ds365.commons.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author 作者 yxz
 * @date 创建时间：2015-12-21 下午11:08:56
 */

public class ListViewHeight {

	/**
	 * 重新计算listview高度，但listview的每一项需为LinearLayout
	 * 
	 * @param listView
	 */
	public static void setListViewHeight(ListView listView, boolean Clickable) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		if (!Clickable) {
			listView.setClickable(false);
			listView.setFocusable(false);
			listView.setPressed(false);
			listView.setEnabled(false);
		}
	}
	/**
	 * 重新计算GridView高度
	 * 
	 * @param listView
	 */
	public static void setGridViewHeightBasedOnChildren(GridView listView,int addHeight) {  
	    // 获取listview的adapter  
	       ListAdapter listAdapter = listView.getAdapter();  
	       if (listAdapter == null) {  
	           return;  
	       }  
	       // 固定列宽，有多少列  
	       int col = 3;// listView.getNumColumns();  
	       int totalHeight = 0;  
	       // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，  
	       // listAdapter.getCount()小于等于8时计算两次高度相加  
	       for (int i = 0; i < listAdapter.getCount(); i += col) {  
	        // 获取listview的每一个item  
	           View listItem = listAdapter.getView(i, null, listView);  
	           listItem.measure(0, 0);  
	           // 获取item的高度和  
	           totalHeight += listItem.getMeasuredHeight();  
	       }  
	  
	       // 获取listview的布局参数  
	       ViewGroup.LayoutParams params = listView.getLayoutParams();  
	       // 设置高度  
	       params.height = totalHeight+addHeight;  
	       // 设置margin  
	       ((MarginLayoutParams) params).setMargins(0, 5, 0, 10);  
	       // 设置参数  
	       listView.setLayoutParams(params);  
	   }  

}
