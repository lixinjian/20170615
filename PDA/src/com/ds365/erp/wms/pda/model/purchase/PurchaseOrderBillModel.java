package com.ds365.erp.wms.pda.model.purchase;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseOrderBillModel extends BasePurchaseBillModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5249653092001503506L;

	private EnumModel<Integer> closeType;

	private String closeReason;

	private EmployeeModel closer;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date closeTime;

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public EmployeeModel getCloser() {
		return closer;
	}

	public void setCloser(EmployeeModel closer) {
		this.closer = closer;
	}

	public EnumModel<Integer> getCloseType() {
		return closeType;
	}

	public void setCloseType(EnumModel<Integer> closeType) {
		this.closeType = closeType;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

}