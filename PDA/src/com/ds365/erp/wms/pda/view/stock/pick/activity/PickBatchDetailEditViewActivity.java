package com.ds365.erp.wms.pda.view.stock.pick.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.utils.StringUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.IntEditField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.QtyMoneyUtils;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.service.pick.PickService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaListActivity;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBatchDetailAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 拣货下架 - 商品批次明细 	
 * @author lxj
 * 			
 *			加载此页数据必须传入billId和skuId,否则程序抛出异常
 */
public class PickBatchDetailEditViewActivity extends BasePdaListActivity implements OnClickListener{
	private PullToRefreshListView listView;
	private PickBatchDetailAdapter adapter;
	private TextView saveButton,addLineButton,unitNameValue, expectUnitQtyValue, expectMinUnitQtyValue, expectQtyValue;
	private TextView skuNameValue, storeNameValue,qtyValue, shelfCodeValue, sysBatchNoValue,specValue;
	private TextView skuCodeValue;
	private IntEditField unitQtyValue,minUnitQtyValue; 
	
	private List<PickBatchDetailModel> details = new ArrayList<PickBatchDetailModel>();
	private PickBatchDetailModel pickBatchDetailModel = new PickBatchDetailModel();
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	public static final int RESULT_CODE = PdaConstants.nextResultCode();
	
	private PickService pickService = new PickService();
	
	private Intent intent;
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_batch_detail_edit_view;
	}
	
	@Override
	protected void initActivityView() {
		
		intent = getIntent();
		
		pickBatchDetailModel = (PickBatchDetailModel) intent.getSerializableExtra(PickBillRegistActivity.SER_KEY);
		
		
		skuCodeValue = (TextView) findViewById(R.id.pickBatchDetailEditView_skuCode_value);
		shelfCodeValue = (TextView) findViewById(R.id.pickBatchDetailEditView_shelfCode_value);
		sysBatchNoValue = (TextView) findViewById(R.id.pickBatchDetailEditView_sysBatchNo_value);
		skuNameValue = (TextView) findViewById(R.id.pickBatchDetailEditView_skuName_value);
		storeNameValue = (TextView) findViewById(R.id.pickBatchDetailEditView_storeName_value);
		unitQtyValue = (IntEditField) findViewById(R.id.pickBatchDetailEditView_unitQty_value);
		minUnitQtyValue = (IntEditField) findViewById(R.id.pickBatchDetailEditView_minUnitQty_value);
		qtyValue = (TextView) findViewById(R.id.pickBatchDetailEditView_qty_value);
		unitNameValue = (TextView) findViewById(R.id.pickBatchDetailEditView_unitName_value);
		expectUnitQtyValue = (TextView) findViewById(R.id.pickBatchDetailEditView_expectUnitQty_value);
		expectMinUnitQtyValue = (TextView) findViewById(R.id.pickBatchDetailEditView_expectMinUnitQty_value);
		expectQtyValue = (TextView) findViewById(R.id.pickBatchDetailEditView_expectQty_value);
		saveButton = (TextView) findViewById(R.id.pickBatchDetailEditView_saveButton);
		addLineButton = (TextView) findViewById(R.id.pickBatchDetailEditView_addLineButton);
		listView = (PullToRefreshListView) findViewById(R.id.pickBatchDetailEditView_details);
		specValue = (TextView) findViewById(R.id.pickBatchDetailEditView_spec_value);
		skuCodeValue.setText(pickBatchDetailModel.getSku().getCode());
//		shelfCodeValue.setText(pickBatchDetailModel.getShelf().getCode());
		sysBatchNoValue.setText(pickBatchDetailModel.getSysBatchNo());
		skuNameValue.setText(pickBatchDetailModel.getSku().getName());
//		storeNameValue.setText(pickBatchDetailModel.getStore().getName());
		unitNameValue.setText(pickBatchDetailModel.getUnitName());
		expectUnitQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectUnitQty()));
		expectMinUnitQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectMinUnitQty()));
		expectQtyValue.setText(String.valueOf(pickBatchDetailModel.getExpectQty()));
		if(pickBatchDetailModel.getPickUnitQty() != null)
			unitQtyValue.setValue(pickBatchDetailModel.getPickUnitQty());
		if(pickBatchDetailModel.getPickMinUnitQty() != null)
			minUnitQtyValue.setValue(pickBatchDetailModel.getPickMinUnitQty());
		specValue.setText(pickBatchDetailModel.getSpec());
		ListViewUtil.setOnPullBothListView(listView);
		adapter = new PickBatchDetailAdapter(context, details );
		listView.setAdapter(adapter);
//		params.getParams().put("billId", String.valueOf(billId));
//		params.getParams().put("skuId", String.valueOf(skuId));
//		params.setUrl(ConstantUrl.PICK_BATCH_DETAIL);
//		getData(params);
		
	}
	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBatchDetailEditView_headerView, R.string.goods_batch_detail);
	}

	@Override
	protected void setListener() {
		saveButton.setOnClickListener(this);
		addLineButton.setOnClickListener(this);
//		listView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Intent intent = new Intent(context, PickBillDetailEditActivity.class);
//				
//				intent.putExtra("shelfCode", details.get(position-1).getShelf().getCode());
//				intent.putExtra("skuBatch", details.get(position-1).getSysBatchNo());
//				intent.putExtra("actualPickUnitQty", details.get(position-1).getPickMinUnitQty());
//				intent.putExtra("actualPickMinUnitQty", details.get(position-1).getPickUnitQty());
//				intent.putExtra("shouldPickMinUnitQty", details.get(position-1).getPickMinUnitQty());
//				intent.putExtra("shouldPickUnitQty", details.get(position-1).getPickUnitQty());
//				
//				startActivityForResult(intent, 1);
//			}
//		});
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {

			@Override
			public void onPullDown() {
				getData();
			}

		}));
		
		
		/**
		 * 监听界面件数、散数的数据变化,计算出总数量
		 */
		/*TextWatcher textWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Integer pickUnitQty = calPickUntiQty();
				
				Integer pickMinUnitQty = calPickMinUintQty();
				
				 
				Integer pickQty = calPickQty(pickUnitQty, pickMinUnitQty);
				qtyValue.setText(String.valueOf(pickQty));
			}
		};
		
		unitQtyValue.addTextChangedListener(textWatcher);
		
		minUnitQtyValue.addTextChangedListener(textWatcher);*/
		
		TextWatcher textWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Integer pickUnitQty = calPickUntiQty();
				Integer pickMinUnitQty = calPickMinUintQty();
				Integer pickQty = calPickQty(pickUnitQty, pickMinUnitQty);
				qtyValue.setText(String.valueOf(pickQty));
			}
		};
		
		unitQtyValue.getEditText().addTextChangedListener(textWatcher);
		minUnitQtyValue.getEditText().addTextChangedListener(textWatcher);
		
	}
	
	private void getData() {
		details.clear();
		
		pickService.searchPickBatchDetailByBillId(pickBatchDetailModel.getId(), new AbstractServiceCallBack<List<PickBatchDetailModel>>(context) {
			
			@Override
			public void doSuccess(List<PickBatchDetailModel> result) {
				if (result != null && result.size() > 0) {
					details.addAll(result);
					adapter.notifyDataSetChanged();
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
			}
		});
	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		
//		if (null != data) {
//			if (resultCode == PickBillDetailEditActivity.PICK_BILL_DETAIL_EDIT_RESULT_CODE) {
//				int actualPickUnitQty = data.getExtras().getInt("actualPickUnitQty");
//				int actualPickMinUnitQty = data.getExtras().getInt("actualPickMinUnitQty");
//				
//			}
//		}
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pickBatchDetailEditView_addLineButton:
			T.showShort(context, "增行");
			break;
		case R.id.pickBatchDetailEditView_saveButton:
			
			
			Integer pickUnitQty = calPickUntiQty();	
			Integer pickMinUnitQty = calPickMinUintQty();
			Integer pickQty = calPickQty(pickUnitQty, pickMinUnitQty);
			
			pickBatchDetailModel.setPickUnitQty(pickUnitQty);
			pickBatchDetailModel.setPickMinUnitQty(pickMinUnitQty);
			pickBatchDetailModel.setPickQty(pickQty);
			Bundle bundle=new Bundle();
	    	
	    	bundle.putSerializable(SER_KEY,pickBatchDetailModel);  
	    	intent.putExtras(bundle);
			
	    	setResult(RESULT_CODE, intent);
			T.showShort(context, "保存成功");
			finish();
			break;
		}
	}

	private Integer calPickQty(Integer pickUnitQty, Integer pickMinUnitQty) {
		Integer specQty = pickBatchDetailModel.getSpecQty();
		Integer pickQty = QtyMoneyUtils.getQty(pickUnitQty, pickMinUnitQty, specQty);
		return pickQty;
	}

	private Integer calPickMinUintQty() {
		Integer pickMinUnitQty=null;
		if ("".equals(minUnitQtyValue.getEditText().getText().toString())) {
			minUnitQtyValue.setValue(0);
		}
		String minUintQtyStr=String.valueOf(minUnitQtyValue.getValue());
		if(StringUtils.isBlank(minUintQtyStr))
			pickMinUnitQty=StringUtils.isEmptyInt(minUintQtyStr);
		pickMinUnitQty = Integer.parseInt(minUintQtyStr);
		return pickMinUnitQty;
	}

	private Integer calPickUntiQty() {
		Integer pickUnitQty=null;
		if ("".equals(unitQtyValue.getEditText().getText().toString())) {
			unitQtyValue.setValue(0);
		}
		String unitQtyStr=String.valueOf(unitQtyValue.getValue());
		
		if(StringUtils.isBlank(unitQtyStr))
			pickUnitQty=StringUtils.isEmptyInt(unitQtyStr);
		pickUnitQty = Integer.parseInt(unitQtyStr);
		return pickUnitQty;
	}

}
