package com.ds365.erp.wms.pda.view.query.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.stock.SkuWarehouseStockModel;
import com.ds365.erp.wms.pda.view.query.activity.SkuShelfBatchStockBySkuIdWarehouseIdSaleTypeActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 
 * 说明 ：库区库存查询的adapter  
 */
public class SkuWarehouseStockAdapter extends CommonAdapter<SkuWarehouseStockModel> {

	public static final  String SER_KEY=PdaConstants.nextSerKey();
	public SkuWarehouseStockAdapter(Context context, List<SkuWarehouseStockModel> list) {
		super(context, list);

	}

	@Override
	public int getContentViewId() {
		return R.layout.query_sku_warehouse_stock_adapter;
	}

	@Override
	public CommonAdapter<SkuWarehouseStockModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.batchDetailButton = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_batchDetail_button);
		holder.skuCode = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_skuCode_value);
		holder.specQty = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_specQty_value);
		holder.skuName = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_skuName_value);
		holder.stockQty = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_stock_value);
		holder.spec = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_spec_value);
		holder.unitName = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_unitName_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.skuWarehouseStockAdapter_minUnitQty_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<SkuWarehouseStockModel>.HolderView contentView, SkuWarehouseStockModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.specQty.setText(String.valueOf(list.get(position).getSku().getGoodsPack().getSpecQty()));
		holder.skuName.setText(list.get(position).getSku().getName());
		holder.stockQty.setText(String.valueOf(list.get(position).getQty()));
		holder.skuCode.setText(list.get(position).getSku().getCode());
		holder.spec.setText(list.get(position).getSku().getSpec());
		holder.unitName.setText(list.get(position).getSku().getGoodsPack().getUnitName());
		holder.unitQty.setText(String.valueOf(list.get(position).getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(list.get(position).getMinUnitQty()));
		holder.batchDetailButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForSeria(context, SkuShelfBatchStockBySkuIdWarehouseIdSaleTypeActivity.class
						, SER_KEY, list.get(position), null);
			}
		});
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
		TextView batchDetailButton;//批次明细   按钮
	}
}
