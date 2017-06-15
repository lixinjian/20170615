package com.ds365.erp.wms.pda.view.query.adapter;

import java.util.List;

import com.ds365.erp.pda.R;
import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.wms.pda.model.stock.SkuStoreStockModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 
 * 说明 ：库区库存查询的adapter  
 */
public class StoreStockSelectorAdapter extends CommonAdapter<SkuStoreStockModel> {

	
	public StoreStockSelectorAdapter(Context context, List<SkuStoreStockModel> list) {
		super(context, list);

	}

	@Override
	public int getContentViewId() {
		return R.layout.query_store_stock_selector_adapter;
	}

	@Override
	public CommonAdapter<SkuStoreStockModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuCode = (TextView) contentView.findViewById(R.id.storeStockSelectorAdapter_skuCode_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.storeStockSelectorAdapter_specQty_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.storeStockSelectorAdapter_skuName_value);
		holder.stockQty = (TextView) contentView.findViewById(R.id.storeStockSelectorAdapter_stock_value);
		holder.spec = (TextView) contentView.findViewById(R.id.storeStockSelectorAdapter_spec_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.storeStockSelectorAdapter_unitName_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.storeStockSelectorAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.storeStockSelectorAdapter_minUnitQty_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<SkuStoreStockModel>.HolderView contentView, SkuStoreStockModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.specQty.setText(String.valueOf(list.get(position).getSku().getGoodsPack().getSpecQty()));
		holder.skuName.setText(list.get(position).getSku().getName());
		holder.stockQty.setText(String.valueOf(list.get(position).getQty()));
		holder.skuCode.setText(list.get(position).getSku().getCode());
		holder.spec.setText(list.get(position).getSku().getSpec());
		holder.unitName.setText(list.get(position).getSku().getGoodsPack().getUnitName());
		holder.unitQty.setText(String.valueOf(list.get(position).getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(list.get(position).getMinUnitQty()));
	}

	class ViewHold extends HolderView{
		TextView skuCode;		//sku编号
		TextView skuName;		//sku名称
		TextView specQty;			//包装数量
		TextView stockQty;		//库存
		TextView spec;		//规格
		TextView unitName;		//单位
		TextView unitQty;
		TextView minUnitQty;
	}
}
