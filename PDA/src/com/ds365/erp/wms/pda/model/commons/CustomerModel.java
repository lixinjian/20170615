package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerModel extends BaseActiveModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5541407734842539979L;

	private String code;
	
	private String name;
	
	private String helpCode;
	
	private RegionModel region;
	
	private String address;
	
	private EmployeeModel salesman;	
	
	private Integer customerLevel;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date firstOrderTime;
	
	private EnumModel<Integer> salesmanType;

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

	public RegionModel getRegion() {
		return region;
	}

	public void setRegion(RegionModel region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EmployeeModel getSalesman() {
		return salesman;
	}

	public void setSalesman(EmployeeModel salesman) {
		this.salesman = salesman;
	}

	public Integer getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(Integer customerLevel) {
		this.customerLevel = customerLevel;
	}

	public Date getFirstOrderTime() {
		return firstOrderTime;
	}

	public void setFirstOrderTime(Date firstOrderTime) {
		this.firstOrderTime = firstOrderTime;
	}

	public EnumModel<Integer> getSalesmanType() {
		return salesmanType;
	}

	public void setSalesmanType(EnumModel<Integer> salesmanType) {
		this.salesmanType = salesmanType;
	}

}