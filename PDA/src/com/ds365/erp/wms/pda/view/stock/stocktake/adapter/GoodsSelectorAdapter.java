package com.ds365.erp.wms.pda.view.stock.stocktake.adapter;

import java.util.List;

import com.ds365.erp.pda.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GoodsSelectorAdapter extends BaseAdapter{

	private Context context ;
	private List<String> list;
	
	public GoodsSelectorAdapter(Context context,List<String> list) {
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
			convertView = View.inflate(context,R.layout.takestock_goods_selector_adapter, null);
			viewHold.shelfCode = (TextView) convertView.findViewById(R.id.goodsSelectorAdapter_skuCode_value);
			convertView.setTag(viewHold);
		}else{
			viewHold = (ViewHold) convertView.getTag();
		}
		
//		viewHold.shelfCode.setText("A-01-01");
		
		return convertView;
	}
	
	private static class ViewHold{
		TextView shelfCode;	
	}
}
