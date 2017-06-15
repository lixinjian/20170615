package com.ds365.commons.activity;

import com.ds365.commons.AppConstants;

/**
 * 
 * 说明 ：分页的activity�?继承 
 * @author Li xinJian
 * @date 2016�?9�?8�?
 */
public abstract class BasePageActivity extends BaseActivity {

	protected int start; //控制分页数据�?始的条数
	protected int limit; //控制分页数据�?始的条数
	/**
	 * 设置第一页的start,limit
	 */
	protected void setFirstPage(){ 
		start = 0;
		//params.getParams().put("start", String.valueOf(start));
		limit = AppConstants.LIMIT;
		//params.getParams().put("limit", String.valueOf(limit));
	}
	/**
	 * �?要自定义每页请求长度limit
	 */
	protected void setFirstPage(int limit){ 
		start = 0;
		//params.getParams().put("start", String.valueOf(start));
		this.limit = limit;
		//params.getParams().put("limit", String.valueOf(limit));
	}
	
	/**
	 * 设置下一页的start,limit
	 */
	public void setNextPage(){
		start += limit;
		//params.getParams().put("start", String.valueOf(start));
		//params.getParams().put("limit",  String.valueOf(limit));
	}

}
