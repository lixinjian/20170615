package com.ds365.erp.wms.pda.view.query;

import com.ds365.commons.utils.IntentUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.query.activity.PickBillListActivity;
import com.ds365.erp.wms.pda.view.query.activity.PurchaseEnterBillListActivity;
import com.ds365.erp.wms.pda.view.query.activity.SaleOutBillListActivity;
import com.ds365.erp.wms.pda.view.query.activity.ShipmentBillListActivity;
import com.ds365.erp.wms.pda.view.query.activity.SkuShelfBatchStockListActivity;
import com.ds365.erp.wms.pda.view.query.activity.SkuStockAccountBookListActivity;
import com.ds365.erp.wms.pda.view.query.activity.SkuStoreStockListActivity;
import com.ds365.erp.wms.pda.view.query.activity.SkuWarehouseStockListActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class QueryFragment extends BaseFragment implements OnClickListener{

	private Context context;
	private RelativeLayout shelfBatchStockQueryButton;
	private RelativeLayout storeStockQueryButton;
	private RelativeLayout purchaseEnterBillQueryButton;
	private RelativeLayout saleOutBillQueryButton;
	private RelativeLayout skuStockAccountBookButton;
	private RelativeLayout pickBillQueryButton;
	private RelativeLayout shipmentBillQueryButton;
	private RelativeLayout warehouseStockQueryButton;
	
	@Override
	protected void initFragmentRequestNet() {

	}

	@Override
	protected View initFragmentView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.query_main_fragment, null);
		context = getActivity();
		shelfBatchStockQueryButton = (RelativeLayout) view.findViewById(R.id.shelfBatchStockQuery_button);
		storeStockQueryButton = (RelativeLayout) view.findViewById(R.id.storeStockQuery_button);
		purchaseEnterBillQueryButton = (RelativeLayout) view.findViewById(R.id.purchaseEnterBillQuery_button);
		saleOutBillQueryButton = (RelativeLayout) view.findViewById(R.id.saleOutBillQuery_button);
		skuStockAccountBookButton = (RelativeLayout) view.findViewById(R.id.skuStockAccountBook_button);
		pickBillQueryButton = (RelativeLayout) view.findViewById(R.id.pickBillQuery_button);
		shipmentBillQueryButton = (RelativeLayout) view.findViewById(R.id.shipmentBillQuery_button);
		warehouseStockQueryButton = (RelativeLayout) view.findViewById(R.id.warehouseStockQuery_button);
		shelfBatchStockQueryButton.setOnClickListener(this);
		storeStockQueryButton.setOnClickListener(this);
		purchaseEnterBillQueryButton.setOnClickListener(this);
		saleOutBillQueryButton.setOnClickListener(this);
		skuStockAccountBookButton.setOnClickListener(this);
		pickBillQueryButton.setOnClickListener(this);
		shipmentBillQueryButton.setOnClickListener(this);
		warehouseStockQueryButton.setOnClickListener(this);
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
		switch (v.getId()) {
		case R.id.shelfBatchStockQuery_button:
			 IntentUtils.startActivity(context, SkuShelfBatchStockListActivity.class);
			break;
		case R.id.storeStockQuery_button:
			IntentUtils.startActivity(context, SkuStoreStockListActivity.class);
			break;
		case R.id.purchaseEnterBillQuery_button:
			IntentUtils.startActivity(context, PurchaseEnterBillListActivity.class);
			break;
		case R.id.saleOutBillQuery_button:
			IntentUtils.startActivity(context, SaleOutBillListActivity.class);
			break;
		case R.id.skuStockAccountBook_button:
			IntentUtils.startActivity(context, SkuStockAccountBookListActivity.class);
			break;
		case R.id.pickBillQuery_button:
			IntentUtils.startActivity(context, PickBillListActivity.class);
			break;
		case R.id.shipmentBillQuery_button:
			IntentUtils.startActivity(context, ShipmentBillListActivity.class);
			break;
		case R.id.warehouseStockQuery_button:
			IntentUtils.startActivity(context, SkuWarehouseStockListActivity.class);
			break;
		}
	}
}
