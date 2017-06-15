package com.ds365.erp.wms.pda.view.common.adapter;

import java.util.List;

import com.ds365.erp.pda.R;
import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShelfBatchStockSelectorAdapter extends CommonAdapter<SkuShelfBatchStockModel> {

	
	public ShelfBatchStockSelectorAdapter(Context context, List<SkuShelfBatchStockModel> list) {
		super(context, list);

	}

	@Override
	public int getContentViewId() {
		return R.layout.common_shelf_batch_stock_selector_adapter;
	}

	@Override
	public CommonAdapter<SkuShelfBatchStockModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.shelfCode = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_shelfCode_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_skuCode_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_specQty_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_skuName_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_sysBatchNo_value);
//		holder.stockQty = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_stock_value);
		holder.spec = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_spec_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_unitName_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_minUnitQty_value);
		holder.produceDate = (DateField) contentView.findViewById(R.id.shelfBatchStockSelectorAdapter_produceDate_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<SkuShelfBatchStockModel>.HolderView contentView, SkuShelfBatchStockModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.shelfCode.setText(list.get(position).getShelf().getCode());
		holder.specQty.setText(String.valueOf(list.get(position).getSku().getGoodsPack().getSpecQty()));
		holder.skuName.setText(list.get(position).getSku().getName());
		holder.sysBatchNo.setText(list.get(position).getSysBatchNo());
//		holder.stockQty.setText(String.valueOf(list.get(position).getQty()));
		holder.skuCode.setText(list.get(position).getSku().getCode());
		holder.spec.setText(list.get(position).getSku().getSpec());
		holder.unitName.setText(list.get(position).getSku().getGoodsPack().getUnitName());
		holder.unitQty.setText(String.valueOf(list.get(position).getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(list.get(position).getMinUnitQty()));
		holder.produceDate.setValue(list.get(position).getProduceDate());
	}

	class ViewHold extends HolderView{
		TextView shelfCode;		//货位
		TextView skuCode;		//sku编号
		TextView skuName;		//sku名称
		TextView specQty;			//包装数量
		TextView sysBatchNo;		//批次
//		TextView stockQty;		//库存
		TextView spec;		//规格
		TextView unitName;		//单位
		TextView unitQty;
		TextView minUnitQty;
		DateField produceDate;
	}
}
