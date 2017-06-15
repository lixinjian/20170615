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

public class PickBatchDetailForNotPickAdapter extends CommonAdapter<PickBatchDetailModel> {
	
	public PickBatchDetailForNotPickAdapter(Context context, List<PickBatchDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_pick_batch_detail_for_not_pick_adapter;
	}

	@Override
	public CommonAdapter<PickBatchDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_skuCode_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_skuName_value);
		holder.expectUnitQty = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_expectUnitQty_value);
		holder.expectMinUnitQty = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_expectMinUnitQty_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_storeName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_shelfCode_value);
		holder.produceDate = (DateField) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_produceDate_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_sysBatchNo_value);
		holder.spec = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_spec_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.pickBatchDetailForNotPickAdapter_unitName_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PickBatchDetailModel>.HolderView contentView, PickBatchDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PickBatchDetailModel pickBatchDetailModel = list.get(position);
		holder.skuCode.setText(pickBatchDetailModel.getSku().getCode());
		holder.skuName.setText(pickBatchDetailModel.getSku().getGoods().getName());
		holder.expectUnitQty.setText(String.valueOf(pickBatchDetailModel.getExpectUnitQty()));
		holder.expectMinUnitQty.setText(String.valueOf(pickBatchDetailModel.getExpectMinUnitQty()));
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
		TextView expectUnitQty; //应捡件数
		TextView expectMinUnitQty; //应捡散数
		TextView shelfCode;
		TextView storeName;
		DateField produceDate;
		TextView sysBatchNo;
		TextView spec;//规格
		TextView specQty;//包装数量
		TextView unitName;//单位
	}
}