package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

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
		return R.layout.enterwarehouse_purchase_enter_detail_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseEnterDetailShow_headerview, R.string.purchase_enter_detail);
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
		TextView billMoneyValue = (TextView) findViewById(R.id.purchaseEnterDetailShow_billMoney_value);
		
		PurchaseEnterDetailModel purchaseEnterDetail=(PurchaseEnterDetailModel)getIntent().getSerializableExtra(
				PurchaseEnterBillShowActivity.SER_KEY);
		
		storeNameValue.setText(purchaseEnterDetail.getStore().getName());
		shelfCodeValue.setText(purchaseEnterDetail.getShelf().getCode());
		produceDateValue.setValue(purchaseEnterDetail.getProduceDate());
		produceBatchNoValue.setText(purchaseEnterDetail.getProduceBatchNo());
		sysBatchNoValue.setText(purchaseEnterDetail.getSysBatchNo());
		unitQtyValue.setText(String.valueOf(purchaseEnterDetail.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(purchaseEnterDetail.getMinUnitQty()));
		minUnitNameValue.setText(purchaseEnterDetail.getMinUnitName());
		unitNameValue.setText(purchaseEnterDetail.getUnitName());
		skuCodeValue.setText(purchaseEnterDetail.getSku().getCode());
		skuNameValue.setText(purchaseEnterDetail.getSku().getName());
		qtyValue.setText(String.valueOf(purchaseEnterDetail.getQty()));
		billMoneyValue.setText(String.valueOf(purchaseEnterDetail.getMoney()));
	}
    
	@Override
	protected void setListener() {
	}
}
