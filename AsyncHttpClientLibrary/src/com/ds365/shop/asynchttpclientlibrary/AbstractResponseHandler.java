package com.ds365.shop.asynchttpclientlibrary;

import android.util.Log;

import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.ResultCodeEnum;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.shop.asynchttpclientloading.LoadingHanlder;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 */
public abstract class AbstractResponseHandler<T>{

    /**
     * 由于每个接口的返回数据不同，要根据对应的JavaBean进行解析
     */
    protected JsonParser<T> jsonParser ; //new BaseParser<T>(){};
    
   // private int  parserType = 0;
    private LoadingHanlder loadingHandler;
    private ServiceCallBack callBack;
    public AbstractResponseHandler(JsonParser<T> jsonParser,ServiceCallBack callBack){
    	this.jsonParser=jsonParser;
    	this.callBack = callBack;
    	loadingHandler = new LoadingHanlder(callBack.getContext());
    }
    //供心跳检查使用
    public AbstractResponseHandler(JsonParser<T> jsonParser){
    	this.jsonParser=jsonParser;
    }
    
//    public AbstractResponseHandler(JsonParser<T> jsonParser,int parserType){
//    	this.jsonParser=jsonParser;
//    	//this.parserType = parserType;
//    	Context context = ApplicationContextUtils.getContext();
//    	loadingHandler = new LoadingHanlder(context);
//    }
    
    public ServiceCallBack getCallBack() {
		return callBack;
	}

	AsyncHttpResponseHandler nativeHandler=new AsyncHttpResponseHandler(){
    	
    	@Override
		public void onFailure(Throwable e) {
			// TODO Auto-generated method stub
			//super.onFailure(arg0);
    		doFaile("网络错误,请稍后重试!");
			if (loadingHandler != null) {
				loadingHandler.sendEmptyMessage(AppConstants.CANCLE_DIALOG);
			}
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			super.onFinish();
			if (loadingHandler != null) {
				loadingHandler.sendEmptyMessage(AppConstants.CANCLE_DIALOG);
			}
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			if (loadingHandler != null) {
				loadingHandler.sendEmptyMessage(AppConstants.CREATE_DIALOG);
			}
		}

		/**
		 * 这里最后加上系统标识
		 */
		@Override
		public void onSuccess(String json) {
			Log.i("PDA", json);
			if (loadingHandler != null) {
				loadingHandler.sendEmptyMessage(AppConstants.CANCLE_DIALOG);
			}
			if (json != null && !json.equals("")) {
					T result = jsonParser.parseJSON(json);
					if (result instanceof JsonResult) {
						JsonResult tempResult = (JsonResult) result;
						if (tempResult.isSuccess()) {
							//根据code值获取token
							if(AppGlobal.ISOPENTOKEN){
								if(tempResult.getCode()!=null&&ResultCodeEnum.idOf(tempResult.getCode())==ResultCodeEnum.tokenTimeout){
									AppGlobal.tokenTimeOutListener.onTokenTimeOut(callBack.getContext());
								}else{
									/*if(ResultCodeEnum.idOf(tempResult.getCode())==ResultCodeEnum.tokenEmpty){
										ConstantUrl.TOKEN = tempResult.getToken();
									}*/
									doSuccess(tempResult);
								}
							}else{
								doSuccess(tempResult);
							}
						} else {
							doFaile(tempResult.getMessage());
						}
					}else {
						doFaile("服务器端返回JSON格式错误!");
					}
			} else {
				doFaile("服务器返回JSON数据非法，数据为空!");
			}
		}
    };
    
    public void doSuccess (JsonResult result){
    	if(callBack!=null){
    		callBack.doSuccess(result.getData());
    	}
    }
    
    public void doFaile(String str){
    	if(callBack!=null){
    		callBack.doFaile(str);
    	}
    }
}
