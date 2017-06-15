package com.ds365.erp.wms.pda.model.stock;

import java.io.Serializable;

import com.ds365.commons.BaseIdModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

public class SkuShelfBatchDynamicStockModel extends BaseIdModel<Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2417283240667309740L;

	private GoodsSkuModel sku;

	private WarehouseModel warehouse;

	private StoreModel store;

	private ShelfModel shelf;

	private String sysBatchNo;

	private Integer usableQty;//可用数量

	private Integer orderQty;

	private Integer frozenQty;

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

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public ShelfModel getShelf() {
		return shelf;
	}

	public void setShelf(ShelfModel shelf) {
		this.shelf = shelf;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public Integer getUsableQty() {
		return usableQty;
	}

	public void setUsableQty(Integer usableQty) {
		this.usableQty = usableQty;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Integer getFrozenQty() {
		return frozenQty;
	}

	public void setFrozenQty(Integer frozenQty) {
		this.frozenQty = frozenQty;
	}

}