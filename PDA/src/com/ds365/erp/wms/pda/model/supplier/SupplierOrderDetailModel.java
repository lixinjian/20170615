package com.ds365.erp.wms.pda.model.supplier;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.BaseCheckFlagModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SupplierOrderDetailModel extends BaseCheckFlagModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7590198353484937240L;
	
	private GoodsSkuModel sku;
	private EnumModel<Integer> saleType;
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
	private GoodsPackModel pack;

	private String unitName;

	private Integer specQty;

	private String spec;

	private EnumModel<Integer> packType;
	// 按最小计量单位计算的总数量
	private Integer qty;
	private Integer minUnitQty;// 散数
	// 按当前计量单位计算的数量
	private Integer unitQty;
	// 按最小计量单位计算的单价
	private BigDecimal price;
	// 按当前计量单位计算的单价
	private BigDecimal unitPrice;
	// 存货成本单价
	private double costPrice;
	// 按当前计量单位计算的成本单价
	private BigDecimal unitCostPrice;
	// 成本金额
	private BigDecimal costMoney;
	// 金额
	private BigDecimal money;

	// 应付金额
	private BigDecimal payableMoney;

	/**
	 * 商品返利率
	 */
	private BigDecimal rebateRate;

	/**
	 * 商品返利率费用
	 */
	private BigDecimal rebateMoney;

	/**
	 * 商品折扣率
	 */
	private BigDecimal discountRate;

	/**
	 * 商品返利额
	 */
	private BigDecimal discountMoney;

	// 合作类型[经销、代销]
	private EnumModel<Integer> cooperateType;

	private EnumModel<Integer> accountSplitWay;
	// 结算扣率
	private BigDecimal serviceRate;
	// 退货原因
	private String returnReason;

	private EnumModel<Integer> goodsState;

	/**
	 * 合同ID
	 */
	private Long contractBillId;

	/**
	 * 合同单据编号
	 */
	private String contractBillCode;

	/**
	 * 合同号
	 */
	private String contractNo;

	/**
	 * 合同ID
	 */
	// private Long subContractBillId;

	/**
	 * 合同单据编号
	 */
	// private String subContractBillCode;

	/**
	 * 合同号
	 */
	// private String subContractNo;

	/**
	 * 合同明细ID
	 */
	private Long contractDetailId;

	private String skuCode;

	private String skuName;

	private String barcode;

	private String goodsName;

	// 发货数量
	private Integer deliverQty;

	// 发货件数
	private Integer deliverUnitQty;

	// 发货散数
	private Integer deliverMinUnitQty;

	private BigDecimal purchasePrice;
	
	private BigDecimal purchaseUnitPrice;
	
	private SupplierOrderBillModel bill;

	private String billCode;

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
	
	private List<PurchaseEnterDetailModel> purchaseEnterDetails;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getUnitCostPrice() {
		return unitCostPrice;
	}

	public void setUnitCostPrice(BigDecimal unitCostPrice) {
		this.unitCostPrice = unitCostPrice;
	}

	public BigDecimal getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(BigDecimal costMoney) {
		this.costMoney = costMoney;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getPayableMoney() {
		return payableMoney;
	}

	public void setPayableMoney(BigDecimal payableMoney) {
		this.payableMoney = payableMoney;
	}

	public BigDecimal getRebateRate() {
		return rebateRate;
	}

	public void setRebateRate(BigDecimal rebateRate) {
		this.rebateRate = rebateRate;
	}

	public BigDecimal getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(BigDecimal rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public BigDecimal getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(BigDecimal discountMoney) {
		this.discountMoney = discountMoney;
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

	public BigDecimal getServiceRate() {
		return serviceRate;
	}

	public void setServiceRate(BigDecimal serviceRate) {
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

	public Long getContractDetailId() {
		return contractDetailId;
	}

	public void setContractDetailId(Long contractDetailId) {
		this.contractDetailId = contractDetailId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getDeliverQty() {
		return deliverQty;
	}

	public void setDeliverQty(Integer deliverQty) {
		this.deliverQty = deliverQty;
	}

	public Integer getDeliverUnitQty() {
		return deliverUnitQty;
	}

	public void setDeliverUnitQty(Integer deliverUnitQty) {
		this.deliverUnitQty = deliverUnitQty;
	}

	public Integer getDeliverMinUnitQty() {
		return deliverMinUnitQty;
	}

	public void setDeliverMinUnitQty(Integer deliverMinUnitQty) {
		this.deliverMinUnitQty = deliverMinUnitQty;
	}

	public SupplierOrderBillModel getBill() {
		return bill;
	}

	public void setBill(SupplierOrderBillModel bill) {
		this.bill = bill;
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

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}

	public void setPurchaseUnitPrice(BigDecimal purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}

	public List<PurchaseEnterDetailModel> getPurchaseEnterDetails() {
		return purchaseEnterDetails;
	}

	public void setPurchaseEnterDetails(List<PurchaseEnterDetailModel> purchaseEnterDetails) {
		this.purchaseEnterDetails = purchaseEnterDetails;
	}

}
