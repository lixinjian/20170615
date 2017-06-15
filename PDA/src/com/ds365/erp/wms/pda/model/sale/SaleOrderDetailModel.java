package com.ds365.erp.wms.pda.model.sale;

import java.io.Serializable;

public class SaleOrderDetailModel extends BaseSaleOrderDetailModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4862403192919339738L;

	/*
	 * //下单包装单位 private GoodsSkuPackEntity orderPack;
	 * 
	 * private String orderUnitName; //下单包装数量 private Integer orderSpecQty; //
	 * 下单包装单位单价 private BigDecimal orderUnitPrice;
	 */
	// 下单件数
	private Integer orderUnitQty;
	private Integer orderMinUnitQty;
	// 下单数量
	private Integer orderQty;

	/*
	 * //出库包装单位 private GoodsSkuPackEntity outPack;
	 * 
	 * private String outUnitName; // 出库包装数量 private Integer outSpecQty; //
	 * 出库件数（包装单位） private Integer outUnitQty; // private Integer outMinUnitQty;
	 * // 出库数量[退货申请数量] private Integer outQty;
	 */

	// 主单价
	// private BigDecimal price;
	// 签收件数(客户签收时回写,包装单位同出库包装单位)
	private Integer signUnitQty;
	// 签收散数
	private Integer signMinUnitQty;
	// 签收数量(客户签收时回写,包装单位同出库包装单位)
	private Integer signQty;

	public Integer getOrderUnitQty() {
		return orderUnitQty;
	}

	public void setOrderUnitQty(Integer orderUnitQty) {
		this.orderUnitQty = orderUnitQty;
	}

	public Integer getOrderMinUnitQty() {
		return orderMinUnitQty;
	}

	public void setOrderMinUnitQty(Integer orderMinUnitQty) {
		this.orderMinUnitQty = orderMinUnitQty;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Integer getSignUnitQty() {
		return signUnitQty;
	}

	public void setSignUnitQty(Integer signUnitQty) {
		this.signUnitQty = signUnitQty;
	}

	public Integer getSignMinUnitQty() {
		return signMinUnitQty;
	}

	public void setSignMinUnitQty(Integer signMinUnitQty) {
		this.signMinUnitQty = signMinUnitQty;
	}

	public Integer getSignQty() {
		return signQty;
	}

	public void setSignQty(Integer signQty) {
		this.signQty = signQty;
	}

}