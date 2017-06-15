package com.ds365.erp.wms.pda.model.stock;

import java.math.BigDecimal;

import com.ds365.commons.BaseIdModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

public class SkuWarehouseStockModel extends BaseIdModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4084597861216640083L;

	private SkuWarehouseDynamicStockModel skuWarehouseDynamicStock;

	private Integer unitQty;// 账面件数

	private Integer minUnitQty;// 账面散数

	private BigDecimal costMoney;

	private Integer highLimitQty;

	private Integer lowLimitQty;

	private GoodsSkuModel sku;

	private WarehouseModel warehouse;

	private EnumModel<Integer> saleType;

	private BigDecimal costPrice;

	private Integer qty;

	public SkuWarehouseDynamicStockModel getSkuWarehouseDynamicStock() {
		return skuWarehouseDynamicStock;
	}

	public void setSkuWarehouseDynamicStock(SkuWarehouseDynamicStockModel skuWarehouseDynamicStock) {
		this.skuWarehouseDynamicStock = skuWarehouseDynamicStock;
	}

	public BigDecimal getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(BigDecimal costMoney) {
		this.costMoney = costMoney;
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

	public EnumModel<Integer> getSaleType() {
		return saleType;
	}

	public void setSaleType(EnumModel<Integer> saleType) {
		this.saleType = saleType;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
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
