package com.ds365.erp.wms.pda.model.purchase;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.base.model.BaseOperationModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseEnterDetailCreateParamsModel extends BaseOperationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1402574228219352178L;

	private Long skuId;//

	private String skuCode;//

	private String barcode;//

	private String goodsName;//

	private String goodsCode;//

	private String manufacturer;//

	private String spec;//

	// 是否为赠品
	private Integer saleTypeId;//
	// 库区Id(退货使用)
	private Long storeId;//

	private Integer storeUseTypeId;//

	// 货位id
	private Long shelfId;//

	private Integer shelfTypeId;//
	// 货位号
	private String shelfCode;//
	// 系统批次号
	private String sysBatchNo;
	// 生产批次号
	private String produceBatchNo;//
	// 保质期
	private Integer guaranteePeriod;
	// 生产日期
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;//
	// 到期日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date expireDate;
	// 包装ID
	private Long packId;//
	// 包装单位名称
	private String unitName;//

	private Integer specQty;//

	private Integer packTypeId;

	// 按最小计量单位计算的数量
	private Integer qty;
	// 按当前计量单位计算的数量
	private Integer unitQty;
	private Integer minUnitQty;
	 
	// 按最小计量单位计算的单价
	private Double price;//
	// 按当前计量单位计算的单价
	private Double unitPrice;//
	// 存货成本单价
	private Double costPrice;//
	// 按当前计量单位计算的成本单价
	private Double unitCostPrice;
	// 成本金额
	private Double costMoney;
	// 金额
	private Double money;
	// 商品返利率
	private Double rebateRate;
	// 商品返利额
	private Double rebateMoney;
	/**
	 * 商品返利率
	 */
	private Double discountRate;

	/**
	 * 商品返利额
	 */
	private Double discountMoney;

	// 当前库存数量
	private Integer stockQty;

	// 合作类型[经销、代销]
	private Integer cooperateTypeId;

	private Integer accountSplitWayId;
	// 结算扣率
	// private Double settleRate;
	private Double serviceRate;
	// 退货原因
	private String returnReason;

	protected Integer goodsStateId;

	// 订单数量
	private Integer orderQty;

	private Integer orderUnitQty;
	// 已收货数量
	private Integer receivedQty;

	private Integer receivedUnitQty;

	// 收货散数
	private Integer receivedMinUnitQty;
	// 按计量单位 可收货数量
	private Integer remainUnitQty;
	private Integer remainMinUnitQty;
	// 可收数量（最小计量单位）
	private Integer remainQty;

	private Integer remainReturnQty;// 剩余退货数量

	private Integer remainReturnableQty;// 剩余可退货数据

	// 应付金额
	private Double payableMoney;

	// 已结算金额
	private Double settledMoney;

	// 未结算金额
	private Double remainMoney;
	private Integer settleStateId;// 结算状态

	private Double pieceUnitDeliverMoney;// 箱计量单位配送费

	private Double deliverMoney;// 配送费

	// 最后进价
	private Double lastPurchasePrice;

	private Long billId;

	private String billCode;

	private String billType;

	private Integer billStateId;

	// 序号
	private Integer ordinalNo;
	// 原单据类型
	private String oldBillTypeId;
	// 原单据Id
	private Long oldBillId;
	// 原单据号
	private String oldBillCode;
	// 原单据序号
	private Integer oldOrdinalNo;
	// 相关单据类型
	private String relatedBillTypeId;
	// 相关单据Id
	private Long relatedBillId;
	// 相关单据号
	private String relatedBillCode;

	private Long relatedDetailId;
	// 相关单据序号
	private Integer relatedOrdinalNo;

	private Integer putawayStateId;

	private Long warehouseId;

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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getSaleTypeId() {
		return saleTypeId;
	}

	public void setSaleTypeId(Integer saleTypeId) {
		this.saleTypeId = saleTypeId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getStoreUseTypeId() {
		return storeUseTypeId;
	}

	public void setStoreUseTypeId(Integer storeUseTypeId) {
		this.storeUseTypeId = storeUseTypeId;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public Integer getShelfTypeId() {
		return shelfTypeId;
	}

	public void setShelfTypeId(Integer shelfTypeId) {
		this.shelfTypeId = shelfTypeId;
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

	public Integer getPackTypeId() {
		return packTypeId;
	}

	public void setPackTypeId(Integer packTypeId) {
		this.packTypeId = packTypeId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getUnitCostPrice() {
		return unitCostPrice;
	}

	public void setUnitCostPrice(Double unitCostPrice) {
		this.unitCostPrice = unitCostPrice;
	}

	public Double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(Double costMoney) {
		this.costMoney = costMoney;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getRebateRate() {
		return rebateRate;
	}

	public void setRebateRate(Double rebateRate) {
		this.rebateRate = rebateRate;
	}

	public Double getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(Double rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public Double getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(Double discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Integer getStockQty() {
		return stockQty;
	}

	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}

	public Integer getCooperateTypeId() {
		return cooperateTypeId;
	}

	public void setCooperateTypeId(Integer cooperateTypeId) {
		this.cooperateTypeId = cooperateTypeId;
	}

	public Integer getAccountSplitWayId() {
		return accountSplitWayId;
	}

	public void setAccountSplitWayId(Integer accountSplitWayId) {
		this.accountSplitWayId = accountSplitWayId;
	}

	public Double getServiceRate() {
		return serviceRate;
	}

	public void setServiceRate(Double serviceRate) {
		this.serviceRate = serviceRate;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public Integer getGoodsStateId() {
		return goodsStateId;
	}

	public void setGoodsStateId(Integer goodsStateId) {
		this.goodsStateId = goodsStateId;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Integer getOrderUnitQty() {
		return orderUnitQty;
	}

	public void setOrderUnitQty(Integer orderUnitQty) {
		this.orderUnitQty = orderUnitQty;
	}

	public Integer getReceivedQty() {
		return receivedQty;
	}

	public void setReceivedQty(Integer receivedQty) {
		this.receivedQty = receivedQty;
	}

	public Integer getReceivedUnitQty() {
		return receivedUnitQty;
	}

	public void setReceivedUnitQty(Integer receivedUnitQty) {
		this.receivedUnitQty = receivedUnitQty;
	}

	public Integer getReceivedMinUnitQty() {
		return receivedMinUnitQty;
	}

	public void setReceivedMinUnitQty(Integer receivedMinUnitQty) {
		this.receivedMinUnitQty = receivedMinUnitQty;
	}

	public Integer getRemainUnitQty() {
		return remainUnitQty;
	}

	public void setRemainUnitQty(Integer remainUnitQty) {
		this.remainUnitQty = remainUnitQty;
	}

	public Integer getRemainMinUnitQty() {
		return remainMinUnitQty;
	}

	public void setRemainMinUnitQty(Integer remainMinUnitQty) {
		this.remainMinUnitQty = remainMinUnitQty;
	}

	public Integer getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Integer remainQty) {
		this.remainQty = remainQty;
	}

	public Integer getRemainReturnQty() {
		return remainReturnQty;
	}

	public void setRemainReturnQty(Integer remainReturnQty) {
		this.remainReturnQty = remainReturnQty;
	}

	public Integer getRemainReturnableQty() {
		return remainReturnableQty;
	}

	public void setRemainReturnableQty(Integer remainReturnableQty) {
		this.remainReturnableQty = remainReturnableQty;
	}

	public Double getPayableMoney() {
		return payableMoney;
	}

	public void setPayableMoney(Double payableMoney) {
		this.payableMoney = payableMoney;
	}

	public Double getSettledMoney() {
		return settledMoney;
	}

	public void setSettledMoney(Double settledMoney) {
		this.settledMoney = settledMoney;
	}

	public Double getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}

	public Integer getSettleStateId() {
		return settleStateId;
	}

	public void setSettleStateId(Integer settleStateId) {
		this.settleStateId = settleStateId;
	}

	public Double getPieceUnitDeliverMoney() {
		return pieceUnitDeliverMoney;
	}

	public void setPieceUnitDeliverMoney(Double pieceUnitDeliverMoney) {
		this.pieceUnitDeliverMoney = pieceUnitDeliverMoney;
	}

	public Double getDeliverMoney() {
		return deliverMoney;
	}

	public void setDeliverMoney(Double deliverMoney) {
		this.deliverMoney = deliverMoney;
	}

	public Double getLastPurchasePrice() {
		return lastPurchasePrice;
	}

	public void setLastPurchasePrice(Double lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}

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

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Integer getBillStateId() {
		return billStateId;
	}

	public void setBillStateId(Integer billStateId) {
		this.billStateId = billStateId;
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public String getOldBillTypeId() {
		return oldBillTypeId;
	}

	public void setOldBillTypeId(String oldBillTypeId) {
		this.oldBillTypeId = oldBillTypeId;
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

	public String getRelatedBillTypeId() {
		return relatedBillTypeId;
	}

	public void setRelatedBillTypeId(String relatedBillTypeId) {
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

	public Long getRelatedDetailId() {
		return relatedDetailId;
	}

	public void setRelatedDetailId(Long relatedDetailId) {
		this.relatedDetailId = relatedDetailId;
	}

	public Integer getRelatedOrdinalNo() {
		return relatedOrdinalNo;
	}

	public void setRelatedOrdinalNo(Integer relatedOrdinalNo) {
		this.relatedOrdinalNo = relatedOrdinalNo;
	}

	public Integer getPutawayStateId() {
		return putawayStateId;
	}

	public void setPutawayStateId(Integer putawayStateId) {
		this.putawayStateId = putawayStateId;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
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

}
