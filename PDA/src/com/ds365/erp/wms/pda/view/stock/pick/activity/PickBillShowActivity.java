package com.ds365.erp.wms.pda.view.stock.pick.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillModel;
import com.ds365.erp.wms.pda.service.pick.PickService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBatchDetailViewAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * 拣货下架 - 拣货单明细 查看
 * 
 * @author lxj
 *
 */
public class PickBillShowActivity extends BasePdaListActivity {
	private List<PickBatchDetailModel> details = new ArrayList<PickBatchDetailModel>();
	
	private DateField makeTimeValue;//拣货单订单日期
	private TextView billCodeValue, pickerNameValue;
	private PullToRefreshListView listView;
	private PickBatchDetailViewAdapter adapter;
	private PickBillModel bill;
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private PickService pickService = new PickService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_bill_show;
	}
	
	@Override
	protected void initActivityView() {
		pickerNameValue = (TextView) findViewById(R.id.pickBillShow_pickerName_value);
		billCodeValue = (TextView) findViewById(R.id.pickBillShow_billCode_value);
		makeTimeValue=(DateField)findViewById(R.id.pickBillShow_makeTime_value);
		listView = (PullToRefreshListView) findViewById(R.id.pickBillShow_details);
		bill= (PickBillModel)getIntent().getExtras().getSerializable(PickBillListActivity.SER_KEY);
		makeTimeValue.setValue(bill.getMakeTime());
		billCodeValue.setText(bill.getBillCode());
		if(bill.getPicker() != null)
			pickerNameValue.setText(bill.getPicker().getName());
		adapter=new PickBatchDetailViewAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		searchPickBatchDetail();
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBillShow_headerview, R.string.title_pick_bill_view);
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context, PickBatchDetailShowActivity.class);
				Bundle  bundle=new Bundle();
				bundle.putSerializable(SER_KEY, details.get(position-1));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchPickBatchDetail();
			}
		}));
	}
	
	private void searchPickBatchDetail() {
		details.clear();
		pickService.searchPickBatchDetailByBillId(bill.getId(), new AbstractServiceCallBack<List<PickBatchDetailModel>>(context) {
			
			@Override
			public void doSuccess(List<PickBatchDetailModel> result) {
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
