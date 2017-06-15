package com.ds365.erp.wms.pda.service.deliver;

import java.util.List;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.shipment.DeliverBillModel;
import com.ds365.erp.wms.pda.model.shipment.DeliverGoodsDetailModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class DeliverService {

	private JsonParser<JsonResult<List<DeliverBillModel>>> deliverBillParser = new JsonParser<JsonResult<List<DeliverBillModel>>>() {
	};
	
	private JsonParser<JsonResult<List<DeliverGoodsDetailModel>>> deliverGoodsDetailModelParser = new JsonParser<JsonResult<List<DeliverGoodsDetailModel>>>() {
	};
	
	public void searchDeliverBillByShipmentBillId(Long shipmentBillId,
			ServiceCallBack<List<DeliverBillModel>> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchDeliverBillByShipmentBillId);
		params.getParams().put(PdaConstants.shipmentBillId,shipmentBillId);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<DeliverBillModel>>>(deliverBillParser, callBack) {
				});
	}
	
	public void searchDeliverGoodsDetailByDeliverBillId(Long deliverBillId,
			ServiceCallBack<List<DeliverGoodsDetailModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchDeliverGoodsDetailByDeliverBillId);
		params.getParams().put(PdaConstants.deliverBillId,deliverBillId);
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<List<DeliverGoodsDetailModel>>>(deliverGoodsDetailModelParser, callBack) {
		});
	}
}
