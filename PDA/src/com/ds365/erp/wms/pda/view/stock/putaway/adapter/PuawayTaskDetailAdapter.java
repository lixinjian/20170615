package com.ds365.erp.wms.pda.view.stock.putaway.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PuawayTaskDetailAdapter extends CommonAdapter<PutawayTaskDetailModel> {

	public PuawayTaskDetailAdapter(Context context, List<PutawayTaskDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_putaway_task_detail_adapter;
	}

	@Override
	public CommonAdapter<PutawayTaskDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.spec = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_spec_value);
		holder.qty = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_totalCount_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_specQty_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_skuName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_shelfCode_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_skuCode_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_storeName_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_unitName_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.putawayTaskDetailAdapter_minUnitQty_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<PutawayTaskDetailModel>.HolderView contentView, PutawayTaskDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PutawayTaskDetailModel putawayTaskDetailModel = list.get(position);
		holder.qty.setText(String.valueOf(putawayTaskDetailModel.getQty()));
		holder.skuName.setText(putawayTaskDetailModel.getSku().getGoods().getName());
		holder.shelfCode.setText(putawayTaskDetailModel.getShelf().getCode());
		holder.skuCode.setText(putawayTaskDetailModel.getSku().getCode());
		holder.storeName.setText(putawayTaskDetailModel.getStore().getName());
		holder.unitName.setText(putawayTaskDetailModel.getUnitName());
		holder.spec.setText(putawayTaskDetailModel.getSku().getSpec());
		holder.specQty.setText(String.valueOf(putawayTaskDetailModel.getSpecQty()));
		holder.unitQty.setText(String.valueOf(putawayTaskDetailModel.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(putawayTaskDetailModel.getMinUnitQty()));
	}
	class ViewHold extends HolderView{
		TextView spec;		//规格
		TextView qty;		//总数量
		TextView specQty;		//包装数量
		TextView unitQty;		//件数
		TextView minUnitQty;	//散数
		TextView skuName;		//
		TextView shelfCode;		//货位号
		TextView skuCode;		//编号
		TextView storeName;
		TextView unitName;
	}
}
