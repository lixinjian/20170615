package com.ds365.erp.wms.pda.model.sale;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.BaseIdModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SaleReturnEnterDetailCreateParamsModel extends BaseIdModel<Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3778379192607312560L;
	private Long relatedDetailId;// (退货申请单明细id)
	private Long relatedBillId;
	private String relatedBillCode;
	private Long skuId;
	private Long packId;
	private int unitQty;
	private int minUnitQty;
	private Long shelfId;	//货位
	private String shelfCode; 
	private Long storeId;		//库区
	private String sysBatchNo;		//批次
	private String produceBatchNo;	//生产批次
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;
	private Long relatedBillTypeId;
	
	private String skuName;
	private Long goodsId;
	private String goodsName;
	private String spec;
	private String barcode;
	private String unitName;
	private Integer specQty;
	// 下单件数（包装单位）
	private Integer orderUnitQty;
	private Integer orderMinUnitQty;
	
	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public Long getRelatedDetailId() {
		return relatedDetailId;
	}

	public void setRelatedDetailId(Long relatedDetailId) {
		this.relatedDetailId = relatedDetailId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getPackId() {
		return packId;
	}

	public void setPackId(Long packId) {
		this.packId = packId;
	}

	public int getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(int unitQty) {
		this.unitQty = unitQty;
	}

	public int getMinUnitQty() {
		return minUnitQty;
	}

	public void setMinUnitQty(int minUnitQty) {
		this.minUnitQty = minUnitQty;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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

	public Long getRelatedBillTypeId() {
		return relatedBillTypeId;
	}

	public void setRelatedBillTypeId(Long relatedBillTypeId) {
		this.relatedBillTypeId = relatedBillTypeId;
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

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
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

	public Integer getOrderUnitQty() {
		return orderUnitQty;
	}

	public void setOrderUnitQty(Integer orderUnitQty) {
		this.orderUnitQty = orderUnitQty;
	}

	public Integer getOrderMinUnitQty() {
		return orderMinUnitQty;
	}

	public void setOrderMinUnitQty(Integer orderMinUnitQty) {
		this.orderMinUnitQty = orderMinUnitQty;
	}
	
}
