package com.ds365.erp.wms.pda.model.putaway;

import java.io.Serializable;
import java.util.List;

import com.ds365.commons.BaseIdModel;

/**
 * 上架所提交的字段
 */
public class PutawayTaskCreateParamsModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6463900895233599676L;
	private Long relativeBillId;
	private Long relativeDetailId; // ***重要的字段必须有值***/
	private Long storeId;
	private Long shelfId;
	private String shelfCode;
	private Integer putawayTypeId;
	private Long skuId;
	private String sysBatchNo;
	private int unitQty; // 件数
	private int minUnitQty; // 散数
	private int qty; // 上架总数量
	private int totalCount; // 总数量 TODO:此字段名称不确定!

	private String relatedBillTypeId;

	private List<PutawayRecordCreateParamsModel> records;

	public PutawayTaskCreateParamsModel() {
		super();
	}

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRelativeBillId() {
		return relativeBillId;
	}

	public void setRelativeBillId(Long relativeBillId) {
		this.relativeBillId = relativeBillId;
	}

	public Long getRelativeDetailId() {
		return relativeDetailId;
	}

	public void setRelativeDetailId(Long relativeDetailId) {
		this.relativeDetailId = relativeDetailId;
	}

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

	public Integer getPutawayTypeId() {
		return putawayTypeId;
	}

	public void setPutawayTypeId(Integer putawayTypeId) {
		this.putawayTypeId = putawayTypeId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public int getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(int unitQty) {
		this.unitQty = unitQty;
	}

	public int getMinUnitQty() {
		return minUnitQty;
	}

	public void setMinUnitQty(int minUnitQty) {
		this.minUnitQty = minUnitQty;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getRelatedBillTypeId() {
		return relatedBillTypeId;
	}

	public void setRelatedBillTypeId(String relatedBillTypeId) {
		this.relatedBillTypeId = relatedBillTypeId;
	}

	public List<PutawayRecordCreateParamsModel> getRecords() {
		return records;
	}

	public void setRecords(List<PutawayRecordCreateParamsModel> records) {
		this.records = records;
	}

}
