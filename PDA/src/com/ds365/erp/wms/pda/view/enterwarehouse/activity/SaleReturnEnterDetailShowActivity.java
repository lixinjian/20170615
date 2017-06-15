package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

/**
 * 退货入库单详情查看
 */
public class SaleReturnEnterDetailShowActivity extends BasePdaListActivity{
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_enter_detail_show;
	}
	
	@Override
	protected void initActivityView() {
		
		TextView storeNameValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_storeName_value);
		TextView shelfCodeValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_shelfCode_value);
		DateField produceDateValue = (DateField) findViewById(R.id.saleReturnEnterDetailShow_produceDate_value);
		TextView produceBatchNoValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_produceBatchNo_value);
		TextView sysBatchNoValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_sysBatchNo_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_unitqty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_minUnitQty_value);
		TextView minUnitNameValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_unitName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_skuName_value);
		TextView billMoneyValue = (TextView) findViewById(R.id.saleReturnEnterDetailShow_billMoney_value);
		
		SaleReturnEnterDetailModel saleReturnEnterDetailModel = (SaleReturnEnterDetailModel) getIntent().getSerializableExtra(
				SaleReturnEnterBillShowActivity.SER_KEY);
		
		storeNameValue.setText(saleReturnEnterDetailModel.getStore().getName());
		shelfCodeValue.setText(saleReturnEnterDetailModel.getShelf().getCode());
		produceDateValue.setValue(saleReturnEnterDetailModel.getProduceDate());
		produceBatchNoValue.setText(saleReturnEnterDetailModel.getProduceBatchNo());
		sysBatchNoValue.setText(saleReturnEnterDetailModel.getSysBatchNo());
		unitQtyValue.setText(String.valueOf(saleReturnEnterDetailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(saleReturnEnterDetailModel.getMinUnitQty()));
		minUnitNameValue.setText(saleReturnEnterDetailModel.getMinUnitName());
		unitNameValue.setText(saleReturnEnterDetailModel.getUnitName());
		skuCodeValue.setText(saleReturnEnterDetailModel.getSku().getCode());
		skuNameValue.setText(saleReturnEnterDetailModel.getSku().getName());
		billMoneyValue.setText(String.valueOf(saleReturnEnterDetailModel.getMoney()));
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleReturnEnterDetailShow_headerview, R.string.sale_return_enter_detail);
	}

	@Override
	protected void setListener() {
	}
}
