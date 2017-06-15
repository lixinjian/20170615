package com.ds365.erp.wms.pda.model.sale;

import com.ds365.erp.wms.pda.common.billtypeenum.BillStateEnum;

/**
 * 销售订单状态
 * 
 * @author huiliwei
 * @date 2015年6月24日
 */
public enum SaleOrderBillStateEnum implements BillStateEnum {
//销售订单:待审核、已审核、已锁定、已生成装运单、已拣货、已出库、配送站已签收、客户已签收;  退货申请单:待销售审核、待财务审核、已审核、已退货 ；回库申请单：待销售审核、待财务审核、已审核、已回库
	WAIT_AUDIT(101, "待审核"), 
	AUDITED(102, "已审核"), 
	LOCKED(103, "已锁定"),
	SHIPMENTED(104, "已生成装运单"),
	PICKED(105, "已拣货"), 
	OUT_COMPLETE(106, "已出库"),
	DELIVER_STATION_SIGNED(107, "配送站已签收"), 
	DELIVER_STATION_GO(107, "配送站已出发"), 
	DELIVER_STATION_RECEIVE(109, "已送达客户"),//是指配送站已送达客户	
	CUSTOMER_SIGNED(108, "客户已签收"),
	
	WAIT_CUSTOMER_SERVICE_AUDIT_RETURN(201, "待客服审核"), 
    //WAIT_FINANCIAL_AUDIT_RETURN(202, "待财务审核"), 
	AUDITED_RETURN(102, "已审核"),
	AUDITED_RETURN_OK(102, "配送站确认退货单"),
	//RETURNED(204, "客户已退货"), 
	DELIVER_STATION_GOFORCONSUMER(107, "配送站退货取货已出发"), //配送站确认客户退货申请单
	DELIVER_STATION_GOBACK(107, "配送站已取货"), //配送站确认客户退货申请单
	DELIVER_STATION_CONSUMER_OK(107, "客户确认退货"), //客户签收
	DELIVER_STATION_CONSUMER_INSTORE(107, "退货入库"), //退货入库
	
	//DEVLIER_ACCEPT(112,"配送站已接受退货"),
	//DELIVER_STATION_RETURN(110, "配送站已退货"),
	DELIVER_STATION_RETURN_FALSE(111, "配送站已拒绝取货"),//配送站去客户取货
	
	//WAIT_CUSTOMER_SERVICE_AUDIT_BACK(301, "待客服审核"), 
    //WAIT_FINANCIAL_AUDIT_BACK(302, "待财务审核"),
	CONSUMER_REFURSE(102, "客户已拒收"), 
	CONSUMER_GOBACK_STATION(102, "客户拒收回配送站"),
	AUDITED_BACK(102, "客户已审核回库单"),//
	//AUDITED_BACK(102, "已审核"), 
	BACKED(304, "已回库");

	private Integer index;

	private String description;

	SaleOrderBillStateEnum(Integer index, String description) {
		this.index = index;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Integer getIndex() {
		return index;
	}

}
