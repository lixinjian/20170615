package com.ds365.erp.wms.pda.model.purchase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：采购入库单或者采购退出出库单的查询model 
 */
public class PurchaseEnterBillQueryParamsModel extends BasePageQueryParamsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4427802816993775535L;
	private Long warehouseId;

	private String warehouseName;
	private Long purchaseOrganizationId;

	private Long warehouseOrganizationId;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date enterDate;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date orderMakeTime;

	private String billCode;
	// 单据类型
	private String billTypeId;

	private Long oldBillId;

	private String oldBillCode;

	private String oldBillTypeId;

	// 相关单据Id
	private Long relatedBillId;
	// 相关单据号
	private String relatedBillCode;

	// 相关单据类型
	private String relatedBillTypeId;

	// 单据状态
	private Integer billStateId;

	// 备注
	private String remark;

	private Long supplierId;

	private String supplierName;

	private String supplierCode;

	private String phoneNo;

	private String address;

	/**
	 * 组织ID
	 */
	private Long orgId;

	private String orgName;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date receiptDate;// 收货时间

	private String deliverBillNo;// 发货单号

	private Long registrarId;

	private String registrarName;

	private Long examinerId;

	private String examinerName;

	private Long makerId;

	private String makerName;

	// 制单日期
	private Date makeTime;

	private Integer settleStateId;// 结算状态

	private List<Integer> settleStateIds;

	private Long auditorId;// 审核人

	private String auditorName;// 审核人

	private Date auditTime;
	// 制单开始时间
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date startTime;
	// 制单结束时间
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date endTime;

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Long getPurchaseOrganizationId() {
		return purchaseOrganizationId;
	}

	public void setPurchaseOrganizationId(Long purchaseOrganizationId) {
		this.purchaseOrganizationId = purchaseOrganizationId;
	}

	public Long getWarehouseOrganizationId() {
		return warehouseOrganizationId;
	}

	public void setWarehouseOrganizationId(Long warehouseOrganizationId) {
		this.warehouseOrganizationId = warehouseOrganizationId;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public Date getOrderMakeTime() {
		return orderMakeTime;
	}

	public void setOrderMakeTime(Date orderMakeTime) {
		this.orderMakeTime = orderMakeTime;
	}

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

	public Integer getBillStateId() {
		return billStateId;
	}

	public void setBillStateId(Integer billStateId) {
		this.billStateId = billStateId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getDeliverBillNo() {
		return deliverBillNo;
	}

	public void setDeliverBillNo(String deliverBillNo) {
		this.deliverBillNo = deliverBillNo;
	}

	public Long getRegistrarId() {
		return registrarId;
	}

	public void setRegistrarId(Long registrarId) {
		this.registrarId = registrarId;
	}

	public String getRegistrarName() {
		return registrarName;
	}

	public void setRegistrarName(String registrarName) {
		this.registrarName = registrarName;
	}

	public Long getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(Long examinerId) {
		this.examinerId = examinerId;
	}

	public String getExaminerName() {
		return examinerName;
	}

	public void setExaminerName(String examinerName) {
		this.examinerName = examinerName;
	}

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

	public String getMakerName() {
		return makerName;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

	public Integer getSettleStateId() {
		return settleStateId;
	}

	public void setSettleStateId(Integer settleStateId) {
		this.settleStateId = settleStateId;
	}

	public List<Integer> getSettleStateIds() {
		return settleStateIds;
	}

	public void setSettleStateIds(List<Integer> settleStateIds) {
		this.settleStateIds = settleStateIds;
	}

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
