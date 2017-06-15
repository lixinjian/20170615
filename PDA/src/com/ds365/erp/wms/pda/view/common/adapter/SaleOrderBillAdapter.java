package com.ds365.erp.wms.pda.view.common.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleOrderBillModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SaleOrderBillAdapter extends CommonAdapter<SaleOrderBillModel> {

	public SaleOrderBillAdapter(Context context, List<SaleOrderBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		
		return R.layout.common_sale_order_bill_adapter;
	}

	@Override
	public CommonAdapter<SaleOrderBillModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.commonSaleOrderBillAdapter_billCode_value);
		holder.makeTime = (DateField) contentView.findViewById(R.id.commonSaleOrderBillAdapter_makeTime_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.commonSaleOrderBillAdapter_itemCount_value);
		holder.customerName = (TextView) contentView.findViewById(R.id.commonSaleOrderBillAdapter_customerName_value);
		holder.billMoney = (TextView) contentView.findViewById(R.id.commonSaleOrderBillAdapter_billMoney_value);
		holder.receivableMoney = (TextView) contentView.findViewById(R.id.commonSaleOrderBillAdapter_receivableMoney_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<SaleOrderBillModel>.HolderView contentView, SaleOrderBillModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.billCode.setText(list.get(position).getBillCode());
		holder.makeTime.setValue(list.get(position).getMakeTime());
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
		holder.customerName.setText(list.get(position).getCustomer().getName());
		holder.billMoney.setText(String.valueOf(list.get(position).getBillMoney()));
		holder.receivableMoney.setText(String.valueOf(list.get(position).getReceivableMoney()));
		
	}
	
	class ViewHold extends HolderView{
		TextView billCode;		//订单号
		DateField makeTime;			//退货时间
		TextView itemCount;			//总数量
		TextView customerName;		//客户名称
		TextView billMoney;//订单金额
		TextView receivableMoney;//应收金额
	}
}