package com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.CommonTitleTab;
import com.ds365.commons.widget.CommonTitleTab.SelectChangeListener;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.sale.SaleOrderBillModel;
import com.ds365.erp.wms.pda.model.shipment.DeliverBillModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentGoodsDetailModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentOrderBillModel;
import com.ds365.erp.wms.pda.model.shipment.ShipmentOutBillCreateParamsModel;
import com.ds365.erp.wms.pda.service.deliver.DeliverService;
import com.ds365.erp.wms.pda.service.sale.SaleOrderService;
import com.ds365.erp.wms.pda.service.shipment.ShipmentService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.SaleOrderBillShowActivity;
import com.ds365.erp.wms.pda.view.common.adapter.SaleOrderBillAdapter;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.adapter.DeliverBillAdapter;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.adapter.ShipmentGoodsDetailAdapter;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.adapter.ShipmentOrderBillAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
/**
 * 销售出库- 装运单详情
 * @author lxj
 *
 */
public class ShipmentOutBillActivity extends BasePdaListActivity implements OnClickListener{
	private CommonTitleTab titleTab;
	private TextView plateNoValue,driverNameValue;//车牌号
	private DateField makeTimeValue;//日期
	private TextView deliverUserNamesValue;	//送货员
//	private EditText fuzzyValue;	
	/*private ImageView searchDeliverUserNamesButton;
	private ImageView searchDriverNameButton;*/
	private ImageView fuzzyButton;
	private TextView submitButton;
	private PullToRefreshListView listView;
	private SaleOrderBillAdapter saleOrderBillAdapter;
	private ShipmentGoodsDetailAdapter shipmentGoodsDetailAdapter;
	private DeliverBillAdapter deliverBilladapter;
	
	private ShipmentOrderBillModel bill=new ShipmentOrderBillModel();
	private List<ShipmentGoodsDetailModel> shipmentGoodsDetailList = new ArrayList<ShipmentGoodsDetailModel>();
	private List<SaleOrderBillModel> saleOrderBillList = new ArrayList<SaleOrderBillModel>();
	private List<DeliverBillModel> deliverBillList = new ArrayList<DeliverBillModel>();
	private List<String> titleTabList = new ArrayList<String>();

	private int select = 0;
	private static final String SALE_ORDER_BILL_TAB_CODE="saleOrderBill";
	private static final String DELIVER_BILL_TAB_CODE="deliverBill";
	private static final String GOODS_DETAIL_TAB_CODE="goodsDetail";
	private  String[]  tabCodes={SALE_ORDER_BILL_TAB_CODE,DELIVER_BILL_TAB_CODE,GOODS_DETAIL_TAB_CODE};
	
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	
	public static final String REQUEST_CODE_KEY = ShipmentOutBillActivity.class.getName();
	
	private ShipmentService shipmentService = new ShipmentService();
	private SaleOrderService saleOrderService = new SaleOrderService();
	private DeliverService deliverService = new DeliverService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_shipment_out_bill;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.shipmentOutBill_headerview, R.string.title_deliver_confirm);
	}

	@Override
	protected void setListener() {
		submitButton.setOnClickListener(this);
		/*searchDeliverUserNamesButton.setOnClickListener(this);
		searchDriverNameButton.setOnClickListener(this);*/
		fuzzyButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (tabCodes[select].equals(SALE_ORDER_BILL_TAB_CODE)) {
					Intent intent = new Intent(context,SaleOrderBillShowActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable(SER_KEY,saleOrderBillList.get(position-1));  
					intent.putExtras(bundle);
					intent.putExtra(REQUEST_CODE_KEY, REQUEST_CODE);
					startActivity(intent);
				}else if(tabCodes[select].equals(DELIVER_BILL_TAB_CODE)){
					Intent intent = new Intent(context,DeliverBillShowActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable(SER_KEY,deliverBillList.get(position-1));  
					intent.putExtras(bundle);
					startActivity(intent);
				}else if (tabCodes[select].equals(GOODS_DETAIL_TAB_CODE)) {
					Intent intent = new Intent(context,ShipmentGoodsDetailShowActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable(SER_KEY,shipmentGoodsDetailList.get(position-1));  
					intent.putExtras(bundle);
					intent.putExtra(REQUEST_CODE_KEY, REQUEST_CODE);
					startActivity(intent);
				}
			}
		});
		
		titleTab.setSelectChangeListener(new SelectChangeListener() {
			
			@Override
			public void onItemSelect(int selectValue, RadioButton selectRadioButton) {
				select = selectValue;
				onTabChange();
			}
		});
		titleTab.setData(titleTabList);
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				onTabChange();
			}
		}));
	}
	
	private void onTabChange(){
		if (tabCodes[select].equals(SALE_ORDER_BILL_TAB_CODE)) {
			listView.setAdapter(saleOrderBillAdapter);
			searchSaleOrderBills();
		}else if (tabCodes[select].equals(DELIVER_BILL_TAB_CODE)) {
			listView.setAdapter(deliverBilladapter);
			searchDeliverBill();
		}else if (tabCodes[select].equals(GOODS_DETAIL_TAB_CODE)) {
			listView.setAdapter(shipmentGoodsDetailAdapter);
			searchShipmentGoodsDetail();
		}
	}
	
	@Override
	protected void initActivityView() {
		ShipmentOrderBillModel shipmentOrderBill = (ShipmentOrderBillModel) getIntent().getSerializableExtra(
				ShipmentOrderBillAdapter.SER_KEY);
		
		Long billId = shipmentOrderBill.getId();
		String billCode = shipmentOrderBill.getBillCode();
		bill.setId(billId);
		bill.setExaminer(new EmployeeModel(GlobalUtils.getSessionUser().getEmployeeId()));
		deliverUserNamesValue = (TextView) findViewById(R.id.shipmentOutBill_deliverUserNames_value);
		driverNameValue = (TextView) findViewById(R.id.shipmentOutBill_driverName_value);
//		fuzzyValue = (EditText) findViewById(R.id.shipmentOutBill_fuzzy_value);
		plateNoValue = (TextView) findViewById(R.id.shipmentOutBill_plateNo_value);
		TextView billCodeValue = (TextView) findViewById(R.id.shipmentOutBill_billCode_value);
		submitButton = (TextView) findViewById(R.id.shipmentOutBill_submitButton);
		listView = (PullToRefreshListView) findViewById(R.id.shipmentOutBill_details);
		/*searchDeliverUserNamesButton = (ImageView) findViewById(R.id.shipmentOutBill_searchDeliverUserNames_Button);
		searchDriverNameButton = (ImageView) findViewById(R.id.shipmentOutBill_searchDriverName_Button);*/
		fuzzyButton = (ImageView) findViewById(R.id.shipmentOutBill_fuzzy_Button);
		makeTimeValue = (DateField) findViewById(R.id.shipmentOutBill_makeTime_value);
		makeTimeValue.setValue(shipmentOrderBill.getMakeTime());
		deliverUserNamesValue.setText(shipmentOrderBill.getDeliverUserNames());		
		driverNameValue.setText(shipmentOrderBill.getDriver().getName());
		billCodeValue.setText(billCode);
		plateNoValue.setText(shipmentOrderBill.getVehicle().getPlateNo());
		titleTab = (CommonTitleTab) findViewById(R.id.shipmentOutBill_titleTab);
		titleTabList.add("订单列表");
		titleTabList.add("送货单列表");
		titleTabList.add("商品明细");
		deliverBilladapter = new DeliverBillAdapter(context, deliverBillList);
		shipmentGoodsDetailAdapter=new ShipmentGoodsDetailAdapter(context, shipmentGoodsDetailList);
		saleOrderBillAdapter = new SaleOrderBillAdapter(context, saleOrderBillList);
		ListViewUtil.setOnPullDownListView(listView);
        
	} 
	
	private void searchShipmentGoodsDetail() {
		shipmentGoodsDetailList.clear();
		shipmentService.searchShipmentGoodsDetailByBillId(bill.getId(), new AbstractServiceCallBack<List<ShipmentGoodsDetailModel>>(context) {
			
			@Override
			public void doSuccess(List<ShipmentGoodsDetailModel> result) {
				if (result != null && result.size() > 0) {
					shipmentGoodsDetailList.addAll(result); 
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
				shipmentGoodsDetailAdapter.notifyDataSetChanged();
			}
		});
	}


	private void searchSaleOrderBills() {
		saleOrderBillList.clear();
		saleOrderService.searchSaleOrderBillsByShipmentBillId(bill.getId(),
				new AbstractServiceCallBack<List<SaleOrderBillModel>>(context) {

					@Override
					public void doSuccess(List<SaleOrderBillModel> result) {
						if (result != null && result.size() > 0) {
							saleOrderBillList.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						saleOrderBillAdapter.notifyDataSetChanged();
					}
				});
	}
	
	private void searchDeliverBill() {
		deliverBillList.clear();
		deliverService.searchDeliverBillByShipmentBillId(bill.getId(),
				new AbstractServiceCallBack<List<DeliverBillModel>>(context) {

					@Override
					public void doSuccess(List<DeliverBillModel> result) {
						if (result != null && result.size() > 0) {
							deliverBillList.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						// 数据刷新后通知适配器更新UI
						deliverBilladapter.notifyDataSetInvalidated();
					}
				});
	}
	
	private void submitList() {
		
		ShipmentOutBillCreateParamsModel createParams = setupShipmentOutBillCreateParamsModel(bill);
		
		shipmentService.createShipmentOutBill(createParams, new AbstractServiceCallBack<String>(context) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				finish();
			}
		});
	}
	
	private ShipmentOutBillCreateParamsModel setupShipmentOutBillCreateParamsModel(ShipmentOrderBillModel bill) {
		
		ShipmentOutBillCreateParamsModel shipmentOutBillCreateParamsModel = new ShipmentOutBillCreateParamsModel();
		
		List<Long> saleOrderBillIds = new ArrayList<Long>();/*shipmentOutBillCreateParamsModel.getSaleOrderBillIds();*/
		for ( SaleOrderBillModel saleOrderBillModel :saleOrderBillList) {
			saleOrderBillIds.add(saleOrderBillModel.getId());
		}
		
//		shipmentOutBillCreateParamsModel.setExaminerId(bill.getExaminer().getId());
		shipmentOutBillCreateParamsModel.setId(null);
		shipmentOutBillCreateParamsModel.setShipmentBillId(bill.getId()); 
		shipmentOutBillCreateParamsModel.setMaker(bill.getMaker());
//		shipmentOutBillCreateParamsModel.setMakerId(bill.getMaker().getId());
		shipmentOutBillCreateParamsModel.setMakeTime(bill.getMakeTime());
		shipmentOutBillCreateParamsModel.setSaleOrderBillIds(saleOrderBillIds);
		
		return shipmentOutBillCreateParamsModel;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shipmentOutBill_submitButton:
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_shipmentbill), true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							submitList();
						}
					});
			break;
		case R.id.search_layout_iv:
//			getData(params);
			break;
		/*
		 * 送货员和司机改成了不可编辑状态,值直接带过来,此种方法先注释
		case R.id.shipmentOutBill_searchDeliverUserNames_Button:
			Intent intent = new Intent(context,EmployeeSelectorActivity.class);
			intent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPageDeliverByParams);
			startActivityForResult(intent, 1);
			break;
		case R.id.shipmentOutBill_searchDriverName_Button:
			Intent driverIntent = new Intent(context,EmployeeSelectorActivity.class);
			driverIntent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPageDriverByParams);
			startActivityForResult(driverIntent, 1);
			break;*/
		}
	}
	
	/*@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data) {
			if (resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				EmployeeModel employeeModel = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
//				bill.setExaminer(employeeModel);
				bill.setDriver(employeeModel);
				deliverUserNamesValue.setText(employeeModel.getName());
			}
		}
	}*/
}