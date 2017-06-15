package com.ds365.erp.wms.pda.common.db.dbmodel;

/**
 * 收货入库 数据库存储对应的实体类
 * 
 * @author LiXinjian
 * @date 2016年7月4日.
 *
 */
public class PurchaseOrderBillInfo {
	//
	private int _id;
	private String billCode;
	// sku码
	private String skuCode;
	// sku名称
	private String skuName;
	// 包装单位
	private String packUnit;
	// 最小单位
	private String minPack;
	// 库区
	private String storeName;
	// 货位
	private String shelfName;
	// 生产日期
	private String produceDate;
	// 批号
	private int produceBatchNo;
	// 实收件数
	private int receivedUnitQty;
	// 实收散数
	private int receivedMinUnitQty;

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

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

	public String getStoreName() {
		return storeName;
	}

	public String getShelfName() {
		return shelfName;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public int getProduceBatchNo() {
		return produceBatchNo;
	}

	public int getReceivedUnitQty() {
		return receivedUnitQty;
	}

	public int getReceivedMinUnitQty() {
		return receivedMinUnitQty;
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

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public void setProduceBatchNo(int produceBatchNo) {
		this.produceBatchNo = produceBatchNo;
	}

	public void setReceivedUnitQty(int receivedUnitQty) {
		this.receivedUnitQty = receivedUnitQty;
	}

	public void setReceivedMinUnitQty(int receivedMinUnitQty) {
		this.receivedMinUnitQty = receivedMinUnitQty;
	}

}
