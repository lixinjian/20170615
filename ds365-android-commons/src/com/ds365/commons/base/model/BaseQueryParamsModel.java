package com.ds365.commons.base.model;

import java.io.Serializable;
import java.util.List;

public abstract class BaseQueryParamsModel extends BaseActiveModel implements Serializable {
	private static final long serialVersionUID = -8321468447974840220L;
	private String fuzzy;

	private String orderBy;// 排序sql

	private Long excludeId;

	private List<Long> excludeIds;

	public String getFuzzy() {
		return fuzzy;
	}

	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Long getExcludeId() {
		return excludeId;
	}

	public void setExcludeId(Long excludeId) {
		this.excludeId = excludeId;
	}

	public List<Long> getExcludeIds() {
		return excludeIds;
	}

	public void setExcludeIds(List<Long> excludeIds) {
		this.excludeIds = excludeIds;
	}

}
