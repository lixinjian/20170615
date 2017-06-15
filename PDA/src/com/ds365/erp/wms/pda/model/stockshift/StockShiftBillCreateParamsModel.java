package com.ds365.erp.wms.pda.model.stockshift;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ds365.commons.BaseIdModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：移库业务提交的model
 */
public class StockShiftBillCreateParamsModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5654806008752836531L;

	private String billCode;
	// 单据类型
	private String billTypeId;

	// 单据状态
	private Integer billStateId;

	private Integer itemCount;

	private double billMoney;

	private Long examinerId;

	private Long makerId;

	// 制单日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;

	// 备注
	private String remark;

	private Long oldBillId;

	private String oldBillCode;

	private String oldBillTypeId;

	// 相关单据Id
	private Long relatedBillId;
	// 相关单据号
	private String relatedBillCode;

	// 相关单据类型
	private String relatedBillTypeId;

	private List<StockShiftDetailCreateParamsModel> details;

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getBillTypeId() {
		return billTypeId;
	}

	public void setBillTypeId(String billTypeId) {
		this.billTypeId = billTypeId;
	}

	public Integer getBillStateId() {
		return billStateId;
	}

	public void setBillStateId(Integer billStateId) {
		this.billStateId = billStateId;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public double getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}

	public Long getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(Long examinerId) {
		this.examinerId = examinerId;
	}

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getOldBillId() {
		return oldBillId;
	}

	public void setOldBillId(Long oldBillId) {
		this.oldBillId = oldBillId;
	}

	public String getOldBillCode() {
		return oldBillCode;
	}

	public void setOldBillCode(String oldBillCode) {
		this.oldBillCode = oldBillCode;
	}

	public String getOldBillTypeId() {
		return oldBillTypeId;
	}

	public void setOldBillTypeId(String oldBillTypeId) {
		this.oldBillTypeId = oldBillTypeId;
	}

	public Long getRelatedBillId() {
		return relatedBillId;
	}

	public void setRelatedBillId(Long relatedBillId) {
		this.relatedBillId = relatedBillId;
	}

	public String getRelatedBillCode() {
		return relatedBillCode;
	}

	public void setRelatedBillCode(String relatedBillCode) {
		this.relatedBillCode = relatedBillCode;
	}

	public String getRelatedBillTypeId() {
		return relatedBillTypeId;
	}

	public void setRelatedBillTypeId(String relatedBillTypeId) {
		this.relatedBillTypeId = relatedBillTypeId;
	}

	public List<StockShiftDetailCreateParamsModel> getDetails() {
		return details;
	}

	public void setDetails(List<StockShiftDetailCreateParamsModel> details) {
		this.details = details;
	}
}
