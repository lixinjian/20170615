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
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuWarehouseStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuWarehouseStockQueryParamsModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.stock.SkuWarehouseStockService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;
import com.ds365.erp.wms.pda.view.query.adapter.SkuWarehouseStockAdapter;
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
 * 仓库库存查询
 *
 */
public class SkuWarehouseStockListActivity extends BasePdaPageActivity {
	private CheckBox checkBox;
	private ImageView barCodeScanButton;
	private TextView queryButton;
	private PullToRefreshListView listView;
	private SkuWarehouseStockAdapter adapter;
	private List<SkuWarehouseStockModel> details = new ArrayList<SkuWarehouseStockModel>();
//	private DropDownListView<Long> warehouseNameValue;
	private DropDownListView<Integer> saleTypeValue;
	private TextView warehouseNameValue;
	private EditText barCodeValue;
	private TextView specValue,unitNameValue,skuNameValue;
	private Long skuId;
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	
	private SkuWarehouseStockService skuWarehouseStockService = new SkuWarehouseStockService();
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private SkuWarehouseStockQueryParamsModel skuWarehouseStockQueryParamsModel = new SkuWarehouseStockQueryParamsModel();
	
	@Override
	protected void initActivityView() {
		checkBox = (CheckBox) findViewById(R.id.skuWarehouseStockList_checkBox_value);
		skuNameValue = (TextView) findViewById(R.id.skuWarehouseStockList_skuName_value);
		barCodeScanButton = (ImageView) findViewById(R.id.skuWarehouseStockList_barCodeScan_button);
		specValue = (TextView) findViewById(R.id.skuWarehouseStockList_spec_value);
		unitNameValue = (TextView) findViewById(R.id.skuWarehouseStockList_unitName_value);
		barCodeValue = (EditText) findViewById(R.id.skuWarehouseStockList_barCode_value);
//		warehouseNameValue = (DropDownListView<Long>) findViewById(R.id.skuWarehouseStockList_warehouseName_value);
		saleTypeValue = (DropDownListView<Integer>) findViewById(R.id.skuWarehouseStockList_saleType_value);
		warehouseNameValue = (TextView) findViewById(R.id.skuWarehouseStockList_warehouseName_value);
		queryButton = (TextView) findViewById(R.id.skuWarehouseStockList_queryButton);
		listView = (PullToRefreshListView) findViewById(R.id.skuWarehouseStockList_details);
		adapter = new SkuWarehouseStockAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		checkBox.setChecked(true);
//		warehouseNameValue.setUrl(ConstantUrl.warehouse_warehouse_searchByParams);
		warehouseNameValue.setText(GlobalUtils.getSessionUser().getWarehouseName());
		saleTypeValue.setUrl(ConstantUrl.common_getsaleTypeEnumsForJsonResult);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuWarehouseStockList_headerView, R.string.warehouse_stock_query);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_warehouse_stock_list;
	}
	
	private void searchPageWarehouseStock(int type){
		
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		skuWarehouseStockService.searchPageWarehouseStockByParams(skuWarehouseStockQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<SkuWarehouseStockModel>>(context) {

					@Override
					public void doSuccess(QueryResult<SkuWarehouseStockModel> result) {
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
		skuWarehouseStockQueryParamsModel.setStart(start);
		skuWarehouseStockQueryParamsModel.setLimit(PdaConstants.LIMIT);
		skuWarehouseStockQueryParamsModel.setWarehouseId(GlobalUtils.getSessionUser().getWarehouseId());
		if(skuId != null)
			skuWarehouseStockQueryParamsModel.setSkuId(skuId);
		if(saleTypeValue.getValue()!=null)
			skuWarehouseStockQueryParamsModel.setSaleTypeId(saleTypeValue.getValue());
		if(checkBox.isChecked()){
			skuWarehouseStockQueryParamsModel.setQtyGt(0);
		}else{
			skuWarehouseStockQueryParamsModel.setQtyGt(null);
		}
		/*if(warehouseNameValue.getValue() != null)
			skuWarehouseStockQueryParamsModel.setWarehouseId(warehouseNameValue.getValue());
		else
			skuWarehouseStockQueryParamsModel.setWarehouseId(null);*/
	}
	
	@Override
	protected void setListener() {
		
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(saleTypeValue.getValue()==null){
					T.showLong(context, "请选择销售类型！");
					return;
				}
				/*if(skuId == null||"".equals(barCodeValue.getText().toString())){
					T.showLong(context, "请扫描sku条码！");
					return;
				}*/
				setFirstPage();
				setParams();
				searchPageWarehouseStock(PdaConstants.CLEAR_LISTVIEW_YES);
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SkuWarehouseStockModel bill=details.get(position-1);
				IntentUtils.startActivityForSeria(context, SkuWarehouseStockShowActivity.class
						, SER_KEY, bill, null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setParams();
				searchPageWarehouseStock(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setParams();
				searchPageWarehouseStock(PdaConstants.CLEAR_LISTVIEW_NO);
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
				IntentUtils.startActivityForResult(SkuWarehouseStockListActivity.this
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
