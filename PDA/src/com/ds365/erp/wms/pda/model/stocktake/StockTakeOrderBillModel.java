package com.ds365.erp.wms.pda.model.stocktake;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StockTakeOrderBillModel extends BaseOperationModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2149996040372452363L;
	
	private Long oldBillId;
	private String oldBillCode;
	// 相关单据Id
	private Long relatedBillId;
	// 相关单据号
	private String relatedBillCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date takeDate;// 盘点时间

	private EmployeeModel examiner;// 盘点人

	private Double billMoney;

	private Double lossMoney;// 损益金额

	private ShelfModel shelf;
	
	private StoreModel store;
	
	// 制单人（开票人）
	private EmployeeModel maker;
	
	// 制单日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;
	
	// 备注
	private String remark;
		
	private String billCode;
	
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

	public Date getTakeDate() {
		return takeDate;
	}

	public void setTakeDate(Date takeDate) {
		this.takeDate = takeDate;
	}

	public EmployeeModel getExaminer() {
		return examiner;
	}

	public void setExaminer(EmployeeModel examiner) {
		this.examiner = examiner;
	}

	public Double getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(Double billMoney) {
		this.billMoney = billMoney;
	}

	public Double getLossMoney() {
		return lossMoney;
	}

	public void setLossMoney(Double lossMoney) {
		this.lossMoney = lossMoney;
	}

	public ShelfModel getShelf() {
		return shelf;
	}

	public void setShelf(ShelfModel shelf) {
		this.shelf = shelf;
	}

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
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

}
