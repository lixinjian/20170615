package com.ds365.erp.wms.pda.model.pickbill;

import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PickDetailQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3954569351203240607L;

	/**
	 * 拣货单ID
	 */
	private Long billId;

	private List<Long> billIds;

	/**
	 * 拣货单code
	 */
	private String billCode;

	/**
	 * 仓库ID
	 */
	private Long warehouseId;

	private String warehouseName;

	/**
	 * 库区
	 */
	private Long storeId;

	private String storeName;

	/**
	 * SKU ID
	 */
	private Long skuId;

	/**
	 * SKU编码
	 */
	private String skuCode;

	/**
	 * 商品ID
	 */
	private Long goodsId;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品规格
	 */
	private String spec;

	/**
	 * 商品条码
	 */
	private String barcode;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date startTime;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date endTime;

	private Integer billStateId;

	private Integer printStateId;

	private Boolean pickFlag;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public List<Long> getBillIds() {
		return billIds;
	}

	public void setBillIds(List<Long> billIds) {
		this.billIds = billIds;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public Integer getBillStateId() {
		return billStateId;
	}

	public void setBillStateId(Integer billStateId) {
		this.billStateId = billStateId;
	}

	public Integer getPrintStateId() {
		return printStateId;
	}

	public void setPrintStateId(Integer printStateId) {
		this.printStateId = printStateId;
	}

	public Boolean getPickFlag() {
		return pickFlag;
	}

	public void setPickFlag(Boolean pickFlag) {
		this.pickFlag = pickFlag;
	}

}
