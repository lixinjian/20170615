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
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.service.stock.SkuShelfBatchStockService;
import com.ds365.erp.wms.pda.view.common.adapter.ShelfBatchStockSelectorAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 货位批次库存选择的acitivity
 * @author cgb2016
 *
 */
public class SkuShelfBatchStockSelectorActivity extends BasePdaPageActivity{
	
	public  final static String SER_KEY = PdaConstants.nextSerKey();  
    //public  final static String PAR_KEY = "com.tutor.objecttran.par"; 
	private ShelfBatchStockSelectorAdapter adapter;
	private PullToRefreshListView listView;
	private List<SkuShelfBatchStockModel> list = new ArrayList<SkuShelfBatchStockModel>();
	
	public static final int RESULT_CODE =  PdaConstants.nextResultCode();
	
	private SkuShelfBatchStockService skuShelfBatchStockService = new SkuShelfBatchStockService();
	private SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParamsModel = new SkuShelfBatchStockQueryParamsModel();
	
	public static final String SELECTOR_URL_KEY = SkuShelfBatchStockSelectorActivity.class.getName();
	private String url;
	
	@Override
	protected void initActivityView() {
		listView = (PullToRefreshListView) findViewById(R.id.commonsGoodsSkuSelect_listView);
		
		adapter = new ShelfBatchStockSelectorAdapter(context, list);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		Bundle  bundle=this.getIntent().getExtras();
		String shelfCode=bundle.getString("shelfCode");
		url = bundle.getString(SELECTOR_URL_KEY);
		Long skuId=bundle.getLong("skuId");
		if(skuId!=null)
			skuShelfBatchStockQueryParamsModel.setSkuId(skuId);
		skuShelfBatchStockQueryParamsModel.setFuzzy(shelfCode);
		setFirstPage();
		setParams();
		getData(PdaConstants.CLEAR_LISTVIEW_NO);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.commonsGoodsSkuSelect_headerView, R.string.shelf_batch_stock_select);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.commons_goods_sku_select;
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
				Intent intent = getIntent();
				SkuShelfBatchStockModel data= list.get(position-1);
				Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(SER_KEY,data);  
		        intent.putExtras(mBundle);
				setResult(RESULT_CODE, intent);
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
	
	private void getData( final int type) {
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			list.clear();
		}
		skuShelfBatchStockService.searchPageSkuShelfBatchStockByParams(url,skuShelfBatchStockQueryParamsModel, new AbstractServiceCallBack<QueryResult<SkuShelfBatchStockModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<SkuShelfBatchStockModel> result) {
				if (result.getTotalCount() <= 0) {
					T.showShort(context, "查找不到货位信息");
					finish();
				}else{
					list.addAll(result.getRecords());
					adapter.notifyDataSetChanged();
				}
			}
		});
	}
}
