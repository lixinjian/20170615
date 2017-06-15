package com.ds365.commons.base.model;

import java.io.Serializable;

public class BaseActiveModel extends BaseOperationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -207252152897264143L;

	protected Boolean activeFlag;

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}
