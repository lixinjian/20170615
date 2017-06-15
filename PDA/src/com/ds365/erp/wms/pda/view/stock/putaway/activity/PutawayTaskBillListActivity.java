package com.ds365.erp.wms.pda.view.stock.putaway.activity;

import java.util.ArrayList;
import java.util.List;

import com.ds365.commons.AbstractServiceCallBack;
import com.ds365.commons.ServiceCallBack;
import com.ds365.commons.json.QueryResult;
import com.ds365.commons.utils.IntentUtils;
import com.ds365.commons.utils.T;
import com.ds365.commons.widget.CommonTitleTab;
import com.ds365.commons.widget.CommonTitleTab.SelectChangeListener;
import com.ds365.commons.widget.DropDownListView;
import com.ds365.commons.widget.SearchField;
import com.ds365.commons.widget.SearchField.EditTextClearListener;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.ConstantUrl;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.ds365.erp.wms.pda.common.utils.ListViewUtil;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener;
import com.ds365.erp.wms.pda.common.utils.RefreshListViewListener.OnPullUpRefreshListener;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskBillModel;
import com.ds365.erp.wms.pda.model.putaway.PutawayTaskBillQueryParamModel;
import com.ds365.erp.wms.pda.service.putaway.PutawayTaskBillService;
import com.ds365.erp.wms.pda.view.common.activity.BasePdaPageActivity;
import com.ds365.erp.wms.pda.view.common.activity.CodeScanActivity;
import com.ds365.erp.wms.pda.view.stock.putaway.adapter.PutawayTaskBillAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 
 * 说明 ：上架单据列表 
 */
public class PutawayTaskBillListActivity extends BasePdaPageActivity implements OnClickListener{

	private DropDownListView<Long> putawayType;
	private PullToRefreshListView listView;
	private ImageView billCodeScanButton, relatedBillCodeScanButton;
	private TextView searchButton;
	private SearchField billCodeValue;
	private SearchField relatedBillCodeValue;
	private CommonTitleTab titleTab;
	private PutawayTaskBillAdapter adapter;
	
	private List<PutawayTaskBillModel> details = new ArrayList<PutawayTaskBillModel>();
	private List<String> titleTabList = new ArrayList<String>();
	
	public static final String SER_KEY = PdaConstants.nextSerKey();
	private static final int requestCodeForBillCode = Integer.valueOf(PdaConstants.nextRequestCode());
	private static final int requestCodeForRelatedBillCode = Integer.valueOf(PdaConstants.nextRequestCode());
	
	private PutawayTaskBillService putawayTaskBillService = new PutawayTaskBillService();

	private PutawayTaskBillQueryParamModel putawayTaskBillQueryParamModel = new PutawayTaskBillQueryParamModel();
	
	private int select = 0;
	private static final String NOT_PUTAWAY_TAB_CODE="notPutaway";
	private static final String PUTAWAYED_TAB_CODE="putawayed";
	private  String[]  tabCodes={NOT_PUTAWAY_TAB_CODE,PUTAWAYED_TAB_CODE};
	
	
	private ServiceCallBack<QueryResult<PutawayTaskBillModel>> putawayTaskBillServiceCallBack = new AbstractServiceCallBack<QueryResult<PutawayTaskBillModel>>(
			PutawayTaskBillListActivity.this) {

		@Override
		public void doSuccess(QueryResult<PutawayTaskBillModel> result) {
			if (result != null && result.getRecords().size() > 0) {
				details.addAll(result.getRecords());
			}else{
				T.showShort(context, R.string.listview_no_more);
			}
			adapter.notifyDataSetChanged();
		}
	};
	
	@Override
	protected void onRestart() {
		super.onRestart();
		setFirstPage();
		onTabChange(true);
	}
	
	@Override
	protected void initNavigation() {
		initHeadView(R.id.putawayTaskBillList_headerview, R.string.putaway_task_bill_list);
	}

	@Override
	protected int getContentViewId() {
		return R.layout.stock_putaway_task_bill_list;
	}

	@Override
	protected void initActivityView() {
		putawayType = (DropDownListView<Long>) findViewById(R.id.putawayTaskBillList_putawayType_value);
		listView = (PullToRefreshListView) findViewById(R.id.putawayTaskBillList_details);
		billCodeScanButton = (ImageView) findViewById(R.id.putawayTaskBillList_billCodeScan_button);
		searchButton = (TextView) findViewById(R.id.putawayTaskBillList_search_button);
		relatedBillCodeScanButton = (ImageView) findViewById(R.id.putawayTaskBillList_relatedBillCodeScan_button);
		billCodeValue = (SearchField) findViewById(R.id.putawayTaskBillList_billCode_value);
		relatedBillCodeValue = (SearchField) findViewById(R.id.putawayTaskBillList_relatedBillCode_value);
		adapter = new PutawayTaskBillAdapter(context, details);
		listView.setAdapter(adapter);
		ListViewUtil.setOnPullBothListView(listView);
		titleTab = (CommonTitleTab) findViewById(R.id.putawayTaskBillList_titleTab);
		titleTabList.add("未上架");
		titleTabList.add("已上架");
		getPutawayType();
	}

	@Override
	protected void setListener() {
		billCodeScanButton.setOnClickListener(this);
		searchButton.setOnClickListener(this);
		relatedBillCodeScanButton.setOnClickListener(this);
		
		titleTab.setSelectChangeListener(new SelectChangeListener() {
			
			@Override
			public void onItemSelect(int selectValue, RadioButton selectRadioButton) {
				select = selectValue;
				billCodeValue.setValue("");
				relatedBillCodeValue.setValue("");
				setFirstPage();
				onTabChange(true);
			}
		});
		titleTab.setData(titleTabList);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IntentUtils.startActivityForSeria(context, PutawayTaskBillShowActivity.class
						, SER_KEY, details.get(position-1), null);
			}
		});
		
		listView.setOnRefreshListener(new RefreshListViewListener(listView, new OnPullUpRefreshListener() {

			@Override
			public void onPullDown() {
					setFirstPage();
					onTabChange(true);
			}

			@Override
			public void onPullUp() {
					setNextPage();
					onTabChange(false);
			}
		}));
		
		billCodeValue.setEditTextClearListener(new EditTextClearListener() {
			
			@Override
			public void onEditTextClear() {
				setFirstPage();
				onTabChange(true);
			}
		});
		relatedBillCodeValue.setEditTextClearListener(new EditTextClearListener() {
			
			@Override
			public void onEditTextClear() {
				setFirstPage();
				onTabChange(true);
			}
		});
	}
	
	private void setParams(){
		String billCode = billCodeValue.getValue();
		String relatedBillCode = relatedBillCodeValue.getValue();
		putawayTaskBillQueryParamModel.setStart(start);
		putawayTaskBillQueryParamModel.setLimit(PdaConstants.LIMIT);
		putawayTaskBillQueryParamModel.setBillCode(billCode);
		putawayTaskBillQueryParamModel.setRelatedBillCode(relatedBillCode);
//		params.getParams().put("putawayTypeId", String.valueOf(putawayType.getValue()));
	}
	
	private void onTabChange(boolean isClearListView){
		setParams();
		if (tabCodes[select].equals(NOT_PUTAWAY_TAB_CODE)) {
			adapter.setPutawayButtonShowFlag(true);
			searchPagePutawayTaskBillsForNotPutaway(isClearListView);
		}else if (tabCodes[select].equals(PUTAWAYED_TAB_CODE)) {
			adapter.setPutawayButtonShowFlag(false);
			searchPagePutawayTaskBillsForPutawayed(isClearListView);
		}
	}
	
	private void searchPagePutawayTaskBillsForNotPutaway(boolean isClearListView){
		if (isClearListView) {
			details.clear();
		}
		putawayTaskBillService.searchPagePutawayTaskBillsForNotPutaway(putawayTaskBillQueryParamModel,
				putawayTaskBillServiceCallBack);
	}
	
	private void searchPagePutawayTaskBillsForPutawayed(boolean isClearListView){
		if (isClearListView) {
			details.clear();
		}
		putawayTaskBillService.searchPagePutawayTaskBillsForPutawayed(putawayTaskBillQueryParamModel,
				putawayTaskBillServiceCallBack);
	}
	
	private void getPutawayType() {
		//此处为网络获取上架类型
		/*EnumService enumService = new EnumService();
		enumService.getEnumsForJsonResult(new AbstractServiceCallBack<List<EnumModel<Long>>>(context) {
			
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
				
//				setFirstPage();
//				params.setUrl(ConstantUrl.putaway_putawayBill_searchPagePutawayTaskBillsForNotPutaway);
//				params.getParams().put("putawayTypeId", String.valueOf(putawayType.getValue()));
//				getData(PdaConstants.CLEAR_LISTVIEW_YES,params); 
			}
		});*/
		putawayType.setUrl(ConstantUrl.common_getPutawayTypeEnumsForJsonResult);
	}

	@Override
	public void onClick(View v) {
		//获取输入框的值
		switch (v.getId()) {
		case R.id.putawayTaskBillList_search_button:
			onTabChange(true);
			break;
		case R.id.putawayTaskBillList_billCodeScan_button:
			IntentUtils.startActivityForResult(PutawayTaskBillListActivity.this
					, CodeScanActivity.class, requestCodeForBillCode);
			break;
		case R.id.putawayTaskBillList_relatedBillCodeScan_button:
			IntentUtils.startActivityForResult(PutawayTaskBillListActivity.this
					, CodeScanActivity.class, requestCodeForRelatedBillCode);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if(requestCode == requestCodeForBillCode && resultCode == CodeScanActivity.RESULT_CODE){
				billCodeValue.setValue(data.getStringExtra(PdaConstants.scanResult));
			}else if(requestCode == requestCodeForRelatedBillCode && resultCode == CodeScanActivity.RESULT_CODE){
				relatedBillCodeValue.setValue(data.getStringExtra(PdaConstants.scanResult));
			}
		}
	}
	
}
