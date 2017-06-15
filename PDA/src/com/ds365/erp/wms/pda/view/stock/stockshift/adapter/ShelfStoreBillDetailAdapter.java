package com.ds365.erp.wms.pda.view.stock.stockshift.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShelfStoreBillDetailAdapter extends  CommonAdapter<String>{

	public ShelfStoreBillDetailAdapter(Context context, List<String> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_shift_store_bill_detail_adapter;
	}

	@Override
	public CommonAdapter<String>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.shiftStoreBillDetailAdapter_billCode_value);
		holder.state = (TextView) contentView.findViewById(R.id.shiftStoreBillDetailAdapter_state_value);
		holder.data = (TextView) contentView.findViewById(R.id.shiftStoreBillDetailAdapter_data_value);
		holder.totleCount = (TextView) contentView.findViewById(R.id.shiftStoreBillDetailAdapter_totleCount_vale);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<String>.HolderView contentView, String arg2) {
		ViewHold holder = (ViewHold) contentView;
		
	}

	class ViewHold extends HolderView{
		TextView billCode;
		TextView state;
		TextView data;
		TextView totleCount;
	}
}
