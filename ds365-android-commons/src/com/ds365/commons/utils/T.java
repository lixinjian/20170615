package com.ds365.commons.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ds365.commons.R;

/**
 * Toast统一管理类
 */
public class T {

	private static final int DEFAULT_OFFSET=200;
	private T() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	
	/***向下移动一点*/
	public static void show(Context context, CharSequence message, int duration,int offset) {
			//showByOffset(context, message,offset);
		Toast toast = new Toast(context);
		View layout = View.inflate(context, R.layout.layout_toast, null);
		TextView title = (TextView) layout.findViewById(R.id.toast_content);
		if (!TextUtils.isEmpty(message)) {
			title.setText(message);
			toast.setGravity(Gravity.BOTTOM, 0, offset);
			toast.setDuration(duration);
			toast.setView(layout);
			toast.show();
		}
	}
	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
			show(context, message,Toast.LENGTH_SHORT);
	}
	/**
	 * 自定义显示Toast时间
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message,int duration) {
		
		show(context, message, duration, DEFAULT_OFFSET);
		
	}
	
	public static void showShortByOffset(Context context, CharSequence message,int offset) {
		show(context, message, Toast.LENGTH_SHORT, offset);
		
		
	}
	
	public static void show(Context context, int message,int duration,int offset) {
		Toast toast = new Toast(context);
		View layout = View.inflate(context, R.layout.layout_toast, null);
		TextView title = (TextView)layout.findViewById(R.id.toast_content);
		title.setText(message);
		toast.setGravity(Gravity.BOTTOM, 0, offset);
		toast.setDuration(duration);
		// 替换掉原有的ToastView
		toast.setView(layout);
		toast.show();
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, int message) {
			show(context, message,Toast.LENGTH_SHORT,DEFAULT_OFFSET);
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
			show(context, message,Toast.LENGTH_LONG);
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, int message) {
			show(context, message,Toast.LENGTH_LONG);
	}

	
	
	/**
	 * 自定义显示Toast时间
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, int message, int duration) {
			show(context, message,duration,DEFAULT_OFFSET);
	}

}