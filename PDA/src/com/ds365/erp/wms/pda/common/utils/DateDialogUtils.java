package com.ds365.erp.wms.pda.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

public class DateDialogUtils {
	/**
	 * 创建日期及时间选择对话框
	 */
	public static Dialog dateDialog(Context context, final EditText et) {
		Dialog dialog = null;
		Calendar c = Calendar.getInstance();
		Object time = et.getTag();
		if (time != null && !"".equals(time)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date d = format.parse((String)time);
				c.setTime(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
				et.setText(year + " - " + (month + 1) + " - " + dayOfMonth);
				Calendar cs = Calendar.getInstance();
				cs.set(Calendar.YEAR, year);
				cs.set(Calendar.MONTH, month);
				cs.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				et.setTag(format.format(cs.getTime()));
			}
		}, c.get(Calendar.YEAR), // 传入年份
				c.get(Calendar.MONTH), // 传入月份
				c.get(Calendar.DAY_OF_MONTH) // 传入天数
		);
		return dialog;
	}
}
