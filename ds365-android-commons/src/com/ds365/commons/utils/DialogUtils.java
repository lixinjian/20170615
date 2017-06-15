package com.ds365.commons.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.KeyEvent;

public class DialogUtils {
	/**
	 * 创建弹窗需要实现确定和取消事件,cancelAble为true时无操作无法取消弹窗
	 * */
	public static void createConfirmDialog(Context context, String dialogTitle,String dialogMessage,boolean cancelAble,
			OnClickListener confirmListener,OnClickListener cancelListener) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage(dialogMessage);
		if(dialogTitle!=null){
			builder.setTitle(dialogTitle);
		}
		
		builder.setPositiveButton("确认",confirmListener);
		builder.setNegativeButton("取消",cancelListener);
		AlertDialog alertDialog = builder.create();
		if(cancelAble){
			alertDialog.setCancelable(false);
			alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener(){
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					if (keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
						return true;
					}else{
						return false;
					}
				}
			});
		}
		alertDialog.show();
	}
	/**
	 * 有头部弹窗,cancelAble为true则无操作无法取消弹窗
	 * */
	public static void createConfirmDialog(Context context, String dialogTitle,String dialogMessage,boolean cancelAble,
			OnClickListener confirmListener){
		createConfirmDialog(context,dialogTitle,dialogMessage,cancelAble,confirmListener,new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	}
	/**
	 * 无头部弹窗
	 * */
	public static void createConfirmDialog(Context context, String dialogMessage,OnClickListener confirmListener){
		createConfirmDialog(context,null,dialogMessage,false,confirmListener);
	}
}
