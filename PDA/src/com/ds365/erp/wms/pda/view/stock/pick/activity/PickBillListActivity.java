package com.ds365.erp.wms.pda.view.stock.pick.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.CommonTitleTab;
import com.ds365.commons.widget.CommonTitleTab.SelectChangeListener;
import com.ds365.commons.widget.SearchField.EditTextClearListener;
import com.ds365.commons.widget.SearchField;
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
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBillListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

/**
 * 拣货下架  -- 拣货单列表
 * @author lxj
 *
 */
public class PickBillListActivity extends BasePdaPageActivity implements OnClickListener{
	private CommonTitleTab titleTab;
	private List<PickBillModel> details = new ArrayList<PickBillModel>();
	private PullToRefreshListView listView;
	private PickBillListAdapter adapter;
	private ImageView pickerNameSearchButton, pickBillScanButton;
	private EditText pickerNameValue;
	private SearchField pickBillValue;
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	private EmployeeModel employeeModel;
	
	private List<String> titleTabList = new ArrayList<String>();
	
	private PickService pickService = new PickService();
	private PickBillQueryParamsModel pickBillQueryParamsModel = new PickBillQueryParamsModel();
	private int select = 0;
	private static final String NOT_PICK_TAB_CODE="notPick";
	private static final String PICKED_TAB_CODE="picked";
	private  String[]  tabCodes={NOT_PICK_TAB_CODE,PICKED_TAB_CODE};
	
	private ServiceCallBack<QueryResult<PickBillModel>> pickBillServiceCallBack = new AbstractServiceCallBack<QueryResult<PickBillModel>>(
			PickBillListActivity.this) {

		@Override
		public void doSuccess(QueryResult<PickBillModel> result) {
			if (result != null && result.getRecords().size() > 0) {
				details.addAll(result.getRecords());
			}else{
				T.showShort(context, R.string.listview_no_more);
			}
			// 数据刷新后通知适配器更新UI
			adapter.notifyDataSetChanged();
		}

	};
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_bill_list;
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		setFirstPage();
		onTabChange(true);
	}
	
	@Override
	protected void initActivityView() {
		pickerNameValue = (EditText) findViewById(R.id.pickBillList_pickerName_value);
		pickBillValue = (SearchField) findViewById(R.id.pickBillList_fuzzySearch_value);
		pickerNameSearchButton = (ImageView) findViewById(R.id.pickBillList_pickerNameSearch_button);
		pickBillScanButton = (ImageView) findViewById(R.id.pickBillList_pickBillScan_button);
		listView = (PullToRefreshListView) findViewById(R.id.pickBillList_details);
		titleTab = (CommonTitleTab) findViewById(R.id.pickBillList_titleTab);
		titleTabList.add("未拣货");
		titleTabList.add("已拣货");
		ListViewUtil.setOnPullBothListView(listView);
		adapter = new PickBillListAdapter(context, details );
		listView.setAdapter(adapter);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBillList_headerview, R.string.title_pick_bill_list);
	}

	@Override
	protected void setListener() {
		pickerNameSearchButton.setOnClickListener(this);
		pickBillScanButton.setOnClickListener(this);
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
				onTabChange(true);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				onTabChange(false);
			}
		}));
		
		pickerNameValue.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String pickName = pickerNameValue.getText().toString().trim();
				if(pickName != null && !"".equals(pickName)){
					onTabChange(true);
				}
			}
		});
		
		pickBillValue.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String pickBill = pickBillValue.getValue();
				if(pickBill != null && !"".equals(pickBill)){
					onTabChange(true);
				}
			}
		});
		
		titleTab.setSelectChangeListener(new SelectChangeListener() {
			
			@Override
			public void onItemSelect(int selectValue, RadioButton selectRadioButton) {
				select = selectValue;
				setFirstPage();
				onTabChange(true);
			}
		});
		titleTab.setData(titleTabList);
		
		pickBillValue.setEditTextClearListener(new EditTextClearListener() {
			
			@Override
			public void onEditTextClear() {
				setFirstPage();
				onTabChange(true);
			}
		});
		
	}
	
	private void setParams(){
		pickBillQueryParamsModel.setStart(start);
		pickBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		String pickName = pickerNameValue.getText().toString().trim();
		String pickBill = pickBillValue.getValue();
		if(pickName != null && !"".equals(pickName)){
			pickBillQueryParamsModel.setPickerId(employeeModel.getId());
		}else{
			pickBillQueryParamsModel.setPickerId(null);
		}
		if(pickBill != null && !"".equals(pickBill)){
			pickBillQueryParamsModel.setFuzzy(pickBill);
		}else{
			pickBillQueryParamsModel.setFuzzy(null);
		}
	}
	
	private void onTabChange(boolean isClearListView){
		setParams();
		if (tabCodes[select].equals(NOT_PICK_TAB_CODE)) {
			adapter.setPickButtonShowFlag(true);
			searchPagePickBillForNotPick(isClearListView);
		}else if (tabCodes[select].equals(PICKED_TAB_CODE)) {
			adapter.setPickButtonShowFlag(false);
			searchPagePickBillForPicked(isClearListView);
		}
	}
	
	private void searchPagePickBillForNotPick(boolean isClearListView) {
		if (isClearListView) {
			details.clear();
		}
		pickService.searchPagePickBillForNotPick(pickBillQueryParamsModel, pickBillServiceCallBack);
	}
	
	private void searchPagePickBillForPicked(boolean isClearListView) {
		if (isClearListView) {
			details.clear();
		}
		pickService.searchPagePickBillForPicked(pickBillQueryParamsModel, pickBillServiceCallBack);
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
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data) {
			if (resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				employeeModel = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				pickerNameValue.setText(employeeModel.getName());
			}else if (resultCode == CodeScanActivity.RESULT_CODE) {
				pickBillValue.setValue(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
}
