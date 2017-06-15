package com.ds365.erp.wms.pda.view.common.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.stock.SkuBatchModel;
import com.ds365.erp.wms.pda.model.stock.SkuBatchQueryParamsModel;
import com.ds365.erp.wms.pda.service.stock.SkuBatchService;
import com.ds365.erp.wms.pda.view.common.adapter.SkuBatchSelectorAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * 说明 ：根据生产日期选择商品信息 
 */
public class SkuBatchSelectorActivity extends BasePdaPageActivity {

	private List<SkuBatchModel> details = new ArrayList<SkuBatchModel>();
	
	private PullToRefreshListView listView;
	private SkuBatchSelectorAdapter adapter;
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	public  final static String SER_KEY = PdaConstants.nextSerKey();  
	
	private SkuBatchService skuBatchService = new SkuBatchService();
	
	private SkuBatchQueryParamsModel skuBatchQueryParamsModel = new SkuBatchQueryParamsModel();
	
	@Override
	protected void initActivityView() {
		Date produceDate = (Date) getIntent().getExtras().getSerializable("produceDate");
		Long skuId = getIntent().getExtras().getLong("skuId");
		
		skuBatchQueryParamsModel.setProduceDate(produceDate);
		skuBatchQueryParamsModel.setSkuId(skuId);
		
		listView = (PullToRefreshListView) findViewById(R.id.skuBatchSelector_details);
		adapter = new SkuBatchSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		setFirstPage();
		setParams();
		getData(PdaConstants.CLEAR_LISTVIEW_YES);
	}

	private void setParams(){
		skuBatchQueryParamsModel.setStart(start);
		skuBatchQueryParamsModel.setLimit(PdaConstants.LIMIT);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuBatchSelector_headerview, R.string.produce_batch_no_selector);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.common_sku_batch_selector;
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SkuBatchModel data = details.get(position-1);
				Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(SER_KEY,data);  
		        getIntent().putExtras(mBundle);
				setResult(RESULT_CODE, getIntent());
				finish();
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setParams();
				getData(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setParams();
				getData(PdaConstants.CLEAR_LISTVIEW_NO);
			}
		}));
	}

	private void getData(int type) {
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		
		skuBatchService.searchPageSkuBatchByParams(skuBatchQueryParamsModel, new AbstractServiceCallBack<QueryResult<SkuBatchModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<SkuBatchModel> result) {
				if (result.getTotalCount() <= 0) {
					T.showShort(context, "查找不到商品信息");
					finish();
				}else{
					details.addAll(result.getRecords());
					adapter.notifyDataSetInvalidated();
				}
			}
		});
	}
}