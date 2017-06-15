package com.ds365.erp.wms.pda.common.billtypeenum;

import java.io.Serializable;

public interface BaseEnum <K>  extends Serializable{
	
	K getIndex();
	
	String getDescription();
	
}
