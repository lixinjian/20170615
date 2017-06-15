package com.ds365.erp.wms.pda.model.pickbill;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ds365.commons.BaseIdModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：拣货提交
 * 
 * @author Li xinJian
 */
public class PickBillRegisterParamModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -66424603567759480L;
	private String pickerName;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date pickDate;

	private Long billId;

	private String billCode;

	private Long pickerId;

	private List<PickBatchDetailRegisterParamsModel> batchDetails;

	public String getPickerName() {
		return pickerName;
	}

	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}

	public Date getPickDate() {
		return pickDate;
	}

	public void setPickDate(Date pickDate) {
		this.pickDate = pickDate;
	}

	public List<PickBatchDetailRegisterParamsModel> getBatchDetails() {
		return batchDetails;
	}

	public void setBatchDetails(List<PickBatchDetailRegisterParamsModel> batchDetails) {
		this.batchDetails = batchDetails;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public Long getPickerId() {
		return pickerId;
	}

	public void setPickerId(Long pickerId) {
		this.pickerId = pickerId;
	}

}
