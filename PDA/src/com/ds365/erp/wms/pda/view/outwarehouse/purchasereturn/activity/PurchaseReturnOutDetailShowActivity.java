package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

/**
 * 
 * 说明 ：采购出库单详情查看 
 */
public class PurchaseReturnOutDetailShowActivity extends BasePdaListActivity{
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_out_detail_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseReturnOutDetailShow_headerview, R.string.purchase_return_out_detail);
	}

	@Override
	protected void setListener() {
	}
	
	@Override
	protected void initActivityView() {
		PurchaseReturnOutDetailModel purchaseReturnOrderDetailModel = (PurchaseReturnOutDetailModel) getIntent()
				.getSerializableExtra(PurchaseReturnOutBillShowActivity.SER_KEY);
		
		TextView unitQtyValue = (TextView) findViewById(R.id.purchaseReturnOutDetailShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.purchaseReturnOutDetailShow_minUnitQty_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.purchaseReturnOutDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.purchaseReturnOutDetailShow_skuName_value);
		TextView minUnitNameValue = (TextView) findViewById(R.id.purchaseReturnOutDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.purchaseReturnOutDetailShow_unitName_value);
		
		unitQtyValue.setText(String.valueOf(purchaseReturnOrderDetailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(purchaseReturnOrderDetailModel.getMinUnitQty()));
		skuCodeValue.setText(purchaseReturnOrderDetailModel.getSku().getCode());
		skuNameValue.setText(purchaseReturnOrderDetailModel.getSku().getName());
		minUnitNameValue.setText(purchaseReturnOrderDetailModel.getMinUnitName());
		unitNameValue.setText(purchaseReturnOrderDetailModel.getUnitName());
	}
}