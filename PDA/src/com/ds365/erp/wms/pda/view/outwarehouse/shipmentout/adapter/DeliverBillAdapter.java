package com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.shipment.DeliverBillModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DeliverBillAdapter extends CommonAdapter<DeliverBillModel> {

	public DeliverBillAdapter(Context context, List<DeliverBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		
		return R.layout.outwarehouse_deliver_bill_adapter;
	}

	@Override
	public CommonAdapter<DeliverBillModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.deliverBillAdapter_billCode_value);
		holder.makeTime = (DateField) contentView.findViewById(R.id.deliverBillAdapter_makeTime_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.deliverBillAdapter_itemCount_value);
		holder.deliverStation = (TextView) contentView.findViewById(R.id.deliverBillAdapter_deliverStation_value);
		holder.billMoney = (TextView) contentView.findViewById(R.id.deliverBillAdapter_billMoney_value);
		holder.receivableMoney = (TextView) contentView.findViewById(R.id.deliverBillAdapter_receivableMoney_value);
		holder.orderCount = (TextView) contentView.findViewById(R.id.deliverBillAdapter_orderCount_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<DeliverBillModel>.HolderView contentView, DeliverBillModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.billCode.setText(list.get(position).getBillCode());
		holder.makeTime.setValue(list.get(position).getMakeTime());
		holder.orderCount.setText(String.valueOf(list.get(position).getOrderCount()));
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
		holder.deliverStation.setText(list.get(position).getDeliverStation().getName());
		holder.billMoney.setText(String.valueOf(list.get(position).getBillMoney()));
		holder.receivableMoney.setText(String.valueOf(list.get(position).getReceivableMoney()));
		
	}
	
	class ViewHold extends HolderView{
		TextView billCode;		//送货单号
		DateField makeTime;			//日期
		TextView itemCount;			//商品数
		TextView deliverStation;		//配送站
		TextView billMoney;//订单金额
		TextView receivableMoney;//应收金额
		TextView orderCount;//订单数
	}
}