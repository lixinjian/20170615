package com.ds365.commons.json;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
*  这里是关于自定义网络框架的网络请求封装的请求的JavaBean
*  主要用来封装关于网络请求过程主要的参数
*/
public class RequestParamsModel {

    /**
     * 当前网络请求的类型
     * 1、HTTP_GET     http协议的Get请求
     * 2、HTTP_POST    http协议的Post请求
     * 3、HTTPS_GET    https协议的Get请求（客户端不包含证书访问）
     * 4、HTTPS_POST   https协议的Post请求（客户端不包含证书的访问）
     */
    private int requestType=RequestType.HTTP_POST;
    
    /**
     * 关于网络访问的url
     * 1、使用Get请求的操作的时候，需要将参数直接拼接到baseUrl后
     * 2、使用Post请求的操作的时候，这里的baseUrl直接存储post请求的url参数
     *    至于Post请求的参数直接放到HashMap集合中
     */
    private String url;
    /**
     * 用来存储使用Post请求操作的时候，使用的参数
     */
    private Map<String,Object> params=new HashMap<String,Object>();
    /**
     * 关于Post请求中文件的存储集合
     */
    private Map<String,File> paramFiles=new HashMap<String,File>();
    
    private String  jsonBody;
    
    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String,Object> getParams() {
        return params;
    }

    public void setParams(Map<String,Object> params) {
        this.params = params;
    }

	public Map<String, File> getParamFiles() {
		return paramFiles;
	}

	public void setParamFiles(Map<String, File> paramFiles) {
		this.paramFiles = paramFiles;
	}

	public String getJsonBody() {
		return jsonBody;
	}

	public void setJsonBody(String jsonBody) {
		this.jsonBody = jsonBody;
	}
   
}
