package com.ds365.erp.wms.pda.model.stock;

import java.io.Serializable;

import com.ds365.commons.BaseIdModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

/**
 * 
 * 说明 ：商品库区库存
 */
public class SkuStoreStockModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8136742141996799873L;

	private GoodsSkuModel sku;

	private WarehouseModel warehouse;

	private Double costPrice;

	private Integer qty;

	private StoreModel store;

	private Integer highLimitQty;

	private Integer lowLimitQty;

	private Double costMoney;

	

	private Integer unitQty;
	private Integer minUnitQty;

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public Integer getHighLimitQty() {
		return highLimitQty;
	}

	public void setHighLimitQty(Integer highLimitQty) {
		this.highLimitQty = highLimitQty;
	}

	public Integer getLowLimitQty() {
		return lowLimitQty;
	}

	public void setLowLimitQty(Integer lowLimitQty) {
		this.lowLimitQty = lowLimitQty;
	}

	public Double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(Double costMoney) {
		this.costMoney = costMoney;
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
}
