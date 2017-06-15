package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseEnterService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.PurchaseEnterDetailViewAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * 说明 ：采购入库单详情
 */
public class PurchaseEnterBillShowActivity extends BasePdaListActivity {
	private PullToRefreshListView listView;
	private PurchaseEnterDetailViewAdapter adapter;
	private TextView orderBillCodeValue,enterBillCodeValue;
	private TextView supplierNameValue,receiptorNameValue;
	private DateField makeTimeValue;
	private Long billId;
	private List<PurchaseEnterDetailModel> details = new ArrayList<PurchaseEnterDetailModel>();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private PurchaseEnterService purchaseEnterService = new PurchaseEnterService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_bill_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseEnterBillShow_headerview, R.string.purchase_enter_bill);
	}
	
	@Override
	protected void initActivityView() {
		PurchaseEnterBillModel bill=(PurchaseEnterBillModel)getIntent().getSerializableExtra(PurchaseOrderBillListActivity.SER_KEY);	
		billId = bill.getId();
		enterBillCodeValue = (TextView) findViewById(R.id.purchaseEnterBillShow_enterBillCode_value);
		orderBillCodeValue = (TextView) findViewById(R.id.purchaseEnterBillShow_orderBillCode_value);
		supplierNameValue = (TextView) findViewById(R.id.purchaseEnterBillShow_supplierName_value);
		receiptorNameValue = (TextView) findViewById(R.id.purchaseEnterBillShow_receiptorName_value);
		makeTimeValue=(DateField) findViewById(R.id.purchaseEnterBillShow_makeTime_value);
		
		supplierNameValue.setText(bill.getSupplier().getName());
		orderBillCodeValue.setText(bill.getRelatedBillCode());
		enterBillCodeValue.setText(bill.getBillCode());
		makeTimeValue.setValue(bill.getMakeTime());
		receiptorNameValue.setText(bill.getExaminer().getName());	//TODO此处应显示收货员
		listView = (PullToRefreshListView) findViewById(R.id.purchaseEnterBillShow_details);
		adapter = new PurchaseEnterDetailViewAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		searchPurchaseEnterDetails();
	}

	private void searchPurchaseEnterDetails() {
		details.clear();
		purchaseEnterService.searchPurchaseEnterDetailsByBillId(billId,
				new AbstractServiceCallBack<List<PurchaseEnterDetailModel>>(PurchaseEnterBillShowActivity.this) {

					@Override
					public void doSuccess(List<PurchaseEnterDetailModel> result) {
						if (result != null && result.size() > 0) {
							details.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						adapter.notifyDataSetInvalidated();
					}
				});
	}
	
	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				IntentUtils.startActivityForSeria(context, PurchaseEnterDetailShowActivity.class
						, SER_KEY, details.get(position-1), null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchPurchaseEnterDetails();
			}
		}));
	}
}
