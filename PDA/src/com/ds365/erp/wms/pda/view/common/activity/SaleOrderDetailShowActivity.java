package com.ds365.erp.wms.pda.view.common.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleOrderDetailModel;

import android.widget.TextView;

public class SaleOrderDetailShowActivity extends BasePdaActivity{

	@Override
	protected void initActivityView() {
		TextView minUnitNameValue = (TextView) findViewById(R.id.commonSaleOrderDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.commonSaleOrderDetailShow_unitName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.commonSaleOrderDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.commonSaleOrderDetailShow_skuName_value);
		DateField produceDateValue = (DateField) findViewById(R.id.commonSaleOrderDetailShow_produceDate_value);
		TextView produceBatchNoValue = (TextView) findViewById(R.id.commonSaleOrderDetailShow_produceBatchNo_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.commonSaleOrderDetailShow_unitQty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.commonSaleOrderDetailShow_minUnitQty_value);
		TextView sysBatchNo = (TextView) findViewById(R.id.commonSaleOrderDetailShow_sysBatchNo_value);
		
		SaleOrderDetailModel detailModel = (SaleOrderDetailModel) getIntent().getSerializableExtra(SaleOrderBillShowActivity.SER_KEY);
				
		produceDateValue.setValue(detailModel.getProduceDate());
		produceBatchNoValue.setText(detailModel.getProduceBatchNo());
		unitQtyValue.setText(String.valueOf(detailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(detailModel.getMinUnitQty()));
		minUnitNameValue.setText(detailModel.getMinUnitName());
		unitNameValue.setText(detailModel.getUnitName());
		skuCodeValue.setText(detailModel.getSku().getCode());
		skuNameValue.setText(detailModel.getSku().getName());
		sysBatchNo.setText(detailModel.getSysBatchNo());
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.commonSaleOrderDetailShow_headerview, R.string.shipment_goods_detail);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.common_sale_order_detail_show;
	}

	@Override
	protected void setListener() {
		
	}
}
