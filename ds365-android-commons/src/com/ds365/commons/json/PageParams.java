package com.ds365.commons.json;

import java.io.Serializable;

/**
 * 
 * @Title: PageVo.java
 * @Package com.ds365.erp.wms.common.web.vo
 * @Description: (pageVO)
 * @author Gavin
 * @date 2015-1-23 下午4:08:05
 * @version V1.0
 */
public class PageParams implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8715602092889027725L;

	public PageParams(){
		
	
	}
	public PageParams(int start,int limit){
		this.start=start;
		this.limit=limit;
	
	}
	/**
	 * 起始页
	 */
	private int start;
	
	/**
	 * 页面大小
	 */
	private int limit;

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	
	
	
}
