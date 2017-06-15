package com.ds365.commons;

import android.content.Context;

/** * @author yxz  
 * @date 创建时间：2016-9-30 下午6:30:58 
 * @version 1.0  
 */
public interface ServiceCallBack <E>{
	void doSuccess(E data);
	
	public void doFaile(String str);
	
	public Context getContext();
}
