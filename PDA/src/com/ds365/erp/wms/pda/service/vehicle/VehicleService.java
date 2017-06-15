package com.ds365.erp.wms.pda.service.vehicle;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.commons.VehicleModel;
import com.ds365.erp.wms.pda.model.commons.VehicleQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class VehicleService {
	private JsonParser<JsonResult<QueryResult<VehicleModel>>> vehicleParser = new JsonParser<JsonResult<QueryResult<VehicleModel>>>() {
	};

	public void searchPageByParams(VehicleQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<VehicleModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();

		params.setUrl(ConstantUrl.vehicle_vehicle_searchPageByParams);

		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<QueryResult<VehicleModel>>>(vehicleParser, callBack) {
				});
	}
}
