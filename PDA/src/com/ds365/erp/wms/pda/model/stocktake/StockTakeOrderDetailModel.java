package com.ds365.erp.wms.pda.model.stocktake;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.BaseCheckFlagModel;
import com.ds365.erp.wms.pda.model.goods.GoodsModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StockTakeOrderDetailModel extends BaseCheckFlagModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9002671163879838663L;

	private Long billId;

	private String billCode;
	private GoodsSkuModel sku;

	private GoodsModel goods;

	private String skuName;

	private String goodsName;

	private String skuCode;

	private String spec;// 规格

	private String barcode;//
	// 包装单位
	private GoodsPackModel pack;// 包装

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

	// 销售类型
	private EnumModel<Integer> saleType;
	// 商品状态
	private EnumModel<Integer> goodsState;

	private String produceBatchNo;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date expireDate;

	private Integer guaranteePeriod;

	// 库区Id(退货使用)
	private StoreModel store;
	// 货位id
	private ShelfModel shelf;

	private String shelfCode;// 货位号

	// 系统批次号
	private String sysBatchNo;

	private StockTakeOrderBillModel bill;

	private EnumModel<String> billType;
	// 序号
	private Integer ordinalNo;

	// 原单据Id
	private Long oldBillId;
	// 原单据号
	private String oldBillCode;
	// 原单据类型
	private EnumModel<String> oldBillType;
	// 原单据序号
	private Integer oldOrdinalNo;

	// 相关单据Id
	private Long relatedBillId;

	// 相关单据号
	private String relatedBillCode;
	// 相关单据类型
	private EnumModel<String> relatedBillType;
	// 相关单据序号
	private Integer relatedOrdinalNo;

	private Long relatedDetailId;

 	/*private SupplierEntity supplier;//供应商
 	
  	// 合作类型[经销、代销]
  	@JsonSerialize(using = BaseEnumSerializer.class)
 	private CooperateTypeEnum cooperateType;
  	//分账方式
  	@JsonSerialize(using = BaseEnumSerializer.class)
 	private AccountSplitWayEnum accountSplitWay;
 	//服务费率
 	private BigDecimal serviceRate;
 // 商品折扣率
 	private BigDecimal discountRate;
 	// 商品返利率
 	private BigDecimal rebateRate;*/
 	
 	/*private GoodsPackEntity baseWeightPack;
	
	private Integer baseWeightSpecQty;//重量基数单位的包装数量
	
	private BigDecimal baseWeight;
	
	// 重量
	private BigDecimal weight;
	
	private GoodsPackEntity baseVolumePack;
	
	private Integer baseVolumeSpecQty;//体积基数单位的包装数量
	
	private BigDecimal baseVolume;
	//体积
	private BigDecimal volume;*/

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

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public GoodsModel getGoods() {
		return goods;
	}

	public void setGoods(GoodsModel goods) {
		this.goods = goods;
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

	public GoodsPackModel getPack() {
		return pack;
	}

	public void setPack(GoodsPackModel pack) {
		this.pack = pack;
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

	public EnumModel<Integer> getSaleType() {
		return saleType;
	}

	public void setSaleType(EnumModel<Integer> saleType) {
		this.saleType = saleType;
	}

	public EnumModel<Integer> getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(EnumModel<Integer> goodsState) {
		this.goodsState = goodsState;
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

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public ShelfModel getShelf() {
		return shelf;
	}

	public void setShelf(ShelfModel shelf) {
		this.shelf = shelf;
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

	public StockTakeOrderBillModel getBill() {
		return bill;
	}

	public void setBill(StockTakeOrderBillModel bill) {
		this.bill = bill;
	}

	public EnumModel<String> getBillType() {
		return billType;
	}

	public void setBillType(EnumModel<String> billType) {
		this.billType = billType;
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

	public EnumModel<String> getOldBillType() {
		return oldBillType;
	}

	public void setOldBillType(EnumModel<String> oldBillType) {
		this.oldBillType = oldBillType;
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

	public EnumModel<String> getRelatedBillType() {
		return relatedBillType;
	}

	public void setRelatedBillType(EnumModel<String> relatedBillType) {
		this.relatedBillType = relatedBillType;
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
