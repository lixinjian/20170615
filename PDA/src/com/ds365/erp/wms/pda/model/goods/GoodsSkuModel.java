package com.ds365.erp.wms.pda.model.goods;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;
import com.ds365.commons.enums.EnumModel;

public class GoodsSkuModel extends BaseActiveModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 931083423035593565L;

	private String code;// sku编码

	private String name;

	private GoodsModel goods;

	private String helpCode;

	private String spec;
	
//	private String unitName;
	
	private String minUnitName;

	private String barcode;// sku条码

	private double purchasePrice;

	private double costPrice;

	private double salePrice;

	private double marketPrice;

	private double purchaseUnitPrice;

	private double costUnitPrice;

	private double saleUnitPrice;

	private double marketUnitPrice;

	private GoodsPackModel purchaseOrderPack;

	private GoodsPackModel saleOrderPack;

	private double baseWeight;

	private double baseVolume;

	private GoodsPackModel baseWeightPack;

	private GoodsPackModel baseVolumePack;

	private GoodsSkuExtModel goodsSkuExtEntity;

	private GoodsPackModel goodsPack;

	private EnumModel<Integer> priceWay;

	private boolean batchManageFlag;

	private int guaranteePeriod;

	private String purchaseInfo;

	private String saleInfo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GoodsModel getGoods() {
		return goods;
	}

	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
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

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public double getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}

	public void setPurchaseUnitPrice(double purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}

	public double getCostUnitPrice() {
		return costUnitPrice;
	}

	public void setCostUnitPrice(double costUnitPrice) {
		this.costUnitPrice = costUnitPrice;
	}

	public double getSaleUnitPrice() {
		return saleUnitPrice;
	}

	public void setSaleUnitPrice(double saleUnitPrice) {
		this.saleUnitPrice = saleUnitPrice;
	}

	public double getMarketUnitPrice() {
		return marketUnitPrice;
	}

	public void setMarketUnitPrice(double marketUnitPrice) {
		this.marketUnitPrice = marketUnitPrice;
	}

	public GoodsPackModel getPurchaseOrderPack() {
		return purchaseOrderPack;
	}

	public void setPurchaseOrderPack(GoodsPackModel purchaseOrderPack) {
		this.purchaseOrderPack = purchaseOrderPack;
	}

	public GoodsPackModel getSaleOrderPack() {
		return saleOrderPack;
	}

	public void setSaleOrderPack(GoodsPackModel saleOrderPack) {
		this.saleOrderPack = saleOrderPack;
	}

	public double getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(double baseWeight) {
		this.baseWeight = baseWeight;
	}

	public double getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(double baseVolume) {
		this.baseVolume = baseVolume;
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

	public GoodsSkuExtModel getGoodsSkuExtEntity() {
		return goodsSkuExtEntity;
	}

	public void setGoodsSkuExtEntity(GoodsSkuExtModel goodsSkuExtEntity) {
		this.goodsSkuExtEntity = goodsSkuExtEntity;
	}

	public GoodsPackModel getGoodsPack() {
		return goodsPack;
	}

	public void setGoodsPack(GoodsPackModel goodsPack) {
		this.goodsPack = goodsPack;
	}

	public EnumModel<Integer> getPriceWay() {
		return priceWay;
	}

	public void setPriceWay(EnumModel<Integer> priceWay) {
		this.priceWay = priceWay;
	}

	public boolean isBatchManageFlag() {
		return batchManageFlag;
	}

	public void setBatchManageFlag(boolean batchManageFlag) {
		this.batchManageFlag = batchManageFlag;
	}

	public int getGuaranteePeriod() {
		return guaranteePeriod;
	}

	public void setGuaranteePeriod(int guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}

	public String getPurchaseInfo() {
		return purchaseInfo;
	}

	public void setPurchaseInfo(String purchaseInfo) {
		this.purchaseInfo = purchaseInfo;
	}

	public String getSaleInfo() {
		return saleInfo;
	}

	public void setSaleInfo(String saleInfo) {
		this.saleInfo = saleInfo;
	}

	public String getMinUnitName() {
		return minUnitName;
	}

	public void setMinUnitName(String minUnitName) {
		this.minUnitName = minUnitName;
	}

	/*public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}*/

}