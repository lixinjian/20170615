package com.ds365.erp.wms.pda.model.putaway;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

public class PutawayTaskDetailModel extends BaseOperationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3877201333709230333L;

	private PutawayTaskBillModel bill;

	private GoodsSkuModel sku;

	private WarehouseModel warehouse;

	private StoreModel tempStore;// 库区（临时库区）

	private ShelfModel tempShelf;// 临时的货位

	private String tempShelfCode;// 临时货位

	private StoreModel store;// 库区（入库的库区）

	private ShelfModel shelf;// 真正的货位
	private String shelfCode;

	private String sysBatchNo;

	private GoodsPackModel pack;// 上架的包装

	private EnumModel<Integer> packType;//

	private Integer specQty;// 包装数量

	private String unitName;// 单位名称

	private Integer unitQty;

	private Integer minUnitQty;

	private Integer qty;// 上架的数量

	private Boolean putFlag;// 是否已上架

	private Long relatedDetailId;
	
	
	private Integer remainUnitQty;// 上架剩余件数
	private Integer remainMinUnitQty;// 上架剩余散数
	private Integer remainQty;// 上架剩余的数量
	private Integer expectQty;// 应上架数量

	public PutawayTaskBillModel getBill() {
		return bill;
	}

	public void setBill(PutawayTaskBillModel bill) {
		this.bill = bill;
	}

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

	public StoreModel getTempStore() {
		return tempStore;
	}

	public void setTempStore(StoreModel tempStore) {
		this.tempStore = tempStore;
	}

	public ShelfModel getTempShelf() {
		return tempShelf;
	}

	public void setTempShelf(ShelfModel tempShelf) {
		this.tempShelf = tempShelf;
	}

	public String getTempShelfCode() {
		return tempShelfCode;
	}

	public void setTempShelfCode(String tempShelfCode) {
		this.tempShelfCode = tempShelfCode;
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

	public Boolean getPutFlag() {
		return putFlag;
	}

	public void setPutFlag(Boolean putFlag) {
		this.putFlag = putFlag;
	}

	public Long getRelatedDetailId() {
		return relatedDetailId;
	}

	public void setRelatedDetailId(Long relatedDetailId) {
		this.relatedDetailId = relatedDetailId;
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

	public Integer getExpectQty() {
		return expectQty;
	}

	public void setExpectQty(Integer expectQty) {
		this.expectQty = expectQty;
	}
}
