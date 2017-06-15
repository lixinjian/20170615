package com.ds365.commons.message.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.ds365.commons.enums.EnumModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public  class MessageModel/**<C extends MessageCategoryType,T extends MessageFunctionType> */implements Serializable{

	private static final long serialVersionUID = -1729227871779842317L;
	
	private long id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date sendTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
	private Date expiryTime;

	private EnumModel<Integer> messageType;

	private EnumModel<Integer> messageCategory;

	private String title;

	private String content;
	/**
     * 消息类型对应的功能标识
     */
    private EnumModel<Integer> messageFunctionType;

	private boolean readFlag;
	
	private Map<String, Object> paramsMap;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public EnumModel<Integer> getMessageType() {
		return messageType;
	}

	public void setMessageType(EnumModel<Integer> messageType) {
		this.messageType = messageType;
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

	public boolean getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public EnumModel<Integer> getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(EnumModel<Integer> messageCategory) {
		this.messageCategory = messageCategory;
	}

	public EnumModel<Integer> getMessageFunctionType() {
		return messageFunctionType;
	}

	public void setMessageFunctionType(EnumModel<Integer> messageFunctionType) {
		this.messageFunctionType = messageFunctionType;
	}

//	public C getMessageCategory() {
//		return messageCategory;
//	}
//
//	public void setMessageCategory(C messageCategory) {
//		this.messageCategory = messageCategory;
//	}
//
//	public T getMessageFunctionType() {
//		return messageFunctionType;
//	}
//
//	public void setMessageFunctionType(T messageFunctionType) {
//		this.messageFunctionType = messageFunctionType;
//	}

}
