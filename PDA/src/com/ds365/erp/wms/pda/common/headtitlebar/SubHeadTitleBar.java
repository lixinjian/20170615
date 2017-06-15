package com.ds365.erp.wms.pda.common.headtitlebar;

import com.ds365.commons.utils.ActivityUtils;
import com.ds365.commons.utils.StringUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubHeadTitleBar extends LinearLayout implements OnClickListener {
	
	private Activity context;
	private ImageView leftImg;
	private TextView title, warehouseNameValue;
	
	//把左边按钮点击事件交给子类处理,因为现在所有左边按钮都是返回功能,故在此类做了统一处理
	private ButtonClickListener listener;
	public void setListener(ButtonClickListener listener) {
		this.listener = listener;
	}
	public SubHeadTitleBar(Context context) {
		super(context);
		initViews(context);
		this.context = (Activity) context;
	}

	public SubHeadTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
		this.context = (Activity) context;
	}
	
	public void initViews(Context context) {
		View container = View.inflate(context, R.layout.headtitlebar_sub, null);
		leftImg = (ImageView) container.findViewById(R.id.headerView_leftImg);
		title = (TextView) container.findViewById(R.id.headerview_title);
		warehouseNameValue = (TextView) container.findViewById(R.id.headerview_warehouseaddress);
		if (null != GlobalUtils.getSessionUser()) {
			warehouseNameValue.setText(StringUtils.isEmptyString(GlobalUtils.getSessionUser().getWarehouseName()));
		}
		leftImg.setOnClickListener(this);
		addView(container);
	}
	
	public void setLeftImg(int id) {
		if (leftImg != null) {
			leftImg.setImageResource(id);
		}
	}
	
	public interface ButtonClickListener {
		/**
		 * back 键是否需要处理
		 */
		void setLeftListener();
	}
	
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.headerView_leftImg:
//			if (listener != null) {
//				listener.setLeftListener();
//			}
//			break;
//		default:
//			break;
//		}
//	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.headerView_leftImg:
			if(listener!=null)
				listener.setLeftListener();
			ActivityUtils.remove(context);
			context.finish();
		}
	}
	
	/*** 设置标题头 */
	public void setTitle(String string) {
		title.setText(string);
	}
	
	public void setTitle(int id) {
		if (title != null) {
			title.setText(id);
		}
	}
	public void setWarehouseAddress(int id) {
		if (warehouseNameValue != null) {
			warehouseNameValue.setText(id);
		}
	}
}
