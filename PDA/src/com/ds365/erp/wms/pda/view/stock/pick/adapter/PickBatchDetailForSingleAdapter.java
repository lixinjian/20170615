package com.ds365.erp.wms.pda.view.stock.pick.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PickBatchDetailForSingleAdapter extends CommonAdapter<PickBatchDetailModel> {

	public PickBatchDetailForSingleAdapter(Context context, List<PickBatchDetailModel> list) {
		super(context, list);
	}

	/**
	 * 删除选中的item
	 * @param delete
	 */
	public void deleteItem(int delete){
		list.remove(delete);
		notifyDataSetChanged();
	}
	
	@Override
	public int getContentViewId() {
		return R.layout.stock_pick_batch_detail_for_single_adapter;
	}

	@Override
	public CommonAdapter<PickBatchDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.unitQty = (TextView) contentView.findViewById(R.id.pickBatchDetailForSingleAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.pickBatchDetailForSingleAdapter_minUnitQty_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.pickBatchDetailForSingleAdapter_storeName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.pickBatchDetailForSingleAdapter_shelfCode_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.pickBatchDetailForSingleAdapter_sysBatchNo_value);
		holder.qty = (TextView) contentView.findViewById(R.id.pickBatchDetailForSingleAdapter_qty_value);

		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PickBatchDetailModel>.HolderView contentView,
			PickBatchDetailModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		PickBatchDetailModel pickBatchDetailModel = list.get(position);
		holder.storeName.setText(pickBatchDetailModel.getStore().getName());
		holder.shelfCode.setText(pickBatchDetailModel.getShelf().getCode());
		holder.sysBatchNo.setText(pickBatchDetailModel.getSysBatchNo());
		holder.unitQty.setText(String.valueOf(pickBatchDetailModel.getPickUnitQty()));
		holder.minUnitQty.setText(String.valueOf(pickBatchDetailModel.getPickMinUnitQty()));
		holder.qty.setText(String.valueOf(pickBatchDetailModel.getPickQty()));

	}

	class ViewHold extends HolderView {
		TextView unitQty; // 件数
		TextView minUnitQty; // 件数
		TextView qty; // 总数量
		TextView shelfCode;
		TextView storeName;
		TextView sysBatchNo;
	}
}