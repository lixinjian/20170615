package com.ds365.shop.asynchttpclientloading;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds365.shop.asynchttpclientlibrary.R;

public class LoadingDialog extends BaseDialog {

	private TextView textView;

	public LoadingDialog(Context context) {
		super(context, R.style.DialogStyle);
	}

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	void setUpViews() {
		LinearLayout layout = (LinearLayout) convertView
				.findViewById(R.id.dialog_view);// 加载布局

		textView = (TextView) layout.findViewById(R.id.progressbar_text);
		textView.setText(R.string.watting_hint);
	}

	public void setPromptTxt(int stringId) {
		textView.setText(stringId);
	}

	@Override
	LayoutParams getILayoutParams() {
		LayoutParams lp = new LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		return lp;
	}

	@Override
	int getContentView() {
		// TODO Auto-generated method stub
		return R.layout.dialog_loading;
	}

	@Override
	int getDialogWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int getDialogHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int getContentGravity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
