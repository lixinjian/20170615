package com.ds365.erp.wms.pda.view.stock.stockshift.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StockShiftDetailForUpAdapter extends CommonAdapter<StockShiftDetailModel> {

	public StockShiftDetailForUpAdapter(Context context, List<StockShiftDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_stock_shelf_detail_for_up_adapter;
	}

	@Override
	public CommonAdapter<StockShiftDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.shelfCodeValue = (TextView) contentView.findViewById(R.id.stockShiftDetailForUpAdapter_shelfCode_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.stockShiftDetailForUpAdapter_storeName_value);
		holder.unitQtyValue = (TextView) contentView.findViewById(R.id.stockShiftDetailForUpAdapter_unitQty_value);
		holder.minUnitQtyValue = (TextView) contentView.findViewById(R.id.stockShiftDetailForUpAdapter_minUnitQty_value);
		holder.qty = (TextView) contentView.findViewById(R.id.stockShiftDetailForUpAdapter_qty_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<StockShiftDetailModel>.HolderView contentView, StockShiftDetailModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		StockShiftDetailModel detailModel = list.get(position);
		holder.shelfCodeValue.setText(detailModel.getUpShelf().getCode());
		holder.storeName.setText(detailModel.getUpStore().getName());
		holder.unitQtyValue.setText(String.valueOf(detailModel.getUnitQty()));
		holder.minUnitQtyValue.setText(String.valueOf(detailModel.getMinUnitQty()));
		holder.qty.setText(String.valueOf(detailModel.getQty()));
		
	}
	
	class ViewHold extends HolderView{	
		TextView shelfCodeValue;
		TextView storeName;
		TextView unitQtyValue;
		TextView minUnitQtyValue;
		TextView qty;//总数量
	}
	
}
