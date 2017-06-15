package com.ds365.erp.wms.pda.model.sale;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ds365.commons.BaseIdModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：退货入库、销售出库 ---提交信息
 * 
 * @author Li xinJian
 * @date 2016年8月24日
 */
public class SaleReturnEnterBillCreateParamsModel extends BaseIdModel<Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1734045987422441974L;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date outDate; // 出库时间
	private Long warehouseId; // 仓库id
	private Long userId; // 用户id
	private Long relatedBillId; // 订单id (退货申请单id)     
	private List<SaleReturnEnterDetailCreateParamsModel> details; // 详情

	private String relatedBillCode;

	private Long relatedBillTypeId;

	public SaleReturnEnterBillCreateParamsModel() {
		super();
	}

	public SaleReturnEnterBillCreateParamsModel(Date outDate, Long warehouseId, Long userId, Long relatedBillId,
			List<SaleReturnEnterDetailCreateParamsModel> details) {
		super();
		this.outDate = outDate;
		this.warehouseId = warehouseId;
		this.userId = userId;
		this.relatedBillId = relatedBillId;
		this.details = details;
	}

	

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRelatedBillId() {
		return relatedBillId;
	}

	public void setRelatedBillId(Long relatedBillId) {
		this.relatedBillId = relatedBillId;
	}

	public List<SaleReturnEnterDetailCreateParamsModel> getDetails() {
		return details;
	}

	public void setDetails(List<SaleReturnEnterDetailCreateParamsModel> details) {
		this.details = details;
	}

	public String getRelatedBillCode() {
		return relatedBillCode;
	}

	public void setRelatedBillCode(String relatedBillCode) {
		this.relatedBillCode = relatedBillCode;
	}

	public Long getRelatedBillTypeId() {
		return relatedBillTypeId;
	}

	public void setRelatedBillTypeId(Long relatedBillTypeId) {
		this.relatedBillTypeId = relatedBillTypeId;
	}
}
