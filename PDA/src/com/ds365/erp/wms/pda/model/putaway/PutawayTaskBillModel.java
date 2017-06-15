package com.ds365.erp.wms.pda.model.putaway;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PutawayTaskBillModel extends BaseOperationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3367789991647779625L;

	private WarehouseModel warehouse;

	private OrganizationModel warehouseOrganization;

	/**
	 * 上架登记人
	 */
	private EmployeeModel putter;

	// 制单人（开票人）
	private EmployeeModel maker;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date putDate;
	
	private Integer itemCount;
	
	private Integer unitQty;
	
	private Integer minUnitQty;
	
	/**
	 * 打印状态
	 */
	private EnumModel<Integer> printState;

	private Long purchaseEnterBillId;// 拣货单关联的入库单id

	private List<PutawayTaskDetailModel> details;

	// 备注
	private String remark;

	private String billCode;

	private EnumModel<String> billType;

	private Long oldBillId;

	private String oldBillCode;

	private EnumModel<String> oldBillType;

	// 相关单据Id
	private Long relatedBillId;
	// 相关单据号
	private String relatedBillCode;

	// 相关单据类型
	private EnumModel<String> relatedBillType;

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public OrganizationModel getWarehouseOrganization() {
		return warehouseOrganization;
	}

	public void setWarehouseOrganization(OrganizationModel warehouseOrganization) {
		this.warehouseOrganization = warehouseOrganization;
	}

	public EmployeeModel getPutter() {
		return putter;
	}

	public void setPutter(EmployeeModel putter) {
		this.putter = putter;
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

	public Date getPutDate() {
		return putDate;
	}

	public void setPutDate(Date putDate) {
		this.putDate = putDate;
	}

	public EnumModel<Integer> getPrintState() {
		return printState;
	}

	public void setPrintState(EnumModel<Integer> printState) {
		this.printState = printState;
	}

	public Long getPurchaseEnterBillId() {
		return purchaseEnterBillId;
	}

	public void setPurchaseEnterBillId(Long purchaseEnterBillId) {
		this.purchaseEnterBillId = purchaseEnterBillId;
	}

	public List<PutawayTaskDetailModel> getDetails() {
		return details;
	}

	public void setDetails(List<PutawayTaskDetailModel> details) {
		this.details = details;
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

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Integer getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}

	public Integer getMinUnitQty() {
		return minUnitQty;
	}

	public void setMinUnitQty(Integer minUnitQty) {
		this.minUnitQty = minUnitQty;
	}
	
}
