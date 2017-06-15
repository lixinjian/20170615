package com.ds365.commons.json;

import java.io.Serializable;

/**
 * 
 * @author cgb
 *后台响应json格式标准
 */
public class JsonResult<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3863559687276427577L;

	private boolean success=true;
	private T  data;//数据
	private String message;
	private String code;
	private String token;
	
	public JsonResult(){};
	
	public JsonResult(T data, Boolean success, String message,String code) {
		// TODO Auto-generated constructor stub
		this.data=data;
		this.success=success;
		this.message=message;
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public  static <T> JsonResult<T> newResult(){
		return new JsonResult<T>();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
