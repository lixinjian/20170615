package com.ds365.erp.wms.pda.view.query.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SkuShelfBatchStockShowActivity extends BasePdaListActivity {

	private SkuShelfBatchStockModel skuShelfBatchStock;

	@Override
	protected void initActivityView() {

		TextView skuNameValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_skuName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_skuCode_value);
		TextView sysBatchNoValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_sysBatchNo_value);
		TextView shelfCodeValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_shelfCode_value);
		TextView storeNameValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_storeName_value);
		DateField produceDateValue = (DateField) findViewById(R.id.skuShelfBatchStockShow_produceDate_value);
		TextView stockQtyValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_stockQty_value);
		TextView specValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_spec_value);
		TextView unitNameValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_unitName_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_minUnitQty_value);
		TextView usableQtyValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_usableQty_value);
		TextView usableUnitQtyValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_usableUnitQty_value);
		TextView usableMinUnitQtyValue = (TextView) findViewById(R.id.skuShelfBatchStockShow_usableMinUnitQty_value);
		
		Bundle bundle = getIntent().getExtras();
		if (SkuShelfBatchStockBySkuIdWarehouseIdSaleTypeActivity.REQUEST_CODE.equals(bundle.getString(SkuShelfBatchStockBySkuIdWarehouseIdSaleTypeActivity.REQUEST_CODE_KEY))) {
			skuShelfBatchStock = (SkuShelfBatchStockModel) getIntent().getSerializableExtra(SkuShelfBatchStockBySkuIdWarehouseIdSaleTypeActivity.SER_KEY);
		}else{
			skuShelfBatchStock = (SkuShelfBatchStockModel) getIntent().getSerializableExtra(SkuShelfBatchStockListActivity.SER_KEY);
		}
		skuNameValue.setText(skuShelfBatchStock.getSku().getName());
		skuCodeValue.setText(skuShelfBatchStock.getSku().getCode());
		sysBatchNoValue.setText(skuShelfBatchStock.getSysBatchNo());
		shelfCodeValue.setText(skuShelfBatchStock.getShelf().getCode());
		storeNameValue.setText(skuShelfBatchStock.getStore().getName());
		produceDateValue.setValue(skuShelfBatchStock.getProduceDate());
		stockQtyValue.setText(String.valueOf(skuShelfBatchStock.getQty()));
		specValue.setText(skuShelfBatchStock.getSku().getSpec());
		unitNameValue.setText(skuShelfBatchStock.getSku().getGoodsPack().getUnitName());
		unitQtyValue.setText(String.valueOf(skuShelfBatchStock.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(skuShelfBatchStock.getMinUnitQty()));
		usableQtyValue.setText(String.valueOf(skuShelfBatchStock.getSkuShelfBatchDynamicStock().getUsableQty()));
		usableUnitQtyValue.setText(String.valueOf(skuShelfBatchStock.getUsableUnitQty()));
		usableMinUnitQtyValue.setText(String.valueOf(skuShelfBatchStock.getUsableMinUnitQty()));
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuShelfBatchStockShow_headerView, R.string.shelf_query_detail);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_shelf_batch_stock_show;
	}

	@Override
	protected void setListener() {

	}

}
