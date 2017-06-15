package com.ds365.erp.wms.pda.model.purchase;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.BaseCheckFlagModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.goods.GoodsModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BasePurchaseDetailModel extends BaseCheckFlagModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8496400214673688817L;

	private String billCode;

	private EnumModel<String> billType;

	private Integer ordinalNo;

	private String oldBillId;

	private String oldBillCode;

	private EnumModel<String> oldBillType;

	private Integer oldOrdinalNo;

	private Long relatedBillId;

	private String relatedBillCode;

	private EnumModel<String> relatedBillType;

	private Integer relatedOrdinalNo;

	private Long relatedDetailId;

	private GoodsSkuModel sku;
	
	private GoodsModel goods;

	private EnumModel<Integer> saleType;

	private StoreModel store;

	private ShelfModel shelf;

	private String shelfCode;

	private String sysBatchNo;

	private String produceBatchNo;

	private int guaranteePeriod;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date expireDate;

	private GoodsPackModel pack;

	private String unitName;
	private String minUnitName;
	private Integer specQty;

	private String spec;

	private EnumModel<Integer> packType;
	
	private Integer qty;

	private Integer minUnitQty;

	private Integer unitQty;

	private double price;

	private double unitPrice;

	private double costPrice;

	private double unitCostPrice;

	private double costMoney;

	private double lastPurchasePrice;

	private double money;

	private double payableMoney;

	private double rebateRate;

	private double rebateMoney;

	private double discountRate;

	private double discountMoney;

	private Integer stockQty;

	private EnumModel<Integer> cooperateType;

	private EnumModel<Integer> accountSplitWay;

	private double serviceRate;

	private String returnReason;

	private EnumModel<Integer> goodsState;

	private WarehouseModel warehouse;

	private Long contractDetailId;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date deliverDate;

	private double purchasePrice;

	private double purchaseUnitPrice;

	private Integer receivedQty;

	private Integer receivedUnitQty;

	private Integer receivedMinUnitQty;

	private Long contractBillId;

	private String contractBillCode;

	private String contractNo;

	private Long subContractBillId;

	private String subContractBillCode;

	private String subContractNo;

	private Integer returnedQty;

	private Integer returnedUnitQty;

	private Integer returnedMinUnitQty;

	private Integer deliveredQty;

	private Integer deliveredUnitQty;

	private Integer deliveredMinUnitQty;

	private EnumModel<Integer> billState;

	private OrganizationModel purchaseOrganization;

	private OrganizationModel warehouseOrganization;

	private double giftMoney;

	public BasePurchaseDetailModel() {
		super();
	}

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

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public String getOldBillId() {
		return oldBillId;
	}

	public void setOldBillId(String oldBillId) {
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

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public EnumModel<Integer> getSaleType() {
		return saleType;
	}

	public void setSaleType(EnumModel<Integer> saleType) {
		this.saleType = saleType;
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

	public String getProduceBatchNo() {
		return produceBatchNo;
	}

	public void setProduceBatchNo(String produceBatchNo) {
		this.produceBatchNo = produceBatchNo;
	}

	public int getGuaranteePeriod() {
		return guaranteePeriod;
	}

	public void setGuaranteePeriod(int guaranteePeriod) {
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

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public EnumModel<Integer> getPackType() {
		return packType;
	}

	public void setPackType(EnumModel<Integer> packType) {
		this.packType = packType;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getUnitCostPrice() {
		return unitCostPrice;
	}

	public void setUnitCostPrice(double unitCostPrice) {
		this.unitCostPrice = unitCostPrice;
	}

	public double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(double costMoney) {
		this.costMoney = costMoney;
	}

	public double getLastPurchasePrice() {
		return lastPurchasePrice;
	}

	public void setLastPurchasePrice(double lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getPayableMoney() {
		return payableMoney;
	}

	public void setPayableMoney(double payableMoney) {
		this.payableMoney = payableMoney;
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

	public Integer getStockQty() {
		return stockQty;
	}

	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
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

	public double getServiceRate() {
		return serviceRate;
	}

	public void setServiceRate(double serviceRate) {
		this.serviceRate = serviceRate;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public EnumModel<Integer> getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(EnumModel<Integer> goodsState) {
		this.goodsState = goodsState;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public Long getContractDetailId() {
		return contractDetailId;
	}

	public void setContractDetailId(Long contractDetailId) {
		this.contractDetailId = contractDetailId;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}

	public void setPurchaseUnitPrice(double purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
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

	public Long getContractBillId() {
		return contractBillId;
	}

	public void setContractBillId(Long contractBillId) {
		this.contractBillId = contractBillId;
	}

	public String getContractBillCode() {
		return contractBillCode;
	}

	public void setContractBillCode(String contractBillCode) {
		this.contractBillCode = contractBillCode;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Long getSubContractBillId() {
		return subContractBillId;
	}

	public void setSubContractBillId(Long subContractBillId) {
		this.subContractBillId = subContractBillId;
	}

	public String getSubContractBillCode() {
		return subContractBillCode;
	}

	public void setSubContractBillCode(String subContractBillCode) {
		this.subContractBillCode = subContractBillCode;
	}

	public String getSubContractNo() {
		return subContractNo;
	}

	public void setSubContractNo(String subContractNo) {
		this.subContractNo = subContractNo;
	}

	public Integer getReturnedQty() {
		return returnedQty;
	}

	public void setReturnedQty(Integer returnedQty) {
		this.returnedQty = returnedQty;
	}

	public Integer getReturnedUnitQty() {
		return returnedUnitQty;
	}

	public void setReturnedUnitQty(Integer returnedUnitQty) {
		this.returnedUnitQty = returnedUnitQty;
	}

	public Integer getReturnedMinUnitQty() {
		return returnedMinUnitQty;
	}

	public void setReturnedMinUnitQty(Integer returnedMinUnitQty) {
		this.returnedMinUnitQty = returnedMinUnitQty;
	}

	public Integer getDeliveredQty() {
		return deliveredQty;
	}

	public void setDeliveredQty(Integer deliveredQty) {
		this.deliveredQty = deliveredQty;
	}

	public Integer getDeliveredUnitQty() {
		return deliveredUnitQty;
	}

	public void setDeliveredUnitQty(Integer deliveredUnitQty) {
		this.deliveredUnitQty = deliveredUnitQty;
	}

	public Integer getDeliveredMinUnitQty() {
		return deliveredMinUnitQty;
	}

	public void setDeliveredMinUnitQty(Integer deliveredMinUnitQty) {
		this.deliveredMinUnitQty = deliveredMinUnitQty;
	}

	public EnumModel<Integer> getBillState() {
		return billState;
	}

	public void setBillState(EnumModel<Integer> billState) {
		this.billState = billState;
	}

	public OrganizationModel getPurchaseOrganization() {
		return purchaseOrganization;
	}

	public void setPurchaseOrganization(OrganizationModel purchaseOrganization) {
		this.purchaseOrganization = purchaseOrganization;
	}

	public OrganizationModel getWarehouseOrganization() {
		return warehouseOrganization;
	}

	public void setWarehouseOrganization(OrganizationModel warehouseOrganization) {
		this.warehouseOrganization = warehouseOrganization;
	}

	public double getGiftMoney() {
		return giftMoney;
	}

	public void setGiftMoney(double giftMoney) {
		this.giftMoney = giftMoney;
	}

	
	public String getMinUnitName() {
		return minUnitName;
	}

	public void setMinUnitName(String minUnitName) {
		this.minUnitName = minUnitName;
	}

	public GoodsModel getGoods() {
		return goods;
	}

	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}
}