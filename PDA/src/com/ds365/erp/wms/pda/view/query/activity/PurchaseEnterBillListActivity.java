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
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillQueryParamsModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseEnterService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.PurchaseEnterBillAdapter;
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
 * 
 * 说明 ：采购入库单查询 
 */
public class PurchaseEnterBillListActivity extends BasePdaPageActivity{
	private PullToRefreshListView listView;
	private PurchaseEnterBillAdapter adapter;
	private EditText relatedBillCodeValue,billCodeValue;//相关单据号，入库单据号
	private TextView queryButton;
	private DateEditField startTimeValue;
	private DateEditField endTimeValue;
	private ImageView billCodeScanButton, relatedBillCodeScanButton;
	private List<PurchaseEnterBillModel> details = new ArrayList<PurchaseEnterBillModel>();
	
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	
	private static final int requestCodeForOrderBillCode = 1;
	private static final int requestCodeForEnterBillCode = 2;
	
	private PurchaseEnterService purchaseEnterService = new PurchaseEnterService();
	private PurchaseEnterBillQueryParamsModel purchaseEnterBillQueryParamsModel = new PurchaseEnterBillQueryParamsModel();
	@Override
	protected int getContentViewId() {
		return R.layout.query_purchase_enter_bill_list;
	}
	
	@Override
	protected void initActivityView() {
		billCodeScanButton = (ImageView) findViewById(R.id.purchaseEnterBillList_billCodeScan_button);
		relatedBillCodeScanButton = (ImageView) findViewById(R.id.purchaseEnterBillList_relatedBillCodeScan_button);
		startTimeValue = (DateEditField) findViewById(R.id.purchaseEnterBillList_startTime_value);
		endTimeValue = (DateEditField) findViewById(R.id.purchaseEnterBillList_endTime_value);
		relatedBillCodeValue = (EditText) findViewById(R.id.purchaseEnterBillList_relatedBillCode_value);
		billCodeValue = (EditText) findViewById(R.id.purchaseEnterBillList_billCode_value);
		queryButton = (TextView) findViewById(R.id.purchaseEnterBillList_queryButton);
		listView = (PullToRefreshListView) findViewById(R.id.purchaseEnterBillList_listview);
		
		adapter = new PurchaseEnterBillAdapter(context, details); 
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		setFirstPage();
		setParams();
		searchPagePurchaseEnterBill(PdaConstants.CLEAR_LISTVIEW_YES);
		
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseEnterBillList_headerview, R.string.query_purchase_enter_bill_list);
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				PurchaseEnterBillModel bill=details.get(position-1);
				IntentUtils.startActivityForSeria(context, PurchaseEnterBillShowActivity.class
							, SER_KEY, bill, null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
					setFirstPage();
					setParams();
					searchPagePurchaseEnterBill(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
					setNextPage();
					setParams();
					searchPagePurchaseEnterBill(PdaConstants.CLEAR_LISTVIEW_NO);
				}
		}));
		
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setFirstPage();
				setParams();
				searchPagePurchaseEnterBill(PdaConstants.CLEAR_LISTVIEW_YES);
				
			}
		});
		
		billCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(PurchaseEnterBillListActivity.this
						, CodeScanActivity.class, requestCodeForEnterBillCode);
			}
		});
		
		relatedBillCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(PurchaseEnterBillListActivity.this
						, CodeScanActivity.class, requestCodeForOrderBillCode);
			}
		});
	}
	
	protected void setParams() {
		String relatedBillCode = relatedBillCodeValue.getText().toString().trim();
		String billCode = billCodeValue.getText().toString().trim();
		Date startTime = startTimeValue.getValue();
		Date endTime = endTimeValue.getValue();
		
		purchaseEnterBillQueryParamsModel.setStart(start);
		purchaseEnterBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		purchaseEnterBillQueryParamsModel.setStartTime(startTime);
		purchaseEnterBillQueryParamsModel.setEndTime(endTime);
		purchaseEnterBillQueryParamsModel.setRelatedBillCode(relatedBillCode);
		purchaseEnterBillQueryParamsModel.setBillCode(billCode);
		
	}

	private void searchPagePurchaseEnterBill(final int type) {
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		purchaseEnterService.searchPagePurchaseEnterBillByParams(purchaseEnterBillQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<PurchaseEnterBillModel>>(context) {

					@Override
					public void doSuccess(QueryResult<PurchaseEnterBillModel> result) {
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
		if (data != null) {
			if(requestCode == requestCodeForEnterBillCode && resultCode == CodeScanActivity.RESULT_CODE){
				billCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}else if(requestCode == requestCodeForOrderBillCode && resultCode == CodeScanActivity.RESULT_CODE){
				relatedBillCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
}
