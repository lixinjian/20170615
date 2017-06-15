package com.ds365.erp.wms.pda.service.sale;

import java.util.List;
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
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.sale.SaleOutBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class SaleReturnEnterService extends AbstractBaseService{

	private JsonParser<JsonResult<List<SaleReturnEnterDetailModel>>> saleReturnEnterDetailParser = new JsonParser<JsonResult<List<SaleReturnEnterDetailModel>>>() {
	};

	private JsonParser<JsonResult<QueryResult<SaleReturnEnterBillModel>>> saleReturnEnterParser = new JsonParser<JsonResult<QueryResult<SaleReturnEnterBillModel>>>() {
	};

	/**
	 * 查询销售退货入库单详情
	 */
	public void searchSaleReturnEnterDetailsByBillId(Long billId,
			ServiceCallBack<List<SaleReturnEnterDetailModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchSaleOutDetailsByBillId);
		params.getParams().put(PdaConstants.billId, billId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<SaleReturnEnterDetailModel>>>(saleReturnEnterDetailParser, callBack) {
		});
	}

	/**
	 * 查询销售退货入库单
	 */
	public void searchPageSaleReturnEnterBillsByParams(SaleOutBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SaleReturnEnterBillModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.sale_saleOut_searchPageSaleOutBillsByParams);

		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<SaleReturnEnterBillModel>>>(saleReturnEnterParser, callBack) {
		});
	}
	
	public void createSaleReurnOutBill(SaleReturnEnterBillCreateParamsModel createParams,
			ServiceCallBack<String> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setUrl(ConstantUrl.sale_saleOut_createSaleReurnOutBill);
		params.setJsonBody(submitJsonResult);
		
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}
	
	
}
