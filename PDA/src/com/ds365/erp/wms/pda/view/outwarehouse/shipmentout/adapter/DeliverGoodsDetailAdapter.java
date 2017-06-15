package com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.shipment.DeliverGoodsDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DeliverGoodsDetailAdapter extends CommonAdapter<DeliverGoodsDetailModel> {

	public DeliverGoodsDetailAdapter(Context context, List<DeliverGoodsDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.outwarehouse_deliver_goods_detail_adapter;
	}

	@Override
	public CommonAdapter<DeliverGoodsDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.deliverGoodsDetailAdapter_skuCode_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.deliverGoodsDetailAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.deliverGoodsDetailAdapter_minUnitQty_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.deliverGoodsDetailAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.deliverGoodsDetailAdapter_unitName_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.deliverGoodsDetailAdapter_skuName_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<DeliverGoodsDetailModel>.HolderView contentView, DeliverGoodsDetailModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		DeliverGoodsDetailModel detailModel = list.get(position);
		holder.skuName.setText(detailModel.getSku().getName());
		holder.skuCode.setText(detailModel.getSku().getCode());
		holder.unitName.setText(detailModel.getUnitName());
		holder.specQty.setText(String.valueOf(detailModel.getSpecQty()));
		holder.unitQty.setText(String.valueOf(detailModel.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(detailModel.getMinUnitQty()));
	}
	
	class ViewHold extends HolderView{
		TextView skuName;				//sku名称
		TextView skuCode;				//sku码
		TextView unitName;				//单位
		TextView specQty;				//包装数量
		TextView unitQty;			//件数
		TextView minUnitQty;		//散数
	}
}
