package com.ds365.erp.wms.pda.model.stockshift;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseTreeModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;

public class ShelfModel extends BaseTreeModel<ShelfModel> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1356935031308723675L;

	private String row;

	private String floor;

	private String slot;

	private StoreModel store;

	private EnumModel<Integer> shelfType;

	private EnumModel<Integer> shelfPositionType;

	private String length;

	private String width;

	private String height;

	private String weight;

	private EmployeeModel custodian;

	public ShelfModel(Long id, String code) {
		super();
		this.id = id;
		this.code = code;
	}

	public ShelfModel() {
		super();
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public EnumModel<Integer> getShelfType() {
		return shelfType;
	}

	public void setShelfType(EnumModel<Integer> shelfType) {
		this.shelfType = shelfType;
	}

	public EnumModel<Integer> getShelfPositionType() {
		return shelfPositionType;
	}

	public void setShelfPositionType(EnumModel<Integer> shelfPositionType) {
		this.shelfPositionType = shelfPositionType;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public EmployeeModel getCustodian() {
		return custodian;
	}

	public void setCustodian(EmployeeModel custodian) {
		this.custodian = custodian;
	}

}