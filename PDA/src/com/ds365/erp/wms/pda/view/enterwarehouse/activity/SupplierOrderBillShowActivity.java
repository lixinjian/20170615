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
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderBillModel;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderDetailModel;
import com.ds365.erp.wms.pda.service.supplier.SupplierOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SupplierOrderDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 *
 */
public class SupplierOrderBillShowActivity extends BasePdaListActivity {
	private TextView makerNameValue;
	private PullToRefreshListView listView;
	private SupplierOrderDetailAdapter adapter;
	private TextView billCodeValue,deliverBillNo;
	private TextView supplierNameValue;
	private DateField makeTimeValue;
	private Long billId;
	private List<SupplierOrderDetailModel> details = new ArrayList<SupplierOrderDetailModel>();
	
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private SupplierOrderService supplierOrderService = new SupplierOrderService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_supplier_order_bill_show;
	}
	@Override
	protected void initNavigation() {
		initHeadView(R.id.supplierOrderBillShow_headerview, R.string.supplier_order_bill);
	}
	
	@Override
	protected void initActivityView() {
		SupplierOrderBillModel bill=(SupplierOrderBillModel)getIntent().getSerializableExtra(PurchaseOrderBillListActivity.SER_KEY);	
		billId=bill.getId();
		deliverBillNo = (TextView) findViewById(R.id.supplierOrderBillShow_deliverBillNo_value);
		makerNameValue = (TextView) findViewById(R.id.supplierOrderBillShow_makerName_value);
		makerNameValue.setText(bill.getMaker().getName());
		supplierNameValue = (TextView) findViewById(R.id.supplierOrderBillShow_supplierName_value);
		
		makeTimeValue=(DateField) findViewById(R.id.supplierOrderBillShow_makeTime_value);
		
		billCodeValue = (TextView) findViewById(R.id.supplierOrderBillShow_billCode_value);
		
		supplierNameValue.setText(bill.getSupplier().getName());
		billCodeValue.setText(bill.getRelatedBillCode());
		makeTimeValue.setValue(bill.getMakeTime());
		deliverBillNo.setText(bill.getBillCode());
		listView = (PullToRefreshListView) findViewById(R.id.supplierOrderBillShow_details);
		
		adapter = new SupplierOrderDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
        searchSupplierOrderDetails();
	}

	private void searchSupplierOrderDetails() {
		details.clear();
		supplierOrderService.searchSupplierOrderDetailsByBillId(billId,
				new AbstractServiceCallBack<List<SupplierOrderDetailModel>>(SupplierOrderBillShowActivity.this) {

					@Override
					public void doSuccess(List<SupplierOrderDetailModel> result) {
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
				IntentUtils.startActivityForSeria(context, SupplierOrderDetailShowActivity.class
						, SER_KEY, details.get(position-1), null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchSupplierOrderDetails();
			}
		}));
	}
}
