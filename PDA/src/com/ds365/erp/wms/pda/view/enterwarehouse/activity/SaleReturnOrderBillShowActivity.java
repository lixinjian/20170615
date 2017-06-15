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
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderDetailModel;
import com.ds365.erp.wms.pda.service.sale.SaleReturnOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SaleReturnOrderDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * 退货入库--查看退货申请单详情
 * @author lxj
 *
 */
public class SaleReturnOrderBillShowActivity extends BasePdaListActivity {
	
	private TextView billCodeValue,customerNameValue,receiptorNameValue;
	private DateField makeTimeValue;
	private SaleReturnOrderDetailAdapter adapter;
	private PullToRefreshListView listView;
	
	private SaleReturnOrderService saleReturnOrderService = new SaleReturnOrderService();
	
	private List<SaleReturnOrderDetailModel> details = new ArrayList<SaleReturnOrderDetailModel>();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private Long billId;
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_order_bill_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleReturnOrderBillShow_headerview, R.string.sale_return_order_bill);
	}
	
	@Override
	protected void initActivityView() {
		
		SaleReturnOrderBillModel bill = (SaleReturnOrderBillModel) getIntent().getSerializableExtra(SaleReturnOrderBillListActivity.SER_KEY);
		billId = bill.getId();
		billCodeValue = (TextView) findViewById(R.id.saleReturnOrderBillShow_billCode_value);
		customerNameValue = (TextView) findViewById(R.id.saleReturnOrderBillShow_customerName_value);
		receiptorNameValue = (TextView) findViewById(R.id.saleReturnOrderBillShow_receiptorName_value);
		makeTimeValue = (DateField) findViewById(R.id.saleReturnOrderBillShow_makeTime_value);
		billCodeValue.setText(bill.getBillCode());
		customerNameValue.setText(bill.getCustomer().getName());
		makeTimeValue.setValue(bill.getMakeTime());
		receiptorNameValue.setText(GlobalUtils.getSessionUser().getEmployeeName());
		
		listView = (PullToRefreshListView) findViewById(R.id.saleReturnOrderBillShow_details);
		adapter = new SaleReturnOrderDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		searchSaleOrderDetail();
	}
	
	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityBySerialForResult(SaleReturnOrderBillShowActivity.this
						, SaleReturnOrderDetailShowActivity.class, SER_KEY
						, details.get(position-1), PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchSaleOrderDetail();
			}
		}));
	}

	private void searchSaleOrderDetail() {
		details.clear();
		saleReturnOrderService.searchSaleOrderDetailByBillId(billId,
				new AbstractServiceCallBack<List<SaleReturnOrderDetailModel>>(SaleReturnOrderBillShowActivity.this) {

					@Override
					public void doSuccess(List<SaleReturnOrderDetailModel> result) {
						if (result != null && result.size() > 0) {
							details.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						adapter.notifyDataSetInvalidated();
					}
				});
	}
}

