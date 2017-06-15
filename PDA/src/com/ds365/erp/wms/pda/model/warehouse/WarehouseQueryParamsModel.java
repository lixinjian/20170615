package com.ds365.erp.wms.pda.model.warehouse;

import com.ds365.commons.base.model.BasePageQueryParamsModel;

public class WarehouseQueryParamsModel extends BasePageQueryParamsModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3887365341252899602L;

	private String code;

	// 名称
	private String name;

	// 助记码
	private String helpCode;

	// 城市区域
	private Long regionId;

	private Long regionName;

	// 城市区域
	private Long cityId;

	private Long cityName;

	private Long provinceId;

	private Long provinceName;

	// 类型
	private Integer typeId;

	private String typeName;

	// 地址
	private String address;

	// 联系人
	private String contactor;

	// 联系电话
	private String phoneNo;

	// 移动电话
	private String mobileNo;

	// 邮箱
	private String email;

	private Boolean shelfManageFlag;

	private Long organizationId;// 所属组织

	private Long purchaseOrganizationId;// 采购组织id

	private Long saleOrganizationId;// 销售组织id

	private Long purchasePaymentOrganizationId;// 采购收款组织Id

	private Long saleReceiveOrganizationId;// 销售收款组织Id

	private Long deliverOrganizationId;// 仓储物流组织id

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

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getRegionName() {
		return regionName;
	}

	public void setRegionName(Long regionName) {
		this.regionName = regionName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getCityName() {
		return cityName;
	}

	public void setCityName(Long cityName) {
		this.cityName = cityName;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(Long provinceName) {
		this.provinceName = provinceName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getShelfManageFlag() {
		return shelfManageFlag;
	}

	public void setShelfManageFlag(Boolean shelfManageFlag) {
		this.shelfManageFlag = shelfManageFlag;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getPurchaseOrganizationId() {
		return purchaseOrganizationId;
	}

	public void setPurchaseOrganizationId(Long purchaseOrganizationId) {
		this.purchaseOrganizationId = purchaseOrganizationId;
	}

	public Long getSaleOrganizationId() {
		return saleOrganizationId;
	}

	public void setSaleOrganizationId(Long saleOrganizationId) {
		this.saleOrganizationId = saleOrganizationId;
	}

	public Long getPurchasePaymentOrganizationId() {
		return purchasePaymentOrganizationId;
	}

	public void setPurchasePaymentOrganizationId(Long purchasePaymentOrganizationId) {
		this.purchasePaymentOrganizationId = purchasePaymentOrganizationId;
	}

	public Long getSaleReceiveOrganizationId() {
		return saleReceiveOrganizationId;
	}

	public void setSaleReceiveOrganizationId(Long saleReceiveOrganizationId) {
		this.saleReceiveOrganizationId = saleReceiveOrganizationId;
	}

	public Long getDeliverOrganizationId() {
		return deliverOrganizationId;
	}

	public void setDeliverOrganizationId(Long deliverOrganizationId) {
		this.deliverOrganizationId = deliverOrganizationId;
	}

}
