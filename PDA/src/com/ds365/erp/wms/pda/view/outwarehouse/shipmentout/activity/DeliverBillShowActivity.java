package com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.CommonTitleTab;
import com.ds365.commons.widget.CommonTitleTab.SelectChangeListener;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.sale.SaleOrderBillModel;
import com.ds365.erp.wms.pda.model.shipment.DeliverBillModel;
import com.ds365.erp.wms.pda.model.shipment.DeliverGoodsDetailModel;
import com.ds365.erp.wms.pda.service.deliver.DeliverService;
import com.ds365.erp.wms.pda.service.sale.SaleOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.SaleOrderBillShowActivity;
import com.ds365.erp.wms.pda.view.common.adapter.SaleOrderBillAdapter;
import com.ds365.erp.wms.pda.view.outwarehouse.shipmentout.adapter.DeliverGoodsDetailAdapter;
import com.ds365.erp.wms.pda.view.query.activity.ShipmentBillShowActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.TextView;
/**
 *送货单查看
 */
public class DeliverBillShowActivity extends BasePdaListActivity{
	private CommonTitleTab titleTab;
	private TextView plateNoValue,driverNameValue;//车牌号
	private DateField deliverTimeValue;//日期
	private TextView deliverUserNamesValue;	//送货员
	private TextView orderCountValue,itemCountValue,billMoneyValue,receivableMoneyValue;
	private TextView weightValue,volumeValue,deliverStationNameValue,deliverStationTypeValue;
	private PullToRefreshListView listView;
	private SaleOrderBillAdapter saleOrderBillAdapter;
	private DeliverGoodsDetailAdapter deliverGoodsDetailAdapter;
	
	private DeliverBillModel deliverBill;
	private List<DeliverGoodsDetailModel> deliverGoodsDetailList = new ArrayList<DeliverGoodsDetailModel>();
	private List<SaleOrderBillModel> saleOrderBillList = new ArrayList<SaleOrderBillModel>();
	private List<String> titleTabList = new ArrayList<String>();

	private int select = 0;
	private static final String SALE_ORDER_BILL_TAB_CODE = "saleOrderBill";
	private static final String GOODS_DETAIL_TAB_CODE = "goodsDetail";
	private String[] tabCodes = {SALE_ORDER_BILL_TAB_CODE,GOODS_DETAIL_TAB_CODE};
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	public static final String REQUEST_CODE_KEY = DeliverBillShowActivity.class.getName();
	
	private SaleOrderService saleOrderService = new SaleOrderService();
	private DeliverService deliverService = new DeliverService();

	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_deliver_bill_show;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.deliverBillShow_headerview, R.string.deliver_bill);
	}

	@Override
	protected void setListener() {
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				onTabChange();
			}
		}));
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				 if(tabCodes[select].equals(SALE_ORDER_BILL_TAB_CODE)){
					Intent intent = new Intent(context,SaleOrderBillShowActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable(SER_KEY,saleOrderBillList.get(position-1));  
					intent.putExtras(bundle);
					intent.putExtra(REQUEST_CODE_KEY, REQUEST_CODE);
					startActivity(intent);
				}else if (tabCodes[select].equals(GOODS_DETAIL_TAB_CODE)) {
					Intent intent = new Intent(context,DeliverGoodsDetailShowActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable(SER_KEY,deliverGoodsDetailList.get(position-1));  
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
	}
	
	/**
	 * tab切换(订单列表、商品明细)
	 */
	private void onTabChange(){
		if (tabCodes[select].equals(SALE_ORDER_BILL_TAB_CODE)) {
			listView.setAdapter(saleOrderBillAdapter);
			searchSaleOrderBill();
		}else if (tabCodes[select].equals(GOODS_DETAIL_TAB_CODE)) {
			listView.setAdapter(deliverGoodsDetailAdapter);
			searchDeliverGoodsDetail();
		}
	}
	
	@Override
	protected void initActivityView() {
		Bundle bundle = getIntent().getExtras();
		if (ShipmentOrderBillShowActivity.REQUEST_CODE.equals(bundle.getString(ShipmentOrderBillShowActivity.REQUEST_CODE_KEY))) {
			deliverBill = (DeliverBillModel) getIntent().getSerializableExtra(ShipmentOrderBillShowActivity.SER_KEY);
		}else if (ShipmentBillShowActivity.REQUEST_CODE.equals(bundle.getString(ShipmentBillShowActivity.REQUEST_CODE_KEY))) {
			deliverBill = (DeliverBillModel) getIntent().getSerializableExtra(ShipmentBillShowActivity.SER_KEY);
		}else{
			deliverBill = (DeliverBillModel) getIntent().getSerializableExtra(ShipmentOutBillActivity.SER_KEY);
		}
		orderCountValue = (TextView) findViewById(R.id.deliverBillShow_orderCount_value);
		itemCountValue = (TextView) findViewById(R.id.deliverBillShow_itemCount_value);
		billMoneyValue = (TextView) findViewById(R.id.deliverBillShow_billMoney_value);
		receivableMoneyValue = (TextView) findViewById(R.id.deliverBillShow_receivableMoney_value);
		weightValue = (TextView) findViewById(R.id.deliverBillShow_weight_value);
		volumeValue = (TextView) findViewById(R.id.deliverBillShow_volume_value);
		deliverStationNameValue = (TextView) findViewById(R.id.deliverBillShow_deliverStationName_value);
		deliverStationTypeValue = (TextView) findViewById(R.id.deliverBillShow_deliverStationType_value);
		deliverUserNamesValue = (TextView) findViewById(R.id.deliverBillShow_deliverUserNames_value);
		driverNameValue = (TextView) findViewById(R.id.deliverBillShow_driverName_value);
		plateNoValue = (TextView) findViewById(R.id.deliverBillShow_plateNo_value);
		TextView deliverBillCodeValue = (TextView) findViewById(R.id.deliverBillShow_deliverBillCode_value);
		listView = (PullToRefreshListView) findViewById(R.id.deliverBillShow_details);
		deliverTimeValue = (DateField) findViewById(R.id.deliverBillShow_makeTime_value);
		deliverTimeValue.setValue(deliverBill.getMakeTime());
		deliverUserNamesValue.setText(deliverBill.getDeliverUserNames());		
		driverNameValue.setText(deliverBill.getDriver().getName());
		deliverBillCodeValue.setText(deliverBill.getBillCode());
		plateNoValue.setText(deliverBill.getVehicle().getPlateNo());
		orderCountValue.setText(String.valueOf(deliverBill.getOrderCount()));
		itemCountValue.setText(String.valueOf(deliverBill.getItemCount()));
		billMoneyValue.setText(String.valueOf(deliverBill.getBillMoney()));
		receivableMoneyValue.setText(String.valueOf(deliverBill.getReceivableMoney()));
		weightValue.setText(String.valueOf(deliverBill.getWeight()));
		volumeValue.setText(String.valueOf(deliverBill.getVolume()));
		deliverStationNameValue.setText(deliverBill.getDeliverStation().getName());
		deliverStationTypeValue.setText(deliverBill.getDeliverStation().getType().getName());
		titleTab = (CommonTitleTab) findViewById(R.id.deliverBillShow_titleTab);
		titleTabList.add("订单列表");
		titleTabList.add("商品明细");
		deliverGoodsDetailAdapter=new DeliverGoodsDetailAdapter(context, deliverGoodsDetailList);
		saleOrderBillAdapter = new SaleOrderBillAdapter(context, saleOrderBillList);
		ListViewUtil.setOnPullDownListView(listView);
	} 
	
	private void searchDeliverGoodsDetail() {
		deliverGoodsDetailList.clear();
		
		deliverService.searchDeliverGoodsDetailByDeliverBillId(deliverBill.getId(),
				new AbstractServiceCallBack<List<DeliverGoodsDetailModel>>(context) {

					@Override
					public void doSuccess(List<DeliverGoodsDetailModel> result) {
						if (result != null && result.size() > 0) {
							deliverGoodsDetailList.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						deliverGoodsDetailAdapter.notifyDataSetChanged();
					}
				});
	}

	private void searchSaleOrderBill() {
		saleOrderBillList.clear();
		saleOrderService.searchSaleOrderBillByDeliverBillId(deliverBill.getId(),
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
}