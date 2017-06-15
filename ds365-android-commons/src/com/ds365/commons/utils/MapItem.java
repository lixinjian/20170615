package com.ds365.commons.utils;

import java.io.Serializable;

public class MapItem<T extends Serializable> {

	private T value;
	private String displayValue;
	
	public MapItem(T value,String displayValue){
		this.value = value;
		this.displayValue = displayValue;
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	
	
}
