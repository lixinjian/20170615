package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterBillModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SaleReturnEnterBillAdapter extends CommonAdapter<SaleReturnEnterBillModel> {

	
	public SaleReturnEnterBillAdapter(Context context, List<SaleReturnEnterBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_enter_bill_adapter;
	}

	@Override
	public CommonAdapter<SaleReturnEnterBillModel>.HolderView getHoldView(final int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.orderBillCode = (TextView) contentView.findViewById(R.id.saleReturnEnterbillAdapter_orderBillCode_value);
		holder.enterBillCode = (TextView) contentView.findViewById(R.id.saleReturnEnterbillAdapter_enterBillCode_value);
		holder.customerName = (TextView) contentView.findViewById(R.id.saleReturnEnterbillAdapter_customer_value);
		holder.orderMakeTime = (DateField) contentView.findViewById(R.id.saleReturnEnterbillAdapter_orderMakeTime_value);
		holder.outDate = (DateField) contentView.findViewById(R.id.saleReturnEnterbillAdapter_outDate_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.saleReturnEnterbillAdapter_itemCount_value);
		holder.billMoney = (TextView) contentView.findViewById(R.id.saleReturnEnterbillAdapter_billMoney_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<SaleReturnEnterBillModel>.HolderView contentView, SaleReturnEnterBillModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		
		holder.orderBillCode.setText(list.get(position).getRelatedBillCode());
		holder.enterBillCode.setText(list.get(position).getBillCode());
		holder.customerName.setText(list.get(position).getCustomer().getName());
		holder.orderMakeTime.setValue(list.get(position).getMakeTime());
		holder.outDate.setValue(list.get(position).getOutDate());
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
		holder.billMoney.setText(String.valueOf(list.get(position).getBillMoney()));
	}
	
	class ViewHold extends HolderView{
		TextView orderBillCode;		//订单号
		TextView enterBillCode;		//入库单号
		TextView customerName;	//客户名称
		DateField orderMakeTime;		//下单时间
		DateField outDate;			//入库日期
		TextView itemCount;			//总数量
		TextView billMoney;
		
	}
}