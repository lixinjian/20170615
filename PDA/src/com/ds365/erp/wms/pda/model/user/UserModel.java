package com.ds365.erp.wms.pda.model.user;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.commons.enums.EnumModel;

/**
 * 
 * 说明 ： PDA登录用户信息实体类
 * 
 * @author Li xinJian
 * @date 2016年8月21日
 */
public class UserModel extends BaseActiveModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7565537600274040127L;

	private EnumModel<Integer> userType;

	private Long employeeId;

	private String employeeName;

	private String organizationCode;

	private String password;

	private String userName;

	private Long userId;

	private Long organizationId;

	private String organizationName;

	private Long warehouseId;

	private String warehouseName;

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public EnumModel<Integer> getUserType() {
		return userType;
	}

	public void setUserType(EnumModel<Integer> userType) {
		this.userType = userType;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
