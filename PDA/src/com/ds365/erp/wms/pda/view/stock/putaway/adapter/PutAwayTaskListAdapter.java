package com.ds365.erp.wms.pda.view.stock.putaway.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskModel;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PutAwayTaskListAdapter extends CommonAdapter<PutAwayTaskModel> {
	
	public PutAwayTaskListAdapter(Context context, List<PutAwayTaskModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_putaway_task_list_adapter;
	}

	@Override
	public CommonAdapter<PutAwayTaskModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.spec = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_spec_value);
		holder.qty = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_totalCount_value);
		holder.remainQty = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_remainQty_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_skuName_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_sysBatchNo_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_shelfCode_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_code_value);
		holder.makeSure = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_makesure);
		holder.makeSureImg = (ImageView) contentView.findViewById(R.id.putawayTaskListAdapter_makesureImg);
		holder.storeName = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_storeName_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_unitName_value);
		holder.remainUnitQty = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_remainUnitQty_value);
		holder.remainMinUnitQty = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_remainMinUnitQty_value);
		holder.relativeBillCode = (TextView) contentView.findViewById(R.id.putawayTaskListAdapter_relativeBillCode_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PutAwayTaskModel>.HolderView contentView, PutAwayTaskModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PutAwayTaskModel putAwayTaskModel = list.get(position);
		holder.spec.setText(putAwayTaskModel.getSpec());
		holder.remainQty.setText(String.valueOf(putAwayTaskModel.getRemainQty()));
		holder.qty.setText(String.valueOf(putAwayTaskModel.getQty()));
		holder.skuName.setText(putAwayTaskModel.getSku().getName());
		holder.sysBatchNo.setText(putAwayTaskModel.getSysBatchNo());
		holder.shelfCode.setText(putAwayTaskModel.getShelf().getCode());
		holder.skuCode.setText(putAwayTaskModel.getSku().getCode());
		holder.storeName.setText(putAwayTaskModel.getStore().getName());
		holder.unitName.setText(putAwayTaskModel.getUnitName());
		holder.remainUnitQty.setText(String.valueOf(putAwayTaskModel.getRemainUnitQty()));
		holder.remainMinUnitQty.setText(String.valueOf(putAwayTaskModel.getRemainMinUnitQty()));
		holder.relativeBillCode.setText(putAwayTaskModel.getRelativeBillCode());
		if (putAwayTaskModel.isCheckFlag()) {
			holder.makeSure.setText("已检查");
			holder.makeSureImg.setVisibility(View.VISIBLE);
		}else{
			holder.makeSure.setText("未检查");
			holder.makeSureImg.setVisibility(View.GONE);
		}
		
		holder.makeSure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!putAwayTaskModel.isCheckFlag()) {
					holder.makeSure.setText("已检查");
					holder.makeSureImg.setVisibility(View.VISIBLE);
					putAwayTaskModel.setCheckFlag(true);
        			
				} else if (putAwayTaskModel.isCheckFlag()) {
					holder.makeSure.setText("未检查");
					holder.makeSureImg.setVisibility(View.GONE);
					putAwayTaskModel.setCheckFlag(false);
				}
			}
		});
	}
	
	class ViewHold extends HolderView{
		TextView spec;		//规格
		TextView qty;		//总数量
		TextView remainQty;		//剩余数量
		TextView remainUnitQty;		//剩余件数
		TextView remainMinUnitQty;		//剩余散数
		TextView skuName;		//
		TextView sysBatchNo;	//批次
		TextView shelfCode;		//货位号
		TextView skuCode;		//编号
		TextView storeName;
		TextView unitName;
		TextView makeSure;
		ImageView makeSureImg;
		TextView relativeBillCode;
	}
}