package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.CommonTitleTab;
import com.ds365.commons.widget.CommonTitleTab.SelectChangeListener;
import com.ds365.commons.widget.SearchField.EditTextClearListener;
import com.ds365.commons.widget.SearchField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutBillModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseEnterService;
import com.ds365.erp.wms.pda.service.purchase.PurchaseOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter.PurchaseReturnOrderBillAdapter;
import com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter.PurchaseReturnOutBillAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
/**
 * 采购退货 - 退货申请单
 * @author lxj
 *
 */
public class PurchaseReturnOrderBillListActivity extends BasePdaPageActivity implements OnClickListener{
	private CommonTitleTab titleTab;
	private TextView billCodeLabel;
	private PullToRefreshListView listView;
	private ImageView billCodeSearchButton, billCodeScanButton;	//搜索按钮
	private SearchField billCodeValue;
	
	private PurchaseReturnOrderBillAdapter orderBillAdapter;
	private PurchaseReturnOutBillAdapter outBillAdapter;
	
	private List<PurchaseReturnOrderBillModel> orderBillDetails = new ArrayList<PurchaseReturnOrderBillModel>(); 
	private List<PurchaseReturnOutBillModel> outBillDetails = new ArrayList<PurchaseReturnOutBillModel>(); 
	private List<String> titleTabList = new ArrayList<String>();
	
	private PurchaseEnterService purchaseEnterService = new PurchaseEnterService();
	private PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
	
	public static final  String SER_KEY=PdaConstants.nextSerKey();

	private int select = 0;
	private static final String NOT_RETURN_TAB_CODE="notReturn";
	private static final String NOT_RETURN_BY_SUPPLIER_TAB_CODE="notReturnBySupplier";
	private static final String RETURN_TAB_CODE="return";
	private static final String OUTTER_TAB_CODE="outter";
	private  String[]  tabCodes={NOT_RETURN_TAB_CODE,NOT_RETURN_BY_SUPPLIER_TAB_CODE,RETURN_TAB_CODE,OUTTER_TAB_CODE};
	
	private PurchaseOrderBillQueryParamsModel purchaseOrderBillQueryParamsModel = new PurchaseOrderBillQueryParamsModel();
	private PurchaseEnterBillQueryParamsModel purchaseEnterBillQueryParamsModel = new PurchaseEnterBillQueryParamsModel();
	
	private ServiceCallBack<QueryResult<PurchaseReturnOrderBillModel>> PurchaseReturnOrderBillServiceCallBack = new AbstractServiceCallBack<QueryResult<PurchaseReturnOrderBillModel>>(
			PurchaseReturnOrderBillListActivity.this) {

		@Override
		public void doSuccess(QueryResult<PurchaseReturnOrderBillModel> result) {
			if (result != null && result.getRecords().size() > 0) {
				orderBillDetails.addAll(result.getRecords());
			}else{
				T.showShort(context, R.string.listview_no_more);
			}
			orderBillAdapter.notifyDataSetChanged();
		}

	};
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_order_bill_list;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseReturnOrderBillList_headerview, R.string.purchase_return_out);
	}

	@Override
	protected void setListener() {
		billCodeSearchButton.setOnClickListener(this);
		billCodeScanButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				if (tabCodes[select].equals(NOT_RETURN_TAB_CODE) || tabCodes[select].equals(RETURN_TAB_CODE)
						|| tabCodes[select].equals(NOT_RETURN_BY_SUPPLIER_TAB_CODE)) {
					PurchaseReturnOrderBillModel bill = orderBillDetails.get(position-1);
					IntentUtils.startActivityForSeria(context, PurchaseReturnOrderBillShowActivity.class
							, SER_KEY, bill, null);
					
				}else if(tabCodes[select].equals(OUTTER_TAB_CODE)){
					PurchaseReturnOutBillModel bill = outBillDetails.get(position-1);
					IntentUtils.startActivityForSeria(context, PurchaseReturnOutBillShowActivity.class
							, SER_KEY, bill, null);
				}
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				onTabChange(true);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				onTabChange(false);
			}
		}));
		
		titleTab.setSelectChangeListener(new SelectChangeListener() {
			
			@Override
			public void onItemSelect(int selectValue, RadioButton selectRadioButton) {
				select = selectValue;
				setFirstPage();
				onTabChange(true);
			}
		});
		titleTab.setData(titleTabList);
		
		billCodeValue.setEditTextClearListener(new EditTextClearListener() {
			
			@Override
			public void onEditTextClear() {
				setFirstPage();
				onTabChange(true);
			}
		});
	}
	
	/**
	 * 设置采购退货单的参数
	 */
	private void setPurchaseReturnOrderBillQueryParams(){
		String billCode = billCodeValue.getValue();
		purchaseOrderBillQueryParamsModel.setStart(start);
		purchaseOrderBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		purchaseOrderBillQueryParamsModel.setBillCode(billCode);
	}
	
	/**
	 * 设置采购退货出库单的参数
	 */
	private void setPurchaseReturnOutBillQueryParams(){
		String billCode = billCodeValue.getValue();
		purchaseEnterBillQueryParamsModel.setStart(start);
		purchaseEnterBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		purchaseEnterBillQueryParamsModel.setBillCode(billCode);
	}
	
	/**
	 * tab切换(未退货、已退货、出库单列表)
	 */
	private void onTabChange(boolean isClearListView){
		if (tabCodes[select].equals(NOT_RETURN_TAB_CODE)) {
			billCodeLabel.setText("退货单号:");
			listView.setAdapter(orderBillAdapter);
			setPurchaseReturnOrderBillQueryParams();
			orderBillAdapter.setReturnOutterButtonShowFlag(true);
			searchPagePurchaseReturnOrderBillForNotReturn(isClearListView);
		}else if (tabCodes[select].equals(NOT_RETURN_BY_SUPPLIER_TAB_CODE)) {
			billCodeLabel.setText("退货单号:");
			listView.setAdapter(orderBillAdapter);
			setPurchaseReturnOrderBillQueryParams();
			orderBillAdapter.setReturnOutterButtonShowFlag(true);
			searchPagePurchaseReturnOrderBillBySupplierForNotReturn(isClearListView);
		}else if (tabCodes[select].equals(RETURN_TAB_CODE)) {
			billCodeLabel.setText("退货单号:");
			listView.setAdapter(orderBillAdapter);
			setPurchaseReturnOrderBillQueryParams();
			orderBillAdapter.setReturnOutterButtonShowFlag(false);
			searchPagePurchaseReturnOrderBillForReturn(isClearListView);
		}else if (tabCodes[select].equals(OUTTER_TAB_CODE)) {
			billCodeLabel.setText("出库单号:");
			listView.setAdapter(outBillAdapter);
			setPurchaseReturnOutBillQueryParams();
			searchPagePurchaseReturnOutBill(isClearListView);
		}
	}
	
	@Override
	protected void initActivityView() {
		billCodeLabel = (TextView) findViewById(R.id.purchaseReturnOrderBillList_billCode_label);
		billCodeValue = (SearchField) findViewById(R.id.purchaseReturnOrderBillList_billCode_value);
		billCodeSearchButton = (ImageView) findViewById(R.id.purchaseReturnOrderBillList_billCodeSearch_button);
		billCodeScanButton = (ImageView) findViewById(R.id.purchaseReturnOrderBillList_billCodeScan_button);
		listView = (PullToRefreshListView) findViewById(R.id.purchaseReturnOrderBillList_details);
		titleTab = (CommonTitleTab) findViewById(R.id.purchaseReturnOrderBillList_titleTab);
		titleTabList.add("未退货");
		titleTabList.add("未退货(供应商)");
		titleTabList.add("已退货");
		titleTabList.add("出库单列表");
		
		outBillAdapter = new PurchaseReturnOutBillAdapter(context, outBillDetails); 
		orderBillAdapter = new PurchaseReturnOrderBillAdapter(context, orderBillDetails);
		ListViewUtil.setOnPullBothListView(listView);
	}
	
	/**
	 * 获取未退货列表数据
	 */
	private void searchPagePurchaseReturnOrderBillForNotReturn(boolean isClearListView) {
		if (isClearListView) {
			orderBillDetails.clear();
		}
		purchaseOrderService.searchPagePurchaseReturnOrderBillForNotReturn(purchaseOrderBillQueryParamsModel,
				PurchaseReturnOrderBillServiceCallBack);
	}
	
	/**
	 * 获取未退货(供应商)列表数据
	 */
	private void searchPagePurchaseReturnOrderBillBySupplierForNotReturn(boolean isClearListView) {
		if (isClearListView) {
			orderBillDetails.clear();
		}
		purchaseOrderService.searchPagePurchaseReturnOrderBillBySupplierForNotReturn(purchaseOrderBillQueryParamsModel,
				PurchaseReturnOrderBillServiceCallBack);
	}
	
	/**
	 * 获取已退货列表数据
	 */
	private void searchPagePurchaseReturnOrderBillForReturn(boolean isClearListView) {
		if (isClearListView) {
			orderBillDetails.clear();
		}
		purchaseOrderService.searchPagePurchaseReturnOrderBillForReturn(purchaseOrderBillQueryParamsModel,
				PurchaseReturnOrderBillServiceCallBack);
	}
	
	/**
	 * 获取出库单列表数据
	 */
	private void searchPagePurchaseReturnOutBill(boolean isClearListView) {
		if (isClearListView) {
			outBillDetails.clear();
		}
		purchaseEnterService.searchPagePurchaseReturnOutBillByParams(purchaseEnterBillQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<PurchaseReturnOutBillModel>>(PurchaseReturnOrderBillListActivity.this) {

					@Override
					public void doSuccess(QueryResult<PurchaseReturnOutBillModel> result) {
						if (result != null && result.getRecords().size() > 0) {
							outBillDetails.addAll(result.getRecords());
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						// 数据刷新后通知适配器更新UI
						outBillAdapter.notifyDataSetInvalidated();
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.purchaseReturnOrderBillList_billCodeSearch_button:
			setFirstPage();
			onTabChange(true);
			break;
		case R.id.purchaseReturnOrderBillList_billCodeScan_button:
			IntentUtils.startActivityForResult(PurchaseReturnOrderBillListActivity.this
					, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
			break;
		}
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		setFirstPage();
		onTabChange(true);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (resultCode == CodeScanActivity.RESULT_CODE) {
				billCodeValue.setValue(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
}
