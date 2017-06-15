package com.ds365.shop.asynchttpclientlibrary;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.entity.StringEntity;

import android.util.Log;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.json.RequestType;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class RequestUtil {

	private static AsyncHttpClient client = new AsyncHttpClient();
	
	private static int timeOut=15*1000;
	
	static{
		client.setTimeout(timeOut);
	}

	public static <T> void request(RequestParamsModel params, AbstractResponseHandler<T> responseHandler)  {
		validate(params);
		RequestParams nativePrams = convertToNative(params.getParams());
		
		if(AppGlobal.ISOPENTOKEN){
			nativePrams.put(AppConstants.requestSourceParamsName, AppConstants.requestSourceValue_app);
			nativePrams.put(AppConstants.tokenParamsName, AppGlobal.TOKEN);
			
			client.addHeader(AppConstants.requestSourceParamsName, AppConstants.requestSourceValue_app);
			client.addHeader(AppConstants.tokenParamsName,AppGlobal.TOKEN);
			client.addHeader(AppConstants.PLATFORMTYPE_NAME,AppConstants.PLATFORMTYPE_KEY);
		}

		switch (params.getRequestType()) {
		case RequestType.HTTP_GET:
			client.get(params.getUrl(), nativePrams, responseHandler.nativeHandler);
			break;

		case RequestType.HTTP_POST:
			Log.i("net", params.getUrl()+"参数"+nativePrams.toString());
			client.post(params.getUrl(), nativePrams, responseHandler.nativeHandler);
			break;

		case RequestType.HTTP_JSON_POST:
			
			
			
			break;

		default:
			Log.i("net", params.getUrl()+"参数"+nativePrams.toString());
			client.post(params.getUrl(), nativePrams, responseHandler.nativeHandler);
			break;
		}

	}

	public static <T> void requestJson(RequestParamsModel params, AbstractResponseHandler<T> responseHandler) {
		validate(params);
		RequestParams nativePrams = convertToNative(params.getParams());
		client.addHeader(AppConstants.requestSourceParamsName, AppConstants.requestSourceValue_app);
		client.addHeader(AppConstants.tokenParamsName,AppGlobal.TOKEN);
		client.addHeader(AppConstants.PLATFORMTYPE_NAME,AppConstants.PLATFORMTYPE_KEY);
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(params.getJsonBody(),"utf-8");
			Log.i("PDA", params.getUrl()+"参数"+params.getJsonBody());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("json创建StringEntity编码错误",e);
		}
		
		client.post(responseHandler.getCallBack().getContext(), params.getUrl(), stringEntity, "application/json", responseHandler.nativeHandler);
//			Log.e("Pda", ConstantUrl.BASE_URL + params.getUrl() + nativePrams.toString());
	}


	private  static void validate(RequestParamsModel params){
		
		String url=params.getUrl();
		if(url==null||url.trim().equals(""))
			throw new RuntimeException("严重错误：请求url为空");
		
	}
	
	private static RequestParams convertToNative(Map<String,Object> params) {
		RequestParams requestParams = new RequestParams();

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			requestParams.put(entry.getKey(),String.valueOf(entry.getValue()));
		}

		return requestParams;
	}

}
