package com.ds365.erp.wms.pda.model.goods;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;

public class GoodsModel extends BaseActiveModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 597810039642950926L;

	private String code;

	private String name;

	private String helpCode;

	private GoodsBrandModel brand;

	private GoodsCategoryModel category;

	private String manufacturer;

	private String originPlace;

	private String description;

	public GoodsModel() {
		super();
	}

	public GoodsModel(String name) {
		super();
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public GoodsBrandModel getBrand() {
		return brand;
	}

	public void setBrand(GoodsBrandModel brand) {
		this.brand = brand;
	}

	public GoodsCategoryModel getCategory() {
		return category;
	}

	public void setCategory(GoodsCategoryModel category) {
		this.category = category;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}