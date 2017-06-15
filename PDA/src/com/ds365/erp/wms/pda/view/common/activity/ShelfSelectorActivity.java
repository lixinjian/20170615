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
import com.ds365.erp.wms.pda.model.stock.ShelfQueryParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.view.common.adapter.ShelfSelectorAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * 选择库区货位
 *
 */
public class ShelfSelectorActivity extends BasePdaPageActivity {
	private List<ShelfModel> details = new ArrayList<ShelfModel>();
	private PullToRefreshListView listView;
	private ShelfSelectorAdapter adapter;
//	private JsonParser<JsonResult<QueryResult<>>> jsonParser;
	
	public static final int RESULT_CODE=PdaConstants.nextResultCode();
	
	public static final String SER_KEY=PdaConstants.nextSerKey();
	
	private ShelfService shelfService = new ShelfService();
	private ShelfQueryParamsModel shelfQueryParamsModel = new ShelfQueryParamsModel();
	@Override
	protected int getContentViewId() {
		return R.layout.common_shelf_selector;
	}
	

	
	@Override
	protected void initActivityView() {
		listView = (PullToRefreshListView) findViewById(R.id.shelfSelector_details);
		adapter = new ShelfSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		
		String  fuzzy = getIntent().getExtras().getString("fuzzy");
		shelfQueryParamsModel.setFuzzy(fuzzy);
		setParams();
		setFirstPage();
		getData(PdaConstants.CLEAR_LISTVIEW_NO);
		
	}
	
	private void setParams(){
		shelfQueryParamsModel.setStart(start);
		shelfQueryParamsModel.setLimit(PdaConstants.LIMIT);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.shelfSelector_headerview, R.string.shelf_selector);
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				ShelfModel shelf=details.get(position-1);
				
				Bundle bundle=new Bundle();
				bundle.putSerializable(SER_KEY, shelf);
				getIntent().putExtras(bundle);
				setResult(RESULT_CODE,getIntent());
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

	private void getData(final int type) {
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		
		shelfService.searchPageByParams(shelfQueryParamsModel, new AbstractServiceCallBack<QueryResult<ShelfModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<ShelfModel> result) {
				if (result.getTotalCount() <= 0) {
					T.showShort(context, "查找不到货位信息");
					finish();
				}else{
					details.addAll(result.getRecords());
					adapter.notifyDataSetInvalidated();
				}
			}
		});
	}
}
