package com.ds365.erp.wms.pda.model.sale;

import java.io.Serializable;

public class SaleOrderBillModel extends BaseSaleOrderBillModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2835203585746059778L;

	private Integer orderQty;

	private double costMoney;
	
	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(double costMoney) {
		this.costMoney = costMoney;
	}
}