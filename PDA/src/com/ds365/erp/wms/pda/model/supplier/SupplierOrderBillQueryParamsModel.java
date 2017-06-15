package com.ds365.erp.wms.pda.model.supplier;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SupplierOrderBillQueryParamsModel extends BasePageQueryParamsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5194817173403452875L;

	private Long warehouseId;

	private Long purchaseOrganizationId;

	private Long warehouseOrganizationId;
	/**
	 * 订单开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date startTime;
	/**
	 * 订单结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date endTime;

	/**
	 * 供应商Id
	 */
	private Long supplierId;

	/**
	 * 供应商
	 */
	private String supplierName;

	/**
	 * 单据编号
	 */
	private String billCode;

	// 单据状态
	private Integer billStateId;

	// 单据状态集合
	private List<Integer> billStateIds;

	/**
	 * 类型
	 */
	private String billTypeId;

	private Integer closeTypeId;

	/**
	 * 合同业务合作类型
	 */
	private Integer cooperateTypeId;

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
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

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public Integer getBillStateId() {
		return billStateId;
	}

	public void setBillStateId(Integer billStateId) {
		this.billStateId = billStateId;
	}

	public List<Integer> getBillStateIds() {
		return billStateIds;
	}

	public void setBillStateIds(List<Integer> billStateIds) {
		this.billStateIds = billStateIds;
	}

	public String getBillTypeId() {
		return billTypeId;
	}

	public void setBillTypeId(String billTypeId) {
		this.billTypeId = billTypeId;
	}

	public Integer getCloseTypeId() {
		return closeTypeId;
	}

	public void setCloseTypeId(Integer closeTypeId) {
		this.closeTypeId = closeTypeId;
	}

	public Integer getCooperateTypeId() {
		return cooperateTypeId;
	}

	public void setCooperateTypeId(Integer cooperateTypeId) {
		this.cooperateTypeId = cooperateTypeId;
	}

}
