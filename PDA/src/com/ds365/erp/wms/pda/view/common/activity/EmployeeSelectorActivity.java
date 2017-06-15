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
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeQueryParamsModel;
import com.ds365.erp.wms.pda.service.employee.EmployeeService;
import com.ds365.erp.wms.pda.view.common.adapter.EmployeeSelectorAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * 说明 ：保管员、收货员 列表
 * @author Li xinJian
 * @date 2016年8月29日
 */
public class EmployeeSelectorActivity extends BasePdaPageActivity {

	private PullToRefreshListView listView;
	private List<EmployeeModel> details = new ArrayList<EmployeeModel>();
	private EmployeeSelectorAdapter adapter;
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	public static final String SELECTOR_URL_KEY = EmployeeSelectorActivity.class.getName();
	
	private EmployeeService employeeService = new EmployeeService();
	
	private EmployeeQueryParamsModel employeeQueryParamsModel = new EmployeeQueryParamsModel();
	private String url;
	
	@Override
	protected void onStart() {
		super.onStart();
		setFirstPage();
		setQueryParams();
//		Constants.getUserInfo().getOrganizationId();
		getData(PdaConstants.CLEAR_LISTVIEW_YES);
	}
	
	@Override
	protected void initActivityView() {
		listView = (PullToRefreshListView) findViewById(R.id.employeeSelectList_details);
		
		url = getIntent().getStringExtra(SELECTOR_URL_KEY);
		
		adapter = new EmployeeSelectorAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
	}

	private void setQueryParams(){
		employeeQueryParamsModel.setStart(start);
		employeeQueryParamsModel.setLimit(PdaConstants.LIMIT);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.employeeSelectList_headerview, R.string.employee_choice);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.common_employee_selector;
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
				/*String employeeName = details.get(position-1).getName();
				intent.putExtra("employeeName", employeeName);*/
				
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
		
		employeeService.searchPageByParams(url,employeeQueryParamsModel, new AbstractServiceCallBack<QueryResult<EmployeeModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<EmployeeModel> result) {
				if (result != null && result.getRecords().size() > 0) {
					details.addAll(result.getRecords());
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
				//数据刷新后通知适配器更新UI
				adapter.notifyDataSetChanged();
			}
		});
	}
}
