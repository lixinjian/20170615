package com.ds365.erp.wms.pda.model.stockshift;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.erp.wms.pda.model.goods.GoodsModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author cgb2016
 *
 */
public class StockShiftDetailModel extends BaseOperationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5140957103130838518L;

	private GoodsSkuModel sku;

	private String unitName;

	private String spec;

	private Integer specQty;

	private Integer unitQty;

	private Integer minUnitQty;

	private Integer qty;

	private ShelfModel downShelf;// 下架货位

	private String downShelfCode;

	private String sysBatchNo;

	private GoodsModel goods;

	private String goodsName;

	private String skuName;

	private String barcode;

	private String skuCode;

	// 包装单位
	private GoodsPackModel pack;

	private double costPrice;
	// 按当前计量单位计算的成本单价
	private double costUnitPrice;
	// 成本金额
	private double costMoney;

	private StoreModel downStore;

	private StoreModel upStore;

	private ShelfModel upShelf;

	private String upShelfCode;

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

	private String billCode;
	// 序号
	private Integer ordinalNo;

	// 原单据Id
	private Long oldBillId;
	// 原单据号
	private String oldBillCode;
	// 原单据序号
	private Integer oldOrdinalNo;

	// 相关单据Id
	private Long relatedBillId;

	// 相关单据号
	private String relatedBillCode;
	// 相关单据序号
	private Integer relatedOrdinalNo;

	private Long relatedDetailId;

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
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

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public ShelfModel getDownShelf() {
		return downShelf;
	}

	public void setDownShelf(ShelfModel downShelf) {
		this.downShelf = downShelf;
	}

	public GoodsModel getGoods() {
		return goods;
	}

	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public GoodsPackModel getPack() {
		return pack;
	}

	public void setPack(GoodsPackModel pack) {
		this.pack = pack;
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

	public StoreModel getDownStore() {
		return downStore;
	}

	public void setDownStore(StoreModel downStore) {
		this.downStore = downStore;
	}

	public String getDownShelfCode() {
		return downShelfCode;
	}

	public void setDownShelfCode(String downShelfCode) {
		this.downShelfCode = downShelfCode;
	}

	public StoreModel getUpStore() {
		return upStore;
	}

	public void setUpStore(StoreModel upStore) {
		this.upStore = upStore;
	}

	public ShelfModel getUpShelf() {
		return upShelf;
	}

	public void setUpShelf(ShelfModel upShelf) {
		this.upShelf = upShelf;
	}

	public String getUpShelfCode() {
		return upShelfCode;
	}

	public void setUpShelfCode(String upShelfCode) {
		this.upShelfCode = upShelfCode;
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

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public Long getOldBillId() {
		return oldBillId;
	}

	public void setOldBillId(Long oldBillId) {
		this.oldBillId = oldBillId;
	}

	public String getOldBillCode() {
		return oldBillCode;
	}

	public void setOldBillCode(String oldBillCode) {
		this.oldBillCode = oldBillCode;
	}

	public Integer getOldOrdinalNo() {
		return oldOrdinalNo;
	}

	public void setOldOrdinalNo(Integer oldOrdinalNo) {
		this.oldOrdinalNo = oldOrdinalNo;
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

	public Integer getRelatedOrdinalNo() {
		return relatedOrdinalNo;
	}

	public void setRelatedOrdinalNo(Integer relatedOrdinalNo) {
		this.relatedOrdinalNo = relatedOrdinalNo;
	}

	public Long getRelatedDetailId() {
		return relatedDetailId;
	}

	public void setRelatedDetailId(Long relatedDetailId) {
		this.relatedDetailId = relatedDetailId;
	}

}