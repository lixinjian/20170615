package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.content.Intent;
import android.widget.TextView;
/**
 * 收货入库-订单详情-查看商品详情
 *
 */
public class SupplierOrderDetailShowActivity extends BasePdaListActivity {
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_supplier_order_detail_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.supplierOrderDetailShow_headerview, R.string.supplier_order_detail);
	}

	@Override
	protected void initActivityView() {
		
		Intent intent = getIntent();
		
		TextView minUnitNameValue = (TextView) findViewById(R.id.supplierOrderDetailShow_minUnitName_value);
		TextView unitNameValue = (TextView) findViewById(R.id.supplierOrderDetailShow_unitName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.supplierOrderDetailShow_skuCode_value);
		TextView skuNameValue = (TextView) findViewById(R.id.supplierOrderDetailShow_skuName_value);
		DateField produceDateValue = (DateField) findViewById(R.id.supplierOrderDetailShow_produceDate_value);
		TextView produceBatchNoValue = (TextView) findViewById(R.id.supplierOrderDetailShow_produceBatchNo_value);
		TextView deliveredQty = (TextView) findViewById(R.id.supplierOrderDetailShow_deliveredQty_value);
		TextView deliveredUnitQty = (TextView) findViewById(R.id.supplierOrderDetailShow_deliveredUnitQty_value);
		TextView deliveredMinUnitQty = (TextView) findViewById(R.id.supplierOrderDetailShow_deliveredMinUnitQty_value);
		SupplierOrderDetailModel supplierOrderDetail=(SupplierOrderDetailModel)intent.getSerializableExtra(SupplierOrderBillShowActivity.SER_KEY);
		
		produceDateValue.setValue(supplierOrderDetail.getProduceDate());
		produceBatchNoValue.setText(supplierOrderDetail.getProduceBatchNo());
//		minUnitNameValue.setText(supplierOrderDetail.getMinUnitName());
		unitNameValue.setText(supplierOrderDetail.getUnitName());
		skuCodeValue.setText(supplierOrderDetail.getSku().getCode());
		skuNameValue.setText(supplierOrderDetail.getSku().getName());
		deliveredUnitQty.setText(String.valueOf(supplierOrderDetail.getDeliverUnitQty()));
		deliveredMinUnitQty.setText(String.valueOf(supplierOrderDetail.getDeliverMinUnitQty()));
		deliveredQty.setText(String.valueOf(supplierOrderDetail.getDeliverQty()));
	}
    
	@Override
	protected void setListener() {
	}
}
