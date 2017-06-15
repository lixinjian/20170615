package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PutawayTaskDetailShowActivity extends BasePdaActivity {

	@Override
	protected void initActivityView() {
		TextView specValue = (TextView) findViewById(R.id.putawayTaskDetailShow_spec_value);
		TextView specQtyValue = (TextView) findViewById(R.id.putawayTaskDetailShow_specQty_value);
		TextView qtyValue = (TextView) findViewById(R.id.putawayTaskDetailShow_totalCount_value);
		TextView skuNameValue = (TextView) findViewById(R.id.putawayTaskDetailShow_skuName_value);
		TextView shelfCodeValue = (TextView) findViewById(R.id.putawayTaskDetailShow_shelfCode_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.putawayTaskDetailShow_skuCode_value);
		TextView storeNameValue = (TextView) findViewById(R.id.putawayTaskDetailShow_storeName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.putawayTaskDetailShow_unitName_value);
		TextView relatedBillCodeValue = (TextView) findViewById(R.id.putawayTaskDetailShow_relatedBillCode_value);
		TextView billCodeValue = (TextView) findViewById(R.id.putawayTaskDetailShow_billCode_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.putawayTaskDetailShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.putawayTaskDetailShow_minUnitQty_value);
		DateField updateTime = (DateField) findViewById(R.id.putawayTaskDetailShow_updateTime_value);
		
		PutawayTaskDetailModel putawayTaskDetailModel = null;
		Bundle bundle = getIntent().getExtras();
		if (PutawayTaskBillShowActivity.REQUEST_CODE.equals(bundle.getString(PutawayTaskBillShowActivity.REQUEST_CODE_KEY))) {
			putawayTaskDetailModel = (PutawayTaskDetailModel) getIntent()
					.getSerializableExtra(PutawayTaskBillShowActivity.SER_KEY);
		}
		if(PutawayTaskDetailActivity.REQUEST_CODE.equals(bundle.getString(PutawayTaskDetailActivity.REQUEST_CODE_KEY))){
			putawayTaskDetailModel = (PutawayTaskDetailModel) getIntent()
					.getSerializableExtra(PutawayTaskDetailActivity.SER_KEY);
		}

		qtyValue.setText(String.valueOf(putawayTaskDetailModel.getQty()));
		skuNameValue.setText(putawayTaskDetailModel.getSku().getGoods().getName());
		shelfCodeValue.setText(putawayTaskDetailModel.getShelf().getCode());
		skuCodeValue.setText(putawayTaskDetailModel.getSku().getCode());
		storeNameValue.setText(putawayTaskDetailModel.getStore().getName());
		unitNameValue.setText(putawayTaskDetailModel.getUnitName());
		relatedBillCodeValue.setText(putawayTaskDetailModel.getBill().getRelatedBillCode());
		billCodeValue.setText(putawayTaskDetailModel.getBill().getBillCode());
		specValue.setText(putawayTaskDetailModel.getSku().getSpec());
		specQtyValue.setText(String.valueOf(putawayTaskDetailModel.getSpecQty()));
		unitQtyValue.setText(String.valueOf(putawayTaskDetailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(putawayTaskDetailModel.getMinUnitQty()));
		updateTime.setValue(putawayTaskDetailModel.getUpdateTime());
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawayTaskDetailShow_headerview, R.string.putaway_task_detail_view);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_detail_show;
	}

	@Override
	protected void setListener() {

	}

}
