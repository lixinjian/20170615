package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PurchaseEnterBillAdapter extends CommonAdapter<PurchaseEnterBillModel> {
	
	public PurchaseEnterBillAdapter(Context context, List<PurchaseEnterBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_bill_adapter;
	}

	@Override
	public CommonAdapter<PurchaseEnterBillModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.enterBillCode = (TextView) contentView.findViewById(R.id.purchaseEnterBillAdapter_enterBillCode_value);
		holder.orderBillCode = (TextView) contentView.findViewById(R.id.purchaseEnterBillAdapter_orderBillCode_value);
		holder.supplierName = (TextView) contentView.findViewById(R.id.purchaseEnterBillAdapter_supplierName_value);
		holder.orderMakeTime = (DateField) contentView.findViewById(R.id.purchaseEnterBillAdapter_orderMakeTime_value);
		holder.enterDate = (DateField) contentView.findViewById(R.id.purchaseEnterBillAdapter_enterDate_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.purchaseEnterBillAdapter_itemCount_value);
		holder.billMoney = (TextView) contentView.findViewById(R.id.purchaseEnterBillAdapter_billMoney_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PurchaseEnterBillModel>.HolderView contentView, PurchaseEnterBillModel item) {
		
		ViewHold holder = (ViewHold) contentView;
		
		holder.enterBillCode.setText(list.get(position).getBillCode());
		holder.orderBillCode.setText(list.get(position).getRelatedBillCode());
		holder.supplierName.setText(list.get(position).getSupplier().getName());
		holder.orderMakeTime.setValue(list.get(position).getOrderMakeTime());
		holder.enterDate.setValue(list.get(position).getEnterDate());
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
		holder.billMoney.setText(String.valueOf(list.get(position).getBillMoney()));
	}
	
	class ViewHold extends HolderView{
		TextView enterBillCode;		//入库单号
		TextView orderBillCode;		//订单号
		TextView supplierName;	//供应商名称
		DateField orderMakeTime;		//下单日期
		DateField enterDate;			//入库日期
		TextView itemCount;			//商品数
		TextView billMoney;//总金额
	}
}