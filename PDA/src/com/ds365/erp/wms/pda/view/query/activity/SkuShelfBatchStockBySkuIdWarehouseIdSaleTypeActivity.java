package com.ds365.erp.wms.pda.view.query.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuWarehouseStockModel;
import com.ds365.erp.wms.pda.service.stock.SkuShelfBatchStockService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.adapter.ShelfBatchStockSelectorAdapter;
import com.ds365.erp.wms.pda.view.query.adapter.SkuWarehouseStockAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SkuShelfBatchStockBySkuIdWarehouseIdSaleTypeActivity extends BasePdaPageActivity {

	private PullToRefreshListView listView;
	
	private ShelfBatchStockSelectorAdapter adapter;
	private CheckBox checkBox;//是否过滤0库存
	private List<SkuShelfBatchStockModel> details = new ArrayList<SkuShelfBatchStockModel>();
	private SkuShelfBatchStockService skuShelfBatchStockService = new SkuShelfBatchStockService();
	private SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParamsModel = new SkuShelfBatchStockQueryParamsModel();
	private SkuWarehouseStockModel skuWarehouseStock;
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	public static final String REQUEST_CODE_KEY = SkuShelfBatchStockBySkuIdWarehouseIdSaleTypeActivity.class.getName();
	@Override
	protected void initActivityView() {
		
		skuWarehouseStock = (SkuWarehouseStockModel) getIntent()
				.getSerializableExtra(SkuWarehouseStockAdapter.SER_KEY);
		
		listView = (PullToRefreshListView) findViewById(R.id.skuShelfBatchStockBySkuIdWarehouseIdSaleType_details);
		checkBox = (CheckBox) findViewById(R.id.skuShelfBatchStockBySkuIdWarehouseIdSaleType_checkBox_value);
		checkBox.setChecked(true);
		adapter = new ShelfBatchStockSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		

		setFirstPage();
		setParams();
		searchPageSkuShelfBatchStock(PdaConstants.CLEAR_LISTVIEW_YES);
	}

	private void setParams() {
		skuShelfBatchStockQueryParamsModel.setStart(start);
		skuShelfBatchStockQueryParamsModel.setLimit(PdaConstants.LIMIT);
		skuShelfBatchStockQueryParamsModel.setSkuId(skuWarehouseStock.getSku().getId());
		skuShelfBatchStockQueryParamsModel.setWarehouseId(skuWarehouseStock.getWarehouse().getId());
		skuShelfBatchStockQueryParamsModel.setSaleTypeId(skuWarehouseStock.getSaleType().getId());
		if(checkBox.isChecked()){
			skuShelfBatchStockQueryParamsModel.setQtyGt(0);
		}else{
			skuShelfBatchStockQueryParamsModel.setQtyGt(null);
		}
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuShelfBatchStockBySkuIdWarehouseIdSaleType_headerView, R.string.shelf_query);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_shelf_batch_stock_by_sku_id_warehouse_id_sale_type;
	}

	@Override
	protected void setListener() {
		
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				setFirstPage();
				setParams();
				searchPageSkuShelfBatchStock(PdaConstants.CLEAR_LISTVIEW_YES);
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SkuShelfBatchStockModel skuShelfBatchStock=details.get(position-1);
				Intent intent = new Intent(context,SkuShelfBatchStockShowActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(SER_KEY,skuShelfBatchStock);  
				intent.putExtras(bundle);
				intent.putExtra(REQUEST_CODE_KEY, REQUEST_CODE);
				startActivity(intent);
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
}
