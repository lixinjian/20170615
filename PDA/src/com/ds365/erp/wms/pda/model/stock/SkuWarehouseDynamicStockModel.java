package com.ds365.erp.wms.pda.model.stock;

import java.io.Serializable;

import com.ds365.commons.BaseIdModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

public class SkuWarehouseDynamicStockModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7782928660662960387L;

	private GoodsSkuModel sku;

	private WarehouseModel warehouse;

	private Integer salableQty;// 可销量

	private Integer salableUnitQty;// 可销件数

	private Integer salableMinUnitQty;// 可销散数

	private Integer usableQty;// 可用量

	private Integer orderQty;// 下单占用数量

	private Integer frozenQty;// 冻结数量

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

	public Integer getSalableQty() {
		return salableQty;
	}

	public void setSalableQty(Integer salableQty) {
		this.salableQty = salableQty;
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

	public Integer getSalableUnitQty() {
		return salableUnitQty;
	}

	public void setSalableUnitQty(Integer salableUnitQty) {
		this.salableUnitQty = salableUnitQty;
	}

	public Integer getSalableMinUnitQty() {
		return salableMinUnitQty;
	}

	public void setSalableMinUnitQty(Integer salableMinUnitQty) {
		this.salableMinUnitQty = salableMinUnitQty;
	}

}
