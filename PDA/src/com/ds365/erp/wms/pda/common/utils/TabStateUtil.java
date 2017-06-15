package com.ds365.erp.wms.pda.common.utils;

import com.ds365.erp.pda.R;
import android.content.Context;
import android.widget.TextView;

public class TabStateUtil {
	public static void changeTwoButtonState(Context context, TextView clickText, TextView visiableText) {
		if (clickText != null) {
			
			clickText.setBackgroundColor(context.getResources().getColor(R.color.tab_checked));
//			clickText.setTextColor(context.getResources().getColor(R.color.white));
			
			visiableText.setBackgroundColor(context.getResources().getColor(R.color.tab_uncheck));
//			visiableText.setTextColor(context.getResources().getColor(R.color.black));
		}
	}
	
	public static void changeThreeButtonState(Context context, TextView clickText, TextView visiableText, TextView visiableAnotherText) {
		if (clickText != null) {
			
			clickText.setBackgroundColor(context.getResources().getColor(R.color.tab_checked));
//			clickText.setTextColor(context.getResources().getColor(R.color.white));
			
			visiableText.setBackgroundColor(context.getResources().getColor(R.color.tab_uncheck));
//			visiableText.setTextColor(context.getResources().getColor(R.color.black));
			
			visiableAnotherText.setBackgroundColor(context.getResources().getColor(R.color.tab_uncheck));
//			visiableAnotherText.setTextColor(context.getResources().getColor(R.color.black));
			
		}
	}
}
