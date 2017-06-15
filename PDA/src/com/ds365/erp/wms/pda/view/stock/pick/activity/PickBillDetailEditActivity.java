package com.ds365.erp.wms.pda.view.stock.pick.activity;

import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PickBillDetailEditActivity extends BasePdaListActivity {
	private TextView saveButton;
	private TextView shelfCodeValue;					//货位号
	private TextView skuCodeValue;					//sku批次
	private IntEditField pickUnitQtyValue;			//实捡件数
	private IntEditField pickMinUnitQtyValue;		//实捡散数
	private TextView expectMinUnitQtyValue;		//应捡散数
	private TextView expectUnitQtyValue;			//应捡件数
	private Intent intent;

	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	@Override
	protected void initActivityView() {
		
		intent = getIntent();
		
		shelfCodeValue = (TextView) findViewById(R.id.pickBillDetailEdit_shelfCode_value);
		saveButton = (TextView) findViewById(R.id.pickBillDetailEdit_saveButton);
		skuCodeValue = (TextView) findViewById(R.id.pickBillDetailEdit_skuBatch_value);
		pickUnitQtyValue = (IntEditField) findViewById(R.id.pickBillDetailEdit_pickUnitQty_value);
		pickMinUnitQtyValue = (IntEditField) findViewById(R.id.pickBillDetailEdit_pickMinUnitQty_value);
		expectMinUnitQtyValue = (TextView) findViewById(R.id.pickBillDetailEdit_expectMinUnitQty_value);
		expectUnitQtyValue = (TextView) findViewById(R.id.pickBillDetailEdit_expectUnitQty_value);
		
		shelfCodeValue.setText(intent.getExtras().getString("shelfCode"));
		skuCodeValue.setText(intent.getExtras().getString("skuCode"));
		pickUnitQtyValue.setValue(intent.getExtras().getInt("pickUnitQty"));
		pickMinUnitQtyValue.setValue(intent.getExtras().getInt("pickMinUnitQty"));
		expectMinUnitQtyValue.setText(String.valueOf(intent.getExtras().getInt("expectMinUnitQty")));
		expectUnitQtyValue.setText(String.valueOf(intent.getExtras().getInt("expectUnitQty")));
		
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBillDetailEdit_headerview, R.string.goods_batch_detail);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_bill_detail_edit;
	}

	@Override
	protected void setListener() {
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int pickUnitQty = pickUnitQtyValue.getValue();
				int pickMinUnitQty = pickMinUnitQtyValue.getValue();
				
				intent.putExtra("pickUnitQty", pickUnitQty);
				intent.putExtra("pickMinUnitQty", pickMinUnitQty);
				setResult(RESULT_CODE, intent);
				finish();
			}
		});
	}
}
