package com.ds365.erp.wms.pda.view.query.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.stock.SkuStockAccountBookModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;

import android.widget.TextView;

/**
 * 说明 ：分页查询商品进销存账页明细
 */
public class SkuStockAccountBookDetailShowActivity extends BasePdaActivity {
	
	@Override
	protected void initActivityView() {
		TextView skuNameValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_skuName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_skuCode_value);
		TextView entCodeValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_entCode_value);
		TextView entNameValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_entName_value);
		TextView saleTypeValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_saleType_value);
		TextView operateTypeValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_operateType_value);
		TextView priceValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_price_value);
		TextView sysBatchValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_sysBatch_value);
		TextView specValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_spec_value);
		TextView specQtyValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_specQty_value);
		TextView unitNameValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_unitName_value);
		DateField bizDateValue = (DateField) findViewById(R.id.skuStockAccountBookDetailShow_bizDate_value);
		TextView warehouseRemainQtyValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_warehouseRemainQty_value);
		TextView warehouseRemainMoneyValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_storeRemainMoney_value);
		TextView qtyValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_qty_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_minUnitQty_value);
		TextView warehouseRemainUnitQtyValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_warehouseRemainUnitQty_value);
		TextView warehouseRemainMinUnitQtyValue = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_warehouseRemainMinUnitQty_value);
		TextView money = (TextView) findViewById(R.id.skuStockAccountBookDetailShow_money_value);
		
		
		SkuStockAccountBookModel skuStockAccountBookModel = (SkuStockAccountBookModel) getIntent().getSerializableExtra(SkuStockAccountBookListActivity.SER_KEY);
		
		skuNameValue.setText(skuStockAccountBookModel.getSku().getName());
		skuCodeValue.setText(skuStockAccountBookModel.getSku().getCode());
		entCodeValue.setText(skuStockAccountBookModel.getEntCode());
		entNameValue.setText(skuStockAccountBookModel.getEntName());
		saleTypeValue.setText(skuStockAccountBookModel.getSaleType().getName());
		operateTypeValue.setText(skuStockAccountBookModel.getOperateType().getName());
		priceValue.setText(String.valueOf(skuStockAccountBookModel.getSku().getCostPrice()));
		sysBatchValue.setText(skuStockAccountBookModel.getSysBatchNo());
		specQtyValue.setText(String.valueOf(skuStockAccountBookModel.getSpecQty()));
		specValue.setText(skuStockAccountBookModel.getSku().getSpec());
		unitNameValue.setText(skuStockAccountBookModel.getUnitName());
		bizDateValue.setValue(skuStockAccountBookModel.getBizDate());
		warehouseRemainQtyValue.setText(String.valueOf(skuStockAccountBookModel.getWarehouseRemainQty()));
		warehouseRemainMoneyValue.setText(String.valueOf(skuStockAccountBookModel.getWarehouseRemainMoney()));
		qtyValue.setText(String.valueOf(skuStockAccountBookModel.getQty()));
		money.setText(String.valueOf(skuStockAccountBookModel.getMoney()));
		
		QtyModel qtyModel = QtyMoneyUtils.getQtyEntity(skuStockAccountBookModel.getQty(), skuStockAccountBookModel.getPack().getSpecQty());
		QtyModel warehouseRemainUnitQtyModel = QtyMoneyUtils.getQtyEntity(skuStockAccountBookModel.getWarehouseRemainQty(), skuStockAccountBookModel.getPack().getSpecQty());
		unitQtyValue.setText(String.valueOf(qtyModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(qtyModel.getMinUnitQty()));
		warehouseRemainUnitQtyValue.setText(String.valueOf(warehouseRemainUnitQtyModel.getUnitQty()));
		warehouseRemainMinUnitQtyValue.setText(String.valueOf(warehouseRemainUnitQtyModel.getMinUnitQty()));
		
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuStockAccountBookDetailShow_headerview, R.string.query_sku_stock_account_book);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_stock_account_book_detail_show;
	}

	@Override
	protected void setListener() {
		
	}
}
