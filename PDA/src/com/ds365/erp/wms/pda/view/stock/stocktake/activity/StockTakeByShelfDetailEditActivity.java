package com.ds365.erp.wms.pda.view.stock.stocktake.activity;

import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * 说明 :货位盘点编辑
 */
public class StockTakeByShelfDetailEditActivity extends BasePdaActivity {

	private TextView saveButton;
	private TextView skuNameValue, skuCodeValue, specValue, specQtyValue, shelfCodeValue, sysBatchNoValue;
	private TextView expectUnitQty, expectMinUnitQty;
	private IntEditField unitQtyValue;//实盘件数
	private IntEditField minUnitQtyValue;//实盘散数
	
	private StockTakeOrderDetailModel detailModel;
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	@Override
	protected void initActivityView() {
		
		saveButton = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_save_button);
		skuNameValue = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_skuName_value);
		skuCodeValue = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_skuCode_value);
		specValue = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_spec_value);
		specQtyValue = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_specQty_value);
		shelfCodeValue = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_shelfCode_value);
		sysBatchNoValue = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_sysBatchNo_value);
		expectUnitQty = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_expectUnitQty_value);
		expectMinUnitQty = (TextView) findViewById(R.id.stockTakeByShelfDetailEdit_expectMinUnitQty_value);
		unitQtyValue = (IntEditField) findViewById(R.id.stockTakeByShelfDetailEdit_unitQtyValue_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.stockTakeByShelfDetailEdit_minUnitQtyValue_value);
		
		detailModel = (StockTakeOrderDetailModel) getIntent().getSerializableExtra(StockTakeByShelfActivity.SER_KEY);
		skuNameValue.setText(detailModel.getSku().getName());
		skuCodeValue.setText(detailModel.getSku().getCode());
		specValue.setText(detailModel.getSpec());
		specQtyValue.setText(String.valueOf(detailModel.getSku().getGoodsPack().getSpecQty()));
		shelfCodeValue.setText(detailModel.getShelf().getCode());
		sysBatchNoValue.setText(detailModel.getSysBatchNo());
		expectUnitQty.setText(String.valueOf(detailModel.getExpectUnitQty()));
		expectMinUnitQty.setText(String.valueOf(detailModel.getExpectMinUnitQty()));
		if (detailModel.getUnitQty() != null)
			unitQtyValue.setValue(detailModel.getUnitQty());
		if (detailModel.getMinUnitQty() != null)
			minUnitQtyValue.setValue(detailModel.getMinUnitQty());
		
		minUnitQtyValue.setMaxValueAndTextChangeListener(detailModel.getSku().getGoodsPack().getSpecQty(),"散数不能大于包装数量！",null);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.stockTakeByShelfDetailEdit_headerview, R.string.shelf_take_stock);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stocktake_stock_take_by_shelf_detail_edit;
	}

	@Override
	protected void setListener() {
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (StringUtils.isEmptyEditText(unitQtyValue.getEditText())) {
					T.showShort(context, "请输入实盘件数!");
					return;
				}else if (StringUtils.isEmptyEditText(minUnitQtyValue.getEditText())) {
					T.showShort(context, "请输入实盘散数!");
					return;
				}
				
				detailModel.setUnitQty(unitQtyValue.getValue());
				detailModel.setMinUnitQty(minUnitQtyValue.getValue());
				
				Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(SER_KEY,detailModel);  
		        getIntent().putExtras(mBundle);
				
				setResult(RESULT_CODE, getIntent());
				finish();
			}
		});
	}
}
