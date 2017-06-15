package com.ds365.erp.wms.pda.model.shipment;

import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.erp.wms.pda.common.billtypeenum.BillTypeEnum;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ShipmentOutBillCreateParamsModel extends BaseOperationModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -889663108532146073L;

	// 验收人
	private Long examinerId;

	private List<Long> saleOrderBillIds;

	private EmployeeModel maker;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;

	private Long shipmentBillId;

	private Date outDate;

	private Long deliverStationId;
	private Long vehicleId;

	private Long driverId;

	private Long lineId;

	private Long makerId;
	private BillTypeEnum billType;
	private Long warehouseOrganizationId;

	public Long getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(Long examinerId) {
		this.examinerId = examinerId;
	}

	public List<Long> getSaleOrderBillIds() {
		return saleOrderBillIds;
	}

	public void setSaleOrderBillIds(List<Long> saleOrderBillIds) {
		this.saleOrderBillIds = saleOrderBillIds;
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

	public Long getShipmentBillId() {
		return shipmentBillId;
	}

	public void setShipmentBillId(Long shipmentBillId) {
		this.shipmentBillId = shipmentBillId;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Long getDeliverStationId() {
		return deliverStationId;
	}

	public void setDeliverStationId(Long deliverStationId) {
		this.deliverStationId = deliverStationId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

	public BillTypeEnum getBillType() {
		return billType;
	}

	public void setBillType(BillTypeEnum billType) {
		this.billType = billType;
	}

	public Long getWarehouseOrganizationId() {
		return warehouseOrganizationId;
	}

	public void setWarehouseOrganizationId(Long warehouseOrganizationId) {
		this.warehouseOrganizationId = warehouseOrganizationId;
	}

}