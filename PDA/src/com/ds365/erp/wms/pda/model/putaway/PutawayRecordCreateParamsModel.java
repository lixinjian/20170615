package com.ds365.erp.wms.pda.model.putaway;

import java.io.Serializable;

import com.ds365.commons.BaseIdModel;

public class PutawayRecordCreateParamsModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -542035691345794250L;

	private Long storeId;

	private Long shelfId;

	private Integer unitQty;

	private Integer minUnitQty;

	private String shelfCode;
	
	private Integer shelfTypeId;

    private String sysBatchNo;

    private Integer qty;

    private Long putawayTaskId;
	
	private Long skuId;
	
	private String barcode;
	
	private String spec;
	
	private Long warehouseId;
	
	private  Long packId;//上架的包装
	
	private Integer packTypeId;//
	
	private Integer specQty;//包装数量
	
	private  String unitName;//单位名称
	
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
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

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public Integer getShelfTypeId() {
		return shelfTypeId;
	}

	public void setShelfTypeId(Integer shelfTypeId) {
		this.shelfTypeId = shelfTypeId;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Long getPutawayTaskId() {
		return putawayTaskId;
	}

	public void setPutawayTaskId(Long putawayTaskId) {
		this.putawayTaskId = putawayTaskId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getPackId() {
		return packId;
	}

	public void setPackId(Long packId) {
		this.packId = packId;
	}

	public Integer getPackTypeId() {
		return packTypeId;
	}

	public void setPackTypeId(Integer packTypeId) {
		this.packTypeId = packTypeId;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
