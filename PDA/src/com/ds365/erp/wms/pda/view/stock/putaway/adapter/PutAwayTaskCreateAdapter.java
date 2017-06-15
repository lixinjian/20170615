package com.ds365.erp.wms.pda.view.stock.putaway.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PutAwayTaskCreateAdapter extends CommonAdapter<String> {
	
	public PutAwayTaskCreateAdapter(Context context, List<String> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_purchase_order_bill_adapter;
	}

	@Override
	public CommonAdapter<String>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
//		holder.billCoad = (TextView) contentView.findViewById(R.id.enterwarehouse_main_adapter_billCoad);
//		holder.supplierName = (TextView) contentView.findViewById(R.id.enterwarehouse_main_adapter_supplierName);
//		holder.date = (TextView) contentView.findViewById(R.id.enterwarehouse_main_adapter_date);
//		holder.total = (TextView) contentView.findViewById(R.id.enterwarehouse_main_adapter_total);
//		holder.arrivalState = (TextView) contentView.findViewById(R.id.enterwarehouse_main_adapter_arrivalState);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<String>.HolderView contentView, String arg2) {
		ViewHold holder = (ViewHold) contentView;
//		holder.billCoad.setText(list.get(position).getBillCode());
//		if (null != list.get(position).getSupplier()) {
//			holder.supplierName.setText(list.get(position).getSupplier().getName());
//		}
//		holder.date.setText(list.get(position).getCreateTime());
//		holder.total.setText(String.valueOf(list.get(position).getId()));
//		holder.arrivalState.setText(list.get(position).getBillState().getName());
	}
	
	class ViewHold extends HolderView{
		TextView billCoad;
		TextView supplierName;
		TextView date;
		TextView total;
		TextView arrivalState;
	}
}