package com.ds365.erp.wms.pda.model.stock;

import java.util.Date;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：分页查询商品Sku批次合作信息的model
 */
public class SkuBatchQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6584955621033438579L;

	private Long skuId;

	private String skuCode;

	private String goodsName;

	private String sysBatchNo;

	private Long supplierId;

	private String supplierCode;

	private String supplierName;

	private String produceBatchNo;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;

	private Long warehouseId;

	private Long purchaseOrganizationId;

	private Integer saleTypeId;
	/**
	 * 合同明细ID
	 */
	private Long contractDetailId;

	/**
	 * 合同ID
	 */
	private Long contractBillId;

	/**
	 * 合同单据编号
	 */
	private String contractBillCode;

	/**
	 * 合同号
	 */
	private String contractNo;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getProduceBatchNo() {
		return produceBatchNo;
	}

	public void setProduceBatchNo(String produceBatchNo) {
		this.produceBatchNo = produceBatchNo;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

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

	public Integer getSaleTypeId() {
		return saleTypeId;
	}

	public void setSaleTypeId(Integer saleTypeId) {
		this.saleTypeId = saleTypeId;
	}

	public Long getContractDetailId() {
		return contractDetailId;
	}

	public void setContractDetailId(Long contractDetailId) {
		this.contractDetailId = contractDetailId;
	}

	public Long getContractBillId() {
		return contractBillId;
	}

	public void setContractBillId(Long contractBillId) {
		this.contractBillId = contractBillId;
	}

	public String getContractBillCode() {
		return contractBillCode;
	}

	public void setContractBillCode(String contractBillCode) {
		this.contractBillCode = contractBillCode;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

}
