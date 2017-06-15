package com.ds365.erp.wms.pda.model.stock;

import java.util.Date;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.ds365.erp.wms.pda.common.billtypeenum.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：分页查询商品进销存账页
 */
public class SkuStockAccountBookQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4282772204070249243L;

	private Long entId;// 供应商或者客户

	private Long skuId;

	private Long storeId;

	private String sysBatchNo;

	private Long billId;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date bizDate;

	private Long warehouseId;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date makeTime;

	private Long makerId;

	private Long salesmanId;

	private String forwardNo;

	private int forwardYear;

	private int forwardMonth;

	private String billCode;

	private BillTypeEnum billType;

	private Long oldBillId;

	private String oldBillCode;

	private BillTypeEnum oldBillType;

	private Integer oldOrdinalNo;
	// 相关单据Id
	private Long relatedBillId;
	// 相关单据号
	private String relatedBillCode;
	// 相关单据类型
	private BillTypeEnum relatedBillType;

	private Integer relatedOrdinalNo;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date startTime;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date endTime;

	private Integer saleTypeId;// 添加的

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

	public Long getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Long salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getForwardNo() {
		return forwardNo;
	}

	public void setForwardNo(String forwardNo) {
		this.forwardNo = forwardNo;
	}

	public int getForwardYear() {
		return forwardYear;
	}

	public void setForwardYear(int forwardYear) {
		this.forwardYear = forwardYear;
	}

	public int getForwardMonth() {
		return forwardMonth;
	}

	public void setForwardMonth(int forwardMonth) {
		this.forwardMonth = forwardMonth;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public BillTypeEnum getBillType() {
		return billType;
	}

	public void setBillType(BillTypeEnum billType) {
		this.billType = billType;
	}

	public Long getOldBillId() {
		return oldBillId;
	}

	public void setOldBillId(Long oldBillId) {
		this.oldBillId = oldBillId;
	}

	public String getOldBillCode() {
		return oldBillCode;
	}

	public void setOldBillCode(String oldBillCode) {
		this.oldBillCode = oldBillCode;
	}

	public BillTypeEnum getOldBillType() {
		return oldBillType;
	}

	public void setOldBillType(BillTypeEnum oldBillType) {
		this.oldBillType = oldBillType;
	}

	public Integer getOldOrdinalNo() {
		return oldOrdinalNo;
	}

	public void setOldOrdinalNo(Integer oldOrdinalNo) {
		this.oldOrdinalNo = oldOrdinalNo;
	}

	public Long getRelatedBillId() {
		return relatedBillId;
	}

	public void setRelatedBillId(Long relatedBillId) {
		this.relatedBillId = relatedBillId;
	}

	public String getRelatedBillCode() {
		return relatedBillCode;
	}

	public void setRelatedBillCode(String relatedBillCode) {
		this.relatedBillCode = relatedBillCode;
	}

	public BillTypeEnum getRelatedBillType() {
		return relatedBillType;
	}

	public void setRelatedBillType(BillTypeEnum relatedBillType) {
		this.relatedBillType = relatedBillType;
	}

	public Integer getRelatedOrdinalNo() {
		return relatedOrdinalNo;
	}

	public void setRelatedOrdinalNo(Integer relatedOrdinalNo) {
		this.relatedOrdinalNo = relatedOrdinalNo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getSaleTypeId() {
		return saleTypeId;
	}

	public void setSaleTypeId(Integer saleTypeId) {
		this.saleTypeId = saleTypeId;
	}

}
