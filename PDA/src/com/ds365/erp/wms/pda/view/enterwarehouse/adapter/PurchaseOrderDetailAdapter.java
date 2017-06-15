package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PurchaseOrderDetailAdapter extends CommonAdapter<PurchaseOrderDetailModel> {

	public PurchaseOrderDetailAdapter(Context context, List<PurchaseOrderDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_purchase_order_detail_adapter;
	}

	@Override
	public CommonAdapter<PurchaseOrderDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuName = (TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_skuName_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_skuCode_value);
		holder.specQty  =(TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_unitName_value);
		holder.unitQty =(TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_unitQty_value);
		holder.minUnitQty=(TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_minUnitQty_value);
		holder.remainUnitQty =(TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_remainUnitQty_value);
		holder.remainMinUnitQty=(TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_remainMinUnitQty_value);
		holder.qty =(TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_qty_value);
		holder.remainQty=(TextView) contentView.findViewById(R.id.purchaseOrderDetailAdapter_remainQty_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<PurchaseOrderDetailModel>.HolderView contentView, PurchaseOrderDetailModel item) {
		ViewHold holder = (ViewHold) contentView;
		
		PurchaseOrderDetailModel detailModel = list.get(position);
		
		holder.skuName.setText(detailModel.getSku().getName());
		holder.skuCode.setText(detailModel.getSku().getCode());
		holder.specQty.setText(String.valueOf(detailModel.getSpecQty()));
		holder.unitName.setText(detailModel.getUnitName());
		holder.unitQty.setText(String.valueOf(detailModel.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(detailModel.getMinUnitQty()));
		holder.remainUnitQty.setText(String.valueOf(detailModel.getRemainUnitQty()));
		holder.remainMinUnitQty.setText(String.valueOf(detailModel.getRemainMinUnitQty()));
		holder.qty.setText(String.valueOf(detailModel.getQty()));	//由于空间限制,暂不显示
		holder.remainQty.setText(String.valueOf(detailModel.getRemainQty()));
		
	}
	
	class ViewHold extends HolderView{
		TextView skuName;		//SkuName
		TextView skuCode;		//SKU码
		TextView specQty;		//包装数量
		TextView unitName;		//单位
		TextView unitQty;		//总件数
		TextView minUnitQty; 	//总散数
		TextView remainUnitQty;		//剩余件数
		TextView remainMinUnitQty; 	//剩余散数
		TextView qty;		//总数量
		TextView remainQty;		//剩余总数量
		
		
		
	}
}