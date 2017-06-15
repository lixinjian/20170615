package com.ds365.erp.wms.pda.model.user;

import com.ds365.commons.base.model.BaseActiveModel;

/**
 * 
 * 说明 ：修改密码 
 */
public class LoginUserCreateParamsModel extends BaseActiveModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -309670204433890187L;

	private Long employeeId;

	private Integer userTypeId;// 后期会使用

	/**
	 * 登陆账号
	 */
	private String userName;

	/**
	 * 登陆密码
	 */
	private String password;

	private String oldPassword;// 原密码

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
