package com.ds365.erp.wms.pda.service.sale;

import java.util.List;
import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.sale.SaleOutBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleOutBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleOutDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class SaleOutService {

	private JsonParser<JsonResult<QueryResult<SaleOutBillModel>>> saleOutBillParser = new JsonParser<JsonResult<QueryResult<SaleOutBillModel>>>() {
	};

	private JsonParser<JsonResult<List<SaleOutDetailModel>>> saleOutDetailParser = new JsonParser<JsonResult<List<SaleOutDetailModel>>>() {
	};

	public void searchPageSaleOutBillsByParams(SaleOutBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SaleOutBillModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.sale_saleOut_searchPageSaleOutBillsByParams);

		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<QueryResult<SaleOutBillModel>>>(saleOutBillParser, callBack) {
				});
	}

	public void searchSaleOutDetailsByBillId(Long billId,
			ServiceCallBack<List<SaleOutDetailModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchSaleOutDetailsByBillId);
		params.getParams().put(PdaConstants.billId,billId); 
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<SaleOutDetailModel>>>(saleOutDetailParser, callBack) {
				});

	}

}
