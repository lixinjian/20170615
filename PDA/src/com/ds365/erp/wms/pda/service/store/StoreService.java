package com.ds365.erp.wms.pda.service.store;

import java.util.List;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class StoreService {

	private JsonParser<JsonResult<List<StoreModel>>> storeParser = new JsonParser<JsonResult<List<StoreModel>>>() {
	};

	public void searchStoreByParams(StoreQueryParamsModel queryParamsModel, ServiceCallBack<List<StoreModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.warehouse_store_searchByParams);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<StoreModel>>>(storeParser, callBack) {
		});
	}

}
