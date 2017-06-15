package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;
import java.util.Date;

public class OperationInfoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2441598971948392449L;

	private Long operatorId;

	private String operatorName;

	private Date operateTime;

	public OperationInfoModel() {
	}

	public OperationInfoModel(Long operatorId, String operatorName, Date operateTime) {
		super();
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operateTime = operateTime;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

}
