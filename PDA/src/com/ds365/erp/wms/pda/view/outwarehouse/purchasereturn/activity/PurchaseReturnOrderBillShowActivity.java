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
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderDetailModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter.PurchaseReturnOrderDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
/**
 * 收货入库-订单详情
 *
 */
public class PurchaseReturnOrderBillShowActivity extends BasePdaListActivity {
	private DateField makeTimeValue;
	private PullToRefreshListView listView;
	private PurchaseReturnOrderDetailAdapter adapter;
	private TextView billCodeValue;
	private TextView supplierNameValue;
	
	private Long relatedBillId;
	private List<PurchaseReturnOrderDetailModel>  details=new ArrayList<PurchaseReturnOrderDetailModel>();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_order_bill_show;
	}
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseReturnOrderBillShow_headerview, R.string.purchase_return_order_bill);
	}
	
	@Override
	protected void initActivityView() {
		PurchaseReturnOrderBillModel bill = (PurchaseReturnOrderBillModel) getIntent().getSerializableExtra(PurchaseReturnOrderBillListActivity.SER_KEY);
		supplierNameValue = (TextView) findViewById(R.id.purchaseReturnOrderBillShow_supplierName_value);
		makeTimeValue = (DateField) findViewById(R.id.purchaseReturnOrderBillShow_makeTime_value);
		billCodeValue = (TextView) findViewById(R.id.purchaseReturnOrderBillShow_billCode_value);
		listView = (PullToRefreshListView) findViewById(R.id.purchaseReturnOrderBillShow_details);
		
		relatedBillId = bill.getId();
		bill.setRelatedBillId(relatedBillId);
		supplierNameValue.setText(bill.getSupplier().getName());
		billCodeValue.setText(bill.getBillCode());
		makeTimeValue.setValue(bill.getMakeTime());
		adapter = new PurchaseReturnOrderDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		searchPurchaseReturnOrderDetails();
	}

	private void searchPurchaseReturnOrderDetails() {
		details.clear();
		purchaseOrderService.searchPurchaseReturnOrderDetailsByBillId(relatedBillId,
				new AbstractServiceCallBack<List<PurchaseReturnOrderDetailModel>>(PurchaseReturnOrderBillShowActivity.this) {

					@Override
					public void doSuccess(List<PurchaseReturnOrderDetailModel> result) {
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
				IntentUtils.startActivityBySerialForResult(PurchaseReturnOrderBillShowActivity.this
						, PurchaseReturnOrderDetailShowActivity.class, SER_KEY
						, details.get(position-1), PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchPurchaseReturnOrderDetails();
			}
		}));
	}
}