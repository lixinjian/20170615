package com.ds365.erp.wms.pda.service.employee;

import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.json.RequestParamsModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeQueryParamsModel;
import com.ds365.shop.asynchttpclientlibrary.AbstractResponseHandler;
import com.ds365.shop.asynchttpclientlibrary.RequestUtil;

public class EmployeeService {

	private JsonParser<JsonResult<QueryResult<EmployeeModel>>> employeeParser = new JsonParser<JsonResult<QueryResult<EmployeeModel>>>() {
	};

	public void searchPageByParams(String url, EmployeeQueryParamsModel  queryParamsModel, ServiceCallBack<QueryResult<EmployeeModel>> callBack) {

		
		RequestParamsModel params = new RequestParamsModel();
		
		params.setUrl(url);
		
		
		RequestUtil.request(params,
				new AbstractResponseHandler<JsonResult<QueryResult<EmployeeModel>>>(employeeParser, callBack) {
				});
	}

}
