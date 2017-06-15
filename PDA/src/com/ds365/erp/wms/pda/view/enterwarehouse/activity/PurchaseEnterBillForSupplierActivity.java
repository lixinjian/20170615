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
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderBillModel;
import com.ds365.erp.wms.pda.model.supplier.SupplierOrderDetailModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseEnterService;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.service.supplier.SupplierOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.EmployeeSelectorActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SupplierOrderDetailGroupAdapter;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.SupplierOrderBillAdapter;
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

public class PurchaseEnterBillForSupplierActivity extends BasePdaListActivity implements OnClickListener{
	private TextView submitButton;	//提交按钮
	private PullToRefreshListView listView;
	private SupplierOrderDetailGroupAdapter adapter;
	private ImageView searchButton,barCodeScanButton;
	private EditText barCodeValue;
	private TextView barCodeSearchButton;
	private TextView billCodeValue;
	private TextView supplierNameValue;
	private EditText receiptorNameValue;
	private DateField makeTimeValue;
	private TextView deliverBillNoValue;
	private int selectPosition = 0;
	private PurchaseEnterBillModel bill=new PurchaseEnterBillModel();
	private List<SupplierOrderDetailModel>  details=new ArrayList<SupplierOrderDetailModel>();
	
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private ShelfService shelfService = new ShelfService();
	private SupplierOrderService supplierOrderService = new SupplierOrderService();
	private PurchaseEnterService purchaseEnterService = new PurchaseEnterService();
	private ListViewFindByFieldUtils<SupplierOrderDetailModel> listFindByField;

	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_bill_for_supplier;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseEnterBill_headerview, R.string.supplier_enter_bill);
	}
	
	@Override
	protected void initActivityView() {
		barCodeSearchButton = (TextView) findViewById(R.id.purchaseEnterBillForSupplier_barCodeSearchButton);
		barCodeValue = (EditText) findViewById(R.id.purchaseEnterBillForSupplier_barCode_value);
		barCodeScanButton = (ImageView) findViewById(R.id.purchaseEnterBillForSupplier_barCode_Scan_button);
		deliverBillNoValue = (TextView) findViewById(R.id.purchaseEnterBillForSupplier_deliverBillNo_value);
		receiptorNameValue = (EditText) findViewById(R.id.purchaseEnterBillForSupplier_receiptorName_value);
		makeTimeValue = (DateField) findViewById(R.id.purchaseEnterBillForSupplier_makeTime_value);
		submitButton = (TextView) findViewById(R.id.purchaseEnterBillForSupplier_submitButton);
		searchButton = (ImageView) findViewById(R.id.purchaseEnterBillForSupplier_receiptorName_searchButton);
		supplierNameValue = (TextView) findViewById(R.id.purchaseEnterBillForSupplier_supplierName_value);
		billCodeValue = (TextView) findViewById(R.id.purchaseEnterBillForSupplier_relatedBillCode_value);
		listView = (PullToRefreshListView) findViewById(R.id.purchaseEnterBillForSupplier_details);
		
		SupplierOrderBillModel supplierOrderBill = (SupplierOrderBillModel) getIntent().getSerializableExtra(SupplierOrderBillAdapter.SER_KEY);
		Long relatedBillId = supplierOrderBill.getId();
		String relatedBillCode = supplierOrderBill.getRelatedBillCode();
		String supplierName = supplierOrderBill.getSupplier().getName();
		Long supplierId = supplierOrderBill.getSupplier().getId();
		
		makeTimeValue.setValue(supplierOrderBill.getMakeTime());
		
		supplierNameValue.setText(supplierName);
		billCodeValue.setText(relatedBillCode);
		deliverBillNoValue.setText(supplierOrderBill.getBillCode());
		bill.setRelatedBillId(relatedBillId);
		bill.setRelatedBillCode(relatedBillCode);
		bill.setSupplier(new SupplierModel(supplierId));
		bill.setRelatedBillType(supplierOrderBill.getBillType());
			
		receiptorNameValue.setText(GlobalUtils.getSessionUser().getEmployeeName());
		bill.setMaker(new EmployeeModel(GlobalUtils.getSessionUser().getEmployeeId()));
		bill.setExaminer(new EmployeeModel(GlobalUtils.getSessionUser().getEmployeeId()));
		
		adapter = new SupplierOrderDetailGroupAdapter(context, details);
		listView.setAdapter(adapter);
		listFindByField = new ListViewFindByFieldUtils<SupplierOrderDetailModel>(listView.getRefreshableView(), details,adapter);
		ListViewUtil.setOnPullDownListView(listView);
		
		getShelfForPurchaseEnter();
        
	}
	/**
	 * 获取数据
	 * @param params
	 */
	private void getShelfForPurchaseEnter() {
		details.clear();
		
		shelfService.getShelfForPurchaseEnter(new AbstractServiceCallBack<ShelfModel>(PurchaseEnterBillForSupplierActivity.this) {

					@Override
					public void doSuccess(ShelfModel result) {
						if (null != result) {
							searchSupplierOrderDetails(result);
						} else {
							T.showLong(context, "没有设置默认的临时入库库区货位");
						}
					}
				});
	}
	
	private void searchSupplierOrderDetails(final ShelfModel shelf) {
		supplierOrderService.searchSupplierOrderDetailsByBillId(bill.getRelatedBillId(),
				new AbstractServiceCallBack<List<SupplierOrderDetailModel>>(PurchaseEnterBillForSupplierActivity.this) {
			
			@Override
			public void doSuccess(List<SupplierOrderDetailModel> result) {
				
				if (result != null && result.size() > 0) {
					details.addAll(result);
					for (int i = 0; i < details.size(); i++) {
						PurchaseEnterDetailModel data = convertToPurchaseEnterDetailModelForSupplier(details.get(i), shelf);
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
	
	protected PurchaseEnterDetailModel convertToPurchaseEnterDetailModelForSupplier(SupplierOrderDetailModel oldDetail, ShelfModel shelf) {
		
		PurchaseEnterDetailModel detail=new PurchaseEnterDetailModel();
		detail.setId(null);
		detail.setBillCode(null);
		
		detail.setGoods(oldDetail.getPack().getGoods());
		detail.setSku(oldDetail.getSku());
		detail.setAccountSplitWay(oldDetail.getAccountSplitWay());
		detail.setRelatedBillCode(oldDetail.getBillCode());
		detail.setCooperateType(oldDetail.getCooperateType());
		detail.setPack(oldDetail.getPack());
		detail.setPackType(oldDetail.getPack().getPackType());
		detail.setUnitName(oldDetail.getUnitName());
		detail.setSpec(oldDetail.getSpec());
		detail.setSpecQty(oldDetail.getSpecQty());
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

		purchaseEnterService.createPurchaseEnterBill(createParams, new AbstractServiceCallBack<String>(PurchaseEnterBillForSupplierActivity.this) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				finish();
			}
		});
	}
	
	private PurchaseEnterBillCreateParamsModel setupPurchaseEnterBillCreateParamsModel(PurchaseEnterBillModel bill,List<PurchaseEnterDetailModel> details) {
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
				createDetail.setBarcode(detail.getSku().getBarcode());
				createDetail.setPackId(detail.getPack().getId());
				createDetail.setPackTypeId(detail.getPackType().getId());
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
		case R.id.purchaseEnterBillForSupplier_submitButton:
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_receivedbill), true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							submitList();
						}
					});
			break;
		case R.id.purchaseEnterBillForSupplier_receiptorName_searchButton:
			Intent intent = new Intent(context,EmployeeSelectorActivity.class);
			intent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPageCustodianByParams);
			startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
			break;
		case R.id.purchaseEnterBillForSupplier_barCode_Scan_button:
			IntentUtils.startActivityForResult(PurchaseEnterBillForSupplierActivity.this
					, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
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
				
			}else if(resultCode == PurchaseEnterDetailEditForSupplierActivity.RESULT_CODE){
				SupplierOrderDetailModel  enterDetailModel=(SupplierOrderDetailModel)data.getSerializableExtra(PurchaseEnterDetailEditForSupplierActivity.SER_KEY);
				details.set(selectPosition, enterDetailModel);
				for (int i = 0; i <details.get(selectPosition).getPurchaseEnterDetails().size() ; i++) {
					details.get(selectPosition).getPurchaseEnterDetails().get(i).setCheckFlag(true);
				}
				enterDetailModel.setCheckFlag(true);
				adapter.notifyDataSetChanged();
			}else if(resultCode == CodeScanActivity.RESULT_CODE){
				barCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}

	@Override
	protected void setListener() {
		searchButton.setOnClickListener(this);
		submitButton.setOnClickListener(this);
		barCodeScanButton.setOnClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectPosition = position-1;
				
				IntentUtils.startActivityBySerialForResult(PurchaseEnterBillForSupplierActivity.this
						, PurchaseEnterDetailEditForSupplierActivity.class 
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
				.hideSoftInputFromWindow(PurchaseEnterBillForSupplierActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
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
