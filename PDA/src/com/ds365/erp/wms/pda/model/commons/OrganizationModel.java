package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseTreeModel;
import com.ds365.commons.enums.EnumModel;

public class OrganizationModel extends BaseTreeModel<OrganizationModel> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6613714540360379885L;

	private EnumModel<Integer> type;

	private String remarks;

	public EnumModel<Integer> getType() {
		return type;
	}

	public void setType(EnumModel<Integer> type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}