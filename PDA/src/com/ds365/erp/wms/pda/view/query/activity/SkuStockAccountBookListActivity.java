package com.ds365.erp.wms.pda.view.query.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuModel;
import com.ds365.erp.wms.pda.model.goods.GoodsSkuQueryParamsModel;
import com.ds365.erp.wms.pda.model.stock.SkuStockAccountBookModel;
import com.ds365.erp.wms.pda.model.stock.SkuStockAccountBookQueryParamsModel;
import com.ds365.erp.wms.pda.service.goodssku.GoodsSkuService;
import com.ds365.erp.wms.pda.service.stock.SkuStockAccountBookService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.activity.GoodsSkuSelectorActivity;
import com.ds365.erp.wms.pda.view.query.adapter.SkuStockAccountBookAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
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
 * 说明 ：分页查询商品进销存账页
 */
public class SkuStockAccountBookListActivity extends BasePdaPageActivity {

	private DateEditField startTimeValue;
	private DateEditField endTimeValue;
	private EditText barCodeValue;
	private ImageView barCodeScanButton;
	private TextView skuNameValue, specValue, unitNameValue;
	private TextView queryButton;
	private PullToRefreshListView listView;

	private Long skuId;
	
	private SkuStockAccountBookAdapter adapter;
	private List<SkuStockAccountBookModel> details = new ArrayList<SkuStockAccountBookModel>();

	public static final String SER_KEY = PdaConstants.nextSerKey();

	private SkuStockAccountBookService skuStockAccountBookService = new SkuStockAccountBookService();
	private GoodsSkuService goodsSkuService = new GoodsSkuService();
	private SkuStockAccountBookQueryParamsModel skuStockAccountBookQueryParamsModel = new SkuStockAccountBookQueryParamsModel();
	@Override
	protected void initNavigation() {
		initHeadView(R.id.skuStockAccountBookList_headerview, R.string.query_sku_stock_account_book);
	} 

	@Override
	protected int getContentViewId() {
		return R.layout.query_sku_stock_account_book_list;
	}

	@Override
	protected void initActivityView() {
		skuNameValue = (TextView) findViewById(R.id.skuStockAccountBookList_skuName_value);
		specValue = (TextView) findViewById(R.id.skuStockAccountBookList_spec_value);
		unitNameValue = (TextView) findViewById(R.id.skuStockAccountBookList_unitName_value);
		queryButton = (TextView) findViewById(R.id.skuStockAccountBookList_queryButton);
		listView = (PullToRefreshListView) findViewById(R.id.skuStockAccountBookList_details);
		startTimeValue = (DateEditField) findViewById(R.id.skuStockAccountBookList_startTime_value);
		endTimeValue = (DateEditField) findViewById(R.id.skuStockAccountBookList_endTime_value);
		barCodeValue = (EditText) findViewById(R.id.skuStockAccountBookList_barCode_value);
		barCodeScanButton = (ImageView) findViewById(R.id.skuStockAccountBookList_barCodeScan_button);

		adapter = new SkuStockAccountBookAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
//		setFirstPage();
//		setParams();
//		searchPageSkuStockAccountBook(PdaConstants.CLEAR_LISTVIEW_YES);

	}

	@Override
	protected void setListener() {
		queryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(skuStockAccountBookQueryParamsModel.getSkuId() == null
						||"".equals(barCodeValue.getText().toString())){
					T.showShort(context, "请扫描sku条码");
					return;
				}
				setFirstPage();
				setParams();
				searchPageSkuStockAccountBook(PdaConstants.CLEAR_LISTVIEW_YES);
			}
		});

		barCodeScanButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(SkuStockAccountBookListActivity.this, CodeScanActivity.class,
						PdaConstants.PDA_REQUEST_CODE);
			}
		});
		
		barCodeValue.setOnEditorActionListener(new OnEditorActionListener() {
			
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
	
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
					setFirstPage();
					setParams();
					searchPageSkuStockAccountBook(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
					setNextPage();
					setParams();
					searchPageSkuStockAccountBook(PdaConstants.CLEAR_LISTVIEW_NO);
			}
		}));
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				SkuStockAccountBookModel model = details.get(position-1);
				IntentUtils.startActivityForSeria(context, SkuStockAccountBookDetailShowActivity.class, SER_KEY, model, null);
			}
		});

	}

	protected void setParams() {
		skuStockAccountBookQueryParamsModel.setStart(start);
		skuStockAccountBookQueryParamsModel.setLimit(PdaConstants.LIMIT);
		if(skuId != null)
			skuStockAccountBookQueryParamsModel.setSkuId(skuId);
		skuStockAccountBookQueryParamsModel.setStartTime(startTimeValue.getValue());
		skuStockAccountBookQueryParamsModel.setEndTime(endTimeValue.getValue());
	}

	private void searchPageSkuStockAccountBook(final int type) {
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		skuStockAccountBookService.searchPageSkuStockAccountBook(skuStockAccountBookQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<SkuStockAccountBookModel>>(context) {

					@Override
					public void doSuccess(QueryResult<SkuStockAccountBookModel> result) {
						if (result != null && result.getRecords().size() > 0) {
							details.addAll(result.getRecords());
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						// 数据刷新后通知适配器更新UI
						adapter.notifyDataSetChanged();
					}
				});
	}

	private void searchGoodsInfo(TextView v){
		final String fuzzy = v.getText().toString().trim();
		
		GoodsSkuQueryParamsModel goodsSkuQueryParamsModel = new GoodsSkuQueryParamsModel();
		goodsSkuQueryParamsModel.setFuzzy(fuzzy);
		
		goodsSkuService.searchPageGoodsSkuByParams(goodsSkuQueryParamsModel,
				new AbstractServiceCallBack<QueryResult<GoodsSkuModel>>(context) {

					@Override
					public void doSuccess(QueryResult<GoodsSkuModel> result) {
						long totalCount = result.getTotalCount();
						if (totalCount == 0) {
							T.showShort(context, "找不到该商品!");
							barCodeValue.setText("");
							barCodeValue.requestFocus();
						} else if (totalCount == 1) {
							GoodsSkuModel goodsSkuModel = result.getRecords().get(0);
							setViews(goodsSkuModel);

						} else if (totalCount > 1) {
							Intent intent = new Intent(context, GoodsSkuSelectorActivity.class);
							intent.putExtra(PdaConstants.fuzzy, fuzzy);
							startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
						}
					}
				});
	}

	private void setViews(GoodsSkuModel goodsSkuModel) {
		specValue.setText(goodsSkuModel.getSpec());
		unitNameValue.setText(goodsSkuModel.getGoodsPack().getName());
		barCodeValue.setText(goodsSkuModel.getBarcode());
		skuNameValue.setText(goodsSkuModel.getName());
		skuId = goodsSkuModel.getId();
		skuStockAccountBookQueryParamsModel.setSkuId(goodsSkuModel.getId());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data) {
			if (resultCode == CodeScanActivity.RESULT_CODE) {
				barCodeValue.setText(data.getStringExtra(PdaConstants.scanResult));
			}else if (resultCode == GoodsSkuSelectorActivity.RESULT_CODE) {
				GoodsSkuModel goodsSkuModel = (GoodsSkuModel)data.getSerializableExtra(GoodsSkuSelectorActivity.SER_KEY);
				setViews(goodsSkuModel);
			}
		}
	}

}
