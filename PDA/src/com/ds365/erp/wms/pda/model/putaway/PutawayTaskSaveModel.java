package com.ds365.erp.wms.pda.model.putaway;

import java.io.Serializable;

import com.ds365.commons.BaseIdModel;

public class PutawayTaskSaveModel extends BaseIdModel<Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6812617324685481840L;
	private String skuName;
	private String spec;
	private String shelfCode;

	// private int *** ;//本次上架
	public PutawayTaskSaveModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PutawayTaskSaveModel(String skuName, String spec, String shelfCode) {
		super();
		this.skuName = skuName;
		this.spec = spec;
		this.shelfCode = shelfCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

}
