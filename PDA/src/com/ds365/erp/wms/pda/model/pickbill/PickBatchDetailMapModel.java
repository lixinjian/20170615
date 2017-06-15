package com.ds365.erp.wms.pda.model.pickbill;

import java.io.Serializable;
import java.util.List;

import com.ds365.commons.BaseIdModel;

public class PickBatchDetailMapModel extends BaseIdModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3165373630524526299L;

	private Long batchDetailId;

	private List<PickBatchDetailModel> batchDetails;

	public Long getBatchDetailId() {
		return batchDetailId;
	}

	public void setBatchDetailId(Long batchDetailId) {
		this.batchDetailId = batchDetailId;
	}

	public List<PickBatchDetailModel> getBatchDetails() {
		return batchDetails;
	}

	public void setBatchDetails(List<PickBatchDetailModel> batchDetails) {
		this.batchDetails = batchDetails;
	}

}
