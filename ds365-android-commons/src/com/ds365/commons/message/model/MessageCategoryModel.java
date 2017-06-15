package com.ds365.commons.message.model;

import java.io.Serializable;

import com.ds365.commons.enums.EnumModel;

/**
 *
 * Created by yxz on 2016/12/16.
 */
public class MessageCategoryModel<T extends Serializable> extends EnumModel<T> {

	private static final long serialVersionUID = -2866760203518150253L;

	private int notReadCount = 0;

	public int getNotReadCount() {
		return notReadCount;
	}

	public void setNotReadCount(int notReadCount) {
		this.notReadCount = notReadCount;
	}
	
}
