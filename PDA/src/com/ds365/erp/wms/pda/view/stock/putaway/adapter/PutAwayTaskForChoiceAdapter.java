package com.ds365.erp.wms.pda.view.stock.putaway.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskCreateParamsModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PutAwayTaskForChoiceAdapter extends CommonAdapter<PutawayTaskCreateParamsModel> {

	public PutAwayTaskForChoiceAdapter(Context context, List<PutawayTaskCreateParamsModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_putaway_task_adapter;
	}

	@Override
	public CommonAdapter<PutawayTaskCreateParamsModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.shelfCode = (TextView) contentView.findViewById(R.id.putawaytask_adapter_shelfCode_value);
		holder.putNum = (TextView) contentView.findViewById(R.id.putawaytask_adapter_this_shelf_count_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.putawaytask_adapter_skuName_value);
		holder.spec = (TextView) contentView.findViewById(R.id.putawaytask_adapter_spec_value);
		holder.batchNo = (TextView) contentView.findViewById(R.id.putawaytask_adapter_sysBatchNo_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<PutawayTaskCreateParamsModel>.HolderView contentView, PutawayTaskCreateParamsModel arg2) {
		ViewHold holder = (ViewHold) contentView;
//		holder.shelfCode.setText(list.get(position).getShelfCode());
//		holder.putNum.setText(String.valueOf(list.get(position).getQty())); 
//		holder.skuName.setText(list.get(position).getSkuName());
//		holder.spec.setText(list.get(position).getSpec());
	}
	
	class ViewHold extends HolderView{
		TextView shelfCode;	//货位编码
		TextView putNum;	//上架数量
		TextView skuName;	//SKU名称
		TextView spec;		//规格
		TextView batchNo;		//批次
	}
}