package com.ds365.erp.wms.pda.model.sale;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.CustomerModel;
import com.ds365.erp.wms.pda.model.commons.DeliverStationModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseSaleBillModel extends BaseOperationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2353993567350516882L;

	private String billCode;

	private EnumModel<String> billType;

	private Long oldBillId;

	private String oldBillCode;

	private EnumModel<String> oldBillType;

	private Long relatedBillId;

	private String relatedBillCode;

	private EnumModel<String> relatedBillType;

	private WarehouseModel warehouse;

	private EmployeeModel maker;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;

	private EnumModel<String> billState;

	private String remark;

	private OrganizationModel saleOrganization;

	private OrganizationModel warehouseOrganization;

	private CustomerModel customer;

	private double billMoney;

	private double billDiscountRate;

	private double billDiscountMoney;

	private double totalGoodsDiscountMoney;

	private double totalDiscountMoney;

	private double billRebateRate;

	private double billRebateMoney;

	private double totalGoodsRebateMoney;

	private double totalRebateMoney;

	private double discountCouponMoney;

	private double cashCouponMoney;

	private double rebateBalancePayMoney;

	private double returnBalancePayMoney;

	private double prepayBalancePayMoney;

	private Long rebateBalancePayMoneyId;

	private Long returnBalancePayMoneyId;

	private Long prepayBalancePayMoneyId;

	private double receivableMoney;

	private double payMoney;

	private EnumModel<Integer> payState;

	private EnumModel<Integer> paymentWay;

	private EnumModel<String> billSourceType;

	private DeliverStationModel deliverStation;

	private Long deliverBillId;

	private String deliverBillCode;

	private Long shipmentBillId;

	private String shipmentBillCode;

	private EmployeeModel salesman;

	private double promotionMoney;

	private double giftMoney;

	private Long useCouponId;

	private Long useCouponCodeId;

	private Long giftCouponId;

	private boolean firstOrderFlag;

	private EnumModel<Integer> orderType;

	private EmployeeModel lockor;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date lockTime;

	private EmployeeModel shipmentLockor;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date shipmentLockTime;

	private Long signerId;

	private String signerName;

	private EnumModel<Integer> signerType;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date signTime;

	private EnumModel<Integer> closeState;

	private EnumModel<Integer> closeType;

	private boolean billLockFlag;

	private Long billLockerId;

	private int itemCount;

	private String sysBatchDetails;

	private OrganizationModel organization;

	private Integer unitQty;
	private Integer minUnitQty;

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

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public EmployeeModel getMaker() {
		return maker;
	}

	public void setMaker(EmployeeModel maker) {
		this.maker = maker;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

	public EnumModel<String> getBillState() {
		return billState;
	}

	public void setBillState(EnumModel<String> billState) {
		this.billState = billState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public OrganizationModel getSaleOrganization() {
		return saleOrganization;
	}

	public void setSaleOrganization(OrganizationModel saleOrganization) {
		this.saleOrganization = saleOrganization;
	}

	public OrganizationModel getWarehouseOrganization() {
		return warehouseOrganization;
	}

	public void setWarehouseOrganization(OrganizationModel warehouseOrganization) {
		this.warehouseOrganization = warehouseOrganization;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public double getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}

	public double getBillDiscountRate() {
		return billDiscountRate;
	}

	public void setBillDiscountRate(double billDiscountRate) {
		this.billDiscountRate = billDiscountRate;
	}

	public double getBillDiscountMoney() {
		return billDiscountMoney;
	}

	public void setBillDiscountMoney(double billDiscountMoney) {
		this.billDiscountMoney = billDiscountMoney;
	}

	public double getTotalGoodsDiscountMoney() {
		return totalGoodsDiscountMoney;
	}

	public void setTotalGoodsDiscountMoney(double totalGoodsDiscountMoney) {
		this.totalGoodsDiscountMoney = totalGoodsDiscountMoney;
	}

	public double getTotalDiscountMoney() {
		return totalDiscountMoney;
	}

	public void setTotalDiscountMoney(double totalDiscountMoney) {
		this.totalDiscountMoney = totalDiscountMoney;
	}

	public double getBillRebateRate() {
		return billRebateRate;
	}

	public void setBillRebateRate(double billRebateRate) {
		this.billRebateRate = billRebateRate;
	}

	public double getBillRebateMoney() {
		return billRebateMoney;
	}

	public void setBillRebateMoney(double billRebateMoney) {
		this.billRebateMoney = billRebateMoney;
	}

	public double getTotalGoodsRebateMoney() {
		return totalGoodsRebateMoney;
	}

	public void setTotalGoodsRebateMoney(double totalGoodsRebateMoney) {
		this.totalGoodsRebateMoney = totalGoodsRebateMoney;
	}

	public double getTotalRebateMoney() {
		return totalRebateMoney;
	}

	public void setTotalRebateMoney(double totalRebateMoney) {
		this.totalRebateMoney = totalRebateMoney;
	}

	public double getDiscountCouponMoney() {
		return discountCouponMoney;
	}

	public void setDiscountCouponMoney(double discountCouponMoney) {
		this.discountCouponMoney = discountCouponMoney;
	}

	public double getCashCouponMoney() {
		return cashCouponMoney;
	}

	public void setCashCouponMoney(double cashCouponMoney) {
		this.cashCouponMoney = cashCouponMoney;
	}

	public double getRebateBalancePayMoney() {
		return rebateBalancePayMoney;
	}

	public void setRebateBalancePayMoney(double rebateBalancePayMoney) {
		this.rebateBalancePayMoney = rebateBalancePayMoney;
	}

	public double getReturnBalancePayMoney() {
		return returnBalancePayMoney;
	}

	public void setReturnBalancePayMoney(double returnBalancePayMoney) {
		this.returnBalancePayMoney = returnBalancePayMoney;
	}

	public double getPrepayBalancePayMoney() {
		return prepayBalancePayMoney;
	}

	public void setPrepayBalancePayMoney(double prepayBalancePayMoney) {
		this.prepayBalancePayMoney = prepayBalancePayMoney;
	}

	public Long getRebateBalancePayMoneyId() {
		return rebateBalancePayMoneyId;
	}

	public void setRebateBalancePayMoneyId(Long rebateBalancePayMoneyId) {
		this.rebateBalancePayMoneyId = rebateBalancePayMoneyId;
	}

	public Long getReturnBalancePayMoneyId() {
		return returnBalancePayMoneyId;
	}

	public void setReturnBalancePayMoneyId(Long returnBalancePayMoneyId) {
		this.returnBalancePayMoneyId = returnBalancePayMoneyId;
	}

	public Long getPrepayBalancePayMoneyId() {
		return prepayBalancePayMoneyId;
	}

	public void setPrepayBalancePayMoneyId(Long prepayBalancePayMoneyId) {
		this.prepayBalancePayMoneyId = prepayBalancePayMoneyId;
	}

	public double getReceivableMoney() {
		return receivableMoney;
	}

	public void setReceivableMoney(double receivableMoney) {
		this.receivableMoney = receivableMoney;
	}

	public double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	public EnumModel<Integer> getPayState() {
		return payState;
	}

	public void setPayState(EnumModel<Integer> payState) {
		this.payState = payState;
	}

	public EnumModel<Integer> getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(EnumModel<Integer> paymentWay) {
		this.paymentWay = paymentWay;
	}

	public EnumModel<String> getBillSourceType() {
		return billSourceType;
	}

	public void setBillSourceType(EnumModel<String> billSourceType) {
		this.billSourceType = billSourceType;
	}

	public DeliverStationModel getDeliverStation() {
		return deliverStation;
	}

	public void setDeliverStation(DeliverStationModel deliverStation) {
		this.deliverStation = deliverStation;
	}

	public Long getDeliverBillId() {
		return deliverBillId;
	}

	public void setDeliverBillId(Long deliverBillId) {
		this.deliverBillId = deliverBillId;
	}

	public String getDeliverBillCode() {
		return deliverBillCode;
	}

	public void setDeliverBillCode(String deliverBillCode) {
		this.deliverBillCode = deliverBillCode;
	}

	public Long getShipmentBillId() {
		return shipmentBillId;
	}

	public void setShipmentBillId(Long shipmentBillId) {
		this.shipmentBillId = shipmentBillId;
	}

	public String getShipmentBillCode() {
		return shipmentBillCode;
	}

	public void setShipmentBillCode(String shipmentBillCode) {
		this.shipmentBillCode = shipmentBillCode;
	}

	public EmployeeModel getSalesman() {
		return salesman;
	}

	public void setSalesman(EmployeeModel salesman) {
		this.salesman = salesman;
	}

	public double getPromotionMoney() {
		return promotionMoney;
	}

	public void setPromotionMoney(double promotionMoney) {
		this.promotionMoney = promotionMoney;
	}

	public double getGiftMoney() {
		return giftMoney;
	}

	public void setGiftMoney(double giftMoney) {
		this.giftMoney = giftMoney;
	}

	public Long getUseCouponId() {
		return useCouponId;
	}

	public void setUseCouponId(Long useCouponId) {
		this.useCouponId = useCouponId;
	}

	public Long getUseCouponCodeId() {
		return useCouponCodeId;
	}

	public void setUseCouponCodeId(Long useCouponCodeId) {
		this.useCouponCodeId = useCouponCodeId;
	}

	public Long getGiftCouponId() {
		return giftCouponId;
	}

	public void setGiftCouponId(Long giftCouponId) {
		this.giftCouponId = giftCouponId;
	}

	public boolean isFirstOrderFlag() {
		return firstOrderFlag;
	}

	public void setFirstOrderFlag(boolean firstOrderFlag) {
		this.firstOrderFlag = firstOrderFlag;
	}

	public EnumModel<Integer> getOrderType() {
		return orderType;
	}

	public void setOrderType(EnumModel<Integer> orderType) {
		this.orderType = orderType;
	}

	public EmployeeModel getLockor() {
		return lockor;
	}

	public void setLockor(EmployeeModel lockor) {
		this.lockor = lockor;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public EmployeeModel getShipmentLockor() {
		return shipmentLockor;
	}

	public void setShipmentLockor(EmployeeModel shipmentLockor) {
		this.shipmentLockor = shipmentLockor;
	}

	public Date getShipmentLockTime() {
		return shipmentLockTime;
	}

	public void setShipmentLockTime(Date shipmentLockTime) {
		this.shipmentLockTime = shipmentLockTime;
	}

	public Long getSignerId() {
		return signerId;
	}

	public void setSignerId(Long signerId) {
		this.signerId = signerId;
	}

	public String getSignerName() {
		return signerName;
	}

	public void setSignerName(String signerName) {
		this.signerName = signerName;
	}

	public EnumModel<Integer> getSignerType() {
		return signerType;
	}

	public void setSignerType(EnumModel<Integer> signerType) {
		this.signerType = signerType;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public EnumModel<Integer> getCloseState() {
		return closeState;
	}

	public void setCloseState(EnumModel<Integer> closeState) {
		this.closeState = closeState;
	}

	public EnumModel<Integer> getCloseType() {
		return closeType;
	}

	public void setCloseType(EnumModel<Integer> closeType) {
		this.closeType = closeType;
	}

	public boolean isBillLockFlag() {
		return billLockFlag;
	}

	public void setBillLockFlag(boolean billLockFlag) {
		this.billLockFlag = billLockFlag;
	}

	public Long getBillLockerId() {
		return billLockerId;
	}

	public void setBillLockerId(Long billLockerId) {
		this.billLockerId = billLockerId;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getSysBatchDetails() {
		return sysBatchDetails;
	}

	public void setSysBatchDetails(String sysBatchDetails) {
		this.sysBatchDetails = sysBatchDetails;
	}

	public OrganizationModel getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
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
