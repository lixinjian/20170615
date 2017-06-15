package com.ds365.erp.wms.pda.view.stock.pick.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PickBatchDetailAdapter extends CommonAdapter<PickBatchDetailModel> {
	
	public PickBatchDetailAdapter(Context context, List<PickBatchDetailModel> list) {
		super(context, list);
	}
	

	@Override
	public int getContentViewId() {
		return R.layout.stock_pick_batch_detail_adapter;
	}

	@Override
	public CommonAdapter<PickBatchDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_skuCode_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_skuName_value);
		holder.expectQty = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_expectQty_value);
		holder.pickQty = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_pickQty_value);
		holder.makeSure = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_makesure);
		holder.makeSureImg = (ImageView) contentView.findViewById(R.id.pickBatchDetailAdapter_makesureImg);
		holder.storeName = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_storeName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_shelfCode_value);
		holder.produceDate = (DateField) contentView.findViewById(R.id.pickBatchDetailAdapter_produceDate_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_sysBatchNo_value);
		holder.spec = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_spec_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_specQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.pickBatchDetailAdapter_unitName_value);
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
		if (pickBatchDetailModel.isCheckFlag()) {
			holder.makeSure.setText("已检查");
			holder.makeSureImg.setVisibility(View.VISIBLE);
		}else{
			holder.makeSure.setText("未检查");
			holder.makeSureImg.setVisibility(View.GONE);
		}
		
		holder.makeSure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!pickBatchDetailModel.isCheckFlag()) {
					
					StringBuilder message=new StringBuilder();
					if (pickBatchDetailModel.getPickUnitQty() == null && pickBatchDetailModel.getPickMinUnitQty() == null) {
						message.append("件数和散数不能同时为空,请录入");
					}
					if(message.length()!=0){
						T.showShort(context, message.toString());
						return;
					}
					
					holder.makeSure.setText("已检查");
					holder.makeSureImg.setVisibility(View.VISIBLE);
					pickBatchDetailModel.setCheckFlag(true);
        			
				} else if (pickBatchDetailModel.isCheckFlag()) {
					holder.makeSure.setText("未检查");
					holder.makeSureImg.setVisibility(View.GONE);
					pickBatchDetailModel.setCheckFlag(false);
				}
			}
		});
	}
	
	class ViewHold extends HolderView{
		TextView skuCode;		//sku码
		TextView skuName;		//sku名称
		TextView expectQty; //应捡总数量
		TextView pickQty; //实捡总数量
		TextView makeSure;		
		ImageView makeSureImg;
		TextView shelfCode;
		TextView storeName;
		DateField produceDate;
		TextView sysBatchNo;
		TextView spec;//规格
		TextView specQty;//包装数量
		TextView unitName;//单位
	}
}