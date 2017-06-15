package com.ds365.erp.wms.pda.service.stock;

import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.stock.SkuStockAccountBookModel;
import com.ds365.erp.wms.pda.model.stock.SkuStockAccountBookQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class SkuStockAccountBookService {

	private JsonParser<JsonResult<QueryResult<SkuStockAccountBookModel>>> skuStockAccountBookParser = new JsonParser<JsonResult<QueryResult<SkuStockAccountBookModel>>>() {
	};

	public void searchPageSkuStockAccountBook(SkuStockAccountBookQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SkuStockAccountBookModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.skuStock_skuStock_searchPageSkuStockAccountBook);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<SkuStockAccountBookModel>>>(
				skuStockAccountBookParser, callBack) {
		});

	}

}
