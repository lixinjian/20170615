package com.ds365.erp.wms.pda.model.shipment;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.BaseIdModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.DeliverStationModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.commons.VehicleModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DeliverBillModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6905593792136812050L;

	private OrganizationModel warehouseOrganization;

	private OrganizationModel deliverOrganization;

	/**
	 * 线路ID
	 */
	// private DeliverLineEntity line;

	/**
	 * 站点ID
	 */
	private DeliverStationModel deliverStation;

	/**
	 * 包装数量
	 */
	private Integer specQty;

	private Integer orderCount;

	private Integer itemCount;

	/**
	 * 配送件数
	 */
	private Integer unitQty;

	private Integer minUnitQty;

	/**
	 * 配送数量
	 */
	private Integer qty;

	/**
	 * 金额
	 */
	private Double billMoney;

	private Double receivableMoney;// 应收金额

	// 应收未结算金额
	private Double remainMoney;

	// 应收未结算金额
	private Double settledMoney;

	/**
	 * 重量
	 */
	private Double weight;

	/**
	 * 体积
	 */
	private Double volume;

	private String deliverUserIds;

	private String deliverUserNames;

	private EmployeeModel driver;

	private VehicleModel vehicle;

	private EmployeeModel deliverUser;// 交付操作者（送货人员可能会有多个，操作者只有一个）

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date deliverTime;// 送货交付时间

	/**
	 * 签收人
	 */
	private EmployeeModel signUser;

	/**
	 * 签收时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date signTime;

	/**
	 * 考虑到配送站的单据和装运单是不同的系统，所以没有进行表关联
	 */
	private Long shipmentBillId;

	private String shipmentBillCode;

	// 制单人（开票人）
	private EmployeeModel maker;
	// 制单日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;

	// 备注
	private String remark;

	private String billCode;

	private EnumModel<String> billType;

	private Long oldBillId;
	private String oldBillCode;

	private EnumModel<String> oldBillType;

	// 相关单据Id
	private Long relatedBillId;
	// 相关单据号
	private String relatedBillCode;

	// 相关单据类型
	private EnumModel<String> relatedBillType;

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

	public DeliverStationModel getDeliverStation() {
		return deliverStation;
	}

	public void setDeliverStation(DeliverStationModel deliverStation) {
		this.deliverStation = deliverStation;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Integer getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}

	public Integer getMinUnitQty() {
		return minUnitQty;
	}

	public void setMinUnitQty(Integer minUnitQty) {
		this.minUnitQty = minUnitQty;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(Double billMoney) {
		this.billMoney = billMoney;
	}

	public Double getReceivableMoney() {
		return receivableMoney;
	}

	public void setReceivableMoney(Double receivableMoney) {
		this.receivableMoney = receivableMoney;
	}

	public Double getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}

	public Double getSettledMoney() {
		return settledMoney;
	}

	public void setSettledMoney(Double settledMoney) {
		this.settledMoney = settledMoney;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getDeliverUserIds() {
		return deliverUserIds;
	}

	public void setDeliverUserIds(String deliverUserIds) {
		this.deliverUserIds = deliverUserIds;
	}

	public String getDeliverUserNames() {
		return deliverUserNames;
	}

	public void setDeliverUserNames(String deliverUserNames) {
		this.deliverUserNames = deliverUserNames;
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

	public EmployeeModel getDeliverUser() {
		return deliverUser;
	}

	public void setDeliverUser(EmployeeModel deliverUser) {
		this.deliverUser = deliverUser;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public EmployeeModel getSignUser() {
		return signUser;
	}

	public void setSignUser(EmployeeModel signUser) {
		this.signUser = signUser;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Long getShipmentBillId() {
		return shipmentBillId;
	}

	public void setShipmentBillId(Long shipmentBillId) {
		this.shipmentBillId = shipmentBillId;
	}

	public String getShipmentBillCode() {
		return shipmentBillCode;
	}

	public void setShipmentBillCode(String shipmentBillCode) {
		this.shipmentBillCode = shipmentBillCode;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

}
