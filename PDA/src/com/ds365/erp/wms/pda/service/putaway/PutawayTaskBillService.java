package com.ds365.erp.wms.pda.service.putaway;

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
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskBillModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskBillQueryParamModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class PutawayTaskBillService extends AbstractBaseService{

	private JsonParser<JsonResult<QueryResult<PutawayTaskBillModel>>> putawayTaskBillParser = new JsonParser<JsonResult<QueryResult<PutawayTaskBillModel>>>() {
	};
	
	private JsonParser<JsonResult<PutawayTaskBillModel>> searchPutawayTaskBillByIdParser = new JsonParser<JsonResult<PutawayTaskBillModel>>() {
	};

	private JsonParser<JsonResult<List<PutawayTaskDetailModel>>> putawayTaskDetailParser = new JsonParser<JsonResult<List<PutawayTaskDetailModel>>>() {
	};

	public void searchPagePutawayTaskBillsForNotPutaway(PutawayTaskBillQueryParamModel queryParamModel,
			ServiceCallBack<QueryResult<PutawayTaskBillModel>> callBack) {
		
		String url = ConstantUrl.putaway_putawayBill_searchPagePutawayTaskBillsForNotPutaway;
		searchPagePutawayTaskBills(url, queryParamModel, callBack);
	}
	
	public void searchPagePutawayTaskBillsForPutawayed(PutawayTaskBillQueryParamModel queryParamModel,
			ServiceCallBack<QueryResult<PutawayTaskBillModel>> callBack) {
		
		String url = ConstantUrl.putaway_putawayBill_searchPagePutawayTaskBillsForPutawayed;
		searchPagePutawayTaskBills(url, queryParamModel, callBack);
	}
	
	private void searchPagePutawayTaskBills(String url, PutawayTaskBillQueryParamModel queryParamModel,
			ServiceCallBack<QueryResult<PutawayTaskBillModel>> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamModel);
		
		params.setParams(map);
		params.setUrl(url);
				
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<PutawayTaskBillModel>>>(
				putawayTaskBillParser, callBack) {
		});
	}
	
	public void searchPutawayTaskBillById(Long billId,
			ServiceCallBack<PutawayTaskBillModel> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.putaway_putawayBill_searchPutawayTaskBillById);
		params.getParams().put(PdaConstants.id, billId);
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<PutawayTaskBillModel>>(
				searchPutawayTaskBillByIdParser, callBack) {
		});
	}

	public void searchPutawayTaskDetailByBillId(Long billId,
			ServiceCallBack<List<PutawayTaskDetailModel>> callBack) {
		
		String url = ConstantUrl.putaway_putawayBill_searchPutawayTaskDetailByBillId;
		searchPutawayTaskDetail(url, billId, callBack);
	}
	
	public void searchPutawayTaskDetailForNotPutawayByBillId(Long billId,
			ServiceCallBack<List<PutawayTaskDetailModel>> callBack) {
		
		String url = ConstantUrl.putaway_putawayBill_searchPutawayTaskDetailForNotPutawayByBillId;  
		searchPutawayTaskDetail(url, billId, callBack);
	}
	
	public void searchPutawayTaskDetailForPutawayedByBillId(Long billId,
			ServiceCallBack<List<PutawayTaskDetailModel>> callBack) {
		
		String url = ConstantUrl.putaway_putawayBill_searchPutawayTaskDetailForPutawayedByBillId;
		searchPutawayTaskDetail(url, billId, callBack);
	}
	
	private void searchPutawayTaskDetail(String url, Long billId, ServiceCallBack<List<PutawayTaskDetailModel>> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(url);
		params.getParams().put(PdaConstants.billId,billId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<PutawayTaskDetailModel>>>(
				putawayTaskDetailParser, callBack) {
		});
	}
	
	public void putaway(Long taskDetailId, ServiceCallBack<String> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(taskDetailId);
		params.setUrl(ConstantUrl.putaway_putawayBill_putaway);
		params.setJsonBody(submitJsonResult);
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}
	
	/**
	 * 修改上架明细
	 */
	public void modifyPutawayTaskDetail(List<PutawayTaskDetailCreateParamsModel> putawayTaskDetails, ServiceCallBack<String> callBack){
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(putawayTaskDetails);
		
		params.setUrl(ConstantUrl.putaway_putawayBill_modifyPutawayTaskDetail);
		params.setJsonBody(submitJsonResult);
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}
	
}
