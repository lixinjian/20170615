package com.ds365.erp.wms.pda.view.query.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.stock.SkuStockAccountBookModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SkuStockAccountBookAdapter extends CommonAdapter<SkuStockAccountBookModel> {
	
	public SkuStockAccountBookAdapter(Context context, List<SkuStockAccountBookModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.query_sku_stock_account_book_adapter;
	}

	@Override
	public CommonAdapter<SkuStockAccountBookModel>.HolderView getHoldView(final int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.entCode = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_entCode_value);
		holder.entName = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_entName_value);
		holder.goodsSaleType = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_goodsSaleType_value);
		holder.skuStockOperateType = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_skuStockOperateType_value);
		holder.billType = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_billType_value);
		holder.billCode = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_billCode_value);
		holder.unitQty = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_unitQty_value);
		holder.minUnitQty = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_minUnitQty_value);
		holder.warehouseRemainUnitQty = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_warehouseRemainUnitQty_value);
		holder.warehouseRemainMinUnitQty = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_warehouseRemainMinUnitQty_value);
		holder.bizDate = (DateField) contentView.findViewById(R.id.skuStockAccountBookAdapter_bizDate_value);
		holder.price = (TextView) contentView.findViewById(R.id.skuStockAccountBookAdapter_price_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<SkuStockAccountBookModel>.HolderView contentView, SkuStockAccountBookModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.entCode.setText(list.get(position).getEntCode());
		holder.entName.setText(list.get(position).getEntName());
		holder.goodsSaleType.setText(list.get(position).getSaleType().getName());
		if(null != list.get(position).getOperateType()){
			holder.skuStockOperateType.setText(list.get(position).getOperateType().getName());
		}
		holder.billType.setText(list.get(position).getBillType().getName());
		holder.billCode.setText(list.get(position).getBillCode());
		QtyModel qtyModel = QtyMoneyUtils.getQtyEntity(list.get(position).getQty(), list.get(position).getPack().getSpecQty());
		QtyModel warehouseRemainUnitQtyModel = QtyMoneyUtils.getQtyEntity(list.get(position).getStoreRemainQty(), list.get(position).getPack().getSpecQty());
		holder.unitQty.setText(String.valueOf(qtyModel.getUnitQty()));
		holder.minUnitQty.setText(String.valueOf(qtyModel.getMinUnitQty()));
		holder.warehouseRemainUnitQty.setText(String.valueOf(warehouseRemainUnitQtyModel.getUnitQty()));
		holder.warehouseRemainMinUnitQty.setText(String.valueOf(warehouseRemainUnitQtyModel.getMinUnitQty()));
		holder.bizDate.setValue(list.get(position).getBizDate());
		holder.price.setText(String.valueOf(list.get(position).getPrice()));
	}
	
	class ViewHold extends HolderView{
		TextView entCode;		//单位编号
		TextView entName;		//单位名称
		TextView goodsSaleType;	//销售类型
		TextView skuStockOperateType;//操作类型
		TextView billType;//单据类型
		TextView billCode;//单据编号
		TextView unitQty;//件数
		TextView minUnitQty;//散数
		TextView warehouseRemainUnitQty;//结存件数
		TextView warehouseRemainMinUnitQty;//结存散数
		DateField bizDate;//日期
		TextView price;//单价
		
	}
}