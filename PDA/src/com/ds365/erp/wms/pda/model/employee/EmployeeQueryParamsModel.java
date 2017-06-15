package com.ds365.erp.wms.pda.model.employee;

import com.ds365.commons.base.model.BasePageQueryParamsModel;

/**
 * 
 * 说明 ：关于员工的查询model 
 */
public class EmployeeQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1221605024238698455L;

	private Long organizationId;

	private Long regionId;

	private Integer employeeTypeId;

	private String name;

	private String email;

	private String phone;

	private String identityCard;

	private Long positionId;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Integer getEmployeeTypeId() {
		return employeeTypeId;
	}

	public void setEmployeeTypeId(Integer employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

}
