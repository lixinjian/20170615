package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.content.Intent;
import android.widget.TextView;
/**
 * 收货入库-订单详情-查看商品详情
 *
 */
public class PurchaseOrderDetailShowActivity extends BasePdaListActivity {
	
	
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_purchase_order_detail_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseOrderDetailShow_headerview, R.string.purchase_order_detail);
	}

	@Override
	protected void initActivityView() {
		
		Intent intent = getIntent();
		
		TextView minUnitNameValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_unitName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_skuName_value);
		DateField produceDateValue = (DateField) findViewById(R.id.purchaseOrderDetailShow_produceDate_value);
		TextView produceBatchNoValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_produceBatchNo_value);
		TextView qtyValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_qty_value);
		TextView remainQtyValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_remainQty_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_minUnitQty_value);
		TextView remainUnitQtyValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_remainUnitQty_value);
		TextView remainMinUnitQtyValue = (TextView) findViewById(R.id.purchaseOrderDetailShow_remainMinUnitQty_value);
		PurchaseOrderDetailModel purchaseOrderDetailModel=(PurchaseOrderDetailModel)intent.getSerializableExtra(PurchaseOrderBillShowActivity.SER_KEY);
		
		produceDateValue.setValue(purchaseOrderDetailModel.getProduceDate());
		produceBatchNoValue.setText(purchaseOrderDetailModel.getProduceBatchNo());
		unitQtyValue.setText(String.valueOf(purchaseOrderDetailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(purchaseOrderDetailModel.getMinUnitQty()));
		minUnitNameValue.setText(purchaseOrderDetailModel.getMinUnitName());
		unitNameValue.setText(purchaseOrderDetailModel.getUnitName());
		skuCodeValue.setText(purchaseOrderDetailModel.getSku().getCode());
		skuNameValue.setText(purchaseOrderDetailModel.getSku().getName());
		qtyValue.setText(String.valueOf(purchaseOrderDetailModel.getQty()));
		remainQtyValue.setText(String.valueOf(purchaseOrderDetailModel.getRemainQty()));
		remainUnitQtyValue.setText(String.valueOf(purchaseOrderDetailModel.getRemainUnitQty()));
		remainMinUnitQtyValue.setText(String.valueOf(purchaseOrderDetailModel.getRemainMinUnitQty()));
	}
    
	@Override
	protected void setListener() {
	}
}
