package com.ds365.erp.wms.pda.view.stock.stockshift.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchDynamicStockModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftDetailModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.service.stock.SkuShelfBatchStockService;
import com.ds365.erp.wms.pda.service.stockshift.StockShiftService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;
import com.ds365.erp.wms.pda.view.common.activity.SkuShelfBatchStockSelectorActivity;
import com.ds365.erp.wms.pda.view.common.activity.ShelfSelectorActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

/**
 * 
 * 说明 ：货位移动:从一个货位移动到另一个货位 
 */
public class StockShiftBillForSingleActivity extends BasePdaActivity {

	
	private TextView submitButton;
	private EditText skuCodeValue;
	private EditText downShelfCodeValue;
	private EditText upShelfCodeValue;
	private TextView downStoreNameValue;
	private TextView upStoreNameValue;
	private TextView skuNameValue;
	private TextView specValue;
	private TextView specQtyValue;
	private TextView unitNameValue;
	private IntEditField unitQtyValue;
	private IntEditField minUnitQtyValue;
	private TextView qtyValue,usableQtyValue;
	private ImageView skuCodeScanButton, downShelfCodeScanButton, upShelfCodeScanButton;
	
	private StockShiftDetailModel stockShiftDetailModel = new StockShiftDetailModel();
	
	/**
	 * 上架的列表
	 */
	private List<StockShiftDetailModel> list = new ArrayList<StockShiftDetailModel>();
	private Integer defaultUnitQty;
	private Integer defaultMinUnitQty;
	
	private static final int requestCodeForSkuCodeScan = 1;
	private static final int requestCodeForDownShelfCodeScan = 2;
	private static final int requestCodeForUpShelfCodeScan = 3;
	
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private StockShiftService stockShiftService = new StockShiftService();
	private ShelfService shelfService = new ShelfService();
	private SkuShelfBatchStockService skuShelfBatchStockService = new SkuShelfBatchStockService();
	private SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParamsModel = new SkuShelfBatchStockQueryParamsModel();
	
	@Override
	protected void initActivityView() {
		skuCodeScanButton = (ImageView) findViewById(R.id.stockShiftBillForSingle_skuCodeScan_button);
		downShelfCodeScanButton = (ImageView) findViewById(R.id.stockShiftBillForSingle_downShelfCodeScan_button);
		upShelfCodeScanButton = (ImageView) findViewById(R.id.stockShiftBillForSingle_upShelfCodeScan_button);
		submitButton = (TextView) findViewById(R.id.stockShiftBillForSingle_submitButton);
		skuCodeValue = (EditText) findViewById(R.id.stockShiftBillForSingle_skuCode_value);
		skuNameValue = (TextView) findViewById(R.id.stockShiftBillForSingle_skuName_value);
		specValue = (TextView) findViewById(R.id.stockShiftBillForSingle_spec_value);
		specQtyValue = (TextView) findViewById(R.id.stockShiftBillForSingle_specQty_value);
		unitNameValue = (TextView) findViewById(R.id.stockShiftBillForSingle_packUnit_value);
		unitQtyValue = (IntEditField) findViewById(R.id.stockShiftBillForSingle_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.stockShiftBillForSingle_minUnitQty_value);
		qtyValue = (TextView) findViewById(R.id.stockShiftBillForSingle_qty_value);
		usableQtyValue = (TextView) findViewById(R.id.stockShiftBillForSingle_usableQty_value);
		downShelfCodeValue = (EditText) findViewById(R.id.stockShiftBillForSingle_downShelfCode_value);
		upShelfCodeValue = (EditText) findViewById(R.id.stockShiftBillForSingle_upShelfCode_value);
		downStoreNameValue = (TextView) findViewById(R.id.stockShiftBillForSingle_downStoreName_value);
		upStoreNameValue = (TextView) findViewById(R.id.stockShiftBillForSingle_upStoreName_value);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.stockShiftBillForSingle_headerview, R.string.transferStore);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_stock_shelf_bill_for_single;
	}

	@Override
	protected void setListener() {
		getGoodsInfo();
		getdownShelfInfo();
		getUpShelfInfo();
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				StringBuilder message=new StringBuilder();
				if (stockShiftDetailModel.getSku() == null) {
					message.append("请输入sku条码并获取商品信息\r\n");
				}
				if (stockShiftDetailModel.getDownShelf() == null) {
					message.append("请输入移出货位信息\r\n");
				}
				if (stockShiftDetailModel.getUpShelf() == null) {
					message.append("请输入移入货位信息");
				}
				if(message.length()!=0){
					T.showShort(context, message.toString());
					return;
				}
				
				DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
						, getString(R.string.dialog_submit_stock_shift_bill), true, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						list.add(stockShiftDetailModel);
						submitList();
					}
				});
			}
		});
		
		TextWatcher textWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				verificationUsableQty(s);
			}
		};
		
		unitQtyValue.getEditText().addTextChangedListener(textWatcher);
		minUnitQtyValue.getEditText().addTextChangedListener(textWatcher);
		
		skuCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(StockShiftBillForSingleActivity.this
						, CodeScanActivity.class, requestCodeForSkuCodeScan);
			}
		});
		
		downShelfCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(StockShiftBillForSingleActivity.this
						, CodeScanActivity.class, requestCodeForDownShelfCodeScan);
			}
		});
		
		upShelfCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(StockShiftBillForSingleActivity.this
						, CodeScanActivity.class, requestCodeForUpShelfCodeScan);
			}
		});
	}
	
	/**
	 * 验证可用数量是否大于移出的总数量
	 */
	private void verificationUsableQty(Editable s){
		
		if(stockShiftDetailModel.getSku() == null||"".equals(skuCodeValue.getText().toString())){
			T.showShort(context, "请扫描sku条码获取商品信息");
			skuCodeValue.findFocus();
		}else{
			if(!"".equals(s.toString())){
				int unitQty = Integer.valueOf(unitQtyValue.getValue());
				int minUnitQty = Integer.valueOf(minUnitQtyValue.getValue());
				int qty = calQty(unitQty, minUnitQty);
				int usableQty = 0;
				if(!"".equals(usableQtyValue.getText().toString().trim())){
					usableQty = Integer.valueOf(usableQtyValue.getText().toString().trim());
					if (qty > usableQty) {
						T.showShort(context, "您输入的数量已经超出了可用数量,请重新输入!");
					}
					qtyValue.setText(String.valueOf(qty));
				}else{
					T.showShort(context, "请扫描货位号并获取移出的商品信息！");
					downShelfCodeValue.findFocus();
				}
			}else{
				T.showShort(context, "请输入数量！");
			}
		}
	}
	
	/**
	 * 获取商品信息
	 */
	private void getGoodsInfo(){
		
		skuCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					searchGoodsInfo(v);
				}
				
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchGoodsInfo(v);
                    return true;
                }
				return false;
			}
		});
	}
	
	/**
	 * 获取移出货位信息
	 */
	private void getdownShelfInfo(){
		downShelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				//按回车键调用
				if(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
					searchDownShelfInfo(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchDownShelfInfo(v);
                    return true;
                }
				return false;
			}
		});
	}
	
	/**
	 * 获取移入货位信息
	 */
	private void getUpShelfInfo(){
		upShelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				//按回车键调用
				if(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
					searchUpShelfInfo(v);
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    searchUpShelfInfo(v);
                    return true;
                }
				return false;
			}
		});
	}

	private void searchDownShelfInfo(final TextView v){
		if(stockShiftDetailModel.getSku() == null||"".equals(skuCodeValue.getText().toString())){
			T.showShort(context, "请扫描sku条码获取商品信息");
			return;
		}
		final String url = ConstantUrl.skuStock_skuStock_searchPageSkuShelfBatchStockForNormal;
		skuShelfBatchStockQueryParamsModel.setSkuId(stockShiftDetailModel.getSku().getId());
		skuShelfBatchStockQueryParamsModel.setFuzzy(v.getText().toString());
		
		skuShelfBatchStockService.searchPageSkuShelfBatchStockByParams(url
				,skuShelfBatchStockQueryParamsModel, new AbstractServiceCallBack<QueryResult<SkuShelfBatchStockModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<SkuShelfBatchStockModel> result) {
				long totalCount = result.getTotalCount();
				if (totalCount == 0) {
					T.showShort(context, "找不到对应的货位!");
					downShelfCodeValue.setText("");
					downShelfCodeValue.requestFocus();
				}else if (totalCount == 1){
					SkuShelfBatchStockModel skuShelfBatchStock = result.getRecords().get(0);
					setDownShelfValues(skuShelfBatchStock);
				}else if (totalCount > 1) {
					Intent intent=new Intent(context, SkuShelfBatchStockSelectorActivity.class);
					intent.putExtra("shelfCode", v.getText().toString());
					intent.putExtra("skuId",stockShiftDetailModel.getSku().getId());
					intent.putExtra(SkuShelfBatchStockSelectorActivity.SELECTOR_URL_KEY,url); 
					startActivityForResult(intent,PdaConstants.PDA_REQUEST_CODE);
				}
			}
		});
	}
	
	private void searchUpShelfInfo(TextView v){
		/*Intent intent=new Intent(context, ShelfSelectorActivity.class);
		intent.putExtra("shelfCode", v.getText().toString());
		startActivityForResult(intent,PdaConstants.PDA_REQUEST_CODE);*/
		
		if(stockShiftDetailModel.getSku() == null||"".equals(skuCodeValue.getText().toString())){
			T.showShort(context, "请扫描sku条码获取商品信息");
			skuCodeValue.findFocus();
			return;
		}
		if(stockShiftDetailModel.getDownShelf() == null){
			T.showShort(context, "请扫描移出商品的货位号");
			downShelfCodeValue.findFocus();
			return;
		}
		SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParams = new SkuShelfBatchStockQueryParamsModel();
		skuShelfBatchStockQueryParams.setSkuId(stockShiftDetailModel.getSku().getId());
		skuShelfBatchStockQueryParams.setExcludeSysBatchNo(stockShiftDetailModel.getSysBatchNo());
		
//		List<Long> excludeIds = new ArrayList<Long>();
//		excludeIds.add(stockShiftDetailModel.getDownShelf().getId());
//		skuShelfBatchStockQueryParams.setExcludeIds(excludeIds);
//		此处做原货位和目标货位的验证
		skuShelfBatchStockQueryParams.setShelfCode(upShelfCodeValue.getText().toString().trim());
		
		shelfService.getShelfByCodeAndVal(skuShelfBatchStockQueryParams, new AbstractServiceCallBack<ShelfModel>(context) {

			@Override
			public void doSuccess(ShelfModel shelf) {
				if (shelf !=null) {
					if(stockShiftDetailModel.getDownShelf().getId().longValue()== shelf.getId().longValue()){
						T.showLong(context, "目标货位不能与原货位相同！");
						upShelfCodeValue.setText("");
						upShelfCodeValue.requestFocus();
						return;
					}
					upShelfCodeValue.setText(shelf.getCode());
					stockShiftDetailModel.setUpShelf(shelf);
					StoreModel store=shelf.getStore();
					stockShiftDetailModel.setUpStore(store);
					upStoreNameValue.setText(store.getName());
				}else{
					T.showLong(context, "找不到对应的货位！");
					upShelfCodeValue.setText("");
					upShelfCodeValue.requestFocus();
				}
			}

			//如果验证不通过，将原因向用户进行提示
			@Override
			public void doFaile(String str) {
				T.showLong(context, str);
			}
		});
	}				
	
	private void searchGoodsInfo(TextView v){

		final String fuzzy = v.getText().toString().trim();
		GoodsSkuQueryParamsModel goodsSkuQueryParamsModel = new GoodsSkuQueryParamsModel();
		goodsSkuQueryParamsModel.setFuzzy(fuzzy);
		
		goodsSkuService.searchPageGoodsSkuByParams(goodsSkuQueryParamsModel, new AbstractServiceCallBack<QueryResult<GoodsSkuModel>>(context) {
			
			@Override
			public void doSuccess(QueryResult<GoodsSkuModel> result) {
				long totalCount = result.getTotalCount();
				if (totalCount == 0) {
					T.showShort(context, "找不到该商品!");
					skuCodeValue.setText("");
					skuCodeValue.requestFocus();
				}else if (totalCount == 1){
					GoodsSkuModel sku=result.getRecords().get(0);
					setDownViewValue(sku);
					
				}else if (totalCount > 1) {
					//跳转
					Intent intent = new Intent(context, GoodsSkuSelectorActivity.class);
					intent.putExtra(PdaConstants.fuzzy, fuzzy);
					startActivityForResult(intent, 1);
				}
			}
		});
	
	}
	
	private void setDownShelfValues(SkuShelfBatchStockModel skuShelfBatchStock) {
		downShelfCodeValue.setText(skuShelfBatchStock.getShelf().getCode());
		usableQtyValue.setText(String.valueOf(skuShelfBatchStock.getSkuShelfBatchDynamicStock().getUsableQty()));
		
		stockShiftDetailModel.setDownShelf(skuShelfBatchStock.getShelf());
		stockShiftDetailModel.setDownStore(skuShelfBatchStock.getStore());
		stockShiftDetailModel.setSysBatchNo(skuShelfBatchStock.getSysBatchNo());
		
		SkuShelfBatchDynamicStockModel stock = new SkuShelfBatchDynamicStockModel();
		stock.setUsableQty(skuShelfBatchStock.getSkuShelfBatchDynamicStock().getUsableQty());
		
		
		Integer qty=stock.getUsableQty();
		QtyModel  qtyModel=QtyMoneyUtils.getQtyEntity(qty, skuShelfBatchStock.getSku().getGoodsPack().getSpecQty());
		defaultUnitQty = qtyModel.getUnitQty();
		defaultMinUnitQty = qtyModel.getMinUnitQty();
		stockShiftDetailModel.setUnitQty(defaultUnitQty);
		stockShiftDetailModel.setMinUnitQty(defaultMinUnitQty);
		stockShiftDetailModel.setQty(qty);
		
		unitQtyValue.setValue(qtyModel.getUnitQty());
		minUnitQtyValue.setValue(qtyModel.getMinUnitQty());
		qtyValue.setText(String.valueOf(qty));
		StoreModel store=skuShelfBatchStock.getStore();
		downStoreNameValue.setText(store.getName());
		
		minUnitQtyValue.setMaxValueAndTextChangeListener(skuShelfBatchStock.getSku().getGoodsPack().getSpecQty(),"散数不能大于包装数量！",null);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (intent != null) {
			if (resultCode == GoodsSkuSelectorActivity.RESULT_CODE) {
				
				GoodsSkuModel sku = (GoodsSkuModel)intent.getSerializableExtra(GoodsSkuSelectorActivity.SER_KEY);
				setDownViewValue(sku);
				
			}else if (resultCode == SkuShelfBatchStockSelectorActivity.RESULT_CODE) {
				SkuShelfBatchStockModel skuShelfBatchStock = (SkuShelfBatchStockModel)intent.getSerializableExtra(SkuShelfBatchStockSelectorActivity.SER_KEY); 
				setDownShelfValues(skuShelfBatchStock);
				
			}/*else if (resultCode == ShelfSelectorActivity.RESULT_CODE) {
				
				ShelfModel shelf = (ShelfModel)intent.getSerializableExtra(ShelfSelectorActivity.SER_KEY); 
				upShelfCodeValue.setText(shelf.getCode());
				
				stockShiftDetailModel.setUpShelf(shelf);
				stockShiftDetailModel.setUpStore(shelf.getStore());
				
				StoreModel store=shelf.getStore();
				upStoreNameValue.setText(store.getName());
			}*/else if(requestCode == requestCodeForSkuCodeScan && resultCode == CodeScanActivity.RESULT_CODE){
				skuCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}else if(requestCode == requestCodeForDownShelfCodeScan && resultCode == CodeScanActivity.RESULT_CODE){
				downShelfCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}else if(requestCode == requestCodeForUpShelfCodeScan && resultCode == CodeScanActivity.RESULT_CODE){
				upShelfCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
	
	/**
	 * 根据件数散数计算总数量
	 * @param unitQty
	 * @param minUnitQty
	 * @return
	 */
	private Integer calQty(Integer unitQty, Integer minUnitQty) {
		
		Integer specQty = stockShiftDetailModel.getSpecQty();
		Integer qty = QtyMoneyUtils.getQty(unitQty, minUnitQty, specQty);
		
		return qty;
	}
	
	/**
	 * 提交处理
	 * @param params
	 */
	private void submitList() {
		
		StockShiftBillCreateParamsModel  createParams = setupStockShiftBillCreateParamsModel();
		
		if(createParams.getDetails().size()==0){
			T.showShort(context, "提交的检查记录数不能为空");
			return;
		}
		
		stockShiftService.stockShiftCreate(createParams, new AbstractServiceCallBack<String>(context) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				IntentUtils.startActivity(StockShiftBillForSingleActivity.this, StockShiftBillForSingleActivity.class);
				finish();
			}
		});
		
	}
	
	private StockShiftBillCreateParamsModel setupStockShiftBillCreateParamsModel() {
		// TODO Auto-generated method stub
		StockShiftBillCreateParamsModel stockShiftBillCreateParamsModel = new StockShiftBillCreateParamsModel();
		
//		stockShiftBillCreateParamsModel.setBillCode(bill.getBillCode());
//		stockShiftBillCreateParamsModel.setBillMoney(bill.getBillMoney());
//		stockShiftBillCreateParamsModel.setExaminerId(bill.getExaminer().getId());
//		stockShiftBillCreateParamsModel.setId(bill.getId());
//		stockShiftBillCreateParamsModel.setItemCount(bill.getItemCount());
//		stockShiftBillCreateParamsModel.setMakerId(bill.getMaker().getId());
//		stockShiftBillCreateParamsModel.setMakeTime(bill.getMakeTime());
//		stockShiftBillCreateParamsModel.setOldBillCode(bill.getOldBillCode());
//		stockShiftBillCreateParamsModel.setOldBillId(bill.getOldBillId());
//		stockShiftBillCreateParamsModel.setRelatedBillCode(bill.getRelatedBillCode());
//		stockShiftBillCreateParamsModel.setRelatedBillId(bill.getRelatedBillId());
//		stockShiftBillCreateParamsModel.setRemark(bill.getRemark());
		
		
//		stockShiftBillCreateParamsModel.setBillStateId(bill.getbills);
//		stockShiftBillCreateParamsModel.setBillTypeId(bill.getbillt);
//		stockShiftBillCreateParamsModel.setOldBillTypeId(bill.getoldbillt);
//		stockShiftBillCreateParamsModel.setRelatedBillTypeId(relatedBillTypeId);
		
		
		List<StockShiftDetailCreateParamsModel> createDetails=new ArrayList<StockShiftDetailCreateParamsModel>();
		for(StockShiftDetailModel detail: list){
			StockShiftDetailCreateParamsModel createDetail=new StockShiftDetailCreateParamsModel();
			
			createDetail.setBarcode(detail.getBarcode());
			createDetail.setBillCode(detail.getBillCode());
			createDetail.setCostMoney(detail.getCostMoney());
			createDetail.setCostPrice(detail.getCostPrice());
			createDetail.setCostUnitPrice(detail.getCostUnitPrice());
			createDetail.setDownShelfCode(detail.getDownShelf().getCode());
			createDetail.setDownShelfId(detail.getDownShelf().getId());
			createDetail.setDownStoreId(detail.getDownStore().getId());
			createDetail.setExpireDate(detail.getExpireDate());
			/*createDetail.setGoodsId(detail.getGoods().getId());
			createDetail.setGoodsName(detail.getGoods().getName());*/
			createDetail.setGuaranteePeriod(detail.getGuaranteePeriod());
			createDetail.setId(detail.getId());
			createDetail.setMinUnitQty(detail.getMinUnitQty());
			createDetail.setOrdinalNo(detail.getOrdinalNo());
			//createDetail.setPackId(detail.getPack().getId());
			createDetail.setProduceBatchNo(detail.getProduceBatchNo());
			createDetail.setProduceDate(detail.getProduceDate());
			createDetail.setQty(detail.getQty());
			createDetail.setSkuCode(detail.getSku().getCode());
			createDetail.setSpec(detail.getSpec());
			createDetail.setSkuId(detail.getSku().getId());
			createDetail.setSkuName(detail.getSku().getName());
			createDetail.setSpecQty(detail.getSpecQty());
			createDetail.setSysBatchNo(detail.getSysBatchNo());
			createDetail.setUnitName(detail.getUnitName());
			createDetail.setUnitQty(detail.getUnitQty());
			createDetail.setUpShelfCode(detail.getUpShelf().getCode());
			createDetail.setUpShelfId(detail.getUpShelf().getId());
			createDetail.setUpStoreId(detail.getUpStore().getId());
			
//			createDetail.setStockQty(detail.getstoc);
//			createDetail.setPackTypeId(detail.getpa);
//			createDetail.setBillId(detail.getbilli);
			
			createDetails.add(createDetail);
		}
		
		stockShiftBillCreateParamsModel.setDetails(createDetails);
		
		return stockShiftBillCreateParamsModel;
		
	}
	private void setDownViewValue(GoodsSkuModel sku) {
		stockShiftDetailModel.setSku(sku);
		stockShiftDetailModel.setSpecQty(sku.getGoodsPack().getSpecQty());
		stockShiftDetailModel.setSpec(sku.getSpec());
		stockShiftDetailModel.setUnitName(sku.getGoodsPack().getUnitName());
		skuNameValue.setText(sku.getName());
		specValue.setText(sku.getSpec());
		unitNameValue.setText(sku.getGoodsPack().getName());
		skuCodeValue.setText(sku.getCode());
		specQtyValue.setText(String.valueOf(sku.getGoodsPack().getSpecQty()));
	}
	
}
