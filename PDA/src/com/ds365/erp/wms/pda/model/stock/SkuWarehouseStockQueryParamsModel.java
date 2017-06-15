package com.ds365.erp.wms.pda.model.stock;

import java.util.List;

import com.ds365.commons.base.model.BasePageQueryParamsModel;

public class SkuWarehouseStockQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3202003293547091306L;

	// 仓库id
	private Long warehouseId;
	private List<Long> warehouseIds;
	// 商品sku id
	private Long skuId;

	private String skuCode;

	private List<String> skuCodes;

	private String skuName;

	private Integer saleTypeId;// 添加的

	private List<Integer> saleTypeIds;

	private Long goodsId;
	// 商品名称
	private String goodsName;
	// 商品编码
	private String goodsCode;

	private String helpCode;

	private Integer qty;
	private Integer qtyGt;// 需要大于的数量

	private Integer usableQtyGt;// 需要大于的可用数量

	private Integer salableQtyGt;// 需要大于的可销量

	// 条形码
	private String barcode;
	// 生产企业
	private String manufacturer;

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public List<Long> getWarehouseIds() {
		return warehouseIds;
	}

	public void setWarehouseIds(List<Long> warehouseIds) {
		this.warehouseIds = warehouseIds;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public List<String> getSkuCodes() {
		return skuCodes;
	}

	public void setSkuCodes(List<String> skuCodes) {
		this.skuCodes = skuCodes;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Integer getSaleTypeId() {
		return saleTypeId;
	}

	public void setSaleTypeId(Integer saleTypeId) {
		this.saleTypeId = saleTypeId;
	}

	public List<Integer> getSaleTypeIds() {
		return saleTypeIds;
	}

	public void setSaleTypeIds(List<Integer> saleTypeIds) {
		this.saleTypeIds = saleTypeIds;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQtyGt() {
		return qtyGt;
	}

	public void setQtyGt(Integer qtyGt) {
		this.qtyGt = qtyGt;
	}

	public Integer getUsableQtyGt() {
		return usableQtyGt;
	}

	public void setUsableQtyGt(Integer usableQtyGt) {
		this.usableQtyGt = usableQtyGt;
	}

	public Integer getSalableQtyGt() {
		return salableQtyGt;
	}

	public void setSalableQtyGt(Integer salableQtyGt) {
		this.salableQtyGt = salableQtyGt;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
