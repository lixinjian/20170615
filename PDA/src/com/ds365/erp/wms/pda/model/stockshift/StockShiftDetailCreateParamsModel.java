package com.ds365.erp.wms.pda.model.stockshift;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.BaseIdModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StockShiftDetailCreateParamsModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7923200094646998290L;

	private Long billId;

	private String billCode;
	private Long skuId;

	private String skuName;

	private Long goodsId;

	private String goodsName;

	private String skuCode;

	private String barcode;

	private Long downStoreId;

	private Long downShelfId;

	private String downShelfCode;

	private Long upStoreId;

	private Long upShelfId;

	private String upShelfCode;

	private String sysBatchNo;
	// 生产批次号
	private String produceBatchNo;
	// 保质期
	private Integer guaranteePeriod;
	// 生产日期

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;

	// 到期日期
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date expireDate;
	// 包装单位
	private Long packId;

	private Integer packTypeId;

	private String unitName;

	private Integer specQty;

	private String spec;

	// 按最小计量单位计算的总数量
	private Integer qty;

	private Integer minUnitQty;// 散数

	// 按当前计量单位计算的数量
	private Integer unitQty;

	private double costPrice;

	// 按当前计量单位计算的成本单价
	private double costUnitPrice;

	// 成本金额
	private double costMoney;

	// 当前库存数量
	private Integer stockQty;

	private Integer ordinalNo;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
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

	public Long getDownStoreId() {
		return downStoreId;
	}

	public void setDownStoreId(Long downStoreId) {
		this.downStoreId = downStoreId;
	}

	public Long getDownShelfId() {
		return downShelfId;
	}

	public void setDownShelfId(Long downShelfId) {
		this.downShelfId = downShelfId;
	}

	public String getDownShelfCode() {
		return downShelfCode;
	}

	public void setDownShelfCode(String downShelfCode) {
		this.downShelfCode = downShelfCode;
	}

	public Long getUpStoreId() {
		return upStoreId;
	}

	public void setUpStoreId(Long upStoreId) {
		this.upStoreId = upStoreId;
	}

	public Long getUpShelfId() {
		return upShelfId;
	}

	public void setUpShelfId(Long upShelfId) {
		this.upShelfId = upShelfId;
	}

	public String getUpShelfCode() {
		return upShelfCode;
	}

	public void setUpShelfCode(String upShelfCode) {
		this.upShelfCode = upShelfCode;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public String getProduceBatchNo() {
		return produceBatchNo;
	}

	public void setProduceBatchNo(String produceBatchNo) {
		this.produceBatchNo = produceBatchNo;
	}

	public Integer getGuaranteePeriod() {
		return guaranteePeriod;
	}

	public void setGuaranteePeriod(Integer guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getMinUnitQty() {
		return minUnitQty;
	}

	public void setMinUnitQty(Integer minUnitQty) {
		this.minUnitQty = minUnitQty;
	}

	public Integer getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getCostUnitPrice() {
		return costUnitPrice;
	}

	public void setCostUnitPrice(double costUnitPrice) {
		this.costUnitPrice = costUnitPrice;
	}

	public double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(double costMoney) {
		this.costMoney = costMoney;
	}

	public Integer getStockQty() {
		return stockQty;
	}

	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}
}
