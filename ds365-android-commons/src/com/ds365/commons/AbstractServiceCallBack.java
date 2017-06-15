package com.ds365.commons;

import android.content.Context;

import com.ds365.commons.utils.T;

public abstract class AbstractServiceCallBack<E> implements ServiceCallBack<E>{
	
	private  Context context;
	
	/*public AbstractServiceCallBack() {
		super();
	}*/
	
	public AbstractServiceCallBack(Context context) {
		this.context = context;
	}
	
	public void doFaile(String str){
		T.showShort(context, str);
	}

	public  Context getContext() {
		return context;
	}

}
