package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;

public class SupplierModel extends BaseActiveModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3969791012195729343L;

	private String code;

	private String name;

	private String helpCode;

	private String accountPeriod;

	private String accountTerm;

	private String settlePeriod;

	private EnumModel<Integer> cooperateType;

	private EnumModel<Integer> supplierType;

	private EmployeeModel salesman;

	private String paymentWay;

	private EnumModel<Integer> settleType;

	private boolean auditFlag;

	private OrganizationModel organization;

	private String address;

	private RegionModel region;

	public SupplierModel() {
		super();
	}
	
	public SupplierModel(Long id) {
		super();
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getAccountTerm() {
		return accountTerm;
	}

	public void setAccountTerm(String accountTerm) {
		this.accountTerm = accountTerm;
	}

	public String getSettlePeriod() {
		return settlePeriod;
	}

	public void setSettlePeriod(String settlePeriod) {
		this.settlePeriod = settlePeriod;
	}

	public EnumModel<Integer> getCooperateType() {
		return cooperateType;
	}

	public void setCooperateType(EnumModel<Integer> cooperateType) {
		this.cooperateType = cooperateType;
	}

	public EnumModel<Integer> getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(EnumModel<Integer> supplierType) {
		this.supplierType = supplierType;
	}

	public EmployeeModel getSalesman() {
		return salesman;
	}

	public void setSalesman(EmployeeModel salesman) {
		this.salesman = salesman;
	}

	public String getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	public EnumModel<Integer> getSettleType() {
		return settleType;
	}

	public void setSettleType(EnumModel<Integer> settleType) {
		this.settleType = settleType;
	}

	public boolean isAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(boolean auditFlag) {
		this.auditFlag = auditFlag;
	}

	public OrganizationModel getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public RegionModel getRegion() {
		return region;
	}

	public void setRegion(RegionModel region) {
		this.region = region;
	}

}