package com.ds365.commons.utils;

import java.util.List;

import android.content.Context;

public abstract class CommonSelectAdapter<T> extends CommonAdapter<T> {

	public int selectPositon = -1;
	
	public CommonSelectAdapter(Context context, List<T> list) {
		super(context, list);
	}

	 public void changeSelected(int positon){ //刷新方法
	     if(positon != selectPositon){
	    	 selectPositon = positon;
	    	 notifyDataSetChanged();
	     }
	 }
}
