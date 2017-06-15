package com.ds365.erp.wms.pda.service.shipment;

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
import com.ds365.erp.wms.pda.model.shipment.ShipmentBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentGoodsDetailModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentOrderBillModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentOutBillCreateParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class ShipmentService extends AbstractBaseService{

	private JsonParser<JsonResult<QueryResult<ShipmentOrderBillModel>>> shipmentOrderParser = new JsonParser<JsonResult<QueryResult<ShipmentOrderBillModel>>>() {
	};

	private JsonParser<JsonResult<List<ShipmentGoodsDetailModel>>> shipmentGoodsDetailParser = new JsonParser<JsonResult<List<ShipmentGoodsDetailModel>>>() {
	};
	
	/**
	 * 分页查询装运单-未出库
	 */
	public void searchPageShipmentBillForNotOut(ShipmentBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<ShipmentOrderBillModel>> callBack) {

		String url = ConstantUrl.sale_saleOut_searchPageShipmentBillForNotOut;
		searchPageShipmentBill(url, queryParamsModel, callBack);
	}
	/**
	 * 分页查询装运单-已出库
	 */
	public void searchPageShipmentBillForOut(ShipmentBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<ShipmentOrderBillModel>> callBack) {
		
		String url = ConstantUrl.sale_saleOut_searchPageShipmentBillForOut;
		searchPageShipmentBill(url, queryParamsModel, callBack);
	}
	
	/**
	 * 分页查询装运单列表
	 */
	public void searchPageShipmentBillByParams(ShipmentBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<ShipmentOrderBillModel>> callBack) {
		
		String url = ConstantUrl.sale_saleOut_searchPageShipmentBillByParams;
		searchPageShipmentBill(url, queryParamsModel, callBack);
	}

	private void searchPageShipmentBill(String url, ShipmentBillQueryParamsModel queryParamsModel,
			ServiceCallBack<QueryResult<ShipmentOrderBillModel>> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(url);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<ShipmentOrderBillModel>>>(
				shipmentOrderParser, callBack) {
		});
	}
	
	public void searchShipmentGoodsDetailByBillId(Long billId,
			ServiceCallBack<List<ShipmentGoodsDetailModel>> callBack) {
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.sale_saleOut_searchShipmentGoodsDetailByBillId);
		params.getParams().put(PdaConstants.billId,billId);
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<ShipmentGoodsDetailModel>>>(
				shipmentGoodsDetailParser, callBack) {
		});
	}

	public void createShipmentOutBill(ShipmentOutBillCreateParamsModel createParams,
			ServiceCallBack<String> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setUrl(ConstantUrl.sale_saleOut_createShipmentOutBill);
		params.setJsonBody(submitJsonResult);
		
		RequestUtil.requestJson(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}
}
