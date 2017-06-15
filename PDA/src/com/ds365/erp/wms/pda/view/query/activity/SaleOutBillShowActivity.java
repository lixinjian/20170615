package com.ds365.erp.wms.pda.view.query.activity;

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
import com.ds365.erp.wms.pda.model.sale.SaleOutBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleOutDetailModel;
import com.ds365.erp.wms.pda.service.sale.SaleOutService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.query.adapter.SaleOutDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * 说明 ：销售出库单详情 
 */
public class SaleOutBillShowActivity extends BasePdaListActivity {
	
	private TextView orderBillCodeValue,outBillCodeValue,customerNameValue,deliverNameValue/*送货员*/,driverNameValue;
	private DateField makeTimeValue;
	private SaleOutDetailAdapter adapter;
	private PullToRefreshListView listView;
	
	private List<SaleOutDetailModel> details = new ArrayList<SaleOutDetailModel>();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private SaleOutService saleOutService = new SaleOutService();
	private Long billId;
	@Override
	protected int getContentViewId() {
		return R.layout.query_sale_out_bill_view;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleOutBillShow_headerview, R.string.query_sale_out_bill);
	}
	
	@Override
	protected void initActivityView() {
		SaleOutBillModel bill = (SaleOutBillModel) getIntent().getSerializableExtra(SaleOutBillListActivity.SER_KEY);
		billId = bill.getId();
		orderBillCodeValue = (TextView) findViewById(R.id.saleOutBillShow_orderBillCode_value);
		outBillCodeValue = (TextView) findViewById(R.id.saleOutBillShow_outBillCode_value);
		customerNameValue = (TextView) findViewById(R.id.saleOutBillShow_customerName_value);
		deliverNameValue = (TextView) findViewById(R.id.saleOutBillShow_deliverName_value);
		driverNameValue = (TextView) findViewById(R.id.saleOutBillShow_driverName_value);
		makeTimeValue = (DateField) findViewById(R.id.saleOutBillShow_makeTime_value);
		orderBillCodeValue.setText(bill.getRelatedBillCode());
		outBillCodeValue.setText(bill.getBillCode());
//		deliverNameValue.setText(bill.getde);
		driverNameValue.setText(bill.getDriver().getName());
		customerNameValue.setText(bill.getCustomer().getName());
		makeTimeValue.setValue(bill.getMakeTime());
		
		listView = (PullToRefreshListView) findViewById(R.id.saleOutBillShow_details);
		adapter = new SaleOutDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		searchSaleOutDetails();
	}
	
	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityBySerialForResult(SaleOutBillShowActivity.this
						, SaleOutDetailShowActivity.class , SER_KEY
						,details.get(position-1), PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchSaleOutDetails();
			}
		}));
	}

	private void searchSaleOutDetails() {
		details.clear();
		saleOutService.searchSaleOutDetailsByBillId(billId,
				new AbstractServiceCallBack<List<SaleOutDetailModel>>(context) {

					@Override
					public void doSuccess(List<SaleOutDetailModel> result) {
						if (result != null && result.size() > 0) {
							details.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						adapter.notifyDataSetChanged();
					}
				});
	}
}

