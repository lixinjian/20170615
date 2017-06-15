package com.ds365.erp.wms.pda.view.stock;

import com.ds365.commons.utils.IntentUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.stock.pick.activity.PickBillListActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.activity.PutAwayTaskActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.activity.PutawayTaskBillListActivity;
import com.ds365.erp.wms.pda.view.stock.stockshift.activity.StockShiftBillForSingleActivity;
import com.ds365.erp.wms.pda.view.stock.stocktake.activity.StockTakeByGoodsSkuActivity;
import com.ds365.erp.wms.pda.view.stock.stocktake.activity.StockTakeByShelfActivity;
import com.ds365.erp.wms.pda.view.stock.stocktake.activity.StockTakeBySkuActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

/**
 * 库存业务的Fragment
 */
@SuppressLint("NewApi")
public class StockFragment extends BaseFragment implements OnClickListener{
	private RelativeLayout putawayTaskButton;
	private RelativeLayout pickBillButton;
	private RelativeLayout transferStoreButton;
	
	private RelativeLayout stockTakeByShelfButton;
	private RelativeLayout stockTakeBySkuButton;
	private RelativeLayout stockTakeByPlanButton;
	
	// 上下文
	private Context context;
	@Override
	protected void initFragmentRequestNet() {
	}

	@Override
	protected View initFragmentView(LayoutInflater inflater) {
		view=inflater.inflate(R.layout.stock_main_fragment, null);
		context = getActivity();
		putawayTaskButton = (RelativeLayout) view.findViewById(R.id.stock_putawayTaskButton);
		pickBillButton = (RelativeLayout) view.findViewById(R.id.stock_pickBillButton);
		transferStoreButton = (RelativeLayout) view.findViewById(R.id.stock_transferStoreButton);
		
		stockTakeByShelfButton = (RelativeLayout) view.findViewById(R.id.stock_stockTakeByShelf_button);
		stockTakeBySkuButton = (RelativeLayout) view.findViewById(R.id.stock_stockTakeBySku_button);
		stockTakeByPlanButton = (RelativeLayout) view.findViewById(R.id.stock_stockTakeByPlan_button);
		stockTakeByShelfButton.setOnClickListener(this);
		stockTakeBySkuButton.setOnClickListener(this);
		stockTakeByPlanButton.setOnClickListener(this);
		
		putawayTaskButton.setOnClickListener(this);
		pickBillButton.setOnClickListener(this);
		transferStoreButton.setOnClickListener(this);
		return view;
	}

	@Override
	protected void initFragmentChildView(View view) {
		
	}

	@Override
	protected void initFragmentData(Bundle savedInstanceState) {
		
	}

	@Override
	public void onClick(View v) {
		int ID = v.getId();
		switch (ID) {
		case R.id.stock_putawayTaskButton:
//			IntentUtils.startActivity(context, PutAwayTaskActivity.class);
			IntentUtils.startActivity(context, PutawayTaskBillListActivity.class);
			break;
		case R.id.stock_pickBillButton:
			IntentUtils.startActivity(context, PickBillListActivity.class);
			break;
		case R.id.stock_transferStoreButton:
//			IntentUtils.startActivity(context, ShiftStoreMainActivity.class);
			
//			IntentUtils.startActivity(context, StockShiftBillActivity.class);
			IntentUtils.startActivity(context, StockShiftBillForSingleActivity.class);
			break;
			
		case R.id.stock_stockTakeByShelf_button:
			IntentUtils.startActivity(context, StockTakeByShelfActivity.class);
			break;
		case R.id.stock_stockTakeBySku_button:
//			IntentUtils.startActivity(context, StockTakeBySkuActivity.class);
			IntentUtils.startActivity(context, StockTakeByGoodsSkuActivity.class);
			break;
		case R.id.stock_stockTakeByPlan_button:
			break;
		}
	}
}