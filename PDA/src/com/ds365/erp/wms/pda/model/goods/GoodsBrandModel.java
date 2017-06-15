package com.ds365.erp.wms.pda.model.goods;

import java.io.Serializable;

import com.ds365.commons.base.model.BaseActiveModel;

public class GoodsBrandModel extends BaseActiveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6873339987516524044L;

	private String code;

	private String name;

	private String helpCode;

	private String logoImageUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getLogoImageUrl() {
		return logoImageUrl;
	}

	public void setLogoImageUrl(String logoImageUrl) {
		this.logoImageUrl = logoImageUrl;
	}
}
