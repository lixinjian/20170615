package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

public class DeliverLineModel extends BaseActiveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8356103591090844504L;

	private String code;

	private String name;

	@Deprecated
	private WarehouseModel warehouse;

	private OrganizationModel deliverOrganization;

	private String helpCode;

	private String remark;

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

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public OrganizationModel getDeliverOrganization() {
		return deliverOrganization;
	}

	public void setDeliverOrganization(OrganizationModel deliverOrganization) {
		this.deliverOrganization = deliverOrganization;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
