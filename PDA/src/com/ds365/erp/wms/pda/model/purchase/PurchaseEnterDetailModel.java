package com.ds365.erp.wms.pda.model.purchase;

import java.io.Serializable;

public class PurchaseEnterDetailModel extends BasePurchaseDetailModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7633823222654778351L;
	private PurchaseEnterBillModel bill;

	private Integer expectQty;

	private Integer expectMinUnitQty;

	private Integer expectUnitQty;

	/**
	 * 前台做计算用
	 */
	private Integer remainUnitQty;// 剩余件数
	private Integer remainMinUnitQty;// 剩余散数
	private Integer remainQty;// 剩余的数量

	public PurchaseEnterBillModel getBill() {
		return bill;
	}

	public void setBill(PurchaseEnterBillModel bill) {
		this.bill = bill;
	}

	public Integer getExpectQty() {
		return expectQty;
	}

	public void setExpectQty(Integer expectQty) {
		this.expectQty = expectQty;
	}

	public Integer getExpectMinUnitQty() {
		return expectMinUnitQty;
	}

	public void setExpectMinUnitQty(Integer expectMinUnitQty) {
		this.expectMinUnitQty = expectMinUnitQty;
	}

	public Integer getExpectUnitQty() {
		return expectUnitQty;
	}

	public void setExpectUnitQty(Integer expectUnitQty) {
		this.expectUnitQty = expectUnitQty;
	}

	public Integer getRemainUnitQty() {
		return remainUnitQty;
	}

	public void setRemainUnitQty(Integer remainUnitQty) {
		this.remainUnitQty = remainUnitQty;
	}

	public Integer getRemainMinUnitQty() {
		return remainMinUnitQty;
	}

	public void setRemainMinUnitQty(Integer remainMinUnitQty) {
		this.remainMinUnitQty = remainMinUnitQty;
	}

	public Integer getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Integer remainQty) {
		this.remainQty = remainQty;
	}

}