package com.ds365.erp.wms.pda.model.sale;

import com.ds365.erp.wms.pda.common.billtypeenum.BaseEnum;

/**
 * 出库单状态
 * 
 * @author huiliwei
 * @date 2015年6月24日
 */
public enum SaleOutBillStateEnum implements BaseEnum<Integer> {
	OUT_COMPLETE(1, "已出库"),
	RETURN_COMPLETE(2, "已退货"),
	BACK_COMPLETE(3, "已回库");
	
	private Integer index;

	private String description;

	SaleOutBillStateEnum(Integer index, String description) {
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