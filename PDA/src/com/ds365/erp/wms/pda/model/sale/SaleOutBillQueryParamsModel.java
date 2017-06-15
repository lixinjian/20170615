package com.ds365.erp.wms.pda.model.sale;

import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：销售出库单和销售退货入库单的查询model
 */
public class SaleOutBillQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1342014702924734663L;
	private Long saleOrganizationId;

	private Long warehouseOrganizationId;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date outDate;

	private String billCode;

	private String relatedBillCode;

	private String billTypeId;

	private Long warehouseId;

	// 制单人（开票人）
	private Long makerId;

	// 单据状态
	private Integer billStateId;
	// 备注
	private String remark;

	private Long customerId;

	private String customerCode;

	// 支付状态[未支付、部分支付、已支付]
	private Integer payStateId;
	// 业务员
	private Long salesmanId;
	// 是否首单
	private Boolean firstOrderFlag;

	private Integer closeStateId;

	private Integer closeTypeId;

	private Boolean billLockFlag;

	/**
	 * 制单开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date startTime;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date endTime;

	private List<Integer> billStateIds;

	private List<String> billTypeIds;

	private Integer auditTypeId;

	private List<Long> billIds;

	private Long shipmentBillId;

	private String shipmentBillCode;

	private Long deliverBillId;

	private String deliverBillCode;

	private Long deliverStationId;

	public Long getSaleOrganizationId() {
		return saleOrganizationId;
	}

	public void setSaleOrganizationId(Long saleOrganizationId) {
		this.saleOrganizationId = saleOrganizationId;
	}

	public Long getWarehouseOrganizationId() {
		return warehouseOrganizationId;
	}

	public void setWarehouseOrganizationId(Long warehouseOrganizationId) {
		this.warehouseOrganizationId = warehouseOrganizationId;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
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

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Integer getPayStateId() {
		return payStateId;
	}

	public void setPayStateId(Integer payStateId) {
		this.payStateId = payStateId;
	}

	public Long getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Long salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Boolean getFirstOrderFlag() {
		return firstOrderFlag;
	}

	public void setFirstOrderFlag(Boolean firstOrderFlag) {
		this.firstOrderFlag = firstOrderFlag;
	}

	public Integer getCloseStateId() {
		return closeStateId;
	}

	public void setCloseStateId(Integer closeStateId) {
		this.closeStateId = closeStateId;
	}

	public Integer getCloseTypeId() {
		return closeTypeId;
	}

	public void setCloseTypeId(Integer closeTypeId) {
		this.closeTypeId = closeTypeId;
	}

	public Boolean getBillLockFlag() {
		return billLockFlag;
	}

	public void setBillLockFlag(Boolean billLockFlag) {
		this.billLockFlag = billLockFlag;
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

	public List<Integer> getBillStateIds() {
		return billStateIds;
	}

	public void setBillStateIds(List<Integer> billStateIds) {
		this.billStateIds = billStateIds;
	}

	public List<String> getBillTypeIds() {
		return billTypeIds;
	}

	public void setBillTypeIds(List<String> billTypeIds) {
		this.billTypeIds = billTypeIds;
	}

	public Integer getAuditTypeId() {
		return auditTypeId;
	}

	public void setAuditTypeId(Integer auditTypeId) {
		this.auditTypeId = auditTypeId;
	}

	public List<Long> getBillIds() {
		return billIds;
	}

	public void setBillIds(List<Long> billIds) {
		this.billIds = billIds;
	}

	public Long getShipmentBillId() {
		return shipmentBillId;
	}

	public void setShipmentBillId(Long shipmentBillId) {
		this.shipmentBillId = shipmentBillId;
	}

	public String getShipmentBillCode() {
		return shipmentBillCode;
	}

	public void setShipmentBillCode(String shipmentBillCode) {
		this.shipmentBillCode = shipmentBillCode;
	}

	public Long getDeliverBillId() {
		return deliverBillId;
	}

	public void setDeliverBillId(Long deliverBillId) {
		this.deliverBillId = deliverBillId;
	}

	public String getDeliverBillCode() {
		return deliverBillCode;
	}

	public void setDeliverBillCode(String deliverBillCode) {
		this.deliverBillCode = deliverBillCode;
	}

	public Long getDeliverStationId() {
		return deliverStationId;
	}

	public void setDeliverStationId(Long deliverStationId) {
		this.deliverStationId = deliverStationId;
	}

	public String getRelatedBillCode() {
		return relatedBillCode;
	}

	public void setRelatedBillCode(String relatedBillCode) {
		this.relatedBillCode = relatedBillCode;
	}

}
