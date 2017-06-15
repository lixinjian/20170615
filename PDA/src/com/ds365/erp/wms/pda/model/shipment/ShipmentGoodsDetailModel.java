package com.ds365.erp.wms.pda.model.shipment;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseOperationModel;
import com.ds365.commons.enums.EnumModel;
import com.ds365.erp.wms.pda.model.goods.GoodsModel;
import com.ds365.erp.wms.pda.model.goods.GoodsPackModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;

public class ShipmentGoodsDetailModel extends BaseOperationModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2733818605271292520L;

	private ShipmentOrderBillModel bill;

	private GoodsSkuModel sku;

	private String skuCode;

	private GoodsModel goods;

	private String goodsName;

	private String spec;

	private String barcode;

	private WarehouseModel warehouse;

	private GoodsPackModel pack;

	private EnumModel<Integer> packType;

	private GoodsPackModel baseWeightPack;

	private GoodsPackModel baseVolumePack;

	private Integer specQty;

	private String unitName;
	private String minUnitName;

	private int weight;

	private int volume;

	private double money;

	private Integer unitQty;

	private Integer minUnitQty;

	private Integer qty;

	private Integer ordinalNo;

	private String baseWeight;

	private String baseVolume;

	public ShipmentOrderBillModel getBill() {
		return bill;
	}

	public void setBill(ShipmentOrderBillModel bill) {
		this.bill = bill;
	}

	public GoodsSkuModel getSku() {
		return sku;
	}

	public void setSku(GoodsSkuModel sku) {
		this.sku = sku;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public GoodsModel getGoods() {
		return goods;
	}

	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public WarehouseModel getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
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

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public String getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(String baseWeight) {
		this.baseWeight = baseWeight;
	}

	public String getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(String baseVolume) {
		this.baseVolume = baseVolume;
	}

	public String getMinUnitName() {
		return minUnitName;
	}

	public void setMinUnitName(String minUnitName) {
		this.minUnitName = minUnitName;
	}

}