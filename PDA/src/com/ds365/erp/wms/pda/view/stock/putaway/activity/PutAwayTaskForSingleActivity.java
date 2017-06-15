package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskModel;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskRecordModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayRecordCreateParamsModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskCreateParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskService;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.fragment.PutawayTaskListFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
/**
 *	上架任务
 */
public class PutAwayTaskForSingleActivity extends BasePdaListActivity implements OnClickListener{
	private TextView submitButton;
	private TextView skuNameValue, specValue, skuCodeValue , sysBatchNoValue, totalUnitQtyValue/*总件数*/,totalMinUnitQtyValue;
	private TextView remainUnitQtyValue /*上架剩余件数*/, remainMinUnitQtyValue /*上架剩余散数*/;
	private IntEditField unitQtyValue /*本次上架件数*/, minUnitQtyValue /*本次上架散数*/;
	private TextView unitNameValue;
	private DateField produceDateValue;
	private EditText shelfCodeValue;
	private TextView storeNameValue;
	private TextView relativeBillCode;
	private ImageView shelfCodeScanButton;
	
	private PutAwayTaskModel putAwayTaskModel;
	private PutAwayTaskRecordModel record = new PutAwayTaskRecordModel();
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	public static final String ON_ITEM_CLICK_REQUEST = "OnItemClick";
	public static final String ON_ADD_BUTTON_CLICK_REQUEST = "OnAddButtonClick";
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private PutawayTaskService putawayTaskService = new PutawayTaskService();
	private ShelfService shelfService = new ShelfService();

	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_for_single;
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawayTaskForSingle_headerview, R.string.title_putaway);
	}

	@Override
	protected void setListener() {
		submitButton.setOnClickListener(this);
		shelfCodeScanButton.setOnClickListener(this);
		
		shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					searchShelf(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchShelf(v);
                    return true;
                }
				return false;
			}
		});
		
		
	}
	private void searchShelf(TextView v){
		
		String shelfCode = v.getText().toString().trim();

		shelfService.getShelfByCode(shelfCode, new AbstractServiceCallBack<ShelfModel>(context) {

			@Override
			public void doSuccess(ShelfModel shelf) {
				if (shelf !=null) {
					record.setShelf(shelf);
					record.setStore(shelf.getStore());
					record.setShelfCode(shelf.getCode());
					storeNameValue.setText(shelf.getStore().getName());
					shelfCodeValue.setText(shelf.getCode());
				}else{
					T.showShort(context, "找不到此货位信息");
					shelfCodeValue.requestFocus();
				}
			}
		});
		
		
	}
	
	@Override
	protected void initActivityView() {
		relativeBillCode = (TextView) findViewById(R.id.putawayTaskForSingle_relativeBillCode_Value);
		shelfCodeScanButton = (ImageView) findViewById(R.id.putawayTaskForSingle_shelfCodeScan_button);
		storeNameValue = (TextView) findViewById(R.id.putawayTaskForSingle_storeName_value);
		shelfCodeValue = (EditText) findViewById(R.id.putawayTaskForSingle_shelfCode_value);
		unitQtyValue = (IntEditField) findViewById(R.id.putawayTaskForSingle_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.putawayTaskForSingle_minUnitQty_value);
		unitNameValue = (TextView) findViewById(R.id.putawayTaskForSingle_unitName_value);
		submitButton = (TextView) findViewById(R.id.putawayTaskForSingle_submitButton);
		skuNameValue = (TextView) findViewById(R.id.putawayTaskForSingle_skuName_Value);
		specValue = (TextView) findViewById(R.id.putawayTaskForSingle_spec_value);
		skuCodeValue = (TextView) findViewById(R.id.putawayTaskForSingle_skuCode_value);
		sysBatchNoValue = (TextView) findViewById(R.id.putawayTaskForSingle_sysBatchNo_value);
		totalUnitQtyValue = (TextView) findViewById(R.id.putawayTaskForSingle_totalUnitQty_value);
		totalMinUnitQtyValue = (TextView) findViewById(R.id.putawayTaskForSingle_totalMinUnitQty_value);
		remainUnitQtyValue = (TextView) findViewById(R.id.putawayTaskForSingle_remainUnitQty_value);
		remainMinUnitQtyValue = (TextView) findViewById(R.id.putawayTaskForSingle_remainMinUnitQty_value);
		produceDateValue = (DateField) findViewById(R.id.putawayTaskForSingle_produceDate_value);
		
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
		relativeBillCode.setText(putAwayTaskModel.getRelativeBillCode());
		QtyModel qtyModel=QtyMoneyUtils.getQtyEntity(putAwayTaskModel.getQty(), putAwayTaskModel.getSpecQty());
		totalUnitQtyValue.setText(String.valueOf(qtyModel.getUnitQty()));
		totalMinUnitQtyValue.setText(String.valueOf(qtyModel.getMinUnitQty()));
		putAwayTaskModel.setUnitQty(qtyModel.getUnitQty());
		putAwayTaskModel.setMinUnitQty(qtyModel.getMinUnitQty());
		
		unitQtyValue.setValue(putAwayTaskModel.getRemainUnitQty());
		minUnitQtyValue.setValue(putAwayTaskModel.getRemainMinUnitQty());
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.putawayTaskForSingle_submitButton:
			//如果用户没有输入上架的货位信息及数量则在此提示
			StringBuilder message=new StringBuilder();
			if (record.getShelf() == null) {
				message.append("请输入货位码\r\n");
			}
			if (StringUtils.isEmptyEditText(unitQtyValue.getEditText())) {
				message.append("请输入件数\r\n");
			}
			if(StringUtils.isEmptyEditText(minUnitQtyValue.getEditText())){
				message.append("请输入散数");
			}
			if(message.length()!=0){
				T.showShort(context, message.toString());
				return;
			}
//			验证上架数量不能大于剩余上架数量
			Integer qty=QtyMoneyUtils.getQty(unitQtyValue.getValue(), minUnitQtyValue.getValue(), putAwayTaskModel.getSpecQty());
			if (qty>putAwayTaskModel.getRemainQty()) {
				T.showShort(context, "上架数量不能大于剩余上架数量");
				return;
			}else{
				record.setUnitQty(unitQtyValue.getValue());
				record.setMinUnitQty(minUnitQtyValue.getValue());
			}
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_putawaytaskbill), true, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							record.setPutawayTask(putAwayTaskModel);
							submitList();
						}
					});
			break;
			
		case R.id.putawayTaskForSingle_shelfCodeScan_button:
			IntentUtils.startActivityForResult(PutAwayTaskForSingleActivity.this
					, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
			break;
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
		
		PutawayTaskCreateParamsModel putawayTaskCreateParamsModel = new PutawayTaskCreateParamsModel();
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
			PutawayRecordCreateParamsModel createDetail = new PutawayRecordCreateParamsModel();
			createDetail.setShelfId(record.getShelf().getId());
			createDetail.setStoreId(record.getStore().getId());
			createDetail.setUnitQty(record.getUnitQty());
			createDetail.setMinUnitQty(record.getMinUnitQty());
			createDetail.setSkuId(record.getPutawayTask().getSku().getId());
			createDetail.setBarcode(record.getPutawayTask().getSku().getBarcode());
			createDetail.setPackId(record.getPutawayTask().getPack().getId());
			createDetail.setSpecQty(record.getPutawayTask().getSpecQty());
			createDetail.setSysBatchNo(record.getPutawayTask().getSysBatchNo());
			createDetail.setUnitName(record.getPutawayTask().getUnitName());
			createDetail.setPutawayTaskId(record.getPutawayTask().getId());
			createDetails.add(createDetail);
		putawayTaskCreateParamsModel.setRecords(createDetails);
		return putawayTaskCreateParamsModel;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (resultCode == CodeScanActivity.RESULT_CODE) {
				shelfCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
}
