package com.ds365.erp.wms.pda.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 头部导航栏View
 */
public abstract class HeadTitleBar extends LinearLayout {
	protected Context context;
	private TextView title, warehouseAddress;

	public HeadTitleBar(Context context) {
		super(context);
		this.context = context;
	}

	public HeadTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	/*** 设置标题头 */
	public void setTittle(String string) {
		title.setText(string);
	}

	/*** 设置仓库地址 */
	public void setWarehouseAddress(String string) {
		warehouseAddress.setText(string);
	}

	public void setAlphaBackground(int alphaValue) {
		Drawable d = getBackground();
		if (d != null)
			d.setAlpha(alphaValue);

	}

	/**
	 * 居中的title 设置 默认为隐藏
	 * 
	 * @param id
	 * @param visiable
	 */
	public void setTitle(int id/*, int visiable*/) {
		if (title != null) {
			title.setText(id);
//			title.setVisibility(visiable == View.GONE ? View.INVISIBLE : View.VISIBLE);
		}
	}

	public void setWarehouseAddress(int id/*, int visiable*/) {
		if (warehouseAddress != null) {
			warehouseAddress.setText(id);
//			warehouseAddress.setVisibility(visiable == View.GONE ? View.INVISIBLE : View.VISIBLE);
		}
	}
}
