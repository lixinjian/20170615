package com.ds365.erp.wms.pda.model.purchase;

import com.ds365.erp.wms.pda.common.billtypeenum.BaseEnum;

/**
 * 采购入库单状态
 * 
 * @author huiliwei
 * @date 2015年6月24日
 */
public enum PurchaseReturnOrderBillStateEnum implements BaseEnum<Integer> {

	WAIT_WAREHOUSE(1, "采购收货待入（出）库"), WAREHOUSED(2, "采购收货已入（出）库"), SHELVED(3, "已上架");

	private Integer index;

	private String description;

	PurchaseReturnOrderBillStateEnum(Integer index, String description) {
		this.index = index;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Integer getIndex() {
		return index;
	}

}
