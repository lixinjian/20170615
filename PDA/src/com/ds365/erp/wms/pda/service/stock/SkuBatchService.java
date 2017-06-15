package com.ds365.erp.wms.pda.service.stock;

import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.stock.SkuBatchModel;
import com.ds365.erp.wms.pda.model.stock.SkuBatchQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class SkuBatchService {

	JsonParser<JsonResult<QueryResult<SkuBatchModel>>> skuBatchParser = new JsonParser<JsonResult<QueryResult<SkuBatchModel>>>() {
	};

	public void searchPageSkuBatchByParams(SkuBatchQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SkuBatchModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.skuStock_skuStock_searchPageSkuBatchByParams);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<QueryResult<SkuBatchModel>>>(skuBatchParser, callBack) {
				});

	}

}
