package com.ds365.erp.wms.pda.view.enterwarehouse.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterDetailModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SaleReturnEnterDetailViewAdapter extends CommonAdapter<SaleReturnEnterDetailModel> {
	
	public SaleReturnEnterDetailViewAdapter(Context context, List<SaleReturnEnterDetailModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_enter_detail_view_adapter;
	}

	@Override
	public CommonAdapter<SaleReturnEnterDetailModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_skuCode_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_minUnitQty_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_unitName_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_skuName_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_specQty_value);
		holder.storeName = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_storeName_value);
		holder.shelfCode = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_shelfCode_value);
		holder.billMoney = (TextView) contentView.findViewById(R.id.saleReturnEnterDetailViewAdapter_billMoney_value);
		return holder;
	} 

	@Override
	public void setItemView(int position, CommonAdapter<SaleReturnEnterDetailModel>.HolderView contentView, SaleReturnEnterDetailModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.skuCode.setText(list.get(position).getSku().getCode());
		holder.unitQty.setText(String.valueOf(list.get(position).getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(list.get(position).getMinUnitQty()));
		holder.unitName.setText(list.get(position).getUnitName());
		holder.storeName.setText(list.get(position).getStore().getName());
		holder.shelfCode.setText(list.get(position).getShelf().getCode());
		holder.skuName.setText(String.valueOf(list.get(position).getSku().getName()));
		holder.specQty.setText(String.valueOf(list.get(position).getSpecQty()));
		holder.billMoney.setText(String.valueOf(list.get(position).getMoney()));
	}

	class ViewHold extends HolderView{
		TextView skuCode;	//sku码
		TextView unitQty;		//件数
		TextView minUnitQty; 	//散数
		TextView unitName;	//单位
		TextView skuName;	//sku名称
		TextView specQty;	//包装数量
		TextView storeName;
		TextView shelfCode;
		TextView billMoney;
		
	}
}