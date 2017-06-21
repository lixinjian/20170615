package com.ds365.erp.wms.pda.service.employee;

import java.util.Map;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.commons.utils.BeanUtils;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkRegisterLogModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkRegisterModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkStateModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeWorkStateQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class EmployeeWorkService {

	private JsonParser<JsonResult<QueryResult<EmployeeWorkStateModel>>> employeeWorkStateParser = new JsonParser<JsonResult<QueryResult<EmployeeWorkStateModel>>>() {
	};

	private JsonParser<JsonResult<QueryResult<EmployeeWorkRegisterLogModel>>> employeeWorkRegisterLogParser = new JsonParser<JsonResult<QueryResult<EmployeeWorkRegisterLogModel>>>() {
	};

	private JsonParser<JsonResult> registerForWorkParser = new JsonParser<JsonResult>() {
	};

	/**
	 * 查询员工上班状态
	 */
	public void searchPageForWorkStateByParams(EmployeeWorkStateQueryParamsModel employeeWorkStateQueryParamsModel,
			ServiceCallBack<QueryResult<EmployeeWorkStateModel>> callBack) {
		
		RequestParamsModel params = new RequestParamsModel();
		Map<String,Object> map  = BeanUtils.beanToMap(employeeWorkStateQueryParamsModel);
		
		params.setParams(map);
		params.setUrl(ConstantUrl.employee_work_searchPageForWorkStateByParams);

		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<EmployeeWorkStateModel>>>(
				employeeWorkStateParser, callBack) {
		});
	}

	/**
	 * 查询登记日志
	 */
	public void searchPageForWorkRegisterLogByParams(RequestParamsModel params,
			ServiceCallBack<QueryResult<EmployeeWorkRegisterLogModel>> callBack) {

		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<EmployeeWorkRegisterLogModel>>>(
				employeeWorkRegisterLogParser, callBack) {
		});
	}

	/**
	 * 上班登记
	 */
	public void registerForWorkOn(EmployeeWorkRegisterModel employeeWorkRegisterModel, ServiceCallBack<JsonResult> callBack) {

		String url = ConstantUrl.employee_work_registerForWorkOn;
		registerForWork(url, employeeWorkRegisterModel, callBack);
	}

	/**
	 * 下班登记
	 */
	public void registerForWorkOff(EmployeeWorkRegisterModel employeeWorkRegisterModel, ServiceCallBack<JsonResult> callBack) {
		
		String url = ConstantUrl.employee_work_registerForWorkOff;
		registerForWork(url, employeeWorkRegisterModel, callBack);
	}
	
	private void registerForWork(String url, EmployeeWorkRegisterModel employeeWorkRegisterModel, ServiceCallBack<JsonResult> callBack){
		
		RequestParamsModel params = new RequestParamsModel();
		Map<String,Object> map  = BeanUtils.beanToMap(employeeWorkRegisterModel);
		
		params.setParams(map);
		params.setUrl(url);
		
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult>(registerForWorkParser, callBack) {
		});
		
	}
	
	
	
}
