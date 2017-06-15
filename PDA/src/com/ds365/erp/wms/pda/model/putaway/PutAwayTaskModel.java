package com.ds365.erp.wms.pda.model.putaway;

import java.io.Serializable;
import java.util.Date;

import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.commons.BaseCheckFlagModel;
import com.ds365.erp.wms.pda.model.commons.OrganizationModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PutAwayTaskModel extends BaseCheckFlagModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3580885301021590840L;

	private Long relativeBillId;

	private String relativeBillCode;

	private EnumModel<String> relativeBillType;

	private Long relativeDetailId;

	private EnumModel<Integer> putawayType;

	private GoodsSkuModel sku;

	private OrganizationModel warehouseOrganization;

	private WarehouseModel warehouse;

	private StoreModel store;

	private ShelfModel shelf;

	private String shelfCode;

	private String sysBatchNo;

	private GoodsPackModel pack;

	private EnumModel<Integer> packType;

	private Integer specQty;

	private String unitName;

	private String spec;

	private Integer qty;//上架的总数量

	private double costPrice;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date produceDate;

	private int guaranteePeriod;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh_CN", timezone = "GMT+8")
	private Date expireDate;

	private Integer remainUnitQty;//上架剩余件数

	private Integer remainMinUnitQty;//上架剩余散数

	private Integer remainQty;//上架剩余的数量

	private Integer unitQty;
	private Integer minUnitQty;
	
	private EnumModel<Integer> putawayState;

	public Long getRelativeBillId() {
		return relativeBillId;
	}

	public void setRelativeBillId(Long relativeBillId) {
		this.relativeBillId = relativeBillId;
	}

	public String getRelativeBillCode() {
		return relativeBillCode;
	}

	public void setRelativeBillCode(String relativeBillCode) {
		this.relativeBillCode = relativeBillCode;
	}

	public EnumModel<String> getRelativeBillType() {
		return relativeBillType;
	}

	public void setRelativeBillType(EnumModel<String> relativeBillType) {
		this.relativeBillType = relativeBillType;
	}

	public Long getRelativeDetailId() {
		return relativeDetailId;
	}

	public void setRelativeDetailId(Long relativeDetailId) {
		this.relativeDetailId = relativeDetailId;
	}

	public EnumModel<Integer> getPutawayType() {
		return putawayType;
	}

	public void setPutawayType(EnumModel<Integer> putawayType) {
		this.putawayType = putawayType;
	}

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public OrganizationModel getWarehouseOrganization() {
		return warehouseOrganization;
	}

	public void setWarehouseOrganization(OrganizationModel warehouseOrganization) {
		this.warehouseOrganization = warehouseOrganization;
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

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public String getSysBatchNo() {
		return sysBatchNo;
	}

	public void setSysBatchNo(String sysBatchNo) {
		this.sysBatchNo = sysBatchNo;
	}

	public GoodsPackModel getPack() {
		return pack;
	}

	public void setPack(GoodsPackModel pack) {
		this.pack = pack;
	}

	public EnumModel<Integer> getPackType() {
		return packType;
	}

	public void setPackType(EnumModel<Integer> packType) {
		this.packType = packType;
	}

	public Integer getSpecQty() {
		return specQty;
	}

	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public int getGuaranteePeriod() {
		return guaranteePeriod;
	}

	public void setGuaranteePeriod(int guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getRemainUnitQty() {
		return remainUnitQty;
	}

	public void setRemainUnitQty(Integer remainUnitQty) {
		this.remainUnitQty = remainUnitQty;
	}

	public Integer getRemainMinUnitQty() {
		return remainMinUnitQty;
	}

	public void setRemainMinUnitQty(Integer remainMinUnitQty) {
		this.remainMinUnitQty = remainMinUnitQty;
	}

	public Integer getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Integer remainQty) {
		this.remainQty = remainQty;
	}

	public EnumModel<Integer> getPutawayState() {
		return putawayState;
	}

	public void setPutawayState(EnumModel<Integer> putawayState) {
		this.putawayState = putawayState;
	}

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

}