package com.ds365.erp.wms.pda.view.stock.stockshift.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class ShiftUpStoreListActivity extends BasePdaPageActivity {

	private EditText upStoreBillValue;
	private ImageView upStoreBillSearchButton;
	
	@Override
	protected void initActivityView() {
		upStoreBillValue = (EditText)findViewById(R.id.shiftUpStoreList_upStoreBill_value);
		upStoreBillSearchButton = (ImageView)findViewById(R.id.shiftUpStoreList_upStoreBill_searchButton);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.shiftUpStoreList_headerview, R.string.shift_up_store_bill);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_shift_up_store_list;
	}

	@Override
	protected void setListener() {
		upStoreBillSearchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String upStoreBill = upStoreBillValue.getText().toString().trim();
//				params.getParams().put("", upStoreBill);
//				params.setUrl(ConstantUrl.BASE_URL);
			}
		});
	}

}
