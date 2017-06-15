package com.ds365.erp.wms.pda.model.shipment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：装运单的查询model 
 */
public class ShipmentBillQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2687767925424257374L;
	/**
	 * 装运单编号
	 */
	private String billTypeId;
	private String billCode;
	private Long warehouseOrganizationId;
	private Long deliverOrganizationId;

	/**
	 * 仓库ID
	 */
	private Long warehouseId;

	/**
	 * 线路ID
	 */
	private Long lineId;

	private String lineName;

	/**
	 * 线路类型
	 */
	private Integer lineTypeId;

	/**
	 * 制单人
	 */
	private Long makerId;

	/**
	 * 制单时间
	 */
	// 审核时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;

	/**
	 * 司机
	 */
	private Long driverId;

	private String driverName;

	private Long[] deliverUserIds;// 送货人员

	/**
	 * 配送站ID数组
	 */
	private List<Long> deliverStationIds;

	/**
	 * 车辆
	 */
	private Long vehicleId;

	/**
	 * 车牌号
	 */
	private String plateNo;

	/**
	 * 车辆载重
	 */
	private BigDecimal loadWeight;

	/**
	 * 车辆载体积
	 */
	private BigDecimal loadVolume;

	/**
	 * 金额合计
	 */
	private BigDecimal billMoney;

	/**
	 * 件数合计
	 */
	private Integer unitQty;

	/**
	 * 包装数量
	 */
	private Integer specQty;

	/**
	 * 数量
	 */
	private Integer qty;

	/**
	 * 实装重量合计
	 */
	private BigDecimal realWeight;

	/**
	 * 实装体积合计
	 */
	private BigDecimal realVolume;

	/**
	 * 打印状态
	 */
	private Integer printStateId;

	/**
	 * 取消人
	 */
	private Long cancelId;

	/**
	 * 取消时间
	 */
	// 审核时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date cancelTime;

	/**
	 * 老单据ID
	 */
	private Long oldBillId;

	/**
	 * 老单据Code
	 */
	private String oldBillCode;

	/**
	 * 相关单据ID
	 */
	private Long relatedBillId;

	/**
	 * 相关单据Code
	 */
	private String relatedBillCode;

	/**
	 * 装运单开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTimeBegin;
	/**
	 * 装运单结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTimeEnd;

	private Boolean autoPickBillFlag;

	/**
	 * 单据状态
	 */
	private Integer billStateId;

	private List<Integer> billStateIds;

	private Integer warehouseSubStateId;

	private Integer transportSubStateId;

	public String getBillTypeId() {
		return billTypeId;
	}

	public void setBillTypeId(String billTypeId) {
		this.billTypeId = billTypeId;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public Long getWarehouseOrganizationId() {
		return warehouseOrganizationId;
	}

	public void setWarehouseOrganizationId(Long warehouseOrganizationId) {
		this.warehouseOrganizationId = warehouseOrganizationId;
	}

	public Long getDeliverOrganizationId() {
		return deliverOrganizationId;
	}

	public void setDeliverOrganizationId(Long deliverOrganizationId) {
		this.deliverOrganizationId = deliverOrganizationId;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Integer getLineTypeId() {
		return lineTypeId;
	}

	public void setLineTypeId(Integer lineTypeId) {
		this.lineTypeId = lineTypeId;
	}

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
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

	public Long[] getDeliverUserIds() {
		return deliverUserIds;
	}

	public void setDeliverUserIds(Long[] deliverUserIds) {
		this.deliverUserIds = deliverUserIds;
	}

	public List<Long> getDeliverStationIds() {
		return deliverStationIds;
	}

	public void setDeliverStationIds(List<Long> deliverStationIds) {
		this.deliverStationIds = deliverStationIds;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
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

	public BigDecimal getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(BigDecimal billMoney) {
		this.billMoney = billMoney;
	}

	public Integer getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getRealWeight() {
		return realWeight;
	}

	public void setRealWeight(BigDecimal realWeight) {
		this.realWeight = realWeight;
	}

	public BigDecimal getRealVolume() {
		return realVolume;
	}

	public void setRealVolume(BigDecimal realVolume) {
		this.realVolume = realVolume;
	}

	public Integer getPrintStateId() {
		return printStateId;
	}

	public void setPrintStateId(Integer printStateId) {
		this.printStateId = printStateId;
	}

	public Long getCancelId() {
		return cancelId;
	}

	public void setCancelId(Long cancelId) {
		this.cancelId = cancelId;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
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

	public Date getMakeTimeBegin() {
		return makeTimeBegin;
	}

	public void setMakeTimeBegin(Date makeTimeBegin) {
		this.makeTimeBegin = makeTimeBegin;
	}

	public Date getMakeTimeEnd() {
		return makeTimeEnd;
	}

	public void setMakeTimeEnd(Date makeTimeEnd) {
		this.makeTimeEnd = makeTimeEnd;
	}

	public Boolean getAutoPickBillFlag() {
		return autoPickBillFlag;
	}

	public void setAutoPickBillFlag(Boolean autoPickBillFlag) {
		this.autoPickBillFlag = autoPickBillFlag;
	}

	public Integer getBillStateId() {
		return billStateId;
	}

	public void setBillStateId(Integer billStateId) {
		this.billStateId = billStateId;
	}

	public List<Integer> getBillStateIds() {
		return billStateIds;
	}

	public void setBillStateIds(List<Integer> billStateIds) {
		this.billStateIds = billStateIds;
	}

	public Integer getWarehouseSubStateId() {
		return warehouseSubStateId;
	}

	public void setWarehouseSubStateId(Integer warehouseSubStateId) {
		this.warehouseSubStateId = warehouseSubStateId;
	}

	public Integer getTransportSubStateId() {
		return transportSubStateId;
	}

	public void setTransportSubStateId(Integer transportSubStateId) {
		this.transportSubStateId = transportSubStateId;
	}

}
