package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import java.util.Date;

import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateEditField;
import com.ds365.commons.widget.DateEditField.DateSelectListener;
import com.ds365.commons.widget.DropDownListView;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.sale.SaleReturnEnterDetailModel;
import com.ds365.erp.wms.pda.model.stock.SkuBatchModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.SkuBatchSelectorActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * 退货入库--退货申请单详情-编辑页面
 * @author lxj
 */
public class SaleReturnEnterDetailEditActivity extends BasePdaListActivity implements OnClickListener{
	
	private TextView saveButton;	//保存按钮
	private TextView skuNameValue,skuCodeValue,minUnitNameValue,unitNameValue,shelfIdValue,produceBatchNoValue,sysBatchValue;
	private IntEditField unitQtyValue,minUnitQtyValue;
	private DateEditField produceDateValue;
	private TextView storeNameValue, shelfCodeValue;
	
//	private DataBaseManager dbManager; 
	
	private Intent intent;
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	private SaleReturnEnterDetailModel saleReturnEnterDetailModel = new SaleReturnEnterDetailModel();
	
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_sale_return_enter_detail_edit;
	}
	
	@Override
	protected void initActivityView() {
		intent = getIntent();
		saveButton = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_saveButton);

		storeNameValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_storeName_value);
		shelfIdValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_shelfId_value);
		shelfCodeValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_shelfCode_value);
		produceDateValue = (DateEditField) findViewById(R.id.saleReturnEnterDetailEdit_produceDate_value);
		produceBatchNoValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_produceBatchNo_value);
		sysBatchValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_sysBatchNo_value);
		unitQtyValue = (IntEditField) findViewById(R.id.saleReturnEnterDetailEdit_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.saleReturnEnterDetailEdit_minUnitQty_value);
		minUnitNameValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_minUnitName_value);
		unitNameValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_unitName_value);
		skuCodeValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_skuCode_value);
		skuNameValue = (TextView) findViewById(R.id.saleReturnEnterDetailEdit_skuName_value);
		
		initData();
	}
	
	private void initData() {
		
		saleReturnEnterDetailModel = (SaleReturnEnterDetailModel) intent.getSerializableExtra(SaleReturnEnterBillActivity.SER_KEY);
		
		minUnitQtyValue.setMaxValueAndTextChangeListener(saleReturnEnterDetailModel.getSpecQty(),"散数不能大于包装数量！",null);
		
		/*dbManager = DataBaseManager.getDBManagerInstence(getApplicationContext());
		SaleReturnBillInfo saleReturnBillInfo = dbManager.getSaleReturnBillInfo(relatedBillCode,skuCode);
		if (null != saleReturnBillInfo) {
			storeNameValue.setText(saleReturnBillInfo.getWareHouseName()); //
			shelfCodeValue.setText(saleReturnBillInfo.getShelfName());
			produceDateValue.setText(saleReturnBillInfo.getProduceDate());
			produceBatchNoValue.setText(String.valueOf(saleReturnBillInfo.getProduceBatchNo()));
			sysBatchValue.setText(String.valueOf(saleReturnBillInfo.getProduceBatch()));
			unitQtyValue.setText(String.valueOf(saleReturnBillInfo.getReceivedUnitQty()));
			minUnitQtyValue.setText(String.valueOf(saleReturnBillInfo.getReceivedMinUnitQty()));
		}else{
		}*/
		/*long selectStoreName = 0;
		if(saleReturnEnterDetailModel.getStore()!=null){
			selectStoreName = saleReturnEnterDetailModel.getStore().getId();
		}
		InitStoreData.initStore(context,storeNameValue,selectStoreName);
		if(saleReturnEnterDetailModel.getShelf()!=null)
			shelfCodeValue.setText(saleReturnEnterDetailModel.getShelf().getCode());*/
		
		if(saleReturnEnterDetailModel.getStore()!=null){
			storeNameValue.setText(saleReturnEnterDetailModel.getStore().getName()); 
		}
		if(saleReturnEnterDetailModel.getShelf()!=null)
			shelfCodeValue.setText(saleReturnEnterDetailModel.getShelf().getCode());
		
		
		produceDateValue.setValue(saleReturnEnterDetailModel.getProduceDate());
		produceBatchNoValue.setText(saleReturnEnterDetailModel.getProduceBatchNo());
		sysBatchValue.setText(saleReturnEnterDetailModel.getSysBatchNo());
		unitQtyValue.setValue(saleReturnEnterDetailModel.getUnitQty());
		minUnitQtyValue.setValue(saleReturnEnterDetailModel.getMinUnitQty());
		
		minUnitNameValue.setText(saleReturnEnterDetailModel.getMinUnitName());
		unitNameValue.setText(saleReturnEnterDetailModel.getUnitName());
		skuCodeValue.setText(saleReturnEnterDetailModel.getSku().getCode());
		skuNameValue.setText(saleReturnEnterDetailModel.getSkuName());
		
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.saleReturnEnterDetailEdit_headerview, R.string.sale_return_enter_detail_edit);
	}

	@Override
	protected void setListener() {
		saveButton.setOnClickListener(this);
		/*shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					searchShelf(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){  
                    隐藏软键盘
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchShelf(v);
                    return true;
                }
				return false;
			}
		});*/
		
		/**
		 * 根据生产日期选择退货的商品
		 */
		produceDateValue.setDateSelectListener(new DateSelectListener() {
			
			@Override
			public void onDateSelect(Date dateValue) {
				Intent intent = new Intent(context, SkuBatchSelectorActivity.class);
				//将参数'生产日期'传入;
				intent.putExtra("produceDate", dateValue);
				intent.putExtra("skuId", saleReturnEnterDetailModel.getSku().getId());
				startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
			}
		});
	}
	
	/*private void searchShelf(TextView v){
		ModelUtils.getShelfByShelfCode(v.getText().toString().trim(), new ModelCallBack<ShelfModel>() {

			@Override
			public void onBack(ShelfModel shelf) {
				if (shelf != null) {
					saleReturnEnterDetailModel.setShelf(shelf);
					saleReturnEnterDetailModel.setStore(shelf.getStore());
					
					storeNameValue.setValue(shelf.getStore().getId());
					shelfCodeValue.setText(shelf.getCode());
					shelfIdValue.setText(String.valueOf(shelf.getId()));
				}else{
					T.showShort(context, "找不到此货位信息");
					shelfCodeValue.requestFocus();
				}
			}
		}, context);
	}*/
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == SkuBatchSelectorActivity.RESULT_CODE) {
				SkuBatchModel skuBatchModel = (SkuBatchModel) intent.getSerializableExtra(SkuBatchSelectorActivity.SER_KEY);
				
				String sysBatchNo = skuBatchModel.getSysBatchNo();
				String produceBatchNo = skuBatchModel.getProduceBatchNo();
				Date produceDate = skuBatchModel.getProduceDate();
				
				sysBatchValue.setText(sysBatchNo);
				produceBatchNoValue.setText(produceBatchNo);
				produceDateValue.setValue(produceDate);
				
				saleReturnEnterDetailModel.setProduceBatchNo(produceBatchNo);
				saleReturnEnterDetailModel.setSysBatchNo(sysBatchNo);
				saleReturnEnterDetailModel.setProduceDate(produceDate);
				
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.saleReturnEnterDetailEdit_saveButton:
			StringBuilder message = new StringBuilder();
			
			CharSequence  shelfIdTextValue=shelfIdValue.getText();
			/*if(shelfIdTextValue==null){
				message.append("货位不能为空\r\n");
			}
			String shelfId = shelfIdValue.getText().toString().trim();
			if (StringUtils.isEmptyEditText(shelfCodeValue)  || "".equals(shelfId)) {
				message.append("货位不能为空\r\n");
			}*/
			if(produceDateValue.getValue() == null){
				message.append("请输入生产日期\r\n");
			}
			if(StringUtils.isEmptyEditText(unitQtyValue.getEditText())){
				message.append("请输入件数\r\n");
			}
			if(StringUtils.isEmptyEditText(minUnitQtyValue.getEditText())){
				message.append("请输入散数");
			}
			if (message.length() != 0) {
				T.showShort(context, message);
				return;
			}
			
			
			Integer unitQty = unitQtyValue.getValue();
			Integer minUnitQty = minUnitQtyValue.getValue();
			
			saleReturnEnterDetailModel.setUnitQty(unitQty);
			saleReturnEnterDetailModel.setMinUnitQty(minUnitQty);
			
			Bundle bundle=new Bundle();
			bundle.putSerializable(SER_KEY,saleReturnEnterDetailModel);  
			intent.putExtras(bundle);
	    	setResult(RESULT_CODE, intent);
			
//			将数据存到SaleReturnBill所对应的表的bean当中
//			List<SaleReturnBillInfo>list = new ArrayList<SaleReturnBillInfo>();
//			SaleReturnBillInfo bill = new SaleReturnBillInfo();
//			bill.setSkuCode(StringUtils.isEmptyString(skuCode));
//			bill.setSkuName(StringUtils.isEmptyString(skuName));
//			bill.setPackUnit(StringUtils.isEmptyString(unitName));
//			bill.setMinPack(StringUtils.isEmptyString(minUnitName));
//			bill.setBillCode(StringUtils.isEmptyString(billCode));
//			bill.setShelfName(StringUtils.isEmptyString(shelfCodeValue.getText().toString().trim()));
//			bill.setProduceDate(StringUtils.isEmptyString(produceDateValue.getText().toString().trim()));
//			bill.setProduceBatchNo(StringUtils.isEmptyInt(produceBatchNoValue.getText().toString().trim()));
//			bill.setProduceBatch(StringUtils.isEmptyInt(sysBatchValue.getText().toString().trim()));
//			bill.setReceivedUnitQty(StringUtils.isEmptyInt(unitQtyValue.getText().toString().trim()));
//			bill.setReceivedMinUnitQty(StringUtils.isEmptyInt(minUnitQtyValue.getText().toString().trim()));
//			list.add(bill);
//			
//			dbManager.addSaleReturnBill(list);
			
			T.showShort(context, "已保存");
			finish();
			break;
		}
	}
}
