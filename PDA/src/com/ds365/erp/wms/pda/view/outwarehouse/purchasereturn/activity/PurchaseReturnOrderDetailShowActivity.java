package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

public class PurchaseReturnOrderDetailShowActivity extends BasePdaListActivity{
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_order_detail_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseReturnOrderDetailShow_headerview, R.string.purchase_return_order_detail);
	}

	@Override
	protected void setListener() {
	}
	
	@Override
	protected void initActivityView() {
		TextView storeNameValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_storeName_value);
		TextView shelfCodeValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_shelfCode_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_minUnitQty_value);
		
		TextView skuCodeValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_skuName_value);
		TextView minUnitNameValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_unitName_value);
		DateField produceDateValue = (DateField) findViewById(R.id.purchaseReturnOrderDetailShow_produceDate_value);
		TextView produceBatchNoValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_produceBatchNo_value);
		TextView sysBatchNoValue = (TextView) findViewById(R.id.purchaseReturnOrderDetailShow_sysBatchNo_value);
		PurchaseReturnOrderDetailModel purchaseReturnOrderDetailModel = (PurchaseReturnOrderDetailModel) getIntent().getSerializableExtra(PurchaseReturnOrderBillShowActivity.SER_KEY);
		
		unitQtyValue.setText(String.valueOf(purchaseReturnOrderDetailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(purchaseReturnOrderDetailModel.getMinUnitQty()));
		skuCodeValue.setText(purchaseReturnOrderDetailModel.getSku().getCode());
		skuNameValue.setText(purchaseReturnOrderDetailModel.getSku().getName());
		minUnitNameValue.setText(purchaseReturnOrderDetailModel.getMinUnitName());
		unitNameValue.setText(purchaseReturnOrderDetailModel.getUnitName());
		storeNameValue.setText(purchaseReturnOrderDetailModel.getStore().getName());
		shelfCodeValue.setText(purchaseReturnOrderDetailModel.getShelf().getCode());
		produceDateValue.setValue(purchaseReturnOrderDetailModel.getProduceDate());
		produceBatchNoValue.setText(purchaseReturnOrderDetailModel.getProduceBatchNo());
		sysBatchNoValue.setText(purchaseReturnOrderDetailModel.getSysBatchNo());
	}
}