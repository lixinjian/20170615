package com.ds365.erp.wms.pda.model.commons;

import java.io.Serializable;

import com.ds365.commons.BaseIdModel;

/**
 * 
 * @author cgb
 *
 */
public class QtyModel extends BaseIdModel<Long> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6291581817727064898L;

	private  Integer unitQty;
	
	private Integer   minUnitQty;
	
	private  Integer qty;
	
	private  Integer specQty;

	public Integer getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}

	public Integer getMinUnitQty() {
		return minUnitQty;
	}

	public void setMinUnitQty(Integer minUnitQty) {
		this.minUnitQty = minUnitQty;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

}
