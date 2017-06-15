package com.ds365.erp.wms.pda.view.common.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShelfSelectorAdapter extends CommonAdapter<ShelfModel>{

	public ShelfSelectorAdapter(Context context,List<ShelfModel> list) {
		super(context,list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.common_shelf_selector_adapter;
	}

	@Override
	public CommonAdapter<ShelfModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.shelfCode = (TextView) contentView.findViewById(R.id.common_shelfSelector_shelfCode_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.common_shelfSelector_storeName_value);
		holder.shelfTypeName = (TextView) contentView.findViewById(R.id.common_shelfSelector_shelfTypeName_value);
		holder.storeCode = (TextView) contentView.findViewById(R.id.common_shelfSelector_storeCode_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<ShelfModel>.HolderView contentView, ShelfModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.shelfCode.setText(list.get(position).getCode());
		holder.storeName.setText(list.get(position).getStore().getName());
		holder.shelfTypeName.setText(list.get(position).getShelfType().getName());
		holder.storeCode.setText(list.get(position).getStore().getCode());
	}

	class ViewHold extends HolderView{
		TextView shelfCode;		//货位
		TextView storeName;		//sku编号
		TextView shelfTypeName;
		TextView storeCode;	
	}
}
