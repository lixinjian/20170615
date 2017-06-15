package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

public class LineModel extends BaseActiveModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6237309474145108675L;

	private String code;

	private String name;

	private WarehouseModel warehouse;

	private OrganizationModel deliverOrganization;

	private String helpCode;

	private String remark;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public void setDeliverOrganization(OrganizationModel deliverOrganization) {
		this.deliverOrganization = deliverOrganization;
	}

	public OrganizationModel getDeliverOrganization() {
		return this.deliverOrganization;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getHelpCode() {
		return this.helpCode;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

}