package com.ds365.erp.wms.pda.model.putaway;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ds365.commons.base.model.BaseOperationModel;

public class PutawayTaskDetailCreateParamsModel extends BaseOperationModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4587133373995185542L;

	private Long billId;

	private Long storeId;

	private Long shelfId;

	private String shelfCode;

	private String sysBatchNo;

	private Integer unitQty;

	private Integer minUnitQty;

	private Integer qty;

	private Long skuId;

	private String skuName;

	private String goodsName;

	private Long tempShelfId;

	private String tempShelfCode;

	private Long tempStoreId;

	private String barcode;

	private String spec;

	private Long warehouseId;

	private Long packId;// 上架的包装

	private Integer packTypeId;//

	private Integer specQty;// 包装数量

	private String unitName;// 单位名称

	private Boolean putFlag;// 是否已上架
	private Long putterId;

	private Long baseWeightPackId;

	private Long baseVolumePackId;

	/**
	 * 重量
	 */
	private BigDecimal weight;

	/**
	 * 体积
	 */
	private BigDecimal volume;

	private BigDecimal baseWeight;

	private BigDecimal baseVolume;

	private Long relatedDetailId;

	private Long relatedBillId;

	private String relatedBillTypeId;

	private String relatedBillCode;

	/**
	 * 序号
	 */
	private Integer ordinalNo;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

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

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getTempShelfId() {
		return tempShelfId;
	}

	public void setTempShelfId(Long tempShelfId) {
		this.tempShelfId = tempShelfId;
	}

	public String getTempShelfCode() {
		return tempShelfCode;
	}

	public void setTempShelfCode(String tempShelfCode) {
		this.tempShelfCode = tempShelfCode;
	}

	public Long getTempStoreId() {
		return tempStoreId;
	}

	public void setTempStoreId(Long tempStoreId) {
		this.tempStoreId = tempStoreId;
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

	public Boolean getPutFlag() {
		return putFlag;
	}

	public void setPutFlag(Boolean putFlag) {
		this.putFlag = putFlag;
	}

	public Long getPutterId() {
		return putterId;
	}

	public void setPutterId(Long putterId) {
		this.putterId = putterId;
	}

	public Long getBaseWeightPackId() {
		return baseWeightPackId;
	}

	public void setBaseWeightPackId(Long baseWeightPackId) {
		this.baseWeightPackId = baseWeightPackId;
	}

	public Long getBaseVolumePackId() {
		return baseVolumePackId;
	}

	public void setBaseVolumePackId(Long baseVolumePackId) {
		this.baseVolumePackId = baseVolumePackId;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(BigDecimal baseWeight) {
		this.baseWeight = baseWeight;
	}

	public BigDecimal getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(BigDecimal baseVolume) {
		this.baseVolume = baseVolume;
	}

	public Long getRelatedDetailId() {
		return relatedDetailId;
	}

	public void setRelatedDetailId(Long relatedDetailId) {
		this.relatedDetailId = relatedDetailId;
	}

	public Long getRelatedBillId() {
		return relatedBillId;
	}

	public void setRelatedBillId(Long relatedBillId) {
		this.relatedBillId = relatedBillId;
	}

	public String getRelatedBillTypeId() {
		return relatedBillTypeId;
	}

	public void setRelatedBillTypeId(String relatedBillTypeId) {
		this.relatedBillTypeId = relatedBillTypeId;
	}

	public String getRelatedBillCode() {
		return relatedBillCode;
	}

	public void setRelatedBillCode(String relatedBillCode) {
		this.relatedBillCode = relatedBillCode;
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}
}
