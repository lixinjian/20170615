package com.ds365.erp.wms.pda.view.stock.putaway.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.utils.DialogUtils;
import com.ds365.commons.utils.StringUtils;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.SubmitInfo;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskCreateParamsModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskService;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.stock.putaway.activity.PutAwayTaskCreateActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.activity.PutAwayTaskListActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutAwayTaskForChoiceAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class PutawayTaskByChoiceFragment extends BaseFragment implements OnClickListener{
	private Context context;
	
	private TextView storeNameValue,storeIdValue;
	
	private PullToRefreshListView listView;
	private PutAwayTaskForChoiceAdapter adapter;
	private TextView putawayType;
	private TextView submitButton,add,skuNameValue,specValue;
	private EditText skuCodeValue;
	private ArrayAdapter<String> storeAdapter;
//	private JsonParser<JsonResult<List<上架类型Model>>> wareHouseJsonParser;
	private Long storeId;
	
	private List<PutawayTaskCreateParamsModel> arrayList = new ArrayList<PutawayTaskCreateParamsModel>();
	
	private boolean isAdd;
	
	private PutawayTaskService putawayTaskService = new PutawayTaskService();
	
	@Override
	protected void initFragmentRequestNet() {
		
	}

	@SuppressLint("NewApi")
	@Override
	protected View initFragmentView(LayoutInflater inflater) {
		view=inflater.inflate(R.layout.stock_putaway_task_by_choice, null);
		context = getActivity();
		storeNameValue = (TextView) findViewById(R.id.putawaytask_storeName_value);
		storeIdValue = (TextView) findViewById(R.id.putawaytask_storeId_value);
		submitButton = (TextView) findViewById(R.id.putawaytask_submitButton);
		listView = (PullToRefreshListView) findViewById(R.id.putawaytask_lv);
		skuNameValue = (TextView) findViewById(R.id.putawaytask_skuName_value);
		specValue = (TextView) findViewById(R.id.putawaytask_specName_value);
		listView.setMode(Mode.DISABLED);
		putawayType = (TextView) findViewById(R.id.putawaytask_putawayType_value);
		add = (TextView) findViewById(R.id.putawaytask_add);
		skuCodeValue = (EditText) findViewById(R.id.putawaytask_skuCodeEdt);
		
		submitButton.setOnClickListener(this);
		add.setOnClickListener(this);
		
		initSpinner();
		
		adapter = new PutAwayTaskForChoiceAdapter(context, arrayList);
		listView.setAdapter(adapter);
		
		/**
		 * 当输入完sku条码时,即离开焦点时触发此事件.
		 */
		skuCodeValue.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (skuCodeValue.getText().toString().length()!=0 && !hasFocus) {
//					TODO:当编辑框发生变化后所做的操作: 条码采集器扫码后自动搜索该skuCode下的商品并展示出商品列表页面
					Intent intent = new Intent(context, PutAwayTaskListActivity.class);
					getActivity().startActivityForResult(intent, 1);
				}
			}
		});
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context, PutAwayTaskCreateActivity.class);
				getActivity().startActivityForResult(intent, 1);
				
			}
		});
		
//		storeNameValue.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				InitStoreData.initStore(context,storeNameValue,storeIdValue);
//			}
//		});
		
		return view;
	}

	private void initSpinner() {
//		TODO:此处为网络获取得到 putawayTypeList 中数据,填充到spinner中,
		final String [] wareHouseType = { "全部","收货入库","退货入库", "调拨入库" };
		
		//此处为网络获取上架类型
//		wareHouseJsonParser = new JsonParser<JsonResult<List<WarehouseModel>>>() {};
//		RequestUtil.request(params, new AbstractResponseHandler<JsonResult<List<WarehouseModel>>>(context, wareHouseJsonParser) {
//			
//			@Override
//			public void doSuccess(JsonResult<List<WarehouseModel>> result) {
//				
//			}
//			
//			@Override
//			public void doFaile(String str) {
//				
//			}
//		});
		
		putawayType.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(context).setItems(
						wareHouseType , new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								putawayType.setText(wareHouseType[which]);
								dialog.dismiss();
							}
						}).show();
			}
		});
	}

	private void submitList() {
		
		/*putawayTaskService.createPutawayTask(createParams, new AbstractServiceCallBack<String>(context) {

			@Override
			public void doSuccess(String data) {
				T.showShort(context, "提交成功");
				arrayList.clear();
			}
			
			@Override
			public void doFaile(String str) {
				T.showShort(context, str);
			}
		});*/
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data) {
//			接收上架任务单商品信息   start
			String spec = StringUtils.isEmptyString(data.getExtras().getString("spec"));
			String skuName = StringUtils.isEmptyString(data.getExtras().getString("skuName"));
			int qty = data.getExtras().getInt("qty");
			Long skuId = data.getExtras().getLong("skuId");
			Long putawayTypeId = data.getExtras().getLong("putawayTypeId");
			Long storeId = data.getExtras().getLong("storeId");
			Long shelfId = data.getExtras().getLong("shelfId");
			Long relativeBillId = data.getExtras().getLong("relativeBillId");
			Long relativedDetailId = data.getExtras().getLong("relativedDetailId");
			String sysBatchNo = data.getExtras().getString("sysBatchNo");
			
			//把以上这些传到上一页1013
			
			
			
			
//			skuNameTV.setText(String.format(getResources().getString(R.string.skuName), skuName));
//			specTV.setText(String.format(getResources().getString(R.string.specName), spec));
//			end
			
//			保存上架所提交的信息
//			PutawayTaskCreateParamsModel putawayTaskCreateParams = new PutawayTaskCreateParamsModel();
//			putawayTaskCreateParams.setId(Long.valueOf(123));
//			putawayTaskCreateParams.setQty(qty);
////			putawayTaskCreateParams.setMinUnitQty(minUnitQty);
//			putawayTaskCreateParams.setPutawayTypeId(putawayTypeId);
//			putawayTaskCreateParams.setRelativeBillId(relativeBillId);
//			putawayTaskCreateParams.setRelativedDetailId(relativedDetailId);
//			putawayTaskCreateParams.setShelfId(shelfId);
//			putawayTaskCreateParams.setSkuId(skuId);
//			putawayTaskCreateParams.setStoreId(storeId);
////			putawayTaskCreateParams.setUnitQty(unitQty);
//			putawayTaskCreateParams.setSysBatchNo(sysBatchNo);
////			putawayTaskCreateParams.setTotalCount(totalCount);
//			SubmitInfo.setPutawayTaskCreateInf(putawayTaskCreateParams);
			
			
		}
	}

	
	@Override
	protected void initFragmentChildView(View view) {
		
	}

	@Override
	protected void initFragmentData(Bundle savedInstanceState) {
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.putawaytask_submitButton:
			DialogUtils.createConfirmDialog(context, getString(R.string.dialog_submit_title)
					, getString(R.string.dialog_submit_putawaytaskbill), true, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					submitList();
				}
			});
			break;
		case R.id.putawaytask_add:
			if (isAdd == true) {
				arrayList.add(0,SubmitInfo.getPutawayTaskCreateInfo());
				adapter.notifyDataSetChanged();  
			}
			isAdd = false;
			break;
		}
	}
	
}
