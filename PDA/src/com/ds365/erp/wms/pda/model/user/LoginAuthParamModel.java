package com.ds365.erp.wms.pda.model.user;

import java.io.Serializable;

public class LoginAuthParamModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4314459242411206867L;

	/**
	 * 登陆账号
	 */
	private String userName;

	/**
	 * 登陆密码
	 */
	private String password;

	private Long organizationId;

	private Integer organizationTypeId;

	private String captchaCode;

	private String captchaTokenKey;

	private Long warehouseId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getOrganizationTypeId() {
		return organizationTypeId;
	}

	public void setOrganizationTypeId(Integer organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getCaptchaTokenKey() {
		return captchaTokenKey;
	}

	public void setCaptchaTokenKey(String captchaTokenKey) {
		this.captchaTokenKey = captchaTokenKey;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

}
