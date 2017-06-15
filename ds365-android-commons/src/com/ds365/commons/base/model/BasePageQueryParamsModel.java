package com.ds365.commons.base.model;

import java.io.Serializable;

public abstract class BasePageQueryParamsModel extends BaseQueryParamsModel implements Serializable {
	private static final long serialVersionUID = -8910001776057519391L;
	private Integer start;
	private Integer limit;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
