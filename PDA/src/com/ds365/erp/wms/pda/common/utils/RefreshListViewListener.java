package com.ds365.erp.wms.pda.common.utils;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.widget.ListView;

/**
 * 分页listView的监听
 */
public class RefreshListViewListener implements OnRefreshListener2<ListView>{

	private PullToRefreshListView listView;
	private OnPullDownRefreshListener onPullDownRefreshListener;
	private OnPullUpRefreshListener onPullUpRefreshListener;
	
	public RefreshListViewListener(PullToRefreshListView listView,OnPullDownRefreshListener onPullDownRefreshListener){
		this.listView = listView;
		this.onPullDownRefreshListener = onPullDownRefreshListener;
	}
	
	public RefreshListViewListener(PullToRefreshListView listView,OnPullUpRefreshListener onPullUpRefreshListener){
		this.listView = listView;
		this.onPullUpRefreshListener = onPullUpRefreshListener;
	}
	
    public interface OnPullDownRefreshListener{
        public void onPullDown();
    }
    
    public interface OnPullUpRefreshListener{
    	public void onPullDown();
    	public void onPullUp();
    }

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		if(onPullDownRefreshListener!=null){
			onPullDownRefreshListener.onPullDown();
		}
		if(onPullUpRefreshListener!=null){
			onPullUpRefreshListener.onPullDown();
		}
		refreshComplete();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		if(onPullUpRefreshListener!=null){
			onPullUpRefreshListener.onPullUp();
		}
		refreshComplete();
	}
	
	private void refreshComplete(){
		listView.postDelayed(new Runnable() {
			@Override
			public void run() {
				listView.onRefreshComplete();
			}
		}, 500);
	}
}
