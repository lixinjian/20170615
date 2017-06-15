package com.ds365.appupdate;

import java.io.Serializable;
import java.util.List;

import com.ds365.commons.enums.EnumModel;

public class UpdateDataEntity implements Serializable{

	private static final long serialVersionUID = 6734311708189627274L;

	private Integer updateCode;

	private Boolean forceUpdateFlag;

	private Long appId;

	private String appName;

	private EnumModel<Integer> deviceType;

	private String versionCode;

	private String versionName;

	private String url;

	private Long size;

	private String md5;

	private String description;

	private List<UpdateItemEntity> updateItems;
	
	public Integer getUpdateCode() {
		return updateCode;
	}

	public void setUpdateCode(Integer updateCode) {
		this.updateCode = updateCode;
	}

	public Boolean getForceUpdateFlag() {
		return forceUpdateFlag;
	}

	public void setForceUpdateFlag(Boolean forceUpdateFlag) {
		this.forceUpdateFlag = forceUpdateFlag;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public EnumModel<Integer> getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(EnumModel<Integer> deviceType) {
		this.deviceType = deviceType;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UpdateItemEntity> getUpdateItems() {
		return updateItems;
	}

	public void setUpdateItems(List<UpdateItemEntity> updateItems) {
		this.updateItems = updateItems;
	}

}