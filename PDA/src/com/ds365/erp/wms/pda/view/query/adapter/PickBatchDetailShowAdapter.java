package com.ds365.erp.wms.pda.view.query.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PickBatchDetailShowAdapter extends CommonAdapter<PickBatchDetailModel> {
	
	public PickBatchDetailShowAdapter(Context context, List<PickBatchDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.query_pick_batch_detail_show_adapter;
	}

	@Override
	public CommonAdapter<PickBatchDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_skuCode_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_skuName_value);
		holder.expectQty = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_expectQty_value);
		holder.pickQty = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_pickQty_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_storeName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_shelfCode_value);
		holder.produceDate = (DateField) contentView.findViewById(R.id.pickBatchDetailShowAdapter_produceDate_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_sysBatchNo_value);
		holder.spec = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_spec_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_unitName_value);
		holder.pickFlag = (TextView) contentView.findViewById(R.id.pickBatchDetailShowAdapter_pickFlag_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PickBatchDetailModel>.HolderView contentView, PickBatchDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PickBatchDetailModel pickBatchDetailModel = list.get(position);
		holder.skuCode.setText(pickBatchDetailModel.getSku().getCode());
		holder.skuName.setText(pickBatchDetailModel.getSku().getName());
		holder.expectQty.setText(String.valueOf(pickBatchDetailModel.getExpectQty()));
		holder.pickQty.setText(String.valueOf(pickBatchDetailModel.getPickQty()));
		holder.spec.setText(pickBatchDetailModel.getSpec());
		holder.specQty.setText(String.valueOf(pickBatchDetailModel.getSpecQty()));
		holder.storeName.setText(pickBatchDetailModel.getStore().getName());
		holder.shelfCode.setText(pickBatchDetailModel.getShelf().getCode());
		holder.produceDate.setValue(pickBatchDetailModel.getProduceDate());
		holder.sysBatchNo.setText(pickBatchDetailModel.getSysBatchNo());
		holder.unitName.setText(pickBatchDetailModel.getUnitName());
		if(pickBatchDetailModel.getPickFlag()){
			holder.pickFlag.setText("已拣货");
		}else{
			holder.pickFlag.setText("待拣货");
		}
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
		TextView pickFlag;
	}
}