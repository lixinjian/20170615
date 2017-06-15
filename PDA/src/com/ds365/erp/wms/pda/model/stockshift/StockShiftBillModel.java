package com.ds365.erp.wms.pda.model.stockshift;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StockShiftBillModel extends BaseOperationModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4969654150566818717L;

	
	private  EmployeeModel examiner;//移库人员
	
	private Integer itemCount;//个数
	
	private double billMoney;

	private List<StockShiftDetailModel> details;
	
	// 制单人（开票人）
	private EmployeeModel maker;
	
	// 制单日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;
	
	// 备注
	private String remark;

	private String billCode;
	
	private Long oldBillId;
	
	private String oldBillCode;
	
	// 相关单据Id
	private Long relatedBillId;
	// 相关单据号
	private String relatedBillCode;
	
	public EmployeeModel getExaminer() {
		return examiner;
	}

	public void setExaminer(EmployeeModel examiner) {
		this.examiner = examiner;
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

	public List<StockShiftDetailModel> getDetails() {
		return details;
	}

	public void setDetails(List<StockShiftDetailModel> details) {
		this.details = details;
	}

	public EmployeeModel getMaker() {
		return maker;
	}

	public void setMaker(EmployeeModel maker) {
		this.maker = maker;
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

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
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
	
}
