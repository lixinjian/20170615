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
import com.ds365.erp.wms.pda.model.sale.SaleOrderBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleOutBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderBillModel;
import com.ds365.erp.wms.pda.service.sale.SaleReturnEnterService;
import com.ds365.erp.wms.pda.service.sale.SaleReturnOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SaleReturnEnterBillAdapter;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SaleReturnOrderBillAdapter;
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
 * 退货入库退货申请单列表
 * @author lxj
 *
 */
public class SaleReturnOrderBillListActivity extends BasePdaPageActivity implements OnClickListener{
	private CommonTitleTab titleTab;
	private SearchField billCodeValue;
	private ImageView billCodeSearchButton, billCodeScanButton;
	private TextView billCodeLabel;
	
	private List<SaleReturnOrderBillModel> orderBillDetails = new ArrayList<SaleReturnOrderBillModel>();
	private List<SaleReturnEnterBillModel> enterBillDetails = new ArrayList<SaleReturnEnterBillModel>();
	private List<String> titleTabList = new ArrayList<String>();
	
	private PullToRefreshListView listView;
	private SaleReturnOrderBillAdapter orderBillAdapter;
	private SaleReturnEnterBillAdapter enterBillAdapter;
	
	private SaleReturnEnterService saleReturnEnterService = new SaleReturnEnterService();
	private SaleReturnOrderService saleReturnOrderService = new SaleReturnOrderService();
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	
	private int select = 0;
	private static final String NOT_RETURN_TAB_CODE="notReturn";
	private static final String RETURN_TAB_CODE="return";
	private static final String ENTER_TAB_CODE="enter";
	private  String[]  tabCodes={NOT_RETURN_TAB_CODE,RETURN_TAB_CODE,ENTER_TAB_CODE};
	
	
	private SaleOrderBillQueryParamsModel saleOrderBillQueryParamsModel = new SaleOrderBillQueryParamsModel();
	private SaleOutBillQueryParamsModel saleOutBillQueryParamsModel = new SaleOutBillQueryParamsModel();

	private ServiceCallBack<QueryResult<SaleReturnOrderBillModel>> saleReturnOrderBillServiceCallBack = new AbstractServiceCallBack<QueryResult<SaleReturnOrderBillModel>>(
			SaleReturnOrderBillListActivity.this) {

		@Override
		public void doSuccess(QueryResult<SaleReturnOrderBillModel> result) {
			if (result != null && result.getRecords().size() > 0) {
				List<SaleReturnOrderBillModel> arrayList = result.getRecords();
				orderBillDetails.addAll(arrayList);
			}else{
				T.showShort(context, R.string.listview_no_more);
			}
			orderBillAdapter.notifyDataSetChanged();
		}
	};
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_order_bill_list;
	}
	
	@Override
	protected void initActivityView() {
		billCodeLabel = (TextView) findViewById(R.id.saleReturnOrderBillList_billCode_label);
		billCodeValue = (SearchField) findViewById(R.id.saleReturnOrderBillList_billCode_value);
		billCodeSearchButton = (ImageView) findViewById(R.id.saleReturnOrderBillList_billCodeSearch_button);
		billCodeScanButton =  (ImageView) findViewById(R.id.saleReturnOrderBillList_billCodeScan_button);
		listView = (PullToRefreshListView) findViewById(R.id.saleReturnOrderBillList_details);
		titleTab = (CommonTitleTab) findViewById(R.id.saleReturnOrderBillList_titleTab);
		titleTabList.add("未退货");
		titleTabList.add("已退货");
		titleTabList.add("入库单列表");
		orderBillAdapter = new SaleReturnOrderBillAdapter(context, orderBillDetails);
		enterBillAdapter = new SaleReturnEnterBillAdapter(context, enterBillDetails); 
		ListViewUtil.setOnPullBothListView(listView);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleReturnOrderBillList_headerview, R.string.sale_return_enter);
	}

	@Override
	protected void setListener() {		
		billCodeScanButton.setOnClickListener(this);
		billCodeSearchButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (tabCodes[select].equals(NOT_RETURN_TAB_CODE) || tabCodes[select].equals(RETURN_TAB_CODE)) {
					SaleReturnOrderBillModel bill=orderBillDetails.get(position-1);
					IntentUtils.startActivityForSeria(context, SaleReturnOrderBillShowActivity.class
							, SER_KEY, bill, null);
				}else if(tabCodes[select].equals(ENTER_TAB_CODE)){
					SaleReturnEnterBillModel bill=enterBillDetails.get(position-1);
					IntentUtils.startActivityForSeria(context, SaleReturnEnterBillShowActivity.class
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
	 * 销售退货订单查询参数
	 */
	private void setSaleReturnOrderBillQueryParams(){
		String billCode = billCodeValue.getValue();
		saleOrderBillQueryParamsModel.setStart(start);
		saleOrderBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		saleOrderBillQueryParamsModel.setBillCode(billCode);
	}
	
	/**
	 * 销售退货入库单查询参数
	 */
	private void setSaleReturnOutBillQueryParams(){
		String billCode = billCodeValue.getValue();
		saleOutBillQueryParamsModel.setStart(start);
		saleOutBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		saleOutBillQueryParamsModel.setBillCode(billCode);
	}
	
	/**
	 * tab切换(未退货、已退货、入库单列表)
	 */
	private void onTabChange(boolean isClearListView){
		if (tabCodes[select].equals(NOT_RETURN_TAB_CODE)) {
			billCodeLabel.setText("退货单号:");
			listView.setAdapter(orderBillAdapter);
			setSaleReturnOrderBillQueryParams();
			orderBillAdapter.setEnterButtonShowFlag(true);
			searchPageSaleReturnOrderBillsForNotReturn(isClearListView);
		}else if (tabCodes[select].equals(RETURN_TAB_CODE)) {
			billCodeLabel.setText("退货单号:");
			listView.setAdapter(orderBillAdapter);
			setSaleReturnOrderBillQueryParams();
			orderBillAdapter.setEnterButtonShowFlag(false);
			searchPageSaleReturnOrderBillsForReturn(isClearListView);
		}else if (tabCodes[select].equals(ENTER_TAB_CODE)) {
			billCodeLabel.setText("入库单号:");
			listView.setAdapter(enterBillAdapter); 
			setSaleReturnOutBillQueryParams();
			searchPageSaleReturnEnterBills(isClearListView);
		}
	}
	
	/**
	 * 获取未退货列表数据
	 */
	private void searchPageSaleReturnOrderBillsForNotReturn(boolean isClearListView) {
		if (isClearListView) {
			orderBillDetails.clear();
		}
		saleReturnOrderService.searchPageSaleReturnOrderBillsForNotReturn(saleOrderBillQueryParamsModel,
				saleReturnOrderBillServiceCallBack);
	}
	
	/**
	 * 获取已退货列表数据
	 */
	private void searchPageSaleReturnOrderBillsForReturn(boolean isClearListView) {
		if (isClearListView) {
			orderBillDetails.clear();
		}
		saleReturnOrderService.searchPageSaleReturnOrderBillsForReturn(saleOrderBillQueryParamsModel,
				saleReturnOrderBillServiceCallBack);
	}
	
	/**
	 * 获取入库单列表数据
	 */
	private void searchPageSaleReturnEnterBills(boolean isClearListView) {
		if (isClearListView) {
			enterBillDetails.clear();
		}
		saleReturnEnterService.searchPageSaleReturnEnterBillsByParams(saleOutBillQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<SaleReturnEnterBillModel>>(SaleReturnOrderBillListActivity.this) {

					@Override
					public void doSuccess(QueryResult<SaleReturnEnterBillModel> result) {
						if (result != null && result.getRecords().size() > 0) {
							enterBillDetails.addAll(result.getRecords());
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						enterBillAdapter.notifyDataSetChanged();
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.saleReturnOrderBillList_billCodeSearch_button:
			setFirstPage();
			onTabChange(true);
			break;
		case R.id.saleReturnOrderBillList_billCodeScan_button:
			IntentUtils.startActivityForResult(SaleReturnOrderBillListActivity.this
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