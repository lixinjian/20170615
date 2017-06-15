package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;

public class VehicleModel extends BaseActiveModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1630160069491027579L;

	/**
	 * 所属仓库
	 */
	// private WarehouseEntity warehouse;

	/**
	 * 车辆类型
	 */

	private EnumModel<Integer> vehicleType;

	/**
	 * 车牌号
	 */
	private String plateNo;

	/**
	 * 载重
	 */
	private BigDecimal loadWeight;

	/**
	 * 容积
	 */
	private BigDecimal loadVolume;

	// 内径 长宽高(m)
	private BigDecimal innerDiameterLength;
	private BigDecimal innerDiameterWidth;
	private BigDecimal innerDiameterHeight;

	/**
	 * 车辆购买日期
	 */
	private Date purchaseDate;

	/**
	 * 车辆采购金额
	 */
	private BigDecimal purchaseAmount;

	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 可用日期
	 */
	private Date availableDate;

	/**
	 * 报废日期
	 */
	private Date retirementDate;

	/**
	 * 引擎编号
	 */
	private String engineNo;

	/**
	 * 初始里程
	 */
	private Double initialMileage;

	/**
	 * 结算里程数
	 */
	private Double endMileage;

	/**
	 * 保险公司
	 */
	private String insurer;

	/**
	 * 注册日期
	 */
	private Date regeditDate;

	/**
	 * 组织
	 */
	private OrganizationModel organization;

	/**
	 * 负责人
	 */
	private EmployeeModel responsiblePerson;

	/**
	 * 默认司机
	 */
	private EmployeeModel driver;

	/**
	 * 备注
	 */
	private String remark;

	public EnumModel<Integer> getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(EnumModel<Integer> vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public BigDecimal getLoadWeight() {
		return loadWeight;
	}

	public void setLoadWeight(BigDecimal loadWeight) {
		this.loadWeight = loadWeight;
	}

	public BigDecimal getLoadVolume() {
		return loadVolume;
	}

	public void setLoadVolume(BigDecimal loadVolume) {
		this.loadVolume = loadVolume;
	}

	public BigDecimal getInnerDiameterLength() {
		return innerDiameterLength;
	}

	public void setInnerDiameterLength(BigDecimal innerDiameterLength) {
		this.innerDiameterLength = innerDiameterLength;
	}

	public BigDecimal getInnerDiameterWidth() {
		return innerDiameterWidth;
	}

	public void setInnerDiameterWidth(BigDecimal innerDiameterWidth) {
		this.innerDiameterWidth = innerDiameterWidth;
	}

	public BigDecimal getInnerDiameterHeight() {
		return innerDiameterHeight;
	}

	public void setInnerDiameterHeight(BigDecimal innerDiameterHeight) {
		this.innerDiameterHeight = innerDiameterHeight;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	public Date getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public Double getInitialMileage() {
		return initialMileage;
	}

	public void setInitialMileage(Double initialMileage) {
		this.initialMileage = initialMileage;
	}

	public Double getEndMileage() {
		return endMileage;
	}

	public void setEndMileage(Double endMileage) {
		this.endMileage = endMileage;
	}

	public String getInsurer() {
		return insurer;
	}

	public void setInsurer(String insurer) {
		this.insurer = insurer;
	}

	public Date getRegeditDate() {
		return regeditDate;
	}

	public void setRegeditDate(Date regeditDate) {
		this.regeditDate = regeditDate;
	}

	public OrganizationModel getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationModel organization) {
		this.organization = organization;
	}

	public EmployeeModel getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(EmployeeModel responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public EmployeeModel getDriver() {
		return driver;
	}

	public void setDriver(EmployeeModel driver) {
		this.driver = driver;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}