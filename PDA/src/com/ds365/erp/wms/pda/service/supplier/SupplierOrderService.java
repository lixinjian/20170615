package com.ds365.erp.wms.pda.service.supplier;

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
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderBillModel;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class SupplierOrderService {

	private JsonParser<JsonResult<QueryResult<SupplierOrderBillModel>>> supplierOrderBillParser = new JsonParser<JsonResult<QueryResult<SupplierOrderBillModel>>>() {
	};
	private JsonParser<JsonResult<List<SupplierOrderDetailModel>>> supplierOrderDetailParser = new JsonParser<JsonResult<List<SupplierOrderDetailModel>>>() {
	};
	/**
	 * 获取采购订单-未收货(供应商)
	 */
	public void searchPageSupplierOrderForDeliverOut(SupplierOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SupplierOrderBillModel>> callBack) {
		
		String url = ConstantUrl.purchase_supplierOrder_searchPageSupplierOrderForDeliverOut;
		searchPageSupplierOrderBill(url, queryParamsModel, callBack);
	}
	
	private void searchPageSupplierOrderBill(String url, SupplierOrderBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<SupplierOrderBillModel>> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(url);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<SupplierOrderBillModel>>>(supplierOrderBillParser, callBack) {
		});
	}
	
	public void searchSupplierOrderDetailsByBillId(Long billId,
			ServiceCallBack<List<SupplierOrderDetailModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		
		params.setUrl(ConstantUrl.purchase_supplierOrder_searchSupplierOrderDetailByBillId);
		
		params.getParams().put(PdaConstants.billId, billId);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<SupplierOrderDetailModel>>>(supplierOrderDetailParser, callBack) {
		});
	}
}
