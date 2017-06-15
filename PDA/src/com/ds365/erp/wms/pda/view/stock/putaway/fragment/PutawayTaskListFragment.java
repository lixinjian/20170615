package com.ds365.erp.wms.pda.view.stock.putaway.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.enums.EnumModel;
import com.ds365.commons.utils.MapItem;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.DropDownListView;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.putaway.PutAwayTaskModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskQueryParamModel;
import com.ds365.erp.wms.pda.service.enums.EnumService;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskService;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.common.fragment.BaseFragment;
import com.ds365.erp.wms.pda.view.stock.putaway.activity.PutAwayTaskForSingleActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.activity.PutAwayTaskListByBillCodeActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutAwayTaskListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class PutawayTaskListFragment extends BaseFragment implements OnClickListener{
	private Context context;
	
	private TextView submitButton;
	private DropDownListView<Long> putawayType;
	private PullToRefreshListView listView;
	private ImageView billCodeSearchButton, billCodeScanButton;
	private EditText billCodeValue;
	
	private List<PutAwayTaskModel> details = new ArrayList<PutAwayTaskModel>();
	
	private PutAwayTaskListAdapter adapter;
	
	private int selectPosition = 0;
	public static final String  SER_KEY = PdaConstants.nextSerKey();
	
	private PutawayTaskService putawayTaskService = new PutawayTaskService();
	private EnumService enumService = new EnumService();
	
	private PutawayTaskQueryParamModel putawayTaskQueryParamModel = new PutawayTaskQueryParamModel();
	
	@Override
	protected void initFragmentRequestNet() {
	}
	
	@Override
	public void onStart() {
		super.onStart();
		getPutawayType();
	}
	
	@Override
	protected View initFragmentView(LayoutInflater inflater) {
		view=inflater.inflate(R.layout.stock_putaway_task_list_fragment, null);
		context = getActivity();
		details.clear();
		submitButton = (TextView) view.findViewById(R.id.putawayTaskListFragment_submitButton);
		putawayType = (DropDownListView<Long>) view.findViewById(R.id.putawayTaskListFragment_putawayType_value);
		listView = (PullToRefreshListView) findViewById(R.id.putawayTaskListFragment_details);
		billCodeSearchButton = (ImageView) findViewById(R.id.putawayTaskListFragment_billCodeSearch_button);
		billCodeScanButton = (ImageView) findViewById(R.id.putawayTaskListFragment_billCodeScan_button);
		billCodeValue = (EditText) findViewById(R.id.putawayTaskListFragment_billCode_value);
		ListViewUtil.setOnPullBothListView(listView);
		submitButton.setOnClickListener(this);
		billCodeSearchButton.setOnClickListener(this);
		billCodeScanButton.setOnClickListener(this);
		adapter = new PutAwayTaskListAdapter(context, details);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Intent intent = new Intent(context, PutAwayTaskByBillCodeActivity.class);
				Intent intent = new Intent(context, PutAwayTaskForSingleActivity.class);
				selectPosition = position-1;
				
				Bundle bundle = new Bundle();  
		        bundle.putSerializable(SER_KEY,details.get(selectPosition));  
		        intent.putExtras(bundle);
		        
				getActivity().startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
				
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
					setFirstPage();
					putawayTaskQueryParamModel.setStart(start);
					getData(PdaConstants.CLEAR_LISTVIEW_YES);
			}

			@Override
			public void onPullUp() {
					setNextPage();
					putawayTaskQueryParamModel.setStart(start);
					getData(PdaConstants.CLEAR_LISTVIEW_NO);
			}
		}));
		
		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			if (resultCode == PutAwayTaskListByBillCodeActivity.RESULT_CODE) {
				
				PutAwayTaskModel putAwayTaskModel = (PutAwayTaskModel) intent.getSerializableExtra(PutAwayTaskListByBillCodeActivity.SER_KEY);
				details.set(selectPosition, putAwayTaskModel);
				adapter.notifyDataSetChanged();
			}else if (resultCode == CodeScanActivity.RESULT_CODE) {
				billCodeValue.setText(intent.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
	
	@Override
	protected void initFragmentChildView(View view) {
		
	}

	@Override
	protected void initFragmentData(Bundle savedInstanceState) {
		
	}
	
	private void getData(final int type){
		if (type == PdaConstants.CLEAR_LISTVIEW_YES) {
			details.clear();
		}
		putawayTaskService.searchPagePutawayTaskByParamsForPut(putawayTaskQueryParamModel, new AbstractServiceCallBack<List<PutAwayTaskModel>>(getActivity()) {
			
			@Override
			public void doSuccess(List<PutAwayTaskModel> result) {
				if (result != null && result.size() > 0) {
					details.addAll(result);
					listView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
			}
		});
	}
	
	/*private void submitList() {
		
		RequestParamsModel params=new RequestParamsModel();
		JsonParser<JsonResult<String>> submitJsonParser = new JsonParser<JsonResult<String>>() {};
		
		PutawayTaskCreateParamsModel createParams = setupPurchaseEnterBillCreateParamsModel(putAwayTaskModel,details);
		String submitJsonResult = JacksonUtils.toJson(createParams);
		params.setJsonBody(submitJsonResult);
		
		params.setUrl(ConstantUrl.putaway_putaway_createPutawayTask);
		
		RequestUtil.requestJson(context, params, new AbstractResponseHandler<JsonResult<String>>(context, submitJsonParser) {
			
			@Override
			public void doSuccess(JsonResult<String> result) {
				T.showShort(context, "提交成功");
				details.clear();
			}
			
			@Override
			public void doFaile(String str) {
				T.showShort(context, str);
			}
		});
	}*/
	
	/*private PutawayTaskCreateParamsModel setupPurchaseEnterBillCreateParamsModel(PutAwayTaskModel task,List<PutAwayTaskModel> details) {
		
		PutawayTaskCreateParamsModel putawayTaskCreateParamsModel = new PutawayTaskCreateParamsModel();
		putawayTaskCreateParamsModel.setId(task.getId());
		putawayTaskCreateParamsModel.setMinUnitQty(task.getRemainMinUnitQty());
		putawayTaskCreateParamsModel.setUnitQty(task.getRemainUnitQty());
//		putawayTaskCreateParamsModel.setPutawayTypeId(bill.getPutawayType().getId());
		putawayTaskCreateParamsModel.setQty(task.getQty());
		putawayTaskCreateParamsModel.setRelativeBillId(task.getRelativeBillId());
		putawayTaskCreateParamsModel.setRelativeDetailId(task.getRelativeDetailId());
		putawayTaskCreateParamsModel.setShelfId(task.getShelf().getId());
		putawayTaskCreateParamsModel.setShelfCode(task.getShelfCode());
		putawayTaskCreateParamsModel.setSkuId(task.getSku().getId());
		putawayTaskCreateParamsModel.setStoreId(task.getStore().getId());
		putawayTaskCreateParamsModel.setSysBatchNo(task.getSysBatchNo());
		
		return putawayTaskCreateParamsModel;
	}*/

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.putawayTaskListFragment_billCodeSearch_button:
			String billCode = billCodeValue.getText().toString();
			putawayTaskQueryParamModel.setFuzzy(billCode);
//			params.getParams().put("putawayTypeId", String.valueOf());
//			putawayTaskQueryParamModel.setPutawayTypeId(putawayType.getValue());
			getData(PdaConstants.CLEAR_LISTVIEW_YES);
			break;
		case R.id.putawayTaskListFragment_billCodeScan_button:
			Intent intent = new Intent(context, CodeScanActivity.class);
			startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
			break;
		/*case R.id.putawaytask_submitButton:
			DialogUtils.getDialog(context, R.string.dialog_submit_title, R.string.dialog_submit_putawaytaskbill, new DialogUtils.OnConfirmOrCancel() {
				
				@Override
				public void onConfirm(View dialog) {
					// TODO Auto-generated method stub
					submitList();
				}
			});
			break;*/
		}
	}

	private void getPutawayType() {
		//此处为网络获取上架类型
		
		/*enumService.getEnumsForJsonResult(new AbstractServiceCallBack<List<EnumModel<Long>>>(getActivity()) {
			
			@Override
			public void doSuccess(List<EnumModel<Long>> result) {
				List<EnumModel<Long>> list = new ArrayList<EnumModel<Long>>();
				list.addAll(result);
				Map<Integer,MapItem> map = new HashMap<Integer,MapItem>();
				MapItem childMap ;
				for(int i = 0; i < list.size(); i++){
					childMap = new MapItem(list.get(i).getId(), list.get(i).getName());
					map.put(i, childMap);
				}
				putawayType.setItemsData(map);
				
				setFirstPage();
//				params.getParams().put("putawayTypeId", String.valueOf(putawayType.getValue()));
				getData(PdaConstants.CLEAR_LISTVIEW_YES);
			}
		});*/
		putawayType.setUrl(ConstantUrl.common_getPutawayTypeEnumsForJsonResult);
	}
}