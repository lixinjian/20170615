package com.ds365.erp.wms.pda.model.employee;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.commons.enums.EnumModel;

public class EmployeeWorkStateModel extends BaseOperationModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3616222511972304320L;

	private EnumModel<Integer> workState;

	private EmployeeModel employee;

	public EnumModel<Integer> getWorkState() {
		return workState;
	}

	public void setWorkState(EnumModel<Integer> workState) {
		this.workState = workState;
	}

	public EmployeeModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}

}
