package com.ds365.erp.wms.pda.view.query.activity;

import android.widget.TextView;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.stock.SkuStoreStockModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

public class SkuStoreStockViewActivity extends BasePdaListActivity {

	@Override
	protected void initActivityView() {

		TextView skuNameValue = (TextView) findViewById(R.id.skuStoreStockShow_skuName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.skuStoreStockShow_skuCode_value);
		TextView storeNameValue = (TextView) findViewById(R.id.skuStoreStockShow_storeName_value);
		//DateField produceDateValue = (DateField) findViewById(R.id.skuStoreStockShow_produceDate_value);
		TextView stockQtyValue = (TextView) findViewById(R.id.skuStoreStockShow_stockQty_value);
		TextView unitNameValue = (TextView) findViewById(R.id.skuStoreStockShow_unitName_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.skuStoreStockShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.skuStoreStockShow_minUnitQty_value);
		
		SkuStoreStockModel skuStoreStock = (SkuStoreStockModel) getIntent().getSerializableExtra(SkuStoreStockListActivity.SER_KEY);
		
		skuNameValue.setText(skuStoreStock.getSku().getName());
		skuCodeValue.setText(skuStoreStock.getSku().getCode());
		storeNameValue.setText(skuStoreStock.getStore().getName());
		//produceDateValue.setValue(skuStoreStock.getProduceDate());
		stockQtyValue.setText(String.valueOf(skuStoreStock.getQty()));
		unitNameValue.setText(skuStoreStock.getSku().getGoodsPack().getUnitName());
		unitQtyValue.setText(String.valueOf(skuStoreStock.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(skuStoreStock.getMinUnitQty()));
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuStoreStockShow_headerView, R.string.store_stock_query_detail);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_store_stock_view;
	}

	@Override
	protected void setListener() {

	}

}
