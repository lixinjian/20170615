package com.ds365.erp.wms.pda.model.shipment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.LineModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.commons.VehicleModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ShipmentOrderBillModel extends BaseOperationModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6162077265595856837L;

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

	private EnumModel<Integer> deliverStationType;

	private OrganizationModel warehouseOrganization;

	private OrganizationModel deliverOrganization;

	private LineModel line;

	private EnumModel<Integer> lineType;

	private EmployeeModel driver;

	private VehicleModel vehicle;

	private double loadWeight;

	private double loadVolume;

	private double billMoney;

	private Integer unitQty;

	private double realWeight;

	private double realVolume;

	private EnumModel<Integer> printState;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date cancelTime;

	private String deliverUserNames;
	private String deliverUserIds;
	private int itemCount;
	private int orderCount;
	private int deliverStationCount;
	private EnumModel<Integer> warehouseSubState;
	private EnumModel<Integer> transportSubState;
	private EmployeeModel cancel;

	private List<Integer> deliverStationIds;
	private String shipmentOrderListDetails;
	private String shipmentGoodsDetailDtos;
	private String shipmentGoodsBatchDetailDtos;
	private EmployeeModel examiner;//����Ա
	
	
	public EmployeeModel getExaminer() {
		return examiner;
	}

	public void setExaminer(EmployeeModel examiner) {
		this.examiner = examiner;
	}

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

	public EnumModel<Integer> getDeliverStationType() {
		return deliverStationType;
	}

	public void setDeliverStationType(EnumModel<Integer> deliverStationType) {
		this.deliverStationType = deliverStationType;
	}

	public OrganizationModel getWarehouseOrganization() {
		return warehouseOrganization;
	}

	public void setWarehouseOrganization(OrganizationModel warehouseOrganization) {
		this.warehouseOrganization = warehouseOrganization;
	}

	public OrganizationModel getDeliverOrganization() {
		return deliverOrganization;
	}

	public void setDeliverOrganization(OrganizationModel deliverOrganization) {
		this.deliverOrganization = deliverOrganization;
	}

	public LineModel getLine() {
		return line;
	}

	public void setLine(LineModel line) {
		this.line = line;
	}

	public EnumModel<Integer> getLineType() {
		return lineType;
	}

	public void setLineType(EnumModel<Integer> lineType) {
		this.lineType = lineType;
	}

	public EmployeeModel getDriver() {
		return driver;
	}

	public void setDriver(EmployeeModel driver) {
		this.driver = driver;
	}

	public VehicleModel getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleModel vehicle) {
		this.vehicle = vehicle;
	}

	public double getLoadWeight() {
		return loadWeight;
	}

	public void setLoadWeight(double loadWeight) {
		this.loadWeight = loadWeight;
	}

	public double getLoadVolume() {
		return loadVolume;
	}

	public void setLoadVolume(double loadVolume) {
		this.loadVolume = loadVolume;
	}

	public double getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}

	public Integer getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}

	public double getRealWeight() {
		return realWeight;
	}

	public void setRealWeight(double realWeight) {
		this.realWeight = realWeight;
	}

	public double getRealVolume() {
		return realVolume;
	}

	public void setRealVolume(double realVolume) {
		this.realVolume = realVolume;
	}

	public EnumModel<Integer> getPrintState() {
		return printState;
	}

	public void setPrintState(EnumModel<Integer> printState) {
		this.printState = printState;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getDeliverUserNames() {
		return deliverUserNames;
	}

	public void setDeliverUserNames(String deliverUserNames) {
		this.deliverUserNames = deliverUserNames;
	}

	public String getDeliverUserIds() {
		return deliverUserIds;
	}

	public void setDeliverUserIds(String deliverUserIds) {
		this.deliverUserIds = deliverUserIds;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getDeliverStationCount() {
		return deliverStationCount;
	}

	public void setDeliverStationCount(int deliverStationCount) {
		this.deliverStationCount = deliverStationCount;
	}

	public EnumModel<Integer> getWarehouseSubState() {
		return warehouseSubState;
	}

	public void setWarehouseSubState(EnumModel<Integer> warehouseSubState) {
		this.warehouseSubState = warehouseSubState;
	}

	public EnumModel<Integer> getTransportSubState() {
		return transportSubState;
	}

	public void setTransportSubState(EnumModel<Integer> transportSubState) {
		this.transportSubState = transportSubState;
	}

	public EmployeeModel getCancel() {
		return cancel;
	}

	public void setCancel(EmployeeModel cancel) {
		this.cancel = cancel;
	}

	public List<Integer> getDeliverStationIds() {
		return deliverStationIds;
	}

	public void setDeliverStationIds(List<Integer> deliverStationIds) {
		this.deliverStationIds = deliverStationIds;
	}

	public String getShipmentOrderListDetails() {
		return shipmentOrderListDetails;
	}

	public void setShipmentOrderListDetails(String shipmentOrderListDetails) {
		this.shipmentOrderListDetails = shipmentOrderListDetails;
	}

	public String getShipmentGoodsDetailDtos() {
		return shipmentGoodsDetailDtos;
	}

	public void setShipmentGoodsDetailDtos(String shipmentGoodsDetailDtos) {
		this.shipmentGoodsDetailDtos = shipmentGoodsDetailDtos;
	}

	public String getShipmentGoodsBatchDetailDtos() {
		return shipmentGoodsBatchDetailDtos;
	}

	public void setShipmentGoodsBatchDetailDtos(String shipmentGoodsBatchDetailDtos) {
		this.shipmentGoodsBatchDetailDtos = shipmentGoodsBatchDetailDtos;
	}

}