package com.ds365.erp.wms.pda.service.warehouse;

import java.util.List;
import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseModel;
import com.ds365.erp.wms.pda.model.warehouse.WarehouseQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class WarehouseService {

	/**
	 * 获取仓库信息的解析器
	 */
	private JsonParser<JsonResult<List<WarehouseModel>>> warehouseParser = new JsonParser<JsonResult<List<WarehouseModel>>>() {
	};

	/**
	 * 获取仓库
	 */
	public void searchWarehouse(WarehouseQueryParamsModel queryParamsModel, ServiceCallBack<List<WarehouseModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.warehouse_warehouse_searchByParams);

		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<WarehouseModel>>>(warehouseParser, callBack) {
				});
	}
}
