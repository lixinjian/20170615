package com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.shipment.DeliverGoodsDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.widget.TextView;

public class DeliverGoodsDetailShowActivity extends BasePdaListActivity {
	
	@Override
	protected void initActivityView() {
		
		DeliverGoodsDetailModel detailModel = (DeliverGoodsDetailModel) getIntent().getSerializableExtra(DeliverBillShowActivity.SER_KEY);
		
		TextView skuNameValue = (TextView) findViewById(R.id.deliverGoodsDetailShow_skuName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.deliverGoodsDetailShow_skuCode_value);
		TextView specQtyValue = (TextView) findViewById(R.id.deliverGoodsDetailShow_specQty_value);
		TextView unitNameValue = (TextView) findViewById(R.id.deliverGoodsDetailShow_unitName_value);
		TextView specValue = (TextView) findViewById(R.id.deliverGoodsDetailShow_spec_value);
		TextView unitQty = (TextView) findViewById(R.id.deliverGoodsDetailShow_unitQty_value);
		TextView minUnitQty = (TextView) findViewById(R.id.deliverGoodsDetailShow_minUnitQty_value);
		TextView qty = (TextView) findViewById(R.id.deliverGoodsDetailShow_qty_value);
		
		skuNameValue.setText(detailModel.getSku().getName());
		skuCodeValue.setText(detailModel.getSku().getCode());
		specQtyValue.setText(String.valueOf(detailModel.getSpecQty()));
		unitNameValue.setText(detailModel.getUnitName());
		specValue.setText(detailModel.getSpec());
		unitQty.setText(String.valueOf(detailModel.getUnitQty()));
		minUnitQty.setText(String.valueOf(detailModel.getMinUnitQty()));
		qty.setText(String.valueOf(detailModel.getQty()));
		
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.deliverGoodsDetailShow_headerview, R.string.deliver_goods_detail_view);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_deliver_goods_detail_show;
	}

	@Override
	protected void setListener() {

	}
}
