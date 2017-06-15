package com.ds365.commons.message.model;

import java.io.Serializable;


public enum MessageTypeEnum implements Serializable{
    TEXT(1, "文本"),
    LINK(2, "链接"),
    IMAGE(3, "图片"),
    VOICE(4, "语音"),
    VIDEO(5, "视频");
    
    private Integer id;
    private String name;

    MessageTypeEnum(Integer id,String name) {
        this.id = id;
        this.name = name;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
