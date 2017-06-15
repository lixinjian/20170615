package com.ds365.erp.wms.pda.service.stockshift;

import com.ds365.commons.AbstractBaseService;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JacksonUtils;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftBillCreateParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class StockShiftService extends AbstractBaseService{

	public void stockShiftCreate(StockShiftBillCreateParamsModel  createParams,
			ServiceCallBack<String> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setUrl(ConstantUrl.stockShift_stockShift_create);
		params.setJsonBody(submitJsonResult);
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
		
	}
	
}
