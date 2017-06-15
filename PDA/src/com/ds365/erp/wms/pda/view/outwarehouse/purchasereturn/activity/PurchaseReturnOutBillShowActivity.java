package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.activity;

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
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutDetailModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseEnterService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter.PurchaseReturnOutDetailViewAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * 说明 ：采购出库单详情 
 */
public class PurchaseReturnOutBillShowActivity extends BasePdaListActivity {
	private DateField makeTimeValue;
	private PullToRefreshListView listView;
	private PurchaseReturnOutDetailViewAdapter adapter;
	private TextView orderBillCodeValue,outBillCodeValue;
	private TextView supplierNameValue;
	
	private Long relatedBillId;
	private List<PurchaseReturnOutDetailModel> details = new ArrayList<PurchaseReturnOutDetailModel>();
	
	private PurchaseEnterService purchaseEnterService = new PurchaseEnterService();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_out_bill_show;
	}
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseReturnOutBillShow_headerview, R.string.purchase_return_out_bill);
	}
	
	@Override
	protected void initActivityView() {
		PurchaseReturnOutBillModel bill = (PurchaseReturnOutBillModel) getIntent().getSerializableExtra(
				PurchaseReturnOrderBillListActivity.SER_KEY);
		supplierNameValue = (TextView) findViewById(R.id.purchaseReturnOutBillShow_supplierName_value);
		makeTimeValue = (DateField) findViewById(R.id.purchaseReturnOutBillShow_makeTime_value);
		orderBillCodeValue = (TextView) findViewById(R.id.purchaseReturnOutBillShow_orderBillCode_value); 
		outBillCodeValue = (TextView) findViewById(R.id.purchaseReturnOutBillShow_outBillCode_value); 
		listView = (PullToRefreshListView) findViewById(R.id.purchaseReturnOutBillShow_details);
		
		relatedBillId = bill.getId();
		bill.setRelatedBillId(relatedBillId);
		supplierNameValue.setText(bill.getSupplier().getName());
		orderBillCodeValue.setText(bill.getRelatedBillCode());
		outBillCodeValue.setText(bill.getBillCode());
		makeTimeValue.setValue(bill.getMakeTime());
		adapter = new PurchaseReturnOutDetailViewAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		searchPurchaseReturnOutDetails();
	}

	private void searchPurchaseReturnOutDetails() {
		details.clear();
		purchaseEnterService.searchPurchaseReturnOutDetailsByBillId(relatedBillId,
				new AbstractServiceCallBack<List<PurchaseReturnOutDetailModel>>(PurchaseReturnOutBillShowActivity.this) {

					@Override
					public void doSuccess(List<PurchaseReturnOutDetailModel> result) {
						if (result != null && result.size() > 0) {
							details.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						adapter.notifyDataSetChanged();
					}
				});
	}

	@Override
	protected void setListener() {
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityBySerialForResult(PurchaseReturnOutBillShowActivity.this
						, PurchaseReturnOutDetailShowActivity.class, SER_KEY
						, details.get(position-1), PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchPurchaseReturnOutDetails();
			}
		}));
	}
}