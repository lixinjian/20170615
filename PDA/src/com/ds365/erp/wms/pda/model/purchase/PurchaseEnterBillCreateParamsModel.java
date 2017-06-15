package com.ds365.erp.wms.pda.model.purchase;

import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BaseOperationModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：收货入库、采购退货---提交的信息 
 * @author Li xinJian
 * @date 2016年8月24日
 */
public class PurchaseEnterBillCreateParamsModel extends BaseOperationModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3179056790336094953L;
	private Long makerId; // 用户id
	private Long relatedBillId; // 采购订单id
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date enterDate; // 入库时间(取当前时间)
	
	private String relatedBillCode;// 采购订单编号
	
	private Long supplierId;
//	
//	添加	收货人  的字段:
//	private String  ;
//	
	private String deliverBillNo;	//发货单号
	private  Long examinerId;	//验收员
	
	private String relatedBillTypeId;
	
	public String getRelatedBillTypeId() {
		return relatedBillTypeId;
	}

	public void setRelatedBillTypeId(String relatedBillTypeId) {
		this.relatedBillTypeId = relatedBillTypeId;
	}

	private List<PurchaseEnterDetailCreateParamsModel> details; // 详情信息

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

	public Long getRelatedBillId() {
		return relatedBillId;
	}

	public void setRelatedBillId(Long relatedBillId) {
		this.relatedBillId = relatedBillId;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public List<PurchaseEnterDetailCreateParamsModel> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseEnterDetailCreateParamsModel> details) {
		this.details = details;
	}

	public String getDeliverBillNo() {
		return deliverBillNo;
	}

	public void setDeliverBillNo(String deliverBillNo) {
		this.deliverBillNo = deliverBillNo;
	}

	public Long getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(Long examinerId) {
		this.examinerId = examinerId;
	}

	public String getRelatedBillCode() {
		return relatedBillCode;
	}

	public void setRelatedBillCode(String relatedBillCode) {
		this.relatedBillCode = relatedBillCode;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

}
