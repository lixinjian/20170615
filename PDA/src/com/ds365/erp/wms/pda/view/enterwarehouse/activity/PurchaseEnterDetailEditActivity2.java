package com.ds365.erp.wms.pda.view.enterwarehouse.activity;

import com.ds365.commons.utils.ScreenUtils;
import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.model.commons.QtyModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseEnterDetailModel;
import com.ds365.erp.wms.pda.model.purchase.PurchaseOrderDetailModel;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.enterwarehouse.adapter.PurchaseEnterDetailEditAdapter2;
import com.ds365.swipelistview.SwipeMenu;
import com.ds365.swipelistview.SwipeMenuCreator;
import com.ds365.swipelistview.SwipeMenuItem;
import com.ds365.swipelistview.SwipeMenuListView;
import com.ds365.swipelistview.SwipeMenuListView.OnMenuItemClickListener;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 收货入库-订单详情-编辑页面
 *
 */
public class PurchaseEnterDetailEditActivity2 extends BasePdaListActivity implements OnClickListener{
	private TextView saveButton,addButton;	//保存按钮
	private TextView skuCodeValue;
	private TextView skuNameValue;
	private TextView unitNameValue;
	private Intent intent;
	
	private PurchaseOrderDetailModel purchaseOrderDetailModel;
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private PurchaseEnterDetailEditAdapter2 adapter;
	private SwipeMenuListView listView;
	
	@Override
	protected int getContentViewId() {
		return R.layout.enterwarehouse_purchase_enter_detail_edit2;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.purchaseEnterDetailEdit_headerview, R.string.purchase_enter_detail_edit);
	}

	@Override
	protected void initActivityView() {
		
		intent = getIntent();
		saveButton = (TextView) findViewById(R.id.purchaseEnterDetailEdit_saveButton);
		addButton = (TextView) findViewById(R.id.purchaseEnterDetailEdit_addButton);
		unitNameValue = (TextView) findViewById(R.id.purchaseEnterDetailEdit_unitName_value);
		skuCodeValue = (TextView) findViewById(R.id.purchaseEnterDetailEdit_skuCode_value);
		skuNameValue = (TextView) findViewById(R.id.purchaseEnterDetailEdit_skuName_value);
		listView = (SwipeMenuListView) findViewById(R.id.purchaseEnterDetailEdit_listView);
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		initData();
	}
	
	private void initData(){
		purchaseOrderDetailModel=(PurchaseOrderDetailModel)intent.getSerializableExtra(PurchaseEnterBillActivity2.SER_KEY);
		unitNameValue.setText(purchaseOrderDetailModel.getUnitName());
		skuCodeValue.setText(purchaseOrderDetailModel.getSku().getCode());
		skuNameValue.setText(purchaseOrderDetailModel.getSku().getName());
		adapter = new PurchaseEnterDetailEditAdapter2(context,purchaseOrderDetailModel.getPurchaseEnterDetails());
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
		
		listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                adapter.deleteItem(position);
                adapter.notifyDataSetChanged();
				return false;
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.purchaseEnterDetailEdit_saveButton:
			
			int actualQty = 0;
			for(int i = 0;i<purchaseOrderDetailModel.getPurchaseEnterDetails().size();i++){
				if(purchaseOrderDetailModel.getPurchaseEnterDetails().get(i).getProduceDate() == null
						||"".equals(purchaseOrderDetailModel.getPurchaseEnterDetails().get(i).getProduceDate())){
					T.showShort(context, "生产日期不能为空！");
					return;
				}
				if(purchaseOrderDetailModel.getPurchaseEnterDetails().get(i).getProduceBatchNo() == null 
						|| "".equals(purchaseOrderDetailModel.getPurchaseEnterDetails().get(i).getProduceBatchNo())){
					T.showShort(context, "生产批次不能为空！");
					return;
				}
				actualQty = actualQty+purchaseOrderDetailModel.getPurchaseEnterDetails().get(i).getQty();
			}
			if(actualQty>purchaseOrderDetailModel.getRemainQty()){
				T.showShort(context, "输入的明细总数量不能大于应收总数量！");
				return;
			}
			
			Bundle bundle=new Bundle();
	    	bundle.putSerializable(SER_KEY,purchaseOrderDetailModel);  
	    	intent.putExtras(bundle);
	    	setResult(RESULT_CODE, intent);
			finish();
			break;
			
		case R.id.purchaseEnterDetailEdit_addButton:
			
			for(PurchaseEnterDetailModel purchaseEnterDetail : purchaseOrderDetailModel.getPurchaseEnterDetails()){
				//验证生产日期和生产批次不能为空
				if(null == purchaseEnterDetail.getProduceBatchNo() 
						|| "".equals(purchaseEnterDetail.getProduceBatchNo()) 
						|| null == purchaseEnterDetail.getProduceDate()
						|| "".equals(purchaseEnterDetail.getProduceDate())){
					T.showShort(context, "请将数据录入完整再进行添加");
					return;
				}
				//验证实收件数和散数不能同时为0
				if(purchaseEnterDetail.getUnitQty() == 0 && purchaseEnterDetail.getMinUnitQty() == 0){
					T.showShort(context, "实收件数和散数不能同时为0");
					return;
				}
			}
			
			int totalQty = 0;
			for(int i = 0;i<purchaseOrderDetailModel.getPurchaseEnterDetails().size();i++){
				totalQty = totalQty+purchaseOrderDetailModel.getPurchaseEnterDetails().get(i).getQty();
			}
			int remainQty = purchaseOrderDetailModel.getRemainQty()-totalQty;
			if(remainQty>0){
				PurchaseEnterDetailModel newPurchaseEnterDetail = new PurchaseEnterDetailModel();
				newPurchaseEnterDetail.setRelatedDetailId(purchaseOrderDetailModel.getId());
				newPurchaseEnterDetail.setSpecQty(purchaseOrderDetailModel.getSpecQty());
				newPurchaseEnterDetail.setSku(purchaseOrderDetailModel.getSku());
				newPurchaseEnterDetail.setUnitName(purchaseOrderDetailModel.getUnitName());
				newPurchaseEnterDetail.setSpecQty(purchaseOrderDetailModel.getSpecQty());
				newPurchaseEnterDetail.setStore(purchaseOrderDetailModel.getPurchaseEnterDetails().get(0).getStore());
				newPurchaseEnterDetail.setShelf(purchaseOrderDetailModel.getPurchaseEnterDetails().get(0).getShelf());
				newPurchaseEnterDetail.setShelfCode(purchaseOrderDetailModel.getPurchaseEnterDetails().get(0).getShelfCode());
				newPurchaseEnterDetail.setPack(purchaseOrderDetailModel.getPack());
				newPurchaseEnterDetail.setPackType(purchaseOrderDetailModel.getPackType());
				newPurchaseEnterDetail.setSpec(purchaseOrderDetailModel.getSpec());
				newPurchaseEnterDetail.setSpecQty(purchaseOrderDetailModel.getSpecQty());
				QtyModel qtyModel = QtyMoneyUtils.getQtyEntity(remainQty, purchaseOrderDetailModel.getSpecQty());
				newPurchaseEnterDetail.setQty(remainQty);
				newPurchaseEnterDetail.setUnitQty(qtyModel.getUnitQty());
				newPurchaseEnterDetail.setMinUnitQty(qtyModel.getMinUnitQty());
				purchaseOrderDetailModel.getPurchaseEnterDetails().add(newPurchaseEnterDetail);
				adapter.notifyDataSetChanged();
			}else{
				T.showLong(context,"剩余数量为0，不可添加！");
			}
			
			break;
		}
	}

	@Override
	protected void setListener() {
		saveButton.setOnClickListener(this);
		addButton.setOnClickListener(this);
	}
}