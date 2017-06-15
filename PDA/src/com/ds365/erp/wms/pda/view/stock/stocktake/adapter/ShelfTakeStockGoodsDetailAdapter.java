package com.ds365.erp.wms.pda.view.stock.stocktake.adapter;

import java.util.List;

import com.ds365.erp.pda.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ShelfTakeStockGoodsDetailAdapter extends BaseAdapter {

	private Context context ;
	private List<String> list;
	
	public ShelfTakeStockGoodsDetailAdapter(Context context,List<String> list) {
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHold viewHold;
		if (convertView == null) {
			viewHold = new ViewHold();
			convertView = View.inflate(context,R.layout.takestock_shelf_take_stock_goods_detail_adapter, null);
			viewHold.skuCode = (TextView) convertView.findViewById(R.id.shelfTakeStockGoodsDetailAdapter_skuCode_value);
			viewHold.skuName = (TextView) convertView.findViewById(R.id.shelfTakeStockGoodsDetailAdapter_skuName_value);
			viewHold.sysBatchNo = (TextView) convertView.findViewById(R.id.shelfTakeStockGoodsDetailAdapter_sysBatchNo_value);
			convertView.setTag(viewHold);
		}else{
			viewHold = (ViewHold) convertView.getTag();
		}
		
		viewHold.skuCode.setText("010030508");
		viewHold.skuName.setText("农夫矿泉水");
		viewHold.sysBatchNo.setText("00120160308001");
		return convertView;
	}
	
	private static class ViewHold{
		TextView shelfCode;		
		TextView skuCode;		
		TextView skuName;		
		TextView sysBatchNo;		
		
	}
}
