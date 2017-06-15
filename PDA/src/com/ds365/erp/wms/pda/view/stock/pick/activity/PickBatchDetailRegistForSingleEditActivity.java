package com.ds365.erp.wms.pda.view.stock.pick.activity;

import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.SkuShelfBatchStockSelectorActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class PickBatchDetailRegistForSingleEditActivity extends BasePdaActivity {

	private TextView storeNameValue, sysBatchNoValue, saveButton, qtyValue, shelfIdValue;
	private IntEditField unitQtyValue, minUnitQtyValue;
	private EditText shelfCodeValue;
	private ImageView shelfCodeScanButton;
	
	private PickBatchDetailModel pickBatchDetailModel;
	
	public  final static String SER_KEY = PdaConstants.nextSerKey();  
	public static final int RESULT_CODE =  PdaConstants.nextResultCode();
	
	@Override
	protected void initActivityView() {
		
		pickBatchDetailModel = (PickBatchDetailModel) getIntent()
				.getSerializableExtra(PickBatchDetailRegistForSingleActivity.SER_KEY);
		shelfCodeScanButton = (ImageView) findViewById(R.id.pickBatchDetailRegistForSingleEdit_shelfCodeScan_button);
		shelfIdValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingleEdit_shelfId_value);
		saveButton = (TextView) findViewById(R.id.pickBatchDetailRegistForSingleEdit_save_button);
		shelfCodeValue = (EditText) findViewById(R.id.pickBatchDetailRegistForSingleEdit_shelfCode_value);
		sysBatchNoValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingleEdit_sysBatchNo_value);
		storeNameValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingleEdit_storeName_value);
		qtyValue = (TextView) findViewById(R.id.pickBatchDetailRegistForSingleEdit_qty_value);
		unitQtyValue = (IntEditField) findViewById(R.id.pickBatchDetailRegistForSingleEdit_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.pickBatchDetailRegistForSingleEdit_minUnitQty_value);
		
		minUnitQtyValue.setMaxValueAndTextChangeListener(pickBatchDetailModel.getSpecQty(),"散数不能大于包装数量！",null);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBatchDetailRegistForSingleEdit_headerView, R.string.goods_batch_detail);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_batch_detail_regist_for_single_edit;
	}

	@Override
	protected void setListener() {
		shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				//按回车键调用
				if(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
					searchShelfInfo(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchShelfInfo(v);
                    return true;
                }
				return false;
			}
		});
		
//		用Enter键或软键盘的搜索键进行计算
//		OnEditorActionListener actionListener = new OnEditorActionListener() {
//			
//			@Override
//			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//				
//				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
//					verificationPickQty();
//				}
//				if(actionId == EditorInfo.IME_ACTION_SEARCH){
//                    /*隐藏软键盘*/
//                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    if (imm.isActive()) {
//                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
//                    }
//                    verificationPickQty();
//                    return true;
//                }
//				return false;
//			}
//		};
//		unitQtyValue.setOnEditorActionListener(actionListener);
//		minUnitQtyValue.setOnEditorActionListener(actionListener);
		
		//监测输入框的值,只要有变化就计算
		TextWatcher textWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
				verificationPickQty(editable);
			}
		};
		unitQtyValue.getEditText().addTextChangedListener(textWatcher);
		minUnitQtyValue.getEditText().addTextChangedListener(textWatcher);
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//验证页面上的值是否合法
				StringBuilder message=new StringBuilder();
				
				if("".equals(shelfIdValue.getText().toString().trim())){
					message.append("请输入货位号并选择货位批次库存信息\r\n");
				}
				if (StringUtils.isEmptyEditText(unitQtyValue.getEditText())) {
					message.append("请输入件数\r\n");
				}
				if(StringUtils.isEmptyEditText(minUnitQtyValue.getEditText())){
					message.append("请输入散数\r\n");
				}
				if ("".equals(qtyValue.getText().toString().trim())) {
					message.append("请计算出总数量");
				}
				if ("0".equals(qtyValue.getText().toString().trim())) {
					message.append("拣货数量不能为零");
				}
				if(message.length()!=0){
					T.showShort(context, message.toString());
					return;
				}
//				验证通过后将值传回
				pickBatchDetailModel.setExpectPickQty(Integer.valueOf(qtyValue.getText().toString().trim()));
				Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(SER_KEY,pickBatchDetailModel);  
		        getIntent().putExtras(mBundle);
				setResult(RESULT_CODE, getIntent());
				finish();
			}
		});
		
		shelfCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(PickBatchDetailRegistForSingleEditActivity.this
						, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
	}
	
	/**
	 * 验证拣货总数量是否小于应捡总数量
	 */
	private void verificationPickQty(Editable editable){
		Integer pickUnitQty = calPickUntiQty(editable);
		Integer pickMinUnitQty = calPickMinUintQty(editable);
		Integer pickQty = calPickQty(pickUnitQty, pickMinUnitQty);
		Integer expectPickQty = pickBatchDetailModel.getExpectPickQty();
		if (pickQty.intValue() > expectPickQty.intValue()) {
			T.showShort(context, "拣货总数量已经大于应捡总数量了,请重新输入!");
		}else{
			pickBatchDetailModel.setPickUnitQty(pickUnitQty);
			pickBatchDetailModel.setPickMinUnitQty(pickMinUnitQty);
			pickBatchDetailModel.setPickQty(pickQty);
			qtyValue.setText(String.valueOf(pickQty));
		}
	}
	
	private Integer calPickQty(Integer pickUnitQty, Integer pickMinUnitQty) {
		Integer specQty = pickBatchDetailModel.getSpecQty();
		Integer pickQty = QtyMoneyUtils.getQty(pickUnitQty, pickMinUnitQty, specQty);
		return pickQty;
	}

	private Integer calPickMinUintQty(Editable editable) {
		int pickMinUnitQty=0;
		if(!"".equals(editable.toString())){
			String minUintQtyStr=String.valueOf(minUnitQtyValue.getValue());
			if(StringUtils.isBlank(minUintQtyStr))
				pickMinUnitQty=StringUtils.isEmptyInt(minUintQtyStr);
			pickMinUnitQty = Integer.parseInt(minUintQtyStr);
			return pickMinUnitQty;
		}else{
			T.showShort(context, "请输入数量！");
			return pickMinUnitQty;
		}
	}

	private Integer calPickUntiQty(Editable editable) {
		int pickUnitQty=0;
		if(!"".equals(editable.toString())){
			String unitQtyStr=String.valueOf(unitQtyValue.getValue());
			if(StringUtils.isBlank(unitQtyStr))
				pickUnitQty=StringUtils.isEmptyInt(unitQtyStr);
			pickUnitQty = Integer.parseInt(unitQtyStr);
			return pickUnitQty;
		}else{
			T.showShort(context, "请输入数量！");
			return pickUnitQty;
		}
	}
	
	/**
	 * 获取货位批次库存信息
	 */
	private void searchShelfInfo(TextView v){
		Intent intent=new Intent(context, SkuShelfBatchStockSelectorActivity.class);
		intent.putExtra("shelfCode", v.getText().toString());
		intent.putExtra("skuId",pickBatchDetailModel.getSku().getId());
		intent.putExtra(SkuShelfBatchStockSelectorActivity.SELECTOR_URL_KEY, ConstantUrl.skuStock_skuStock_searchPageSkuShelfBatchStockForNormal);
		startActivityForResult(intent,PdaConstants.PDA_REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (data != null) {
			if (resultCode == SkuShelfBatchStockSelectorActivity.RESULT_CODE) {
				SkuShelfBatchStockModel skuShelfBatchStock = (SkuShelfBatchStockModel)data.getSerializableExtra(SkuShelfBatchStockSelectorActivity.SER_KEY); 
				
				pickBatchDetailModel.setShelf(skuShelfBatchStock.getShelf());
				pickBatchDetailModel.setStore(skuShelfBatchStock.getStore());
				pickBatchDetailModel.setSysBatchNo(skuShelfBatchStock.getSysBatchNo());
				
				shelfCodeValue.setText(skuShelfBatchStock.getShelf().getCode());
				storeNameValue.setText(skuShelfBatchStock.getStore().getName());
				sysBatchNoValue.setText(skuShelfBatchStock.getSysBatchNo());
				
				shelfIdValue.setText(String.valueOf(skuShelfBatchStock.getShelf().getId()));
			}else if (resultCode == CodeScanActivity.RESULT_CODE) {
				shelfCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
}
