package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskModel;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskRecordModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayRecordCreateParamsModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskCreateParamsModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutAwayTaskRecordAdapter;
import com.ds365.erp.wms.pda.view.stock.putaway.fragment.PutawayTaskListFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
/**
 * 
 * @author LiXinjian
 *	上架业务  - 创建上架任务单编辑页面
 */
public class PutAwayTaskListByBillCodeActivity extends BasePdaListActivity implements OnClickListener{
	private TextView submitButton;
	private TextView skuNameValue, specValue, skuCodeValue , sysBatchNoValue, totalUnitQtyValue/*总件数*/,totalMinUnitQtyValue;
	private TextView remainUnitQtyValue /*上架剩余件数*/, remainMinUnitQtyValue /*上架剩余散数*/;
	private TextView unitQtyValue /*本次上架件数*/, minUnitQtyValue /*本次上架散数*/;
	private TextView unitNameValue;
	private DateField produceDateValue;
	private TextView addButton;
	private PullToRefreshListView listView;
	
	private PutAwayTaskModel putAwayTaskModel;
	private List<PutAwayTaskRecordModel> list = new ArrayList<PutAwayTaskRecordModel>();
	private PutAwayTaskRecordAdapter adapter;
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	public static final String ON_ITEM_CLICK_REQUEST = "OnItemClick";
	public static final String ON_ADD_BUTTON_CLICK_REQUEST = "OnAddButtonClick";
	private int itemRequestCode = 1;
	private int addRequestCode = 2;
	private int selectPosition = 0;
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private PutawayTaskService putawayTaskService = new PutawayTaskService();
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_by_billcode;
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawayTaskCreate_headerview, R.string.title_putaway);
	}

	@Override
	protected void setListener() {
		submitButton.setOnClickListener(this);
		addButton.setOnClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context, PutAwayTaskRecordEditActivity.class);
				selectPosition = position-1;
				Bundle bundle = new Bundle();  
				list.get(selectPosition).setPutawayTask(putAwayTaskModel);
		        bundle.putSerializable(SER_KEY,list.get(selectPosition));  
		        bundle.putString(ON_ITEM_CLICK_REQUEST, REQUEST_CODE);
		        intent.putExtras(bundle);
		        
				startActivityForResult(intent, itemRequestCode);
			}
		});
	}
	
	@Override
	protected void initActivityView() {
		unitQtyValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_unitQty_value);
		minUnitQtyValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_minUnitQty_value);
		listView = (PullToRefreshListView) findViewById(R.id.putawayTaskByBillCodeEdit_listView);
		addButton = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_addButton);
		unitNameValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_unitName_value);
		submitButton = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_submitButton);
		skuNameValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_skuName_Value);
		specValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_spec_value);
		skuCodeValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_skuCode_value);
		sysBatchNoValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_sysBatchNo_value);
		totalUnitQtyValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_totalUnitQty_value);
		totalMinUnitQtyValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_totalMinUnitQty_value);
		remainUnitQtyValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_remainUnitQty_value);
		remainMinUnitQtyValue = (TextView) findViewById(R.id.putawayTaskByBillCodeEdit_remainMinUnitQty_value);
		produceDateValue = (DateField) findViewById(R.id.putawayTaskByBillCodeEdit_produceDate_value);
		
		putAwayTaskModel = (PutAwayTaskModel) getIntent().getSerializableExtra(PutawayTaskListFragment.SER_KEY);
		
		skuNameValue.setText(putAwayTaskModel.getSku().getName());
		skuCodeValue.setText(putAwayTaskModel.getSku().getCode());
		specValue.setText(putAwayTaskModel.getSpec());
		sysBatchNoValue.setText(putAwayTaskModel.getSysBatchNo());
		totalUnitQtyValue.setText(String.valueOf(putAwayTaskModel.getUnitQty()));
		totalMinUnitQtyValue.setText(String.valueOf(putAwayTaskModel.getMinUnitQty()));
		remainUnitQtyValue.setText(String.valueOf(putAwayTaskModel.getRemainUnitQty()));
		remainMinUnitQtyValue.setText(String.valueOf(putAwayTaskModel.getRemainMinUnitQty()));
		produceDateValue.setValue(putAwayTaskModel.getProduceDate());
		unitNameValue.setText(putAwayTaskModel.getUnitName());
		
		QtyModel qtyModel=QtyMoneyUtils.getQtyEntity(putAwayTaskModel.getQty(), putAwayTaskModel.getSpecQty());
		totalUnitQtyValue.setText(String.valueOf(qtyModel.getUnitQty()));
		totalMinUnitQtyValue.setText(String.valueOf(qtyModel.getMinUnitQty()));
		putAwayTaskModel.setUnitQty(qtyModel.getUnitQty());
		putAwayTaskModel.setMinUnitQty(qtyModel.getMinUnitQty());
		adapter = new PutAwayTaskRecordAdapter(context, list);
		listView.setAdapter(adapter);
	}
	
//	/**
//	 * 根据扫出的架位号向后台发起请求,得到库区的名称和id,做提交用
//	 */
//	private void shelfCodeGetStoreName(){
//		RequestParamsModel params = new RequestParamsModel();
//		JsonParser<JsonResult<List<ShelfModel>>> jsonParser = new JsonParser<JsonResult<List<ShelfModel>>>() {};
//		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<ShelfModel>>>(context, jsonParser) {
//
//			@Override
//			public void doSuccess(JsonResult<List<ShelfModel>> result) {
//				
//				storeName = result.getData().get(0).getStore().getName();
//				storeId = result.getData().get(0).getStore().getId();
//				shelfId = result.getData().get(0).getId();
//				shelfCode = result.getData().get(0).getName();
//				//扫描获取的架位编码和库区名称填充到页面中
//				shelfCodeValue.setText(shelfCode);
//				storeNameValue.setText(storeName);
//			}
//
//			@Override
//			public void doFaile(String str) {
//				T.showShort(context, str);
//			}
//		});
//	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.putawayTaskByBillCodeEdit_submitButton:
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_putawaytaskbill), true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							submitList();
						}
					});
			break;
		case R.id.putawayTaskByBillCodeEdit_addButton:
			Intent intent = new Intent(context, PutAwayTaskRecordEditActivity.class);
			
			Bundle bundle = new Bundle();  
	        bundle.putSerializable(SER_KEY,putAwayTaskModel);  
	        bundle.putString(ON_ADD_BUTTON_CLICK_REQUEST, REQUEST_CODE);
	        intent.putExtras(bundle);
			startActivityForResult(intent, addRequestCode);
			break;
		}
	}
	
	/**
	 * 计算本次上架件数和本次上架散数并将值设置到页面上
	 */
	
	private  void setViewForUnitQtyAndMinUnitQty(){
		int unitQty = 0;
		int minUnitQty = 0;
		
		for(PutAwayTaskRecordModel recordModel :list){
			unitQty += recordModel.getUnitQty();
			minUnitQty += recordModel.getMinUnitQty();
		}
		unitQtyValue.setText(String.valueOf(unitQty));
		minUnitQtyValue.setText(String.valueOf(minUnitQty));
	}
	
	/**
	 * 计算剩余上架件数和剩余上架散数并将值设置到页面上
	 */
	private  void setViewForRemainUnitQtyAndMinUnitQty(){
		int remainUnitQty = putAwayTaskModel.getUnitQty();
		int remainMinUnitQty = putAwayTaskModel.getMinUnitQty();
		
		for(PutAwayTaskRecordModel recordModel :list){
			remainUnitQty -= recordModel.getUnitQty();
			remainMinUnitQty -= recordModel.getMinUnitQty();
		}
		
		int remainQty = QtyMoneyUtils.getQty(remainUnitQty, remainMinUnitQty, putAwayTaskModel.getSpecQty());
		
		putAwayTaskModel.setRemainQty(remainQty);
		putAwayTaskModel.setRemainUnitQty(remainUnitQty);
		putAwayTaskModel.setRemainMinUnitQty(remainMinUnitQty);
		
		remainUnitQtyValue.setText(String.valueOf(remainUnitQty));
		remainMinUnitQtyValue.setText(String.valueOf(remainMinUnitQty));
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (intent != null) {
			if (requestCode == addRequestCode && resultCode == PutAwayTaskRecordEditActivity.RESULT_CODE) {
				PutAwayTaskRecordModel putAwayTaskRecordModel = (PutAwayTaskRecordModel) intent.getSerializableExtra(PutAwayTaskRecordEditActivity.SER_KEY);
				list.add(putAwayTaskRecordModel); 
				
				setViewForUnitQtyAndMinUnitQty();
				setViewForRemainUnitQtyAndMinUnitQty();
				
				adapter.notifyDataSetChanged();
			}else if (requestCode == itemRequestCode && resultCode == PutAwayTaskRecordEditActivity.RESULT_CODE) {
				PutAwayTaskRecordModel putAwayTaskRecordModel = (PutAwayTaskRecordModel) intent.getSerializableExtra(PutAwayTaskRecordEditActivity.SER_KEY);
				list.set(selectPosition, putAwayTaskRecordModel);
				
				setViewForUnitQtyAndMinUnitQty();
				setViewForRemainUnitQtyAndMinUnitQty();
				
				adapter.notifyDataSetChanged();
			}
		}
	}

	private void submitList() {
		PutawayTaskCreateParamsModel createParams = setupPutawayTaskCreateParamsModel();
		if(createParams.getRecords().size()==0){
			T.showShort(context, "提交的检查记录数不能为空");
			return;
		}
		putawayTaskService.createPutawayTask(createParams, new AbstractServiceCallBack<String>(context) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				finish();
			}
		});
	}
	
	
	private PutawayTaskCreateParamsModel setupPutawayTaskCreateParamsModel() {
		
		PutawayTaskCreateParamsModel putawayTaskCreateParamsModel = new PutawayTaskCreateParamsModel(); //putAwayTaskModel做转换
		putawayTaskCreateParamsModel.setId(putAwayTaskModel.getId());
		
		putawayTaskCreateParamsModel.setMinUnitQty(putAwayTaskModel.getMinUnitQty());
		putawayTaskCreateParamsModel.setUnitQty(putAwayTaskModel.getUnitQty());
		putawayTaskCreateParamsModel.setQty(putAwayTaskModel.getQty());
		putawayTaskCreateParamsModel.setPutawayTypeId(putAwayTaskModel.getPutawayType().getId());
		
		putawayTaskCreateParamsModel.setRelatedBillTypeId(putAwayTaskModel.getRelativeBillType().getId());
		putawayTaskCreateParamsModel.setRelativeBillId(putAwayTaskModel.getRelativeBillId());
		putawayTaskCreateParamsModel.setRelativeDetailId(putAwayTaskModel.getRelativeDetailId());
		putawayTaskCreateParamsModel.setShelfCode(putAwayTaskModel.getShelf().getCode());
		putawayTaskCreateParamsModel.setShelfId(putAwayTaskModel.getShelf().getId());
		putawayTaskCreateParamsModel.setSkuId(putAwayTaskModel.getSku().getId());
		putawayTaskCreateParamsModel.setStoreId(putAwayTaskModel.getStore().getId());
		putawayTaskCreateParamsModel.setSysBatchNo(putAwayTaskModel.getSysBatchNo());
		
		
		List<PutawayRecordCreateParamsModel> createDetails = new ArrayList<PutawayRecordCreateParamsModel>();
		for (PutAwayTaskRecordModel detail: list) {
			PutawayRecordCreateParamsModel createDetail = new PutawayRecordCreateParamsModel();
			createDetail.setId(detail.getId());
			createDetail.setShelfId(detail.getShelf().getId());
			createDetail.setStoreId(detail.getStore().getId());
			createDetail.setUnitQty(detail.getUnitQty());
			createDetail.setMinUnitQty(detail.getMinUnitQty());
			createDetail.setSkuId(detail.getPutawayTask().getSku().getId());
			createDetail.setBarcode(detail.getPutawayTask().getSku().getBarcode());
			createDetail.setPackId(detail.getPutawayTask().getPack().getId());
			createDetail.setSpecQty(detail.getSpecQty());
			createDetail.setSysBatchNo(detail.getSysBatchNo());
			createDetail.setUnitName(detail.getUnitName());
			createDetail.setPutawayTaskId(detail.getPutawayTask().getId());
			
			createDetails.add(createDetail);
		}
		
		putawayTaskCreateParamsModel.setRecords(createDetails);
		
		return putawayTaskCreateParamsModel;
	}
}
