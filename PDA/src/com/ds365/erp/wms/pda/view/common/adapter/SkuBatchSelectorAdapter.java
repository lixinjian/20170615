package com.ds365.erp.wms.pda.view.common.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.stock.SkuBatchModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SkuBatchSelectorAdapter extends CommonAdapter<SkuBatchModel> {

	public SkuBatchSelectorAdapter(Context context, List<SkuBatchModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.common_sku_batch_selector_adapter;
	}

	@Override
	public CommonAdapter<SkuBatchModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.skuName = (TextView) contentView.findViewById(R.id.commonSkuBatchSelectorAdapter_skuName_value);
		holder.skuCode = (TextView) contentView.findViewById(R.id.commonSkuBatchSelectorAdapter_skuCode_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.commonSkuBatchSelectorAdapter_specQty_value);
		holder.sysBatchNo = (TextView) contentView.findViewById(R.id.commonSkuBatchSelectorAdapter_sysBatchNo_value);
		holder.produceBatchNo = (TextView) contentView.findViewById(R.id.commonSkuBatchSelectorAdapter_produceBatchNo_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.commonSkuBatchSelectorAdapter_unitName_value);
		holder.produceDate = (DateField) contentView.findViewById(R.id.commonSkuBatchSelectorAdapter_produceDate_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<SkuBatchModel>.HolderView contentView, SkuBatchModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.skuName.setText(list.get(position).getSku().getName());
		holder.skuCode.setText(list.get(position).getSku().getCode());
		holder.specQty.setText(String.valueOf(list.get(position).getSku().getGoodsPack().getSpecQty()));
		holder.sysBatchNo.setText(list.get(position).getSysBatchNo());
		holder.produceBatchNo.setText(list.get(position).getProduceBatchNo());
		holder.unitName.setText(list.get(position).getUnitName());
		holder.produceDate.setValue(list.get(position).getProduceDate());
	}
	class ViewHold extends HolderView{
		TextView skuName;		//sku名称
		TextView skuCode;		//sku编号
		TextView specQty;			//包装数量
		TextView sysBatchNo;	//系统批次
		TextView produceBatchNo;	//生产批次
		TextView unitName;
		DateField produceDate;	//生产日期
	}
}
