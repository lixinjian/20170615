package com.ds365.commons;
/** * @author yxz  
 * @date 创建时间：2016-8-22 下午4:12:13 
 * @version 1.0  
 */
public enum ResultCodeEnum {

	tokenOK("1","token正常"),tokenTimeout("2","token失效"),
	tokenEmpty("3","token为空"),
	LOGIN_SUCESS("4","登录成功"),
    LOGIN_FAILURE("5","登录失败")
	; //    调用构造函数来构造枚举项

    private String id ;
    
    private String name;

    private ResultCodeEnum(String id,String name) {    //    必须是private的，否则编译错误
        this.id = id;
        this.name=name;
    }

    public static ResultCodeEnum idOf(String id) {    //    手写的从int到enum的转换函数
        switch (id) {
        case "1":
            return tokenOK;
        case "2":
            return tokenTimeout;
        case "3":
            return tokenEmpty;
        default:
            return null;
        }
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
	
}
