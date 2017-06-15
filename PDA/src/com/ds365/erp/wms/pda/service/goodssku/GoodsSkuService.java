package com.ds365.erp.wms.pda.service.goodssku;

import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class GoodsSkuService {

	private JsonParser<JsonResult<QueryResult<GoodsSkuModel>>> goodsSkuParser = new JsonParser<JsonResult<QueryResult<GoodsSkuModel>>>() {
	};

	public void searchPageGoodsSkuByParams(GoodsSkuQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<GoodsSkuModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.goods_goodsSku_searchPageGoodsSkuByParams);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<QueryResult<GoodsSkuModel>>>(goodsSkuParser, callBack) {
				});

	}

}
