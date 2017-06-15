package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.AppConstants;
import com.ds365.commons.AppGlobal;
import com.ds365.commons.json.JacksonUtils;
import com.ds365.commons.message.model.MessageDatebaseModel;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.CommonTitleTab;
import com.ds365.commons.widget.CommonTitleTab.SelectChangeListener;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.headtitlebar.SubHeadTitleBar.ButtonClickListener;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskBillModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskDetailModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskBillService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaMessageActivity;
import com.ds365.erp.wms.pda.view.login.activity.LoginActivity;
import com.ds365.erp.wms.pda.view.login.activity.MainActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutawayTaskBillAdapter;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutawayTaskDetailForNotPutawayAdapter;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutawayTaskDetailForNotPutawayAdapter.EditCallback;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutawayTaskDetailForPutawayedAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

public class PutawayTaskDetailActivity extends BasePdaMessageActivity implements EditCallback{
	private CommonTitleTab titleTab;
	private PullToRefreshListView listView;
	private TextView putterNameValue, billCodeValue, relatedBillCodeValue;
	private TextView itemCountValue, unitQtyValue, minUnitQtyValue;
	
	private PutawayTaskDetailForNotPutawayAdapter putawayTaskDetailForNotPutawayAdapter;
	private PutawayTaskDetailForPutawayedAdapter putawayTaskDetailForPutawayedAdapter;
	
	private List<PutawayTaskDetailModel> putawayedDetails = new ArrayList<PutawayTaskDetailModel>();
	private List<PutawayTaskDetailModel> notPutawayDetails = new ArrayList<PutawayTaskDetailModel>();
	
	private List<String> titleTabList = new ArrayList<String>();
	
	private Long billId;
	private static final String NOT_PUTAWAY_TAB_CODE="notPutaway";
	private static final String PUTAWAYED_TAB_CODE="putawayed";
	private  String[]  tabCodes={NOT_PUTAWAY_TAB_CODE,PUTAWAYED_TAB_CODE};

	public static final String SER_KEY = PdaConstants.nextSerKey();
	public static final String REQUEST_CODE = PdaConstants.nextRequestCode();
	public static final String REQUEST_CODE_KEY = PutawayTaskDetailActivity.class.getName();

	private PutawayTaskBillService putawayTaskBillService = new PutawayTaskBillService();
	private int select = 0;
	private Integer editPosition;
	private MessageDatebaseModel message;

	private int resultCode;
	
	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_detail;
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawayTaskDetail_headerview, R.string.putaway_task_operation);
	}
	
	@Override
	protected void initActivityView() {
		itemCountValue = (TextView) findViewById(R.id.putawayTaskDetail_itemCount_value);
		unitQtyValue = (TextView) findViewById(R.id.putawayTaskDetail_unitQty_value);
		minUnitQtyValue = (TextView) findViewById(R.id.putawayTaskDetail_minUnitQty_value);
		billCodeValue = (TextView) findViewById(R.id.putawayTaskDetail_billCode_value);
		relatedBillCodeValue = (TextView) findViewById(R.id.putawayTaskDetail_relatedBillCode_value);
		listView = (PullToRefreshListView) findViewById(R.id.putawayTaskDetail_details);
		putterNameValue = (TextView) findViewById(R.id.putawayTaskDetail_putterName_Value);
		titleTab = (CommonTitleTab) findViewById(R.id.putawayTaskDetail_titleTab);
		titleTabList.add("待上架");
		titleTabList.add("已上架");
		PutawayTaskBillModel putawayTask = null;
		message = (MessageDatebaseModel) getIntent().getSerializableExtra(PdaConstants.MESSAGE_SER_KEY);
		if (message != null) {
			Map<String,String> map = JacksonUtils.stringToMap(message.getParamsMap());
			billId = Long.valueOf(map.get(PdaConstants.MESSAGE_BILLID));
		}else {
			//从上一页传过来 
			putawayTask= (PutawayTaskBillModel) getIntent().getSerializableExtra(PutawayTaskBillAdapter.SER_KEY);
			billId = putawayTask.getId();
			putterNameValue.setText(putawayTask.getPutter().getName());
			billCodeValue.setText(putawayTask.getBillCode());
			relatedBillCodeValue.setText(putawayTask.getRelatedBillCode());
			itemCountValue.setText(String.valueOf(putawayTask.getItemCount()));
			unitQtyValue.setText(String.valueOf(putawayTask.getUnitQty()));
			minUnitQtyValue.setText(String.valueOf(putawayTask.getMinUnitQty()));
			
		}
		
		
		putawayTaskDetailForNotPutawayAdapter = new PutawayTaskDetailForNotPutawayAdapter(context, notPutawayDetails,this);
		putawayTaskDetailForPutawayedAdapter = new PutawayTaskDetailForPutawayedAdapter(context, putawayedDetails);
		ListViewUtil.setOnPullDownListView(listView);
	}

	@Override
	protected void setListener() {
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullDownRefreshListener() {
			@Override
			public void onPullDown() {
				onTabChange();
			}
		}));
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int selectPosition = position-1;
				if (tabCodes[select].equals(NOT_PUTAWAY_TAB_CODE)) {
					Intent intent = new Intent(context, PutawayTaskDetailShowActivity.class);
					Bundle bundle = new Bundle();  
			        bundle.putSerializable(SER_KEY,notPutawayDetails.get(selectPosition));  
			        intent.putExtras(bundle);
			        intent.putExtra(REQUEST_CODE_KEY, REQUEST_CODE);
					startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
				}else if(tabCodes[select].equals(PUTAWAYED_TAB_CODE)){
					Intent intent = new Intent(context, PutawayTaskDetailShowActivity.class);
					Bundle bundle = new Bundle();
			        bundle.putSerializable(SER_KEY,putawayedDetails.get(selectPosition));  
			        intent.putExtras(bundle);
			        intent.putExtra(REQUEST_CODE_KEY, REQUEST_CODE);
					startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
				}
			}
		});
		
		titleTab.setSelectChangeListener(new SelectChangeListener() {
			
			@Override
			public void onItemSelect(int selectValue, RadioButton selectRadioButton) {
				select = selectValue;
				onTabChange();
			}
		});
		titleTab.setData(titleTabList);
	}
	
	private void onTabChange(){
		if (tabCodes[select].equals(NOT_PUTAWAY_TAB_CODE)) {
			listView.setAdapter(putawayTaskDetailForNotPutawayAdapter);
			searchPutawayTaskDetailForNotPutaway();
		}else if (tabCodes[select].equals(PUTAWAYED_TAB_CODE)) {
			listView.setAdapter(putawayTaskDetailForPutawayedAdapter);
			searchPutawayTaskDetailForPutawayed();
		}
	}
	
	private void searchPutawayTaskDetailForNotPutaway() {
		notPutawayDetails.clear();
		putawayTaskBillService.searchPutawayTaskDetailForNotPutawayByBillId(billId,
				new AbstractServiceCallBack<List<PutawayTaskDetailModel>>(context) {

					@Override
					public void doSuccess(List<PutawayTaskDetailModel> result) {
						if (result != null && result.size() > 0) {
							notPutawayDetails.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						// 数据刷新后通知适配器更新UI
						putawayTaskDetailForNotPutawayAdapter.notifyDataSetChanged();
					}
				});
	}
	
	private void searchPutawayTaskDetailForPutawayed() {
		putawayedDetails.clear();
		putawayTaskBillService.searchPutawayTaskDetailForPutawayedByBillId(billId,
				new AbstractServiceCallBack<List<PutawayTaskDetailModel>>(context) {

					@Override
					public void doSuccess(List<PutawayTaskDetailModel> result) {
						if (result != null && result.size() > 0) {
							putawayedDetails.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						putawayTaskDetailForPutawayedAdapter.notifyDataSetChanged();
					}
				});
	}
	
	private void searchPutawayTaskBillById() {
		putawayTaskBillService.searchPutawayTaskBillById(billId, new AbstractServiceCallBack<PutawayTaskBillModel>(context) {

			@Override
			public void doSuccess(PutawayTaskBillModel result) {
				if (result != null) {
					putterNameValue.setText(result.getPutter().getName());
					billCodeValue.setText(result.getBillCode());
					relatedBillCodeValue.setText(result.getRelatedBillCode());
					itemCountValue.setText(String.valueOf(result.getItemCount()));
					unitQtyValue.setText(String.valueOf(result.getUnitQty()));
					minUnitQtyValue.setText(String.valueOf(result.getMinUnitQty()));
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
			}
		});
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		if(resultCode == PutawayTaskDetailEditActivity.RESULT_CODE){
			return;
		}
		onTabChange();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(message != null){
			if(AppGlobal.dbManager!=null&&message.getReadFlag()==0){
				AppGlobal.dbManager.updateMessageReadFlag(message.get_id());
			}
			if(AppGlobal.TOKEN!=null&&!"".equals(AppGlobal.TOKEN)){
				searchPutawayTaskBillById();
			}
		}
	}

	@Override
	protected void checkUserLogin() {
		if(message != null){
			if(GlobalUtils.getSessionUser()==null||AppGlobal.dbManager==null){
				intentMainFlag = true;
				setHeadLeft(new ButtonClickListener() {
					
					@Override
					public void setLeftListener() {
						IntentUtils.startActivity(context,MainActivity.class);
					}
				});
				IntentUtils.startActivity(context,LoginActivity.class,AppConstants.Intent_FROMMESSAGE_KEY,AppConstants.Intent_FROMMESSAGE_KEY,null);
			}	
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
            if(intentMainFlag){
            	IntentUtils.startActivity(context,MainActivity.class);
            	return true;
            }
        }
        return super.onKeyDown(keyCode, event);
	}

	@Override
	public void click(View v) {
		editPosition = (Integer) v.getTag();
		IntentUtils.startActivityBySerialForResult(PutawayTaskDetailActivity.this, PutawayTaskDetailEditActivity.class, SER_KEY
				, notPutawayDetails.get(editPosition), PdaConstants.PDA_REQUEST_CODE);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (intent != null) {
			if (resultCode == PutawayTaskDetailEditActivity.RESULT_CODE) {
				this.resultCode = resultCode;
				PutawayTaskDetailModel putawayTaskDetail = (PutawayTaskDetailModel) intent.getSerializableExtra(PutawayTaskDetailEditActivity.SER_KEY);
				
				notPutawayDetails.set(editPosition, putawayTaskDetail);
				putawayTaskDetailForNotPutawayAdapter.notifyDataSetChanged();
			}
			
		}
	}
}
