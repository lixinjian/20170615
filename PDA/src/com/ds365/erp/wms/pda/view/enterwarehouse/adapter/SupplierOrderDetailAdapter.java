package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SupplierOrderDetailAdapter extends CommonAdapter<SupplierOrderDetailModel> {

	public SupplierOrderDetailAdapter(Context context, List<SupplierOrderDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_supplier_order_detail_adapter;
	}

	@Override
	public CommonAdapter<SupplierOrderDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuName = (TextView) contentView.findViewById(R.id.supplierOrderDetailAdapter_skuName_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.supplierOrderDetailAdapter_skuCode_value);
		holder.specQty  =(TextView) contentView.findViewById(R.id.supplierOrderDetailAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.supplierOrderDetailAdapter_unitName_value);
		holder.deliverUnitQty =(TextView) contentView.findViewById(R.id.supplierOrderDetailAdapter_deliverUnitQty_value);
		holder.deliverMinUnitQty=(TextView) contentView.findViewById(R.id.supplierOrderDetailAdapter_deliverMinUnitQty_value);
		holder.deliverQty =(TextView) contentView.findViewById(R.id.supplierOrderDetailAdapter_deliverQty_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<SupplierOrderDetailModel>.HolderView contentView, SupplierOrderDetailModel item) {
		ViewHold holder = (ViewHold) contentView;
		
		SupplierOrderDetailModel detailModel = list.get(position);
		
		holder.skuName.setText(detailModel.getSku().getName());
		holder.skuCode.setText(detailModel.getSku().getCode());
		holder.specQty.setText(String.valueOf(detailModel.getSpecQty()));
		holder.unitName.setText(detailModel.getUnitName());
		holder.deliverUnitQty.setText(String.valueOf(detailModel.getDeliverUnitQty()));
		holder.deliverMinUnitQty.setText(String.valueOf(detailModel.getDeliverMinUnitQty()));
		holder.deliverQty.setText(String.valueOf(detailModel.getDeliverQty()));
	}
	
	class ViewHold extends HolderView{
		TextView skuName;		//SkuName
		TextView skuCode;		//SKU码
		TextView specQty;		//包装数量
		TextView unitName;		//单位
		TextView deliverQty;		//发货总数量
		TextView deliverUnitQty; 	//发货件数
		TextView deliverMinUnitQty;		//发货散数
	}
}