package com.ds365.commons.base.model;

import java.io.Serializable;

public class BaseTreeModel<T extends BaseTreeModel<?>> extends BaseActiveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7339103224216593846L;

	protected String code;

	protected String name;

	protected String description;

	protected Boolean leaf;

	protected Integer level;

	protected Integer sortNo;

	// 拼音
	// protected String spell;

	protected String helpCode;

	// 拼音缩写
	// protected String spellShort;

	protected String navIds;// 导航ids
	
	protected T parent;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getNavIds() {
		return navIds;
	}

	public void setNavIds(String navIds) {
		this.navIds = navIds;
	}

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

}
