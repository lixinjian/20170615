package com.ds365.erp.wms.pda.view.stock.stocktake.activity;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderDetailModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.stock.SkuShelfBatchStockService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;
import com.ds365.erp.wms.pda.view.common.activity.SkuShelfBatchStockSelectorActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class StockTakeByGoodsSkuEditActivity extends BasePdaActivity {

	private TextView saveButton;
	private ImageView shelfCodeScanButton,barCodeScanButton;
	private EditText shelfCodeValue,barCodeValue;
	private TextView storeNameValue,skuNameValue,specValue,specQtyValue,expectUnitQty,expectMinUnitQty,unitNameValue;
	private IntEditField unitQtyValue,minUnitQtyValue;
	private DateField produceDateValue;
	
	private SkuShelfBatchStockService skuShelfBatchStockService = new SkuShelfBatchStockService();
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	public static final String SER_KEY = PdaConstants.nextSerKey();
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	private static final int requestCodeForBarCodeScan = Integer.parseInt(PdaConstants.nextRequestCode());
	private static final int requestCodeForShelfCodeScan = Integer.parseInt(PdaConstants.nextRequestCode());
	private StockTakeOrderDetailModel stockTakeOrderDetail;
	
	@Override
	protected void initActivityView() {
		
		saveButton = (TextView) findViewById(R.id.stockTakeByGoodsSkuEdit_save_button);
		shelfCodeScanButton = (ImageView) findViewById(R.id.stockTakeByGoodsSkuEdit_shelfCodeScan_button);
		barCodeScanButton = (ImageView) findViewById(R.id.stockTakeByGoodsSkuEdit_barCodeScan_button);
		shelfCodeValue = (EditText) findViewById(R.id.stockTakeByGoodsSkuEdit_shelfCode_value);
		barCodeValue = (EditText) findViewById(R.id.stockTakeByGoodsSkuEdit_barCode_value);
		storeNameValue = (TextView) findViewById(R.id.stockTakeByGoodsSkuEdit_storeName_value);
		skuNameValue = (TextView) findViewById(R.id.stockTakeByGoodsSkuEdit_skuName_value);
		specValue = (TextView) findViewById(R.id.stockTakeByGoodsSkuEdit_spec_value);
		unitNameValue = (TextView) findViewById(R.id.stockTakeByGoodsSkuEdit_unitName_value);
		specQtyValue = (TextView) findViewById(R.id.stockTakeByGoodsSkuEdit_specQty_value);
		expectUnitQty = (TextView) findViewById(R.id.stockTakeByGoodsSkuEdit_expectUnitQty_value);
		expectMinUnitQty = (TextView) findViewById(R.id.stockTakeByGoodsSkuEdit_expectMinUnitQty_value);
		unitQtyValue = (IntEditField) findViewById(R.id.stockTakeByGoodsSkuEdit_unitQtyValue_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.stockTakeByGoodsSkuEdit_minUnitQtyValue_value);
		produceDateValue = (DateField) findViewById(R.id.stockTakeByGoodsSkuEdit_produceDate_value);
		stockTakeOrderDetail = (StockTakeOrderDetailModel) getIntent().getSerializableExtra(StockTakeByGoodsSkuActivity.SER_KEY);
		if (stockTakeOrderDetail == null) {
			stockTakeOrderDetail = new StockTakeOrderDetailModel();
		}else{
			shelfCodeValue.setText(stockTakeOrderDetail.getShelf().getCode()); 
			storeNameValue.setText(stockTakeOrderDetail.getStore().getName());
			barCodeValue.setText(stockTakeOrderDetail.getSku().getBarcode());
			skuNameValue.setText(stockTakeOrderDetail.getSku().getName());
			unitNameValue.setText(stockTakeOrderDetail.getUnitName());
			specValue.setText(stockTakeOrderDetail.getSpec());
			specQtyValue.setText(String.valueOf(stockTakeOrderDetail.getSpecQty()));
			expectUnitQty.setText(String.valueOf(stockTakeOrderDetail.getExpectUnitQty()));
			expectMinUnitQty.setText(String.valueOf(stockTakeOrderDetail.getExpectMinUnitQty()));
			unitQtyValue.setValue(stockTakeOrderDetail.getUnitQty());
			minUnitQtyValue.setValue(stockTakeOrderDetail.getMinUnitQty());
			produceDateValue.setValue(stockTakeOrderDetail.getProduceDate());
			minUnitQtyValue.setMaxValueAndTextChangeListener(stockTakeOrderDetail.getSpecQty(),"散数不能大于包装数量！",null);
		}
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.stockTakeByGoodsSkuEdit_headerview, R.string.take_stock_by_goods_sku);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stocktake_stock_take_by_goods_sku_edit;
	}

	@Override
	protected void setListener() {
		shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					searchSkuShelfBatchStock(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchSkuShelfBatchStock(v);
                    return true;
                }
				return false;
			}
		});
		
		barCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					getGoodsInfo(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    getGoodsInfo(v);
                    return true;
                }
				return false;
			}
		});
		
		shelfCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(StockTakeByGoodsSkuEditActivity.this
						, CodeScanActivity.class, requestCodeForShelfCodeScan);
			}
		});
		
		barCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(StockTakeByGoodsSkuEditActivity.this
						, CodeScanActivity.class, requestCodeForBarCodeScan);
			}
		});
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				StringBuilder message = new StringBuilder();
				if(stockTakeOrderDetail.getSku() == null){
					message.append("请输入sku条码并带出商品信息！\r\n");
				}
				if(stockTakeOrderDetail.getShelf() == null){
					message.append("请输入货位号并带出库区！\r\n");
				}
				if(StringUtils.isEmptyEditText(unitQtyValue.getEditText())){
					message.append("请输入实盘件数！\r\n");
				}
				if(StringUtils.isEmptyEditText(minUnitQtyValue.getEditText())){
					message.append("请输入实盘散数！\r\n");
				}
				if(message.length()!=0){
					T.showShort(context, message.toString());
					return;
				}
				stockTakeOrderDetail.setUnitQty(unitQtyValue.getValue());
				stockTakeOrderDetail.setMinUnitQty(minUnitQtyValue.getValue());
				
				Bundle bundle=new Bundle();
		    	bundle.putSerializable(SER_KEY ,stockTakeOrderDetail);  
		    	getIntent().putExtras(bundle);
		    	setResult(RESULT_CODE, getIntent());
				finish();
			}
		});
	}
	
	private void searchSkuShelfBatchStock(TextView v ){
		final String shelfCode = v.getText().toString().trim();
		/*shelfService.getShelfByCode(shelfCode, new AbstractServiceCallBack<ShelfModel>(context) {

			@Override
			public void doSuccess(ShelfModel shelf) {
				if (shelf != null) {
					stockTakeOrderDetail.setShelf(shelf);
					stockTakeOrderDetail.setStore(shelf.getStore());
					storeNameValue.setText(shelf.getStore().getName());
					shelfCodeValue.setText(shelf.getCode());
				}else{
					T.showShort(context, "找不到此货位信息");
					shelfCodeValue.requestFocus();
				}
			}
		});*/
		if(stockTakeOrderDetail.getSku() == null||"".equals(barCodeValue.getText().toString())){
			T.showShort(context, "请扫描sku条码获取商品信息！");
			return;
		}
		SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParams = new SkuShelfBatchStockQueryParamsModel();
		skuShelfBatchStockQueryParams.setSkuId(stockTakeOrderDetail.getSku().getId());
		skuShelfBatchStockQueryParams.setShelfCode(shelfCode);
		
		final String url = ConstantUrl.skuStock_skuStock_searchPageSkuShelfBatchStockForNormal;
		skuShelfBatchStockService.searchPageSkuShelfBatchStockByParams(url
				,skuShelfBatchStockQueryParams, new AbstractServiceCallBack<QueryResult<SkuShelfBatchStockModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<SkuShelfBatchStockModel> result) {
				long totalCount = result.getTotalCount();
				if (totalCount == 0) {
					T.showShort(context, "找不到对应的货位!");
					shelfCodeValue.setText("");
					shelfCodeValue.requestFocus();
				}else if (totalCount == 1){
					SkuShelfBatchStockModel skuShelfBatchStock = result.getRecords().get(0);
					setSkuShelfBatchStockViews(skuShelfBatchStock);
				}else if (totalCount > 1) {
					Intent intent=new Intent(context, SkuShelfBatchStockSelectorActivity.class);
					intent.putExtra("shelfCode", shelfCode);
					intent.putExtra("skuId",stockTakeOrderDetail.getSku().getId());
					intent.putExtra(SkuShelfBatchStockSelectorActivity.SELECTOR_URL_KEY,url); 
					startActivityForResult(intent,PdaConstants.PDA_REQUEST_CODE);
				}
			}
		});
	}
	
	/**
	 * 获取商品信息
	 */
	private void getGoodsInfo(TextView v){
		
		final String fuzzy = v.getText().toString().trim();
		GoodsSkuQueryParamsModel goodsSkuQueryParamsModel = new GoodsSkuQueryParamsModel();
		goodsSkuQueryParamsModel.setFuzzy(fuzzy);
		goodsSkuService.searchPageGoodsSkuByParams(goodsSkuQueryParamsModel, new AbstractServiceCallBack<QueryResult<GoodsSkuModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<GoodsSkuModel> result) {
				long totalCount = result.getTotalCount();
				if (totalCount == 0) {
					T.showShort(context, "找不到该商品!");
					barCodeValue.setText("");
					barCodeValue.requestFocus();
				}else if (totalCount == 1){
					GoodsSkuModel goodsSku=result.getRecords().get(0);
					setGoodsSkuValue(goodsSku);
				}else if (totalCount > 1) {
					//跳转
					Intent intent = new Intent(context, GoodsSkuSelectorActivity.class);
					intent.putExtra(PdaConstants.fuzzy, fuzzy);
					startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
				}
			}
		});
	}

	private void setGoodsSkuValue(GoodsSkuModel goodsSku) {
		skuNameValue.setText(goodsSku.getName());
		unitNameValue.setText(goodsSku.getGoodsPack().getUnitName());
		specValue.setText(goodsSku.getSpec());
		specQtyValue.setText(String.valueOf(goodsSku.getGoodsPack().getSpecQty()));
		barCodeValue.setText(goodsSku.getBarcode());
		stockTakeOrderDetail.setSku(goodsSku);
		stockTakeOrderDetail.setUnitName(goodsSku.getGoodsPack().getUnitName());
		stockTakeOrderDetail.setSpecQty(goodsSku.getGoodsPack().getSpecQty());
		stockTakeOrderDetail.setPack(goodsSku.getGoodsPack());
		stockTakeOrderDetail.setSpec(goodsSku.getSpec()); 
	}
	
	private void setSkuShelfBatchStockViews(SkuShelfBatchStockModel skuShelfBatchStock) {
		stockTakeOrderDetail.setUnitQty(skuShelfBatchStock.getUnitQty());
		stockTakeOrderDetail.setMinUnitQty(skuShelfBatchStock.getMinUnitQty());
		stockTakeOrderDetail.setSysBatchNo(skuShelfBatchStock.getSysBatchNo());
		stockTakeOrderDetail.setProduceDate(skuShelfBatchStock.getProduceDate());
		stockTakeOrderDetail.setExpectUnitQty(skuShelfBatchStock.getUnitQty());
		stockTakeOrderDetail.setExpectMinUnitQty(skuShelfBatchStock.getMinUnitQty());
		stockTakeOrderDetail.setShelf(skuShelfBatchStock.getShelf());
		stockTakeOrderDetail.setSaleType(skuShelfBatchStock.getSaleType());
		stockTakeOrderDetail.setSysBatchNo(skuShelfBatchStock.getSysBatchNo());
		stockTakeOrderDetail.setStore(skuShelfBatchStock.getShelf().getStore());
		minUnitQtyValue.setMaxValueAndTextChangeListener(skuShelfBatchStock.getSku().getGoodsPack().getSpecQty(),"散数不能大于包装数量！",null);
		expectUnitQty.setText(String.valueOf(skuShelfBatchStock.getUnitQty()));
		expectMinUnitQty.setText(String.valueOf(skuShelfBatchStock.getMinUnitQty()));
		produceDateValue.setValue(skuShelfBatchStock.getProduceDate());
		storeNameValue.setText(skuShelfBatchStock.getShelf().getStore().getName());
		shelfCodeValue.setText(skuShelfBatchStock.getShelf().getCode());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == SkuShelfBatchStockSelectorActivity.RESULT_CODE) {
				SkuShelfBatchStockModel skuShelfBatchStock = (SkuShelfBatchStockModel)intent.getSerializableExtra(SkuShelfBatchStockSelectorActivity.SER_KEY);
				setSkuShelfBatchStockViews(skuShelfBatchStock);
			} else if(resultCode == GoodsSkuSelectorActivity.RESULT_CODE) {
				GoodsSkuModel goodsSku = (GoodsSkuModel)intent.getSerializableExtra(GoodsSkuSelectorActivity.SER_KEY);
				setGoodsSkuValue(goodsSku);
			}else if (requestCode == requestCodeForShelfCodeScan && resultCode == CodeScanActivity.RESULT_CODE) {
				shelfCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}else if(requestCode == requestCodeForBarCodeScan && resultCode == CodeScanActivity.RESULT_CODE){
				barCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}
		}
		
	}
}
