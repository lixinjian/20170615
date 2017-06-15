package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class VehicleQueryParamsModel extends BasePageQueryParamsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4259614141783295249L;

	/**
	 * 车辆类型
	 */
	private Integer vehicleTypeId;

	private Long organizationId;
	/**
	 * 所属仓库
	 */
	/*
	 * private Long warehouseId;
	 * 
	 * private String warehouseName;
	 */

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

	// 内径 长宽高
	private BigDecimal innerDiameter;

	/**
	 * 车辆购买日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
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
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date availableDate;

	/**
	 * 报废日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
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
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date regeditDate;

	/**
	 * 组织
	 */
	private Long orgId;

	private String orgName;

	/**
	 * 负责人
	 */
	private Long responsiblePersonId;
	private String responsiblePersonName;

	private Long driverId;

	private String driverName;

	/**
	 * 备注
	 */
	private String remark;

	public Integer getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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

	public BigDecimal getInnerDiameter() {
		return innerDiameter;
	}

	public void setInnerDiameter(BigDecimal innerDiameter) {
		this.innerDiameter = innerDiameter;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getResponsiblePersonId() {
		return responsiblePersonId;
	}

	public void setResponsiblePersonId(Long responsiblePersonId) {
		this.responsiblePersonId = responsiblePersonId;
	}

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
