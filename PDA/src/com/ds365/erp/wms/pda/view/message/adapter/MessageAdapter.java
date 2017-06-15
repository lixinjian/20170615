package com.ds365.erp.wms.pda.view.message.adapter;

import java.util.List;

import com.ds365.commons.message.model.MessageDatebaseModel;
import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.utils.DateFormatUtils;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MessageAdapter extends CommonAdapter<MessageDatebaseModel> {

	public MessageAdapter(Context context, List<MessageDatebaseModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.message_adapter;
	}

	@Override
	public CommonAdapter<MessageDatebaseModel>.HolderView getHoldView(
			int position, View contentView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		holder.content = (TextView) contentView.findViewById(R.id.messageAdapter_content);
		holder.time = (DateField) contentView.findViewById(R.id.messageAdapter_time);
		holder.title = (TextView) contentView.findViewById(R.id.messageAdapter_title);
		return holder;
	}

	@Override
	public void setItemView(int arg0,CommonAdapter<MessageDatebaseModel>.HolderView arg1, MessageDatebaseModel arg2) {
		ViewHolder holder = (ViewHolder) arg1;
		holder.time.setValue(DateFormatUtils.stringToDateForyyyyMMddHHmmss(arg2.getSendTime()));
		holder.title.setText(arg2.getTitle());
		holder.content.setText(arg2.getContent());
	}
	
	private class ViewHolder extends HolderView{
		TextView content,title;
		DateField time;
	}
}
