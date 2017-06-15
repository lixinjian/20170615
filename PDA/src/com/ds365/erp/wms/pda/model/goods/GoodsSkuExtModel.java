package com.ds365.erp.wms.pda.model.goods;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;

public class GoodsSkuExtModel extends BaseActiveModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5570361428022323836L;

	private GoodsSkuModel sku;

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

}