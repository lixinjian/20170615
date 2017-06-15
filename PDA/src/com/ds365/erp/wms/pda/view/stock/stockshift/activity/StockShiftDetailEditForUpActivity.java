package com.ds365.erp.wms.pda.view.stock.stockshift.activity;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftDetailModel;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class StockShiftDetailEditForUpActivity extends BasePdaListActivity {

	private TextView saveButton;
	
	private TextView unitNameValue; //单位
	private TextView skuCodeValue;
	private TextView skuNameValue;
	private TextView specValue;	//规格
	
	private EditText shelfCodeValue;
	private EditText storeNameValue;//库区
	private IntEditField unitQtyValue;
	private IntEditField minUnitQtyValue;
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	private StockShiftDetailModel upModel=new StockShiftDetailModel(); 
	private SkuShelfBatchStockModel skuShelfBatchStockModel;
	public static final  String SER_KEY=PdaConstants.nextSerKey();
	
	private ShelfService shelfService = new ShelfService();
	
	@Override
	protected void initActivityView() {
		saveButton = (TextView) findViewById(R.id.stockShiftDetailForUp_saveButton);
		unitNameValue = (TextView) findViewById(R.id.stockShiftDetailEditForUp_unitName_value);
		skuCodeValue = (TextView) findViewById(R.id.stockShiftDetailEditForUp_skuCode_value);
		skuNameValue = (TextView) findViewById(R.id.stockShiftDetailEditForUp_skuName_value);
		specValue = (TextView) findViewById(R.id.stockShiftDetailEditForUp_spec_value);
		
		shelfCodeValue = (EditText) findViewById(R.id.stockShiftDetailEditForUp_shelfCode_value);
		storeNameValue = (EditText) findViewById(R.id.stockShiftDetailEditForUp_shelf_value);
		unitQtyValue = (IntEditField) findViewById(R.id.stockShiftDetailEditForUp_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.stockShiftDetailEditForUp_minUnitQty_value);
		
		if (StockShiftBillActivity.REQUEST_CODE.equals(getIntent().getExtras().getString(StockShiftBillActivity.ON_ADD_BUTTON_CLICK_REQUEST))) {
			skuShelfBatchStockModel = (SkuShelfBatchStockModel) getIntent().getSerializableExtra(StockShiftBillActivity.SER_KEY);
			
			skuCodeValue.setText(skuShelfBatchStockModel.getSku().getCode());
			skuNameValue.setText(skuShelfBatchStockModel.getSku().getName());
			specValue.setText(skuShelfBatchStockModel.getSku().getSpec());
			unitNameValue.setText(skuShelfBatchStockModel.getSku().getGoodsPack().getUnitName());
		}else{
			StockShiftDetailModel stockShiftDetailModel = (StockShiftDetailModel) getIntent().getSerializableExtra(StockShiftBillActivity.SER_KEY);
			stockShiftDetailModel = (StockShiftDetailModel)getIntent().getSerializableExtra(StockShiftBillActivity.SER_KEY);
			skuCodeValue.setText(stockShiftDetailModel.getSku().getCode());
			skuNameValue.setText(stockShiftDetailModel.getSku().getName());
			specValue.setText(stockShiftDetailModel.getSku().getSpec());
			unitNameValue.setText(stockShiftDetailModel.getSku().getGoodsPack().getUnitName());
			upModel.setSku(stockShiftDetailModel.getSku());
		}
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.storeShift_headerview, R.string.transferStore);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_stock_shift_detail_edit_for_up;
	}

	@Override
	protected void setListener() {
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (StringUtils.isEmptyEditText(shelfCodeValue)) {
					T.showShort(context, "货位不能为空!");
					return;
				}else if (StringUtils.isEmptyEditText(storeNameValue)) {
					T.showShort(context, "库区不能为空!");
					return;
				}else if(StringUtils.isEmptyEditText(unitQtyValue.getEditText())){
					T.showShort(context, "请输入件数!");
					return;
				}else if(StringUtils.isEmptyEditText(minUnitQtyValue.getEditText())){
					T.showShort(context, "请输入散数!");
					return;
				}
				
//				验证上架数量不能超过可用数量
				int qty = QtyMoneyUtils.getQty(unitQtyValue.getValue(), minUnitQtyValue.getValue(), upModel.getSku().getGoodsPack().getSpecQty());
				if (qty > skuShelfBatchStockModel.getSkuShelfBatchDynamicStock().getUsableQty()) {
					T.showLong(context, "您输入的数量已经超出了可用数量,请重新输入!");
					return;
				}
				
				//upModel.setEnterShelf(enterShelf);
				Integer minUnitQty= minUnitQtyValue.getValue();
				Integer unitQty=  unitQtyValue.getValue();
				upModel.setUnitQty(unitQty);
				upModel.setMinUnitQty(minUnitQty);
				upModel.setQty(QtyMoneyUtils.getQty(unitQty, minUnitQty, skuShelfBatchStockModel.getSku().getGoodsPack().getSpecQty()));
				upModel.setSku(skuShelfBatchStockModel.getSku());
				upModel.setSpec(skuShelfBatchStockModel.getSpec());
				upModel.setUnitName(skuShelfBatchStockModel.getUnitName());
				Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(SER_KEY,upModel);  
		        getIntent().putExtras(mBundle);
				
				setResult(RESULT_CODE, getIntent());
				finish();
			}
		});
		
		shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					
					String shelfCode = v.getText().toString().trim();

					shelfService.getShelfByCode(shelfCode, new AbstractServiceCallBack<ShelfModel>(context) {

						@Override
						public void doSuccess(ShelfModel shelf) {
							upModel.setUpShelf(shelf);
							upModel.setUpShelfCode(shelf.getCode());
							upModel.setUpStore(shelf.getStore());
							storeNameValue.setText(shelf.getStore().getName());
							shelfCodeValue.setText(shelf.getCode());
						}
					});
				}
				return false;
			}
		});
	}
}
