package com.ds365.erp.wms.pda.model.user;

import java.io.Serializable;

import com.ds365.commons.BaseIdModel;

public class VerifyCodeModel extends BaseIdModel<Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 911999371179646986L;
	private String token;
//	private String imgVerifyCodeUrl;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
//	public String getImgVerifyCodeUrl() {
//		return imgVerifyCodeUrl;
//	}
//	public void setImgVerifyCodeUrl(String imgVerifyCodeUrl) {
//		this.imgVerifyCodeUrl = imgVerifyCodeUrl;
//	}
	
}
