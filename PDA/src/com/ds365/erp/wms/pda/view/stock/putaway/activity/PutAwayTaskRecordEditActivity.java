package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskModel;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskRecordModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class PutAwayTaskRecordEditActivity extends BasePdaActivity {
	
	private TextView saveButton;
	private TextView skuNameValue, specValue, skuCodeValue , sysBatchNoValue, totalUnitQtyValue/*总件数*/,totalMinUnitQtyValue;
	private TextView remainUnitQtyValue /*上架剩余件数*/, remainMinUnitQtyValue /*上架剩余散数*/;
	private TextView unitNameValue,storeNameValue;
	private DateField produceDateValue;
	private EditText shelfCodeValue;
	private IntEditField unitQtyValue,minUnitQtyValue;
	
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	public static final String SER_KEY = PdaConstants.nextSerKey();

	private PutAwayTaskRecordModel putAwayTaskRecordModel = new PutAwayTaskRecordModel();
	private PutAwayTaskModel putAwayTaskModel;
	
	private ShelfService shelfService = new ShelfService();

	@Override
	protected void initActivityView() {

		unitNameValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_unitName_value);
		saveButton = (TextView) findViewById(R.id.putawaytaskRecordEdit_saveButton);
		shelfCodeValue = (EditText) findViewById(R.id.putawaytaskRecordEdit_shelfCode_value);
		unitQtyValue = (IntEditField) findViewById(R.id.putawaytaskRecordEdit_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.putawaytaskRecordEdit_minUnitQty_value);
		storeNameValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_storeName_value);
		skuNameValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_skuName_Value);
		specValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_spec_value);
		skuCodeValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_skuCode_value);
		sysBatchNoValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_sysBatchNo_value);
		totalUnitQtyValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_totalUnitQty_value);
		totalMinUnitQtyValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_totalMinUnitQty_value);
		remainUnitQtyValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_remainUnitQty_value);
		remainMinUnitQtyValue = (TextView) findViewById(R.id.putawaytaskRecordEdit_remainMinUnitQty_value);
		produceDateValue = (DateField) findViewById(R.id.putawaytaskRecordEdit_produceDate_value);
		
		if (PutAwayTaskListByBillCodeActivity.REQUEST_CODE.equals(getIntent().getExtras().getString(PutAwayTaskListByBillCodeActivity.ON_ADD_BUTTON_CLICK_REQUEST))) {
			putAwayTaskModel = (PutAwayTaskModel) getIntent().getSerializableExtra(PutAwayTaskListByBillCodeActivity.SER_KEY);
			
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
			unitQtyValue.setValue(putAwayTaskModel.getRemainUnitQty());
			minUnitQtyValue.setValue(putAwayTaskModel.getRemainMinUnitQty());
			
		}else{
			putAwayTaskRecordModel = (PutAwayTaskRecordModel) getIntent().getSerializableExtra(PutAwayTaskListByBillCodeActivity.SER_KEY);
			
			skuNameValue.setText(putAwayTaskRecordModel.getPutawayTask().getSku().getName());
			skuCodeValue.setText(putAwayTaskRecordModel.getPutawayTask().getSku().getCode());
			specValue.setText(putAwayTaskRecordModel.getPutawayTask().getSpec());
			sysBatchNoValue.setText(putAwayTaskRecordModel.getPutawayTask().getSysBatchNo());
			totalUnitQtyValue.setText(String.valueOf(putAwayTaskRecordModel.getPutawayTask().getUnitQty()));
			totalMinUnitQtyValue.setText(String.valueOf(putAwayTaskRecordModel.getPutawayTask().getMinUnitQty()));
			remainUnitQtyValue.setText(String.valueOf(putAwayTaskRecordModel.getPutawayTask().getRemainUnitQty()));
			remainMinUnitQtyValue.setText(String.valueOf(putAwayTaskRecordModel.getPutawayTask().getRemainMinUnitQty()));
			produceDateValue.setValue(putAwayTaskRecordModel.getPutawayTask().getProduceDate());
			unitNameValue.setText(putAwayTaskRecordModel.getPutawayTask().getUnitName());
			
			unitQtyValue.setValue(putAwayTaskRecordModel.getUnitQty());
			minUnitQtyValue.setValue(putAwayTaskRecordModel.getMinUnitQty());
			shelfCodeValue.setText(putAwayTaskRecordModel.getShelf().getCode());
			storeNameValue.setText(putAwayTaskRecordModel.getStore().getName());
			
		
		}
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawaytaskRecordEdit_headerview, R.string.putaway);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_record_edit;
	}

	/**
	 * 验证上架的总数量不能超过剩余上架总数量
	 */
	private void verificationQty(){
		
		int remianQty = 0;
		int specQty = 0;
		
		int unitQty = unitQtyValue.getValue();
		int minUnitQty = minUnitQtyValue.getValue();
		
		if (putAwayTaskModel != null) {
			remianQty = putAwayTaskModel.getRemainQty();
			specQty = putAwayTaskModel.getSpecQty();
		}else{
			remianQty = putAwayTaskRecordModel.getPutawayTask().getRemainQty();
			specQty = putAwayTaskRecordModel.getPutawayTask().getSpecQty();
		}
		int qty = QtyMoneyUtils.getQty(unitQty, minUnitQty, specQty);
		
		if (qty > remianQty) {
			T.showShort(context, "上架的数量不能超过剩余上架数量");
			return;
		}
		
	}
	
	@Override
	protected void setListener() {
		
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
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				StringBuilder message = new StringBuilder();
				
				if (StringUtils.isEmptyEditText(shelfCodeValue) || putAwayTaskRecordModel.getShelf() == null) { 
					message.append("货位不能为空\r\n");
				}
				if (StringUtils.isEmptyEditText(unitQtyValue.getEditText())) {
					message.append("请输入件数\r\n");
				}
				if (StringUtils.isEmptyEditText(minUnitQtyValue.getEditText())) {
					message.append("请输入散数");
				}
				if (message.length() != 0) {
					T.showShort(context, message);
					return;
				}
				verificationQty();
				putAwayTaskRecordModel.setUnitQty(unitQtyValue.getValue());
				putAwayTaskRecordModel.setMinUnitQty(minUnitQtyValue.getValue());
				
				putAwayTaskRecordModel.setPutawayTask(putAwayTaskModel);
				
				Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(SER_KEY,putAwayTaskRecordModel);  
		        getIntent().putExtras(mBundle);
		        setResult(RESULT_CODE, getIntent());
				finish();
			}
		});
	}
	
	private void searchShelf(TextView v){
		
		String shelfCode = v.getText().toString().trim();

		shelfService.getShelfByCode(shelfCode, new AbstractServiceCallBack<ShelfModel>(context) {

			@Override
			public void doSuccess(ShelfModel shelf) {
				if (shelf !=null) {
					putAwayTaskRecordModel.setShelf(shelf);
					putAwayTaskRecordModel.setStore(shelf.getStore());
					putAwayTaskRecordModel.setShelfCode(shelf.getCode());
					storeNameValue.setText(shelf.getStore().getName());
					shelfCodeValue.setText(shelf.getCode());
				}else{
					T.showShort(context, "找不到此货位信息");
					shelfCodeValue.requestFocus();
				}
			}
		});
	}
	
}
