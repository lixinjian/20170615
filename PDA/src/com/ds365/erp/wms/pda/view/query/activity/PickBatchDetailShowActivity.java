package com.ds365.erp.wms.pda.view.query.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;

import android.widget.TextView;

public class PickBatchDetailShowActivity extends BasePdaActivity {

	@Override
	protected void initActivityView() {
		
		PickBatchDetailModel detailModel = (PickBatchDetailModel) getIntent().getSerializableExtra(PickBillShowActivity.SER_KEY);
		TextView skuNameValue = (TextView) findViewById(R.id.pickBatchDetailShow_skuName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.pickBatchDetailShow_skuCode_value);
		TextView specValue = (TextView) findViewById(R.id.pickBatchDetailShow_spec_value);
		TextView unitNameValue = (TextView) findViewById(R.id.pickBatchDetailShow_unitName_value);
		TextView storeNameValue = (TextView) findViewById(R.id.pickBatchDetailShow_storeName_value);
		TextView shelfCodeValue = (TextView) findViewById(R.id.pickBatchDetailShow_shelfCode_value);
		TextView sysBatchNoValue = (TextView) findViewById(R.id.pickBatchDetailShow_sysBatchNo_value);
		DateField produceDateValue = (DateField) findViewById(R.id.pickBatchDetailShow_produceDate_value);
		TextView expectQtyValue = (TextView) findViewById(R.id.pickBatchDetailShow_expectQty_value);
		TextView specQtyValue = (TextView) findViewById(R.id.pickBatchDetailShow_specQty_value);
		TextView pickQtyValue = (TextView) findViewById(R.id.pickBatchDetailShow_pickQty_value);

		skuNameValue.setText(detailModel.getSku().getName());
		skuCodeValue.setText(detailModel.getSku().getCode());
		specValue.setText(detailModel.getSpec());
		unitNameValue.setText(detailModel.getUnitName());
		storeNameValue.setText(detailModel.getStore().getName());
		shelfCodeValue.setText(detailModel.getShelf().getCode());
		sysBatchNoValue.setText(detailModel.getSysBatchNo());
//		produceDateValue.setValue(detailModel.getProduceDate());	
		expectQtyValue.setText(String.valueOf(detailModel.getExpectQty()));
		specQtyValue.setText(String.valueOf(detailModel.getSpecQty()));
		pickQtyValue.setText(String.valueOf(detailModel.getPickQty()));
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBatchDetailShow_headerview, R.string.pick_batch_detail_view);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_pick_batch_detail_show;
	}

	@Override
	protected void setListener() {

	}
}
