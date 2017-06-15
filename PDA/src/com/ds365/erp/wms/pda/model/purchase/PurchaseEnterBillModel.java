package com.ds365.erp.wms.pda.model.purchase;

import java.io.Serializable;
import java.util.Date;

import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonFormat;


public class PurchaseEnterBillModel extends BasePurchaseBillModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7131530322703607475L;

		//*******
		private String deliverBillNo;//发货单号
		
		private  EmployeeModel registrar;//登记员
		
		private EmployeeModel examiner;//验收员/收货员
		
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
		private Date  enterDate;//采购收货入库时间、采购退货出库时间
		
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh_CN", timezone = "GMT+8")
		private Date orderMakeTime;//采购订单时间
		
		public String getDeliverBillNo() {
			return deliverBillNo;
		}

		public void setDeliverBillNo(String deliverBillNo) {
			this.deliverBillNo = deliverBillNo;
		}

		public EmployeeModel getRegistrar() {
			return registrar;
		}

		public void setRegistrar(EmployeeModel registrar) {
			this.registrar = registrar;
		}

		public EmployeeModel getExaminer() {
			return examiner;
		}

		public void setExaminer(EmployeeModel examiner) {
			this.examiner = examiner;
		}

		public Date getEnterDate() {
			return enterDate;
		}

		public void setEnterDate(Date enterDate) {
			this.enterDate = enterDate;
		}

		public Date getOrderMakeTime() {
			return orderMakeTime;
		}

		public void setOrderMakeTime(Date orderMakeTime) {
			this.orderMakeTime = orderMakeTime;
		}
		
}