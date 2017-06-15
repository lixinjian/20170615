package com.ds365.erp.wms.pda.model.stockshift;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseTreeModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

public class StoreModel extends BaseTreeModel<StoreModel> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2534242692537613239L;
	private WarehouseModel warehouse;
	private EnumModel<Integer> type;
	private EnumModel<Integer> useType;
	private String address;
	private boolean deliverFlag;
	private boolean defectiveFlag;
	private boolean borrowFlag;
	private String contactor;
	private String phoneNo;
	private String mobileNo;
	private String email;
	private String remark;
	private String area;
	private String workerCount;
	private boolean underFlag;
	private boolean shelfManageFlag;

	public StoreModel() {
		super();
	}

	public StoreModel(Long id) {
		super();
		this.id = id;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public EnumModel<Integer> getType() {
		return type;
	}

	public void setType(EnumModel<Integer> type) {
		this.type = type;
	}

	public EnumModel<Integer> getUseType() {
		return useType;
	}

	public void setUseType(EnumModel<Integer> useType) {
		this.useType = useType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isDeliverFlag() {
		return deliverFlag;
	}

	public void setDeliverFlag(boolean deliverFlag) {
		this.deliverFlag = deliverFlag;
	}

	public boolean isDefectiveFlag() {
		return defectiveFlag;
	}

	public void setDefectiveFlag(boolean defectiveFlag) {
		this.defectiveFlag = defectiveFlag;
	}

	public boolean isBorrowFlag() {
		return borrowFlag;
	}

	public void setBorrowFlag(boolean borrowFlag) {
		this.borrowFlag = borrowFlag;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWorkerCount() {
		return workerCount;
	}

	public void setWorkerCount(String workerCount) {
		this.workerCount = workerCount;
	}

	public boolean isUnderFlag() {
		return underFlag;
	}

	public void setUnderFlag(boolean underFlag) {
		this.underFlag = underFlag;
	}

	public boolean isShelfManageFlag() {
		return shelfManageFlag;
	}

	public void setShelfManageFlag(boolean shelfManageFlag) {
		this.shelfManageFlag = shelfManageFlag;
	}

}