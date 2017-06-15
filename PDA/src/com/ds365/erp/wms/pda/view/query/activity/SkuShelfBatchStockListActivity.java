package com.ds365.erp.wms.pda.view.query.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DropDownListView;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.service.stock.SkuShelfBatchStockService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;
import com.ds365.erp.wms.pda.view.common.adapter.ShelfBatchStockSelectorAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SkuShelfBatchStockListActivity extends BasePdaPageActivity {
	private CheckBox checkBox;//是否过滤0库存
	private ImageView shelfCodeScanButton, barCodeScanButton;
	private TextView queryButton;
	private EditText barCodeValue,shelfCodeValue;
	private PullToRefreshListView listView;
	private ShelfBatchStockSelectorAdapter adapter;
	private SkuShelfBatchStockModel skuShelfBatchStockModel = new SkuShelfBatchStockModel();
	private List<SkuShelfBatchStockModel> details = new ArrayList<SkuShelfBatchStockModel>();
	private DropDownListView<Long> storeNameValue;
	private TextView specValue,unitNameValue,skuNameValue;
	private Long skuId,shelfId;
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	
	private static final int requestCodeForShelfCode = 1;
	private static final int requestCodeForBarCode = 2;
	
	private SkuShelfBatchStockService skuShelfBatchStockService = new SkuShelfBatchStockService();
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private ShelfService shelfService = new ShelfService();
	private SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParamsModel = new SkuShelfBatchStockQueryParamsModel();
	
	@Override
	protected void initActivityView() {
		checkBox = (CheckBox) findViewById(R.id.skuShelfBatchStockList_checkBox);
		skuNameValue = (TextView) findViewById(R.id.skuShelfBatchStockList_skuName_value);
		shelfCodeScanButton = (ImageView) findViewById(R.id.skuShelfBatchStockList_shelfCodeScan_button);
		barCodeScanButton = (ImageView) findViewById(R.id.skuShelfBatchStockList_barCodeScan_button);
		specValue = (TextView) findViewById(R.id.skuShelfBatchStockList_spec_value);
		unitNameValue = (TextView) findViewById(R.id.skuShelfBatchStockList_unitName_value);
		barCodeValue = (EditText) findViewById(R.id.skuShelfBatchStockList_barCode_value);
		storeNameValue = (DropDownListView<Long>) findViewById(R.id.skuShelfBatchStockList_storeName_value);
		shelfCodeValue = (EditText) findViewById(R.id.skuShelfBatchStockList_shelfCode_value);
		queryButton = (TextView) findViewById(R.id.skuShelfBatchStockList_queryButton);
		listView = (PullToRefreshListView) findViewById(R.id.skuShelfBatchStockList_details);
		//设置默认为过滤0库存
		checkBox.setChecked(true);
		adapter = new ShelfBatchStockSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		storeNameValue.setUrl(ConstantUrl.warehouse_store_searchByParams);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuShelfBatchStockList_headerView, R.string.shelf_query);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_shelf_batch_stock_list;
	}
	
	private void searchPageSkuShelfBatchStock(int type){
		
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		skuShelfBatchStockService.searchPageSkuShelfBatchStockByParams(
				ConstantUrl.skuStock_skuStock_searchPageSkuShelfBatchStockByParams,skuShelfBatchStockQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<SkuShelfBatchStockModel>>(context) {

					@Override
					public void doSuccess(QueryResult<SkuShelfBatchStockModel> result) {
						if (result != null && result.getRecords().size() > 0) {
							details.addAll(result.getRecords());
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						adapter.notifyDataSetChanged();
					}
				});
	}

	private void setParams(){
		skuShelfBatchStockQueryParamsModel.setStart(start);
		skuShelfBatchStockQueryParamsModel.setLimit(PdaConstants.LIMIT);
		if(shelfId != null){
			skuShelfBatchStockQueryParamsModel.setShelfId(shelfId);
		}
		if(skuId != null)
			skuShelfBatchStockQueryParamsModel.setSkuId(skuId);
		if(storeNameValue.getValue() != null)
			skuShelfBatchStockQueryParamsModel.setStoreId(storeNameValue.getValue());
		if(checkBox.isChecked()){
			skuShelfBatchStockQueryParamsModel.setQtyGt(0);
		}else{
			skuShelfBatchStockQueryParamsModel.setQtyGt(null);
		}
	}
	
	@Override
	protected void setListener() {
		
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
		
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setFirstPage();
				setParams();
				searchPageSkuShelfBatchStock(PdaConstants.CLEAR_LISTVIEW_YES);
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SkuShelfBatchStockModel bill=details.get(position-1);
				IntentUtils.startActivityForSeria(context, SkuShelfBatchStockShowActivity.class
						, SER_KEY, bill, null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setParams();
				searchPageSkuShelfBatchStock(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setParams();
				searchPageSkuShelfBatchStock(PdaConstants.CLEAR_LISTVIEW_NO);
			}
		}));
		
		barCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					searchGoodsInfo(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchGoodsInfo(v);
                    return true;
                }
				return false;
			}
		});
		
		shelfCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(SkuShelfBatchStockListActivity.this
						, CodeScanActivity.class, requestCodeForShelfCode);
			}
		});
		
		barCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(SkuShelfBatchStockListActivity.this
						, CodeScanActivity.class, requestCodeForBarCode);
			}
		});
		
	}
	
	private void searchShelf(TextView v){
		
		String shelfCode = v.getText().toString().trim();
		
		shelfService.getShelfByCode(shelfCode, new AbstractServiceCallBack<ShelfModel>(context) {

			@Override
			public void doSuccess(ShelfModel shelf) {
				if (shelf != null) {
					skuShelfBatchStockModel.setShelf(shelf);
					skuShelfBatchStockModel.setStore(shelf.getStore());
//					storeNameValue.setValue(shelf.getStore().getId());
					shelfCodeValue.setText(shelf.getCode());
					shelfId = shelf.getId();
				}else{
					T.showShort(context, "查找不到此货位信息");
				}
			}
		});
		
	}
	
	/**
	 * 获取商品信息
	 */
	private void searchGoodsInfo(TextView v){
		final String fuzzy = v.getText().toString().trim();
		
		GoodsSkuQueryParamsModel goodsSkuQueryParamsModel = new GoodsSkuQueryParamsModel();
		goodsSkuQueryParamsModel.setFuzzy(fuzzy);
		
		goodsSkuService.searchPageGoodsSkuByParams(goodsSkuQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<GoodsSkuModel>>(context) {

					@Override
					public void doSuccess(QueryResult<GoodsSkuModel> result) {
						long totalCount = result.getTotalCount();
						if (totalCount == 0) {
							T.showShort(context, "找不到该商品!");
							barCodeValue.setText("");
							barCodeValue.requestFocus();
						} else if (totalCount == 1) {
							GoodsSkuModel goodsSkuModel = result.getRecords().get(0);
							setViews(goodsSkuModel);

						} else if (totalCount > 1) {
							Intent intent = new Intent(context, GoodsSkuSelectorActivity.class);
							intent.putExtra(PdaConstants.fuzzy, fuzzy);
							startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
						}
					}
				});
	}
	
	private void setViews(GoodsSkuModel goodsSkuModel) {
		specValue.setText(goodsSkuModel.getSpec());
		unitNameValue.setText(goodsSkuModel.getGoodsPack().getName());
		barCodeValue.setText(goodsSkuModel.getBarcode());
		skuNameValue.setText(goodsSkuModel.getName());
		skuId = goodsSkuModel.getId();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == GoodsSkuSelectorActivity.RESULT_CODE) {
				GoodsSkuModel goodsSkuModel = (GoodsSkuModel)intent.getSerializableExtra(GoodsSkuSelectorActivity.SER_KEY);
				setViews(goodsSkuModel);
			}else if(requestCode == requestCodeForBarCode && resultCode == CodeScanActivity.RESULT_CODE){
				barCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}else if(requestCode == requestCodeForShelfCode && resultCode == CodeScanActivity.RESULT_CODE){
				shelfCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
}
