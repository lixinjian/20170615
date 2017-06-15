package com.ds365.erp.wms.pda.service.enums;

import java.util.List;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.enums.EnumModel;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.message.model.MessageCategoryModel;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class EnumService {

	JsonParser<JsonResult<List<EnumModel<Long>>>> enumParser = new JsonParser<JsonResult<List<EnumModel<Long>>>>() {
	};
	
	JsonParser<JsonResult<List<MessageCategoryModel<Integer>>>> messageParser = new JsonParser<JsonResult<List<MessageCategoryModel<Integer>>>>() {
	};

	public void getEnumsForJsonResult(ServiceCallBack<List<EnumModel<Long>>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		
		params.setUrl(ConstantUrl.common_getPutawayTypeEnumsForJsonResult);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<EnumModel<Long>>>>(enumParser, callBack) {
				});

	}
	
	public void searchMessageCategoryForPdaEnum(ServiceCallBack<List<MessageCategoryModel<Integer>>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		
		params.setUrl(ConstantUrl.common_messageCategoryForPdaEnum);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<MessageCategoryModel<Integer>>>>(messageParser, callBack) {
		});
		
	}

}
