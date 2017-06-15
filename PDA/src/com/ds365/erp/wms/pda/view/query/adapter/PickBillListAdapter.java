package com.ds365.erp.wms.pda.view.query.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.pickbill.PickBillModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PickBillListAdapter extends CommonAdapter<PickBillModel>{

	public PickBillListAdapter(Context context, List<PickBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.query_pick_bill_list_adapter;
	}

	@Override
	public CommonAdapter<PickBillModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.pickBillListAdapter_billCode_value);
		holder.pickDate = (DateField) contentView.findViewById(R.id.pickBillListAdapter_pickDate_value);
		holder.pickerNameValue = (TextView) contentView.findViewById(R.id.pickBillListAdapter_pickerName_value);
		holder.billState = (TextView) contentView.findViewById(R.id.pickBillListAdapter_billState_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PickBillModel>.HolderView contentView, PickBillModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.billCode.setText(list.get(position).getBillCode());
		holder.pickDate.setValue(list.get(position).getPickDate());
		holder.pickerNameValue.setText(list.get(position).getPicker().getName());
		holder.billState.setText(list.get(position).getBillState().getName());
	}

	class ViewHold extends HolderView{
		TextView billCode;
		DateField pickDate;
		TextView pickerNameValue;	//拣货员
		TextView billState;
	}
}