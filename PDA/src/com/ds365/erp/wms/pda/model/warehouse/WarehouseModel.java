package com.ds365.erp.wms.pda.model.warehouse;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.commons.RegionModel;

public class WarehouseModel extends BaseActiveModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7942367062451252808L;

	private String code;

	private String name;

	private String helpCode;

	private RegionModel region;

	private EnumModel<Integer> type;

	private String address;

	private String contactor;

	private String phoneNo;

	private String mobileNo;

	private String email;

	private String description;

	private String remark;

	private boolean shelfManageFlag;

	private OrganizationModel organization;

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

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public RegionModel getRegion() {
		return region;
	}

	public void setRegion(RegionModel region) {
		this.region = region;
	}

	public EnumModel<Integer> getType() {
		return type;
	}

	public void setType(EnumModel<Integer> type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isShelfManageFlag() {
		return shelfManageFlag;
	}

	public void setShelfManageFlag(boolean shelfManageFlag) {
		this.shelfManageFlag = shelfManageFlag;
	}

	public OrganizationModel getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
	}

}
