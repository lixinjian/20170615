package com.ds365.erp.wms.pda.service.purchase;

import java.util.List;
import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderDetailModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class PurchaseOrderService {

	/**
	 * 采购订单的解析器
	 */
	private JsonParser<JsonResult<QueryResult<PurchaseOrderBillModel>>> purchaseOrderBillParser = new JsonParser<JsonResult<QueryResult<PurchaseOrderBillModel>>>() {
	};

	/**
	 * 采购订单详情的解析器
	 */
	private JsonParser<JsonResult<List<PurchaseOrderDetailModel>>> purchaseOrderDetailParser = new JsonParser<JsonResult<List<PurchaseOrderDetailModel>>>() {
	};

	private JsonParser<JsonResult<QueryResult<PurchaseReturnOrderBillModel>>> purchaseReturnOrderParser = new JsonParser<JsonResult<QueryResult<PurchaseReturnOrderBillModel>>>() {
	};

	private JsonParser<JsonResult<List<PurchaseReturnOrderDetailModel>>> purchaseReturnOrderDetailParser = new JsonParser<JsonResult<List<PurchaseReturnOrderDetailModel>>>() {
	};
	
	/**
	 * 获取采购订单-未收货
	 */
	public void searchPagePurchaseOrderBillForNotReceive(PurchaseOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseOrderBillModel>> callBack) {
		
		String url = ConstantUrl.purchase_purchase_searchPagePurchaseOrderBillForNotReceive;
		searchPagePurchaseOrderBill(url, queryParamsModel, callBack);
	}
	
	/**
	 * 获取采购订单-已收货
	 */
	public void searchPagePurchaseOrderBillForReceived(PurchaseOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseOrderBillModel>> callBack) {
		
		String url = ConstantUrl.purchase_purchase_searchPagePurchaseOrderBillForReceived;
		searchPagePurchaseOrderBill(url, queryParamsModel, callBack);
	}

	private void searchPagePurchaseOrderBill(String url, PurchaseOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseOrderBillModel>> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		Map<String,Object> map  = BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(url);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<PurchaseOrderBillModel>>>(purchaseOrderBillParser, callBack) {
		});
	}
	
	
	/**
	 * 获取采购订单详情
	 */
	public void searchPurchaseOrderDetailsByBillId(Long billId,
			ServiceCallBack<List<PurchaseOrderDetailModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		
		params.setUrl(ConstantUrl.purchase_purchase_searchPurchaseOrderDetailsByBillId);
		
		params.getParams().put(PdaConstants.billId, billId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<PurchaseOrderDetailModel>>>(purchaseOrderDetailParser, callBack) {
		});
	}
	
	/**
	 * 采购退货-未退货
	 */
	public void searchPagePurchaseReturnOrderBillForNotReturn(PurchaseOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseReturnOrderBillModel>> callBack) {
		
		String url = ConstantUrl.purchase_purchase_searchPagePurchaseReturnOrderBillForNotReturn;
		searchPagePurchaseReturnOrderBill(url, queryParamsModel, callBack);
	}
	
	/**
	 * 采购退货-未退货(供应商)
	 */
	public void searchPagePurchaseReturnOrderBillBySupplierForNotReturn(PurchaseOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseReturnOrderBillModel>> callBack) {
		
		String url = ConstantUrl.purchase_purchase_searchPagePurchaseReturnOrderBillBySupplierForNotReturn;
		searchPagePurchaseReturnOrderBill(url, queryParamsModel, callBack);
	}
	
	/**
	 * 采购退货-已退货
	 */
	public void searchPagePurchaseReturnOrderBillForReturn(PurchaseOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseReturnOrderBillModel>> callBack) {
		String url = ConstantUrl.purchase_purchase_searchPagePurchaseReturnOrderBillForReturn;
		searchPagePurchaseReturnOrderBill(url, queryParamsModel, callBack);
	}
	
	
	private void searchPagePurchaseReturnOrderBill(String url, PurchaseOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseReturnOrderBillModel>> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		Map<String,Object> map  = BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(url);
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<PurchaseReturnOrderBillModel>>>(purchaseReturnOrderParser, callBack) {
		});
	}
	
	/**
	 * 采购退货申请单详情列表
	 */
	public void searchPurchaseReturnOrderDetailsByBillId(Long billId,
			ServiceCallBack<List<PurchaseReturnOrderDetailModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		params.getParams().put(PdaConstants.billId, billId);
		params.setUrl(ConstantUrl.purchase_purchase_searchPurchaseOrderDetailsByBillId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<PurchaseReturnOrderDetailModel>>>(purchaseReturnOrderDetailParser, callBack) {
		});
	}
}
