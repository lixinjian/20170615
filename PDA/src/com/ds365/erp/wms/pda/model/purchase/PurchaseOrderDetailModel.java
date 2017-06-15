package com.ds365.erp.wms.pda.model.purchase;

import java.io.Serializable;
import java.util.List;

public class PurchaseOrderDetailModel extends BasePurchaseDetailModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3019676808426510667L;

	private PurchaseOrderBillModel bill;

	private Integer remainQty;

	private Integer remainUnitQty;

	private Integer remainMinUnitQty;
	
	private List<PurchaseEnterDetailModel> purchaseEnterDetails;

	public PurchaseOrderBillModel getBill() {
		return bill;
	}

	public void setBill(PurchaseOrderBillModel bill) {
		this.bill = bill;
	}

	public Integer getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Integer remainQty) {
		this.remainQty = remainQty;
	}

	public Integer getRemainUnitQty() {
		return remainUnitQty;
	}

	public void setRemainUnitQty(Integer remainUnitQty) {
		this.remainUnitQty = remainUnitQty;
	}

	public Integer getRemainMinUnitQty() {
		return remainMinUnitQty;
	}

	public void setRemainMinUnitQty(Integer remainMinUnitQty) {
		this.remainMinUnitQty = remainMinUnitQty;
	}

	public List<PurchaseEnterDetailModel> getPurchaseEnterDetails() {
		return purchaseEnterDetails;
	}

	public void setPurchaseEnterDetails(List<PurchaseEnterDetailModel> purchaseEnterDetails) {
		this.purchaseEnterDetails = purchaseEnterDetails;
	}
	
}