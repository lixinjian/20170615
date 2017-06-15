package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutBillModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;;

public class PurchaseReturnOutBillAdapter extends CommonAdapter<PurchaseReturnOutBillModel> {
	
	public PurchaseReturnOutBillAdapter(Context context,List<PurchaseReturnOutBillModel> list) {
		super(context,list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_out_bill_adapter;
	}

	@Override
	public CommonAdapter<PurchaseReturnOutBillModel>.HolderView getHoldView(int position, View contentView,ViewGroup parent) {
		
		ViewHold holder = new ViewHold();
		
		holder.orderBillCode = (TextView) contentView.findViewById(R.id.purchaseReturnOutBillAdapter_orderBillCode_value);
		holder.outBillCode = (TextView) contentView.findViewById(R.id.purchaseReturnOutBillAdapter_outBillCode_value);
		holder.supplierName = (TextView) contentView.findViewById(R.id.purchaseReturnOutBillAdapter_supplierName_value);
		holder.orderMakeTime = (DateField) contentView.findViewById(R.id.purchaseReturnOutBillAdapter_orderMakeTime_value);
		holder.outDate = (DateField) contentView.findViewById(R.id.purchaseReturnOutBillAdapter_outDate_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.purchaseReturnOutBillAdapter_itemCount_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PurchaseReturnOutBillModel>.HolderView contentView, PurchaseReturnOutBillModel item) {
		
		ViewHold holder = (ViewHold) contentView;
		
		holder.orderBillCode.setText(list.get(position).getRelatedBillCode());
		holder.outBillCode.setText(list.get(position).getBillCode());
		holder.supplierName.setText(list.get(position).getSupplier().getName());
		holder.orderMakeTime.setValue(list.get(position).getOrderMakeTime());
		holder.outDate.setValue(list.get(position).getEnterDate());	//出库时间,与入库时间共用一个字段enterDate
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
		
	}
	
	class ViewHold extends HolderView{
		TextView orderBillCode;		//订单号
		TextView outBillCode;		//出库单号
		TextView supplierName;	//供应商名称
		DateField orderMakeTime;			//下单时间
		DateField outDate;			//出库时间
		TextView itemCount;			//总数量
	}
}
