package com.ds365.erp.wms.pda.service.purchase;

import java.util.List;
import java.util.Map;

import com.ds365.commons.AbstractBaseService;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JacksonUtils;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class PurchaseEnterService extends AbstractBaseService{

	/**
	 * 采购入库单的解析器
	 */
	private JsonParser<JsonResult<QueryResult<PurchaseEnterBillModel>>> purchaseEnterBillParser = new JsonParser<JsonResult<QueryResult<PurchaseEnterBillModel>>>() {
	};

	/**
	 * 采购入库单详情的解析器
	 */
	private JsonParser<JsonResult<List<PurchaseEnterDetailModel>>> purchaseEnterDetailParser = new JsonParser<JsonResult<List<PurchaseEnterDetailModel>>>() {
	};

	private JsonParser<JsonResult<QueryResult<PurchaseReturnOutBillModel>>> purchaseReturnOutParser = new JsonParser<JsonResult<QueryResult<PurchaseReturnOutBillModel>>>() {
	};

	private JsonParser<JsonResult<List<PurchaseReturnOutDetailModel>>> purchaseReturnOutDetailParser = new JsonParser<JsonResult<List<PurchaseReturnOutDetailModel>>>() {
	};

	/**
	 * 获取采购入库订单
	 */
	public void searchPagePurchaseEnterBillByParams(PurchaseEnterBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseEnterBillModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		
		Map<String,Object> map  = BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		
		params.setUrl(ConstantUrl.purchase_purchase_searchPagePurchaseEnterBillByParams);

		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<PurchaseEnterBillModel>>>(
				purchaseEnterBillParser, callBack) {
		});
	}

	/**
	 * 查询采购入库单详情
	 */
	public void searchPurchaseEnterDetailsByBillId(Long billId,
			ServiceCallBack<List<PurchaseEnterDetailModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();

		params.setUrl(ConstantUrl.purchase_purchase_searchPurchaseEnterDetailsByBillId);
		params.getParams().put(PdaConstants.billId, billId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<PurchaseEnterDetailModel>>>(
				purchaseEnterDetailParser, callBack) {
		});
	}

	/**
	 * 查询采购退货出库单
	 */
	public void searchPagePurchaseReturnOutBillByParams(PurchaseEnterBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<PurchaseReturnOutBillModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map<String,Object> map  = BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.purchase_purchase_searchPagePurchaseEnterBillByParams);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<PurchaseReturnOutBillModel>>>(
				purchaseReturnOutParser, callBack) {
		});
	}

	/**
	 * 查询采购退货出库单详情
	 */
	public void searchPurchaseReturnOutDetailsByBillId(Long billId,
			ServiceCallBack<List<PurchaseReturnOutDetailModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.purchase_purchase_searchPurchaseEnterDetailsByBillId);
		params.getParams().put(PdaConstants.billId, billId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<PurchaseReturnOutDetailModel>>>(
				purchaseReturnOutDetailParser, callBack) {
		});
	}

	/**
	 * 采购入库-提交
	 */
	public void createPurchaseEnterBill(PurchaseEnterBillCreateParamsModel createParams,
			ServiceCallBack<String> callBack) {

		String url = ConstantUrl.purchase_purchase_createPurchaseEnterBill;
		createPurchaseBillByParams(url, createParams, callBack);

	}
	/**
	 * 采购退货出库-提交
	 */
	public void createPurchaseOutBill(PurchaseEnterBillCreateParamsModel createParams,
			ServiceCallBack<String> callBack) {
		
		String url = ConstantUrl.purchase_purchase_createPurchaseOutBill;
		createPurchaseBillByParams(url, createParams, callBack);
	}
	
	private void createPurchaseBillByParams(String url, PurchaseEnterBillCreateParamsModel createParams,
			ServiceCallBack<String> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setUrl(url);
		params.setJsonBody(submitJsonResult);
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}
}
