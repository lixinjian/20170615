package com.ds365.erp.wms.pda.view.stock.stocktake.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderBillCreateParamsModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderBillModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.stocktake.StockTakeOrderDetailModel;
import com.ds365.erp.wms.pda.service.stocktake.StockTakeByShelfService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.stock.stocktake.adapter.StockTakeOrderDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * 
 * 说明 ：商品盘点
 */
public class StockTakeByGoodsSkuActivity extends BasePdaPageActivity {

	
	private TextView submitButton,addButton,extractButton;//提交,添加,提取按钮
	private PullToRefreshListView listView;
	
	private StockTakeOrderDetailAdapter adapter;
	
	private List<StockTakeOrderDetailModel> details = new ArrayList<StockTakeOrderDetailModel>();
	private StockTakeOrderBillModel stockTakeOrderBillModel = new StockTakeOrderBillModel();
	private StockTakeByShelfService stockTakeByShelfService = new StockTakeByShelfService();
	public static final String SER_KEY = PdaConstants.nextSerKey();

	private int selectPosition = 0;
	private static final int requestCodeForAddButton = Integer.parseInt(PdaConstants.nextRequestCode());
	private static final int requestCodeForItemClick = Integer.parseInt(PdaConstants.nextRequestCode());
	
	@Override
	protected void initActivityView() {
		submitButton = (TextView) findViewById(R.id.stockTakeByGoodsSku_submit_button);
		addButton = (TextView) findViewById(R.id.stockTakeByGoodsSku_add_button);
		extractButton = (TextView) findViewById(R.id.stockTakeByGoodsSku_extract_button);
		listView = (PullToRefreshListView) findViewById(R.id.stockTakeByGoodsSku_listView);
		
		adapter = new StockTakeOrderDetailAdapter(context, details);
		listView.setAdapter(adapter);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.stockTakeByGoodsSku_headerview, R.string.take_stock_by_goods_sku);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stocktake_stock_take_by_goods_sku;
	}

	@Override
	protected void setListener() {
		
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
		
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivityForResult(StockTakeByGoodsSkuActivity.this
						, StockTakeByGoodsSkuEditActivity.class, requestCodeForAddButton);
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityBySeriaForResult(StockTakeByGoodsSkuActivity.this
						, StockTakeByGoodsSkuEditActivity.class,SER_KEY,details.get(position-1),null,requestCodeForItemClick);
			}
		});
	}
	
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
				//提交成功后清空数据
				details.clear();
				adapter.notifyDataSetChanged();
			}
		});
	}
	
	
	private StockTakeOrderBillCreateParamsModel setupStockTakeOrderBillCreateParamsModel(StockTakeOrderBillModel bill,List<StockTakeOrderDetailModel>details) {
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (requestCode == requestCodeForAddButton && resultCode == StockTakeByGoodsSkuEditActivity.RESULT_CODE) {
				StockTakeOrderDetailModel detailModel = (StockTakeOrderDetailModel) intent.getSerializableExtra(StockTakeByGoodsSkuEditActivity.SER_KEY);
				detailModel.setCheckFlag(true);
				details.add(detailModel);
				adapter.notifyDataSetChanged();
			}else if(requestCode == requestCodeForItemClick && resultCode == StockTakeByGoodsSkuEditActivity.RESULT_CODE){
				StockTakeOrderDetailModel detailModel = (StockTakeOrderDetailModel) intent.getSerializableExtra(StockTakeByGoodsSkuEditActivity.SER_KEY);
				details.set(selectPosition, detailModel);
				details.get(selectPosition).setCheckFlag(true);
				adapter.notifyDataSetChanged();
			}
		}
	}
}
