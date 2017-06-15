package com.ds365.erp.wms.pda.model.sale;

import java.io.Serializable;

public class SaleReturnEnterDetailModel extends BaseSaleOutDetailModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3725205966333224319L;
	
	
	private Integer expectQty;

	private Integer expectMinUnitQty;

	private Integer expectUnitQty;

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
	
	
	
	

}