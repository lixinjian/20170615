package com.ds365.erp.wms.pda.common.billtypeenum;

/**
 * 
 * @author cgb
 *
 */
public enum BillTypeEnum implements BaseEnum<String> {
	
	
	PURCHASE_CONTRACT_BILL("AHT", "采购合同"),
	
	PURCHASE_SUB_CONTRACT_BILL("AZH", "采购子合同"),
	
	PURCHASE_ORDER_BILL("ADD", "采购订单"),
	PURCHASE_RECEIPT_BILL("ASH", "采购收货单"),	
	PURCHASE_ENTER_BILL("ARK", "采购入库单"),
	PURCHASE_RETURN_APPLY_BILL("ATD", "采购退货申请单"),
	PURCHASE_RETURN_OUT_BILL("ATK", "采购退货出库单"),
	PURCHASE_ACCOUNT_BILL("AZD", "应付对账账单"),
	PURCHASE_SETTLE_BILL("AJS", "应付结算单"),
	
	
	SALE_ORDER_BILL("BDD", "销售订单"),
	SALE_RETURN_APPLY_BILL("BTD", "销售退货申请单"),
	SALE_BACK_APPLY_BILL("BHD", "销售回库申请单"),
	
	SALE_OUT_BILL("BCK", "销售出库单"),
	SALE_RETURN_OUT_BILL("BTK", "销售退货入库单"),
	SALE_BACK_OUT_BILL("BHK", "销售回库确认单"),
	
	/*PAY_ACCOUNT_BILL("FDZ", "应付对账单"),
	PAY_SETTLE_BILL("FYFJ", "应付款结算单"),*/
	
	SALE_SETTLE_BILL("BJS", "应收结算单"),
	
	SHIPMENT_BILL("BZY", "装运单"),
	SHIPMENT_RETURN_BILL("BTY", "退货装运单"),
	PICK_BILL("BJH", "拣货单"),
	DELIVER_BILL("BSH", "配送站送货单"),
	
	
	//fms begin
	CUSTOMER_DEAL_BILL("FCCZ","客户充值单"),
	SUPPLIER_DEAL_BILL("FSCZ","供应商充值单"),
	DELIVER_STATION_DEAL_BILL("FDCZ","配送站充值单"),
	MARKETING_DEAL_BILL("FMZZ","市场部资金分配单"),
	COMPANY_ACCOUNT_BALANCE_BILL("FCDD","公司资金登记单"),
	//fms end
	;
	
	private String index;
	
	private String description;
	
	BillTypeEnum(String index,String description) {
		this.index=index;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public String getIndex() {
		return index;
	}

}
