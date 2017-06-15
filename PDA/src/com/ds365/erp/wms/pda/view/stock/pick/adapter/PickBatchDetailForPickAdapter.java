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

public class PickBatchDetailForPickAdapter extends CommonAdapter<PickBatchDetailModel> {
	
	public PickBatchDetailForPickAdapter(Context context, List<PickBatchDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_pick_batch_detail_for_pick_adapter;
	}

	@Override
	public CommonAdapter<PickBatchDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_skuCode_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_skuName_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_minUnitQty_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_storeName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_shelfCode_value);
		holder.produceDate = (DateField) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_produceDate_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_sysBatchNo_value);
		holder.spec = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_spec_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.pickBatchDetailForPickAdapter_unitName_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PickBatchDetailModel>.HolderView contentView, PickBatchDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PickBatchDetailModel pickBatchDetailModel = list.get(position);
		holder.skuCode.setText(pickBatchDetailModel.getSku().getCode());
		holder.skuName.setText(pickBatchDetailModel.getSku().getGoods().getName());
		holder.unitQty.setText(String.valueOf(pickBatchDetailModel.getPickUnitQty()));
		holder.minUnitQty.setText(String.valueOf(pickBatchDetailModel.getPickMinUnitQty()));
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
		TextView unitQty; //
		TextView minUnitQty; //
		TextView shelfCode;
		TextView storeName;
		DateField produceDate;
		TextView sysBatchNo;
		TextView spec;//规格
		TextView specQty;//包装数量
		TextView unitName;//单位
	}
}