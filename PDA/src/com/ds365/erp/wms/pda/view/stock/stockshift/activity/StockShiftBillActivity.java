package com.ds365.erp.wms.pda.view.stock.stockshift.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchDynamicStockModel;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.StockShiftDetailModel;
import com.ds365.erp.wms.pda.model.stockshift.StoreModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.stockshift.StockShiftService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;
import com.ds365.erp.wms.pda.view.common.activity.SkuShelfBatchStockSelectorActivity;
import com.ds365.erp.wms.pda.view.stock.stockshift.adapter.StockShiftDetailForUpAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * 
 * 说明 ：货位移动:可从一个货位移动到多个货位 
 */
public class StockShiftBillActivity extends BasePdaListActivity {
	
	public static final String SER_KEY=PdaConstants.nextSerKey();

	private TextView submitButton;
	private TextView addButton;
	
	private EditText skuCodeValue;
	private EditText shelfCodeValue;
	
	private TextView skuNameValue;
	private TextView specValue;
	private TextView specQtyValue;
	private TextView unitNameValue;
	private TextView storeNameValue;	//库区
	private TextView unitQtyVlaue;
	private TextView minUnitQtyValue;
	private TextView qtyValue,usableQtyValue;	//总数量
	
	private PullToRefreshListView listView;
	private int selectPosition = 0;
	
	/**
	 * 下架的模型
	 */
	private SkuShelfBatchStockModel downModel = new SkuShelfBatchStockModel();
	
	//private JsonParser<JsonResult<QueryResult<SkuShelfBatchStockModel>>> shelfJsonParser;
	
	private StockShiftDetailForUpAdapter adapter;
	/**
	 * 上架的列表
	 */
	private List<StockShiftDetailModel> list = new ArrayList<StockShiftDetailModel>();
	
	private int itemRequestCode = 1;
	private int addRequestCode = 2;
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	public static final String ON_ITEM_CLICK_REQUEST = "OnItemClick";
	public static final String ON_ADD_BUTTON_CLICK_REQUEST = "OnAddButtonClick";
	
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private StockShiftService stockShiftService = new StockShiftService();
	
	@Override
	protected void initActivityView() {
		submitButton = (TextView) findViewById(R.id.stockShiftBill_submitButton);
		addButton = (TextView) findViewById(R.id.stockShiftBill_addButton);
		skuCodeValue = (EditText) findViewById(R.id.stockShiftBill_skuCode_value);
		shelfCodeValue = (EditText) findViewById(R.id.stockShiftBill_shelfCode_value);
		skuNameValue = (TextView) findViewById(R.id.stockShiftBill_skuName_value);
		specValue = (TextView) findViewById(R.id.stockShiftBill_spec_value);
		specQtyValue = (TextView) findViewById(R.id.stockShiftBill_specQty_value);
		unitNameValue = (TextView) findViewById(R.id.stockShiftBill_packUnit_value);
		storeNameValue = (TextView) findViewById(R.id.stockShiftBill_storeName_value);
		unitQtyVlaue = (TextView) findViewById(R.id.stockShiftBill_unitQty_value);
		minUnitQtyValue = (TextView) findViewById(R.id.stockShiftBill_minUnitQty_value);
		qtyValue = (TextView) findViewById(R.id.stockShiftBill_qty_value);
		usableQtyValue = (TextView) findViewById(R.id.stockShiftBill_usableQty_value);
		listView = (PullToRefreshListView) findViewById(R.id.stockShiftBill_listView);
		adapter = new StockShiftDetailForUpAdapter(context, list);
		listView.setAdapter(adapter);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.stockShiftBill_headerview, R.string.transferStore);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_stock_shelf_bill;
	}

	@Override
	protected void setListener() {
		
		getGoodsInfo();
		
		getShelfInfo();
		
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
						, getString(R.string.dialog_submit_stock_shift_bill), true, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						submitList();
					}
				});
			}
		});
		
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (StringUtils.isEmptyEditText(skuCodeValue)) {
					T.showShort(context, "sku条码不能为空!");
					return;
				}else if(StringUtils.isEmptyEditText(shelfCodeValue)){
					T.showShort(context, "货位号不能为空!");
					return;
				}
				Intent intent = new Intent(context, StockShiftDetailEditForUpActivity.class);
				Bundle mBundle = new Bundle();  
				mBundle.putString(ON_ADD_BUTTON_CLICK_REQUEST, REQUEST_CODE);
		        mBundle.putSerializable(SER_KEY,downModel);  
		        
		        intent.putExtras(mBundle);
				startActivityForResult(intent, addRequestCode);
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context, StockShiftDetailEditForUpActivity.class);
				selectPosition = position-1;
				Bundle mBundle = new Bundle();  
				mBundle.putString(ON_ITEM_CLICK_REQUEST, REQUEST_CODE);
		        mBundle.putSerializable(SER_KEY,list.get(selectPosition));  
		        intent.putExtras(mBundle);
				startActivityForResult(intent, itemRequestCode);
			}
		});
	}
	
	/**
	 * 将ShelfBatchStockModel转换为StockShiftDetailModel
	 */
	/*private StockShiftDetailModel setupStockShiftDetailModel(ShelfBatchStockModel downModel){
		
		StockShiftDetailModel stockShiftDetailModel = new StockShiftDetailModel();
		stockShiftDetailModel.setSku(downModel.getSku());
		stockShiftDetailModel.setSpec(downModel.getSpec());
		stockShiftDetailModel.setUnitName(downModel.getUnitName());
		stockShiftDetailModel.setDownShelf(downModel.getShelf());
		stockShiftDetailModel.setDownShelfCode(downModel.getShelf().getCode());
		stockShiftDetailModel.setDownStore(downModel.getStore());
		stockShiftDetailModel.setUnitQty(downModel.getUnitQty());
		stockShiftDetailModel.setMinUnitQty(downModel.getMinUnitQty());
		stockShiftDetailModel.setQty(downModel.getQty());
//		stockShiftDetailModel.set
		return stockShiftDetailModel;
	}*/
	
	/**
	 * 获取商品信息
	 */
	private void getGoodsInfo(){
		skuCodeValue.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (skuCodeValue.getText().toString().length()!=0 && !hasFocus) {
//					TODO:当编辑框发生变化后所做的操作: 条码采集器扫码后自动搜索该skuCode下的商品并展示出商品列表页面
					final String fuzzy = skuCodeValue.getText().toString().trim();
					
					GoodsSkuQueryParamsModel goodsSkuQueryParamsModel = new GoodsSkuQueryParamsModel();
					goodsSkuQueryParamsModel.setFuzzy(fuzzy);
					
					goodsSkuService.searchPageGoodsSkuByParams(goodsSkuQueryParamsModel, new AbstractServiceCallBack<QueryResult<GoodsSkuModel>>(context) {
						
						@Override
						public void doSuccess(QueryResult<GoodsSkuModel> result) {
							long totalCount = result.getTotalCount();
							if (totalCount == 0) {
								T.showShort(context, "找不到该商品!");
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
			}
		});
	}
	private void setDownViewValue(GoodsSkuModel sku) {
		downModel.setSku(sku);
//		downModel.setSpecQty(sku.getGoodsPack().getSpecQty());
		downModel.setSpec(sku.getSpec());
		downModel.setUnitName(sku.getGoodsPack().getUnitName());
		skuNameValue.setText(sku.getName());
		specValue.setText(sku.getSpec());
		unitNameValue.setText(sku.getGoodsPack().getName());
		skuCodeValue.setText(sku.getCode());
	}
	/**
	 * 获取架位信息
	 */
	private void getShelfInfo(){
		
//		shelfCodeVlaue.setOnFocusChangeListener(new OnFocusChangeListener() {
//			
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				if (shelfCodeVlaue.getText().toString().length()!=0 && !hasFocus) {
////					TODO:当编辑框发生变化后所做的操作: 条码采集器扫码后自动搜索该skuCode下的商品并展示出商品列表页面
//					params.getParams().clear();
//					params.setUrl(ConstantUrl.SKU_SHELF_BATCH_STOCK);
//					params.getParams().put("shelfCode", shelfCodeVlaue.getText().toString().trim());
//					params.getParams().put("skuId", String.valueOf(skuId));
//					
//					shelfJsonParser = new JsonParser<JsonResult<QueryResult<SkuShelfBatchStockModel>>>() {};
//					RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<SkuShelfBatchStockModel>>>(context, shelfJsonParser) {
//
//						@Override
//						public void doSuccess(JsonResult<QueryResult<SkuShelfBatchStockModel>> result) {
//							long totalCount = result.getData().getTotalCount();
//							if (totalCount == 0) {
//								T.showShort(context, "没有数据");
//								shelfCodeVlaue.setText("");
//							}else if (totalCount == 1) {
//								shelfVlaue.setText(sku.getShelf().getCode());
//							}else{
//								T.showShort(context, "服务器数据有误");
//							}
//						}
//
//						@Override
//						public void doFaile(String str) {
//							T.showShort(context, str);
//						}
//					});
//				}
//			}
//		});
		
		shelfCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				//按回车键调用
				if(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
					/*params.getParams().clear();
					params.setUrl(ConstantUrl.SKU_SHELF_BATCH_STOCK);
					params.getParams().put("shelfCode", shelfCodeVlaue.getText().toString().trim());
					params.getParams().put("skuId", String.valueOf(skuId));*/
					
					Intent intent=new Intent(context, SkuShelfBatchStockSelectorActivity.class);
					intent.putExtra("shelfCode",  shelfCodeValue.getText().toString());
					intent.putExtra("skuId",downModel.getSku().getId());
					startActivityForResult(intent,1);
					
					/*shelfJsonParser = new JsonParser<JsonResult<QueryResult<SkuShelfBatchStockModel>>>() {};
					RequestUtil.request(params, new AbstractResponseHandler<JsonResult<QueryResult<SkuShelfBatchStockModel>>>(context, shelfJsonParser) {
	
						@Override
						public void doSuccess(JsonResult<QueryResult<SkuShelfBatchStockModel>> result) {
							long totalCount = result.getData().getTotalCount();
							if (totalCount == 0) {
								T.showShort(context, "没有数据");
								shelfCodeVlaue.setText("");
							}else if (totalCount == 1) {
								shelfVlaue.setText(sku.getShelf().getCode());
							}else{
								T.showShort(context, "服务器数据有误");
							}
						}
	
						@Override
						public void doFaile(String str) {
							T.showShort(context, str);
						}
					});*/
				}
				return false;
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (intent != null) {
			if (resultCode == GoodsSkuSelectorActivity.RESULT_CODE) {
				
				//this.getIntent().
				GoodsSkuModel sku = (GoodsSkuModel)intent.getSerializableExtra(GoodsSkuSelectorActivity.SER_KEY);
				setDownViewValue(sku);
				
				
			}else if (requestCode == addRequestCode && resultCode == StockShiftDetailEditForUpActivity.RESULT_CODE) {
				
				StockShiftDetailModel detailModel =(StockShiftDetailModel)intent.getSerializableExtra(StockShiftDetailEditForUpActivity.SER_KEY);
				list.add(detailModel);
				adapter.notifyDataSetChanged(); 
			}else if(requestCode == itemRequestCode && resultCode == StockShiftDetailEditForUpActivity.RESULT_CODE){
				
				StockShiftDetailModel stockShiftDetailModel = (StockShiftDetailModel)intent.getSerializableExtra(StockShiftDetailEditForUpActivity.SER_KEY); 
				list.set(selectPosition, stockShiftDetailModel);
				adapter.notifyDataSetChanged();
				
			}else if (resultCode == SkuShelfBatchStockSelectorActivity.RESULT_CODE) {
				SkuShelfBatchStockModel skuShelfBatchStock = (SkuShelfBatchStockModel)intent.getSerializableExtra(SkuShelfBatchStockSelectorActivity.SER_KEY); 
				
				shelfCodeValue.setText(skuShelfBatchStock.getShelf().getCode());
				usableQtyValue.setText(String.valueOf(skuShelfBatchStock.getSkuShelfBatchDynamicStock().getUsableQty()));
				specQtyValue.setText(String.valueOf(skuShelfBatchStock.getSku().getGoodsPack().getSpecQty()));
				downModel.setShelf(skuShelfBatchStock.getShelf());
				downModel.setStore(skuShelfBatchStock.getStore());
				downModel.setSysBatchNo(skuShelfBatchStock.getSysBatchNo());
				SkuShelfBatchDynamicStockModel stock = new SkuShelfBatchDynamicStockModel();
				stock.setUsableQty(skuShelfBatchStock.getSkuShelfBatchDynamicStock().getUsableQty());
				downModel.setSkuShelfBatchDynamicStock(stock);
				
				Integer qty=skuShelfBatchStock.getQty();
				QtyModel  qtyModel=QtyMoneyUtils.getQtyEntity(qty, skuShelfBatchStock.getSku().getGoodsPack().getSpecQty());
				
				downModel.setUnitQty(qtyModel.getUnitQty());
				downModel.setMinUnitQty(qtyModel.getMinUnitQty());
				downModel.setQty(qty);
				
				
				unitQtyVlaue.setText(String.valueOf(qtyModel.getUnitQty()));
				minUnitQtyValue.setText(String.valueOf(qtyModel.getMinUnitQty()));
				qtyValue.setText(String.valueOf(qty));
//				StoreModel store=skuShelfBatchStock.getShelf().getStore();//Store值为null
				StoreModel store=skuShelfBatchStock.getStore();
				storeNameValue.setText(store.getName());
				
			}
			
		}
	}
	
	/**
	 * 提交处理
	 * @param params
	 */
	private void submitList() {
		
		StockShiftBillCreateParamsModel  createParams = setupStockShiftBillCreateParamsModel(list);
		
		stockShiftService.stockShiftCreate(createParams, new AbstractServiceCallBack<String>(context) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				finish();
			}
		});
	}
	
	private StockShiftBillCreateParamsModel setupStockShiftBillCreateParamsModel(List<StockShiftDetailModel> details) {
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
		for(StockShiftDetailModel detail: details){
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
			createDetail.setGoodsId(detail.getGoods().getId());
			createDetail.setGoodsName(detail.getGoods().getName());
			createDetail.setGuaranteePeriod(detail.getGuaranteePeriod());
			createDetail.setId(detail.getId());
			createDetail.setMinUnitQty(detail.getMinUnitQty());
			createDetail.setOrdinalNo(detail.getOrdinalNo());
			createDetail.setPackId(detail.getPack().getId());
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
	
	
}
