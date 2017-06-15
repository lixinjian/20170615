package com.ds365.erp.wms.pda.service.stocktake;

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
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderBillCreateParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class StockTakeByShelfService extends AbstractBaseService{

	private JsonParser<JsonResult<QueryResult<SkuShelfBatchStockModel>>> stockTakeOrderDetailParser = new JsonParser<JsonResult<QueryResult<SkuShelfBatchStockModel>>>() {
	};

	public void searchPageSkuShelfBatchStockByParams(SkuShelfBatchStockQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SkuShelfBatchStockModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.skuStock_skuStock_searchPageSkuShelfBatchStockByParams);

		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<SkuShelfBatchStockModel>>>(
				stockTakeOrderDetailParser, callBack) {
		});
	}

	public void stockTakeCreate(StockTakeOrderBillCreateParamsModel createParams, ServiceCallBack<String> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setUrl(ConstantUrl.stockTake_stockTake_create);
		params.setJsonBody(submitJsonResult);

		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}

}
