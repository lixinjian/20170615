package com.ds365.erp.wms.pda.model.goods;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.commons.enums.EnumModel;

public class GoodsPackModel extends BaseActiveModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4806531054747896037L;

	private GoodsSkuModel sku;

	private String unitName;

	private EnumModel<Integer> packType;

	private Integer specQty;

	private GoodsModel goods;
	private String name;
	
	public GoodsPackModel() {
		super();
	}

	public GoodsPackModel(Long id) {
		super();
		this.id = id;
	}

	public GoodsPackModel(String unitName) {
		super();
		this.unitName = unitName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GoodsModel getGoods() {
		return goods;
	}

	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public EnumModel<Integer> getPackType() {
		return packType;
	}

	public void setPackType(EnumModel<Integer> packType) {
		this.packType = packType;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

}