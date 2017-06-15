package com.ds365.erp.wms.pda.model.stocktake;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ds365.commons.BaseIdModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StockTakeOrderDetailCreateParamsModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -938663692331743903L;
	private Long billId;

	private String billCode;
	private Long skuId;

	private Long goodsId;

	private String skuName;

	private String goodsName;

	private String skuCode;

	private String spec;// 规格

	private String barcode;//
	// 包装单位
	private Long packId;// 包装

	private Long packTypeId;// 包装类型

	private String unitName;// 单位名称

	private Integer specQty;// 包装数量

	private BigDecimal costUnitPrice;// 件成本价
	// 主单价
	private BigDecimal costPrice;// 单品成本价

	private Integer expectUnitQty;// 件数

	private Integer expectMinUnitQty;// 散数
	// 单品数量
	private Integer expectQty;

	private Integer unitQty;// 盘点件数
	// 散数
	private Integer minUnitQty;// 盘点散数

	private Integer qty;// 盘点单品数量

	private Integer lossQty;// 单品差异数量

	// 金额
	private BigDecimal expectCostMoney;// 账面成本金额

	private BigDecimal costMoney;

	private BigDecimal lossCostMoney;// 损益金额

	/*
	 * private Long baseWeightPackId;
	 * 
	 * private Integer baseWeightSpecQty;//重量基数单位的包装数量
	 * 
	 * private BigDecimal baseWeight;
	 * 
	 * // 重量 private BigDecimal weight;
	 * 
	 * private Long baseVolumePackId;
	 * 
	 * private Integer baseVolumeSpecQty;//体积基数单位的包装数量
	 * 
	 * private BigDecimal baseVolume; //体积 private BigDecimal volume;
	 */

	// 销售类型
	private Integer saleTypeId;
	// 商品状态
	private Integer goodsStateId;

	/*
	 * private Long contractDetailId; private Long contractBillId; private
	 * String contractBillCode; private String contractNo;
	 */
	/*
	 * private Long supplierId;//供应商
	 * 
	 * // 合作类型[经销、代销] private Integer cooperateTypeId; //分账方式 private Integer
	 * accountSplitWayId; //服务费率 private BigDecimal serviceRate;
	 * 
	 * // 商品折扣率 private BigDecimal discountRate;
	 * 
	 * // 商品返利率 private BigDecimal rebateRate;
	 */

	private String produceBatchNo;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date expireDate;

	private Integer guaranteePeriod;

	// 库区Id(退货使用)
	private Long storeId;
	// 货位id
	private Long shelfId;

	private String shelfCode;// 货位号

	// 系统批次号
	private String sysBatchNo;

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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	public Long getPackId() {
		return packId;
	}

	public void setPackId(Long packId) {
		this.packId = packId;
	}

	public Long getPackTypeId() {
		return packTypeId;
	}

	public void setPackTypeId(Long packTypeId) {
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

	public BigDecimal getCostUnitPrice() {
		return costUnitPrice;
	}

	public void setCostUnitPrice(BigDecimal costUnitPrice) {
		this.costUnitPrice = costUnitPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getExpectUnitQty() {
		return expectUnitQty;
	}

	public void setExpectUnitQty(Integer expectUnitQty) {
		this.expectUnitQty = expectUnitQty;
	}

	public Integer getExpectMinUnitQty() {
		return expectMinUnitQty;
	}

	public void setExpectMinUnitQty(Integer expectMinUnitQty) {
		this.expectMinUnitQty = expectMinUnitQty;
	}

	public Integer getExpectQty() {
		return expectQty;
	}

	public void setExpectQty(Integer expectQty) {
		this.expectQty = expectQty;
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

	public Integer getLossQty() {
		return lossQty;
	}

	public void setLossQty(Integer lossQty) {
		this.lossQty = lossQty;
	}

	public BigDecimal getExpectCostMoney() {
		return expectCostMoney;
	}

	public void setExpectCostMoney(BigDecimal expectCostMoney) {
		this.expectCostMoney = expectCostMoney;
	}

	public BigDecimal getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(BigDecimal costMoney) {
		this.costMoney = costMoney;
	}

	public BigDecimal getLossCostMoney() {
		return lossCostMoney;
	}

	public void setLossCostMoney(BigDecimal lossCostMoney) {
		this.lossCostMoney = lossCostMoney;
	}

	public Integer getSaleTypeId() {
		return saleTypeId;
	}

	public void setSaleTypeId(Integer saleTypeId) {
		this.saleTypeId = saleTypeId;
	}

	public Integer getGoodsStateId() {
		return goodsStateId;
	}

	public void setGoodsStateId(Integer goodsStateId) {
		this.goodsStateId = goodsStateId;
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

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getGuaranteePeriod() {
		return guaranteePeriod;
	}

	public void setGuaranteePeriod(Integer guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
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

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

}
