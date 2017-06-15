package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderBillModel;
import com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.activity.PurchaseReturnOutBillActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;;

public class PurchaseReturnOrderBillAdapter extends CommonAdapter<PurchaseReturnOrderBillModel> {

	private boolean returnOutterButtonShowFlag;
	public static final  String SER_KEY=PdaConstants.nextSerKey();

	public void setReturnOutterButtonShowFlag(boolean returnOutterButtonShowFlag){
		this.returnOutterButtonShowFlag = returnOutterButtonShowFlag;
	}
	
	public PurchaseReturnOrderBillAdapter(Context context,List<PurchaseReturnOrderBillModel> list) {
		super(context,list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_order_bill_adapter;
	}

	@Override
	public CommonAdapter<PurchaseReturnOrderBillModel>.HolderView getHoldView(int position, View contentView,ViewGroup parent) {
		
		ViewHold holder = new ViewHold();
		
		holder.billCode = (TextView) contentView.findViewById(R.id.purchaseReturnOrderBillAdapter_billCode_value);
		holder.supplierName = (TextView) contentView.findViewById(R.id.purchaseReturnOrderBillAdapter_supplierName_value);
		holder.makeTime = (DateField) contentView.findViewById(R.id.purchaseReturnOrderBillAdapter_makeTime_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.purchaseReturnOrderBillAdapter_itemCount_value);
		holder.returnOutterButton = (TextView) contentView.findViewById(R.id.purchaseReturnOrderBillAdapter_returnOutterButton);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<PurchaseReturnOrderBillModel>.HolderView contentView, PurchaseReturnOrderBillModel item) {
		
		ViewHold holder = (ViewHold) contentView;
		
		if (returnOutterButtonShowFlag) {
			holder.returnOutterButton.setVisibility(View.VISIBLE); 
		}else {
			holder.returnOutterButton.setVisibility(View.GONE);
		}
		
		holder.billCode.setText(list.get(position).getBillCode());
		holder.supplierName.setText(list.get(position).getSupplier().getName());
		holder.makeTime.setValue(list.get(position).getMakeTime());
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
		
		holder.returnOutterButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForSeria(context, PurchaseReturnOutBillActivity.class, 
						SER_KEY, list.get(position), null);
				
			}
		});
	}
	
	class ViewHold extends HolderView{
		TextView billCode;		//订单号
		TextView supplierName;	//供应商名称
		DateField makeTime;			//日期时间
		TextView itemCount;			//总数量
		TextView returnOutterButton;//退货按钮
	}
}
