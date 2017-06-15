package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PurchaseEnterDetailForGroupAdapter extends CommonAdapter<PurchaseEnterDetailModel> {
	
	public PurchaseEnterDetailForGroupAdapter(Context context, List<PurchaseEnterDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_detail_for_group_adapter;
	}

	@Override
	public HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.produceDate = (DateField) contentView.findViewById(R.id.purchaseEnterDetailForGroupAdapter_produceDate_value);
		holder.produceBatchNo = (TextView) contentView.findViewById(R.id.purchaseEnterDetailForGroupAdapter_produceBatchNo_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.purchaseEnterDetailForGroupAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.purchaseEnterDetailForGroupAdapter_minUnitQty_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, HolderView contentView, PurchaseEnterDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PurchaseEnterDetailModel enterDetail  =list.get(position);
		holder.produceDate.setValue(enterDetail.getProduceDate());
		holder.produceBatchNo.setText(enterDetail.getProduceBatchNo());
		holder.unitQty.setText(String.valueOf(enterDetail.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(enterDetail.getMinUnitQty()));
	}
	
	public class ViewHold extends HolderView{
		DateField produceDate;		
		TextView produceBatchNo;		
		TextView unitQty;		
		TextView minUnitQty;	
	}
}