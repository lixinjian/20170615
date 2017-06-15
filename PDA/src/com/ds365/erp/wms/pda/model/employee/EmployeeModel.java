package com.ds365.erp.wms.pda.model.employee;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.commons.PositionModel;
import com.ds365.erp.wms.pda.model.commons.RegionModel;

public class EmployeeModel extends BaseActiveModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -948613546477002577L;

	private String code;

	private String name;

	private String namespell;

	private String identityCard;

	private String phone;

	private String mobile;

	private String email;

	private String address;

	private String remarks;

	private EnumModel<Integer> employeeType;

	private RegionModel region;

	private OrganizationModel organization;

	private PositionModel position;

	private EnumModel<Integer> sex;

	public EmployeeModel() {
		super();
	}

	public EmployeeModel(Long id) {
		super();
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespell() {
		return namespell;
	}

	public void setNamespell(String namespell) {
		this.namespell = namespell;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public EnumModel<Integer> getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EnumModel<Integer> employeeType) {
		this.employeeType = employeeType;
	}

	public RegionModel getRegion() {
		return region;
	}

	public void setRegion(RegionModel region) {
		this.region = region;
	}

	public OrganizationModel getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
	}

	public PositionModel getPosition() {
		return position;
	}

	public void setPosition(PositionModel position) {
		this.position = position;
	}

	public EnumModel<Integer> getSex() {
		return sex;
	}

	public void setSex(EnumModel<Integer> sex) {
		this.sex = sex;
	}

}