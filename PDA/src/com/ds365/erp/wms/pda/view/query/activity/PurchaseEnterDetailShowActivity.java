package com.ds365.erp.wms.pda.view.query.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

/**
 * 
 * 说明 ：采购入库单详情查看 
 */
public class PurchaseEnterDetailShowActivity extends BasePdaListActivity {
	
	@Override
	protected int getContentViewId() {
		return R.layout.query_purchase_enter_detail_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseEnterDetailShow_headerview, R.string.query_purchase_enter_detail);
	}

	@Override
	protected void initActivityView() {
		
		TextView minUnitNameValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_unitName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_skuName_value);
		TextView storeNameValue = 	(TextView) findViewById(R.id.purchaseEnterDetailShow_storeName_value);	
		TextView shelfCodeValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_shelfCode_value);
		DateField produceDateValue = (DateField) findViewById(R.id.purchaseEnterDetailShow_produceDate_value);
		TextView produceBatchNoValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_produceBatchNo_value);
		TextView sysBatchNoValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_sysBatchNo_value);
		TextView qtyValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_qty_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_minUnitQty_value);
		TextView billMoney = (TextView) findViewById(R.id.purchaseEnterDetailShow_billMoney_value);
		
		PurchaseEnterDetailModel purchaseEnterDetailModel=(PurchaseEnterDetailModel)getIntent().getSerializableExtra(
				PurchaseEnterBillShowActivity.SER_KEY);
		
		storeNameValue.setText(purchaseEnterDetailModel.getStore().getName());
		shelfCodeValue.setText(purchaseEnterDetailModel.getShelf().getCode());
		produceDateValue.setValue(purchaseEnterDetailModel.getProduceDate());
		produceBatchNoValue.setText(purchaseEnterDetailModel.getProduceBatchNo());
		sysBatchNoValue.setText(purchaseEnterDetailModel.getSysBatchNo());
		unitQtyValue.setText(String.valueOf(purchaseEnterDetailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(purchaseEnterDetailModel.getMinUnitQty()));
		minUnitNameValue.setText(purchaseEnterDetailModel.getMinUnitName());
		unitNameValue.setText(purchaseEnterDetailModel.getUnitName());
		skuCodeValue.setText(purchaseEnterDetailModel.getSku().getCode());
		skuNameValue.setText(purchaseEnterDetailModel.getSku().getName());
		qtyValue.setText(String.valueOf(purchaseEnterDetailModel.getQty()));
		billMoney.setText(String.valueOf(purchaseEnterDetailModel.getMoney()));
	}
    
	@Override
	protected void setListener() {
	}
}
