package com.ds365.erp.wms.pda.model.stock;

import java.util.Date;
import java.util.List;

import com.ds365.commons.base.model.BasePageQueryParamsModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 说明 ：分页查询sku 货位、批次信息的model 
 */
public class SkuShelfBatchStockQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2485779387338614125L;

	// 仓库id
	private Long warehouseId;

	private Long storeId;

	private Long shelfId;

	private String shelfCode;

	// 商品sku id
	private Long skuId;

	private String skuCode;

	private String skuName;

	private Long goodsId;
	// 商品名称
	private String goodsName;
	// 商品编码
	private String goodsCode;

	private String helpCode;

	private String sysBatchNo;
	
	private String excludeSysBatchNo;//需要排除的系统批次

	private String produceBatchNo;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;

	private Integer guaranteePeriod;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date expireDate;

	private Integer qty;

	private Integer qtyGt;// 需要大于的数量

	private Integer usableQtyGt;// 需要大于的可用数量
	// 条形码
	private String barcode;
	// 生产企业
	private String manufacturer;

	private Long supplierId;

	private Integer saleTypeId;

	private Integer storeUseTypeId;

	private List<Integer> storeUseTypeIds;

	// 货位类型：1-常规货位、2-移动货位、3-暂存区货位
	private Integer shelfTypeId;

	private List<Integer> shelfTypeIds;

	private Long contractBillId;

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
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

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public String getProduceBatchNo() {
		return produceBatchNo;
	}

	public void setProduceBatchNo(String produceBatchNo) {
		this.produceBatchNo = produceBatchNo;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Integer getGuaranteePeriod() {
		return guaranteePeriod;
	}

	public void setGuaranteePeriod(Integer guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQtyGt() {
		return qtyGt;
	}

	public void setQtyGt(Integer qtyGt) {
		this.qtyGt = qtyGt;
	}

	public Integer getUsableQtyGt() {
		return usableQtyGt;
	}

	public void setUsableQtyGt(Integer usableQtyGt) {
		this.usableQtyGt = usableQtyGt;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSaleTypeId() {
		return saleTypeId;
	}

	public void setSaleTypeId(Integer saleTypeId) {
		this.saleTypeId = saleTypeId;
	}

	public Integer getStoreUseTypeId() {
		return storeUseTypeId;
	}

	public void setStoreUseTypeId(Integer storeUseTypeId) {
		this.storeUseTypeId = storeUseTypeId;
	}

	public List<Integer> getStoreUseTypeIds() {
		return storeUseTypeIds;
	}

	public void setStoreUseTypeIds(List<Integer> storeUseTypeIds) {
		this.storeUseTypeIds = storeUseTypeIds;
	}

	public Integer getShelfTypeId() {
		return shelfTypeId;
	}

	public void setShelfTypeId(Integer shelfTypeId) {
		this.shelfTypeId = shelfTypeId;
	}

	public List<Integer> getShelfTypeIds() {
		return shelfTypeIds;
	}

	public void setShelfTypeIds(List<Integer> shelfTypeIds) {
		this.shelfTypeIds = shelfTypeIds;
	}

	public Long getContractBillId() {
		return contractBillId;
	}

	public void setContractBillId(Long contractBillId) {
		this.contractBillId = contractBillId;
	}

	public String getExcludeSysBatchNo() {
		return excludeSysBatchNo;
	}

	public void setExcludeSysBatchNo(String excludeSysBatchNo) {
		this.excludeSysBatchNo = excludeSysBatchNo;
	}

}
