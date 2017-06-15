package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterDetailModel;
import com.ds365.erp.wms.pda.service.sale.SaleReturnEnterService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SaleReturnEnterDetailViewAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * 退货入库--销售退货入库单详情
 */
public class SaleReturnEnterBillShowActivity extends BasePdaListActivity {
	
	private TextView billCodeValue,customerNameValue,receiptorNameValue;
	private DateField makeTimeValue;
	private SaleReturnEnterDetailViewAdapter adapter;
	private PullToRefreshListView listView;
	
	private SaleReturnEnterService saleReturnEnterService = new SaleReturnEnterService();

	private List<SaleReturnEnterDetailModel> details = new ArrayList<SaleReturnEnterDetailModel>();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private Long billId;
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_enter_bill_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleReturnEnterBillShow_headerview, R.string.sale_return_enter_bill);
	}
	
	@Override
	protected void initActivityView() {
		SaleReturnEnterBillModel bill = (SaleReturnEnterBillModel) getIntent().getSerializableExtra(SaleReturnOrderBillListActivity.SER_KEY);
		billId = bill.getId();
		billCodeValue = (TextView) findViewById(R.id.saleReturnEnterBillShow_billCode_value);
		customerNameValue = (TextView) findViewById(R.id.saleReturnEnterBillShow_customerName_value);
		receiptorNameValue = (TextView) findViewById(R.id.saleReturnEnterBillShow_receiptorName_value);
		makeTimeValue = (DateField) findViewById(R.id.saleReturnEnterBillShow_makeTime_value);
		billCodeValue.setText(bill.getBillCode());
		customerNameValue.setText(bill.getCustomer().getName());
		makeTimeValue.setValue(bill.getMakeTime());
		receiptorNameValue.setText(GlobalUtils.getSessionUser().getEmployeeName());
		listView = (PullToRefreshListView) findViewById(R.id.saleReturnEnterBillShow_details);
		adapter = new SaleReturnEnterDetailViewAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		searchSaleReturnEnterDetails();
    
	}
	
	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				IntentUtils.startActivityBySerialForResult(SaleReturnEnterBillShowActivity.this, 
						SaleReturnEnterDetailShowActivity.class , SER_KEY , 
						details.get(position-1), PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchSaleReturnEnterDetails();
			}
		}));
	}

	private void searchSaleReturnEnterDetails() {
		details.clear();
		saleReturnEnterService.searchSaleReturnEnterDetailsByBillId(billId,
				new AbstractServiceCallBack<List<SaleReturnEnterDetailModel>>(SaleReturnEnterBillShowActivity.this) {

					@Override
					public void doSuccess(List<SaleReturnEnterDetailModel> result) {
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

