package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseTreeModel;

public class PositionModel extends BaseTreeModel<PositionModel> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3098330206195534303L;

	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}