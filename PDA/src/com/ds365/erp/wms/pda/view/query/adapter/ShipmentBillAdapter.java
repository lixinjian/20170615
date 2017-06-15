package com.ds365.erp.wms.pda.view.query.adapter;

import java.util.List;

import com.ds365.commons.utils.CommonAdapter;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.shipment.ShipmentOrderBillModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShipmentBillAdapter extends CommonAdapter<ShipmentOrderBillModel> {
	
	public static final  String SER_KEY=PdaConstants.nextSerKey();

	public ShipmentBillAdapter(Context context, List<ShipmentOrderBillModel> list) {
		super(context, list);
	}
	
	@Override
	public int getContentViewId() {
		return R.layout.query_shipment_bill_adapter;
	}

	@Override
	public CommonAdapter<ShipmentOrderBillModel>.HolderView getHoldView(int position, View contentView, ViewGroup parent) {
		ViewHold holder = new ViewHold();
		holder.billCode = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_billCode_value);
		holder.plateNo = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_plateNo_value);
		holder.itemCount = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_itemCount_value);
		holder.orderCount = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_orderCount_value);
		holder.driverName = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_driverName_value);
		holder.makeTime = (DateField) contentView.findViewById(R.id.shipmentBillAdapter_makeTime_value);
		holder.billState = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_billState_value);
		holder.realVolume = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_realVolume_value);
		holder.realWeight = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_realWeight_value);
		holder.deliverStationCount = (TextView) contentView.findViewById(R.id.shipmentBillAdapter_deliverStationCount_value);
		return holder;
	}

	@Override
	public void setItemView(final int position, CommonAdapter<ShipmentOrderBillModel>.HolderView contentView, ShipmentOrderBillModel arg2) {
		ViewHold holder = (ViewHold) contentView;
		holder.billCode.setText(list.get(position).getBillCode());
		holder.plateNo.setText(list.get(position).getVehicle().getPlateNo());
		holder.itemCount.setText(String.valueOf(list.get(position).getItemCount()));
		holder.orderCount.setText(String.valueOf(list.get(position).getOrderCount()));
		holder.driverName.setText(list.get(position).getDriver().getName());
		holder.makeTime.setValue(list.get(position).getMakeTime());
		holder.billState.setText(list.get(position).getBillState().getName());
		holder.realVolume.setText(String.valueOf(list.get(position).getRealVolume()));
		holder.realWeight.setText(String.valueOf(list.get(position).getRealWeight()));
		holder.deliverStationCount.setText(String.valueOf(list.get(position).getDeliverStationCount()));
	}
	
	class ViewHold extends HolderView{
		TextView billCode;			//订单号
		TextView plateNo;			//车牌号
		TextView itemCount;			//总数量
		TextView orderCount;		//订单个数
		TextView driverName;		//司机名称
		DateField makeTime;			//订单创建时间
		TextView billState;			//订单状态
		TextView realVolume;		//体积
		TextView realWeight;		//重量
		TextView deliverStationCount;//配送站数量
	}
}
