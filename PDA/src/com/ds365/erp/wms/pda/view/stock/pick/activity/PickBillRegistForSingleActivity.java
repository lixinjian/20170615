package com.ds365.erp.wms.pda.view.stock.pick.activity;

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
import com.ds365.commons.widget.DateField;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.headtitlebar.SubHeadTitleBar.ButtonClickListener;
import com.ds365.erp.wms.pda.common.utils.GlobalUtils;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullDownRefreshListener;
import com.ds365.erp.wms.pda.model.pickbill.PickBatchDetailModel;
import com.ds365.erp.wms.pda.model.pickbill.PickBillModel;
import com.ds365.erp.wms.pda.service.pick.PickService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaMessageActivity;
import com.ds365.erp.wms.pda.view.login.activity.LoginActivity;
import com.ds365.erp.wms.pda.view.login.activity.MainActivity;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBatchDetailForNotPickAdapter;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBatchDetailForPickAdapter;
import com.ds365.erp.wms.pda.view.stock.pick.adapter.PickBillListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 单条拣货单的商品列表
 */
public class PickBillRegistForSingleActivity extends BasePdaMessageActivity{
	private CommonTitleTab titleTab;
	private PullToRefreshListView listView;
	private PickBatchDetailForNotPickAdapter notPickAdapter;
	private PickBatchDetailForPickAdapter pickAdapter;
	private TextView pickerNameValue, billCodeValue;
	private DateField makeTimeValue;
	
	private Long billId;
	
	private List<PickBatchDetailModel> notPickDetails = new ArrayList<PickBatchDetailModel>();
	private List<PickBatchDetailModel> pickDetails = new ArrayList<PickBatchDetailModel>();
	private List<String> titleTabList = new ArrayList<String>();

	private int select = 0;
	private static final String NOT_PICK_TAB_CODE="notPick";
	private static final String PICKED_TAB_CODE="picked";
	private  String[]  tabCodes={NOT_PICK_TAB_CODE,PICKED_TAB_CODE};
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	
	private PickService pickService = new PickService();
	private MessageDatebaseModel message;

	@Override
	protected int getContentViewId() {
		return R.layout.stock_pick_bill_regist_for_single;
	}
	
	@Override
	protected void initActivityView() {
		billCodeValue = (TextView) findViewById(R.id.pickBillRegistForSingle_billCode_value);
		makeTimeValue = (DateField) findViewById(R.id.pickBillRegistForSingle_makeTime_value);
		listView = (PullToRefreshListView) findViewById(R.id.pickBillRegistForSingle_details);
		pickerNameValue = (TextView) findViewById(R.id.pickBillRegistForSingle_pickerName_Value);
		titleTab = (CommonTitleTab) findViewById(R.id.pickBillRegistForSingle_titleTab);
		titleTabList.add("待拣货");
		titleTabList.add("已拣货");
		PickBillModel pickBillModel = null;
		message = (MessageDatebaseModel) getIntent().getSerializableExtra(PdaConstants.MESSAGE_SER_KEY);
		if (message != null) {
			Map<String,String> map = JacksonUtils.stringToMap(message.getParamsMap());
			billId = Long.valueOf(map.get(PdaConstants.MESSAGE_BILLID));
		}else {
			//从上一页传过来 
			pickBillModel = (PickBillModel) getIntent().getSerializableExtra(PickBillListAdapter.SER_KEY);
			billId = pickBillModel.getId();
			pickerNameValue.setText(pickBillModel.getPicker().getName());
			makeTimeValue.setValue(pickBillModel.getMakeTime());
			billCodeValue.setText(pickBillModel.getBillCode());
			
		}
		notPickAdapter=new PickBatchDetailForNotPickAdapter(context, notPickDetails);
		pickAdapter = new PickBatchDetailForPickAdapter(context, pickDetails);
		ListViewUtil.setOnPullDownListView(listView);
	}

	@Override
	protected void initNavigation() {
		initHeadView(R.id.pickBillRegistForSingle_headerview, R.string.title_pick_bill_manager);
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
				if (tabCodes[select].equals(NOT_PICK_TAB_CODE)) {
					Intent intent = new Intent(context, PickBatchDetailRegistForSingleActivity.class);
					Bundle bundle = new Bundle();  
			        bundle.putSerializable(SER_KEY,notPickDetails.get(position - 1));  
			        intent.putExtras(bundle);
					startActivityForResult(intent, PdaConstants.PDA_REQUEST_CODE);
				}else if (tabCodes[select].equals(PICKED_TAB_CODE)){
					Intent intent = new Intent(context, PickBatchDetailForSingleDetailShowActivity.class);
					Bundle bundle = new Bundle();  
			        bundle.putSerializable(SER_KEY,pickDetails.get(position - 1));  
			        intent.putExtras(bundle);
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
		if (tabCodes[select].equals(NOT_PICK_TAB_CODE)) {
			listView.setAdapter(notPickAdapter);
			searchPickBatchDetailByBillIdForNotPick();
		}else if (tabCodes[select].equals(PICKED_TAB_CODE)) {
			listView.setAdapter(pickAdapter);
			searchPickBatchDetailByBillIdForPicked();
		}
	}
	
	private void searchPickBatchDetailByBillIdForNotPick() {
		notPickDetails.clear();
		pickService.searchPickBatchDetailByBillIdForNotPick(billId,
				new AbstractServiceCallBack<List<PickBatchDetailModel>>(context) {

					@Override
					public void doSuccess(List<PickBatchDetailModel> result) {
						if (result != null && result.size() > 0) {
							notPickDetails.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						notPickAdapter.notifyDataSetChanged();
					}
				});
	}
	
	private void searchPickBatchDetailByBillIdForPicked() {
		pickDetails.clear();
		pickService.searchPickBatchDetailByBillIdForPicked(billId,
				new AbstractServiceCallBack<List<PickBatchDetailModel>>(context) {

					@Override
					public void doSuccess(List<PickBatchDetailModel> result) {
						if (result != null && result.size() > 0) {
							pickDetails.addAll(result);
						}else{
							T.showShort(context, R.string.listview_no_more);
						}
						pickAdapter.notifyDataSetChanged();
					}
				});
	}
	
	private void searchPickBillById() {
		pickService.searchPickBillById(billId, new AbstractServiceCallBack<PickBillModel>(context) {

			@Override
			public void doSuccess(PickBillModel result) {
				if (result != null) {
					pickerNameValue.setText(result.getPicker().getName());
					makeTimeValue.setValue(result.getMakeTime());
					billCodeValue.setText(result.getBillCode());
				}else{
					T.showShort(context, R.string.listview_no_more);
				}
			}
		});
	}
	@Override
	protected void onRestart() {
		super.onRestart();
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
				searchPickBillById();
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
}