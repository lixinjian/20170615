package com.ds365.erp.wms.pda.service.shelf;

import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.stock.ShelfQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class ShelfService {

	private JsonParser<JsonResult<ShelfModel>> shelfParser = new JsonParser<JsonResult<ShelfModel>>() {
	};

	private JsonParser<JsonResult<QueryResult<ShelfModel>>> serchPageByShelfParser = new JsonParser<JsonResult<QueryResult<ShelfModel>>>() {
	};

	/**
	 * 获取入库暂存区货位单条记录
	 */
	public void getShelfForPurchaseEnter(ServiceCallBack<ShelfModel> callBack) {

		String url = ConstantUrl.warehouse_shelf_getForPurchaseEnter;
		getShelfByParams(url, callBack);
	}
	
	/**
	 * 获取销售退货暂存区货位
	 */
	public void getShelfForSaleReturn(ServiceCallBack<ShelfModel> callBack) {
		
		String url = ConstantUrl.warehouse_shelf_getForSaleReturn;
		getShelfByParams(url, callBack);
	}
	
	private void getShelfByParams(String url, ServiceCallBack<ShelfModel> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(url);
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<ShelfModel>>(shelfParser, callBack) {
		});
	}

	/**
	 * 分页查询货位信息
	 */
	public void searchPageByParams(ShelfQueryParamsModel queryParamsModel, ServiceCallBack<QueryResult<ShelfModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.warehouse_shelf_searchPageByParams);

		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<QueryResult<ShelfModel>>>(serchPageByShelfParser, callBack) {
				});
	}
	
	public void getShelfByCode(String shelfCode, ServiceCallBack<ShelfModel> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		
		params.getParams().put(PdaConstants.shelfCode, shelfCode);
		params.setUrl(ConstantUrl.warehouse_shelf_getShelfByCode);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<ShelfModel>>(shelfParser, callBack) {
		});
		
	}
	
	/**
	 * 验证同一个货位上相同sku，不同系统批次有没有混放
	 * 
	 * 传递的参数：shelfCode，SkuId,,excludeSysBatchNo【warehouseId从session中获取】
	 * 
	 * 注意该方法随时有可能会返回：【success：false,message:""】，前台一定要进行处理
	 */
	public void getShelfByCodeAndVal(SkuShelfBatchStockQueryParamsModel queryParamsModel, ServiceCallBack<ShelfModel> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		
		Map map  = (Map) BeanUtils.beanToMap(queryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.skuStock_skuStock_getShelfByCodeAndVal);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<ShelfModel>>(shelfParser, callBack) {
		});
		
	}
}
