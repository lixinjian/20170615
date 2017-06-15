package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderBillModel;
import com.ds365.erp.wms.pda.view.enterwarehouse.activity.SaleReturnEnterBillActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class SaleReturnOrderBillAdapter extends CommonAdapter<SaleReturnOrderBillModel> {

	private boolean enterButtonShowFlag;
	public static final  String SER_KEY=PdaConstants.nextSerKey();

	public void setEnterButtonShowFlag(boolean enterButtonShowFlag){
		this.enterButtonShowFlag = enterButtonShowFlag;
	}
	
	public SaleReturnOrderBillAdapter(Context context, List<SaleReturnOrderBillModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_order_bill_adapter;
	}

	@Override
	public CommonAdapter<SaleReturnOrderBillModel>.HolderView getHoldView(final int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.saleReturnOrderbill_adapter_billCode_value);
		holder.customerName = (TextView) contentView.findViewById(R.id.saleReturnOrderbill_adapter_customer_value);
		holder.makeTime = (DateField) contentView.findViewById(R.id.saleReturnOrderbill_adapter_makeTime_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.saleReturnOrderbill_adapter_itemCount_value);
		holder.billMoney = (TextView) contentView.findViewById(R.id.saleReturnOrderbill_adapter_billMoney_value);
		holder.billState = (TextView) contentView.findViewById(R.id.saleReturnOrderbill_adapter_billState_value);
		holder.enterWarehouseButton = (TextView) contentView.findViewById(R.id.saleReturnOrderbill_adapter_enterWarehouseButton);
		
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<SaleReturnOrderBillModel>.HolderView contentView, SaleReturnOrderBillModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		if (enterButtonShowFlag) {
			holder.enterWarehouseButton.setVisibility(View.VISIBLE); 
		}else {
			holder.enterWarehouseButton.setVisibility(View.GONE);
		}
		
		holder.billCode.setText(list.get(position).getBillCode());
		holder.customerName.setText(list.get(position).getCustomer().getName());
		holder.makeTime.setValue(list.get(position).getMakeTime());
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
		holder.billMoney.setText(String.valueOf(list.get(position).getBillMoney()));
		holder.billState.setText(list.get(position).getBillState().getName());
		
		holder.enterWarehouseButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForSeria(context, SaleReturnEnterBillActivity.class, 
						SER_KEY, list.get(position), null);
				
			}
		});
	}
	
	class ViewHold extends HolderView{
		TextView billCode;		//订单号
		TextView customerName;	//客户名称
		DateField makeTime;			//退货时间
		TextView itemCount;			//总数量
		TextView billState;		//状态
		TextView enterWarehouseButton;
		TextView billMoney;
	}
}