package com.ds365.erp.wms.pda.view.stock.putaway.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskRecordModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PutAwayTaskRecordAdapter extends CommonAdapter<PutAwayTaskRecordModel> {

	public PutAwayTaskRecordAdapter(Context context, List<PutAwayTaskRecordModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_putaway_task_record_adapter;
	}

	@Override
	public CommonAdapter<PutAwayTaskRecordModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.shelfCodeValue = (TextView) contentView.findViewById(R.id.putawaytaskRecordAdapter_shelfCode_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.putawaytaskRecordAdapter_storeName_value);
		holder.unitQtyValue = (TextView) contentView.findViewById(R.id.putawaytaskRecordAdapter_unitQty_editValue);
		holder.minUnitQtyValue = (TextView) contentView.findViewById(R.id.putawaytaskRecordAdapter_minUnitQty_editValue);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<PutAwayTaskRecordModel>.HolderView contentView, PutAwayTaskRecordModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		PutAwayTaskRecordModel detailModel = list.get(position);
		holder.shelfCodeValue.setText(detailModel.getShelf().getCode());
		holder.storeName.setText(detailModel.getStore().getName());
		holder.unitQtyValue.setText(String.valueOf(detailModel.getUnitQty()));
		holder.minUnitQtyValue.setText(String.valueOf(detailModel.getMinUnitQty()));
		
	}
	class ViewHold extends HolderView{	
		TextView shelfCodeValue;
		TextView storeName;
		TextView unitQtyValue;
		TextView minUnitQtyValue;
	}
}
