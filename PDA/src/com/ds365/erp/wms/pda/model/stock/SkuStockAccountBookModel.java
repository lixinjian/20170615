package com.ds365.erp.wms.pda.model.stock;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.base.model.BaseCreateOperationModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SkuStockAccountBookModel extends BaseCreateOperationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8139262559006317892L;

	private Long entId;// 供应商或者客户

	private String entCode;

	private String entName;

	private OrganizationModel organization;// 采购组织或者销售组织

	private GoodsSkuModel sku;

	private StoreModel store;

	private String sysBatchNo;

	// private PurchaseEnterBill bill;

	private EnumModel<Integer> saleType;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date bizDate;

	private Integer qty;

	private Double price;

	private Double money;

	/*
	 * private BigDecimal outPrice;
	 * 
	 * private Integer outQty;
	 * 
	 * private BigDecimal outMoney;
	 */

	private Double costPrice;

	private Double costMoney;

	private Double profit;

	private GoodsPackModel pack;

	private Integer specQty;

	private String unitName;

	private String spec;

	private EnumModel<Integer> operateType;

	private Integer storeRemainQty;

	private Double storeRemainMoney;

	private Integer warehouseRemainQty;

	private Double warehouseRemainMoney;

	private Date makeTime;

	private EmployeeModel maker;

	private EmployeeModel salesman;

	private Long ordinalNo;

	private String remark;

	private String forwardNo;

	private int forwardYear;

	private int forwardMonth;

	private Long billId;

	private String billCode;

	private EnumModel<String> billType;

	private Long oldBillId;

	private String oldBillCode;

	private EnumModel<String> oldBillType;

	private Long relatedBillId;

	private EnumModel<String> relatedBillType;

	private String relatedBillCode;

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public OrganizationModel getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
	}

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public EnumModel<Integer> getSaleType() {
		return saleType;
	}

	public void setSaleType(EnumModel<Integer> saleType) {
		this.saleType = saleType;
	}

	public EnumModel<Integer> getOperateType() {
		return operateType;
	}

	public void setOperateType(EnumModel<Integer> operateType) {
		this.operateType = operateType;
	}
	
	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(Double costMoney) {
		this.costMoney = costMoney;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public GoodsPackModel getPack() {
		return pack;
	}

	public void setPack(GoodsPackModel pack) {
		this.pack = pack;
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

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getStoreRemainQty() {
		return storeRemainQty;
	}

	public void setStoreRemainQty(Integer storeRemainQty) {
		this.storeRemainQty = storeRemainQty;
	}

	public Double getStoreRemainMoney() {
		return storeRemainMoney;
	}

	public void setStoreRemainMoney(Double storeRemainMoney) {
		this.storeRemainMoney = storeRemainMoney;
	}

	public Integer getWarehouseRemainQty() {
		return warehouseRemainQty;
	}

	public void setWarehouseRemainQty(Integer warehouseRemainQty) {
		this.warehouseRemainQty = warehouseRemainQty;
	}

	public Double getWarehouseRemainMoney() {
		return warehouseRemainMoney;
	}

	public void setWarehouseRemainMoney(Double warehouseRemainMoney) {
		this.warehouseRemainMoney = warehouseRemainMoney;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

	public EmployeeModel getMaker() {
		return maker;
	}

	public void setMaker(EmployeeModel maker) {
		this.maker = maker;
	}

	public EmployeeModel getSalesman() {
		return salesman;
	}

	public void setSalesman(EmployeeModel salesman) {
		this.salesman = salesman;
	}

	public Long getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Long ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getForwardNo() {
		return forwardNo;
	}

	public void setForwardNo(String forwardNo) {
		this.forwardNo = forwardNo;
	}

	public int getForwardYear() {
		return forwardYear;
	}

	public void setForwardYear(int forwardYear) {
		this.forwardYear = forwardYear;
	}

	public int getForwardMonth() {
		return forwardMonth;
	}

	public void setForwardMonth(int forwardMonth) {
		this.forwardMonth = forwardMonth;
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

	public EnumModel<String> getBillType() {
		return billType;
	}

	public void setBillType(EnumModel<String> billType) {
		this.billType = billType;
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

	public Long getRelatedBillId() {
		return relatedBillId;
	}

	public void setRelatedBillId(Long relatedBillId) {
		this.relatedBillId = relatedBillId;
	}

	public EnumModel<String> getRelatedBillType() {
		return relatedBillType;
	}

	public void setRelatedBillType(EnumModel<String> relatedBillType) {
		this.relatedBillType = relatedBillType;
	}

	public String getRelatedBillCode() {
		return relatedBillCode;
	}

	public void setRelatedBillCode(String relatedBillCode) {
		this.relatedBillCode = relatedBillCode;
	}
}
