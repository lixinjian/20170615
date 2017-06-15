package com.ds365.erp.wms.pda.view.query.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateEditField;
import com.ds365.commons.widget.DropDownListView;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillQueryParamsModel;
import com.ds365.erp.wms.pda.service.pick.PickService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.EmployeeSelectorActivity;
import com.ds365.erp.wms.pda.view.query.adapter.PickBillListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 拣货单查询
 */
public class PickBillListActivity extends BasePdaPageActivity implements OnClickListener{
	private List<PickBillModel> details = new ArrayList<PickBillModel>();
	private PullToRefreshListView listView;
	private PickBillListAdapter adapter;
	private ImageView pickerNameSearchButton, pickBillScanButton;
	private EditText pickerNameValue;
	private EditText pickBillValue;
	private DateEditField startTimeValue,endTimeValue;
	private DropDownListView billStateValue;
	private TextView searchButton;
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private PickService pickService = new PickService();
	private PickBillQueryParamsModel pickBillQueryParamsModel = new PickBillQueryParamsModel();
	private EmployeeModel picker;
	
	@Override
	protected int getContentViewId() {
		return R.layout.query_pick_bill_list;
	}
	
	@Override
	protected void initActivityView() {
		searchButton = (TextView) findViewById(R.id.pickBillList_search_button);
		startTimeValue = (DateEditField) findViewById(R.id.pickBillList_startTime_value);
		endTimeValue = (DateEditField) findViewById(R.id.pickBillList_endTime_value);
		billStateValue = (DropDownListView) findViewById(R.id.pickBillList_billState_value);
		pickerNameValue = (EditText) findViewById(R.id.pickBillList_pickerName_value);
		pickBillValue = (EditText) findViewById(R.id.pickBillList_pickBillCode_value);
		pickerNameSearchButton = (ImageView) findViewById(R.id.pickBillList_pickerNameSearch_button);
		pickBillScanButton = (ImageView) findViewById(R.id.pickBillList_pickBillScan_button);
		listView = (PullToRefreshListView) findViewById(R.id.pickBillList_details);
		ListViewUtil.setOnPullBothListView(listView);
		adapter = new PickBillListAdapter(context, details );
		listView.setAdapter(adapter);
		billStateValue.setUrl(ConstantUrl.common_getPickBillStateEnumsForJsonResult);
		
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBillList_headerview, R.string.title_pick_bill_list);
	}

	@Override
	protected void setListener() {
		pickerNameSearchButton.setOnClickListener(this);
		pickBillScanButton.setOnClickListener(this);
		searchButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityForSeria(context, PickBillShowActivity.class
						, SER_KEY, details.get(position-1), null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setParams();
				searchPagePickBill(true);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setParams();
				searchPagePickBill(false);
			}
		}));
	}
	
	private void setParams(){
		pickBillQueryParamsModel.setStart(start);
		pickBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		String pickName = pickerNameValue.getText().toString().trim();
		String pickBill = pickBillValue.getText().toString().trim();
		Date startTime = startTimeValue.getValue();
		Date endTime = endTimeValue.getValue();
		pickBillQueryParamsModel.setStartTime(startTime);
		pickBillQueryParamsModel.setEndTime(endTime);
		if("".equals(pickName)){
			pickBillQueryParamsModel.setPickerId(null);
		}else if(picker != null){
			pickBillQueryParamsModel.setPickerId(picker.getId());
		}
		if("".equals(pickBill)){
			pickBillQueryParamsModel.setFuzzy(null);
		}else{
			pickBillQueryParamsModel.setFuzzy(pickBill);
		}
		if(null != billStateValue.getValue())
			pickBillQueryParamsModel.setBillStateId(Integer.valueOf(billStateValue.getValue().toString()));
		else
			pickBillQueryParamsModel.setBillStateId(null);
	}
	
	private void searchPagePickBill(boolean isClearListView) {
		if (isClearListView) {
			details.clear();
		}
		pickService.searchPagePickBillByParams(pickBillQueryParamsModel, new AbstractServiceCallBack<QueryResult<PickBillModel>>(
				PickBillListActivity.this) {

			@Override
			public void doSuccess(QueryResult<PickBillModel> result) {
				if (result != null && result.getRecords().size() > 0) {
					details.addAll(result.getRecords());
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pickBillList_pickerNameSearch_button:
			Intent intent = new Intent(context,EmployeeSelectorActivity.class);
			intent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPagePickerByParams);
			startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
			break;
		case R.id.pickBillList_pickBillScan_button:
			IntentUtils.startActivityForResult(PickBillListActivity.this
					, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
			break;
		case R.id.pickBillList_search_button:
			setFirstPage();
			setParams();
			searchPagePickBill(true);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				picker = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				pickerNameValue.setText(picker.getName());
			}
		}
	}
	
}
