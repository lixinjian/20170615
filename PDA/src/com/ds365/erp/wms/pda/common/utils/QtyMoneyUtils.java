package com.ds365.erp.wms.pda.common.utils;

import java.math.BigDecimal;

import com.ds365.erp.wms.pda.model.commons.QtyModel;

public class QtyMoneyUtils {

	
	
	
	/**
	 * 
	 * @param qty  单品总数量
	 * @param specQty  包装数量
	 * @return
	 */
	public static   QtyModel   getQtyEntity(Integer qty,Integer specQty){
		
		QtyModel  entity=new QtyModel();
		
		Integer unitQty=qty/specQty;
		
		Integer minUnitQty=qty-unitQty*specQty;
		
		entity.setUnitQty(unitQty);
		entity.setMinUnitQty(minUnitQty);
		entity.setQty(qty);
		entity.setSpecQty(specQty);
		return entity;
		
	}
	/**
	 * 计算单品数量
	 * @param qty
	 * @param specQty
	 * @return
	 */
	public static   Integer   getQty(Integer unitQty,Integer minUnitQty,Integer specQty){
		
		
		return unitQty*specQty+minUnitQty;
		
	}
	
	public static   BigDecimal   getPrice(BigDecimal unitPrice,Integer specQty){
		
		
		return BigDecimalUtil.divide(unitPrice, BigDecimal.valueOf(specQty));
		
	}
	public static   BigDecimal   getUnitPrice(BigDecimal price,Integer specQty){
		
		
		return BigDecimalUtil.multiply(price, BigDecimal.valueOf(specQty));
		
	}
	/**
	 * 根据件数、散数、件单价、散数单价计算金额(考虑到页面传递过来的件单价是准确的，单价是四舍五入的。所以采用这种方式计算金额)
	 * @param unitQty
	 * @param unitPrice
	 * @param minUnitQty
	 * @param price
	 */
	public  static  BigDecimal  getMoney(Integer unitQty,BigDecimal unitPrice,Integer minUnitQty,BigDecimal price){
		
		BigDecimal unitMoney=BigDecimalUtil.multiply(BigDecimal.valueOf(unitQty), unitPrice);
		BigDecimal minUnitMoney=BigDecimalUtil.multiply(BigDecimal.valueOf(minUnitQty), price);
		
		return BigDecimalUtil.add(unitMoney, minUnitMoney);
				
	}
	
	public  static  BigDecimal  getMoney(Integer qty,BigDecimal price){
		
		BigDecimal money=BigDecimalUtil.multiply(BigDecimal.valueOf(qty), price);
		
		return money;
				
	}
	
	
	
	
	
	
}
