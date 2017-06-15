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
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderDetailModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.PurchaseOrderDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
/**
 * 收货入库-订单详情
 *
 */
public class PurchaseOrderBillShowActivity extends BasePdaListActivity {
	private TextView makerNameValue;
	private PullToRefreshListView listView;
	private PurchaseOrderDetailAdapter adapter;
	private TextView billCodeValue;
	private TextView supplierNameValue;
	private DateField makeTimeValue;
	private Long billId;
	private List<PurchaseOrderDetailModel> details = new ArrayList<PurchaseOrderDetailModel>();
	
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_purchase_order_bill_show;
	}
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseOrderBillShow_headerview, R.string.purchase_order_bill);
	}
	
	@Override
	protected void initActivityView() {
		PurchaseOrderBillModel bill=(PurchaseOrderBillModel)getIntent().getSerializableExtra(PurchaseOrderBillListActivity.SER_KEY);	
		billId=bill.getId();
		
		makerNameValue = (TextView) findViewById(R.id.purchaseOrderBillShow_makerName_value);
		makerNameValue.setText(bill.getMaker().getName());
		supplierNameValue = (TextView) findViewById(R.id.purchaseOrderBillShow_supplierName_value);
		
		makeTimeValue=(DateField) findViewById(R.id.purchaseOrderBillShow_makeTime_value);
		
		billCodeValue = (TextView) findViewById(R.id.purchaseOrderBillShow_billCode_value);
		
		supplierNameValue.setText(bill.getSupplier().getName());
		billCodeValue.setText(bill.getBillCode());
		makeTimeValue.setValue(bill.getMakeTime());
		
		listView = (PullToRefreshListView) findViewById(R.id.purchaseOrderBillShow_details);
		
		adapter = new PurchaseOrderDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
        searchPurchaseOrderDetails();
	}

	private void searchPurchaseOrderDetails() {
		details.clear();
		purchaseOrderService.searchPurchaseOrderDetailsByBillId(billId,
				new AbstractServiceCallBack<List<PurchaseOrderDetailModel>>(PurchaseOrderBillShowActivity.this) {

					@Override
					public void doSuccess(List<PurchaseOrderDetailModel> result) {
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
				IntentUtils.startActivityForSeria(context, PurchaseOrderDetailShowActivity.class
						, SER_KEY, details.get(position-1), null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchPurchaseOrderDetails();
			}
		}));
	}
}
