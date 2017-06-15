package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PurchaseReturnOrderDetailAdapter extends CommonAdapter<PurchaseReturnOrderDetailModel> {

	public PurchaseReturnOrderDetailAdapter(Context context, List<PurchaseReturnOrderDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_order_detail_adapter;
	}

	@Override
	public CommonAdapter<PurchaseReturnOrderDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.purchaseReturnOrderDetailAdapter_skuCode_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.purchaseReturnOrderDetailAdapter_skuName_value);
		holder.makeTime = (DateField) contentView.findViewById(R.id.purchaseReturnOrderDetailAdapter_makeTime_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.purchaseReturnOrderDetailAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.purchaseReturnOrderDetailAdapter_minUnitQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.purchaseReturnOrderDetailAdapter_unitName_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<PurchaseReturnOrderDetailModel>.HolderView contentView, PurchaseReturnOrderDetailModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.skuCode.setText(list.get(position).getSku().getCode());
		holder.skuName.setText(list.get(position).getSku().getName());
		holder.makeTime.setValue(list.get(position).getBill().getMakeTime());
		holder.unitQty.setText(String.valueOf(list.get(position).getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(list.get(position).getMinUnitQty()));
		holder.unitName.setText(list.get(position).getPack().getUnitName());
	}
	
	class ViewHold extends HolderView{
		TextView skuCode;			//sku码
		TextView skuName;			//sku名称
		DateField makeTime;				//日期时间
		TextView unitQty;	//退货件数
		TextView minUnitQty;	//退货散数
		TextView unitName;			//单位
	}
}
