package com.ds365.erp.wms.pda.service.sale;

import java.util.List;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.sale.SaleOrderBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleOrderDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class SaleOrderService {

	private JsonParser<JsonResult<List<SaleOrderBillModel>>> saleOrderBillParser = new JsonParser<JsonResult<List<SaleOrderBillModel>>>() {
	};
	
	private JsonParser<JsonResult<List<SaleOrderDetailModel>>> saleOrderDetailParser = new JsonParser<JsonResult<List<SaleOrderDetailModel>>>() {
	};

	public void searchSaleOrderBillsByShipmentBillId(Long shipmentBillId,
			ServiceCallBack<List<SaleOrderBillModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchSaleOrderBillsByShipmentBillId);
		params.getParams().put(PdaConstants.shipmentBillId,shipmentBillId);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<SaleOrderBillModel>>>(saleOrderBillParser, callBack) {
				});
	}

	public void searchSaleOrderDetailByBillId(Long billId,
			ServiceCallBack<List<SaleOrderDetailModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchSaleOrderDetailByBillId);
		params.getParams().put(PdaConstants.billId, billId);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<SaleOrderDetailModel>>>(saleOrderDetailParser, callBack) {
		});
	}
	
	public void searchSaleOrderBillByDeliverBillId(Long deliverBillId,
			ServiceCallBack<List<SaleOrderBillModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchSaleOrderBillByDeliverBillId);
		params.getParams().put(PdaConstants.deliverBillId,deliverBillId);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<SaleOrderBillModel>>>(saleOrderBillParser, callBack) {
		});
	}
}
