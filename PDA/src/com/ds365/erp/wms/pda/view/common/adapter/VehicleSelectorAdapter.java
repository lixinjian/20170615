package com.ds365.erp.wms.pda.view.common.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.model.commons.VehicleModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VehicleSelectorAdapter extends CommonAdapter<VehicleModel> {
	
	public VehicleSelectorAdapter(Context context, List<VehicleModel> list) {
		super(context, list);
	}

	@Override
	public int getContentViewId() {
		return R.layout.common_vehicle_selector_adapter;
	}

	@Override
	public CommonAdapter<VehicleModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.plateNo = (TextView) contentView.findViewById(R.id.common_plateNo_value);
		holder.color = (TextView) contentView.findViewById(R.id.common_color_value);
		holder.driver = (TextView) contentView.findViewById(R.id.common_driver_value);
		holder.loadWeight = (TextView) contentView.findViewById(R.id.common_loadWeight_value);
		holder.loadVolume = (TextView) contentView.findViewById(R.id.common_loadVolume_value);
		return holder;
	}

	@Override
	public void setItemView(int position, CommonAdapter<VehicleModel>.HolderView contentView, VehicleModel item) {
		ViewHold holder = (ViewHold) contentView;
		holder.plateNo.setText(list.get(position).getPlateNo());
		holder.color.setText(list.get(position).getColor());
		holder.driver.setText(list.get(position).getDriver().getName());
		holder.loadWeight.setText(String.valueOf(list.get(position).getLoadWeight()));
		holder.loadVolume.setText(String.valueOf(list.get(position).getLoadVolume()));
	}
	class ViewHold extends HolderView{
		TextView plateNo; // 车牌号
		TextView color; // 颜色
		TextView driver; // 车辆默认司机
		TextView loadWeight; // 负载重量
		TextView loadVolume; // 容积
	}
}