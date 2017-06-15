package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

/**
 * 退货入库--退货单明细查看
 * @author lxj
 */
public class SaleReturnOrderDetailShowActivity extends BasePdaListActivity{
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_order_detail_show;
	}
	
	@Override
	protected void initActivityView() {
		
//		TextView storeNameValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_storeName_value);
//		TextView shelfCodeValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_shelfCode_value);
		DateField produceDateValue = (DateField) findViewById(R.id.saleReturnOrderDetailShow_produceDate_value);
		TextView produceBatchNoValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_produceBatchNo_value);
		TextView sysBatchNoValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_sysBatchNo_value);
		TextView unitQtyValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_unitqty_value);
		TextView minUnitQtyValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_minUnitQty_value);
		TextView minUnitNameValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_unitName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.saleReturnOrderDetailShow_skuName_value);
		
		SaleReturnOrderDetailModel saleReturnOrderDetailModel = (SaleReturnOrderDetailModel) getIntent().getSerializableExtra(
				SaleReturnOrderBillShowActivity.SER_KEY);
		
//		storeNameValue.setText(saleReturnOrderDetailModel.getStore().getName());//不显示库区货位,因为还没有入库
//		shelfCodeValue.setText(saleReturnOrderDetailModel.getShelf().getCode());
		produceDateValue.setValue(saleReturnOrderDetailModel.getProduceDate());
		produceBatchNoValue.setText(saleReturnOrderDetailModel.getProduceBatchNo());
		sysBatchNoValue.setText(saleReturnOrderDetailModel.getSysBatchNo());
		unitQtyValue.setText(String.valueOf(saleReturnOrderDetailModel.getUnitQty()));
		minUnitQtyValue.setText(String.valueOf(saleReturnOrderDetailModel.getMinUnitQty()));
		minUnitNameValue.setText(saleReturnOrderDetailModel.getMinUnitName());
		unitNameValue.setText(saleReturnOrderDetailModel.getUnitName());
		skuCodeValue.setText(saleReturnOrderDetailModel.getSku().getCode());
		skuNameValue.setText(saleReturnOrderDetailModel.getSku().getName());
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleReturnOrderDetailShow_headerview, R.string.sale_return_order_detail);
	}

	@Override
	protected void setListener() {
	}
}
