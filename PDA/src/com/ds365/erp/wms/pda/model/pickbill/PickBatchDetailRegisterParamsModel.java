package com.ds365.erp.wms.pda.model.pickbill;

import java.io.Serializable;

import com.ds365.commons.BaseIdModel;

public class PickBatchDetailRegisterParamsModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6006619719043984987L;

	private Long storeId;
	private Long shelfId;

	private Integer pickUnitQty;// 实拣件数
	private Integer pickMinUnitQty;// 实拣散数
	private Integer pickQty;// 实捡数量
	private Integer diffQty;// 差异数量
	private Integer expectUnitQty;// 应拣件数
	private Integer expectMinUnitQty;// 应拣散数
	private Integer expectQty;// 应捡数量

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public Integer getPickUnitQty() {
		return pickUnitQty;
	}

	public void setPickUnitQty(Integer pickUnitQty) {
		this.pickUnitQty = pickUnitQty;
	}

	public Integer getPickMinUnitQty() {
		return pickMinUnitQty;
	}

	public void setPickMinUnitQty(Integer pickMinUnitQty) {
		this.pickMinUnitQty = pickMinUnitQty;
	}

	public Integer getPickQty() {
		return pickQty;
	}

	public void setPickQty(Integer pickQty) {
		this.pickQty = pickQty;
	}

	public Integer getDiffQty() {
		return diffQty;
	}

	public void setDiffQty(Integer diffQty) {
		this.diffQty = diffQty;
	}

	public Integer getExpectUnitQty() {
		return expectUnitQty;
	}

	public void setExpectUnitQty(Integer expectUnitQty) {
		this.expectUnitQty = expectUnitQty;
	}

	public Integer getExpectMinUnitQty() {
		return expectMinUnitQty;
	}

	public void setExpectMinUnitQty(Integer expectMinUnitQty) {
		this.expectMinUnitQty = expectMinUnitQty;
	}

	public Integer getExpectQty() {
		return expectQty;
	}

	public void setExpectQty(Integer expectQty) {
		this.expectQty = expectQty;
	}

}
