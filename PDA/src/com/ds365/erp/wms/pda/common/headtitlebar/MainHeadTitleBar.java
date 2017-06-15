package com.ds365.erp.wms.pda.common.headtitlebar;

import com.ds365.commons.utils.StringUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.widget.MenuWindow;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainHeadTitleBar extends LinearLayout implements OnClickListener {
	private Activity context;
	private ImageView rightImg;
	private TextView title, warehouseName, userName;

	public MainHeadTitleBar(Context context) {
		super(context);
		initViews(context);
		this.context = (Activity) context;
	}

	public MainHeadTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
		this.context = (Activity) context;
	}

	public void initViews(Context context) {
		
		View container = View.inflate(context, R.layout.headtitlebar_main, null);
		rightImg = (ImageView) container.findViewById(R.id.headerview_righttitle);
		title = (TextView) container.findViewById(R.id.headerview_common_title);
		warehouseName = (TextView) container.findViewById(R.id.headerview_warehouseaddress);
		userName = (TextView) container.findViewById(R.id.headerview_username);
		rightImg.setOnClickListener(this);
		warehouseName.setText(StringUtils.isEmptyString(GlobalUtils.getSessionUser().getWarehouseName()));
		userName.setText(StringUtils.isEmptyString(GlobalUtils.getSessionUser().getUserName()));
		title.setText(getResources().getString(R.string.wms));
		
		addView(container);
	}

	/*** 设置标题头 */
	public void setTittle(String string) {
		title.setText(string);
	}

	/*** 设置用户名 */
	public void setUserName(String string) {
		title.setText(string);
	}

	
	public void setTitle(int id) {
		if (title != null) {
			title.setText(id);
		}
	}

	public void setUserName(int id) {
		if (userName != null) {
			userName.setText(id);
		}
	}

	

	public void setRightImg(int id) {
		if (rightImg != null) {
			rightImg.setImageResource(id);
		}
	}

	public ImageView getRightImage() {
		if (rightImg != null) {
			return rightImg;
		}
		return null;
	}

	@Override
	public void onClick(View v) {
		MenuWindow menuWindow;
		switch (v.getId()) {
		case R.id.headerview_righttitle:
			menuWindow = new MenuWindow(context);
			menuWindow.showPopupWindow(getRightImage());
		default:
			break;
		}
	}
}
