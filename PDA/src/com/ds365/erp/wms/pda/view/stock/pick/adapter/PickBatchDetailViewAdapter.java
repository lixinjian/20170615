package com.ds365.erp.wms.pda.view.stock.pick.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PickBatchDetailViewAdapter extends CommonAdapter<PickBatchDetailModel> {
	
	public PickBatchDetailViewAdapter(Context context, List<PickBatchDetailModel> list) {
		super(context, list);
	}
	

	@Override
	public int getContentViewId() {
		return R.layout.stock_pick_batch_detail_view_adapter;
	}

	@Override
	public CommonAdapter<PickBatchDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_skuCode_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_skuName_value);
		holder.expectQty = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_expectQty_value);
		holder.pickQty = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_pickQty_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_storeName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_shelfCode_value);
		holder.produceDate = (DateField) contentView.findViewById(R.id.pickBatchDetailViewAdapter_produceDate_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_sysBatchNo_value);
		holder.spec = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_spec_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.pickBatchDetailViewAdapter_unitName_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PickBatchDetailModel>.HolderView contentView, PickBatchDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PickBatchDetailModel pickBatchDetailModel = list.get(position);
		holder.skuCode.setText(pickBatchDetailModel.getSku().getCode());
		holder.skuName.setText(pickBatchDetailModel.getSku().getGoods().getName());
		holder.expectQty.setText(String.valueOf(pickBatchDetailModel.getExpectQty()));
		holder.pickQty.setText(String.valueOf(pickBatchDetailModel.getPickQty()));
		holder.spec.setText(pickBatchDetailModel.getSpec());
		holder.specQty.setText(String.valueOf(pickBatchDetailModel.getSpecQty()));
		holder.storeName.setText(pickBatchDetailModel.getStore().getName());
		holder.shelfCode.setText(pickBatchDetailModel.getShelf().getCode());
		holder.produceDate.setValue(pickBatchDetailModel.getProduceDate());
		holder.sysBatchNo.setText(pickBatchDetailModel.getSysBatchNo());
		holder.unitName.setText(pickBatchDetailModel.getUnitName());
	}
	
	class ViewHold extends HolderView{
		TextView skuCode;		//sku码
		TextView skuName;		//sku名称
		TextView expectQty; //应捡总数量
		TextView pickQty; //实捡总数量
		TextView shelfCode;
		TextView storeName;
		DateField produceDate;
		TextView sysBatchNo;
		TextView spec;//规格
		TextView specQty;//包装数量
		TextView unitName;//单位
	}
}