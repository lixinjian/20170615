package com.ds365.erp.wms.pda.view.stock.pick.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.pickbill.PickBillModel;
import com.ds365.erp.wms.pda.view.stock.pick.activity.PickBillRegistActivity;
import com.ds365.erp.wms.pda.view.stock.pick.activity.PickBillRegistForSingleActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PickBillListAdapter extends CommonAdapter<PickBillModel>{

	private boolean pickButtonShowFlag;
	public static final  String SER_KEY=PdaConstants.nextSerKey();

	public void setPickButtonShowFlag(boolean pickButtonShowFlag){
		this.pickButtonShowFlag = pickButtonShowFlag;
	}

	public PickBillListAdapter(Context context, List<PickBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.stock_pick_bill_list_adapter;
	}

	@Override
	public CommonAdapter<PickBillModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.pickBillListAdapter_billCode_value);
		holder.pickDate = (DateField) contentView.findViewById(R.id.pickBillListAdapter_pickDate_value);
		holder.makeTime = (DateField) contentView.findViewById(R.id.pickBillListAdapter_makeTime_value);
		holder.pickerNameValue = (TextView) contentView.findViewById(R.id.pickBillListAdapter_pickerName_value);
		holder.makerNameValue = (TextView) contentView.findViewById(R.id.pickBillListAdapter_makerName_value);
		holder.pickerLayout = (LinearLayout) contentView.findViewById(R.id.pickBillListAdapter_picker_layout);
		holder.pickButton = (TextView) contentView.findViewById(R.id.pickBillListAdapter_pickButton);
		holder.pickForSingleButton = (TextView) contentView.findViewById(R.id.pickBillListAdapter_pickForSingleButton);
		holder.pickDateLable = (TextView) contentView.findViewById(R.id.pickBillListAdapter_pickDate_lable);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PickBillModel>.HolderView contentView, PickBillModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		if (pickButtonShowFlag) {
			holder.pickButton.setVisibility(View.GONE); 
			holder.pickForSingleButton.setVisibility(View.VISIBLE); 
			holder.pickerLayout.setVisibility(View.GONE);
			holder.pickDateLable.setVisibility(View.GONE);
		}else {
			holder.pickButton.setVisibility(View.GONE);
			holder.pickForSingleButton.setVisibility(View.GONE);
			holder.pickerLayout.setVisibility(View.VISIBLE);
			holder.pickDateLable.setVisibility(View.VISIBLE);
		}
		
		holder.pickButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForSeria(context, 
						PickBillRegistActivity.class, SER_KEY, list.get(position), null);
			}
		});
		
		holder.pickForSingleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForSeria(context, 
						PickBillRegistForSingleActivity.class, SER_KEY, list.get(position), null);
			}
		});
		
		holder.billCode.setText(list.get(position).getBillCode());
		holder.pickDate.setValue(list.get(position).getPickDate());
		holder.makeTime.setValue(list.get(position).getMakeTime());
		holder.makerNameValue.setText(list.get(position).getMaker().getName());
		if(list.get(position).getPicker() != null)
			holder.pickerNameValue.setText(list.get(position).getPicker().getName());
	}

	class ViewHold extends HolderView{
		TextView billCode;
		DateField pickDate;//拣货日期
		DateField makeTime;//制单日期
		TextView pickerNameValue;	//拣货员
		TextView makerNameValue;	//制单员
		LinearLayout pickerLayout;
		TextView  pickButton;	//批量拣货按钮
		TextView pickForSingleButton;//单条拣货按钮
		TextView pickDateLable;
	}
}