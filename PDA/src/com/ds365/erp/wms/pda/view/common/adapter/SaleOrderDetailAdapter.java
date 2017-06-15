package com.ds365.erp.wms.pda.view.common.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleOrderDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SaleOrderDetailAdapter extends CommonAdapter<SaleOrderDetailModel> {

	public SaleOrderDetailAdapter(Context context, List<SaleOrderDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.common_sale_order_detail_adapter;
	}

	@Override
	public CommonAdapter<SaleOrderDetailModel>.HolderView getHoldView(int position, View contentView,ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuName = (TextView) contentView.findViewById(R.id.commonSaleOrderDetailAdapter_skuName_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.commonSaleOrderDetailAdapter_skuCode_value);
		holder.specQty  =(TextView) contentView.findViewById(R.id.commonSaleOrderDetailAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.commonSaleOrderDetailAdapter_unitName_value);
		holder.unitQty =(TextView) contentView.findViewById(R.id.commonSaleOrderDetailAdapter_unitQty_value);
		holder.minUnitQty=(TextView) contentView.findViewById(R.id.commonSaleOrderDetailAdapter_minUnitQty_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<SaleOrderDetailModel>.HolderView contentView, SaleOrderDetailModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		SaleOrderDetailModel detailModel = list.get(position);
		holder.skuName.setText(detailModel.getSku().getName());
		holder.skuCode.setText(detailModel.getSku().getCode());
		holder.specQty.setText(String.valueOf(detailModel.getSpecQty()));
		holder.unitName.setText(detailModel.getUnitName());
		holder.unitQty.setText(String.valueOf(detailModel.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(detailModel.getMinUnitQty()));
	}
	
	class ViewHold extends HolderView{
		TextView skuName;		//SkuName
		TextView skuCode;		//SKU码
		TextView specQty;		//包装数量
		TextView unitName;		//单位
		TextView unitQty;		//应收件数
		TextView minUnitQty; 	//应收散数
	}
}
