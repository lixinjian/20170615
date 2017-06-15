package com.ds365.commons;

import java.io.Serializable;

public class BaseIdModel<K> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4758266255422468302L;

	protected K id;

	public K getId() {
		return id;
	}

	public void setId(K id) {
		this.id = id;
	}
}
