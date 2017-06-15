package com.ds365.erp.wms.pda.view.query.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateEditField;
import com.ds365.commons.widget.DropDownListView;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.commons.VehicleModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentBillQueryParamsModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentOrderBillModel;
import com.ds365.erp.wms.pda.service.shipment.ShipmentService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.EmployeeSelectorActivity;
import com.ds365.erp.wms.pda.view.common.activity.VehicleSelectorActivity;
import com.ds365.erp.wms.pda.view.query.adapter.ShipmentBillAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 装运单列表
 */
public class ShipmentBillListActivity extends BasePdaPageActivity implements OnClickListener{
	private PullToRefreshListView listView;
	private List<ShipmentOrderBillModel> details = new ArrayList<ShipmentOrderBillModel>();
	private ShipmentBillAdapter adapter;
	private ImageView billCodeScanButton;	//搜索按钮
	private TextView billCodeSearchButton;
	private EditText billCodeValue;
	private DateEditField startTimeValue,endTimeValue;
	private DropDownListView shipmentBillStateValue;
	private ImageView driverNameSearchButton,deliverUserNamesSearchButton,plateNoSearchButton;
	private EditText driverNameValue,deliverUserNamesValue,plateNoValue;
	private static final int requestCodeForDriver = Integer.parseInt(PdaConstants.nextRequestCode());
	private static final int requestCodeForDeliverUserNames = Integer.parseInt(PdaConstants.nextRequestCode());
	
	private ShipmentService shipmentService = new ShipmentService();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private ShipmentBillQueryParamsModel shipmentBillQueryParamsModel = new ShipmentBillQueryParamsModel();
	private VehicleModel vehicle;
	private EmployeeModel deliverUsers;
	private EmployeeModel driver;

	@Override
	protected int getContentViewId() {
		return R.layout.query_shipment_bill_list;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.shipmentBillList_headerview, R.string.shipment_bill_list);
	}

	@Override
	protected void setListener() {
		billCodeSearchButton.setOnClickListener(this);
		billCodeScanButton.setOnClickListener(this);
		driverNameSearchButton.setOnClickListener(this);
		deliverUserNamesSearchButton.setOnClickListener(this);
		plateNoSearchButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityForSeria(context, 
						ShipmentBillShowActivity.class, SER_KEY, details.get(position-1), null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setParams();
				searchPageShipmentBill(true);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setParams();
				searchPageShipmentBill(false);
			}
		}));
	}
	
	/**
	 * 设置装运单查询参数
	 */
	private void setParams(){
		String billCode = billCodeValue.getText().toString().trim();
		Date startTime = startTimeValue.getValue();
		Date endTime = endTimeValue.getValue();
		String plateNo = plateNoValue.getText().toString().trim();
		String driverName = driverNameValue.getText().toString().trim();
		String deliverUserNames = deliverUserNamesValue.getText().toString().trim();
		shipmentBillQueryParamsModel.setMakeTimeBegin(startTime);
		shipmentBillQueryParamsModel.setMakeTimeEnd(endTime);
		shipmentBillQueryParamsModel.setStart(start);
		shipmentBillQueryParamsModel.setLimit(PdaConstants.LIMIT);
		
		if("".equals(billCode)){
			shipmentBillQueryParamsModel.setFuzzy(null);
		}else{
			shipmentBillQueryParamsModel.setFuzzy(billCode);
		}
		
		//设置司机id
		if("".equals(driverName)){
			shipmentBillQueryParamsModel.setDriverId(null);
		}else if(driver != null){
			shipmentBillQueryParamsModel.setDriverId(driver.getId());
		}
		
		if("".equals(deliverUserNames)){
			shipmentBillQueryParamsModel.setDeliverUserIds(null);
		}else if(deliverUsers != null){
			Long[] deliverUserIds= new Long[]{deliverUsers.getId()};
			shipmentBillQueryParamsModel.setDeliverUserIds(deliverUserIds);
		}
		
		//设置车辆id
		if("".equals(plateNo)){
			shipmentBillQueryParamsModel.setVehicleId(null);
		}else if(vehicle != null){
			shipmentBillQueryParamsModel.setVehicleId(vehicle.getId());
		}
		
		//设置装运单类型
		if (null != shipmentBillStateValue.getValue()) {
			shipmentBillQueryParamsModel.setBillStateId(Integer.valueOf(shipmentBillStateValue.getValue().toString()));
		}else{
			shipmentBillQueryParamsModel.setBillStateId(null);
		}
		
	}
	
	@Override
	protected void initActivityView() {
		driverNameValue = (EditText) findViewById(R.id.shipmentBillList_driverName_value);
		deliverUserNamesValue = (EditText) findViewById(R.id.shipmentBillList_deliverUserNames_value);
		plateNoValue = (EditText) findViewById(R.id.shipmentBillList_plateNo_value);
		startTimeValue = (DateEditField) findViewById(R.id.shipmentBillList_startTime_value);
		endTimeValue = (DateEditField) findViewById(R.id.shipmentBillList_endTime_value);
		shipmentBillStateValue = (DropDownListView) findViewById(R.id.shipmentBillList_shipmentBillState_value);
		driverNameSearchButton = (ImageView) findViewById(R.id.shipmentBillList_driverNameSearch_button);
		deliverUserNamesSearchButton = (ImageView) findViewById(R.id.shipmentBillList_deliverUserNamesSearch_button);
		plateNoSearchButton = (ImageView) findViewById(R.id.shipmentBillList_plateNoSearch_button);
		billCodeValue = (EditText) findViewById(R.id.shipmentBillList_billCode_value);
		billCodeSearchButton = (TextView) findViewById(R.id.shipmentBillList_search_button);
		billCodeScanButton = (ImageView) findViewById(R.id.shipmentBillList_billCodeScan_button);
		listView = (PullToRefreshListView) findViewById(R.id.shipmentBillList_details);
		adapter = new ShipmentBillAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		shipmentBillStateValue.setUrl(ConstantUrl.common_getShipmentBillStateEnumsForJsonResult);
	}

	private void searchPageShipmentBill(boolean isClearListView) {
		if (isClearListView) {
			details.clear();
		}
		shipmentService.searchPageShipmentBillByParams(shipmentBillQueryParamsModel, new AbstractServiceCallBack<QueryResult<ShipmentOrderBillModel>>(
				ShipmentBillListActivity.this) {

			@Override
			public void doSuccess(QueryResult<ShipmentOrderBillModel> result) {
				if (result != null && result.getRecords().size() > 0) {
					List<ShipmentOrderBillModel> arrayList = result.getRecords();
					details.addAll(arrayList);
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shipmentBillList_search_button:
			setFirstPage();
			setParams();
			searchPageShipmentBill(true);
			break;
		case R.id.shipmentBillList_billCodeScan_button:
			IntentUtils.startActivityForResult(ShipmentBillListActivity.this
					, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
			break;
			
			//查询司机 
		case R.id.shipmentBillList_driverNameSearch_button:
			Intent searchPageDriverIntent = new Intent(context,EmployeeSelectorActivity.class);
			searchPageDriverIntent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPageDriverByParams);
			startActivityForResult(searchPageDriverIntent, requestCodeForDriver);
			break;
			
			//查询送货员
		case R.id.shipmentBillList_deliverUserNamesSearch_button:
			Intent searchPageDeliverIntent = new Intent(context,EmployeeSelectorActivity.class);
			searchPageDeliverIntent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPageDeliverByParams);
			startActivityForResult(searchPageDeliverIntent, requestCodeForDeliverUserNames);
			break;
			
			//选择车辆
		case R.id.shipmentBillList_plateNoSearch_button:
			Intent intent = new Intent(context,VehicleSelectorActivity.class);
			startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (resultCode == CodeScanActivity.RESULT_CODE) {
				billCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}else if (resultCode == VehicleSelectorActivity.RESULT_CODE) {
				vehicle = (VehicleModel) data.getSerializableExtra(VehicleSelectorActivity.SER_KEY);
				plateNoValue.setText(vehicle.getPlateNo());
			}else if (requestCode == requestCodeForDriver && resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				driver = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				driverNameValue.setText(driver.getName());
				
			}else if(requestCode == requestCodeForDeliverUserNames && resultCode == EmployeeSelectorActivity.RESULT_CODE){
				deliverUsers = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				deliverUserNamesValue.setText(deliverUsers.getName());
			}
		}
	}
}
