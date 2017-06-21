package com.ds365.erp.wms.pda.common.base;

import com.ds365.commons.AppConstants;
import com.ds365.commons.message.model.MessageUserTypeEnum;

public class ConstantUrl {

	/**
	 * IP
	 */
//	public static String IP = "http://192.168.16.52:8080/";
//	public static String ip = "cdpplus.ds365.com";
	public static String ip = "123.56.202.181:8080";
	
	
	//消息地址
	public static String host_message = "192.168.62.169:8099";
	public static String host_update = "192.168.62.169";
//	public static String host_message = "192.168.16.56:8080";
	
	public static String getBaseIp(){
		return "http://"+ip+"/";
	}
	
	private static String getIp() {
		return getBaseIp() + "erp-mobile-pda-web-main/";
	}

	private static String getCommonIP() {
		return getBaseIp() + "erp-mobile-commons-web-main/";
	}

	// 请求验证码token
	public static String imageResult;
	// 请求验证码图片
	public static String image;

	//消息地址
	public static final String AppServiceUrl = "ws://192.168.16.55:8080/message-notice-service-main/ws?uid=1&userType=0&systemCode=test1&clientType=1";
	//用户消息地址
	public static String userServiceUrl;

	//查询消息列表
	public static final String message_notice_searchPageByParams = "http://192.168.16.55:8080/message-notice-web-main/message/notice/searchPageByParams";
	//查询未读消息数量
	public static final String message_notice_searchCountForUnReadByParams = "http://192.168.16.55:8080/message-notice-web-main/message/notice/searchCountForUnReadByParams";
	//修改消息为已读状态
	public static final String message_notice_modifyReadState = "http://192.168.16.55:8080/message-notice-web-main/message/notice/modifyReadState";
	
	
	
	
	
	static {
		init();
	}
	
	/**
	 * 登录
	 */
	public static String user_user_login;

	/**
	 * 修改密码
	 */
	public static String user_user_editPassword;

	/**
	 * 安全退出
	 */
	public static String user_user_logout;
	
	/**
	 * 检查是否登录
	 */
	public static String user_user_checkLogin;

	/**
	 * 获取仓库信息
	 */
	public static String warehouse_warehouse_searchByParams;

	/**
	 **** 收货入库-已收货*********
	 */
	public static String purchase_purchase_searchPagePurchaseOrderBillForReceived;

	/**
	 **** 收货入库-未收货*********
	 */
	public static String purchase_purchase_searchPagePurchaseOrderBillForNotReceive;
	
	/**
	 **** 收货入库-未收货(供应商)*********
	 */
	public static String purchase_supplierOrder_searchPageSupplierOrderForDeliverOut;
	
	/**
	 **** 收货入库-未收货(供应商)明细*********
	 */
	public static String purchase_supplierOrder_searchSupplierOrderDetailByBillId;

	/**
	 **** 查询采购入库单或者采购退出出库单列表*********
	 */
	public static String purchase_purchase_searchPagePurchaseEnterBillByParams;

	/**
	 **** 查询采购入库单或者采购退出出库单详情列表*********
	 */
	public static String purchase_purchase_searchPurchaseEnterDetailsByBillId;

	/**
	 **** 收货入库-订单详情 和 采购退货申请单详情*********
	 */
	public static String purchase_purchase_searchPurchaseOrderDetailsByBillId;

	/**
	 **** 销售退货入库退货申请单-未退货*********
	 */
	public static String sale_saleOut_searchPageSaleReturnOrderBillsForNotReturn;

	/**
	 **** 销售退货入库退货申请单-已退货*********
	 */
	public static String sale_saleOut_searchPageSaleReturnOrderBillsForReturn;

	/**
	 **** 查询销售出库单和销售退货入库单列表*********
	 */
	public static String sale_saleOut_searchPageSaleOutBillsByParams;

	/**
	 **** 根据出库单或者退货入库单Id查询单据明细*********
	 */
	public static String sale_saleOut_searchSaleOutDetailsByBillId;

	/**
	 **** 销售退货入库退货申请单详情*********
	 */
	public static String sale_saleOut_searchSaleOrderDetailByBillId;

	/**
	 **** 采购退货申请单-已退货*********
	 */
	public static String purchase_purchase_searchPagePurchaseReturnOrderBillForReturn;

	/**
	 **** 采购退货申请单-未退货*********
	 */
	public static String purchase_purchase_searchPagePurchaseReturnOrderBillForNotReturn;
	
	/**
	 **** 采购退货申请单-未退货(供应商)*********
	 */
	public static String purchase_purchase_searchPagePurchaseReturnOrderBillBySupplierForNotReturn;

	/**
	 **** 销售出库 - 装运单号-未出库*********
	 */
	public static String sale_saleOut_searchPageShipmentBillForNotOut;

	/**
	 **** 销售出库 - 装运单号-已出库*********
	 */
	public static String sale_saleOut_searchPageShipmentBillForOut;
	
	/**
	 * 分页查询装运单列表
	 */
	public static String sale_saleOut_searchPageShipmentBillByParams;

	/**
	 **** 销售出库 - 装运单号详情*********
	 */
	public static String sale_saleOut_searchShipmentGoodsDetailByBillId;

	public static String sale_saleOut_searchSaleOrderBillsByShipmentBillId;

	/**
	 * 根据装运单id查询送货单列表
	 */
	public static String sale_saleOut_searchDeliverBillByShipmentBillId;

	/****
	 * 根据送货单id查询送货商品明细
	 */
	public static String sale_saleOut_searchDeliverGoodsDetailByDeliverBillId;

	/**
	 * 根据送货单id查询订单列表
	 */
	public static String sale_saleOut_searchSaleOrderBillByDeliverBillId;

	/**
	 * 拣货单-已拣货
	 */
	public static String pick_pickBill_searchPagePickBillForPicked;
	
	/**
	 * 拣货单列表
	 */
	public static String pick_pickBill_searchPagePickBillByParams;

	/**
	 * 拣货单- 未拣货
	 */
	public static String pick_pickBill_searchPagePickBillForNotPick;

	/**
	 * 单条拣货-待拣货
	 */
	public static String pick_pickBill_searchPickBatchDetailByBillIdForNotPick;

	/**
	 * 单条拣货-已拣货
	 */
	public static String pick_pickBill_searchPickBatchDetailByBillIdForPicked;
	
	/**
	 * 根据拣货单Id查询拣货单
	 */
	public static String pick_pickBill_searchPickBillById;

	/**
	 * 单条拣货-提交
	 */
	public static String pick_pickBill_registByDetail;

	/**
	 * 拣货单明细
	 */
	// public static String PICK_BILL_DETAIL = getIp() +
	// "pick/pickBill/searchPickBatchDetailByParams"; //后台已注释
	// public static String pick_pickbill_searchPickDetailByBillId = getIp() +
	// "pick/pickBill/searchPickDetailByBillId";

	/**
	 * 拣货批次明细
	 */
	public static String pick_pickBill_searchPickBatchDetailByBillId;

	/**
	 * 上架业务***********
	 */
	// public static String PUT_AWAY_TASK = getIp() +
	// "putaway/putaway/searchPagePutawayTaskByParams";
	public static String putaway_putaway_searchPagePutawayTaskByParamsForPut;

	/**
	 * 创建上架业务单---单据--提交**********
	 */
	public static String putaway_putaway_createPutawayTask;

	public static String putaway_putaway_batchCreatePutawayRecords;

	/**
	 * 调拨入库
	 */
	public static String sale_saleOut_searchPageSaleOrderReturnBills;

	/**
	 * 收货入库 ---提交
	 */
	public static String purchase_purchase_createPurchaseEnterBill;

	/**
	 * 创建货位移动单 ---提交
	 */
	public static String stockShift_stockShift_create;

	/**
	 * 创建盘点单 ---提交
	 */
	public static String stockTake_stockTake_create;

	/**
	 * 拣货下架 ---提交
	 */
	public static String pick_pickbill_registerByBill;

	/**
	 * 退货入库 ---提交
	 */
	public static String sale_saleOut_createSaleReurnOutBill;

	/**
	 * 采购退货 ---提交
	 */
	public static String purchase_purchase_createPurchaseOutBill;

	/**
	 * 销售出库单-----提交
	 */
	public static String sale_saleOut_createShipmentOutBill;

	/**
	 * 选择收货员
	 */
	public static String employee_employee_searchPageCustodianByParams;

	/**
	 * 选择复核员
	 */
	public static String employee_employee_searchPageExaminerByParams;

	/**
	 * 选择送货员
	 */
	public static String employee_employee_searchPageDeliverByParams;
	
	/**
	 * 选择车辆
	 */
	public static String vehicle_vehicle_searchPageByParams;

	/**
	 * 选择司机
	 */
	public static String employee_employee_searchPageDriverByParams;

	/**
	 * 选择拣货员
	 */
	public static String employee_employee_searchPagePickerByParams;

	/**
	 * 选择客户
	 */
	public static String employee_employee_searchCustomerByParams;

	/**
	 * 查询库房 需要穿仓库id
	 */
	public static String warehouse_store_searchByParams;

	/**
	 * 货位库存查询
	 */
	public static String skuStock_skuStock_searchPageSkuShelfBatchStockByParams;
	
	/**
	 * 货位库存查询 查询常规货位
	 */
	public static String skuStock_skuStock_searchPageSkuShelfBatchStockForNormal;

	/**
	 * 库区库存查询
	 */
	public static String skuStock_skuStock_searchPageStoreStockByParams;
	
	/**
	 * 仓库库存查询
	 */
	public static String skuStock_skuStock_searchPageWarehouseStock;

	/**
	 * 商品sku信息查询
	 */
	public static String goods_goodsSku_searchPageGoodsSkuByParams;

	/**
	 * 根据shelfCode获取shelfId和storeName,storeId
	 */
	public static String warehouse_shelf_getShelfByCode;
	
	/**
	 * 验证同一个货位上相同sku，不同系统批次有没有混放
	 * 
	 * 传递的参数：shelfCode，SkuId,,excludeSysBatchNo【warehouseId从session中获取】
	 * 
	 * 注意该方法随时有可能会返回：【success：false,message:""】，前台一定要进行处理
	 */
	public static String skuStock_skuStock_getShelfByCodeAndVal;

	/**
	 * 获取入库暂存区货位单条记录
	 */
	public static String warehouse_shelf_getForPurchaseEnter;

	/**
	 * 获取销售退货暂存区货位单条记录
	 */
	public static String warehouse_shelf_getForSaleReturn;

	/**
	 * 分页查询货位信息
	 */
	public static String warehouse_shelf_searchPageByParams;

	/**
	 * 分页查询商品Sku批次合作信息
	 */
	public static String skuStock_skuStock_searchPageSkuBatchByParams;

	/**
	 * 分页查询商品进销存账页
	 */
	public static String skuStock_skuStock_searchPageSkuStockAccountBook;

	/**
	 * 获取上架类型
	 */
	public static String common_getPutawayTypeEnumsForJsonResult;
	
	/**
	 * 获取消息类型
	 */
	public static String common_messageCategoryForPdaEnum;

	/**
	 * 销售类型
	 */
	public static String common_getsaleTypeEnumsForJsonResult;
	
	/**
	 * 获取装运单状态
	 */
	public static String common_getShipmentBillStateEnumsForJsonResult;
	
	/**
	 * 获取拣货状态
	 */
	public static String common_getPickBillStateEnumsForJsonResult;
	
	/**
	 * 查询未上架列表
	 */
	public static String putaway_putawayBill_searchPagePutawayTaskBillsForNotPutaway;
	
	/**
	 * 查询已上架列表
	 */
	public static String putaway_putawayBill_searchPagePutawayTaskBillsForPutawayed;
	
	/**
	 * 查询上架单明细
	 */
	public static String putaway_putawayBill_searchPutawayTaskDetailByBillId;
	
	/**
	 * 查询上架任务单id查询上架任务
	 */
	public static String putaway_putawayBill_searchPutawayTaskBillById;
	
	/**
	 * 根据上架单据id查询未上架的明细
	 */
	public static String putaway_putawayBill_searchPutawayTaskDetailForNotPutawayByBillId;
	
	/**
	 * 根据上架单据id查询已上架的明细
	 */
	public static String putaway_putawayBill_searchPutawayTaskDetailForPutawayedByBillId;
	
	/**
	 * 上架
	 */
	public static String putaway_putawayBill_putaway;
	
	/**
	 * 修改上架明细
	 */
	public static String putaway_putawayBill_modifyPutawayTaskDetail;

	/**
	 * 查询员工上班实时状态
	 */
	public static String employee_work_searchPageForWorkStateByParams;
	
	/**
	 * 查询登记日志
	 */
	public static String employee_work_searchPageForWorkRegisterLogByParams;
	
	/**
	 * 上班登记
	 */
	public static String employee_work_registerForWorkOn;
	
	/**
	 * 下班登记
	 */
	public static String employee_work_registerForWorkOff;
	
	
	
	public static void init() {

		imageResult = getCommonIP() + "commons/captchaCode/getCaptchaToken";

		image = getCommonIP() + "commons/captchaCode/getCaptchaImage";

		user_user_login = getIp() + "user/user/login";

		user_user_editPassword = getIp() + "user/user/editPassword";

		user_user_logout = getIp() + "user/user/logout";

		warehouse_warehouse_searchByParams = getIp() + "warehouse/warehouse/searchByParams";

		purchase_purchase_searchPagePurchaseOrderBillForReceived = getIp() + "purchase/purchase/searchPagePurchaseOrderBillForReceived";

		purchase_purchase_searchPagePurchaseOrderBillForNotReceive = getIp() + "purchase/purchase/searchPagePurchaseOrderBillForNotReceive";
		
		purchase_supplierOrder_searchPageSupplierOrderForDeliverOut = getIp() + "purchase/supplierOrder/searchPageSupplierOrderForDeliverOut";
		
		purchase_supplierOrder_searchSupplierOrderDetailByBillId = getIp() + "purchase/supplierOrder/searchSupplierOrderDetailByBillId";

		purchase_purchase_searchPagePurchaseEnterBillByParams = getIp() + "purchase/purchase/searchPagePurchaseEnterBillByParams";

		purchase_purchase_searchPurchaseEnterDetailsByBillId = getIp() + "purchase/purchase/searchPurchaseEnterDetailsByBillId";

		purchase_purchase_searchPurchaseOrderDetailsByBillId = getIp() + "purchase/purchase/searchPurchaseOrderDetailsByBillId";

		sale_saleOut_searchPageSaleReturnOrderBillsForNotReturn = getIp() + "sale/saleOut/searchPageSaleReturnOrderBillsForNotReturn";

		sale_saleOut_searchPageSaleReturnOrderBillsForReturn = getIp() + "sale/saleOut/searchPageSaleReturnOrderBillsForReturn";

		sale_saleOut_searchPageSaleOutBillsByParams = getIp() + "sale/saleOut/searchPageSaleOutBillsByParams";

		sale_saleOut_searchSaleOutDetailsByBillId = getIp() + "sale/saleOut/searchSaleOutDetailsByBillId";

		sale_saleOut_searchSaleOrderDetailByBillId = getIp() + "sale/saleOut/searchSaleOrderDetailByBillId";

		purchase_purchase_searchPagePurchaseReturnOrderBillForReturn = getIp() + "purchase/purchase/searchPagePurchaseReturnOrderBillForReturn";

		purchase_purchase_searchPagePurchaseReturnOrderBillForNotReturn = getIp() + "purchase/purchase/searchPagePurchaseReturnOrderBillForNotReturn";
		
		purchase_purchase_searchPagePurchaseReturnOrderBillBySupplierForNotReturn = getIp() + "purchase/purchase/searchPagePurchaseReturnOrderBillBySupplierForNotReturn";

		sale_saleOut_searchPageShipmentBillForNotOut = getIp() + "sale/saleOut/searchPageShipmentBillForNotOut";

		sale_saleOut_searchPageShipmentBillForOut = getIp() + "sale/saleOut/searchPageShipmentBillForOut";
		
		sale_saleOut_searchPageShipmentBillByParams = getIp() + "sale/saleOut/searchPageShipmentBillByParams";

		sale_saleOut_searchShipmentGoodsDetailByBillId = getIp() + "sale/saleOut/searchShipmentGoodsDetailByBillId";

		sale_saleOut_searchSaleOrderBillsByShipmentBillId = getIp() + "sale/saleOut/searchSaleOrderBillsByShipmentBillId";

		sale_saleOut_searchDeliverBillByShipmentBillId = getIp() + "sale/saleOut/searchDeliverBillByShipmentBillId";

		sale_saleOut_searchDeliverGoodsDetailByDeliverBillId = getIp() + "sale/saleOut/searchDeliverGoodsDetailByDeliverBillId";

		sale_saleOut_searchSaleOrderBillByDeliverBillId = getIp() + "sale/saleOut/searchSaleOrderBillByDeliverBillId";

		pick_pickBill_searchPagePickBillForPicked = getIp() + "pick/pickBill/searchPagePickBillForPicked";
		
		pick_pickBill_searchPagePickBillByParams = getIp() + "pick/pickBill/searchPagePickBillByParams";

		pick_pickBill_searchPagePickBillForNotPick = getIp() + "pick/pickBill/searchPagePickBillForNotPick";

		pick_pickBill_searchPickBatchDetailByBillIdForNotPick = getIp() + "pick/pickBill/searchPickBatchDetailByBillIdForNotPick";

		pick_pickBill_searchPickBatchDetailByBillIdForPicked = getIp() + "pick/pickBill/searchPickBatchDetailByBillIdForPicked";
		
		pick_pickBill_searchPickBillById = getIp() + "pick/pickBill/searchPickBillById";

		pick_pickBill_registByDetail = getIp() + "pick/pickBill/registByDetail";

		pick_pickBill_searchPickBatchDetailByBillId = getIp() + "pick/pickBill/searchPickBatchDetailByBillId";

		putaway_putaway_searchPagePutawayTaskByParamsForPut = getIp() + "putaway/putaway/searchPagePutawayTaskByParamsForPut";

		putaway_putaway_createPutawayTask = getIp() + "putaway/putaway/createPutawayTask";

		putaway_putaway_batchCreatePutawayRecords = getIp() + "putaway/putaway/batchCreatePutawayRecords";

		sale_saleOut_searchPageSaleOrderReturnBills = getIp() + "sale/saleOut/searchPageSaleOrderReturnBills";

		purchase_purchase_createPurchaseEnterBill = getIp() + "purchase/purchase/createPurchaseEnterBill";

		stockShift_stockShift_create = getIp() + "stockShift/stockShift/create";

		stockTake_stockTake_create = getIp() + "stockTake/stockTake/create";

		pick_pickbill_registerByBill = getIp() + "pick/pickBill/registerByBill";

		sale_saleOut_createSaleReurnOutBill = getIp() + "sale/saleOut/createSaleReurnOutBill";

		purchase_purchase_createPurchaseOutBill = getIp() + "purchase/purchase/createPurchaseOutBill";

		sale_saleOut_createShipmentOutBill = getIp() + "sale/saleOut/createShipmentOutBill";

		employee_employee_searchPageCustodianByParams = getIp() + "employee/employee/searchPageCustodianByParams";

		employee_employee_searchPageExaminerByParams = getIp() + "employee/employee/searchPageExaminerByParams";

		employee_employee_searchPageDeliverByParams = getIp() + "employee/employee/searchPageDeliverByParams";
		
		vehicle_vehicle_searchPageByParams = getIp() + "vehicle/vehicle/searchPageByParams";

		employee_employee_searchPageDriverByParams = getIp() + "employee/employee/searchPageDriverByParams";

		employee_employee_searchPagePickerByParams = getIp() + "employee/employee/searchPagePickerByParams";

		employee_employee_searchCustomerByParams = getIp() + "employee/employee/searchPageByParams";

		warehouse_store_searchByParams = getIp() + "warehouse/store/searchByParams";

		skuStock_skuStock_searchPageSkuShelfBatchStockByParams = getIp() + "skuStock/skuStock/searchPageSkuShelfBatchStockByParams";
		
		skuStock_skuStock_searchPageSkuShelfBatchStockForNormal = getIp() + "skuStock/skuStock/searchPageSkuShelfBatchStockForNormal";

		skuStock_skuStock_searchPageStoreStockByParams = getIp() + "skuStock/skuStock/searchPageStoreStockByParams";
		
		skuStock_skuStock_searchPageWarehouseStock = getIp() + "skuStock/skuStock/searchPageWarehouseStock";

		goods_goodsSku_searchPageGoodsSkuByParams = getIp() + "goods/goodsSku/searchPageGoodsSkuByParams";

		warehouse_shelf_getShelfByCode = getIp() + "warehouse/shelf/getShelfByCode";
		
		skuStock_skuStock_getShelfByCodeAndVal = getIp() + "skuStock/skuStock/getShelfByCodeAndVal";

		warehouse_shelf_getForPurchaseEnter = getIp() + "warehouse/shelf/getForPurchaseEnter";

		warehouse_shelf_getForSaleReturn = getIp() + "warehouse/shelf/getForSaleReturn";

		warehouse_shelf_searchPageByParams = getIp() + "warehouse/shelf/searchPageByParams";
		
		skuStock_skuStock_searchPageSkuBatchByParams = getIp() + "skuStock/skuStock/searchPageSkuBatchByParams";

		skuStock_skuStock_searchPageSkuStockAccountBook = getIp() + "skuStock/skuStock/searchPageSkuStockAccountBook";

		common_getPutawayTypeEnumsForJsonResult = getCommonIP() + "common/getEnumsForJsonResult?enumName=putawayTypeEnum";
		
		common_messageCategoryForPdaEnum = getCommonIP() + "common/getEnumsForJsonResult?enumName=messageCategoryForPdaEnum";
		
		common_getsaleTypeEnumsForJsonResult = getCommonIP() + "common/getEnumsForJsonResult?enumName=goodsSaleTypeEnum";
		
		common_getShipmentBillStateEnumsForJsonResult = getCommonIP() + "common/getEnumsForJsonResult?enumName=shipmentBillStateEnum";
		
		common_getPickBillStateEnumsForJsonResult = getCommonIP() + "common/getEnumsForJsonResult?enumName=pickBillStateEnum";
		
		putaway_putawayBill_searchPagePutawayTaskBillsForNotPutaway = getIp() 
				+ "putaway/putawayBill/searchPagePutawayTaskBillsForNotPutaway";
		
		putaway_putawayBill_searchPagePutawayTaskBillsForPutawayed = getIp() 
				+ "putaway/putawayBill/searchPagePutawayTaskBillsForPutawayed";
		
		putaway_putawayBill_searchPutawayTaskDetailByBillId = getIp() 
				+ "putaway/putawayBill/searchPutawayTaskDetailByBillId";
		
		putaway_putawayBill_searchPutawayTaskBillById = getIp() + "putaway/putawayBill/searchPutawayTaskBillById";
		
		putaway_putawayBill_searchPutawayTaskDetailForNotPutawayByBillId = getIp() 
				+ "putaway/putawayBill/searchPutawayTaskDetailForNotPutawayByBillId";
		
		putaway_putawayBill_searchPutawayTaskDetailForPutawayedByBillId = getIp() 
				+ "putaway/putawayBill/searchPutawayTaskDetailForPutawayedByBillId";
		
		putaway_putawayBill_putaway = getIp() + "putaway/putawayBill/putaway";
		
		putaway_putawayBill_modifyPutawayTaskDetail = getIp() + "putaway/putawayBill/modifyPutawayTaskDetail";
		//用户请求消息地址  
		userServiceUrl = "ws://"+host_message+"/message-notice-service-main/ws?userTypeId="+MessageUserTypeEnum.REAL_USER.getId()
			+"&systemCode="+PdaConstants.SYSTEMCODE+"&"+AppConstants.PLATFORMTYPE_NAME+"="+AppConstants.PLATFORMTYPE_KEY+"&requestSourceId="+AppConstants.requestSourceValue_app+"&token=";

		employee_work_searchPageForWorkStateByParams = getIp() + "employee/work/searchPageForWorkStateByParams";
		
		employee_work_searchPageForWorkRegisterLogByParams = getIp() + "employee/work/searchPageForWorkRegisterLogByParams";
		
		employee_work_registerForWorkOn = getIp() + "employee/work/registerForWorkOn";
		
		employee_work_registerForWorkOff = getIp() + "employee/work/registerForWorkOff";
		
		user_user_checkLogin = getIp() + "user/user/checkLogin";
		
	}
}