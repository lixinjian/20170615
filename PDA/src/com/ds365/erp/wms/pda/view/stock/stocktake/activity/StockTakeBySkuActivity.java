package com.ds365.erp.wms.pda.view.stock.stocktake.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderBillModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderDetailModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * 
 * 说明 ：商品盘点
 */
public class StockTakeBySkuActivity extends BasePdaPageActivity {

	private TextView submitButton, queryButton,specValue,unitNameValue;
	private EditText shelfCodeValue, storeNameValue, skuCodeValue;
	
	private JsonParser<JsonResult<QueryResult<StockTakeOrderDetailModel>>> jsonParser;
	
	private List<StockTakeOrderDetailModel> details = new ArrayList<StockTakeOrderDetailModel>();
	private StockTakeOrderBillModel stockTakeOrderBillModel = new StockTakeOrderBillModel();
	public static final String SER_KEY = PdaConstants.nextSerKey();
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private ShelfService shelfService = new ShelfService();

	@Override
	protected void initActivityView() {
		specValue = (TextView) findViewById(R.id.stockTakeBySku_spec_value);
		unitNameValue = (TextView) findViewById(R.id.stockTakeBySku_unitName_value);
		submitButton = (TextView) findViewById(R.id.stockTakeBySku_submit_button);
		queryButton = (TextView) findViewById(R.id.stockTakeBySku_query_button);
		shelfCodeValue = (EditText) findViewById(R.id.stockTakeBySku_shelfCode_value);
		storeNameValue = (EditText) findViewById(R.id.stockTakeBySku_storeName_value);
		skuCodeValue = (EditText) findViewById(R.id.stockTakeBySku_skuCode_value);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.stockTakeBySku_headerview, R.string.shelf_take_stock);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stocktake_stock_take_by_sku;
	}

	@Override
	protected void setListener() {
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShelfModel shelf = stockTakeOrderBillModel.getShelf();
//				if (!StringUtils.isEmptyEditText(shelfCodeValue))
//					params.getParams().put("shelfId", String.valueOf(shelf.getId()));
//				params.getParams().put("storeName", storeNameValue.getText().toString().trim());
//				params.getParams().put("skuCode", skuCodeValue.getText().toString().trim());
//				
//				getData(PdaConstants.CLEAR_LISTVIEW_YES,params);
			}
		});
		
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
						, getString(R.string.dialog_submit_stock_take), true, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
			}
		});
		
		shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					searchShelf(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchShelf(v);
                    return true;
                }
				return false;
			}
		});
		
		skuCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					getGoodsInfo();
				}
				return false;
			}
		});
		
	}
	
	private void searchShelf(TextView v ){
		String shelfCode = v.getText().toString().trim();
		shelfService.getShelfByCode(shelfCode, new AbstractServiceCallBack<ShelfModel>(context) {

			@Override
			public void doSuccess(ShelfModel shelf) {
				if (shelf != null) {
					stockTakeOrderBillModel.setShelf(shelf);
					stockTakeOrderBillModel.setStore(shelf.getStore());
					storeNameValue.setText(shelf.getStore().getName());
					shelfCodeValue.setText(shelf.getCode());
				}else{
					T.showShort(context, "找不到此货位信息");
					shelfCodeValue.setText("");
					shelfCodeValue.requestFocus();
				}
			}
		});
		
	}
	
	private void getData(int type, RequestParamsModel params){
		
		params.setUrl(ConstantUrl.skuStock_skuStock_searchPageSkuShelfBatchStockByParams);
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		jsonParser = new JsonParser<JsonResult<QueryResult<StockTakeOrderDetailModel>>>() {};
		/*RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<StockTakeOrderDetailModel>>>(jsonParser) {

			@Override
			public void doSuccess(JsonResult<QueryResult<StockTakeOrderDetailModel>> result) {
				details.addAll(result.getData().getRecords());
				
			}

			@Override
			public void doFaile(String str) {
				T.showShort(context, str);
			}
		});*/
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == GoodsSkuSelectorActivity.RESULT_CODE) {
				GoodsSkuModel goodsSkuModel = (GoodsSkuModel)intent.getSerializableExtra(GoodsSkuSelectorActivity.SER_KEY);
				specValue.setText(goodsSkuModel.getSpec());
				unitNameValue.setText(goodsSkuModel.getGoodsPack().getName());
				skuCodeValue.setText(goodsSkuModel.getCode());
			}
		}
	}
	
	/**
	 * 获取商品信息
	 */
	private void getGoodsInfo(){
		final String fuzzy = skuCodeValue.getText().toString().trim();
		GoodsSkuQueryParamsModel goodsSkuQueryParamsModel = new GoodsSkuQueryParamsModel();
		goodsSkuQueryParamsModel.setFuzzy(fuzzy);
		goodsSkuService.searchPageGoodsSkuByParams(goodsSkuQueryParamsModel, new AbstractServiceCallBack<QueryResult<GoodsSkuModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<GoodsSkuModel> result) {
				long totalCount = result.getTotalCount();
				if (totalCount == 0) {
					T.showShort(context, "找不到该商品!");
					skuCodeValue.setText("");
					skuCodeValue.requestFocus();
				}else if (totalCount == 1){
					GoodsSkuModel goodsSkuModel = result.getRecords().get(0);
					specValue.setText(goodsSkuModel.getSpec());
					unitNameValue.setText(goodsSkuModel.getGoodsPack().getName());
					
				}else if (totalCount > 1) {
					Intent intent = new Intent(context, GoodsSkuSelectorActivity.class);
					intent.putExtra(PdaConstants.fuzzy, fuzzy);
					startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
				}
			}
		});
	}
}
