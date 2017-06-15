package com.ds365.erp.wms.pda.view.stock.stocktake.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockModel;
import com.ds365.erp.wms.pda.model.stock.SkuShelfBatchStockQueryParamsModel;
import com.ds365.erp.wms.pda.model.stockshift.ShelfModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderBillModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderDetailModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.shelf.ShelfService;
import com.ds365.erp.wms.pda.service.stocktake.StockTakeByShelfService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;
import com.ds365.erp.wms.pda.view.stock.stocktake.adapter.StockTakeOrderDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * 
 * 说明 ：货位盘点列表
 */
public class StockTakeByShelfActivity extends BasePdaPageActivity {

	private TextView submitButton, queryButton,specValue,unitNameValue;
	private EditText shelfCodeValue, storeNameValue, skuCodeValue;
	private ImageView shelfCodeScanButton;
	private PullToRefreshListView listView;
	private StockTakeOrderDetailAdapter adapter;
	
	private List<StockTakeOrderDetailModel> details = new ArrayList<StockTakeOrderDetailModel>();
	private StockTakeOrderBillModel stockTakeOrderBillModel = new StockTakeOrderBillModel();
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private int selectPosition = 0;
	
	private StockTakeByShelfService stockTakeByShelfService = new StockTakeByShelfService();
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private ShelfService shelfService = new ShelfService();
	
	private SkuShelfBatchStockQueryParamsModel skuShelfBatchStockQueryParamsModel = new SkuShelfBatchStockQueryParamsModel();

	@Override
	protected void initActivityView() {
		shelfCodeScanButton = (ImageView) findViewById(R.id.stockTakeByShelf_shelfCodeScan_button);
		specValue = (TextView) findViewById(R.id.stockTakeByShelf_spec_value);
		unitNameValue = (TextView) findViewById(R.id.stockTakeByShelf_unitName_value);
		submitButton = (TextView) findViewById(R.id.stockTakeByShelf_submit_button);
		queryButton = (TextView) findViewById(R.id.stockTakeByShelf_query_button);
		listView = (PullToRefreshListView) findViewById(R.id.stockTakeByShelf_details_value);
		shelfCodeValue = (EditText) findViewById(R.id.stockTakeByShelf_shelfCode_value);
		storeNameValue = (EditText) findViewById(R.id.stockTakeByShelf_storeName_value);
		skuCodeValue = (EditText) findViewById(R.id.stockTakeByShelf_skuCode_value);
		adapter = new StockTakeOrderDetailAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		setFirstPage();
		setParams();
		getData(PdaConstants.CLEAR_LISTVIEW_YES);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.stockTakeByShelf_headerview, R.string.shelf_take_stock);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stocktake_stock_take_by_shelf;
	}

	private void setParams(){
		ShelfModel shelf = stockTakeOrderBillModel.getShelf();
		skuShelfBatchStockQueryParamsModel.setStart(start);
		skuShelfBatchStockQueryParamsModel.setLimit(PdaConstants.LIMIT);
		if (!StringUtils.isEmptyEditText(shelfCodeValue)){
			skuShelfBatchStockQueryParamsModel.setShelfId(shelf.getId());
			skuShelfBatchStockQueryParamsModel.setStoreId(shelf.getStore().getId());
		}
		skuShelfBatchStockQueryParamsModel.setSkuCode(skuCodeValue.getText().toString().trim());
	}
	
	@Override
	protected void setListener() {
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setFirstPage();
				setParams();
				getData(PdaConstants.CLEAR_LISTVIEW_YES);
			}
		});
		
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
						, getString(R.string.dialog_submit_stock_take), true, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						submitList();
					}
				});
			}
		});
		
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
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectPosition = position-1;
				StockTakeOrderDetailModel bill=details.get(position-1);
				IntentUtils.startActivityBySerialForResult(StockTakeByShelfActivity.this
						, StockTakeByShelfDetailEditActivity.class, SER_KEY, bill, PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
				setFirstPage();
				setParams();
				getData(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
				setNextPage();
				setParams();
				getData(PdaConstants.CLEAR_LISTVIEW_NO);
			}
		}));
		
		skuCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
					getGoodsInfo();
				}
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    getGoodsInfo();
                    return true;
                }
				return false;
			}
		});
		
		shelfCodeScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(StockTakeByShelfActivity.this
						, CodeScanActivity.class, PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
	}
	
	private void searchShelf(TextView v ){
		String shelfCode = v.getText().toString().trim();

		shelfService.getShelfByCode(shelfCode, new AbstractServiceCallBack<ShelfModel>(context) {

			@Override
			public void doSuccess(ShelfModel shelf) {
				if (shelf != null) {
					stockTakeOrderBillModel.setShelf(shelf);
					stockTakeOrderBillModel.setStore(shelf.getStore());
					storeNameValue.setText(shelf.getStore().getName());
					shelfCodeValue.setText(shelf.getCode());
				}else{
					T.showShort(context, "找不到此货位信息");
					shelfCodeValue.setText("");
					shelfCodeValue.requestFocus();
				}
			}
		});
	}
	
	private void getData(int type){
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		
		stockTakeByShelfService.searchPageSkuShelfBatchStockByParams(skuShelfBatchStockQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<SkuShelfBatchStockModel>>(context) {

					@Override
					public void doSuccess(QueryResult<SkuShelfBatchStockModel> result) {
						if (result != null && result.getRecords().size() > 0) {
							List<SkuShelfBatchStockModel> oldData = result.getRecords();
							List<StockTakeOrderDetailModel> data = convertToStockTakeOrderDetailModel(oldData);
							details.addAll(data);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						adapter.notifyDataSetInvalidated();
					}

				});
		
	}
	
	private List<StockTakeOrderDetailModel> convertToStockTakeOrderDetailModel(
			List<SkuShelfBatchStockModel> oldData) {
		List<StockTakeOrderDetailModel> list=new ArrayList<StockTakeOrderDetailModel>();
		for(SkuShelfBatchStockModel oldDetail:oldData){
			
			StockTakeOrderDetailModel detail = new StockTakeOrderDetailModel();
			detail.setSku(oldDetail.getSku());
			detail.setExpectUnitQty(oldDetail.getUnitQty());
			detail.setExpectMinUnitQty(oldDetail.getMinUnitQty());
			detail.setSaleType(oldDetail.getSaleType());
			detail.setSysBatchNo(oldDetail.getSysBatchNo());
			detail.setShelf(oldDetail.getShelf());
			detail.setShelfCode(oldDetail.getShelf().getCode());
			list.add(detail);
		}
		
		return list;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == StockTakeByShelfDetailEditActivity.RESULT_CODE) {
				StockTakeOrderDetailModel detailModel = (StockTakeOrderDetailModel) intent.getSerializableExtra(StockTakeByShelfDetailEditActivity.SER_KEY);
				details.set(selectPosition, detailModel);
				details.get(selectPosition).setCheckFlag(true);
				adapter.notifyDataSetChanged();
			}else if (resultCode == GoodsSkuSelectorActivity.RESULT_CODE) {
				GoodsSkuModel goodsSkuModel = (GoodsSkuModel)intent.getSerializableExtra(GoodsSkuSelectorActivity.SER_KEY);
				specValue.setText(goodsSkuModel.getSpec());
				unitNameValue.setText(goodsSkuModel.getGoodsPack().getName());
				skuCodeValue.setText(goodsSkuModel.getCode());
			}else if (resultCode == CodeScanActivity.RESULT_CODE) {
				shelfCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
	
	/**
	 * 获取商品信息
	 */
	private void getGoodsInfo(){
		final String fuzzy = skuCodeValue.getText().toString().trim();
		
		GoodsSkuQueryParamsModel goodsSkuQueryParamsModel = new GoodsSkuQueryParamsModel();
//		requestParams.getParams().put(PdaConstants.fuzzy, fuzzy);
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
					GoodsSkuModel goodsSkuModel = result.getRecords().get(0);
					specValue.setText(goodsSkuModel.getSpec());
					unitNameValue.setText(goodsSkuModel.getGoodsPack().getName());
					
				}else if (totalCount > 1) {
					Intent intent = new Intent(context, GoodsSkuSelectorActivity.class);
					intent.putExtra(PdaConstants.fuzzy, fuzzy);
					startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
				}
			}
		});
	}
	
	
	/**
	 * 提交处理
	 * @param params
	 */
	private void submitList() {
		
		StockTakeOrderBillCreateParamsModel  createParams = setupStockTakeOrderBillCreateParamsModel(stockTakeOrderBillModel,details);
		
		if(createParams.getDetails().size()==0){
			T.showShort(context, "提交的检查记录数不能为空");
			return;
		}
		
		stockTakeByShelfService.stockTakeCreate(createParams, new AbstractServiceCallBack<String>(context) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				finish();
			}
		});
	}
	
	private StockTakeOrderBillCreateParamsModel setupStockTakeOrderBillCreateParamsModel(StockTakeOrderBillModel bill,List<StockTakeOrderDetailModel>details) {
		// TODO Auto-generated method stub
		StockTakeOrderBillCreateParamsModel stockTakeOrderBillCreateParamsModel = new StockTakeOrderBillCreateParamsModel();
		
		stockTakeOrderBillCreateParamsModel.setExaminerId(GlobalUtils.getSessionUser().getEmployeeId());
		stockTakeOrderBillCreateParamsModel.setMakerId(GlobalUtils.getSessionUser().getEmployeeId());
		
		List<StockTakeOrderDetailCreateParamsModel> createDetails=new ArrayList<StockTakeOrderDetailCreateParamsModel>();
		for(StockTakeOrderDetailModel detail: details){
			StockTakeOrderDetailCreateParamsModel createDetail=new StockTakeOrderDetailCreateParamsModel();
			
			if(detail.isCheckFlag()){

				createDetail.setId(detail.getId());
				createDetail.setSkuId(detail.getSku().getId());
				createDetail.setShelfId(detail.getShelf().getId());
				createDetail.setUnitQty(detail.getUnitQty());
				createDetail.setMinUnitQty(detail.getMinUnitQty());
				createDetail.setExpectUnitQty(detail.getExpectUnitQty());
				createDetail.setExpectMinUnitQty(detail.getExpectMinUnitQty());
				createDetail.setSaleTypeId(detail.getSaleType().getId());
				createDetail.setSysBatchNo(detail.getSysBatchNo());
				createDetail.setPackId(detail.getSku().getGoodsPack().getId());
				
				createDetails.add(createDetail);
			}
		}
		
		stockTakeOrderBillCreateParamsModel.setDetails(createDetails);
		
		return stockTakeOrderBillCreateParamsModel;
		
	}
	
}
