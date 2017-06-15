package com.ds365.erp.wms.pda.common.base;

import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskCreateParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterBillCreateParamsModel;

public class SubmitInfo {

	// 收货入库、采购退货
	public static PurchaseEnterBillCreateParamsModel PurchaseEnterBillCreateInfo;

	// 退货入库、销售出库
	public static SaleReturnEnterBillCreateParamsModel SaleReturnBillCreateInfo;

	// 上架
	public static PutawayTaskCreateParamsModel PutawayTaskCreateInfo;

	public static PurchaseEnterBillCreateParamsModel getPurchaseEnterBillCreateInfo() {
		return PurchaseEnterBillCreateInfo;
	}

	public static void setPurchaseEnterBillCreateInfo(PurchaseEnterBillCreateParamsModel purchaseEnterBillCreateInfo) {
		PurchaseEnterBillCreateInfo = purchaseEnterBillCreateInfo;
	}

	public static SaleReturnEnterBillCreateParamsModel getSaleReturnBillCreateInfo() {
		return SaleReturnBillCreateInfo;
	}

	public static void setSaleReturnBillCreateInfo(SaleReturnEnterBillCreateParamsModel saleReturnBillCreateInfo) {
		SaleReturnBillCreateInfo = saleReturnBillCreateInfo;
	}

	public static PutawayTaskCreateParamsModel getPutawayTaskCreateInfo() {
		return PutawayTaskCreateInfo;
	}

	public static void setPutawayTaskCreateInf(PutawayTaskCreateParamsModel putawayTaskCreateInfo) {
		PutawayTaskCreateInfo = putawayTaskCreateInfo;
	}

}
