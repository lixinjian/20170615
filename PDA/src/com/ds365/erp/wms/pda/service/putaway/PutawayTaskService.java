package com.ds365.erp.wms.pda.service.putaway;

import java.util.List;
import java.util.Map;

import com.ds365.commons.AbstractBaseService;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JacksonUtils;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskCreateParamsModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskQueryParamModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class PutawayTaskService extends AbstractBaseService{

	private JsonParser<JsonResult<List<PutAwayTaskModel>>> putawayTaskParser = new JsonParser<JsonResult<List<PutAwayTaskModel>>>() {
	};

	public void searchPagePutawayTaskByParamsForPut(PutawayTaskQueryParamModel queryParamModel,
			ServiceCallBack<List<PutAwayTaskModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.putaway_putaway_searchPagePutawayTaskByParamsForPut);

		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<PutAwayTaskModel>>>(putawayTaskParser, callBack) {
				});
	}
	
	public void createPutawayTask(PutawayTaskCreateParamsModel createParams,
			ServiceCallBack<String> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setUrl(ConstantUrl.putaway_putaway_createPutawayTask);
		params.setJsonBody(submitJsonResult);
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}
}
