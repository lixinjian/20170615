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
import com.ds365.erp.wms.pda.model.stock.SkuStoreStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuStoreStockQueryParamsModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.stock.SkuStoreStockService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;
import com.ds365.erp.wms.pda.view.query.adapter.StoreStockSelectorAdapter;
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
/**
 * 库区库存查询
 *
 */
public class SkuStoreStockListActivity extends BasePdaPageActivity {
	private CheckBox checkBox;
	private ImageView barCodeScanButton;
	private TextView queryButton;
	private PullToRefreshListView listView;
	private StoreStockSelectorAdapter adapter;
	private List<SkuStoreStockModel> details = new ArrayList<SkuStoreStockModel>();
	private DropDownListView<Long> storeNameValue;
	private EditText barCodeValue;
	private TextView specValue,unitNameValue,skuNameValue;
	private Long skuId;
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	
	private SkuStoreStockService skuStoreStockService = new SkuStoreStockService();
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private SkuStoreStockQueryParamsModel skuStoreStockQueryParamsModel = new SkuStoreStockQueryParamsModel();
	@Override
	protected void initActivityView() {
		checkBox = (CheckBox) findViewById(R.id.skuStoreStockList_checkBox_value);
		skuNameValue = (TextView) findViewById(R.id.skuStoreStockList_skuName_value);
		barCodeScanButton = (ImageView) findViewById(R.id.skuStoreStockList_barCodeScan_button);
		specValue = (TextView) findViewById(R.id.skuStoreStockList_spec_value);
		unitNameValue = (TextView) findViewById(R.id.skuStoreStockList_unitName_value);
		barCodeValue = (EditText) findViewById(R.id.skuStoreStockList_barCode_value);
		storeNameValue = (DropDownListView<Long>) findViewById(R.id.skuStoreStockList_storeName_value);
		queryButton = (TextView) findViewById(R.id.skuStoreStockList_queryButton);
		listView = (PullToRefreshListView) findViewById(R.id.skuStoreStockList_details);
		adapter = new StoreStockSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		checkBox.setChecked(true);
		storeNameValue.setUrl(ConstantUrl.warehouse_store_searchByParams);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuStoreStockList_headerView, R.string.store_stock_query);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_store_stock_list;
	}
	
	private void searchPageStoreStock(int type){
		
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		skuStoreStockService.searchPageStoreStockByParams(skuStoreStockQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<SkuStoreStockModel>>(context) {

					@Override
					public void doSuccess(QueryResult<SkuStoreStockModel> result) {
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
		skuStoreStockQueryParamsModel.setStart(start);
		skuStoreStockQueryParamsModel.setLimit(PdaConstants.LIMIT);
		if(skuId != null)
			skuStoreStockQueryParamsModel.setSkuId(skuId);
		if(storeNameValue.getValue() != null)
			skuStoreStockQueryParamsModel.setStoreId(storeNameValue.getValue());
		else
			skuStoreStockQueryParamsModel.setStoreId(null);
		if(checkBox.isChecked()){
			skuStoreStockQueryParamsModel.setQtyGt(0);
		}else{
			skuStoreStockQueryParamsModel.setQtyGt(null);
		}
	}
	
	@Override
	protected void setListener() {
		
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setFirstPage();
				setParams();
				searchPageStoreStock(PdaConstants.CLEAR_LISTVIEW_YES);
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SkuStoreStockModel bill=details.get(position-1);
				IntentUtils.startActivityForSeria(context, SkuStoreStockViewActivity.class
						, SER_KEY, bill, null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setParams();
				searchPageStoreStock(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setParams();
				searchPageStoreStock(PdaConstants.CLEAR_LISTVIEW_NO);
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
		
		barCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(SkuStoreStockListActivity.this
						, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == GoodsSkuSelectorActivity.RESULT_CODE) {
				GoodsSkuModel goodsSkuModel = (GoodsSkuModel)intent.getSerializableExtra(GoodsSkuSelectorActivity.SER_KEY);
				setViews(goodsSkuModel);
			}else if (resultCode == CodeScanActivity.RESULT_CODE) {
				barCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}
		}
	}

	private void setViews(GoodsSkuModel goodsSkuModel) {
		specValue.setText(goodsSkuModel.getSpec());
		unitNameValue.setText(goodsSkuModel.getGoodsPack().getName());
		barCodeValue.setText(goodsSkuModel.getBarcode());
		skuNameValue.setText(goodsSkuModel.getName());
		skuId = goodsSkuModel.getId();
	}
}
