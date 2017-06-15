package com.ds365.erp.wms.pda.view.common.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.sale.SaleOrderBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleOrderDetailModel;
import com.ds365.erp.wms.pda.service.sale.SaleOrderService;
import com.ds365.erp.wms.pda.view.common.adapter.SaleOrderDetailAdapter;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity.DeliverBillShowActivity;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity.ShipmentOrderBillShowActivity;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity.ShipmentOutBillActivity;
import com.ds365.erp.wms.pda.view.query.activity.ShipmentBillShowActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class SaleOrderBillShowActivity extends BasePdaListActivity {

	private TextView billCodeValue, customerNameValue, billMoneyValue, receivableMoney/*应收金额*/;
	private DateField makeTimeValue;
	private SaleOrderBillModel saleOrderBillModel;
	private List<SaleOrderDetailModel> details = new ArrayList<SaleOrderDetailModel>();
	private SaleOrderDetailAdapter adapter;
	private PullToRefreshListView listView;
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private SaleOrderService saleOrderService = new SaleOrderService();
	
	@Override
	protected void initActivityView() {
		listView = (PullToRefreshListView) findViewById(R.id.commonSaleOrderBillShow_details);
		billCodeValue = (TextView) findViewById(R.id.commonSaleOrderBillShow_billCode_value);
		makeTimeValue = (DateField) findViewById(R.id.commonSaleOrderBillShow_makeTime_value);
		customerNameValue = (TextView) findViewById(R.id.commonSaleOrderBillShow_customerName_value);
		billMoneyValue = (TextView) findViewById(R.id.commonSaleOrderBillShow_billMoney_value);
		receivableMoney = (TextView) findViewById(R.id.commonSaleOrderBillShow_receivableMoney_value);
		
		Bundle bundle = getIntent().getExtras();
		if (ShipmentOutBillActivity.REQUEST_CODE.equals(bundle.getString(ShipmentOutBillActivity.REQUEST_CODE_KEY))) {
			saleOrderBillModel = (SaleOrderBillModel) getIntent().getSerializableExtra(ShipmentOutBillActivity.SER_KEY);
		}else if (ShipmentOrderBillShowActivity.REQUEST_CODE.equals(bundle.getString(ShipmentOrderBillShowActivity.REQUEST_CODE_KEY))){
			saleOrderBillModel = (SaleOrderBillModel) getIntent().getSerializableExtra(ShipmentOrderBillShowActivity.SER_KEY);
		}else if (DeliverBillShowActivity.REQUEST_CODE.equals(bundle.getString(DeliverBillShowActivity.REQUEST_CODE_KEY))){
			saleOrderBillModel = (SaleOrderBillModel) getIntent().getSerializableExtra(DeliverBillShowActivity.SER_KEY);
		}else if (ShipmentBillShowActivity.REQUEST_CODE.equals(bundle.getString(ShipmentBillShowActivity.REQUEST_CODE_KEY))){
			saleOrderBillModel = (SaleOrderBillModel) getIntent().getSerializableExtra(ShipmentBillShowActivity.SER_KEY);
		}
		billCodeValue.setText(saleOrderBillModel.getBillCode());
		makeTimeValue.setValue(saleOrderBillModel.getMakeTime());
		customerNameValue.setText(saleOrderBillModel.getCustomer().getName());
		billMoneyValue.setText(String.valueOf(saleOrderBillModel.getBillMoney()));
		receivableMoney.setText(String.valueOf(saleOrderBillModel.getReceivableMoney()));
		adapter = new SaleOrderDetailAdapter(context, details);
		listView.setAdapter(adapter);
		
		searchSaleOrderDetail();
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.commonSaleOrderBillShow_headerview, R.string.sale_order_bill);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.common_sale_order_bill_show;
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		        IntentUtils.startActivityForSeria(context, SaleOrderDetailShowActivity.class
		        		, SER_KEY, details.get(position-1), null);
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
		saleOrderService.searchSaleOrderDetailByBillId(saleOrderBillModel.getId(), new AbstractServiceCallBack<List<SaleOrderDetailModel>>(context) {
			
			@Override
			public void doSuccess(List<SaleOrderDetailModel> result) {
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
