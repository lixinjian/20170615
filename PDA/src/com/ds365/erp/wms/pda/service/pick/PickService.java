package com.ds365.erp.wms.pda.service.pick;

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
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailMapModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillRegisterParamModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class PickService extends AbstractBaseService{

	private JsonParser<JsonResult<QueryResult<PickBillModel>>> pickBillParser = new JsonParser<JsonResult<QueryResult<PickBillModel>>>() {
	};

	private JsonParser<JsonResult<List<PickBatchDetailModel>>> pickBatchDetailParser = new JsonParser<JsonResult<List<PickBatchDetailModel>>>() {
	};
	
	private JsonParser<JsonResult<PickBillModel>> searchPickBillByIdParser = new JsonParser<JsonResult<PickBillModel>>() {
	};
	
	/**
	 * 分页查询拣货单-未拣货
	 */
	public void searchPagePickBillForNotPick(PickBillQueryParamsModel queryParamsModel, ServiceCallBack<QueryResult<PickBillModel>> callBack) {
		
		String url = ConstantUrl.pick_pickBill_searchPagePickBillForNotPick;
		searchPagePickBill(url, queryParamsModel, callBack);
	}
	
	/**
	 * 分页查询拣货单-已拣货
	 */
	public void searchPagePickBillForPicked(PickBillQueryParamsModel queryParamsModel, ServiceCallBack<QueryResult<PickBillModel>> callBack) {
		
		String url = ConstantUrl.pick_pickBill_searchPagePickBillForPicked;
		searchPagePickBill(url, queryParamsModel, callBack);
	}

	/**
	 * 分页查询拣货单列表
	 */
	public void searchPagePickBillByParams(PickBillQueryParamsModel queryParamsModel, ServiceCallBack<QueryResult<PickBillModel>> callBack) {
		
		String url = ConstantUrl.pick_pickBill_searchPagePickBillByParams;
		searchPagePickBill(url, queryParamsModel, callBack);
	}
	
	private void searchPagePickBill(String url, PickBillQueryParamsModel queryParamsModel, ServiceCallBack<QueryResult<PickBillModel>> callBack){
		
		
		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(url);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<QueryResult<PickBillModel>>>(pickBillParser, callBack) {
		});
	}
	
	/**
	 * 查询拣货批次明细
	 */
	public void searchPickBatchDetailByBillId(Long billId,
			ServiceCallBack<List<PickBatchDetailModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.pick_pickBill_searchPickBatchDetailByBillId);
		params.getParams().put(PdaConstants.billId,billId);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<PickBatchDetailModel>>>(pickBatchDetailParser, callBack) {
				});
	}
	
	public void searchPickBatchDetailByBillIdForNotPick(Long billId,
			ServiceCallBack<List<PickBatchDetailModel>> callBack) {
		
		String url = ConstantUrl.pick_pickBill_searchPickBatchDetailByBillIdForNotPick;  
		searchPickBatchDetailByParams(url, billId, callBack);
	}
	
	public void searchPickBatchDetailByBillIdForPicked(Long billId,
			ServiceCallBack<List<PickBatchDetailModel>> callBack) {
		
		String url = ConstantUrl.pick_pickBill_searchPickBatchDetailByBillIdForPicked;
		searchPickBatchDetailByParams(url, billId, callBack);
	}
	
	private void searchPickBatchDetailByParams(String url, Long billId
			,ServiceCallBack<List<PickBatchDetailModel>> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		
		params.setUrl(url);
		params.getParams().put(PdaConstants.billId,billId);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<PickBatchDetailModel>>>(pickBatchDetailParser, callBack) {
		});
		
	}
	
	public void searchPickBillById(Long billId
			,ServiceCallBack<PickBillModel> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		
		params.setUrl(ConstantUrl.pick_pickBill_searchPickBillById);
		params.getParams().put(PdaConstants.id,billId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<PickBillModel>>(searchPickBillByIdParser, callBack) {
		});
	}
	
	public void pickBillRegistByDetail(PickBatchDetailMapModel createParams,
			ServiceCallBack<String> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setUrl(ConstantUrl.pick_pickBill_registByDetail);
		params.setJsonBody(submitJsonResult);
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
		
	}
	
	public void pickBillRegisterByBill(PickBillRegisterParamModel  createParams,
			ServiceCallBack<String> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setUrl(ConstantUrl.pick_pickbill_registerByBill);
		params.setJsonBody(submitJsonResult);
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
		
	}
}
