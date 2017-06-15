package com.ds365.erp.wms.pda.model.pickbill;
/**   
 * @Title: PickBillStateEnum.java 
 * @Package com.ds365.erp.business.pick.enums 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author huanggaoren    
 * @date 2015年7月15日 下午1:40:07 
 * @version V1.0  
 */

import com.ds365.erp.wms.pda.common.billtypeenum.BaseEnum;

/**
 * @ClassName: PickBillStateEnum
 * @Description: TODO 拣货单单据状态
 * @author huanggaoren 
 * @date 2015年7月15日 下午1:40:07
 * 
 */
public enum PickBillStateEnum implements BaseEnum<Integer> {

	// 待拣货，已拣货
	WAIT_PICKED(1, "待拣货"), PICKED_COMPLETE(2, "已拣货");
	private Integer index;

	private String description;

	PickBillStateEnum(Integer index, String description) {
		this.index = index;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Integer getIndex() {
		return index;
	}
}
