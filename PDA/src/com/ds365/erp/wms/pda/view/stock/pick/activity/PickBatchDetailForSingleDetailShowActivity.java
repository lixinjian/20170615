package com.ds365.erp.wms.pda.view.stock.pick.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

public class PickBatchDetailForSingleDetailShowActivity extends BasePdaListActivity{
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_batch_detail_for_single_detail_view;
	}
	
	@Override
	protected void initActivityView() {
		PickBatchDetailModel pickBatchDetailModel = (PickBatchDetailModel) getIntent().getSerializableExtra(PickBillRegistForSingleActivity.SER_KEY);
		
		TextView skuCodeValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_skuCode_value);
		TextView shelfCodeValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_shelfCode_value);
		TextView sysBatchNoValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_sysBatchNo_value);
		TextView skuNameValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_skuName_value);
		TextView storeNameValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_storeName_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_minUnitQty_value);
		TextView qtyValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_qty_value);
		TextView unitNameValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_unitName_value);
		TextView expectUnitQtyValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_expectUnitQty_value);
		TextView expectMinUnitQtyValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_expectMinUnitQty_value);
		TextView expectQtyValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_expectQty_value);
		TextView specValue = (TextView) findViewById(R.id.pickBatchDetailForSingleDetailShow_spec_value);
		skuCodeValue.setText(pickBatchDetailModel.getSku().getCode());
		shelfCodeValue.setText(pickBatchDetailModel.getShelf().getCode());
		sysBatchNoValue.setText(pickBatchDetailModel.getSysBatchNo());
		skuNameValue.setText(pickBatchDetailModel.getSku().getGoods().getName());
		storeNameValue.setText(pickBatchDetailModel.getStore().getName());
		unitNameValue.setText(pickBatchDetailModel.getUnitName());
		expectUnitQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectUnitQty()));
		expectMinUnitQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectMinUnitQty()));
		expectQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectQty()));
		unitQtyValue.setText(String.valueOf(pickBatchDetailModel.getPickUnitQty()));
		minUnitQtyValue.setText(String.valueOf(pickBatchDetailModel.getPickMinUnitQty()));
		specValue.setText(pickBatchDetailModel.getSpec());
		qtyValue.setText(String.valueOf(pickBatchDetailModel.getPickQty()));
	}
	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBatchDetailForSingleDetailShow_headerView, R.string.goods_batch_detail);
	}

	@Override
	protected void setListener() {
		
	}
}
