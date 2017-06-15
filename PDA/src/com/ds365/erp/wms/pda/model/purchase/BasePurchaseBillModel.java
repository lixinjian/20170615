package com.ds365.erp.wms.pda.model.purchase;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.commons.SupplierModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BasePurchaseBillModel extends BaseOperationModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9036636995813191970L;

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

	private OrganizationModel purchaseOrganization;

	private OrganizationModel warehouseOrganization;

	// 业务员ID
	private EmployeeModel salesman;// 关联业务员

	/**
	 * 组织ID
	 */
	private OrganizationModel organization;

	private double payableMoney;

	private double giftMoney;

	private double rebateRate;

	private double rebateMoney;

	private double discountRate;

	private double discountMoney;

	private double billMoney;

	private SupplierModel supplier;

	private String address;

	private String phoneNo;

	private int itemCount;

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

	public EmployeeModel getSalesman() {
		return salesman;
	}

	public void setSalesman(EmployeeModel salesman) {
		this.salesman = salesman;
	}

	public OrganizationModel getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
	}

	public double getPayableMoney() {
		return payableMoney;
	}

	public void setPayableMoney(double payableMoney) {
		this.payableMoney = payableMoney;
	}

	public double getGiftMoney() {
		return giftMoney;
	}

	public void setGiftMoney(double giftMoney) {
		this.giftMoney = giftMoney;
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

	public double getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

}