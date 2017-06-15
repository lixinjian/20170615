package com.ds365.erp.wms.pda.view.message.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ds365.commons.message.model.MessageCategoryModel;
import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.BadgeView;
import com.ds365.erp.pda.R;

public class MessageCategoryAdapter extends CommonAdapter<MessageCategoryModel<Integer>> {

	private int[] imageArray = {R.drawable.message_application,R.drawable.message_system};
	
	public MessageCategoryAdapter(Context context, List<MessageCategoryModel<Integer>> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.message_message_category_adapter;
	}

	@Override
	public CommonAdapter<MessageCategoryModel<Integer>>.HolderView getHoldView(
			int position, View contentView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		holder.image = (ImageView) contentView.findViewById(R.id.messagecategory_image);
		holder.imageParent = (RelativeLayout) contentView.findViewById(R.id.messagecategory_imageparent);
		holder.badge = new BadgeView(context, holder.imageParent);// 创建一个BadgeView对象，view为你需要显示提醒的控件
		holder.badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
		holder.title = (TextView) contentView.findViewById(R.id.messagecategory_title);
		holder.content = (TextView) contentView.findViewById(R.id.messagecategory_content);
		return holder;
	}

	@Override
	public void setItemView(int arg0,CommonAdapter<MessageCategoryModel<Integer>>.HolderView arg1,MessageCategoryModel<Integer> arg2) {
		ViewHolder holder = (ViewHolder) arg1;
		if(arg0<imageArray.length){
			holder.image.setImageResource(imageArray[arg0]);
		}
		holder.title.setText(arg2.getName());
		if(arg2.getNotReadCount()!=0){
			holder.badge.setText(String.valueOf(arg2.getNotReadCount()));
			holder.badge.setBadgeBackgroundColor(context.getResources().getColor(R.color.blue));
			holder.badge.show();
		}else{
			holder.badge.hide();
		}
	}
	
	private class ViewHolder extends HolderView{
		private RelativeLayout imageParent;
		private ImageView image;
		private BadgeView badge;
		private TextView title,content;
	}
}
