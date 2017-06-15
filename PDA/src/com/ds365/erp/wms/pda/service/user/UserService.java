package com.ds365.erp.wms.pda.service.user;

import java.util.Map;

import com.ds365.commons.AbstractBaseService;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.user.CheckUserLoginModel;
import com.ds365.erp.wms.pda.model.user.LoginAuthParamModel;
import com.ds365.erp.wms.pda.model.user.LoginUserCreateParamsModel;
import com.ds365.erp.wms.pda.model.user.SessionUser;
import com.ds365.erp.wms.pda.model.user.VerifyCodeModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

/**
 * 说明 ：userService
 */
public class UserService extends AbstractBaseService{

	/**
	 * 获取验证码的解析器
	 */
	private JsonParser<JsonResult<VerifyCodeModel>> captchaTokenParser = new JsonParser<JsonResult<VerifyCodeModel>>() {
	};

	/**
	 * 登录的解析器
	 */
	private JsonParser<JsonResult<SessionUser>> loginParser = new JsonParser<JsonResult<SessionUser>>() {
	};

	public void getCaptchaToken(ServiceCallBack<VerifyCodeModel> callBack) {
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.imageResult);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<VerifyCodeModel>>(captchaTokenParser, callBack) {
		});
	}

	/**
	 * 登录
	 */
	public void login(LoginAuthParamModel loginAuthParamModel,
			final ServiceCallBack<JsonResult<SessionUser>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		
		Map map  = (Map) BeanUtils.beanToMap(loginAuthParamModel);
		
		params.setUrl(ConstantUrl.user_user_login);
		params.setParams(map);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<SessionUser>>(loginParser, callBack) {

			@Override
			public void doSuccess(JsonResult result) {
				callBack.doSuccess(result);
			}
		});
	}

	/**
	 * 修改密码
	 */
	public void editPassword(LoginUserCreateParamsModel loginUserCreateParamsModel,
			ServiceCallBack<String> callBack) {

		RequestParamsModel params = new RequestParamsModel();
		
		Map map  = (Map) BeanUtils.beanToMap(loginUserCreateParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.user_user_editPassword);

		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}
	
	/**
	 * 安全退出
	 */
	public void logout(ServiceCallBack<String> callBack){
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.user_user_logout);
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<String>>(stringParser, callBack) {
		});
	}
	
	/**
	 * 检查是否登录
	 */
	public void checkLogin(AbstractResponseHandler<JsonResult<CheckUserLoginModel>> abstractResponseHandler){
		RequestParamsModel params = new RequestParamsModel();
		params.setUrl(ConstantUrl.user_user_checkLogin);
		RequestUtil.request(params,abstractResponseHandler);
	}
}
