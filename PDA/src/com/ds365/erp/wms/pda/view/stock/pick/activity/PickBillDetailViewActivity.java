package com.ds365.erp.wms.pda.view.stock.pick.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.content.Intent;
import android.widget.TextView;

public class PickBillDetailViewActivity extends BasePdaListActivity {

	private TextView shelfCodeValue;					//货位号
	private TextView skuCodeValue;					//sku批次
	private TextView pickUnitQtyValue;			//实捡件数
	private TextView pickMinUnitQtyValue;		//实捡散数
	private TextView expectMinUnitQtyValue;		//应捡散数
	private TextView expectUnitQtyValue;			//应捡件数

	@Override
	protected void initActivityView() {
		Intent intent = getIntent();
		shelfCodeValue = (TextView) findViewById(R.id.pickBillDetailInfo_shelfCode_value);
		skuCodeValue = (TextView) findViewById(R.id.pickBillDetailInfo_skuCode_value);
		pickUnitQtyValue = (TextView) findViewById(R.id.pickBillDetailInfo_pickUnitQty_value);
		pickMinUnitQtyValue = (TextView) findViewById(R.id.pickBillDetailInfo_pickMinUnitQty_value);
		expectMinUnitQtyValue = (TextView) findViewById(R.id.pickBillDetailInfo_expectMinUnitQty_value);
		expectUnitQtyValue = (TextView) findViewById(R.id.pickBillDetailInfo_expectUnitQty_value);
		
		shelfCodeValue.setText(intent.getExtras().getString("shelfCode"));
		skuCodeValue.setText(intent.getExtras().getString("skuCode"));
		pickUnitQtyValue.setText(String.valueOf(intent.getExtras().getInt("pickUnitQty")));
		pickMinUnitQtyValue.setText(String.valueOf(intent.getExtras().getInt("pickMinUnitQty")));
		expectMinUnitQtyValue.setText(String.valueOf(intent.getExtras().getInt("expectMinUnitQty")));
		expectUnitQtyValue.setText(String.valueOf(intent.getExtras().getInt("expectUnitQty")));
		
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBillDetailInfo_headerview, R.string.goods_batch_detail);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_bill_detail_info;
	}

	@Override
	protected void setListener() {
		
	}
}
