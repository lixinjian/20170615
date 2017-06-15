package com.ds365.erp.wms.pda.view.query.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.sale.SaleOutBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleOutBillQueryParamsModel;
import com.ds365.erp.wms.pda.service.sale.SaleOutService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.EmployeeSelectorActivity;
import com.ds365.erp.wms.pda.view.query.adapter.SaleOutBillAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * 说明 ：销售出库单列表 
 */
public class SaleOutBillListActivity extends BasePdaPageActivity {
	
	private DateEditField startTimeValue;
	private DateEditField endTimeValue;
	private EditText customerNameValue;
	private PullToRefreshListView listView;
	private TextView queryButton;
	private EmployeeModel customer;
	private EditText orderBillCodeValue,outBillCodeValue;
	private ImageView orderBillCodeScanButton, outBillCodeScanButton;
	private List<SaleOutBillModel> details = new ArrayList<SaleOutBillModel>();
	private SaleOutBillAdapter adapter;
	public static final  String SER_KEY=PdaConstants.nextSerKey();

	private static final int requestCodeForOrderBillCode = 1;
	private static final int requestCodeForOutBillCode = 2;
	
	private SaleOutService saleOutService = new SaleOutService();
	private SaleOutBillQueryParamsModel saleOutBillQueryParamsModel = new SaleOutBillQueryParamsModel();
	
	@Override
	protected int getContentViewId() {
		return R.layout.query_sale_out_bill_list;
	}
	
	@Override
	protected void initActivityView() {
		orderBillCodeScanButton = (ImageView) findViewById(R.id.saleOutBillList_orderBillCodeScan_button);
		outBillCodeScanButton = (ImageView) findViewById(R.id.saleOutBillList_outBillCodeScan_button);
		queryButton = (TextView) findViewById(R.id.saleOutBillList_queryButton);
		orderBillCodeValue = (EditText) findViewById(R.id.saleOutBillList_orderBillCode_value);
		outBillCodeValue = (EditText) findViewById(R.id.saleOutBillList_outBillCode_value);
		startTimeValue = (DateEditField) findViewById(R.id.saleOutBillList_startTime_value);
		endTimeValue = (DateEditField) findViewById(R.id.saleOutBillList_endTime_value);
		customerNameValue = (EditText) findViewById(R.id.saleOutBillList_customerName_value);
		listView = (PullToRefreshListView) findViewById(R.id.saleOutBillList_details);
		
		adapter = new SaleOutBillAdapter(context, details); 
		listView.setAdapter(adapter); 
		ListViewUtil.setOnPullBothListView(listView);
		
		setFirstPage();
		setParams();
		searchPageSaleOutBills(PdaConstants.CLEAR_LISTVIEW_YES);
		
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleOutBillList_headerview, R.string.query_sale_out_bill_list);
	}

	@Override
	protected void setListener() {		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SaleOutBillModel bill=details.get(position-1);
				IntentUtils.startActivityForSeria(context, SaleOutBillShowActivity.class
						, SER_KEY , bill, null);
			}
		});
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {
			
			@Override
			public void onPullDown() {
					setFirstPage();
					setParams();
					searchPageSaleOutBills(PdaConstants.CLEAR_LISTVIEW_YES);
			}
			
			@Override
			public void onPullUp() {
					setNextPage();
					setParams();
					searchPageSaleOutBills(PdaConstants.CLEAR_LISTVIEW_NO);
			}
		}));
		
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setFirstPage();
				setParams();
				searchPageSaleOutBills(PdaConstants.CLEAR_LISTVIEW_YES);
			}
		});
		
		customerNameValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					Intent intent = new Intent(context,EmployeeSelectorActivity.class);
					intent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchCustomerByParams);
					startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
				}
				return false;
			}
		});
		
		outBillCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(SaleOutBillListActivity.this
						, CodeScanActivity.class, requestCodeForOutBillCode);
			}
		});
		
		orderBillCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(SaleOutBillListActivity.this
						, CodeScanActivity.class, requestCodeForOrderBillCode);
			}
		});
	}
	
	protected void setParams() {
		String orderBillCode = orderBillCodeValue.getText().toString().trim();
		String outBillCode = outBillCodeValue.getText().toString().trim();
		Date startTime = startTimeValue.getValue();
		Date endTime = endTimeValue.getValue();
		
		saleOutBillQueryParamsModel.setStart(start);
		saleOutBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		saleOutBillQueryParamsModel.setStartTime(startTime);
		saleOutBillQueryParamsModel.setEndTime(endTime);
		saleOutBillQueryParamsModel.setRelatedBillCode(orderBillCode);
		saleOutBillQueryParamsModel.setBillCode(outBillCode);
		if (customer != null)
			saleOutBillQueryParamsModel.setCustomerId(customer.getId());
	}

	private void searchPageSaleOutBills(final int type) {
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		saleOutService.searchPageSaleOutBillsByParams(saleOutBillQueryParamsModel, new AbstractServiceCallBack<QueryResult<SaleOutBillModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<SaleOutBillModel> result) {
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data) {
			if (resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				customer = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				customerNameValue.setText(customer.getName());
			}else if(requestCode == requestCodeForOutBillCode && resultCode == CodeScanActivity.RESULT_CODE){
				outBillCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}else if(requestCode == requestCodeForOrderBillCode && resultCode == CodeScanActivity.RESULT_CODE){
				orderBillCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
}