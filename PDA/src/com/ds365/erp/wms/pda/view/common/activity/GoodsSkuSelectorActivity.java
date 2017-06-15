package com.ds365.erp.wms.pda.view.common.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.view.common.adapter.GoodsSkuSelectorAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 *  
 */
public class GoodsSkuSelectorActivity extends BasePdaPageActivity {

	private GoodsSkuSelectorAdapter adapter;
	private PullToRefreshListView listView;
	private List<GoodsSkuModel> details = new ArrayList<GoodsSkuModel>();
	
	public static final int RESULT_CODE =  PdaConstants.nextResultCode();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private GoodsSkuQueryParamsModel goodsSkuQueryParamsModel = new GoodsSkuQueryParamsModel();
	@Override
	protected void initActivityView() {
		listView = (PullToRefreshListView) findViewById(R.id.commonsGoodsSkuSelect_listView);
		adapter = new GoodsSkuSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		goodsSkuQueryParamsModel.setFuzzy(getIntent().getStringExtra(PdaConstants.fuzzy));
		goodsSkuQueryParamsModel.setBarcode(getIntent().getStringExtra(PdaConstants.barCode));
		setFirstPage();
		setQueryParams();
		searchPageGoodsSku(PdaConstants.CLEAR_LISTVIEW_YES);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.commonsGoodsSkuSelect_headerView, R.string.goods_sku_select);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.commons_goods_sku_select;
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = getIntent();
				GoodsSkuModel sku=details.get(position-1);
				Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(SER_KEY,sku);  
		        intent.putExtras(mBundle);
				setResult(RESULT_CODE, intent);
				finish();
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setQueryParams();
				searchPageGoodsSku(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setQueryParams();
				searchPageGoodsSku(PdaConstants.CLEAR_LISTVIEW_NO);
			}
		}));
	}
	
	private void setQueryParams(){
		goodsSkuQueryParamsModel.setStart(start);
		goodsSkuQueryParamsModel.setLimit(PdaConstants.LIMIT);
	}
	
	private void searchPageGoodsSku(int type){
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		goodsSkuService.searchPageGoodsSkuByParams(goodsSkuQueryParamsModel, new AbstractServiceCallBack<QueryResult<GoodsSkuModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<GoodsSkuModel> result) {
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
