package com.ds365.commons.message.model;

import java.io.Serializable;

public class MessageDatebaseModel implements Serializable{

	/**
	 * 用来转换服务器和本地存取消息
	 */
	private static final long serialVersionUID = -380662210741370475L;
	
	private int id;
	private int _id;
//	日期格式"yyyy-MM-dd HH:mm:ss"
	private String sendTime;
	private String expiryTime;
	private int messageType;

	private int messageCategory;

	private String title;

	private String content;
	/**
     * 消息类型对应的功能标识
     */
    private int messageFunctionType = 0;
    
    //服务器为boolean,这里用0和1区分
	private int readFlag = 0;
	
	private String paramsMap="";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(int messageCategory) {
		this.messageCategory = messageCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMessageFunctionType() {
		return messageFunctionType;
	}

	public void setMessageFunctionType(int messageFunctionType) {
		this.messageFunctionType = messageFunctionType;
	}

	public int getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(int readFlag) {
		this.readFlag = readFlag;
	}

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(String paramsMap) {
		this.paramsMap = paramsMap;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

}
