package com.ds365.erp.wms.pda.view.stock.stockshift.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.commons.utils.IntentUtils;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ShiftStoreMainActivity extends BasePdaListActivity implements OnClickListener{

	private TextView upStoreButton, downStoreButton;
	
	@Override
	protected void initActivityView() {
		upStoreButton = (TextView) findViewById(R.id.shiftStoreMain_upStoreButton);
		downStoreButton = (TextView) findViewById(R.id.shiftStoreMain_downStoreButton);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.shiftStoreMain_headerview, R.string.shift_store_bill);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_shift_store_main;
	}

	@Override
	protected void setListener() {
		upStoreButton.setOnClickListener(this);
		downStoreButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shiftStoreMain_upStoreButton:
			IntentUtils.startActivity(context, ShiftUpStoreListActivity.class);
			break;
		case R.id.shiftStoreMain_downStoreButton:
			IntentUtils.startActivity(context, ShiftDownStoreListActivity.class);
			break;
		}
	}

}
