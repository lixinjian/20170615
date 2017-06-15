package com.ds365.erp.wms.pda.model.putaway;

import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PutawayTaskQueryParamModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8087688021607375837L;

	private Long warehouseId;

	private Integer putawayTypeId;

	private Integer putawayStateId;

	private List<Integer> putawayStateIds;

	private Long relativeBillId;

	private Integer relativeBillTypeId;

	private String relativeBillCode;

	private String goodsName;

	private Long skuId;

	private String skuCode;

	private String barcode;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date startTime;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date endTime;

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getPutawayTypeId() {
		return putawayTypeId;
	}

	public void setPutawayTypeId(Integer putawayTypeId) {
		this.putawayTypeId = putawayTypeId;
	}

	public Integer getPutawayStateId() {
		return putawayStateId;
	}

	public void setPutawayStateId(Integer putawayStateId) {
		this.putawayStateId = putawayStateId;
	}

	public List<Integer> getPutawayStateIds() {
		return putawayStateIds;
	}

	public void setPutawayStateIds(List<Integer> putawayStateIds) {
		this.putawayStateIds = putawayStateIds;
	}

	public Long getRelativeBillId() {
		return relativeBillId;
	}

	public void setRelativeBillId(Long relativeBillId) {
		this.relativeBillId = relativeBillId;
	}

	public Integer getRelativeBillTypeId() {
		return relativeBillTypeId;
	}

	public void setRelativeBillTypeId(Integer relativeBillTypeId) {
		this.relativeBillTypeId = relativeBillTypeId;
	}

	public String getRelativeBillCode() {
		return relativeBillCode;
	}

	public void setRelativeBillCode(String relativeBillCode) {
		this.relativeBillCode = relativeBillCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

}
