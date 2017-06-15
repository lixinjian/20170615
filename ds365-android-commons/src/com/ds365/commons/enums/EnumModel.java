
package com.ds365.commons.enums;

import java.io.Serializable;

public class EnumModel<T extends Serializable> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8856204347087267745L;

	private T id;

	private String enumName;

	private String name;

	public void setId(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
