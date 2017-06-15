package com.ds365.erp.wms.pda.model.pickbill;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 拣货单
 * 
 * @author LiXinjian
 * @date 2016年6月14日.
 *
 */
public class PickBillModel extends BaseOperationModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1506157889865185523L;

	private String billCode;

	private EnumModel<String> billType;

	private Long oldBillId;

	private String oldBillCode;

	private EnumModel<String> oldBillType;

	private Long relatedBillId;

	private String relatedBillCode;

	private EnumModel<String> relatedBillType;

	private WarehouseModel warehouse;

	private EmployeeModel maker;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;

	private EnumModel<String> billState;

	private String remark;

	private OrganizationModel warehouseOrganization;

	private EmployeeModel picker;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date pickDate;

	private EnumModel<Long> printState;

	private String details;

	private String batchDetails;

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public EnumModel<String> getBillType() {
		return billType;
	}

	public void setBillType(EnumModel<String> billType) {
		this.billType = billType;
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

	public EnumModel<String> getOldBillType() {
		return oldBillType;
	}

	public void setOldBillType(EnumModel<String> oldBillType) {
		this.oldBillType = oldBillType;
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

	public EnumModel<String> getRelatedBillType() {
		return relatedBillType;
	}

	public void setRelatedBillType(EnumModel<String> relatedBillType) {
		this.relatedBillType = relatedBillType;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
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

	public EnumModel<String> getBillState() {
		return billState;
	}

	public void setBillState(EnumModel<String> billState) {
		this.billState = billState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public OrganizationModel getWarehouseOrganization() {
		return warehouseOrganization;
	}

	public void setWarehouseOrganization(OrganizationModel warehouseOrganization) {
		this.warehouseOrganization = warehouseOrganization;
	}

	public EmployeeModel getPicker() {
		return picker;
	}

	public void setPicker(EmployeeModel picker) {
		this.picker = picker;
	}

	public Date getPickDate() {
		return pickDate;
	}

	public void setPickDate(Date pickDate) {
		this.pickDate = pickDate;
	}

	public EnumModel<Long> getPrintState() {
		return printState;
	}

	public void setPrintState(EnumModel<Long> printState) {
		this.printState = printState;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getBatchDetails() {
		return batchDetails;
	}

	public void setBatchDetails(String batchDetails) {
		this.batchDetails = batchDetails;
	}

}
