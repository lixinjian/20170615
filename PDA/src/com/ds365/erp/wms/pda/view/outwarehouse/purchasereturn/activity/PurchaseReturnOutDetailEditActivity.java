package com.ds365.erp.wms.pda.view.outwarehouse.purchasereturn.activity;

import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseReturnOutDetailModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.SkuShelfBatchStockSelectorActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PurchaseReturnOutDetailEditActivity extends BasePdaListActivity implements OnClickListener{
	private TextView skuNameValue,skuCodeValue,minUnitNameValue,unitNameValue;
	private TextView saveButton,shelfCodeValue;
	private IntEditField unitQtyValue,minUnitQtyValue;
	private TextView expectUnitQtyValue,expectMinUnitQtyValue;
	private TextView storeNameValue,storeIdValue;
//	private DataBaseManager dbManager;
	private Intent intent;
	
	private PurchaseReturnOutDetailModel purchaseReturnOutDetailModel = new PurchaseReturnOutDetailModel();
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	@Override
	protected int getContentViewId() {
		return R.layout.outwarehouse_purchase_return_out_detail_edit;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseReturnOutDetailEdit_headerview, R.string.purchase_return_out_detail_edit);
	}

	@Override
	protected void setListener() {
		saveButton.setOnClickListener(this);
		
		//此处货位是带过来的,不需要编辑
		/*shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
//					shelfCodeGetShelfId();
					Intent intent=new Intent(context, ShelfBatchStockSelectActivity.class);
					intent.putExtra("shelfCode",  shelfCodeValue.getText().toString());
//					intent.putExtra("skuId",downModel.getSku().getId());
					startActivityForResult(intent,1);
				}
				return false;
			}
		});*/
	}
	
	@Override
	protected void initActivityView() {
		
		intent = getIntent();
		
		purchaseReturnOutDetailModel = (PurchaseReturnOutDetailModel) intent.getSerializableExtra(PurchaseReturnOutBillActivity.SER_KEY);
		
//		dbManager = DataBaseManager.getDBManagerInstence(getApplicationContext());
		storeNameValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_storeName_value);
		storeIdValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_storeId_value);
		shelfCodeValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_shelfCode_value);
		unitQtyValue = (IntEditField) findViewById(R.id.purchaseReturnOutDetailEdit_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.purchaseReturnOutDetailEdit_minUnitQty_value);
		expectUnitQtyValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_expectUnitQty_value);
		expectMinUnitQtyValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_expectMinUnitQty_value);
		
		saveButton = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_detail_saveButton);
		skuCodeValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_skuCode_value);
		skuNameValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_skuName_value);
		minUnitNameValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_minUnitName_value);
		unitNameValue = (TextView) findViewById(R.id.purchaseReturnOutDetailEdit_unitName_value);
		
//		此处所填的数据为从服务器获取的默认数据,服务器上暂时没有数据,先写死   start
//		PurchaseEnterBillInfo purchaseEnterBillInfo = dbManager.getPurchaseEnterBillInfo(billCode,skuCode);
//		if (null != purchaseEnterBillInfo) {
//			wareHouseNameEdt.setText(purchaseEnterBillInfo.getWareHouseName());
//			shelfNameEdt.setText(purchaseEnterBillInfo.getShelfName());
//			receivedUnitQtyEdt.setText(String.valueOf(purchaseEnterBillInfo.getUnitQty()));
//			receivedMinUnitQtyEdt.setText(String.valueOf(purchaseEnterBillInfo.getMinUnitQty()));
//		}else{
//			wareHouseNameEdt.setText("西北郊粮库");
//			shelfNameEdt.setText("A1-01");
//			receivedUnitQtyEdt.setText("123");
//			receivedMinUnitQtyEdt.setText("1");
//		}
//		end
		
		unitQtyValue.setValue(purchaseReturnOutDetailModel.getUnitQty());
		minUnitQtyValue.setValue(purchaseReturnOutDetailModel.getMinUnitQty());
		
		storeNameValue.setText(purchaseReturnOutDetailModel.getStore().getName());
		shelfCodeValue.setText(purchaseReturnOutDetailModel.getShelf().getCode());
		
		skuCodeValue.setText(purchaseReturnOutDetailModel.getSku().getCode());
		skuNameValue.setText(purchaseReturnOutDetailModel.getSku().getName());
		minUnitNameValue.setText(purchaseReturnOutDetailModel.getMinUnitName());
		unitNameValue.setText(purchaseReturnOutDetailModel.getUnitName());
		expectUnitQtyValue.setText(String.valueOf(purchaseReturnOutDetailModel.getExpectUnitQty()));
		expectMinUnitQtyValue.setText(String.valueOf(purchaseReturnOutDetailModel.getExpectMinUnitQty()));
		minUnitQtyValue.setMaxValueAndTextChangeListener(purchaseReturnOutDetailModel.getSpecQty(),"散数不能大于包装数量！",null);
		
//		storeNameValue.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				InitStoreData.initStore(context,storeNameValue,storeIdValue);
//			}
//		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.purchaseReturnOutDetailEdit_detail_saveButton:
			
			if(StringUtils.isEmptyEditText(unitQtyValue.getEditText())){
				T.showShort(context, "请输入件数!");
				return;
			}else if(StringUtils.isEmptyEditText(minUnitQtyValue.getEditText())){
				T.showShort(context, "请输入散数!");
				return;
			}
			//验证退货数量不能大于可退数量
			Integer qty = QtyMoneyUtils.getQty(unitQtyValue.getValue(), minUnitQtyValue.getValue(), purchaseReturnOutDetailModel.getSpecQty());
			if(qty.intValue()>purchaseReturnOutDetailModel.getQty().intValue()){
				T.showShort(context, "退货总数量不能大于可退数量");
				return;
			}
			
//			修改件数和散数   将件数和散数传回上一页(收货入库订单详情页)展现在listView当中   start
			Integer unitQty = unitQtyValue.getValue();
			Integer minUnitQty = minUnitQtyValue.getValue();
			purchaseReturnOutDetailModel.setUnitQty(unitQty);
			purchaseReturnOutDetailModel.setMinUnitQty(minUnitQty);
	    	
			Bundle bundle=new Bundle();
	    	
	    	bundle.putSerializable(SER_KEY,purchaseReturnOutDetailModel);  
	    	intent.putExtras(bundle);
			
//	    	这里如果用户做了数量修改就把修改的数量传回去,如果没修改则显示默认数量
    		setResult(RESULT_CODE, intent);
//			修改件数和散数   将件数和散数传回上一页(收货入库订单详情页)展现在listView当中   end
			
//			将数据存到SaleReturnBill所对应的表的bean当中
			/*List<PurchaseEnterBillInfo>list = new ArrayList<PurchaseEnterBillInfo>();
			PurchaseEnterBillInfo bill = new PurchaseEnterBillInfo();
			bill.setSkuCode(StringUtils.isEmptyString(skuCode));
			bill.setSkuName(StringUtils.isEmptyString(skuName));
			bill.setPackUnit(StringUtils.isEmptyString(unitName));
			bill.setMinPack(StringUtils.isEmptyString(minUnitName));
			bill.setShelfName(StringUtils.isEmptyString(shelfCodeValue.getText().toString().trim()));
			bill.setUnitQty(StringUtils.isEmptyInt(unitQtyValue.getText().toString().trim()));
			bill.setMinUnitQty(StringUtils.isEmptyInt(minUnitQtyValue.getText().toString().trim()));
			list.add(bill);
			dbManager.addPurchaseEnterBill(list);*/
			
			T.showShort(context, R.string.save_success);
			finish();
			break;
		}
	}
	/*private void shelfCodeGetShelfId(){
		RequestParamsModel params = new RequestParamsModel();
		params.getParams().put("shelfCode", shelfCodeValue.getText().toString().trim());
		params.setUrl(ConstantUrl.SHELF_BY_CODE);
		JsonParser<JsonResult<ShelfModel>> jsonParser = new JsonParser<JsonResult<ShelfModel>>() {};
		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<ShelfModel>>(context, jsonParser) {

			@Override
			public void doSuccess(JsonResult<ShelfModel> result) {
				if ( null != result) {
					shelfCodeValue.setText(result.getData().getCode());
					storeNameValue.setText(result.getData().getStore().getName());
					storeId = result.getData().getStore().getId();
					shelfId = result.getData().getId();
				}else{
					//弹窗提示:
					T.showShort(context, "未找到此架位信息!");
					//将输入框文字清空
					shelfCodeValue.setText("");
					//焦点重新回到架位编辑框
					
				}
			}

			@Override
			public void doFaile(String str) {
				T.showShort(context, str);
			}
		});
	}*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == SkuShelfBatchStockSelectorActivity.RESULT_CODE) {
				SkuShelfBatchStockModel skuShelfBatchStock = (SkuShelfBatchStockModel)intent.getSerializableExtra(SkuShelfBatchStockSelectorActivity.SER_KEY); 
				
				shelfCodeValue.setText(skuShelfBatchStock.getShelf().getCode());
				storeNameValue.setText(skuShelfBatchStock.getShelf().getStore().getName());
				
				purchaseReturnOutDetailModel.setShelf(skuShelfBatchStock.getShelf());
				purchaseReturnOutDetailModel.setStore(skuShelfBatchStock.getShelf().getStore());
				purchaseReturnOutDetailModel.setShelfCode(skuShelfBatchStock.getShelf().getCode());
				
				Integer qty=skuShelfBatchStock.getQty();
				
				QtyModel  qtyModel=QtyMoneyUtils.getQtyEntity(qty, purchaseReturnOutDetailModel.getSpecQty());
				
				unitQtyValue.setValue(qtyModel.getUnitQty());
				minUnitQtyValue.setValue(qtyModel.getMinUnitQty());
				
			}
		}
	}
	
	
}