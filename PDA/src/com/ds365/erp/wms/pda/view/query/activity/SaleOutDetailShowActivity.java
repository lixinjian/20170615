package com.ds365.erp.wms.pda.view.query.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleOutDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

/**
 * 销售处库单详情查看
 */
public class SaleOutDetailShowActivity extends BasePdaListActivity{
	
	@Override
	protected int getContentViewId() {
		return R.layout.query_sale_out_detail_view;
	}
	
	@Override
	protected void initActivityView() {
		
		DateField produceDateValue = (DateField) findViewById(R.id.saleOutDetailShow_produceDate_value);
		TextView produceBatchNoValue = (TextView) findViewById(R.id.saleOutDetailShow_produceBatchNo_value);
		TextView sysBatchNoValue = (TextView) findViewById(R.id.saleOutDetailShow_sysBatchNo_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.saleOutDetailShow_unitqty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.saleOutDetailShow_minUnitQty_value);
		TextView minUnitNameValue = (TextView) findViewById(R.id.saleOutDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.saleOutDetailShow_unitName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.saleOutDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.saleOutDetailShow_skuName_value);
		TextView billMoney = (TextView) findViewById(R.id.saleOutDetailShow_billMoney_value);
		
		SaleOutDetailModel saleOutDetailModel = (SaleOutDetailModel) getIntent().getSerializableExtra(SaleOutBillShowActivity.SER_KEY);
		
		produceDateValue.setValue(saleOutDetailModel.getProduceDate());
		produceBatchNoValue.setText(saleOutDetailModel.getProduceBatchNo());
		sysBatchNoValue.setText(saleOutDetailModel.getSysBatchNo());
		unitQtyValue.setText(String.valueOf(saleOutDetailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(saleOutDetailModel.getMinUnitQty()));
		minUnitNameValue.setText(saleOutDetailModel.getMinUnitName());
		unitNameValue.setText(saleOutDetailModel.getUnitName());
		skuCodeValue.setText(saleOutDetailModel.getSku().getCode());
		skuNameValue.setText(saleOutDetailModel.getSku().getName());
		billMoney.setText(String.valueOf(saleOutDetailModel.getMoney()));
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleOutDetailShow_headerview, R.string.query_sale_out_detail);
	}

	@Override
	protected void setListener() {
	}
}
