package com.ds365.erp.wms.pda.view.outwarehouse;

import com.ds365.commons.utils.IntentUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.activity.PurchaseReturnOrderBillListActivity;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity.ShipmentOrderBillListActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

/**
 * 这里是关于出库业务的Fragment
 */
@SuppressLint("NewApi")
public class OutWareHouseFragment extends BaseFragment implements OnClickListener {
	private Context context;
	private RelativeLayout saleOutButton;
	private RelativeLayout purchaseEnterButton;
	@Override
	protected void initFragmentRequestNet() {

	}

	@Override
	protected View initFragmentView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.outwarehouse_main_fragment, null);
		context = getActivity();
		saleOutButton = (RelativeLayout) view.findViewById(R.id.outWareHouse_SaleOutBill);
		purchaseEnterButton = (RelativeLayout) view.findViewById(R.id.outWareHouse_PurchaseEnterBill);
		saleOutButton.setOnClickListener(this);
		purchaseEnterButton.setOnClickListener(this);
		return view;
	}

	@Override
	protected void initFragmentChildView(View view) {

	}

	@Override
	protected void initFragmentData(Bundle savedInstanceState) {

	}

	@Override
	public void onClick(View v) {
		int ID = v.getId();
		switch (ID) {
		case R.id.outWareHouse_PurchaseEnterBill:
			IntentUtils.startActivity(context, PurchaseReturnOrderBillListActivity.class);
			break;
		case R.id.outWareHouse_SaleOutBill:
			IntentUtils.startActivity(context, ShipmentOrderBillListActivity.class);
			break;
		}
	}
}
