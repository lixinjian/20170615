package com.ds365.erp.wms.pda.service.stock;

import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class SkuShelfBatchStockService {

	private JsonParser<JsonResult<QueryResult<SkuShelfBatchStockModel>>> shelfBatchStockParser = new JsonParser<JsonResult<QueryResult<SkuShelfBatchStockModel>>>() {
	};

	public void searchPageSkuShelfBatchStockByParams(String url,SkuShelfBatchStockQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SkuShelfBatchStockModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(url);

		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<SkuShelfBatchStockModel>>>(
				shelfBatchStockParser, callBack) {
		});

	}

}
