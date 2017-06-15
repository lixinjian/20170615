package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderBillModel;
import com.ds365.erp.wms.pda.view.enterwarehouse.activity.PurchaseEnterBillActivity2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class PurchaseOrderBillAdapter extends CommonAdapter<PurchaseOrderBillModel> {
	
	private boolean receivedButtonShowFlag;
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	public static final String REQUEST_CODE_KEY = PurchaseOrderBillAdapter.class.getName();
	
	/**
	 * 设置收货按钮是否显示
	 * @param receivedButtonShowFlag false为隐藏,true为显示
	 */
	public void setReceivedButtonShowFlag(boolean receivedButtonShowFlag){
		this.receivedButtonShowFlag = receivedButtonShowFlag;
	}
	
	public PurchaseOrderBillAdapter(Context context, List<PurchaseOrderBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_purchase_order_bill_adapter;
	}

	@Override
	public CommonAdapter<PurchaseOrderBillModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.purchaseorderbill_adapter_billCode_value);
		holder.supplierName = (TextView) contentView.findViewById(R.id.purchaseorderbill_adapter_supplierName_value);
		holder.makeTime = (DateField) contentView.findViewById(R.id.purchaseorderbill_adapter_makeTime_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.purchaseorderbill_adapter_itemCount);
		holder.arrivalState = (TextView) contentView.findViewById(R.id.purchaseorderbill_adapter_arrivalState);
		holder.receivedButton = (TextView) contentView.findViewById(R.id.purchaseorderbill_adapter_receviedButton);
		holder.billMoney = (TextView) contentView.findViewById(R.id.purchaseorderbill_adapter_billMoney_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PurchaseOrderBillModel>.HolderView contentView, PurchaseOrderBillModel item) {
		
		ViewHold holder = (ViewHold) contentView;
		
		if (receivedButtonShowFlag) {
			holder.receivedButton.setVisibility(View.VISIBLE);
		}else{
			holder.receivedButton.setVisibility(View.GONE);
		}
		
		holder.billCode.setText(list.get(position).getBillCode());
		if (null != list.get(position).getSupplier()) {
			holder.supplierName.setText(list.get(position).getSupplier().getName());
		}
		holder.makeTime.setValue(list.get(position).getMakeTime());
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
//		holder.arrivalState.setText(list.get(position).getBillState().getName());	//收货状态,不需要显示
		holder.billMoney.setText(String.valueOf(list.get(position).getBillMoney()));
		holder.receivedButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				IntentUtils.startActivityForSeria(context, PurchaseEnterBillActivity.class, 
//						SER_KEY, list.get(position), null);
				Intent intent = new Intent(context,PurchaseEnterBillActivity2.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(SER_KEY,list.get(position));  
				intent.putExtras(bundle);
				intent.putExtra(REQUEST_CODE_KEY, REQUEST_CODE);
				context.startActivity(intent);
				
			}
		});
	}
	
	class ViewHold extends HolderView{
		TextView billCode;		//采购订单号
		TextView supplierName;	//供应商名称
		DateField makeTime;			//订单生成日期
		TextView itemCount;			//总数量
		TextView arrivalState;	//到货状态
		TextView receivedButton;//收货按钮
		TextView billMoney;//总金额
	}
}