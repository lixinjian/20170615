package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.ScreenUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailCreateParamsModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskBillService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutawayTaskDetailEditAdapter;
import com.ds365.swipelistview.SwipeMenu;
import com.ds365.swipelistview.SwipeMenuCreator;
import com.ds365.swipelistview.SwipeMenuItem;
import com.ds365.swipelistview.SwipeMenuListView;
import com.ds365.swipelistview.SwipeMenuListView.OnMenuItemClickListener;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PutawayTaskDetailEditActivity extends BasePdaActivity {

	private DateField makeTimeValue;
	private TextView saveButton,addButton;
	private TextView expectUnitQtyValue,expectMinUnitQtyValue;
	private TextView specValue,specQtyValue,skuNameValue,skuCodeValue;
	private TextView unitNameValue,billCodeValue,relatedBillCodeValue;
	private SwipeMenuListView listView;
	private PutawayTaskDetailModel putawayTaskDetailModel;

	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	public static final String SER_KEY = PdaConstants.nextSerKey();
	private List<PutawayTaskDetailModel> putawayTaskDetails = new ArrayList<PutawayTaskDetailModel>();
	private PutawayTaskDetailEditAdapter adapter;
	private PutawayTaskBillService putawayTaskBillService = new PutawayTaskBillService();
	
	@Override
	protected void initActivityView() {
		listView = (SwipeMenuListView) findViewById(R.id.putawayTaskDetailEdit_listView);
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		addButton = (TextView) findViewById(R.id.putawayTaskDetailEdit_addButton);
		specValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_spec_value);
		specQtyValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_specQty_value);
		skuNameValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_skuName_value);
		skuCodeValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_skuCode_value);
		unitNameValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_unitName_value);
		relatedBillCodeValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_relatedBillCode_value);
		billCodeValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_billCode_value);
		makeTimeValue = (DateField) findViewById(R.id.putawayTaskDetailEdit_makeTime_value);
		saveButton = (TextView) findViewById(R.id.putawayTaskDetailEdit_save_button);
		expectUnitQtyValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_expectUnitQty_value);
		expectMinUnitQtyValue = (TextView) findViewById(R.id.putawayTaskDetailEdit_expectMinUnitQty_value);
		
		putawayTaskDetailModel = (PutawayTaskDetailModel) getIntent()
				.getSerializableExtra(PutawayTaskDetailActivity.SER_KEY);
		putawayTaskDetails.add(putawayTaskDetailModel);
		skuNameValue.setText(putawayTaskDetailModel.getSku().getGoods().getName());
		skuCodeValue.setText(putawayTaskDetailModel.getSku().getCode());
		unitNameValue.setText(putawayTaskDetailModel.getUnitName());
		relatedBillCodeValue.setText(putawayTaskDetailModel.getBill().getRelatedBillCode());
		billCodeValue.setText(putawayTaskDetailModel.getBill().getBillCode());
		specValue.setText(putawayTaskDetailModel.getSku().getSpec());
		specQtyValue.setText(String.valueOf(putawayTaskDetailModel.getSpecQty()));
		makeTimeValue.setValue(putawayTaskDetailModel.getBill().getMakeTime());
		putawayTaskDetailModel.setExpectQty(putawayTaskDetailModel.getQty());
		
		QtyModel qtyModel = QtyMoneyUtils.getQtyEntity(putawayTaskDetailModel.getExpectQty(), putawayTaskDetailModel.getSpecQty());
		
		expectUnitQtyValue.setText(String.valueOf(qtyModel.getUnitQty()));
		expectMinUnitQtyValue.setText(String.valueOf(qtyModel.getMinUnitQty()));
		
		adapter = new PutawayTaskDetailEditAdapter(context, putawayTaskDetails);
		listView.setAdapter(adapter);
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				SwipeMenuItem openItem = new SwipeMenuItem(context);
				openItem.setBackground(new ColorDrawable(Color.rgb(221, 39, 39)));
				openItem.setWidth(ScreenUtils.dip2px(context, 90));
				openItem.setTitle(R.string.delete);
				openItem.setTitleSize(18);
				openItem.setTitleColor(Color.WHITE);
				menu.addMenuItem(openItem);
			}
		};
		listView.setMenuCreator(creator);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawayTaskDetailEdit_headerview, R.string.putaway_task_detail_view);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_detail_edit;
	}

	@Override
	protected void setListener() {
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				DialogUtils.createConfirmDialog(context, getString(R.string.dialog_save_title)
						, getString(R.string.dialog_save_putaway_task_detail), true, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								savePutawayTaskDetail();
							}
				});
			}
		});
		
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				PutawayTaskDetailModel newPutawayTaskDetail  = new PutawayTaskDetailModel();
				newPutawayTaskDetail.setSpecQty(putawayTaskDetailModel.getSpecQty());
				newPutawayTaskDetail.setUnitQty(0);
				newPutawayTaskDetail.setMinUnitQty(0);
				newPutawayTaskDetail.setSku(putawayTaskDetailModel.getSku());
				newPutawayTaskDetail.setSysBatchNo(putawayTaskDetailModel.getSysBatchNo());
				putawayTaskDetails.add(newPutawayTaskDetail);
				adapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
				if(position!=0){
	                adapter.deleteItem(position);
				}else{
					T.showShort(context, "默认第一条数据不能删除！");
				}
				return false;
			}
		});
	}
	
	private void savePutawayTaskDetail(){
		Set<String> shelfCodes = new HashSet<String>();
		Integer qty = 0;
		for(PutawayTaskDetailModel putawayTaskDetail : putawayTaskDetails){
			
			if(null == putawayTaskDetail.getShelf() || null == putawayTaskDetail.getStore()){
				T.showShort(context, "请输入货位号并带出库区货位信息！");
				return;
			}
			
			if(shelfCodes.contains(putawayTaskDetail.getShelf().getCode())){
				T.showShort(context, "货位不能重复!");
				return;
			}else{
				shelfCodes.add(putawayTaskDetail.getShelf().getCode());
			}
			
			if(putawayTaskDetail.getUnitQty() == 0  || putawayTaskDetail.getUnitQty() == null
					&& putawayTaskDetail.getMinUnitQty() == 0 || putawayTaskDetail.getMinUnitQty() == null){
				T.showShort(context, "上架的件数和散数不能同时为0");
				return;
			}
			qty += putawayTaskDetail.getQty();
		}
		
		if(qty.intValue() != putawayTaskDetailModel.getExpectQty().intValue()){
			T.showShort(context, "修改后的明细数量不等于原始数量，请重新编辑");
			return;
		}
		//model的转换
		List<PutawayTaskDetailCreateParamsModel> details = setupPutawayTaskDetailCreateParamsModel(putawayTaskDetails);
		putawayTaskBillService.modifyPutawayTaskDetail(details, new AbstractServiceCallBack<String>(PutawayTaskDetailEditActivity.this) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "修改成功");
				finish();
			}

			@Override
			public void doFaile(String str) {
				T.showLong(context,str);
			}
		});
	}
	
	private List<PutawayTaskDetailCreateParamsModel> setupPutawayTaskDetailCreateParamsModel(List<PutawayTaskDetailModel> list){
		
		List<PutawayTaskDetailCreateParamsModel> details = new ArrayList<PutawayTaskDetailCreateParamsModel>();
		for(PutawayTaskDetailModel putawayTaskDetail : list){
			PutawayTaskDetailCreateParamsModel putawayTaskDetailCreateParams = new PutawayTaskDetailCreateParamsModel();
			putawayTaskDetailCreateParams.setId(putawayTaskDetail.getId());
			putawayTaskDetailCreateParams.setShelfCode(putawayTaskDetail.getShelfCode());
			putawayTaskDetailCreateParams.setShelfId(putawayTaskDetail.getShelf().getId());
			putawayTaskDetailCreateParams.setStoreId(putawayTaskDetail.getStore().getId());
			putawayTaskDetailCreateParams.setSysBatchNo(putawayTaskDetail.getSysBatchNo());
			putawayTaskDetailCreateParams.setUnitQty(putawayTaskDetail.getUnitQty());
			putawayTaskDetailCreateParams.setMinUnitQty(putawayTaskDetail.getMinUnitQty());
			details.add(putawayTaskDetailCreateParams);
		}
		return details;
	}
}
