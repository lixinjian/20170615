package com.ds365.commons.widget;

import android.app.DatePickerDialog;
import android.content.Context;

public class MyDatePickerDialog extends DatePickerDialog {

	public MyDatePickerDialog(Context context, OnDateSetListener listener, int year, int monthOfYear,
			int dayOfMonth) {
		super(context,listener, year, monthOfYear, dayOfMonth);
	}

	@Override
	protected void onStop() {

	}

}
