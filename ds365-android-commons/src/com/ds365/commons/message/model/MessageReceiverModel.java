package com.ds365.commons.message.model;

import java.io.Serializable;

public class MessageReceiverModel implements Serializable{

	private static final long serialVersionUID = 2155120745669686573L;
	private long userId;
	private String userName;
	private String userPK;
	private String systemCode;
	private boolean deleteFlag;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPK() {
		return userPK;
	}
	public void setUserPK(String userPK) {
		this.userPK = userPK;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}
