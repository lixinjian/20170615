package com.ds365.erp.wms.pda.view.stock.stockshift.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;

public class ShiftDownStoreListActivity extends BasePdaPageActivity {

	@Override
	protected void initActivityView() {

	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.shiftDownStoreList_headerview, R.string.shift_down_store_bill);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_shift_down_store_list;
	}

	@Override
	protected void setListener() {

	}

}
