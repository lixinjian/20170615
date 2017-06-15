package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PurchaseReturnOutDetailViewAdapter extends CommonAdapter<PurchaseReturnOutDetailModel> {

	public PurchaseReturnOutDetailViewAdapter(Context context, List<PurchaseReturnOutDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_out_detail_view_adapter;
	}

	@Override
	public CommonAdapter<PurchaseReturnOutDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailViewAdapter_skuCode_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailViewAdapter_skuName_value);
		holder.billMoney = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailViewAdapter_billMoney_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailViewAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailViewAdapter_minUnitQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.purchaseReturnOutDetailViewAdapter_unitName_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<PurchaseReturnOutDetailModel>.HolderView contentView, PurchaseReturnOutDetailModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.skuCode.setText(list.get(position).getSku().getCode());
		holder.skuName.setText(list.get(position).getSku().getName());
		holder.billMoney.setText(String.valueOf(list.get(position).getMoney()));
		holder.unitQty.setText(String.valueOf(list.get(position).getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(list.get(position).getMinUnitQty()));
		holder.unitName.setText(list.get(position).getPack().getUnitName());
	}
	
	class ViewHold extends HolderView{
		TextView skuCode;			//sku码
		TextView skuName;			//sku名称
		TextView billMoney;			//金额
		TextView unitQty;			//件数
		TextView minUnitQty;		//散数
		TextView unitName;			//单位
	}
}
