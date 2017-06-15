package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.ListViewFindByFieldUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.db.dbmanager.DataBaseManager;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderDetailModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterDetailModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderBillModel;
import com.ds365.erp.wms.pda.model.sale.SaleReturnOrderDetailModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.service.sale.SaleReturnEnterService;
import com.ds365.erp.wms.pda.service.sale.SaleReturnOrderService;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.EmployeeSelectorActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SaleReturnEnterDetailAdapter;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SaleReturnOrderBillAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 退货入库--退货申请单详情
 * @author lxj
 *
 */
public class SaleReturnEnterBillActivity extends BasePdaListActivity implements OnClickListener{
	
	private ImageView searchReceiptorButton;
	private TextView billCodeValue;
	private EditText receiptorNameValue;
	private TextView submitButton;
	private DateField makeTimeValue;
	private PullToRefreshListView listView;
	private EditText barCodeValue;
	private ImageView barCodeScanButton;
	private TextView barCodeSearchButton;
	private SaleReturnEnterDetailAdapter adapter;
//	private DataBaseManager dbManager;

	private SaleReturnEnterBillModel  bill=new SaleReturnEnterBillModel();
	private List<SaleReturnEnterDetailModel> details = new ArrayList<SaleReturnEnterDetailModel>();
	private ListViewFindByFieldUtils<SaleReturnEnterDetailModel> listFindByField;
	private int selectPosition = 0;
	private String relatedBillCode;
//	private Long skuId, packId;
	
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private ShelfService shelfService = new ShelfService();
	private SaleReturnOrderService saleReturnOrderService = new SaleReturnOrderService();
	private SaleReturnEnterService saleReturnEnterService = new SaleReturnEnterService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_enter_bill;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleReturnEnterBill_headerview, R.string.sale_return_enter);
	}
	
	@Override
	protected void initActivityView() {
		
//		dbManager = DataBaseManager.getDBManagerInstence(getApplicationContext());
		
		SaleReturnOrderBillModel saleReturnOrderBill = (SaleReturnOrderBillModel) getIntent().getSerializableExtra(SaleReturnOrderBillAdapter.SER_KEY);
		Long relatedBillId = saleReturnOrderBill.getId();
		relatedBillCode = saleReturnOrderBill.getBillCode();
		barCodeValue = (EditText) findViewById(R.id.saleReturnEnterBill_barCode_value);
		barCodeScanButton = (ImageView) findViewById(R.id.saleReturnEnterBill_barCode_Scan_button);
		barCodeSearchButton = (TextView) findViewById(R.id.saleReturnEnterBill_barCodeSearchButton);
		billCodeValue = (TextView) findViewById(R.id.saleReturnEnterBill_billCode_value);
		TextView customerNameValue = (TextView) findViewById(R.id.saleReturnEnterBill_customer_value);
		submitButton = (TextView) findViewById(R.id.saleReturnEnterBill_submitButton);
		makeTimeValue = (DateField) findViewById(R.id.saleReturnEnterBill_makeTime_value);
		receiptorNameValue = (EditText) findViewById(R.id.saleReturnEnterBill_receiptorName_value);
		receiptorNameValue.setText(GlobalUtils.getSessionUser().getEmployeeName());
		searchReceiptorButton = (ImageView) findViewById(R.id.pickBillList_receiptorName_searchButton);
		listView = (PullToRefreshListView) findViewById(R.id.saleReturnEnterBill_details);
		customerNameValue.setText(saleReturnOrderBill.getCustomer().getName());
		makeTimeValue.setValue(saleReturnOrderBill.getMakeTime());
		
		bill.setRelatedBillId(relatedBillId);
		bill.setRelatedBillCode(relatedBillCode);
	//	bill.setRelatedBillType(relatedBillType);
		bill.setExaminer(new EmployeeModel(GlobalUtils.getSessionUser().getEmployeeId()));
		
		
		billCodeValue.setText(relatedBillCode);
		
		adapter = new SaleReturnEnterDetailAdapter(context, details);
		listView.setAdapter(adapter);
		listFindByField = new ListViewFindByFieldUtils<SaleReturnEnterDetailModel>(listView.getRefreshableView(), details, adapter);
		ListViewUtil.setOnPullDownListView(listView);
		getShelfForSaleReturn();
        
	}
	
	private void submitList() {
		
		relatedBillCode = billCodeValue.getText().toString();
		 
		String receiptorName= receiptorNameValue.getText().toString();
		 
		 /**
		  * 验证相关字段
		  */
		SaleReturnEnterBillCreateParamsModel createParams =setupSaleReturnEnterBillCreateParamsModel(bill,details);
		if(createParams.getDetails().size()==0){
			T.showShort(context, "提交的检查记录数不能为空");
			return;
		}
		
		saleReturnEnterService.createSaleReurnOutBill(createParams, new AbstractServiceCallBack<String>(SaleReturnEnterBillActivity.this) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				//提交成功删除本地数据库中的存储信息
//				dbManager.deleteSaleReturnBill();
				finish();
			}
		});
		
	}
	
	private SaleReturnEnterBillCreateParamsModel setupSaleReturnEnterBillCreateParamsModel(SaleReturnEnterBillModel bill,List<SaleReturnEnterDetailModel> details) {
		SaleReturnEnterBillCreateParamsModel saleReturnEnterBillCreateParamsModel = new SaleReturnEnterBillCreateParamsModel();
		
		saleReturnEnterBillCreateParamsModel.setOutDate(bill.getMakeTime());
		saleReturnEnterBillCreateParamsModel.setRelatedBillId(bill.getRelatedBillId());
		saleReturnEnterBillCreateParamsModel.setUserId(bill.getId());
		saleReturnEnterBillCreateParamsModel.setWarehouseId(GlobalUtils.getSessionUser().getWarehouseId());
		saleReturnEnterBillCreateParamsModel.setRelatedBillCode(bill.getRelatedBillCode());
		
		List<SaleReturnEnterDetailCreateParamsModel> createDetails = new ArrayList<SaleReturnEnterDetailCreateParamsModel>();
		for(SaleReturnEnterDetailModel detail: details){
			
			if (detail.isCheckFlag()) {
				
				SaleReturnEnterDetailCreateParamsModel createDetail = new SaleReturnEnterDetailCreateParamsModel();
				createDetail.setRelatedBillId(bill.getRelatedBillId());
				createDetail.setRelatedBillCode(bill.getRelatedBillCode());
				createDetail.setRelatedDetailId(detail.getRelatedDetailId());
				createDetail.setStoreId(detail.getStore().getId());
				createDetail.setShelfId(detail.getShelf().getId());
				createDetail.setShelfCode(detail.getShelf().getCode());
				createDetail.setProduceBatchNo(detail.getProduceBatchNo());
				createDetail.setUnitQty(detail.getUnitQty());
				createDetail.setMinUnitQty(detail.getMinUnitQty());
				createDetail.setSkuId(detail.getSku().getId());
				createDetail.setSysBatchNo(detail.getSysBatchNo());
				createDetail.setPackId(detail.getPack().getId());
				createDetail.setProduceDate(detail.getProduceDate());
				createDetail.setSpec(detail.getSpec());
				createDetail.setSpecQty(detail.getSpecQty());
				createDetail.setUnitName(detail.getUnitName());
				createDetail.setBarcode(detail.getBarcode());
				createDetail.setGoodsId(detail.getSku().getGoods().getId());
				createDetail.setGoodsName(detail.getSku().getGoods().getName());
				
				if (detail.getUnitQty() == null) 
					createDetail.setUnitQty(0);
				else
					createDetail.setUnitQty(detail.getUnitQty());
				if (detail.getMinUnitQty() == null) 
					createDetail.setMinUnitQty(0);
				else
					createDetail.setMinUnitQty(detail.getMinUnitQty());
				
				int unitQty = createDetail.getUnitQty();
				int minUnitQty = createDetail.getMinUnitQty();
				int qty = QtyMoneyUtils.getQty(unitQty, minUnitQty, detail.getSpecQty());
				if(qty!=0){ 
					createDetails.add(createDetail);
				}
			}
		}
		saleReturnEnterBillCreateParamsModel.setDetails(createDetails);
		
		return saleReturnEnterBillCreateParamsModel;
	}

	@Override
	protected void setListener() {
		submitButton.setOnClickListener(this);
		searchReceiptorButton.setOnClickListener(this);
		barCodeScanButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Intent intent = new Intent(context, SaleReturnEnterDetailEditActivity.class);
				selectPosition = position-1;
				
				Bundle bundle = new Bundle();  
		        bundle.putSerializable(SER_KEY,details.get(selectPosition));  
		        intent.putExtras(bundle);
				
				startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				getShelfForSaleReturn();
			}
		}));
		
		barCodeSearchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String searchCondition = barCodeValue.getText().toString().trim();//查找条件
				((InputMethodManager) barCodeSearchButton.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(SaleReturnEnterBillActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				if("".equals(searchCondition)){
					T.showShort(context, "查找条件不能为空！");
				}else{
					if(listFindByField !=null){
						listFindByField.findItemByFields("sku.barcode","sku.code",searchCondition);
					}
				}
			}
		});
	}

	private void getShelfForSaleReturn() {
		details.clear();
		
		shelfService.getShelfForSaleReturn(new AbstractServiceCallBack<ShelfModel>(SaleReturnEnterBillActivity.this) {

					@Override
					public void doSuccess(ShelfModel result) {
						if (null != result) {
							searchSaleOrderDetails(result);
						} else {
							T.showLong(context, "没有设置默认的临时入库库区货位");
						}
					}
				});
	}
	
	private void searchSaleOrderDetails(final ShelfModel shelf) {
		details.clear();
		saleReturnOrderService.searchSaleOrderDetailByBillId(bill.getRelatedBillId(),
				new AbstractServiceCallBack<List<SaleReturnOrderDetailModel>>(SaleReturnEnterBillActivity.this) {

					@Override
					public void doSuccess(List<SaleReturnOrderDetailModel> result) {
						if (result != null && result.size() > 0) {
							List<SaleReturnEnterDetailModel> list = convertToSaleReturnEnterDetailModel(result,
									shelf);
							details.addAll(list);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						adapter.notifyDataSetInvalidated();
					}
				});
	}
	
	private List<SaleReturnEnterDetailModel> convertToSaleReturnEnterDetailModel(
			List<SaleReturnOrderDetailModel> data, ShelfModel shelf) {
		// TODO Auto-generated method stub
		List<SaleReturnEnterDetailModel> list=new ArrayList<SaleReturnEnterDetailModel>();
		for(SaleReturnOrderDetailModel  orderDetail:data){
			SaleReturnEnterDetailModel enterDetail=new SaleReturnEnterDetailModel();
			enterDetail.setRelatedDetailId(orderDetail.getId());
			enterDetail.setRelatedBillId(bill.getRelatedBillId());
			enterDetail.setRelatedBillCode(bill.getRelatedBillCode());
			enterDetail.setRelatedBillType(bill.getRelatedBillType());
			enterDetail.setUnitQty(orderDetail.getUnitQty());
			enterDetail.setMinUnitQty(orderDetail.getMinUnitQty());
			enterDetail.setQty(orderDetail.getQty());
			enterDetail.setSignUnitQty(orderDetail.getSignUnitQty());
			enterDetail.setSignMinUnitQty(orderDetail.getSignMinUnitQty());
			enterDetail.setSignQty(orderDetail.getSignQty());
			
			enterDetail.setGoodsName(orderDetail.getGoodsName());
			enterDetail.setSkuName(orderDetail.getSkuName());
			enterDetail.setSku(orderDetail.getSku());
			enterDetail.setUnitName(orderDetail.getUnitName());
			enterDetail.setSpec(orderDetail.getSpec());
			enterDetail.setSpecQty(orderDetail.getSpecQty());
			enterDetail.setStore(shelf.getStore());
			enterDetail.setShelf(shelf);
			enterDetail.setSysBatchNo(orderDetail.getSysBatchNo());
			enterDetail.setProduceBatchNo(orderDetail.getProduceBatchNo());
			enterDetail.setProduceDate(orderDetail.getProduceDate());
			enterDetail.setBarcode(orderDetail.getSku().getBarcode());
			enterDetail.setPack(orderDetail.getPack());
			list.add(enterDetail);
		}
		
		
		return list;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				EmployeeModel employeeModel = (EmployeeModel) intent.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				
				bill.setExaminer(employeeModel);
				
				receiptorNameValue.setText(employeeModel.getName());
			}else if (resultCode == SaleReturnEnterDetailEditActivity.RESULT_CODE) {
				
				SaleReturnEnterDetailModel saleReturnEnterDetailModel = (SaleReturnEnterDetailModel) intent.getSerializableExtra(SaleReturnEnterDetailEditActivity.SER_KEY);
				
				details.set(selectPosition, saleReturnEnterDetailModel);
				details.get(selectPosition).setCheckFlag(true);
				adapter.notifyDataSetChanged();
			}else if(resultCode == CodeScanActivity.RESULT_CODE){
				barCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.saleReturnEnterBill_submitButton:
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_returnbill), true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							submitList();
						}
					});
			break;
		case R.id.pickBillList_receiptorName_searchButton:
			Intent intent = new Intent(context,EmployeeSelectorActivity.class);
			intent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPageCustodianByParams);
			startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
		break;
		case R.id.saleReturnEnterBill_barCode_Scan_button:
			IntentUtils.startActivityForResult(SaleReturnEnterBillActivity.this
					, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
			break;
		}
	}
}