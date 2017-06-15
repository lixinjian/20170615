package com.ds365.shop.asynchttpclientloading;

import com.ds365.commons.utils.ScreenUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

/**
 * 创建Dialog基类
 *
 * @author hpt
 */
public abstract class BaseDialog extends Dialog {

	public Context context;
	public View convertView;
	private LayoutParams mLayoutParams;

	public BaseDialog(Context context, int theme) {
		super(context, theme);
		initContent(context);
	}

	protected BaseDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		initContent(context);
	}

	public BaseDialog(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		initContent(context);
	}

	private void initContent(Context context) {
		this.context = context;
		convertView = View.inflate(context, getContentView(), null);
		this.setContentView(convertView, getILayoutParams());
		this.setCancelable(false);
		this.setOnKeyListener(new DialogInterface.OnKeyListener(){
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
					return true;
				}else{
					return false;
				}
			}
		});
		setUpViews();

	}

	public int getWidth() {
		int w = getDialogWidth() == 0 ? ScreenUtils.getScreenWidth(context) * 4 / 5
				: getDialogWidth();
		return w;
	}

	LayoutParams getILayoutParams() {
		int w = getDialogWidth() == 0 ? ScreenUtils.getScreenWidth(context) * 4 / 5
				: getDialogWidth();
		int h = getDialogHeight() == 0 ? android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
				: getDialogHeight();

		android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(
				w, h);
		return lp;
	}

	public void setLayouParams(int w, int h, int x, int y) {
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		//        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
		lp.x = x; // 新位置X坐标
		lp.y = y; // 新位置Y坐标
		lp.width = w; // 宽度

		lp.height = h; // 高度
		// lp.alpha = 0.7f; // 透明度 //
		// 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
		//        onWindowAttributesChanged(lp);
		dialogWindow.setAttributes(lp);
	}

	public void updateLocation(int w, int h, int x, int y) {
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
		lp.width = w; // 宽度

		lp.height = h; // 高度
		if (x == 0)
			x = ScreenUtils.getScreenWidth(context) / 2 - lp.width / 2;
		lp.x = x; // 新位置X坐标
		if (y != 0)
			lp.y = y; // 新位置Y坐标
		// lp.alpha = 0.7f; // 透明度 //
		// 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
		// // dialog.onWindowAttributesChanged(lp);
		dialogWindow.setAttributes(lp);
	}

	/**
	 * 实例化组件
	 */
	abstract void setUpViews();

	/**
	 * 获取Dilalog视图的布局
	 *
	 * @return
	 */
	abstract int getContentView();

	/**
	 * 获取dialog的宽度
	 *
	 * @return 默认返回 LayoutParams.WRAP_CONTENT
	 */
	abstract int getDialogWidth();

	/**
	 * 获取dialog的高度
	 *
	 * @return 默认返回 LayoutParams.WRAP_CONTENT
	 */
	abstract int getDialogHeight();

	/**
	 * 获取Dialog content 的权重 权
	 *
	 * @return
	 */
	abstract int getContentGravity();

}
