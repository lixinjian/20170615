package com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity;

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
import com.ds365.erp.wms.pda.model.shipment.ShipmentBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentOrderBillModel;
import com.ds365.erp.wms.pda.service.shipment.ShipmentService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.adapter.ShipmentOrderBillAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
/**
 * 销售出库首页 - 装运单号列表
 * @author lxj
 *
 */
public class ShipmentOrderBillListActivity extends BasePdaPageActivity implements OnClickListener{
	private CommonTitleTab titleTab;
	private PullToRefreshListView listView;
	private List<ShipmentOrderBillModel> details = new ArrayList<ShipmentOrderBillModel>();
	private ShipmentOrderBillAdapter adapter;
	private ImageView billCodeSearchButton, billCodeScanButton;	//搜索按钮
	private SearchField billCodeValue;
	
	private List<String> titleTabList = new ArrayList<String>();
	
	private ShipmentService shipmentService = new ShipmentService();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private int select = 0;
	private static final String NOT_OUT_TAB_CODE="notOut";
	private static final String OUT_TAB_CODE="out";
	private  String[]  tabCodes={NOT_OUT_TAB_CODE,OUT_TAB_CODE};
	
	private ShipmentBillQueryParamsModel shipmentBillQueryParamsModel = new ShipmentBillQueryParamsModel();

	private ServiceCallBack<QueryResult<ShipmentOrderBillModel>> shipmentOrderBillServiceCallBack = new AbstractServiceCallBack<QueryResult<ShipmentOrderBillModel>>(
			ShipmentOrderBillListActivity.this) {

		@Override
		public void doSuccess(QueryResult<ShipmentOrderBillModel> result) {
			if (result != null && result.getRecords().size() > 0) {
				List<ShipmentOrderBillModel> arrayList = result.getRecords();
				details.addAll(arrayList);
			}else{
				T.showShort(context, R.string.listview_no_more);
			}
			// 数据刷新后通知适配器更新UI
			adapter.notifyDataSetChanged();
		}
	};
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_shipment_order_bill_list;
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		setFirstPage();
		onTabChange(true);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.shipmentOrderBillList_headerview, R.string.title_deliver_goods);
	}

	@Override
	protected void setListener() {
		billCodeSearchButton.setOnClickListener(this);
		billCodeScanButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityForSeria(context, 
						ShipmentOrderBillShowActivity.class, SER_KEY, details.get(position-1), null);
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
	 * 设置装运单查询参数
	 */
	private void setParams(){
		String billCode = billCodeValue.getValue();
		shipmentBillQueryParamsModel.setStart(start);
		shipmentBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		shipmentBillQueryParamsModel.setBillCode(billCode);
	}
	
	/**
	 * tab切换(未出库、已出库)
	 */
	private void onTabChange(boolean isClearListView){
		setParams();
		if (tabCodes[select].equals(NOT_OUT_TAB_CODE)) {
			adapter.setOutterButtonShowFlag(true);
			searchPageShipmentBillForNotOut(isClearListView);
		}else if (tabCodes[select].equals(OUT_TAB_CODE)) {
			adapter.setOutterButtonShowFlag(false);
			searchPageShipmentBillForOut(isClearListView);
		}
	}
	
	@Override
	protected void initActivityView() {
		billCodeValue = (SearchField) findViewById(R.id.shipmentOrderBillList_billCode_value);
		billCodeSearchButton = (ImageView) findViewById(R.id.shipmentOrderBillList_billCodeSearch_button);
		billCodeScanButton = (ImageView) findViewById(R.id.shipmentOrderBillList_billCodeScan_button);
		listView = (PullToRefreshListView) findViewById(R.id.shipmentOrderBillList_details);
		titleTab = (CommonTitleTab) findViewById(R.id.shipmentOrderBillList_titleTab);
		titleTabList.add("未出库");
		titleTabList.add("已出库");
		adapter = new ShipmentOrderBillAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
	}

	private void searchPageShipmentBillForNotOut(boolean isClearListView) {
		if (isClearListView) {
			details.clear();
		}
		shipmentService.searchPageShipmentBillForNotOut(shipmentBillQueryParamsModel, shipmentOrderBillServiceCallBack);
	}
	
	private void searchPageShipmentBillForOut(boolean isClearListView) {
		if (isClearListView) {
			details.clear();
		}
		shipmentService.searchPageShipmentBillForOut(shipmentBillQueryParamsModel, shipmentOrderBillServiceCallBack);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shipmentOrderBillList_billCodeSearch_button:
			setFirstPage();
			onTabChange(true);
			break;
		case R.id.shipmentOrderBillList_billCodeScan_button:
			IntentUtils.startActivityForResult(ShipmentOrderBillListActivity.this
					, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
			break;
		}
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
