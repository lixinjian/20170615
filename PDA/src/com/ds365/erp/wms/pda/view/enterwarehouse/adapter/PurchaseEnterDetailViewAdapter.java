package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PurchaseEnterDetailViewAdapter extends CommonAdapter<PurchaseEnterDetailModel> {
	
	public PurchaseEnterDetailViewAdapter(Context context, List<PurchaseEnterDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_detail_view_adapter;
	}

	@Override
	public HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_skuCode_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_minUnitQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_unitName_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_skuName_value);
		holder.specQty  =(TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_specQty_value);
		holder.storeName=(TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_storeName_value);
		holder.shelfCode=(TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_shelfCode_value);
		holder.billMoney=(TextView) contentView.findViewById(R.id.purchaseEnterDetailViewAdapter_billMoney_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, HolderView contentView, PurchaseEnterDetailModel arg2) {
		final ViewHold holder = (ViewHold) contentView;
		final PurchaseEnterDetailModel enterDetail  =list.get(position);
		
		holder.skuCode.setText(enterDetail.getSku().getCode());
		holder.unitName.setText(enterDetail.getUnitName());
		holder.unitQty.setText(String.valueOf(enterDetail.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(enterDetail.getMinUnitQty()));
		holder.skuName.setText(enterDetail.getSku().getName());
		holder.specQty.setText(String.valueOf(enterDetail.getSpecQty()));
		holder.storeName.setText(enterDetail.getStore().getName());
		holder.shelfCode.setText(enterDetail.getShelf().getCode());
		holder.billMoney.setText(String.valueOf(enterDetail.getMoney()));
	}
	
	 class ViewHold extends HolderView{
		TextView skuCode;		
		TextView unitQty;		//件数
		TextView minUnitQty; 	//散数
		TextView unitName;		//单位
		TextView skuName;		//SkuName
		TextView specQty;		//包装数量
		TextView storeName;		//库区
		TextView shelfCode;		//货位
		TextView billMoney;		//金额
		
	}
}


