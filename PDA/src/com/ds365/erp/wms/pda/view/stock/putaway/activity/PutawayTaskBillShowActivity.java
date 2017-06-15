package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskBillModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskBillService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PuawayTaskDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class PutawayTaskBillShowActivity extends BasePdaListActivity {

	private PullToRefreshListView listView;
	private TextView putterNameValue, billCodeValue, relatedBillCodeValue;
	private TextView itemCountValue, unitQtyValue, minUnitQtyValue;
	private PuawayTaskDetailAdapter adapter;
	private PutawayTaskBillModel bill;
	public static final String SER_KEY = PdaConstants.nextSerKey();
	private List<PutawayTaskDetailModel> details = new ArrayList<PutawayTaskDetailModel>();
	
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	
	public static final String REQUEST_CODE_KEY = PutawayTaskBillShowActivity.class.getName();

	private PutawayTaskBillService putawayTaskBillService = new PutawayTaskBillService();
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawayTaskBillShow_headerview, R.string.putaway_task_detail);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_bill_show;
	}

	@Override
	protected void initActivityView() {
		itemCountValue = (TextView) findViewById(R.id.putawayTaskBillShow_itemCount_value);
		unitQtyValue = (TextView) findViewById(R.id.putawayTaskBillShow_unitQty_value);
		minUnitQtyValue = (TextView) findViewById(R.id.putawayTaskBillShow_minUnitQty_value);
		listView=(PullToRefreshListView) findViewById(R.id.putawayTaskBillShow_details);
		billCodeValue = (TextView) findViewById(R.id.putawayTaskBillShow_billCode_value);
		relatedBillCodeValue = (TextView) findViewById(R.id.putawayTaskBillShow_relatedBillCode_value);
		putterNameValue =  (TextView) findViewById(R.id.putawayTaskBillShow_putterName_Value);
		bill= (PutawayTaskBillModel)getIntent().getExtras().getSerializable(PutawayTaskBillListActivity.SER_KEY);
		putterNameValue.setText(bill.getPutter().getName());
		billCodeValue.setText(bill.getBillCode());
		relatedBillCodeValue.setText(bill.getRelatedBillCode());
		itemCountValue.setText(String.valueOf(bill.getItemCount()));
		unitQtyValue.setText(String.valueOf(bill.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(bill.getMinUnitQty()));
		adapter = new PuawayTaskDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		searchPutawayTaskDetail();
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context, PutawayTaskDetailShowActivity.class);
				Bundle  bundle=new Bundle();
				bundle.putSerializable(SER_KEY, details.get(position-1));
				intent.putExtras(bundle);
				intent.putExtra(REQUEST_CODE_KEY, REQUEST_CODE);
				startActivity(intent);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchPutawayTaskDetail();
			}
		}));
	}

	private void searchPutawayTaskDetail() {
		details.clear();
		putawayTaskBillService.searchPutawayTaskDetailByBillId(bill.getId(),
				new AbstractServiceCallBack<List<PutawayTaskDetailModel>>(context) {

					@Override
					public void doSuccess(List<PutawayTaskDetailModel> result) {
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
