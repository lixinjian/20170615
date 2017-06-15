package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseTreeModel;

public class RegionModel extends BaseTreeModel<RegionModel> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4953858694887944399L;

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
