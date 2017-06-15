package com.ds365.shop.asynchttpclientloading;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.ds365.commons.AppConstants;
import com.ds365.shop.asynchttpclientlibrary.R;


/**
 * 加载框的创建 和取消
 * 
 * @author pengtao_H
 */
public class LoadingHanlder extends Handler {

	private Context context;

	private LoadingDialog mLoading;
	private int titleId = R.string.watting_hint;

	public LoadingHanlder(Context context) {
		super();
		this.context = context;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	@Override
	public void dispatchMessage(Message msg) {
		super.dispatchMessage(msg);

		switch (msg.what) {
		case AppConstants.CREATE_DIALOG:
			if (mLoading == null) {
				mLoading = new LoadingDialog(context);
				mLoading.setPromptTxt(titleId);
			}
			mLoading.show();
			break;
		case AppConstants.CANCLE_DIALOG:
			if (mLoading != null) {
				mLoading.dismiss();
			}
			break;

		default:
			break;
		}

	}
}
