package com.ds365.erp.wms.pda.view.common.adapter;

import java.util.List;

import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.commons.utils.CommonAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EmployeeSelectorAdapter extends CommonAdapter<EmployeeModel> {
	
	
	
	public EmployeeSelectorAdapter(Context context, List<EmployeeModel> list) {
		super(context, list);

	}

	@Override
	public int getContentViewId() {
		return R.layout.common_employee_selector_adapter;
	}

	@Override
	public CommonAdapter<EmployeeModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.employeeCode = (TextView) contentView.findViewById(R.id.common_employeeCode_value);
		holder.employeeName = (TextView) contentView.findViewById(R.id.common_employeeName_value);
		holder.phoneNo = (TextView) contentView.findViewById(R.id.common_phoneNo_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<EmployeeModel>.HolderView contentView, EmployeeModel item) {
		ViewHold holder = (ViewHold) contentView;
		holder.employeeCode.setText(list.get(position).getCode());
		holder.employeeName.setText(list.get(position).getName());
		holder.phoneNo.setText(list.get(position).getPhone());
	}
	class ViewHold extends HolderView{
		TextView employeeCode; // 员工编号
		TextView employeeName; // 姓名
		TextView phoneNo; // 手机号
	}
}