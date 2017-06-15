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
import com.ds365.erp.wms.pda.model.sale.SaleOrderBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class SaleReturnOrderService {

	private JsonParser<JsonResult<QueryResult<SaleReturnOrderBillModel>>> saleReturnOrderParser = new JsonParser<JsonResult<QueryResult<SaleReturnOrderBillModel>>>() {
	};

	private JsonParser<JsonResult<List<SaleReturnOrderDetailModel>>> saleReturnOrderDetailParser = new JsonParser<JsonResult<List<SaleReturnOrderDetailModel>>>() {
	};

	/**
	 * 查询销售退货单- 未退货
	 */
	public void searchPageSaleReturnOrderBillsForNotReturn(SaleOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SaleReturnOrderBillModel>> callBack) {

		String url = ConstantUrl.sale_saleOut_searchPageSaleReturnOrderBillsForNotReturn;
		searchPageSaleOrderReturnBills(url, queryParamsModel, callBack);
	}
	/**
	 * 查询销售退货单- 已退货
	 */
	public void searchPageSaleReturnOrderBillsForReturn(SaleOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SaleReturnOrderBillModel>> callBack) {
		
		String url = ConstantUrl.sale_saleOut_searchPageSaleReturnOrderBillsForReturn;
		searchPageSaleOrderReturnBills(url, queryParamsModel, callBack);
	}
	
	private void searchPageSaleOrderReturnBills(String url, SaleOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SaleReturnOrderBillModel>> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(url);

		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<SaleReturnOrderBillModel>>>(saleReturnOrderParser, callBack) {
		});
		
	}

	/**
	 * 查询销售退货单明细
	 */
	public void searchSaleOrderDetailByBillId(Long billId,
			ServiceCallBack<List<SaleReturnOrderDetailModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchSaleOrderDetailByBillId);
		params.getParams().put(PdaConstants.billId, billId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<SaleReturnOrderDetailModel>>>(saleReturnOrderDetailParser, callBack) {
		});
	}

}
