package com.ds365.erp.wms.pda.model.sale;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.BaseCheckFlagModel;
import com.ds365.erp.wms.pda.model.commons.SupplierModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

//	SaleOrderDetailModel
public class BaseSaleDetailModel extends BaseCheckFlagModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4394582997069540347L;

	private String billCode;

	private EnumModel<String> billType;

	private String ordinalNo;

	private Long oldBillId;

	private String oldBillCode;

	private EnumModel<String> oldBillType;

	private Integer oldOrdinalNo;

	private Long relatedBillId;

	private String relatedBillCode;

	private EnumModel<String> relatedBillType;

	private Integer relatedOrdinalNo;

	private Long relatedDetailId;

	private SaleOrderBillModel bill;

	private GoodsSkuModel sku;

	private String skuName;

	private String goodsName;

	private String spec;

	private String barcode;

	private GoodsPackModel pack;

	private String unitName;
	private String minUnitName;

	private Integer specQty;

	private double unitPrice;

	private double price;

	private Integer unitQty;

	private Integer minUnitQty;

	private Integer qty;

	private String receivableMoney;

	private double money;

	private GoodsPackModel baseWeightPack;

	private Integer baseWeightSpecQty;

	private double baseWeight;

	private double weight;

	private GoodsPackModel baseVolumePack;

	private int baseVolumeSpecQty;

	private double baseVolume;

	private double volume;

	private double discountRate;

	private double discountMoney;

	private double rebateRate;

	private double rebateMoney;

	private EnumModel<Integer> saleType;

	private EnumModel<Integer> goodsState;

	private Integer returnedQty;

	private Integer remainReturnQty;

	private Integer remainReturnableQty;

	private WarehouseModel warehouse;

	private SupplierModel supplier;

	private double costPrice;

	private double costMoney;

	private int profit;

	private EnumModel<Integer> cooperateType;

	private EnumModel<Integer> accountSplitWay;

	private String serviceRate;

	private EnumModel<Integer> returnReason;

	private String returnDescription;

	private Integer orderUnitQty;

	private Integer orderMinUnitQty;

	private Integer orderQty;

	private Integer signUnitQty;

	private Integer signMinUnitQty;

	private Integer signQty;

	private ShelfModel shelf;

	private StoreModel store;

	private String produceBatchNo;

	private String sysBatchNo;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public EnumModel<String> getBillType() {
		return billType;
	}

	public void setBillType(EnumModel<String> billType) {
		this.billType = billType;
	}

	public String getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(String ordinalNo) {
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

	public SaleOrderBillModel getBill() {
		return bill;
	}

	public void setBill(SaleOrderBillModel bill) {
		this.bill = bill;
	}

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
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

	public String getMinUnitName() {
		return minUnitName;
	}

	public void setMinUnitName(String minUnitName) {
		this.minUnitName = minUnitName;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getReceivableMoney() {
		return receivableMoney;
	}

	public void setReceivableMoney(String receivableMoney) {
		this.receivableMoney = receivableMoney;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public GoodsPackModel getBaseWeightPack() {
		return baseWeightPack;
	}

	public void setBaseWeightPack(GoodsPackModel baseWeightPack) {
		this.baseWeightPack = baseWeightPack;
	}

	public Integer getBaseWeightSpecQty() {
		return baseWeightSpecQty;
	}

	public void setBaseWeightSpecQty(Integer baseWeightSpecQty) {
		this.baseWeightSpecQty = baseWeightSpecQty;
	}

	public double getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(double baseWeight) {
		this.baseWeight = baseWeight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public GoodsPackModel getBaseVolumePack() {
		return baseVolumePack;
	}

	public void setBaseVolumePack(GoodsPackModel baseVolumePack) {
		this.baseVolumePack = baseVolumePack;
	}

	public int getBaseVolumeSpecQty() {
		return baseVolumeSpecQty;
	}

	public void setBaseVolumeSpecQty(int baseVolumeSpecQty) {
		this.baseVolumeSpecQty = baseVolumeSpecQty;
	}

	public double getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(double baseVolume) {
		this.baseVolume = baseVolume;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public double getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(double discountMoney) {
		this.discountMoney = discountMoney;
	}

	public double getRebateRate() {
		return rebateRate;
	}

	public void setRebateRate(double rebateRate) {
		this.rebateRate = rebateRate;
	}

	public double getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(double rebateMoney) {
		this.rebateMoney = rebateMoney;
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

	public Integer getReturnedQty() {
		return returnedQty;
	}

	public void setReturnedQty(Integer returnedQty) {
		this.returnedQty = returnedQty;
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

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(double costMoney) {
		this.costMoney = costMoney;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public EnumModel<Integer> getCooperateType() {
		return cooperateType;
	}

	public void setCooperateType(EnumModel<Integer> cooperateType) {
		this.cooperateType = cooperateType;
	}

	public EnumModel<Integer> getAccountSplitWay() {
		return accountSplitWay;
	}

	public void setAccountSplitWay(EnumModel<Integer> accountSplitWay) {
		this.accountSplitWay = accountSplitWay;
	}

	public String getServiceRate() {
		return serviceRate;
	}

	public void setServiceRate(String serviceRate) {
		this.serviceRate = serviceRate;
	}

	public EnumModel<Integer> getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(EnumModel<Integer> returnReason) {
		this.returnReason = returnReason;
	}

	public String getReturnDescription() {
		return returnDescription;
	}

	public void setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
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

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Integer getSignUnitQty() {
		return signUnitQty;
	}

	public void setSignUnitQty(Integer signUnitQty) {
		this.signUnitQty = signUnitQty;
	}

	public Integer getSignMinUnitQty() {
		return signMinUnitQty;
	}

	public void setSignMinUnitQty(Integer signMinUnitQty) {
		this.signMinUnitQty = signMinUnitQty;
	}

	public Integer getSignQty() {
		return signQty;
	}

	public void setSignQty(Integer signQty) {
		this.signQty = signQty;
	}

	public ShelfModel getShelf() {
		return shelf;
	}

	public void setShelf(ShelfModel shelf) {
		this.shelf = shelf;
	}

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public String getProduceBatchNo() {
		return produceBatchNo;
	}

	public void setProduceBatchNo(String produceBatchNo) {
		this.produceBatchNo = produceBatchNo;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

}
