package com.ds365.erp.wms.pda.model.stock;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.BaseIdModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ： 货位库存查询的model
 * 
 * @author Li xinJian
 * @date 2016年9月5日
 */
public class SkuShelfBatchStockModel extends BaseIdModel<Long>
		implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8415785782605491007L;

	private GoodsSkuModel sku;

	private WarehouseModel warehouse;

	private double costPrice;

	private Integer qty;

	private StoreModel store;

	private ShelfModel shelf;

	private String sysBatchNo;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;

	private int guaranteePeriod;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date expireDate;

	private SkuBatchModel skuBatch;

	private SkuShelfBatchDynamicStockModel skuShelfBatchDynamicStock;

	private Long billDetailId;

	private String spec;
	private String unitName;

	private Integer unitQty;
	private Integer minUnitQty;

	private Integer usableQty;// 可用件数
	private Integer usableUnitQty;// 可用件数
	private Integer usableMinUnitQty;// 可用散数
	
	private EnumModel<Integer> saleType;
	
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

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
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

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public int getGuaranteePeriod() {
		return guaranteePeriod;
	}

	public void setGuaranteePeriod(int guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public SkuBatchModel getSkuBatch() {
		return skuBatch;
	}

	public void setSkuBatch(SkuBatchModel skuBatch) {
		this.skuBatch = skuBatch;
	}

	public SkuShelfBatchDynamicStockModel getSkuShelfBatchDynamicStock() {
		return skuShelfBatchDynamicStock;
	}

	public void setSkuShelfBatchDynamicStock(SkuShelfBatchDynamicStockModel skuShelfBatchDynamicStock) {
		this.skuShelfBatchDynamicStock = skuShelfBatchDynamicStock;
	}

	public Long getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(Long billDetailId) {
		this.billDetailId = billDetailId;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public Integer getUsableQty() {
		return usableQty;
	}

	public void setUsableQty(Integer usableQty) {
		this.usableQty = usableQty;
	}

	public Integer getUsableUnitQty() {
		return usableUnitQty;
	}

	public void setUsableUnitQty(Integer usableUnitQty) {
		this.usableUnitQty = usableUnitQty;
	}

	public Integer getUsableMinUnitQty() {
		return usableMinUnitQty;
	}

	public void setUsableMinUnitQty(Integer usableMinUnitQty) {
		this.usableMinUnitQty = usableMinUnitQty;
	}

	public EnumModel<Integer> getSaleType() {
		return saleType;
	}

	public void setSaleType(EnumModel<Integer> saleType) {
		this.saleType = saleType;
	}

}