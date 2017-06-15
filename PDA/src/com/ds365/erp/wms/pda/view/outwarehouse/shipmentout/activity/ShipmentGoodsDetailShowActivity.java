package com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.shipment.ShipmentGoodsDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.query.activity.ShipmentBillShowActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShipmentGoodsDetailShowActivity extends BasePdaListActivity {
	
	@Override
	protected void initActivityView() {
		ShipmentGoodsDetailModel detailModel = null;
		Bundle bundle = getIntent().getExtras();
		if (ShipmentOutBillActivity.REQUEST_CODE.equals(bundle.getString(ShipmentOutBillActivity.REQUEST_CODE_KEY))) {
			detailModel = (ShipmentGoodsDetailModel) getIntent().getSerializableExtra(ShipmentOutBillActivity.SER_KEY);
		}else if(ShipmentOrderBillShowActivity.REQUEST_CODE.equals(bundle.getString(ShipmentOrderBillShowActivity.REQUEST_CODE_KEY))){
			detailModel = (ShipmentGoodsDetailModel) getIntent().getSerializableExtra(ShipmentOrderBillShowActivity.SER_KEY);
		}else if (DeliverBillShowActivity.REQUEST_CODE.equals(bundle.getString(DeliverBillShowActivity.REQUEST_CODE_KEY))){
			detailModel = (ShipmentGoodsDetailModel) getIntent().getSerializableExtra(DeliverBillShowActivity.SER_KEY);
		}else if(ShipmentBillShowActivity.REQUEST_CODE.equals(bundle.getString(ShipmentBillShowActivity.REQUEST_CODE_KEY))){
			detailModel = (ShipmentGoodsDetailModel) getIntent().getSerializableExtra(ShipmentBillShowActivity.SER_KEY);
		}
		
		TextView skuNameValue = (TextView) findViewById(R.id.shipmentGoodsDetailShow_skuName_value);
		TextView skuCodeValue = (TextView) findViewById(R.id.shipmentGoodsDetailShow_skuCode_value);
		TextView specQtyValue = (TextView) findViewById(R.id.shipmentGoodsDetailShow_specQty_value);
		TextView unitNameValue = (TextView) findViewById(R.id.shipmentGoodsDetailShow_unitName_value);
		TextView specValue = (TextView) findViewById(R.id.shipmentGoodsDetailShow_spec_value);
		TextView unitQty = (TextView) findViewById(R.id.shipmentGoodsDetailShow_unitQty_value);
		TextView minUnitQty = (TextView) findViewById(R.id.shipmentGoodsDetailShow_minUnitQty_value);
		TextView qty = (TextView) findViewById(R.id.shipmentGoodsDetailShow_qty_value);
		
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
		initHeadView(R.id.shipmentGoodsDetailShow_headerview, R.string.shipment_goods_detail_view);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_shipment_goods_detail_view;
	}

	@Override
	protected void setListener() {

	}
}
