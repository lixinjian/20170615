package com.ds365.erp.wms.pda.model.pickbill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.BaseCheckFlagModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 拣货单明细
 * 
 * @author LiXinjian
 * @date 2016年6月14日.
 *
 */

public class PickBatchDetailModel extends BaseCheckFlagModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3229898860356936543L;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;
	private PickBillModel bill;
	private WarehouseModel warehouse;
	private StoreModel store;
	private ShelfModel shelf;
	private GoodsSkuModel sku;
	private GoodsPackModel pack;
	private String unitName;
	private Integer specQty;
	private String spec;
	private EnumModel<Integer> packType;
	private String sysBatchNo;
	private Integer expectUnitQty; // 应拣件数
	private Integer expectMinUnitQty; // 应拣散数
	private Integer expectQty; // 应捡数量
	private Integer pickUnitQty; // 实拣件数
	private Integer pickMinUnitQty; // 实拣散数
	private Integer pickQty; // 实捡数量
	private Integer diffQty; // 差异数量
	private GoodsPackModel baseWeightPack;
	private GoodsPackModel baseVolumePack;

	private Integer expectPickQty;// 本地验证使用的字段,与后台无关

	/**
	 * 拣货下架的目标库区
	 */
	private StoreModel pickToStore;

	/**
	 * 拣货下架的目标货位
	 */
	private ShelfModel pickToShelf;

	private Boolean pickFlag;

	private Long oldDetailId;// 关联原来的pickDetailId

	private EnumModel<Integer> saleType;

	/**
	 * 重量
	 */
	private BigDecimal weight;

	/**
	 * 体积
	 */
	private BigDecimal volume;

	private BigDecimal baseWeight;

	private BigDecimal baseVolume;

	/**
	 * 序号
	 */
	private Integer ordinalNo;

	public PickBillModel getBill() {
		return bill;
	}

	public void setBill(PickBillModel bill) {
		this.bill = bill;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public ShelfModel getShelf() {
		return shelf;
	}

	public void setShelf(ShelfModel shelf) {
		this.shelf = shelf;
	}

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public GoodsPackModel getPack() {
		return pack;
	}

	public void setPack(GoodsPackModel pack) {
		this.pack = pack;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public EnumModel<Integer> getPackType() {
		return packType;
	}

	public void setPackType(EnumModel<Integer> packType) {
		this.packType = packType;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
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

	public GoodsPackModel getBaseWeightPack() {
		return baseWeightPack;
	}

	public void setBaseWeightPack(GoodsPackModel baseWeightPack) {
		this.baseWeightPack = baseWeightPack;
	}

	public GoodsPackModel getBaseVolumePack() {
		return baseVolumePack;
	}

	public void setBaseVolumePack(GoodsPackModel baseVolumePack) {
		this.baseVolumePack = baseVolumePack;
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public StoreModel getPickToStore() {
		return pickToStore;
	}

	public void setPickToStore(StoreModel pickToStore) {
		this.pickToStore = pickToStore;
	}

	public Integer getExpectPickQty() {
		return expectPickQty;
	}

	public void setExpectPickQty(Integer expectPickQty) {
		this.expectPickQty = expectPickQty;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Boolean getPickFlag() {
		return pickFlag;
	}

	public void setPickFlag(Boolean pickFlag) {
		this.pickFlag = pickFlag;
	}

	public ShelfModel getPickToShelf() {
		return pickToShelf;
	}

	public void setPickToShelf(ShelfModel pickToShelf) {
		this.pickToShelf = pickToShelf;
	}

	public Long getOldDetailId() {
		return oldDetailId;
	}

	public void setOldDetailId(Long oldDetailId) {
		this.oldDetailId = oldDetailId;
	}

	public EnumModel<Integer> getSaleType() {
		return saleType;
	}

	public void setSaleType(EnumModel<Integer> saleType) {
		this.saleType = saleType;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(BigDecimal baseWeight) {
		this.baseWeight = baseWeight;
	}

	public BigDecimal getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(BigDecimal baseVolume) {
		this.baseVolume = baseVolume;
	}

}
