package com.ds365.erp.wms.pda.model.stock;

import java.util.List;

import com.ds365.commons.base.model.BaseTreeQueryParamsModel;

public class ShelfQueryParamsModel extends BaseTreeQueryParamsModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2679759683394723061L;

	// row varchar(4) null 排
	private String row;
	// floor varchar(3) null 层
	private String floor;
	// slot varchar(4) null 位置
	private String slot;
	// store_id bigint(20) null 库区ID
	private Long storeId;

	private Integer storeUseTypeId;

	private List<Integer> storeUseTypeIds;

	// 货位类型：1-常规货位、2-移动货位、3-暂存区货位
	private Integer shelfTypeId;

	private List<Integer> shelfTypeIds;

	private Long warehouseId;

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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getStoreUseTypeId() {
		return storeUseTypeId;
	}

	public void setStoreUseTypeId(Integer storeUseTypeId) {
		this.storeUseTypeId = storeUseTypeId;
	}

	public List<Integer> getStoreUseTypeIds() {
		return storeUseTypeIds;
	}

	public void setStoreUseTypeIds(List<Integer> storeUseTypeIds) {
		this.storeUseTypeIds = storeUseTypeIds;
	}

	public Integer getShelfTypeId() {
		return shelfTypeId;
	}

	public void setShelfTypeId(Integer shelfTypeId) {
		this.shelfTypeId = shelfTypeId;
	}

	public List<Integer> getShelfTypeIds() {
		return shelfTypeIds;
	}

	public void setShelfTypeIds(List<Integer> shelfTypeIds) {
		this.shelfTypeIds = shelfTypeIds;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

}
