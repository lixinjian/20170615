package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.CommonTitleTab;
import com.ds365.commons.widget.CommonTitleTab.SelectChangeListener;
import com.ds365.commons.widget.SearchField;
import com.ds365.commons.widget.SearchField.EditTextClearListener;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderBillModel;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderBillQueryParamsModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseEnterService;
import com.ds365.erp.wms.pda.service.purchase.PurchaseOrderService;
import com.ds365.erp.wms.pda.service.supplier.SupplierOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.PurchaseEnterBillAdapter;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.PurchaseOrderBillAdapter;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SupplierOrderBillAdapter;
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
 * 收货入库 - 采购订单
 */
public class PurchaseOrderBillListActivity extends BasePdaPageActivity implements OnClickListener{
	private CommonTitleTab titleTab;
	private PullToRefreshListView listView;
	private PurchaseOrderBillAdapter orderBillAdapter;
	private SupplierOrderBillAdapter supplierOrderBillAdapter;
	private PurchaseEnterBillAdapter enterBillAdapter;
	private ImageView billCodeScanButton, billCodeSearchButton;
	private SearchField billCodeValue;
	private TextView billCodeLabel;
	
	private List<PurchaseOrderBillModel> purchaseOrderBillList = new ArrayList<PurchaseOrderBillModel>();
	private List<PurchaseEnterBillModel> purchaseEnterBillList = new ArrayList<PurchaseEnterBillModel>();
	private List<SupplierOrderBillModel> supplierOrderBillList = new ArrayList<SupplierOrderBillModel>();
	
	private PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
	private PurchaseEnterService purchaseEnterService = new PurchaseEnterService();
	private SupplierOrderService supplierOrderService = new SupplierOrderService();
	
	/**
	 * 存放titleTab的名字
	 */
	private List<String> titleTabList = new ArrayList<String>();

	public static final  String SER_KEY=PdaConstants.nextSerKey();
	
	private PurchaseOrderBillQueryParamsModel purchaseOrderBillQueryParamsModel = new PurchaseOrderBillQueryParamsModel();
	private PurchaseEnterBillQueryParamsModel purchaseEnterBillQueryParamsModel = new PurchaseEnterBillQueryParamsModel();
	private SupplierOrderBillQueryParamsModel supplierOrderBillQueryParamsModel = new SupplierOrderBillQueryParamsModel();
	
	private int select = 0;
	private static final String NOT_RECEIVE_TAB_CODE="notReceive";
	private static final String NOT_RECEIVE_BY_SUPPLIER_TAB_CODE="notReceiveBySupplier";
	private static final String RECEIVED_TAB_CODE="received";
	private static final String ENTER_TAB_CODE="enter";
	private  String[]  tabCodes={NOT_RECEIVE_TAB_CODE,NOT_RECEIVE_BY_SUPPLIER_TAB_CODE,RECEIVED_TAB_CODE,ENTER_TAB_CODE};
	
	private ServiceCallBack<QueryResult<PurchaseOrderBillModel>> purchaseOrderBillServiceCallBack = new AbstractServiceCallBack<QueryResult<PurchaseOrderBillModel>>(
			PurchaseOrderBillListActivity.this) {

		@Override
		public void doSuccess(QueryResult<PurchaseOrderBillModel> result) {
//			/**
//			 * 如果数据请求成功后,activity(页面)已经被销毁,就不再往界面(已销毁的界面)上填充数据,防止内存泄漏导致程序异常退出.
//			 * 暂不考虑
//			 
//			if (context == null || ((Activity) context).isFinishing()) {
//			    return;
//			}
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//				//如果版本高于4.2(API17)还要调用isDestroyed()方法.
//			    if (((Activity) context).isDestroyed()) {
//			        return;
//			    }
//			}
			
			if (result != null && result.getRecords().size() > 0) {
				purchaseOrderBillList.addAll(result.getRecords());
				// 数据刷新后通知适配器更新UI
			}else{
				//提示没有更多数据
				T.showShort(context, R.string.listview_no_more);
			}
			//不管有没有请求到数据,都要刷新一下适配器
			orderBillAdapter.notifyDataSetInvalidated();
		}
	};
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_purchase_order_bill_list;
	}
	
	@Override
	protected void initActivityView() {
		billCodeScanButton = (ImageView) findViewById(R.id.purchaseOrderBillList_billCodeScan_button);
		billCodeLabel = (TextView) findViewById(R.id.purchaseOrderBillList_billCode_label);
		billCodeValue = (SearchField) findViewById(R.id.purchaseOrderBillList_billCode_value);
		billCodeSearchButton = (ImageView) findViewById(R.id.purchaseOrderBillList_billCodeSearch_button);
		listView = (PullToRefreshListView) findViewById(R.id.purchaseOrderBillList_listview);
		titleTab = (CommonTitleTab) findViewById(R.id.purchaseOrderBillList_titleTab);
		titleTabList.add("未收货");
		titleTabList.add("未收货(供应商)");
		titleTabList.add("已收货");
		titleTabList.add("入库单列表");
		
		orderBillAdapter = new PurchaseOrderBillAdapter(context, purchaseOrderBillList);
		enterBillAdapter = new PurchaseEnterBillAdapter(context, purchaseEnterBillList); 
		supplierOrderBillAdapter = new SupplierOrderBillAdapter(context, supplierOrderBillList);
		ListViewUtil.setOnPullBothListView(listView);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseOrderBillList_headerview, R.string.purchase_enter_warehouse);
	}

	@Override
	protected void setListener() {
		billCodeScanButton.setOnClickListener(this);
		billCodeSearchButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int selectPosition = position-1;
				if (tabCodes[select].equals(NOT_RECEIVE_TAB_CODE) || tabCodes[select].equals(RECEIVED_TAB_CODE)) {
					PurchaseOrderBillModel purchaseOrderBill=purchaseOrderBillList.get(selectPosition);
					IntentUtils.startActivityForSeria(context, PurchaseOrderBillShowActivity.class
							, SER_KEY, purchaseOrderBill, null);
				}else if (tabCodes[select].equals(ENTER_TAB_CODE)){
					PurchaseEnterBillModel purchaseEnterBill=purchaseEnterBillList.get(selectPosition);
					IntentUtils.startActivityForSeria(context, PurchaseEnterBillShowActivity.class
							, SER_KEY, purchaseEnterBill, null);
				}else if(tabCodes[select].equals(NOT_RECEIVE_BY_SUPPLIER_TAB_CODE)){
					SupplierOrderBillModel supplierOrderBill=supplierOrderBillList.get(selectPosition);
					IntentUtils.startActivityForSeria(context, SupplierOrderBillShowActivity.class
							, SER_KEY, supplierOrderBill, null);
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
	 * 设置采购订单的查询参数
	 */
	private void setPurchaseOrderBillQueryParams(){
		String billCode = billCodeValue.getValue();
		purchaseOrderBillQueryParamsModel.setStart(start);
		purchaseOrderBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		purchaseOrderBillQueryParamsModel.setBillCode(billCode);
	}
	
	private void setSupplierOrderBillQueryParams(){
		String billCode = billCodeValue.getValue();
		supplierOrderBillQueryParamsModel.setStart(start);
		supplierOrderBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		supplierOrderBillQueryParamsModel.setBillCode(billCode);
	}
	
	/**
	 * 设置入库单的查询参数
	 */
	private void setPurchaseEnterBillQueryParams(){
		String billCode = billCodeValue.getValue();
		purchaseEnterBillQueryParamsModel.setStart(start);
		purchaseEnterBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		purchaseEnterBillQueryParamsModel.setBillCode(billCode);
	}
	
	/**
	 * tab切换(未收货、已收货、入库单列表)
	 * @param isClearListView 是否清空列表数据  true为清空(刷新页面时需清空)，false为不清空(分页加载更多时不清空)
	 */
	private void onTabChange(boolean isClearListView){
		if (tabCodes[select].equals(NOT_RECEIVE_TAB_CODE)) {
			billCodeLabel.setText("订单号:");
			listView.setAdapter(orderBillAdapter);
			setPurchaseOrderBillQueryParams();
			searchPagePurchaseOrderBillForNotReceive(isClearListView);
			orderBillAdapter.setReceivedButtonShowFlag(true);
		}else if (tabCodes[select].equals(NOT_RECEIVE_BY_SUPPLIER_TAB_CODE)) {
			billCodeLabel.setText("发货单号:");
			listView.setAdapter(supplierOrderBillAdapter);
			setSupplierOrderBillQueryParams();
			searchPageSupplierOrderBill(isClearListView);
			supplierOrderBillAdapter.setReceivedButtonShowFlag(true);
		}else if (tabCodes[select].equals(RECEIVED_TAB_CODE)) {
			billCodeLabel.setText("订单号:");
			listView.setAdapter(orderBillAdapter);
			setPurchaseOrderBillQueryParams();
			searchPagePurchaseOrderBillForReceived(isClearListView);
			orderBillAdapter.setReceivedButtonShowFlag(false);
		}else if (tabCodes[select].equals(ENTER_TAB_CODE)) {
			billCodeLabel.setText("入库单号:");
			listView.setAdapter(enterBillAdapter);
			setPurchaseEnterBillQueryParams();
			searchPagePurchaseEnterBillByParams(isClearListView);
		}
	}
	
	/**
	 * 获取未收货列表数据
	 */
	private void searchPagePurchaseOrderBillForNotReceive(boolean isClearListView) {
		if (isClearListView) {
			purchaseOrderBillList.clear();
		}
		purchaseOrderService.searchPagePurchaseOrderBillForNotReceive(purchaseOrderBillQueryParamsModel, purchaseOrderBillServiceCallBack) ;
	}
	
	/**
	 * 获取未收货(供应商)列表数据
	 */
	private void searchPageSupplierOrderBill(boolean isClearListView) {
		if (isClearListView) {
			supplierOrderBillList.clear();
		}
		supplierOrderService.searchPageSupplierOrderForDeliverOut(supplierOrderBillQueryParamsModel, new AbstractServiceCallBack<QueryResult<SupplierOrderBillModel>>(
				PurchaseOrderBillListActivity.this) {

			@Override
			public void doSuccess(QueryResult<SupplierOrderBillModel> result) {
				
				if (result != null && result.getRecords().size() > 0) {
					supplierOrderBillList.addAll(result.getRecords());
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
				supplierOrderBillAdapter.notifyDataSetChanged();
			}
		}) ;
	}
	
	/**
	 * 获取已收货列表数据
	 */
	private void searchPagePurchaseOrderBillForReceived(boolean isClearListView) {
		if (isClearListView) {
			purchaseOrderBillList.clear();
		}
		purchaseOrderService.searchPagePurchaseOrderBillForReceived(purchaseOrderBillQueryParamsModel, purchaseOrderBillServiceCallBack) ;
	}
	
	/**
	 * 获取入库单列表数据
	 */
	private void searchPagePurchaseEnterBillByParams(boolean isClearListView) {
		if (isClearListView) {
			purchaseEnterBillList.clear();
		}
		purchaseEnterService.searchPagePurchaseEnterBillByParams(purchaseEnterBillQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<PurchaseEnterBillModel>>(PurchaseOrderBillListActivity.this) {

					@Override
					public void doSuccess(QueryResult<PurchaseEnterBillModel> result) {
						if (result != null && result.getRecords().size() > 0) {
							purchaseEnterBillList.addAll(result.getRecords());
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						// 数据刷新后通知适配器更新UI
						enterBillAdapter.notifyDataSetInvalidated();
					}
				});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.purchaseOrderBillList_billCodeSearch_button:
			setFirstPage();
			onTabChange(true);
			break;
		case R.id.purchaseOrderBillList_billCodeScan_button:
			//扫描订单号
			IntentUtils.startActivityForResult(PurchaseOrderBillListActivity.this
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
			//扫描订单号返回的结果
			if (resultCode == CodeScanActivity.RESULT_CODE) {
				billCodeValue.setValue(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
}
