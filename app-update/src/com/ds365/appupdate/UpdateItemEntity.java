package com.ds365.appupdate;

import java.io.Serializable;


public class UpdateItemEntity  implements Serializable{

	private static final long serialVersionUID = 965732026436162374L;

	private UpdateDataEntity updateData;

	private Long functionId;

	private String functionName;
	
	private String updateInfo;

	public UpdateDataEntity getUpdateData() {
		return updateData;
	}

	public void setUpdateData(UpdateDataEntity updateData) {
		this.updateData = updateData;
	}

	public Long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

	public String getUpdateInfo() {
		return updateInfo;
	}

	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

}