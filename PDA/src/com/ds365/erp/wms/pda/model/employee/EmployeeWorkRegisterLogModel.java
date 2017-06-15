package com.ds365.erp.wms.pda.model.employee;

import java.util.Date;

import com.ds365.commons.base.model.BaseCreateOperationModel;
import com.ds365.commons.enums.EnumModel;

public class EmployeeWorkRegisterLogModel extends BaseCreateOperationModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3945335268141882634L;

	private EmployeeModel employee;

	private EnumModel<Integer> registerType;

	private Date registerTime;

	public EmployeeModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}

	public EnumModel<Integer> getRegisterType() {
		return registerType;
	}

	public void setRegisterType(EnumModel<Integer> registerType) {
		this.registerType = registerType;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

}
