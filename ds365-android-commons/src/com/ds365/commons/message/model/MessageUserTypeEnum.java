package com.ds365.commons.message.model;

/**
 *
 * Created by wanghihua on 2016/11/28.
 */
public enum MessageUserTypeEnum {

    GUEST_USER(0, "匿名用户"),
    REAL_USER(1, "实名用户"),
    //ENTERPRISE_USER(2, "企业用户")
    ;

    private Integer id;
    private String name;

    MessageUserTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public static MessageUserTypeEnum getEnumByIndex(Integer index){
        if (index == null) {
            return null;
        }
        for (MessageUserTypeEnum messageUserTypeEnum : MessageUserTypeEnum.values()) {
            if (messageUserTypeEnum.getId() == index) {
                return messageUserTypeEnum;
            }
        }
        return null;
    }

}
