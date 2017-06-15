package com.ds365.erp.wms.pda.common.base;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class PdaConstants {
	/**
	 * 是否启动修改配置ip信息的开关
	 */
	public static final boolean activeFlag = true;

	//本地消息表名
	public static final String MESSAGETABLENAME="pda_message";
	//与服务器约定好的订单号标识
	public static final String MESSAGE_BILLID ="billId";
	//app升级有关
	public static final String DOWNLOADAPKNAME = "erp_mobile_pda_app.apk";
//	public static final int APKVERSION = 1;
	public static final String SYSTEMCODE = "pda";
	
	public static final String MESSAGE_SER_KEY = "message";
	/**
	 * 清空listView中的数据
	 */
	public static final int CLEAR_LISTVIEW_YES = 1;
	
	/**
	 * 不清空listView中的数据
	 */
	public static final int CLEAR_LISTVIEW_NO = 2;
	
	public static final int PDA_REQUEST_CODE = 1;
	
	/**
	 * 请求数据的参数,用的地方比较多,就在这里做了常量
	 */
	public static final String fuzzy = "fuzzy";
	public static final String billId = "billId";
	public static final String id = "id";
	public static final String shipmentBillId = "shipmentBillId";
	public static final String deliverBillId = "deliverBillId";
	public static final String shelfCode = "shelfCode";
	public static final String barCode = "barCode";
	public static final String intentType = "intentType";
	
	
	/**
	 * url参数的start,limit.
	 */
	public static final int START = 0;
	public static final int LIMIT = 25;
	
	private static final AtomicInteger  RESULT_CODE=new AtomicInteger(1);
	
	private static final AtomicInteger  REQUEST_CODE=new AtomicInteger(1);
	
	public static final String scanResult = "scanResult";
	
	public static int nextResultCode(){
		
		return RESULT_CODE.incrementAndGet();
	}
	
	public static String  nextSerKey(){
		
		return UUID.randomUUID().toString();
	}
	
	public static String  nextRequestCodeKey(){
		
		return UUID.randomUUID().toString();
	}
	
	public static String nextRequestCode(){
		
		return String.valueOf(REQUEST_CODE.incrementAndGet());
	}
	
}
