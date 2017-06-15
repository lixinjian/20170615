package com.ds365.erp.wms.pda.common.enums;

import com.ds365.commons.message.MessageFunctionType;
import com.ds365.erp.wms.pda.view.stock.pick.activity.PickBillRegistForSingleActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.activity.PutawayTaskDetailActivity;



public enum  MessageFunctionTypeEnum  implements MessageFunctionType{

	PUT_AWAY_BILL(101, "putawayBill", "上架任务单",PutawayTaskDetailActivity.class),
	PICK_BILL(102, "pickBill", "拣货任务单",PickBillRegistForSingleActivity.class);

    private Integer id;
    private String name;
    private Class intentClass;

    MessageFunctionTypeEnum(Integer id, String code, String name,Class intentClass) {
        this.id = id;
        this.name = name;
		this.intentClass = intentClass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class getIntentClass() {
		return this.intentClass;
	}
}
