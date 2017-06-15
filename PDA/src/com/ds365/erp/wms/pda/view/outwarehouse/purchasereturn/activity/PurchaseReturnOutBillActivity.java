package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
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
import com.ds365.erp.wms.pda.model.employee.EmployeeModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOrderDetailModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutBillModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutDetailModel;
import com.ds365.erp.wms.pda.service.purchase.PurchaseEnterService;
import com.ds365.erp.wms.pda.service.purchase.PurchaseOrderService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.EmployeeSelectorActivity;
import com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter.PurchaseReturnOrderBillAdapter;
import com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.adapter.PurchaseReturnOutDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 采购退货 - 退货申请单详情
 * @author LiXinjian
 *
 */
public class PurchaseReturnOutBillActivity extends BasePdaListActivity implements OnClickListener{
	private EditText examinerValue;
	private PullToRefreshListView listView;
	private TextView supplierNameValue;
	private TextView billlCode;
	private TextView submitButton;
	private DateField makeTimeValue;
	private ImageView searchExaminerButton;
	
	private int selectPosition;
	
	private PurchaseReturnOutDetailAdapter adapter;
//	private DataBaseManager dbManager;
	
	private PurchaseReturnOutBillModel bill =new PurchaseReturnOutBillModel();
	private List<PurchaseReturnOutDetailModel> details = new ArrayList<PurchaseReturnOutDetailModel>();

	private PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
	private PurchaseEnterService purchaseEnterService = new PurchaseEnterService();
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_out_bill;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseReturnOutBill_headerview, R.string.purchase_return_out);
	}

	@Override
	protected void setListener() {
		submitButton.setOnClickListener(this);
		searchExaminerButton.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 得到listview中订单号的id,根据id去启动所对应订单号下商品列表页
				selectPosition = position-1;
				IntentUtils.startActivityBySerialForResult(PurchaseReturnOutBillActivity.this
						, PurchaseReturnOutDetailEditActivity.class, SER_KEY
						, details.get(selectPosition), PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				searchPurchaseReturnOrderDetails();
			}
		}));
	}
	
	@Override
	protected void initActivityView() {
//		dbManager = DataBaseManager.getDBManagerInstence(getApplicationContext());
		PurchaseReturnOrderBillModel purchaseReturnOrderBill = (PurchaseReturnOrderBillModel) getIntent().getSerializableExtra(PurchaseReturnOrderBillAdapter.SER_KEY);
		
		Long relatedBillId = purchaseReturnOrderBill.getId();
		String relatedBillCode = purchaseReturnOrderBill.getBillCode();
		String supplierName = purchaseReturnOrderBill.getSupplier().getName();
		
		bill.setRelatedBillId(relatedBillId);
		bill.setRelatedBillCode(relatedBillCode);
		bill.setExaminer(new EmployeeModel(GlobalUtils.getSessionUser().getEmployeeId()));
		bill.setMaker(new EmployeeModel(GlobalUtils.getSessionUser().getEmployeeId()));
		bill.setRelatedBillType(purchaseReturnOrderBill.getBillType());
		searchExaminerButton = (ImageView) findViewById(R.id.pickBillList_examinerName_searchButton);
		billlCode = (TextView) findViewById(R.id.purchaseReturnOutBill_billCode_value);
		supplierNameValue = (TextView) findViewById(R.id.purchaseReturnOutBill_supplierName_value);
		examinerValue = (EditText) findViewById(R.id.purchaseReturnOutBill_examinerName_value);
		makeTimeValue = (DateField) findViewById(R.id.purchaseReturnOutBill_makeTime_value);
		listView = (PullToRefreshListView) findViewById(R.id.purchaseReturnOutBill_details);
		submitButton = (TextView) findViewById(R.id.purchaseReturnOutBill_submitButton);
		supplierNameValue.setText(supplierName);
		billlCode.setText(relatedBillCode);
		
		makeTimeValue.setValue(purchaseReturnOrderBill.getMakeTime());
		
		
		adapter = new PurchaseReturnOutDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullDownListView(listView);
		
		searchPurchaseReturnOrderDetails();
	}
	
	private void searchPurchaseReturnOrderDetails() {
		details.clear();
		purchaseOrderService.searchPurchaseReturnOrderDetailsByBillId(bill.getRelatedBillId(),
				new AbstractServiceCallBack<List<PurchaseReturnOrderDetailModel>>(PurchaseReturnOutBillActivity.this) {

					@Override
					public void doSuccess(List<PurchaseReturnOrderDetailModel> result) {
						if (result != null && result.size() > 0) {
							List<PurchaseReturnOrderDetailModel> oldData = result;
							List<PurchaseReturnOutDetailModel> data = convertToPurchaseReturnOutDetailModel(oldData);
							details.addAll(data);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						adapter.notifyDataSetInvalidated();
					}
				});
	}
	
	protected List<PurchaseReturnOutDetailModel> convertToPurchaseReturnOutDetailModel(List<PurchaseReturnOrderDetailModel> oldData) {
		List<PurchaseReturnOutDetailModel> list = new ArrayList<PurchaseReturnOutDetailModel>();
		for(PurchaseReturnOrderDetailModel oldDetail:oldData){
			PurchaseReturnOutDetailModel detail = new PurchaseReturnOutDetailModel();
			detail.setId(null);
			detail.setBillCode(null);
			
			
			detail.setSku(oldDetail.getSku());
			detail.setBill(bill);
			detail.setRelatedBillCode(oldDetail.getBillCode());
			detail.setRelatedDetailId(oldDetail.getId());
			detail.setPack(oldDetail.getPack());
			detail.setPackType(oldDetail.getPackType());
			detail.setCooperateType(oldDetail.getCooperateType());
			detail.setProduceDate(oldDetail.getProduceDate());
			detail.setProduceBatchNo(oldDetail.getProduceBatchNo());
			detail.setSysBatchNo(oldDetail.getSysBatchNo());
			detail.setStore(oldDetail.getStore());
			detail.setShelf(oldDetail.getShelf());
			detail.setShelfCode(oldDetail.getShelfCode());
			detail.setUnitName(oldDetail.getUnitName());
			detail.setSpec(oldDetail.getSpec());
			detail.setSpecQty(oldDetail.getSpecQty());
			detail.setStockQty(oldDetail.getStockQty());
			detail.setGoods(oldDetail.getGoods());
			detail.setUnitQty(oldDetail.getUnitQty());
			detail.setMinUnitQty(oldDetail.getMinUnitQty());
			detail.setQty(oldDetail.getQty());
			detail.setExpectUnitQty(oldDetail.getUnitQty());
			detail.setExpectMinUnitQty(oldDetail.getMinUnitQty());
			detail.setQty(oldDetail.getQty());
			detail.setCostPrice(oldDetail.getCostPrice());
			detail.setRelatedBillCode(oldDetail.getBillCode());
			detail.setReceivedUnitQty(oldDetail.getReceivedUnitQty());
			detail.setReceivedMinUnitQty(oldDetail.getReceivedMinUnitQty());
			detail.setPackType(oldDetail.getPackType());		
			detail.setGoodsState(oldDetail.getGoodsState());
			
			list.add(detail);
		}
		
		return list;
	}

	private void submitList() {
		
		PurchaseEnterBillCreateParamsModel createParams = setupPurchaseEnterBillCreateParamsModel(bill,details);
		if (createParams.getDetails().size() == 0) {
			T.showShort(context, "提交的检查记录数不能为空");
			return;
		}
		
		purchaseEnterService.createPurchaseOutBill(createParams, new AbstractServiceCallBack<String>(PurchaseReturnOutBillActivity.this) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				//提交成功删除本地数据库中的存储信息
//				dbManager.deletePurchaseOrderBill();
				finish();
			}
		});
	}
	
	private PurchaseEnterBillCreateParamsModel setupPurchaseEnterBillCreateParamsModel(PurchaseReturnOutBillModel bill,List<PurchaseReturnOutDetailModel> details) {
		
		PurchaseEnterBillCreateParamsModel purchaseEnterBillCreateParamsModel = new PurchaseEnterBillCreateParamsModel();
		
		purchaseEnterBillCreateParamsModel.setDeliverBillNo(bill.getDeliverBillNo());
		purchaseEnterBillCreateParamsModel.setEnterDate(bill.getMakeTime());
		purchaseEnterBillCreateParamsModel.setExaminerId(bill.getExaminer().getId());
		purchaseEnterBillCreateParamsModel.setMakerId(bill.getMaker().getId());
		purchaseEnterBillCreateParamsModel.setRelatedBillId(bill.getRelatedBillId());
		purchaseEnterBillCreateParamsModel.setRelatedBillCode(bill.getRelatedBillCode());
		purchaseEnterBillCreateParamsModel.setRelatedBillTypeId(bill.getRelatedBillType().getId());
		
		List<PurchaseEnterDetailCreateParamsModel> createDetails=new ArrayList<PurchaseEnterDetailCreateParamsModel>();
		for(PurchaseEnterDetailModel detail: details){
			if (detail.isCheckFlag()) {
				PurchaseEnterDetailCreateParamsModel createDetail=new PurchaseEnterDetailCreateParamsModel();
				createDetail.setRelatedBillId(bill.getRelatedBillId());
				createDetail.setRelatedBillTypeId(bill.getRelatedBillType().getId());
				createDetail.setRelatedDetailId(detail.getRelatedDetailId());
				createDetail.setStoreId(detail.getStore().getId());
				createDetail.setShelfId(detail.getShelf().getId());
				createDetail.setShelfCode(detail.getShelf().getCode());
				createDetail.setProduceBatchNo(detail.getProduceBatchNo());
				createDetail.setReceivedUnitQty(detail.getReceivedUnitQty());
				createDetail.setReceivedMinUnitQty(detail.getReceivedMinUnitQty());
				createDetail.setRelatedBillCode(bill.getRelatedBillCode());
				createDetail.setSpecQty(detail.getSpecQty());
				createDetail.setStockQty(detail.getStockQty());
				createDetail.setProduceDate(detail.getProduceDate());
				createDetail.setSkuId(detail.getSku().getId());
				createDetail.setSkuCode(detail.getSku().getCode());
				createDetail.setSpec(detail.getSpec());
				createDetail.setProduceDate(detail.getProduceDate());
				createDetail.setSysBatchNo(detail.getSysBatchNo());
//				createDetail.setGoodsName(detail.getGoods().getName());
//				createDetail.setGoodsCode(detail.getGoods().getCode());
//				createDetail.setGoodsStateId(detail.getGoodsState().getId());
				createDetail.setBarcode(detail.getSku().getBarcode());
//				createDetail.setStoreUseTypeId(detail.getStore().getUseType().getId());
				createDetail.setPackId(detail.getPack().getId());
				createDetail.setPackTypeId(detail.getPackType().getId());
//				createDetail.setManufacturer(detail.getGoods().getManufacturer());
				createDetail.setUnitName(detail.getUnitName());
				
				if (detail.getUnitQty() == null)
					createDetail.setUnitQty(0);
				else
					createDetail.setUnitQty(detail.getUnitQty());
				if (detail.getMinUnitQty() == null)
					createDetail.setMinUnitQty(0);
				else
					createDetail.setMinUnitQty(detail.getMinUnitQty());
				createDetail.setProduceDate(detail.getProduceDate());
				
				int unitQty = createDetail.getUnitQty();
				int minUnitQty = createDetail.getMinUnitQty();
				int qty = QtyMoneyUtils.getQty(unitQty, minUnitQty, detail.getSpecQty());
				if(qty!=0){ 
					createDetails.add(createDetail);
				}
			}
		}
		
		purchaseEnterBillCreateParamsModel.setDetails(createDetails);
		
		return purchaseEnterBillCreateParamsModel;
	}

	/**
	 * 修改item的值
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data) {
			if (resultCode == EmployeeSelectorActivity.RESULT_CODE) {
				EmployeeModel employeeModel = (EmployeeModel) data.getSerializableExtra(EmployeeSelectorActivity.SER_KEY);
				
				bill.setExaminer(employeeModel);
				
				examinerValue.setText(employeeModel.getName());
				
			}else if(resultCode == PurchaseReturnOutDetailEditActivity.RESULT_CODE) {
				
				PurchaseReturnOutDetailModel purchaseReturnOutDetailModel = (PurchaseReturnOutDetailModel) data.getSerializableExtra(PurchaseReturnOutDetailEditActivity.SER_KEY);
				details.set(selectPosition, purchaseReturnOutDetailModel);
				details.get(selectPosition).setCheckFlag(true);
				adapter.notifyDataSetChanged();
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.purchaseReturnOutBill_submitButton:
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_returnbill), true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							submitList();
//							dbManager.deletePurchaseEnterBill();
						}
					});
			break;
		case R.id.pickBillList_examinerName_searchButton:
			Intent intent = new Intent(context,EmployeeSelectorActivity.class);
			intent.putExtra(EmployeeSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.employee_employee_searchPageExaminerByParams);
			startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
			break;
		}
	}
}