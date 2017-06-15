package com.ds365.commons;

import com.ds365.commons.json.JsonParser;
import com.ds365.commons.json.JsonResult;

public abstract class AbstractBaseService {

	protected JsonParser<JsonResult<String>> stringParser = new JsonParser<JsonResult<String>>() {
	};
	
	
}
