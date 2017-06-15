package com.ds365.erp.wms.pda.common.db.dbmodel;

public class PurchaseEnterBillInfo {
	//
	private int _id;
	// sku码
	private String skuCode;
	// sku名称
	private String skuName;
	// 包装单位
	private String packUnit;
	// 最小单位
	private String minPack;
	// 库区
	private String wareHouseName;
	// 货位
	private String shelfName;
	// 退货件数
	private int UnitQty;
	// 退货散数
	private int MinUnitQty;

	public int get_id() {
		return _id;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public String getPackUnit() {
		return packUnit;
	}

	public String getMinPack() {
		return minPack;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public String getShelfName() {
		return shelfName;
	}

	public int getUnitQty() {
		return UnitQty;
	}

	public int getMinUnitQty() {
		return MinUnitQty;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public void setPackUnit(String packUnit) {
		this.packUnit = packUnit;
	}

	public void setMinPack(String minPack) {
		this.minPack = minPack;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public void setUnitQty(int unitQty) {
		UnitQty = unitQty;
	}

	public void setMinUnitQty(int minUnitQty) {
		MinUnitQty = minUnitQty;
	}

}
