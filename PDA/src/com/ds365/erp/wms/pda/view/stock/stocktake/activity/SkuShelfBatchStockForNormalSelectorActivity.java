package com.ds365.erp.wms.pda.view.stock.stocktake.activity;

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
import com.ds365.erp.wms.pda.service.stock.SkuShelfBatchStockService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.adapter.ShelfBatchStockSelectorAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class SkuShelfBatchStockForNormalSelectorActivity extends BasePdaPageActivity {
	
	private PullToRefreshListView listView;
	private ShelfBatchStockSelectorAdapter adapter;
	private List<SkuShelfBatchStockModel> details = new ArrayList<SkuShelfBatchStockModel>();
	
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	public static final int RESULT_CODE =  PdaConstants.nextResultCode();
	private SkuShelfBatchStockService skuShelfBatchStockService = new SkuShelfBatchStockService();
	private SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParamsModel;
	
	@Override
	protected void initActivityView() {
		listView = (PullToRefreshListView) findViewById(R.id.skuShelfBatchStockList_details);
		adapter = new ShelfBatchStockSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		
		skuShelfBatchStockQueryParamsModel = (SkuShelfBatchStockQueryParamsModel) getIntent().getSerializableExtra(StockTakeByGoodsSkuEditActivity.SER_KEY);
		
		setFirstPage();
		setParams();
		searchPageSkuShelfBatchStock(PdaConstants.CLEAR_LISTVIEW_YES);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuShelfBatchStockList_headerView, R.string.shelf_query);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stocktake_sku_shelf_batch_stock_list;
	}
	
	private void searchPageSkuShelfBatchStock(int type){
		
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		skuShelfBatchStockService.searchPageSkuShelfBatchStockByParams(
				ConstantUrl.skuStock_skuStock_searchPageSkuShelfBatchStockForNormal,skuShelfBatchStockQueryParamsModel,
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
	}
	
	@Override
	protected void setListener() {
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Bundle bundle=new Bundle();
		    	bundle.putSerializable(SER_KEY ,details.get(position-1));  
		    	getIntent().putExtras(bundle);
		    	setResult(RESULT_CODE, getIntent());
				finish();
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
}
