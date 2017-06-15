package com.ds365.erp.wms.pda.model.sale;

import java.io.Serializable;
import java.util.Date;

import com.ds365.erp.wms.pda.model.commons.DeliverLineModel;
import com.ds365.erp.wms.pda.model.commons.VehicleModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseSaleOutBillModel extends BaseSaleBillModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -71780184402512709L;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date outDate;// 可以留着，因为有makeTime

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date orderMakeTime;
	// 车辆
	private VehicleModel vehicle;

	// 司机
	private EmployeeModel driver;

	// 线路
	private DeliverLineModel line;

	// 验收人
	private EmployeeModel examiner;

	// 应收未结算金额
	private Double remainMoney;

	// 历史未结算金额
	private Double oldRemainMoney;

	// 应收已结算金额
	private Double settledMoney;

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Date getOrderMakeTime() {
		return orderMakeTime;
	}

	public void setOrderMakeTime(Date orderMakeTime) {
		this.orderMakeTime = orderMakeTime;
	}

	public VehicleModel getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleModel vehicle) {
		this.vehicle = vehicle;
	}

	public EmployeeModel getDriver() {
		return driver;
	}

	public void setDriver(EmployeeModel driver) {
		this.driver = driver;
	}

	public DeliverLineModel getLine() {
		return line;
	}

	public void setLine(DeliverLineModel line) {
		this.line = line;
	}

	public EmployeeModel getExaminer() {
		return examiner;
	}

	public void setExaminer(EmployeeModel examiner) {
		this.examiner = examiner;
	}

	public Double getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}

	public Double getOldRemainMoney() {
		return oldRemainMoney;
	}

	public void setOldRemainMoney(Double oldRemainMoney) {
		this.oldRemainMoney = oldRemainMoney;
	}

	public Double getSettledMoney() {
		return settledMoney;
	}

	public void setSettledMoney(Double settledMoney) {
		this.settledMoney = settledMoney;
	}

}
