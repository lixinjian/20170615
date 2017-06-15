package com.ds365.erp.wms.pda.view.stock.putaway.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskBillModel;
import com.ds365.erp.wms.pda.view.stock.putaway.activity.PutawayTaskDetailActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class PutawayTaskBillAdapter extends CommonAdapter<PutawayTaskBillModel> {

	private boolean putawayButtonShowFlag;
	public static final  String SER_KEY=PdaConstants.nextSerKey();

	public void setPutawayButtonShowFlag(boolean putawayButtonShowFlag){
		this.putawayButtonShowFlag = putawayButtonShowFlag;
	}
	
	public PutawayTaskBillAdapter(Context context, List<PutawayTaskBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_putaway_task_bill_adapter;
	}

	@Override
	public CommonAdapter<PutawayTaskBillModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.putawayTaskBillAdapter_billCode_value);
		holder.relatedBillCode = (TextView) contentView.findViewById(R.id.putawayTaskBillAdapter_relatedBillCode_value);
		holder.putterName = (TextView) contentView.findViewById(R.id.putawayTaskBillAdapter_putterName_value);
		holder.putawayButton = (TextView) contentView.findViewById(R.id.putawayTaskBillAdapter_putaway_button);
		holder.putDate = (DateField) contentView.findViewById(R.id.putawayTaskBillAdapter_putDate_value);
		holder.putDateLabel = (TextView) contentView.findViewById(R.id.putawayTaskBillAdapter_putDate_label);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PutawayTaskBillModel>.HolderView contentView, PutawayTaskBillModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		if (putawayButtonShowFlag) {
			holder.putawayButton.setVisibility(View.VISIBLE); 
			holder.putDateLabel.setVisibility(View.INVISIBLE);
		}else {
			holder.putawayButton.setVisibility(View.GONE);
			holder.putDateLabel.setVisibility(View.VISIBLE);
		}
		
		holder.billCode.setText(list.get(position).getBillCode());
		holder.relatedBillCode.setText(list.get(position).getRelatedBillCode());
		holder.putterName.setText(list.get(position).getPutter().getName());
		holder.putDate.setValue(list.get(position).getPutDate());
		holder.putawayButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForSeria(context, 
						PutawayTaskDetailActivity.class, SER_KEY, list.get(position), null);
			}
		});
	}

	class ViewHold extends HolderView{
		TextView billCode;
		DateField putDate;
		TextView putDateLabel;
		TextView relatedBillCode;
		TextView putterName;
		TextView putawayButton;

	}
}
