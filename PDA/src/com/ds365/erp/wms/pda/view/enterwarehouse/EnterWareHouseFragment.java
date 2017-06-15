package com.ds365.erp.wms.pda.view.enterwarehouse;

import com.ds365.commons.utils.IntentUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.enterwarehouse.activity.PurchaseOrderBillListActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.activity.SaleReturnOrderBillListActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
/**
 * 这里是"入库业务"展示的Fragment
 */

@SuppressLint({ "InflateParams", "NewApi" })
public class EnterWareHouseFragment extends BaseFragment implements OnClickListener{
	private RelativeLayout purchaseEnterButton;
	private RelativeLayout saleReturnButton;
	private RelativeLayout allotButton;
	// 上下文
	private Context context;
	@Override
	protected void initFragmentRequestNet() {
	}
 
	@Override
	protected View initFragmentView(LayoutInflater inflater) {
		view=inflater.inflate(R.layout.enterwarehouse_main_fragment, null);
		context = getActivity();
		purchaseEnterButton = (RelativeLayout) view.findViewById(R.id.fragmentEnterWarehouseMain_purchaseEnterBill_button);
		saleReturnButton = (RelativeLayout) view.findViewById(R.id.fragmentEnterWarehouseMain_saleReturnBill_button);
		allotButton = (RelativeLayout) view.findViewById(R.id.fragmentEnterWarehouseMain_allot_button);
		purchaseEnterButton.setOnClickListener(this);
		saleReturnButton.setOnClickListener(this);
		allotButton.setOnClickListener(this);
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
		case R.id.fragmentEnterWarehouseMain_purchaseEnterBill_button:
			IntentUtils.startActivity(context, PurchaseOrderBillListActivity.class);
			break;
		case R.id.fragmentEnterWarehouseMain_saleReturnBill_button:
			IntentUtils.startActivity(context, SaleReturnOrderBillListActivity.class);
			break;
		case R.id.fragmentEnterWarehouseMain_allot_button:
			
			break;
		}
	}
}
