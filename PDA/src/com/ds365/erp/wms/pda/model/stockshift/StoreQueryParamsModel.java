package com.ds365.erp.wms.pda.model.stockshift;

import com.ds365.commons.base.model.BasePageQueryParamsModel;

public class StoreQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1575968399000291646L;

	// 所属仓库
	private Long warehouseId;

	// 类型
	private Integer typeId;

	private String typeName;

	private Integer useTypeId;

	private String useTypeName;
	// 编码
	private String code;
	// 名称
	private String name;
	// 助记码（中文）
	private String helpCode;

	// 是否开启货位管理
	private Boolean shelfManageFlag;

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getUseTypeId() {
		return useTypeId;
	}

	public void setUseTypeId(Integer useTypeId) {
		this.useTypeId = useTypeId;
	}

	public String getUseTypeName() {
		return useTypeName;
	}

	public void setUseTypeName(String useTypeName) {
		this.useTypeName = useTypeName;
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

	public Boolean getShelfManageFlag() {
		return shelfManageFlag;
	}

	public void setShelfManageFlag(Boolean shelfManageFlag) {
		this.shelfManageFlag = shelfManageFlag;
	}

}
