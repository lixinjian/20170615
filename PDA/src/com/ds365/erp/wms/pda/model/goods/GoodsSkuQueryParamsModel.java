package com.ds365.erp.wms.pda.model.goods;

import com.ds365.commons.base.model.BasePageQueryParamsModel;

public class GoodsSkuQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7379462255599965855L;

	private Long goodsId;

	private String barcode;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
