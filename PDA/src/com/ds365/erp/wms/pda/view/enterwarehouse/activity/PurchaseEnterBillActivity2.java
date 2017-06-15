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
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.commons.SupplierModel;
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderDetailModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseEnterService;
import com.ds365.erp.wms.pda.service.purchase.PurchaseOrderService;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.EmployeeSelectorActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.PurchaseOrderBillAdapter;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.PurchaseOrderDetailGroupAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 收货入库-订单详情
 *
 */
public class PurchaseEnterBillActivity2 extends BasePdaListActivity implements OnClickListener{
	private TextView submitButton;	//提交按钮
	private PullToRefreshListView listView;
	private PurchaseOrderDetailGroupAdapter adapter;
	private ImageView searchButton,deliverBillNoScanButton,barCodeScanButton;
	private TextView billCodeValue;
	private TextView supplierNameValue;
	private EditText receiptorNameValue;//
	private DateField makeTimeValue;
	private TextView deliverBillNoValue;
	private EditText barCodeValue;
	private TextView barCodeSearchButton;
	private int selectPosition = 0;
	private PurchaseEnterBillModel bill=new PurchaseEnterBillModel();
	private List<PurchaseOrderDetailModel>  details=new ArrayList<PurchaseOrderDetailModel>();
	private ListViewFindByFieldUtils<PurchaseOrderDetailModel> listFindByField;
	
//	private DataBaseManager dbManager;
	
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private ShelfService shelfService = new ShelfService();
	private PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
	private PurchaseEnterService purchaseEnterService = new PurchaseEnterService();
	
	private static final int requestCodeForDeliverBillNo = Integer.valueOf(PdaConstants.nextRequestCode());
	private static final int requestCodeForBarCode = Integer.valueOf(PdaConstants.nextRequestCode());
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_bill;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseEnterBill_headerview, R.string.purchase_enter_warehouse);
	}
	
	@Override
	protected void initActivityView() {
		
		barCodeValue = (EditText) findViewById(R.id.purchaseEnterbill_barCode_value);
		barCodeSearchButton = (TextView) findViewById(R.id.purchaseEnterbill_barCodeSearchButton);
		barCodeScanButton = (ImageView) findViewById(R.id.purchaseEnterbill_barCode_Scan_button);
		deliverBillNoScanButton = (ImageView) findViewById(R.id.purchaseEnterbill_deliverBillNoScan_button);
		deliverBillNoValue = (TextView) findViewById(R.id.purchaseEnterbill_deliverBillNo_value);
		receiptorNameValue = (EditText) findViewById(R.id.purchaseEnterbill_receiptorName_value);
		makeTimeValue = (DateField) findViewById(R.id.purchaseEnterBill_makeTime_value);
		submitButton = (TextView) findViewById(R.id.purchaseEnterBill_submitButton);
		searchButton = (ImageView) findViewById(R.id.pickBillList_receiptorName_searchButton);
		supplierNameValue = (TextView) findViewById(R.id.purchaseEnterbill_supplierName_value);
		billCodeValue = (TextView) findViewById(R.id.purchaseEnterBill_billCode_value);
		listView = (PullToRefreshListView) findViewById(R.id.purchaseEnterBill_details);
		
//		dbManager = DataBaseManager.getDBManagerInstence(getApplicationContext());
		
		PurchaseOrderBillModel purchaseOrderBill = (PurchaseOrderBillModel) getIntent().getSerializableExtra(PurchaseOrderBillAdapter.SER_KEY);
		
		Long relatedBillId = purchaseOrderBill.getId();
		String relatedBillCode = purchaseOrderBill.getBillCode();
		String supplierName = purchaseOrderBill.getSupplier().getName();
		Long supplierId = purchaseOrderBill.getSupplier().getId();
		
		makeTimeValue.setValue(purchaseOrderBill.getMakeTime());
		
		supplierNameValue.setText(supplierName);
		billCodeValue.setText(relatedBillCode);
		
		bill.setRelatedBillId(relatedBillId);
		bill.setRelatedBillCode(relatedBillCode);
		bill.setSupplier(new SupplierModel(supplierId));
		bill.setRelatedBillType(purchaseOrderBill.getBillType());
		receiptorNameValue.setText(GlobalUtils.getSessionUser().getEmployeeName());
		bill.setMaker(new EmployeeModel(GlobalUtils.getSessionUser().getEmployeeId()));
		bill.setExaminer(new EmployeeModel(GlobalUtils.getSessionUser().getEmployeeId()));
		
		adapter = new PurchaseOrderDetailGroupAdapter(context, details);
		listView.setAdapter(adapter);
		listFindByField = new ListViewFindByFieldUtils<PurchaseOrderDetailModel>(listView.getRefreshableView(), details, adapter);
		ListViewUtil.setOnPullDownListView(listView);
		getShelfForPurchaseEnter();
        
	}
	/**
	 * 获取数据
	 * @param params
	 */
	private void getShelfForPurchaseEnter() {
		details.clear();
		
		shelfService.getShelfForPurchaseEnter(new AbstractServiceCallBack<ShelfModel>(PurchaseEnterBillActivity2.this) {

					@Override
					public void doSuccess(ShelfModel result) {
						if (null != result) {
							searchPurchaseOrderDetails(result);
						} else {
							T.showLong(context, "没有设置默认的临时入库库区货位");
						}
					}
				});
	}
	
	private void searchPurchaseOrderDetails(final ShelfModel shelf) {
		purchaseOrderService.searchPurchaseOrderDetailsByBillId(bill.getRelatedBillId(),
			new AbstractServiceCallBack<List<PurchaseOrderDetailModel>>(PurchaseEnterBillActivity2.this) {

				@Override
				public void doSuccess(List<PurchaseOrderDetailModel> result) {
					if (result != null && result.size() > 0) {
						details.addAll(result);
						for (int i = 0; i < details.size(); i++) {
							PurchaseEnterDetailModel data = convertToPurchaseEnterDetailModel(details.get(i), shelf);
							if(details.get(i).getPurchaseEnterDetails() == null){
								List<PurchaseEnterDetailModel> list = new ArrayList<PurchaseEnterDetailModel>();
								list.add(data);
								details.get(i).setPurchaseEnterDetails(list);
							}else{
								details.get(i).getPurchaseEnterDetails().add(data);
							}
						}
					}else{
						T.showShort(context, R.string.listview_no_more);
					}
					adapter.notifyDataSetInvalidated();
				}
			});
	}

	protected PurchaseEnterDetailModel convertToPurchaseEnterDetailModel(PurchaseOrderDetailModel oldDetail, ShelfModel shelf){
		PurchaseEnterDetailModel detail=new PurchaseEnterDetailModel();
		detail.setId(null);
		detail.setBillCode(null);
		
		detail.setGoods(oldDetail.getGoods());
		detail.setSku(oldDetail.getSku());
		detail.setAccountSplitWay(oldDetail.getAccountSplitWay());
		detail.setCooperateType(oldDetail.getCooperateType());
		detail.setPack(oldDetail.getPack());
		detail.setPackType(oldDetail.getPackType());
		detail.setUnitName(oldDetail.getUnitName());
		detail.setSpec(oldDetail.getSpec());
		detail.setSpecQty(oldDetail.getSpecQty());
		detail.setStockQty(oldDetail.getStockQty());
		detail.setUnitQty(oldDetail.getUnitQty());
		detail.setMinUnitQty(oldDetail.getMinUnitQty());
		detail.setQty(oldDetail.getQty());
		detail.setExpectUnitQty(oldDetail.getUnitQty());
		detail.setExpectMinUnitQty(oldDetail.getMinUnitQty());
		detail.setQty(oldDetail.getQty());
		detail.setBill(bill);
		detail.setCostPrice(oldDetail.getCostPrice());
		detail.setRelatedBillCode(oldDetail.getBillCode());
		detail.setRelatedDetailId(oldDetail.getId());
		detail.setReceivedUnitQty(oldDetail.getReceivedUnitQty());
		detail.setReceivedMinUnitQty(oldDetail.getReceivedMinUnitQty());
		detail.setStore(shelf.getStore());
		detail.setShelf(shelf);
		detail.setGoodsState(oldDetail.getGoodsState());
		detail.setProduceBatchNo(oldDetail.getProduceBatchNo());
		detail.setProduceDate(oldDetail.getProduceDate());
		detail.setExpectQty(oldDetail.getQty());
		return detail;
	}
	
	/**
	 * 提交处理
	 * @param params
	 */
	private void submitList() {
		
		List<PurchaseEnterDetailModel>  newDetails = new ArrayList<PurchaseEnterDetailModel>();
		
		for (int i = 0; i < details.size(); i++) {
			if(details.get(i).isCheckFlag()){//过滤未检查的记录
				List<PurchaseEnterDetailModel> models = details.get(i).getPurchaseEnterDetails();
				newDetails.addAll(models);
			}
		}
		
		PurchaseEnterBillCreateParamsModel  createParams=setupPurchaseEnterBillCreateParamsModel(bill,newDetails);
		
		if(createParams.getDetails().size()==0){
			T.showShort(context, "提交的检查记录数不能为空");
			return;
		}

		purchaseEnterService.createPurchaseEnterBill(createParams, new AbstractServiceCallBack<String>(PurchaseEnterBillActivity2.this) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				//提交成功删除本地数据库中的存储信息
//				dbManager.deletePurchaseOrderBill();
				finish();
			}
		});
	}
	
	private PurchaseEnterBillCreateParamsModel setupPurchaseEnterBillCreateParamsModel(PurchaseEnterBillModel bill,List<PurchaseEnterDetailModel> details) {
		// TODO Auto-generated method stub
		 PurchaseEnterBillCreateParamsModel purchaseEnterBillCreateParamsModel = new PurchaseEnterBillCreateParamsModel();
		
		purchaseEnterBillCreateParamsModel.setEnterDate(bill.getMakeTime());
		purchaseEnterBillCreateParamsModel.setMakerId(bill.getMaker().getId());
		purchaseEnterBillCreateParamsModel.setRelatedBillId(bill.getRelatedBillId());
		purchaseEnterBillCreateParamsModel.setRelatedBillTypeId(bill.getRelatedBillType().getId());
		purchaseEnterBillCreateParamsModel.setExaminerId(bill.getExaminer().getId());
		purchaseEnterBillCreateParamsModel.setRelatedBillCode(bill.getRelatedBillCode());
		purchaseEnterBillCreateParamsModel.setSupplierId(bill.getSupplier().getId());
		purchaseEnterBillCreateParamsModel.setDeliverBillNo(deliverBillNoValue.getText().toString().trim());
		List<PurchaseEnterDetailCreateParamsModel> createDetails=new ArrayList<PurchaseEnterDetailCreateParamsModel>();
		for(PurchaseEnterDetailModel detail: details){
//			if(detail.isCheckFlag()){ 
				
				PurchaseEnterDetailCreateParamsModel createDetail=new PurchaseEnterDetailCreateParamsModel();
				createDetail.setRelatedBillId(bill.getRelatedBillId());
				createDetail.setRelatedBillTypeId(bill.getRelatedBillType().getId());
				createDetail.setRelatedBillCode(bill.getRelatedBillCode());
				createDetail.setRelatedDetailId(detail.getRelatedDetailId());
				createDetail.setStoreId(detail.getStore().getId());
				createDetail.setShelfId(detail.getShelf().getId());
				createDetail.setShelfCode(detail.getShelf().getCode());
				createDetail.setProduceBatchNo(detail.getProduceBatchNo());
				createDetail.setSpecQty(detail.getSpecQty());
				createDetail.setStockQty(detail.getStockQty());
				createDetail.setSkuId(detail.getSku().getId());
				createDetail.setSkuCode(detail.getSku().getCode());
				createDetail.setSpec(detail.getSpec());
				createDetail.setProduceDate(detail.getProduceDate());
//				createDetail.setGoodsName(detail.getGoods().getName());
//				createDetail.setGoodsCode(detail.getGoods().getCode());
//				createDetail.setGoodsStateId(detail.getGoodsState().getId());
				createDetail.setBarcode(detail.getSku().getBarcode());
//				createDetail.setStoreUseTypeId(detail.getStore().getUseType().getId());
				createDetail.setPackId(detail.getPack().getId());
				createDetail.setPackTypeId(detail.getPackType().getId());
//				createDetail.setManufacturer(detail.getGoods().getManufacturer());
				createDetail.setReceivedUnitQty(detail.getReceivedUnitQty());
				createDetail.setReceivedMinUnitQty(detail.getReceivedMinUnitQty());
				
				createDetail.setUnitName(detail.getUnitName());
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
//				}
			}
		}
		
		purchaseEnterBillCreateParamsModel.setDetails(createDetails);
		
		return purchaseEnterBillCreateParamsModel;
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.purchaseEnterBill_submitButton:
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_receivedbill), true, new DialogInterface.OnClickListener() {
						
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
		case R.id.purchaseEnterbill_deliverBillNoScan_button:
			IntentUtils.startActivityForResult(PurchaseEnterBillActivity2.this
					, CodeScanActivity.class, requestCodeForDeliverBillNo);
			break;
		case R.id.purchaseEnterbill_barCode_Scan_button:
			IntentUtils.startActivityForResult(PurchaseEnterBillActivity2.this
					, CodeScanActivity.class, requestCodeForBarCode);
			break;
		}
	}
	
	/**
	 * 修改item的值并存储到本地
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data) {
			if (resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				EmployeeModel employeeModel = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				
				bill.setExaminer(employeeModel);
				
				receiptorNameValue.setText(employeeModel.getName());
				
			}else if(resultCode == PurchaseEnterDetailEditActivity2.RESULT_CODE){
				PurchaseOrderDetailModel  enterDetailModel=(PurchaseOrderDetailModel)data.getSerializableExtra(PurchaseEnterDetailEditActivity2.SER_KEY);
				details.set(selectPosition, enterDetailModel);
				for (int i = 0; i <details.get(selectPosition).getPurchaseEnterDetails().size() ; i++) {
					details.get(selectPosition).getPurchaseEnterDetails().get(i).setCheckFlag(true);
				}
				enterDetailModel.setCheckFlag(true);
				adapter.notifyDataSetChanged();
			}else if (requestCode == requestCodeForDeliverBillNo && resultCode == CodeScanActivity.RESULT_CODE) {
					deliverBillNoValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}else if(requestCode == requestCodeForBarCode && resultCode == CodeScanActivity.RESULT_CODE){
				barCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}

	@Override
	protected void setListener() {
		searchButton.setOnClickListener(this);
		submitButton.setOnClickListener(this);
		deliverBillNoScanButton.setOnClickListener(this);
		barCodeScanButton.setOnClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectPosition = position-1;
				
				IntentUtils.startActivityBySerialForResult(PurchaseEnterBillActivity2.this
						, PurchaseEnterDetailEditActivity2.class 
						, SER_KEY, details.get(selectPosition), PdaConstants.PDA_REQUEST_CODE);
				
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				getShelfForPurchaseEnter();
			}
		}));
		
		barCodeSearchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String searchCondition = barCodeValue.getText().toString().trim();//查找条件
				((InputMethodManager) barCodeSearchButton.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(PurchaseEnterBillActivity2.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
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
}
