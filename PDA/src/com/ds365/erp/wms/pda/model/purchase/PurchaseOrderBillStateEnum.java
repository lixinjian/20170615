package com.ds365.erp.wms.pda.model.purchase;

import com.ds365.erp.wms.pda.common.billtypeenum.BillStateEnum;

/**
 * 采购订单状态
 * 
 * @author fanhaoyu
 * @date 2015年5月27日
 */
public enum PurchaseOrderBillStateEnum implements  BillStateEnum  {

	/**
	 * 业务关闭
	 */
	CLOSED(0, "业务关闭"), 
	
	/**
	 * 待采购审核
	 */
	DEFAULT(1, "待采购审核"),
	/**
	 * 采购审核
	 */
	PURCHASE(2, "待财务审核"), 
	/**
	 * 财务审核
	 */
	FINANCE(3,"已审核"),//未入库
	ENTER(4,"已入库");//已入库
	
	
	private Integer index;

	private String description;

	PurchaseOrderBillStateEnum(Integer index, String description) {
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
