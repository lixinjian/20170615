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
import com.ds365.erp.wms.pda.model.commons.VehicleModel;
import com.ds365.erp.wms.pda.model.commons.VehicleQueryParamsModel;
import com.ds365.erp.wms.pda.service.vehicle.VehicleService;
import com.ds365.erp.wms.pda.view.common.adapter.VehicleSelectorAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * 说明 ：车辆信息
 */
public class VehicleSelectorActivity extends BasePdaPageActivity {

	private PullToRefreshListView listView;
	private List<VehicleModel> details = new ArrayList<VehicleModel>();
	private VehicleSelectorAdapter adapter;
	private VehicleService vehicleService = new VehicleService();
	private VehicleQueryParamsModel vehicleQueryParamsModel = new VehicleQueryParamsModel();
	public static final String SER_KEY = PdaConstants.nextSerKey();
	public static final int RESULT_CODE=PdaConstants.nextResultCode();
	
	@Override
	protected void initActivityView() {
		listView = (PullToRefreshListView) findViewById(R.id.vehicleSelectorList_details);
		adapter = new VehicleSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		
		setFirstPage();
		setQueryParams();
		getData(PdaConstants.CLEAR_LISTVIEW_YES);
	}

	private void setQueryParams(){
		vehicleQueryParamsModel.setStart(start);
		vehicleQueryParamsModel.setLimit(PdaConstants.LIMIT);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.vehicleSelectorList_headerview, R.string.vehicle_choice);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.common_vehicle_selector;
	}

	@Override
	protected void setListener() {
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setQueryParams();
				getData(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setQueryParams();
				getData(PdaConstants.CLEAR_LISTVIEW_NO);
			}
		}));
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = getIntent();
				Bundle bundle = new Bundle();  
				bundle.putSerializable(SER_KEY,details.get(position-1));  
				intent.putExtras(bundle);
				setResult(RESULT_CODE, intent);
				finish();
			}
		});
		
	}
	private void getData(final int type) {
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		
		vehicleService.searchPageByParams(vehicleQueryParamsModel, new AbstractServiceCallBack<QueryResult<VehicleModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<VehicleModel> result) {
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
