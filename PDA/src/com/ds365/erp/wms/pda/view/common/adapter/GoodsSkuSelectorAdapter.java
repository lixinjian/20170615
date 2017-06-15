package com.ds365.erp.wms.pda.view.common.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ds365.erp.pda.R;
import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;

public class GoodsSkuSelectorAdapter extends CommonAdapter<GoodsSkuModel> {

	
	public GoodsSkuSelectorAdapter(Context context, List<GoodsSkuModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.commons_goods_sku_list_adapter;
	}

	@Override
	public CommonAdapter<GoodsSkuModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.barCodeValue = (TextView) contentView.findViewById(R.id.commonsGoodsSkuSelect_barCode_value);
		holder.skuCodeValue = (TextView) contentView.findViewById(R.id.commonsGoodsSkuSelect_skuCode_value);
		holder.specValue = (TextView) contentView.findViewById(R.id.commonsGoodsSkuSelect_spec_value);
		holder.skuNameValue = (TextView) contentView.findViewById(R.id.commonsGoodsSkuSelect_skuName_value);
		holder.specQtyValue = (TextView)contentView.findViewById(R.id.commonsGoodsSkuSelect_specQty_value);
		holder.unitNameValue = (TextView) contentView.findViewById(R.id.commonsGoodsSkuSelect_unitName_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<GoodsSkuModel>.HolderView contentView, GoodsSkuModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.barCodeValue.setText(list.get(position).getBarcode());
		holder.skuCodeValue.setText(list.get(position).getCode());
		holder.skuNameValue.setText(list.get(position).getName());
		holder.specValue.setText(list.get(position).getSpec());
		holder.specQtyValue.setText(String.valueOf(list.get(position).getGoodsPack().getSpecQty()));
		holder.unitNameValue.setText(list.get(position).getGoodsPack().getName());
		
	}
	
	class ViewHold extends HolderView{	
		TextView barCodeValue;//sku条码
		TextView skuCodeValue;//sku编码
		TextView specValue;
		TextView skuNameValue;
		TextView specQtyValue;
		TextView unitNameValue;
	}
}
