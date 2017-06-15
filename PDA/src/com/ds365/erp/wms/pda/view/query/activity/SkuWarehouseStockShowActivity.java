package com.ds365.erp.wms.pda.view.query.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.stock.SkuWarehouseStockModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

public class SkuWarehouseStockShowActivity extends BasePdaListActivity {

	@Override
	protected void initActivityView() {

		TextView skuNameValue = (TextView) findViewById(R.id.skuWarehouseStockShow_skuName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.skuWarehouseStockShow_skuCode_value);
		TextView specValue = (TextView) findViewById(R.id.skuWarehouseStockShow_spec_value);
		TextView specQtyValue = (TextView) findViewById(R.id.skuWarehouseStockShow_specQty_value);
		TextView unitNameValue = (TextView) findViewById(R.id.skuWarehouseStockShow_unitName_value);
		TextView stockQtyValue = (TextView) findViewById(R.id.skuWarehouseStockShow_stockQty_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.skuWarehouseStockShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.skuWarehouseStockShow_minUnitQty_value);
		TextView salableQtyValue = (TextView) findViewById(R.id.skuWarehouseStockShow_salableQty_value);
		TextView salableUnitQtyValue = (TextView) findViewById(R.id.skuWarehouseStockShow_salableUnitQty_value);
		TextView salableMinUnitQtyValue = (TextView) findViewById(R.id.skuWarehouseStockShow_salableMinUnitQty_value);
		
		SkuWarehouseStockModel bill = (SkuWarehouseStockModel) getIntent().getSerializableExtra(SkuWarehouseStockListActivity.SER_KEY);
		
		skuNameValue.setText(bill.getSku().getName());
		skuCodeValue.setText(bill.getSku().getCode());
		specValue.setText(bill.getSku().getSpec());
		unitNameValue.setText(bill.getSku().getGoodsPack().getUnitName());
		specQtyValue.setText(String.valueOf(bill.getSku().getGoodsPack().getSpecQty()));
		stockQtyValue.setText(String.valueOf(bill.getQty()));
		unitQtyValue.setText(String.valueOf(bill.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(bill.getMinUnitQty()));
		salableQtyValue.setText(String.valueOf(bill.getSkuWarehouseDynamicStock().getSalableQty()));
		salableUnitQtyValue.setText(String.valueOf(bill.getSkuWarehouseDynamicStock().getSalableUnitQty()));
		salableMinUnitQtyValue.setText(String.valueOf(bill.getSkuWarehouseDynamicStock().getSalableMinUnitQty()));
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuWarehouseStockShow_headerView, R.string.warehouse_stock_query_detail);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_warehouse_stock_show;
	}

	@Override
	protected void setListener() {

	}

}
